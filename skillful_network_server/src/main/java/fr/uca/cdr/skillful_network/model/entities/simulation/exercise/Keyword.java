package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="keyword")
public class Keyword {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "keywords")
	@JsonIgnore
	private Set<Exercise> exercises = new HashSet<Exercise>();
	public Keyword() {
		super();
	}
	 public Keyword(Long id, String name, Set<Exercise> exercises) {
			super();
			this.id = id;
			this.name = name;
			this.exercises = exercises;
		}
	public Keyword(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public Set<Exercise> getExercises() {
		return exercises;
	}
	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}
	@Override
	public String toString() {
		return "Keyword [id=" + id + ", name=" + name + ", exercises=" + exercises + "]";
	}

	
	
}
