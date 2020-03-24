package fr.uca.cdr.skillful_network.security;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CodeGeneration {
	
	private static StringBuilder pass;

	public static String generateCode(int length) {
		
		// définition de la date de création du pass
				LocalDateTime currentTime = LocalDateTime.now();
				String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
				Boolean isAfter = false;

				pass = new StringBuilder();
				
				System.out.println(currentTime);
				// définition de la date d'expiration du pass
				LocalDateTime dateExpiration = currentTime.plus(24, ChronoUnit.HOURS);
				System.out.println(dateExpiration);
				// calcul et vérification de la durée de validité du pass
				Duration d = Duration.between(currentTime, dateExpiration);
				System.out.println(d.getSeconds());
				// valeur booléenne passant à false quand le groupe date/heure réel a dépassé la
				// date d'expiration
				isAfter = currentTime.isAfter(dateExpiration);
				System.out.println(isAfter);

				// test de la date réelle par rapport à la date d'expiration
				if (isAfter == true) {
					// le mot de passe devient null lors isAfter est true
					pass = null;
				}
				

				for (int x = 0; x < length; x++) {
					// tirage au sort d'un caract�re et rajout au String builder
					int i = (int) Math.floor(Math.random() * 62);
					pass.append(chars.charAt(i));
				}

				System.out.println(pass.toString());
				return pass.toString();
			}
}
