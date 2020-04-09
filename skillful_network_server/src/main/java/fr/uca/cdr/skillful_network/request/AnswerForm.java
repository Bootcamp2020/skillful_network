package fr.uca.cdr.skillful_network.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnswerForm {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long questionId;
	private int answer;

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public AnswerForm(long questionId, int answer) {
		super();
		this.questionId = questionId;
		this.answer = answer;
	}

	public AnswerForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AnswerForm [id=" + id + ", questionId=" + questionId + ", answer=" + answer + "]";
	}
	
	
}
