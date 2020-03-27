package fr.uca.cdr.skillful_network.security;

import java.time.LocalDateTime;


public class TestValiditeCode {
	
	public Boolean estValide (LocalDateTime dateExpiration,LocalDateTime currentDate ) {  
		Boolean codeExpire = currentDate.isAfter(dateExpiration);
		return codeExpire;
	}
	
}
