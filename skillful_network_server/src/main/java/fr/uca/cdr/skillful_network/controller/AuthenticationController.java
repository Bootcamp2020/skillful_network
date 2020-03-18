package fr.uca.cdr.skillful_network.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.request.LoginForm;
import fr.uca.cdr.skillful_network.security.CodeGeneration;
import fr.uca.cdr.skillful_network.security.SendMail;
import fr.uca.cdr.skillful_network.model.services.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
	
     @Autowired
     private UserRepository userRepository;
     
     @Autowired
     private UserService userService;

	@PostMapping(value = "/login")
	public ResponseEntity<User> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		if (loginRequest != null) {
			
			Optional<User> userFromDB = userService.findByEmail(loginRequest.getEmail());
			
			
			if (!userFromDB.isPresent()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé");
			} else {
				String passwordFromDB = userFromDB.get().getPassword();
				String passwordRequest = loginRequest.getPassword();
				if (passwordRequest != null && !passwordRequest.equals(passwordFromDB)) {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Les 2 mots de passe ne correspondent pas");
				} else {
					return ResponseEntity.ok().body(userFromDB.get());
				}
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé");
	}

    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity<?> ifFirstConnection(@Valid @RequestBody User user) {
    	if (userService.alreadyExists(user.getEmail())) {
    		if(userService.existingMailIsValidated(user.getEmail())== true) {
    		     return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    		} else {
    			Optional<User> oOldUser = userRepository.findByEmail(user.getEmail());
    	    	userRepository.delete(oOldUser.get());	
    	    }
    	}
    	String randomCode = CodeGeneration.generateCode(10);
    	SendMail.envoyerMailSMTP(user.getEmail(), randomCode);
    	user.setPassword(randomCode);
    	userRepository.save(user);
    	return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
