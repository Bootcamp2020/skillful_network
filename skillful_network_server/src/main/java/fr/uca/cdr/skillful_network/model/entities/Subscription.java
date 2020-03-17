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
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotNull(message = "Subscription name cannot be null")
    @Size(min = 2, max = 20, message = "Subscription name must be between 3 and 20 characters")
    private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "subscription_users", joinColumns = { @JoinColumn(name = "subscription_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Set<User> Users = new HashSet<>();
	
	
	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subscription(long id,
			@NotNull(message = "Subscription name cannot be null") @Size(min = 2, max = 20, message = "Subscription name must be between 3 and 20 characters") String name) {
		super();
		this.id = id;
		this.name = name;
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
	@Override
	public String toString() {
		return "Subscription [id=" + id + ", name=" + name + "]";
	}
	
	
	
    
}