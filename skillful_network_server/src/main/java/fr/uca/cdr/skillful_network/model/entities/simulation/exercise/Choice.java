package fr.uca.cdr.skillful_network.model.entities.simulation.exercise;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Choice { 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String choix;
	
	public Choice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Choice(Long id, String choix) {
		super();
		this.id = id;
		this.choix = choix;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	@Override
	public String toString() {
		return "Choice [id=" + id + ", choix=" + choix + "]";
	}

	

}
