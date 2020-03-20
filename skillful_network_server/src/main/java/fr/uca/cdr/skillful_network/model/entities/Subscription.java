package fr.uca.cdr.skillful_network.model.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
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
@Table(name = "subscriptions")
public class Subscription {

//	--------------------------------------- Attributs de la classe -------------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Subscription name cannot be null")
	@Size(min = 2, max = 20, message = "Subscription name must be between 3 and 20 characters")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "subscriptionSet")
	@JsonIgnore
	private Set<User> userList = new HashSet<User>();


//	-------------------------------------------- Constructeurs -------------------------------------------------------------------------

	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subscription(
			@NotNull(message = "Subscription name cannot be null") @Size(min = 2, max = 20, message = "Subscription name must be between 3 and 20 characters") String name) {
		super();
		this.name = name;
	}

	public Subscription(
			@NotNull(message = "Subscription name cannot be null") @Size(min = 2, max = 20, message = "Subscription name must be between 3 and 20 characters") String name,
			Set<User> userList) {
		super();
		this.name = name;
		this.userList = userList;
	}
//	------------------------------------------ Getter et Setter -------------------------------------------------------------------------

	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}

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

//	----------------------------------------------  Méthodes  -------------------------------------------------------------------------

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", name=" + name + ", userList=" + userList + "]";
	}

}
