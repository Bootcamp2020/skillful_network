package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String question;
	private String[] choices;
	private int indexAnswer;
	private String feedback;
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(Long id, String question, String[] choices, int indexAnswer, String feedback) {
		super();
		this.id = id;
		this.question = question;
		this.choices = choices;
		this.indexAnswer = indexAnswer;
		this.feedback = feedback;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getChoices() {
		return choices;
	}

	public void setChoices(String[] choices) {
		this.choices = choices;
	}

	public int getIndexAnswer() {
		return indexAnswer;
	}

	public void setIndexAnswer(int indexAnswer) {
		this.indexAnswer = indexAnswer;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", choices=" + Arrays.toString(choices)
				+ ", indexAnswer=" + indexAnswer + ", feedback=" + feedback + "]";
	}

}
