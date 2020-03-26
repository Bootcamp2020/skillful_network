package fr.uca.cdr.skillful_network.jwt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
	public String generateJwtToken(Long id, String email, String password) {
		String line = "";
		String jwt = "";
		String choice = "encrypt"; // encrypt ou decrypt
		String code = id + " " + email + " " + password ;
		String url = "src/main/resources/data/script/scriptToken.py"; // à modifier suivant l'emplacement du script et de votre os
		String cmd = "python3" + " " + url + " " + choice + " " + code; // La commande python3 est aussi à adapter suivant les os
		
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = in.readLine()) != null) {
				// On récupère le token
				jwt += line;
				System.out.println(jwt);
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return jwt;
    }
}