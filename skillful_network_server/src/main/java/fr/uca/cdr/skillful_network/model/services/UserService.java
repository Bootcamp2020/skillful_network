package fr.uca.cdr.skillful_network.model.services;

import java.util.Optional;

import fr.uca.cdr.skillful_network.model.entities.User;

public interface UserService {
      Boolean alreadyExists(String mail);
      Boolean existingMailIsValidated(String mail);
      Boolean createRepoImage();
    Boolean updateImage();
    Optional<User> getUserbyId(Long id);
	  
//	boolean getdImagebyId(Long id);

	 
	 
	
	
}
