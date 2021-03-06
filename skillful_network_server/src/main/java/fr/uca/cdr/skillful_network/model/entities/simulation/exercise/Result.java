package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "results")
public class Result {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private long idExercise;
	private double gradeExercise;
	
	public Result(long idExercise, double exerciceResult) {
		super();
		this.idExercise = idExercise;
		this.gradeExercise = exerciceResult;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getIdExercise() {
		return idExercise;
	}
	public void setIdExercise(long idExercise) {
		this.idExercise = idExercise;
	}
	public double getGradeExercise() {
		return gradeExercise;
	}
	public void setGradeExercise(float gradeExercise) {
		this.gradeExercise = gradeExercise;
	}
	@Override
	public String toString() {
		return "Result [id=" + id + ", idExercise=" + idExercise + ", gradeExercise=" + gradeExercise + "]";
	}
	

}
