package fr.uca.cdr.skillful_network.model.services;

import java.util.Optional;

import fr.uca.cdr.skillful_network.model.entities.User;

public interface UserService {
      Boolean alreadyExists(String mail);
      Boolean existingMailIsValidated(String mail);
      User getUserById(long id);
	  User saveOrUpdateUser(User user);
	  void deleteUser(Long id);
      
      /**
       * Il retourne le user s'il le trouve par son adresse email
       * @param String 
       * @return optional user
       */
      Optional<User> findByEmail(String mail);
      
}
