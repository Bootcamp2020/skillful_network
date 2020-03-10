package fr.uca.cdr.skillful_network.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;

/**
 * Cette classe a pour rôle d'identifié les utilisateurs. L'authentification des
 * utilisateurs se fait grâce à l'email ou au numéro de mobile (en tant que nom
 * d'utilisateur) ainsi qu'avec un code temporaire envoyé par le serveur à
 * l'email ou au numéro de mobile. Elle est responsable de notamment du
 * traitement des requêtes /login et /token.
 */
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

	/**
	 * @param login the email or the mobile number of the user
	 * @return the id of the user
	 */

	@Autowired
	public UserRepository userRepository;

	@GetMapping(value = "/login")
	public List<User> login(@RequestBody String login) {
		return userRepository.findAll();
	}

	@PostMapping(value = "/login/v1")
	public ResponseEntity<User> checkpass(@RequestBody User user) throws Exception {

		if (user != null) {
			User userFromDB = userRepository.findByEmail(user.getEmail())
					.orElseThrow(() -> new Exception("email not authenticated, user not found: ")); // User object :
																									// userFromDB
																									// depending on
																									// user.getEmail()

			String passwordFromDB = userRepository.findById(userFromDB.getId())
					.orElseThrow(() -> new Exception("user not found: ")).getPassword(); // get password depending on
																							// userFromDB

			String passwpordFromInput = user.getPassword(); // get password from user value INPUT

			if (!passwordFromDB.equals(passwpordFromInput)) {
				System.out.println("pas le même mdp");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				
				// httpStatus.OK
			}
			System.out.println("ok");
			return ResponseEntity.ok().body(userFromDB);
		}
		System.out.println("pas user trouvé");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
