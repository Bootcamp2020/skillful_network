package fr.uca.cdr.skillful_network.model.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

      
      /**
       * Il retourne le user s'il le trouve par son adresse email
       * @param String 
       * @return optional user
       */
      Optional<User> findByEmail(String mail);
      
      Page<User> getUsersByPage(Pageable pageable);
}
