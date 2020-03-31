package fr.uca.cdr.skillful_network.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	private String status;
	private boolean validated = false;
    private String careerGoal;
	private boolean photo= false;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Skill>skillSet = new HashSet<Skill>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Qualification> qualificationSet = new HashSet<Qualification>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Subscription> subscriptionSet = new HashSet<Subscription>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<JobApplication> jobApplicationSet = new HashSet<>();
  
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TrainingApplication> trainingApplicationSet = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Simulation> simulationSet = new HashSet<>();
	
	public User() {
		super();
	}

	public User(long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}  
  
	public User(long id, String firstName, String lastName, String password, Date birthDate, String email,
				String mobileNumber, int status, boolean photo, String careerGoal) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.birthDate = birthDate;
		this.email = email.toLowerCase();
		this.mobileNumber = mobileNumber;
		this.status = Status.fromId(status);
		this.photo = photo;
		this.validated = true;
		this.careerGoal = careerGoal;
	}

  public User(long id,
		@Size(min = 2, max = 20, message = "firstName must be between 2 and 20 characters") String firstName,
		@Size(min = 2, max = 20, message = "lastName must be between 2 and 20 characters") String lastName,
		@Size(min = 8, message = "password must be at least 8 characters") String password,
		@PastOrPresent Date birthDate,
		@NotNull(message = "Email cannot be null") @Email(message = "Email should be valid") String email,
		String mobileNumber, String status, boolean validated, boolean photo,
		Set<Skill> skillSet, Set<Qualification> qualificationSet, Set<Subscription> subscriptionSet,
		Set<JobApplication> jobApplicationSet, Set<TrainingApplication> trainingApplicationSet, Set<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.birthDate = birthDate;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.status = status;
		this.validated = validated;
		this.photo = photo;
		this.skillSet = skillSet;
		this.qualificationSet = qualificationSet;
		this.subscriptionSet = subscriptionSet;
		this.jobApplicationSet = jobApplicationSet;
		this.trainingApplicationSet = trainingApplicationSet;
		this.roles = roles;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	 
	

	public String getCareerGoal() {
		return careerGoal;
	}

	public void setCareerGoal(String careerGoal) {
		this.careerGoal = careerGoal;
	}

	public boolean isPhoto() {
		return photo;
	}

	public void setPhoto(boolean photo) {
		this.photo = photo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Set<JobApplication> getJobApplicationSet() {
		return jobApplicationSet;
	}

	public void setJobApplicationSet(Set<JobApplication> jobApplicationSet) {
		this.jobApplicationSet = jobApplicationSet;
	}

	public Set<TrainingApplication> getTrainingApplicationSet() {
		return trainingApplicationSet;
	}

	public void setTrainingApplicationSet(Set<TrainingApplication> trainingApplicationSet) {
		this.trainingApplicationSet = trainingApplicationSet;
	}
    
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "User [" + id +
				"] firstName=" + firstName + ", lastName=" + lastName + ", password=" + password +
				", birthDate=" + birthDate + ", email=" + email + ", mobileNumber=" + mobileNumber +
				", status=" + status + ", validated=" + validated +
				", careerGoal=" + careerGoal +", photo=" + photo + ", " +
				", skillSet=" + skillSet +
				", qualificationSet=" + qualificationSet +
				", subscriptionSet=" + subscriptionSet +
				", jobApplicationSets=" + jobApplicationSet +
				", trainingApplicationSet=" + trainingApplicationSet +
				", roles=" + roles + "]";
	}
}
