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
import javax.persistence.OneToMany;

@Entity
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Exercise> exerciseSet = new HashSet<Exercise>();

	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam(Long id, Set<Exercise> exerciseSet) {
		super();
		this.id = id;
		this.exerciseSet = exerciseSet;
	}

	public Exam(Set<Exercise> exerciseSet) {
		super();
		this.exerciseSet = exerciseSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

}
