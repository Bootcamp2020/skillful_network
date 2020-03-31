package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
public class TimedQuestionSet extends QuestionSet {

	private Long timeInMinutes;

	public TimedQuestionSet() {
		super();
	}

	public TimedQuestionSet(Long id, String name, ExerciseType type, String[] keywords) {
		super(id, name, type, keywords);
	}

	public TimedQuestionSet(Set<Question> questions) {
		super(questions);
	}

	public TimedQuestionSet(Set<Question> questions, Long timeInMinutes) {
		super(questions);
		this.timeInMinutes = timeInMinutes;
	}

	public Long getTimeInMinutes() {
		return timeInMinutes;
	}

	public void setTimeInMinutes(Long timeInMinutes) {
		this.timeInMinutes = timeInMinutes;
	}

	@Override
	public String toString() {
		return "TimedQuestionSet [timeInMinutes=" + timeInMinutes + "]";
	}

}
