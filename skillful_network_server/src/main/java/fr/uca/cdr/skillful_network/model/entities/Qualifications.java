package fr.uca.cdr.skillful_network.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Qualifications {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String qualifications;
	
	public String getQualifications() {
		return qualifications;
	}
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Qualifications() {
		super();
	}
	
	public Qualifications(Long id, String qualifications) {
		super();
		this.id = id;
		this.qualifications = qualifications;
	}
	
	@Override
	public String toString() {
		return "Qualifications [id=" + id + ", qualifications=" + qualifications + "]";
	}

}
