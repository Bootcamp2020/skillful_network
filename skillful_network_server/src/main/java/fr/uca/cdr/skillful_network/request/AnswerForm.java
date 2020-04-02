package fr.uca.cdr.skillful_network.request;

public class AnswerForm {

	private long questionId;
	private int answer;

	public long getId() {
		return questionId;
	}

	public void setId(long questionId) {
		this.questionId = questionId;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
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
	@Override
	public String toString() {
		return "AnswerForm [questionId=" + questionId + ", answer=" + answer + "]";
	}
	
}
