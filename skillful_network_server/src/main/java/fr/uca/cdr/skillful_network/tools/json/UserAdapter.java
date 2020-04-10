package fr.uca.cdr.skillful_network.tools.json;

import java.lang.reflect.Type;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import fr.uca.cdr.skillful_network.model.entities.User;

public class UserAdapter implements JsonDeserializer<User>{

	@Override
	public User deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext jsonDeserializationContext)
			throws JsonParseException {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		Gson gson = new Gson();
		String jsonInString;
		
		// Convertie le jsonElement en user
		JsonObject jsonObjectUser = jsonElement.getAsJsonObject();
		user = jsonDeserializationContext.deserialize(jsonElement, User.class);
		
		// Encode le password du user
		user.setPassword(encoder.encode(user.getPassword()));

		//Convertie le user en jsonElement en 3 étapes : user --> string --> jsonObject --> jsonElement
		jsonInString = gson.toJson(user);
		JsonObject jsonObject = new JsonParser().parse(jsonInString).getAsJsonObject();
		jsonElement = (JsonElement) jsonObject;
			
		return jsonDeserializationContext.deserialize(jsonElement, User.class);

	}

}
