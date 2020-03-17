package fr.uca.cdr.skillful_network.model.services;



import fr.uca.cdr.skillful_network.model.entities.User;

public interface UserService {
      Boolean alreadyExists(String mail);
      Boolean existingMailIsValidated(String mail);
      User getUserById(long id);
	  User saveOrUpdateUser(User user);
	  void deleteUser(Long id);
}
