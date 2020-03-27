package fr.uca.cdr.skillful_network.jwt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.uca.cdr.skillful_network.model.entities.User;

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
	
	public String decryptJwtToken(String frontToken) throws JsonMappingException, JsonProcessingException {
		String line = "";
		String scriptResponse="";
		String choice = "decrypt"; // encrypt ou decrypt
		String url = "src/main/resources/data/script/scriptToken.py"; // à modifier suivant l'emplacement du script et de votre os
		String cmd = "python3" + " " + url + " " + choice + " " + frontToken; // La commande python3 est aussi à adapter suivant les os
		
		
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = in.readLine()) != null) {
				// On récupère l'utilisateur
				scriptResponse = line;
				System.out.println(scriptResponse);
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		
		return scriptResponse;
    }
	
	public boolean validateToken(String decryptResponse) {
		return (!(decryptResponse.equals("-5") || decryptResponse.equals("-4")));
	}
	
	public String getEmailFromToken(String jsonDecryptResponse) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> map = mapper.readValue(jsonDecryptResponse, Map.class);
		String email = (String) map.get("email");
		System.out.println("Email récupéré depuis le Json : " + email );
		return email;
	}
	
	public User getUserfromJson(String jsonDecryptResponse) throws JsonMappingException, JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> map = mapper.readValue(jsonDecryptResponse, Map.class);
		
		Long id = Long.parseLong((String)map.get("id"));
		String email = (String)map.get("email");
		String password = (String) map.get("password");
		
		System.out.println("id : "+ id +"\nemail : "+email+"\npassword : "+password);
		
		User userFromJson = new User();
		userFromJson.setId(id);
		userFromJson.setEmail(email);
		userFromJson.setPassword(password);
		
		System.out.println("User from json : \n" + userFromJson.toString());
		return userFromJson;
	}
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
		
		JwtProvider jwtProvider = new JwtProvider();
		String token = jwtProvider.generateJwtToken(1L, "John@uca.fr", "azertyyyy");
//		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpZCI6IjEiLCJlbWFpbCI6IkpvaG5AdWNhLmZyIiwicGFzc3dvcmQiOiJhemVydHl5eXkiLCJleHAiOjE1ODUzMDQ5MzJ9.syA5JoTmDZlNxWoG5Qnsy11mlA9bVlPuTowPRO6zX7BErmOAHRVi8VnNhKDYO0MA";
		String decryptResponse = jwtProvider.decryptJwtToken(token);
		System.out.println("token décrypté : " + decryptResponse +"\ncompareTo -5: "+ decryptResponse.equals("-5"));
		System.out.println("token valide ? " + jwtProvider.validateToken(decryptResponse));
		String email = jwtProvider.getEmailFromToken(decryptResponse);
		User userFromJson = jwtProvider.getUserfromJson(decryptResponse);
		System.out.println(userFromJson.getEmail());
		
		
	}
}