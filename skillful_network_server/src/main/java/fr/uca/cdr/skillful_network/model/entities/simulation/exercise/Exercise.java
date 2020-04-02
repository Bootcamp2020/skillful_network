package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Exercise {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private ExerciseType type;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(
	        name = "exercise_keyword", 
	        joinColumns = { @JoinColumn(name = "exercise_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "keyword_id") }
	    )
	@JsonIgnore
	private Set<Keyword> keywords = new HashSet<>();

	public Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Exercise(String name, ExerciseType type, Set<Keyword> keywords) {
		super();
		this.name = name;
		this.type = type;
		this.keywords = keywords;
	}
	public Exercise(Long id ,String name, ExerciseType type, Set<Keyword> keywords) {
		super();
		this.id= id;
		this.name = name;
		this.type = type;
		this.keywords = keywords;
	}

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

	public ExerciseType getType() {
		return type;
	}

	public void setType(ExerciseType type) {
		this.type = type;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "Exercise [id=" + id + ", name=" + name + ", type=" + type + ", keywords=" + keywords
				+ "]";
	}
}
