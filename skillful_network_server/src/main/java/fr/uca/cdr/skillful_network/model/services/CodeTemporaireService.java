package fr.uca.cdr.skillful_network.model.services;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import fr.uca.cdr.skillful_network.model.entities.CodeTemporaire;

public interface CodeTemporaireService {
	
	Boolean isExpired(LocalDateTime dateExpiration,LocalDateTime currentDate );
	
	CodeTemporaire getMotDePasseByPwd(String pwd);
	
	ResponseEntity<String> testValidite(String token);
}
