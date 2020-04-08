package fr.uca.cdr.skillful_network.request;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ExerciseForm {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	public ExerciseForm(Set<AnswerForm> answerSet) {
		super();
		this.answerSet = answerSet;
	}

	public ExerciseForm(long id, Set<AnswerForm> answerSet) {
		super();
		this.id = id;
		this.answerSet = answerSet;
	}

	@Override
	public String toString() {
		return "ExerciseForm [id=" + id + ", answerSet=" + answerSet + "]";
	}
}
