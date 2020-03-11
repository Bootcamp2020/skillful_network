package fr.uca.cdr.skillful_network.security;

public class CodeGeneration {

	public static String generateCode(int length) {
		// définition des caractères possibles
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		//utilisation String builder pour limiter ressources utilisées
		StringBuilder pass = new StringBuilder();
		for (int x = 0; x < length; x++) {
			// tirage au sort d'un caractère et rajout au String builder
			int i = (int) Math.floor(Math.random() * 62); 
			pass.append(chars.charAt(i));
		}
		//System.out.println(pass);
		return pass.toString();
	}

}
