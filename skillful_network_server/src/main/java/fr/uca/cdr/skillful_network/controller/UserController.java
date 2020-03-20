package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.security.CodeGeneration;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.model.services.UserService;
import fr.uca.cdr.skillful_network.request.UserForm;
import fr.uca.cdr.skillful_network.request.UserPwdUpdateForm;


/**
 * Cette classe est responsable du traitement des requêtes liées aux
 * utilisateurs comme /users.
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
				userToUpdate.setSkillSet(userRequest.getSkillSet());
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
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = { MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE })
	public String fileUpload(@RequestParam("image") MultipartFile image) throws IOException {
		File convertFile = new File("WebContent/images/" + image.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(image.getBytes());
		fout.close();
		return "File is upload successfully";
	}

}
