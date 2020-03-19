package fr.uca.cdr.skillful_network.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Qualification {
//	--------------------------------------- Attributs de la classe -------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Qualification name cannot be null")
	@Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "qualificationSet")
	@JsonIgnore
	private Set<User> userSet = new HashSet<User>();

//	-------------------------------------------- Constructeurs -------------------------------------------------------------------------

	public Qualification() {
		super();
	}

	public Qualification(long id,
			@NotNull(message = "Qualification name cannot be null") @Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters") String name) {
		super();
		this.id = id;
		this.name = name;

	}

	public Qualification(
			@NotNull(message = "Qualification name cannot be null") @Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters") String name) {
		super();
		this.name = name;

	}

	public Qualification(long id, Set<User> userList,
			@NotNull(message = "Qualification name cannot be null") @Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters") String name) {
		super();
		this.id = id;
		this.name = name;
		this.userSet = userList;

	}

//	------------------------------------------ Getters et Setters -------------------------------------------------------------------------

	public long getId() {
		return id;
	}

	public Set<User> getUserList() {
		return userSet;
	}

	public void setUserList(Set<User> userList) {
		this.userSet = userList;
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

//	----------------------------------------------  Méthodes  -------------------------------------------------------------------------

	@Override
	public String toString() {
		return "Qualification [id=" + id + ", name=" + name + ", userList=" + userSet + "]";
	}

}