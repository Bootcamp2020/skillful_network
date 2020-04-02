package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Result {

	private Long idExercise;
	private float gradeExercice;
	
	public Result(Long idExercise, float gradeExercice) {
		super();
		this.idExercise = idExercise;
		this.gradeExercice = gradeExercice;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdExercise() {
		return idExercise;
	}
	public void setIdExercise(Long idExercise) {
		this.idExercise = idExercise;
	}
	public float getGradeExercice() {
		return gradeExercice;
	}
	public void setGradeExercice(float gradeExercice) {
		this.gradeExercice = gradeExercice;
	}
	@Override
	public String toString() {
		return "Result [idExercise=" + idExercise + ", gradeExercice=" + gradeExercice + "]";
	}

}
