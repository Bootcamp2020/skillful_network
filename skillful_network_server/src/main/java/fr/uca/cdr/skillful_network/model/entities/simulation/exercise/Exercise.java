package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
public class Exercise {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private ExerciseType type;
	private String[] keywords;

	public Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Exercise(Long id, String name, ExerciseType type, String[] keywords) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.keywords = keywords;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExerciseType getType() {
		return type;
	}

	public void setType(ExerciseType type) {
		this.type = type;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "Exercise [id=" + id + ", name=" + name + ", type=" + type + ", keywords=" + Arrays.toString(keywords)
				+ "]";
	}
}
