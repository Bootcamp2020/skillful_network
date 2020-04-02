package fr.uca.cdr.skillful_network.security;

public class CodeGeneration {
	
	private static StringBuilder pass;

	public static String generateCode(int length) {
		
		// définition de la date de création du pass
				
				String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
				pass = new StringBuilder();
				for (int x = 0; x < length; x++) {
					// tirage au sort d'un caract�re et rajout au String builder
					int i = (int) Math.floor(Math.random() * 62);
					pass.append(chars.charAt(i));
				}

				System.out.println(pass.toString());
				return pass.toString();
			}
}
