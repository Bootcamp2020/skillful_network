package fr.uca.cdr.skillful_network.model.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import fr.uca.cdr.skillful_network.model.entities.User;

public interface UserService {
	Boolean alreadyExists(String mail);

	Boolean existingMailIsValidated(String mail);

	Optional<User> getUserById(long id);

	User saveOrUpdateUser(User user);

	void deleteUser(Long id);
	
	void sendMail(String mail , String code);
	Boolean createRepoImage();
    Boolean updateImage();

}
