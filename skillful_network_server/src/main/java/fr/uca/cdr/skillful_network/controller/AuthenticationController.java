package fr.uca.cdr.skillful_network.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.uca.cdr.skillful_network.jwt.JwtProvider;
import fr.uca.cdr.skillful_network.jwt.response.JwtResponse;
import fr.uca.cdr.skillful_network.model.entities.Role;
import fr.uca.cdr.skillful_network.model.entities.Rolename;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.RoleRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.model.services.UserService;
import fr.uca.cdr.skillful_network.request.LoginForm;
import fr.uca.cdr.skillful_network.request.RegisterForm;
import fr.uca.cdr.skillful_network.security.CodeGeneration;
import fr.uca.cdr.skillful_network.security.services.UserPrinciple;

/**
 * Cette classe a pour rôle d'identifié les utilisateurs. L'authentification des
 * utilisateurs se fait grâce à l'email ou au numéro de mobile (en tant que nom
 * d'utilisateur) ainsi qu'avec un code temporaire envoyé par le serveur à
 * l'email ou au numéro de mobile. Elle est responsable de notamment du
 * traitement des requêtes /login et /token.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping ("/Authentication")
public class AuthenticationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Value("${spring.profiles.active}")
	private String activeProfil;

	@Autowired
	private JwtProvider jwtProv;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		if (loginRequest != null) {

			Optional<User> userFromDB = userService.findByEmail(loginRequest.getEmail());

			if (!userFromDB.isPresent()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé");
			} else if (!userFromDB.get().isValidated()) {
				LocalDateTime dateExpirationMdp = userFromDB.get().getTemporaryCodeExpirationDate();
				Boolean isExpired = userService.mdpExpired(dateExpirationMdp, LocalDateTime.now());
				userService.validationMdp(isExpired, userFromDB);
				if (isExpired) {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
							"Le mot de passe temporaire n'est plus valide ; veuillez relancer une inscription !");
				}
			}

			String passwordFromDB = userFromDB.get().getPassword();
			String passwordRequest = loginRequest.getPassword();
			boolean passwordMatches = encoder.matches(passwordRequest, passwordFromDB);
			System.out.println("Mots de passes correspondent ? " + passwordMatches);
			if (passwordRequest == null || !passwordMatches) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Les 2 mots de passe ne correspondent pas");
			} else {

				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
				System.out.println("UserPrinciple récupéré : " + userPrinciple.toString());

				// On génère un token en fonction de l'id, l'email et le password de
				// l'utilisateur
				String jwt = jwtProv.generateJwtToken(authentication);
				System.out.println("jwt dans AuthController : " + jwt);

				// On retourne une jwt response qui contient le token et l'utilisateur
				return ResponseEntity.ok(new JwtResponse(jwt, userPrinciple, userPrinciple.getAuthorities()));
			}
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé");

	}

	@RequestMapping(value = "/register", method = POST)
	public ResponseEntity<?> ifFirstConnection(@Valid @RequestBody RegisterForm registerForm) {
		if (userService.alreadyExists(registerForm.getEmail())) {
			if (userService.existingMailIsValidated(registerForm.getEmail()) == true) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {

				Optional<User> oOldUser = userRepository.findByEmail(registerForm.getEmail());
				userRepository.delete(oOldUser.get());
			}
		}
		String randomCode = CodeGeneration.generateCode(10);
		if (activeProfil.contains("prod")) {
			// Send Message!
			userService.sendMail(registerForm.getEmail(), randomCode);
		}
		User user = new User();
		user.setEmail(registerForm.getEmail());
		user.setTemporaryCodeExpirationDate(LocalDateTime.now().plus(24, ChronoUnit.HOURS));
		// On crypte avec bcrypt le mot de passe dans la bdd
		String randomCodeEncrypt = encoder.encode(randomCode);
		user.setPassword(randomCodeEncrypt);
		//Récupération du rôle qui a été choisi dans le formulaire=>role user par defaut, prévision choix de 3 rôles dans le formulaire rgister
		Set<String> strRoles = registerForm.getRole();
		Set<Role> roles= new HashSet<>();
		//Verification des rôles existant dans le RoleRepository et attribution des rôles au current user
		strRoles.forEach(selectedRole->{
			switch (selectedRole) {
//				case "user":
//					Role userRole = roleRepository.findByName(Rolename.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Fail! -> Cause : User role not found"));
//					roles.add(userRole);
			case "organisme":
				Role organismeRole = roleRepository.findByName(Rolename.ROLE_ORGANISME)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause : Organisme role not found"));
				roles.add(organismeRole);
			case "entreprise":
				Role entrepriseRole = roleRepository.findByName(Rolename.ROLE_ENTREPRISE)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause : Entreprise role not found"));
				roles.add(entrepriseRole);
			default:
				Role userRole = roleRepository.findByName(Rolename.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause : default role not found"));
				roles.add(userRole);
			}
		});
		//Sauvegarde des rôles pour le user
		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/passwordForgotten", method = POST)
	public ResponseEntity<?> ifForgotPassword(@Valid @RequestBody User user) {
		if (userService.alreadyExists(user.getEmail())) {
			if (userService.existingMailIsValidated(user.getEmail())) {
				Optional<User> userFDb = userRepository.findByEmail(user.getEmail());
				userFDb.get().setValidated(false);
				String randomCode = CodeGeneration.generateCode(10);
				if (activeProfil.contains("prod")) {
					// Send Message!
					userService.sendMail(user.getEmail(), randomCode);
				}
				userFDb.get().setPassword(randomCode);
				userService.saveOrUpdateUser(userFDb.get());
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			} else {
				Optional<User> oOldUser = userRepository.findByEmail(user.getEmail());
				userRepository.delete(oOldUser.get());
			}
		}
		String randomCode = CodeGeneration.generateCode(10);
		if (activeProfil.contains("prod")) {
			// Send Message!
			userService.sendMail(user.getEmail(), randomCode);
		}
		user.setPassword(randomCode);
		userRepository.save(user);
		return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	}

//	A update plus tard pour récupérer le token dans le header
	@PostMapping(value = "/whoami")
	public ResponseEntity<?> whoAmI(@RequestBody String frontToken)
			throws JsonMappingException, JsonProcessingException {
		String decryptResponse = jwtProv.decryptJwtToken(frontToken);
		if (!(jwtProv.validateToken(decryptResponse))) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalide ou expiré");
		} else {
			User userFromJson = jwtProv.getUserFromJson(decryptResponse);
			User userFromDb = userService.getUserById(userFromJson.getId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé"));
			boolean passwordMatches = encoder.matches(userFromJson.getPassword(), userFromDb.getPassword());
			System.out.println("Mots de passes correspondent ? " + passwordMatches);
			if (!(userFromJson.getEmail().equals(userFromDb.getEmail()) && passwordMatches)) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
						"L'utilisateur retrouvé à partir du token et celui dans la base de donnée ne correspondent pas");
			} else {
				return new ResponseEntity<String>("Le token et les informations utilisateurs sont valides",
						HttpStatus.OK);
			}
		}
	}
}
