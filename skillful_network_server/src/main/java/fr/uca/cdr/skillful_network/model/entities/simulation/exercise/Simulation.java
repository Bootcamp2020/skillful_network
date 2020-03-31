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

public class Simulation {


	private Set<Exercise> exerciseSet = new HashSet<Exercise>();

	public Set<Exercise> getExerciseSet() {
		return exerciseSet;
	}

	public void setExerciseSet(Set<Exercise> exerciseSet) {
		this.exerciseSet = exerciseSet;
	}

	@Override
	public String toString() {
		return "Simulation [exerciseSet=" + exerciseSet + "]";
	}

	public Simulation(Set<Exercise> exerciseSet) {
		super();
		this.exerciseSet = exerciseSet;
	}

	public Simulation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
