package fr.uca.cdr.skillful_network.model.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "qualifications")
public class Qualification {
	
//	--------------------------------------- Attributs de la classe -------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@NotNull(message = "Qualification name cannot be null")
	@Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters")
	@Column(name ="name", nullable=false)
	private String name;

	@ManyToMany(mappedBy = "qualificationSet")
	@JsonIgnore
	private Set<User> userSet = new HashSet<User>();
	
//	-------------------------------------------- Constructeurs -------------------------------------------------------------------------


	public Qualification() {
		super();
	}
	
	public Qualification(
			@NotNull(message = "Qualification name cannot be null") @Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters") String name) {
		super();
		this.name = name;

	}

	public Qualification(long id,
			@NotNull(message = "Qualification name cannot be null") @Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters") String name) {
		super();
		this.id = id;
		this.name = name;

	}


	public Qualification(long id, Set<User> userSet,
			@NotNull(message = "Qualification name cannot be null") @Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters") String name) {
		super();
		this.id = id;
		this.name = name;
		this.userSet = userSet;

	}
	public Qualification(
			@NotNull(message = "Qualification name cannot be null") @Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters") String name,
			Set<User> userSet) {
		super();
		this.name = name;
		this.userSet = userSet;
	}
//	------------------------------------------ Getters et Setters -------------------------------------------------------------------------

	

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUserSet() {
		return userSet;
	}
	
	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
	
//	----------------------------------------------  Méthodes  -------------------------------------------------------------------------
	
	
//	@Override
//	public String toString() {
//		return "Qualification [id=" + id + ", name=" + name + ", userList=" + userSet + "]";
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

}