package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.exceptions.ResourceNotFoundException;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.model.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Cette classe est responsable du traitement des requêtes liées aux utilisateurs comme /users.
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

 	@Autowired
    private final UserRepository repository;
	@Autowired
	private UserService userService;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return (List<User>) this.repository.findAll();
    }
    @Transactional
    @PutMapping(value="/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value="id") long id, @Valid @RequestBody User user){
    	User userToUpdate = userService.getUserById(id);    	
    	if(userToUpdate == null) {
    		throw new ResourceNotFoundException("User not found for id " + id);
    	}    	
    	userToUpdate.setEmail(user.getEmail());
    	userToUpdate.setPassword(user.getPassword());
    	User userUpdated = userService.saveOrUpdateUser(userToUpdate);
		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
    	
    }


}
