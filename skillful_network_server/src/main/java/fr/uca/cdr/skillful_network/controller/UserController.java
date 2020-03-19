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
    
	@GetMapping(value = "/users")
	public List<User> getUsers() {
		return (List<User>) this.repository.findAll();
	}

	@Transactional
	@PutMapping(value = "/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @Valid @RequestBody UserForm userRequest) {

		if (userService.getUserById(id).isPresent()) {
			User userToUpdate = userService.getUserById(id).get();
			if (userRequest != null) {
				userToUpdate.setLastName(userRequest.getLastName());
				userToUpdate.setFirstName(userRequest.getFirstName());
				userToUpdate.setBirthDate(userRequest.getBirthDate());
				userToUpdate.setEmail(userRequest.getEmail());
				userToUpdate.setMobileNumber(userRequest.getMobileNumber());
				userToUpdate.setQualificationSet(userRequest.getQualificationSet());
				User userUpdated = userService.saveOrUpdateUser(userToUpdate);
				return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aucune données en paramètre");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé");

		}
	}
	@Transactional
	@PutMapping(value = "/usersModifPassword/{id}")
	public ResponseEntity<User> updateUserPassword(@PathVariable(value="id") long id, @Valid @RequestBody UserPwdUpdateForm userModifPwd){
		
		User userToUpdate = userService.getUserById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Aucun utilisateur trouvé avec l'id " + id));
		
		userToUpdate.setPassword(userModifPwd.getPassword());
		User userUpdated = userService.saveOrUpdateUser(userToUpdate);
		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
    	
    }


}
