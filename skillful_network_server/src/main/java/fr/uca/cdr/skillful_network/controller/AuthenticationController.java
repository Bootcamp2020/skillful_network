package fr.uca.cdr.skillful_network.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Cette classe a pour rôle d'identifié les utilisateurs.
 * L'authentification des utilisateurs se fait grâce à l'email ou au numéro de mobile (en tant que nom d'utilisateur)
 * ainsi qu'avec un code temporaire envoyé par le serveur à l'email ou au numéro de mobile.
 * Elle est responsable de notamment du traitement des requêtes /login et /token.
 */
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    /**
     * @param login the email or the mobile number of the user
     * @return the id of the user
     */
    @RequestMapping(value = "/login", method = POST)
    public Number login(@RequestBody String login) {
        return -1;
    }
}
