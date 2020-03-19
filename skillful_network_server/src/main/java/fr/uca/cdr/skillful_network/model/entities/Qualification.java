package fr.uca.cdr.skillful_network.model.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Column(name = "name", nullable = false)
	@NotNull(message = "Qualification name cannot be null")
	@Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "qualificationSet")
	@JsonIgnore
	private Set<User> userList = new HashSet<User>();

	
//	-------------------------------------------- Constructeurs -------------------------------------------------------------------------


	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

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


	public Qualification(long id, Set<User> userList,
			@NotNull(message = "Qualification name cannot be null") @Size(min = 2, max = 20, message = "Qualification name must be between 3 and 20 characters") String name) {
		super();
		this.id = id;
		this.name = name;
		this.userList = userList;

	}
	
	
//	------------------------------------------ Getter et Setter -------------------------------------------------------------------------


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

	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}
	

//	----------------------------------------------  Méthodes  -------------------------------------------------------------------------
	
	
	@Override
	public String toString() {
		return "Qualification [id=" + id + ", name=" + name + ", userList=" + userList + "]";
	}

}