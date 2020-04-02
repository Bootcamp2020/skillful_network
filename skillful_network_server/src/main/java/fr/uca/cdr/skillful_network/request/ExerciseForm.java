package fr.uca.cdr.skillful_network.request;

import java.util.HashSet;
import java.util.Set;

public class ExerciseForm {

	private long id;
	private Set<AnswerForm> answerSet = new HashSet<AnswerForm>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<AnswerForm> getAnswerSet() {
		return answerSet;
	}

	public void setAnswerSet(Set<AnswerForm> answerSet) {
		this.answerSet = answerSet;
	}

	public ExerciseForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ExerciseForm [id=" + id + ", answerSet=" + answerSet + "]";
	}
}
