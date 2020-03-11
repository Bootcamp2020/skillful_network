package fr.uca.cdr.skillful_network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.model.services.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Optional;

import javax.validation.Valid;

/**
 * Cette classe a pour rôle d'identifié les utilisateurs.
 * L'authentification des utilisateurs se fait grâce à l'email ou au numéro de mobile (en tant que nom d'utilisateur)
 * ainsi qu'avec un code temporaire envoyé par le serveur à l'email ou au numéro de mobile.
 * Elle est responsable de notamment du traitement des requêtes /login et /token.
 */
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {
	
     @Autowired
     private UserRepository userRepository;
     
     @Autowired
     private UserService userService;

    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity<?> ifFirstConnection(@Valid @RequestBody User user) {
    	if (userService.alreadyExists(user.getEmail())) {
    		if(userService.existingMailIsValidated(user.getEmail())== true)
    		     return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    		}
    	return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
