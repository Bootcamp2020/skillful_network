package fr.uca.cdr.skillful_network.service;

import org.springframework.beans.factory.annotation.Autowired;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;

public class UserService {
	
//	@Autowired
//	private UserRepository userRepository;
	
	/**
	 * methode bla bla bla
	 * @param login
	 * @param password
	 * @return
	 */
	public User getUser(String login,String password){
		User user = new User();
		
		//TODO appeler la methode findUserByLoginAndPassword(string login, string pawd)
		return user; 
	}
}
