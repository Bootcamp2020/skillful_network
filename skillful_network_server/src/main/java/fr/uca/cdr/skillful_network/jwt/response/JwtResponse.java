package fr.uca.cdr.skillful_network.jwt.response;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import fr.uca.cdr.skillful_network.security.services.UserPrinciple;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private UserPrinciple user;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, UserPrinciple user, Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.user = user;
		this.authorities = authorities;
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

	public UserPrinciple getUser() {
		return user;
	}

	public void setUser(UserPrinciple user) {
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}