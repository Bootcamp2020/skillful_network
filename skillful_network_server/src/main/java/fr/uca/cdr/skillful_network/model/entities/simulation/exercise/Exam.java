package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Set<Exercise> exerciseSet = new HashSet<Exercise>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<Exercise> getExerciseSet() {
		return exerciseSet;
	}
	public void setExerciseSet(Set<Exercise> exerciseSet) {
		this.exerciseSet = exerciseSet;
	}
	@Override
	public String toString() {
		return "Exam [id=" + id + ", exerciseSet=" + exerciseSet + "]";
	}
	public Exam(long id, Set<Exercise> exerciseSet) {
		super();
		this.id = id;
		this.exerciseSet = exerciseSet;
	}
	public Exam(Set<Exercise> exerciseSet) {
		super();
		this.exerciseSet = exerciseSet;
	}
	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
