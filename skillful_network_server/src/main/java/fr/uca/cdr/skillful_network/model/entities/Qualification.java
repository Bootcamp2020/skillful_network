package fr.uca.cdr.skillful_network.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Qualification {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String qualification;
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Qualification() {
		super();
	}
	
	public Qualification(Long id, String qualification) {
		super();
		this.id = id;
		this.qualification = qualification;
	}
	
	public Qualification(String qualification) {
		super();
		this.qualification = qualification;
	}
	
	@Override
	public String toString() {
		return "Qualification [id=" + id + ", qualification=" + qualification + "]";
	}

}
