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
	
	private long _id;
	@Size(min = 2, max = 20, message = "firstName must be between 2 and 20 characters")
	private String _firstName;
	@Size(min = 2, max = 20, message = "lastName must be between 2 and 20 characters")
	private String _lastName;
	private String _password;
	@PastOrPresent
	private Date _birthDate;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	private String _email;
	private String _mobileNumber;
	private String _status;
	private boolean _validated;
	private boolean _photo;
	private Set<Skill> _skillSet = new HashSet<Skill>();
	private Set<Qualification> _qualificationSet = new HashSet<Qualification>();
	private Set<Subscription> _subscriptionSet = new HashSet<Subscription>();
	private String _photoProfile;
	private String _careerGoal;
	
	
	
	public UserForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserForm(long _id, String _firstName, String _lastName, String _password, Date _birthDate, String _email,
			String _mobileNumber, String _status, boolean _validated, boolean _photo, Set<Skill> _skillSet,
			Set<Qualification> _qualificationSet, Set<Subscription> _subscriptionSet, String _photoProfile,
			String _careerGoal) {
		super();
		this._id = _id;
		this._firstName = _firstName;
		this._lastName = _lastName;
		this._password = _password;
		this._birthDate = _birthDate;
		this._email = _email;
		this._mobileNumber = _mobileNumber;
		this._status = _status;
		this._validated = _validated;
		this._photo = _photo;
		this._skillSet = _skillSet;
		this._qualificationSet = _qualificationSet;
		this._subscriptionSet = _subscriptionSet;
		this._photoProfile = _photoProfile;
		this._careerGoal = _careerGoal;
	}
	
	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String get_firstName() {
		return _firstName;
	}

	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}

	public String get_lastName() {
		return _lastName;
	}

	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public Date get_birthDate() {
		return _birthDate;
	}

	public void set_birthDate(Date _birthDate) {
		this._birthDate = _birthDate;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_mobileNumber() {
		return _mobileNumber;
	}

	public void set_mobileNumber(String _mobileNumber) {
		this._mobileNumber = _mobileNumber;
	}

	public String get_status() {
		return _status;
	}

	public void set_status(String _status) {
		this._status = _status;
	}

	public boolean is_validated() {
		return _validated;
	}

	public void set_validated(boolean _validated) {
		this._validated = _validated;
	}

	public boolean is_photo() {
		return _photo;
	}

	public void set_photo(boolean _photo) {
		this._photo = _photo;
	}

	public Set<Skill> get_skillSet() {
		return _skillSet;
	}

	public void set_skillSet(Set<Skill> _skillSet) {
		this._skillSet = _skillSet;
	}

	public Set<Qualification> get_qualificationSet() {
		return _qualificationSet;
	}

	public void set_qualificationSet(Set<Qualification> _qualificationSet) {
		this._qualificationSet = _qualificationSet;
	}

	public Set<Subscription> get_subscriptionSet() {
		return _subscriptionSet;
	}

	public void set_subscriptionSet(Set<Subscription> _subscriptionSet) {
		this._subscriptionSet = _subscriptionSet;
	}

	public String get_photoProfile() {
		return _photoProfile;
	}

	public void set_photoProfile(String _photoProfile) {
		this._photoProfile = _photoProfile;
	}

	public String get_careerGoal() {
		return _careerGoal;
	}

	public void set_careerGoal(String _careerGoal) {
		this._careerGoal = _careerGoal;
	}

	@Override
	public String toString() {
		return "UserForm [_id=" + _id + ", _firstName=" + _firstName + ", _lastName=" + _lastName + ", password=" + _password
				+ ", birthDate=" + _birthDate + ", email=" + _email + ", mobileNumber=" + _mobileNumber + ", status="
				+ _status + ", validated=" + _validated + ", photo=" + _photo + ", skillSet=" + _skillSet
				+ ", qualificationSet=" + _qualificationSet + ", subscriptionSet=" + _subscriptionSet + ", photoProfile="
				+ _photoProfile + ", careerGoal=" + _careerGoal + "]";
	}
	
	
	
	
}
