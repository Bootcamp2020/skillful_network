package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String question;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Choice> choices = new HashSet<>();
	private int indexAnswer;
	@Lob   // annotation to save a long text in MySQl
	private String feedBack;
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(Long id, String question, Set<Choice> choices, int indexAnswer, String feedBack) {
		super();
		this.id = id;
		this.question = question;
		this.choices = choices;
		this.indexAnswer = indexAnswer;
		this.feedBack = feedBack;
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

	public Set<Choice> getChoices() {
		return choices;
	}

	public void setChoices(Set<Choice> choices) {
		this.choices = choices;
	}

	public int getIndexAnswer() {
		return indexAnswer;
	}

	public void setIndexAnswer(int indexAnswer) {
		this.indexAnswer = indexAnswer;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", choices=" + choices
				+ ", indexAnswer=" + indexAnswer + ", feedback=" + feedBack + "]";
	}

}
