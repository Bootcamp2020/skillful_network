package fr.uca.cdr.skillful_network.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Keyword;

@Entity
@Table(name = "job_offer")
public class JobOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String company;
	private String description;
	private String type;
	private Date dateBeg;
	private Date dateEnd;
	private Date dateUpload;

	@ManyToMany(fetch = FetchType.EAGER, cascade =  CascadeType.PERSIST)
	private Set<Keyword> keywords = new HashSet<>();

	public enum Risk {
		SIMPLE, MODERATE, CRITICAL;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(length = 50)
	private Risk risk;

	public enum Complexity {
		SIMPLE, MODERATE, COMPLEX;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(length = 50)
	private Complexity complexity;

	@OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<JobApplication> jobApplicationSet = new HashSet<>();
	
	// Tableau des scores
	@Transient
	@JsonSerialize
	@JsonDeserialize
	private final double[][] score = { { 0.4, 0.6, 0.8 }, { 0.6, 0.8, 1 }, { 0.8, 1, 1.2 } };
	
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDateBeg() {
		return dateBeg;
	}

	public void setDateBeg(Date dateBeg) {
		this.dateBeg = dateBeg;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	public Set<JobApplication> getJobApplicationSet() {
		return jobApplicationSet;
	}

	public void setJobApplicationSet(Set<JobApplication> jobApplicationSet) {
		this.jobApplicationSet = jobApplicationSet;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public Complexity getComplexity() {
		return complexity;
	}

	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}

	public JobOffer() {
		super();
	}

	public JobOffer(Long id, String name, String company, String description, String type, Date dateBeg, Date dateEnd,
			Date dateUpload, Set<Keyword> keywords, Risk risk, Complexity complexity,
			Set<JobApplication> jobApplicationSet) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.description = description;
		this.type = type;
		this.dateBeg = dateBeg;
		this.dateEnd = dateEnd;
		this.dateUpload = dateUpload;
		this.keywords = keywords;
		this.risk = risk;
		this.complexity = complexity;
		this.jobApplicationSet = jobApplicationSet;
	}

	public JobOffer(String name, String company, String description, String type, Date dateBeg, Date dateEnd,
			Date dateUpload, Set<Keyword> keywords, Set<JobApplication> jobApplicationSet) {
		super();
		this.name = name;
		this.company = company;
		this.description = description;
		this.type = type;
		this.dateBeg = dateBeg;
		this.dateEnd = dateEnd;
		this.dateUpload = dateUpload;
		this.keywords = keywords;
		this.jobApplicationSet = jobApplicationSet;
	}

	@Override
	public String toString() {
		return "JobOffer [id=" + id + ", name=" + name + ", company=" + company + ", description=" + description
				+ ", type=" + type + ", dateBeg=" + dateBeg + ", dateEnd=" + dateEnd + ", dateUpload=" + dateUpload
				+ ", keywords=" + keywords + ", risk=" + risk + ", complexity=" + complexity + ", jobApplicationSet="
				+ jobApplicationSet + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	public double getScore() {
		return score[this.complexity.ordinal()][this.risk.ordinal()];

	}
}
