package fr.uca.cdr.skillful_network.model.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
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
	private String[] keywords;

	@OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<JobApplication> jobApplicationSet = new HashSet<>();

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
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	public Set<JobApplication> getJobApplicationSet() { return jobApplicationSet; }
	public void setJobApplicationSet(Set<JobApplication> jobApplicationSet) { this.jobApplicationSet = jobApplicationSet; }

	public JobOffer() {
		super();
	}

	public JobOffer(Long id, String name, String company, String description, String type, Date dateBeg, Date dateEnd,
			Date dateUpload, String[] keywords, Set<JobApplication> jobApplicationSet) {
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
		this.jobApplicationSet = jobApplicationSet;
	}
	
	
	public JobOffer(String name, String company, String description, String type, Date dateBeg, Date dateEnd,
			Date dateUpload, String[] keywords, Set<JobApplication> jobApplicationSet) {
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
	public JobOffer(String name, String company, String description, String type, Date dateBeg, Date dateEnd,
			Date dateUpload, Set<JobApplication> jobApplicationSet) {
		super();
		this.name = name;
		this.company = company;
		this.description = description;
		this.type = type;
		this.dateBeg = dateBeg;
		this.dateEnd = dateEnd;
		this.dateUpload = dateUpload;
		this.jobApplicationSet = jobApplicationSet;
	}
	@Override
	public String toString() {
		return "JobOffer [" + id + "] name=" + name + ", company=" + company + ", description=" + description
				+ ", type=" + type + ", dateBeg=" + dateBeg + ", dateEnd=" + dateEnd + ", dateUpload=" + dateUpload
				+ ", keywords=" + keywords +
				", jobApplicationSets=" + jobApplicationSet;
	}
}
