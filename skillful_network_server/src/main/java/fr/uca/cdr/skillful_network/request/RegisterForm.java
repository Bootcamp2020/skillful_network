package fr.uca.cdr.skillful_network.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class RegisterForm {
	
	@NotNull
	@Size(min=8,max=20)
	private String password;
	
	@NotNull
	@Email
	private String email;
	
	private Set<String> role;	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public RegisterForm(@Size(min = 8, max = 20) String password, @Email String email, Set<String> role) {
		super();
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	

}
