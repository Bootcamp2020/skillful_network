package fr.uca.cdr.skillful_network.security.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.uca.cdr.skillful_network.model.entities.JobApplication;
import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.entities.Role;
import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.entities.TrainingApplication;
import fr.uca.cdr.skillful_network.model.entities.User;

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1L;

	private long id;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private String password;
	private Date birthDate;
	private String email;
	private String mobileNumber;
	private String status;
	private boolean validated = false;
	private String careerGoal;
	private boolean photo = false;
	private Set<Skill> skillSet = new HashSet<Skill>();
	private Set<Qualification> qualificationSet = new HashSet<Qualification>();
	private Set<Subscription> subscriptionSet = new HashSet<Subscription>();
	private Set<JobApplication> jobApplicationSet = new HashSet<>();
	private Set<TrainingApplication> trainingApplicationSet = new HashSet<>();
	private Set<Role> roles = new HashSet<>();
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(long id, String firstName, String lastName, String password, Date birthDate, String email,
			String mobileNumber, String status, boolean validated, String careerGoal, boolean photo,
			Set<Skill> skillSet, Set<Qualification> qualificationSet, Set<Subscription> subscriptionSet,
			Set<JobApplication> jobApplicationSet, Set<TrainingApplication> trainingApplicationSet, Set<Role> roles,
			Collection<? extends GrantedAuthority> authorities) {
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
		this.careerGoal = careerGoal;
		this.photo = photo;
		this.skillSet = skillSet;
		this.qualificationSet = qualificationSet;
		this.subscriptionSet = subscriptionSet;
		this.jobApplicationSet = jobApplicationSet;
		this.trainingApplicationSet = trainingApplicationSet;
		this.roles = roles;
		this.authorities = authorities;
	}

	public static UserPrinciple build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(roles -> new SimpleGrantedAuthority(roles.getName().name())).collect(Collectors.toList());
		return new UserPrinciple(user.getId(), user.getFirstName(), user.getLastName(), user.getPassword(),
				user.getBirthDate(), user.getEmail(), user.getMobileNumber(), user.getStatus(), user.isValidated(),
				user.getCareerGoal(), user.isPhoto(), user.getSkillSet(), user.getQualificationSet(),
				user.getSubscriptionSet(), user.getJobApplicationSet(), user.getTrainingApplicationSet(),
				user.getRoles(), authorities);

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;

	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "UserPrinciple [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", birthDate=" + birthDate + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", status=" + status + ", validated=" + validated + ", careerGoal=" + careerGoal + ", photo=" + photo
				+ ", skillSet=" + skillSet + ", qualificationSet=" + qualificationSet + ", subscriptionSet="
				+ subscriptionSet + ", jobApplicationSet=" + jobApplicationSet + ", trainingApplicationSet="
				+ trainingApplicationSet + ", roles=" + roles + ", authorities=" + authorities + "]";
	}

}
