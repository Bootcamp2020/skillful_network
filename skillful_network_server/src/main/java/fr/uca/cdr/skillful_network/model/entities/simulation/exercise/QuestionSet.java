package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.HashSet;
import java.util.Set;

public class QuestionSet extends Exercise {

	private Set<Question> questions = new HashSet<>();

	public QuestionSet(Set<Question> questions) {
		super();
		this.questions = questions;
	}

	public QuestionSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionSet(Long id, String name, ExerciseType type, String[] keywords) {
		super(id, name, type, keywords);
		// TODO Auto-generated constructor stub
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "QuestionSet [questions=" + questions + "]";
	}

	
}