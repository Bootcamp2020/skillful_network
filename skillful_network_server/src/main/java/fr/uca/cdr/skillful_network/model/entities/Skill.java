package fr.uca.cdr.skillful_network.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="skills")
public class Skill {
	
//	--------------------------------------- Attributs de la classe -------------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER ,mappedBy = "skillSet")
	@JsonIgnore
	private Set<User> userList = new HashSet<User>();
	
	
//	-------------------------------------------- Constructeurs -------------------------------------------------------------------------

	public Skill() {
		super();
	}
	
	public Skill (String name) {
		this.name = name;
	}
	
	public Skill (String name, Set<User>userList) {
		this.name = name;
		this.userList = userList;
	}

	
//	------------------------------------------ Getter et Setter -------------------------------------------------------------------------

	public Long getId() {
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
		return "Skill [id=" + id + ", name=" + name + ", userList=" + userList + "]";
	}
	
	
}
