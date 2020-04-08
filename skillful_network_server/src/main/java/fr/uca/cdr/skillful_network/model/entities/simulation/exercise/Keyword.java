package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.HashSet;
import java.util.Objects;
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

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Training;

@Entity
@Table(name = "keyword")
public class Keyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST},mappedBy = "keywords")
	@JsonIgnore
	private Set<QuestionSet> exercises = new HashSet<QuestionSet>();
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST}, mappedBy = "keywords")
	@JsonIgnore
	private Set<JobOffer> jobOffers = new HashSet<JobOffer>();
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST}, mappedBy = "keywords")
	@JsonIgnore
	private Set<Training> trainings = new HashSet<Training>();

	public Keyword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Keyword(Long id, String name, Set<QuestionSet> exercises, Set<JobOffer> jobOffers, Set<Training> trainings) {
		super();
		this.id = id;
		this.name = name;
		this.exercises = exercises;
		this.jobOffers = jobOffers;
		this.trainings = trainings;
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

	public Set<QuestionSet> getExercises() {
		return exercises;
	}

	public void setExercises(Set<QuestionSet> exercises) {
		this.exercises = exercises;
	}

	public Set<JobOffer> getJobOffers() {
		return jobOffers;
	}

	public void setJobOffers(Set<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}

	public Set<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	@Override
	public String toString() {
		return "Keyword [id=" + id + ", name=" + name  + "]";
	}

	

	
	
	
	
}
