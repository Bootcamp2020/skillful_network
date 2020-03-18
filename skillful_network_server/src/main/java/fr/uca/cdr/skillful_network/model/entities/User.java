package fr.uca.cdr.skillful_network.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 2, max = 20, message = "firstName must be between 2 and 20 characters")
    private String firstName;
    @Size(min = 2, max = 20, message = "lastName must be between 2 and 20 characters")
	private String lastName;
    @Size(min = 8, message = "password must be at least 8 characters")
	private String password;
	@PastOrPresent
	private Date birthDate;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	private String email;
	private String mobileNumber;
	private boolean validated = false;
	private Subscription subscription;
	
	public User() {
		super();
	}
	
	public User(long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
    
	public User(Subscription subscription) {
		super();
		this.subscription = subscription;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public User(long id, String firstName, String lastName, String password, Date birthDate, String email, String mobileNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.birthDate = birthDate;
		this.email = email.toLowerCase();
		this.mobileNumber = mobileNumber;
		this.validated = true;
	}

	
	public User(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		this.email = email.toLowerCase();
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", birthDate=" + birthDate + ", email=" + email + ", mobileNumber=" + mobileNumber + ", validated="
				+ validated + ", subscription=" + subscription + "]";
	}
	
	
	
}