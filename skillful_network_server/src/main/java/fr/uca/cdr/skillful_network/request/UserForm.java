package fr.uca.cdr.skillful_network.request;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.entities.Subscription;

public class UserForm {

	@Size(min = 2, max = 20, message = "firstName must be between 2 and 20 characters")
	private String firstName;
	@Size(min = 2, max = 20, message = "lastName must be between 2 and 20 characters")
	private String lastName;
	@PastOrPresent
	private Date birthDate;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	private String email;
	private String mobileNumber;
	private Set<Skill>skillSet = new HashSet<Skill>();
	private Set<Qualification> qualificationSet;
	private Set<Subscription> subscriptionSet = new HashSet<Subscription>();
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<Skill> getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(Set<Skill> skillSet) {
		this.skillSet = skillSet;
	}

	public Set<Qualification> getQualificationSet() {
		return qualificationSet;
	}

	public void setQualificationSet(Set<Qualification> qualificationSet) {
		this.qualificationSet = qualificationSet;
	}

	public Set<Subscription> getSubscriptionSet() {
		return subscriptionSet;
	}

	public void setSubscriptionSet(Set<Subscription> subscriptionSet) {
		this.subscriptionSet = subscriptionSet;
	}

	
	
}
