package fr.uca.cdr.skillful_network.jwt.response;

import fr.uca.cdr.skillful_network.model.entities.User;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private User user;
	
	public JwtResponse(String accessToken, User user) {
		this.token = accessToken;
		this.user = user;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}