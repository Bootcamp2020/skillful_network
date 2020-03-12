package fr.uca.cdr.skillful_network.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Boolean alreadyExists(String mail) {
		Optional<User> oUser = userRepository.findByEmail(mail);
		return oUser.isPresent();
	}

	@Override
	public Boolean existingMailIsValidated(String mail) {
		return userRepository.findByEmail(mail).get().isValidated();
	} 
}
