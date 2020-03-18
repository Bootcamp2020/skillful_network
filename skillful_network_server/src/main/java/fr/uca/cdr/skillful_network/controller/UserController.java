package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.security.CodeGeneration;
import fr.uca.cdr.skillful_network.security.SendMail;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.validation.Valid;


/**
 * Cette classe est responsable du traitement des requêtes liées aux utilisateurs comme /users.
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    /**
     *
     */
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
        
    }

    @RequestMapping(value = "/users")
    public List<User> getUsers(){
        return (List<User>) this.repository.findAll();
    }
    


}
