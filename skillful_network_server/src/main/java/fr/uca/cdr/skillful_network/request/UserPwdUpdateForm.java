package fr.uca.cdr.skillful_network.request;

import javax.validation.constraints.Size;

public class UserPwdUpdateForm {
	
	@Size(min=2, max=20 , message = "Le mot de passe doit comporter au minimum 2 caractères")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
