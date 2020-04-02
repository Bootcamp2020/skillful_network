package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.Set;

import javax.persistence.Entity;

@Entity
public class TimedQuestionSet extends QuestionSet {

	private Long timeInMinutes;

	public TimedQuestionSet() {
		super();
	}

	public TimedQuestionSet(Set<Question> questions,Long id, String name, ExerciseType type, Set<Keyword> keywords,Long timeInMinutes) {
		super(questions,id, name, type, keywords);
		this.timeInMinutes = timeInMinutes;
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
