package fr.uca.cdr.skillful_network.model.entities;

import javax.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "training")
public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String organisation;
	private String description;
	private String financer;
	private Date dateBeg;
	private Date dateEnd;
	private Date dateUpload;
	private Long durationHours;
	private String[] prerequisites;
	private String[] keywords;

	@OneToMany(mappedBy = "training", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TrainingApplication> trainingApplicationSet = new HashSet<>();
	
	
	
	public Set<TrainingApplication> getTrainingApplicationSet() {
		return trainingApplicationSet;
	}
	public void setTrainingApplicationSet(Set<TrainingApplication> trainingApplicationSet) {
		this.trainingApplicationSet = trainingApplicationSet;
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
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFinancer() {
		return financer;
	}
	public void setFinancer(String financer) {
		this.financer = financer;
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
	public Long getDurationHours() {
		return durationHours;
	}
	public void setDurationHours(Long durationHours) {
		this.durationHours = durationHours;
	}
	public String[] getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(String[] prerequisites) {
		this.prerequisites = prerequisites;
	}
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	public Training() {
		super();
	}
	public Training(Long id, String name, String organisation, String description, String financer, Date dateBeg,
			Date dateEnd, Date dateUpload, Long durationHours, String[] prerequisites, String[] keywords) {
		super();
		this.id = id;
		this.name = name;
		this.organisation = organisation;
		this.description = description;
		this.financer = financer;
		this.dateBeg = dateBeg;
		this.dateEnd = dateEnd;
		this.dateUpload = dateUpload;
		this.durationHours = durationHours;
		this.prerequisites = prerequisites;
		this.keywords = keywords;
	}
	public Training(String name, String organisation, String description, String financer, Date dateBeg, Date dateEnd,
			Date dateUpload, Long durationHours, String[] prerequisites, String[] keywords) {
		super();
		this.name = name;
		this.organisation = organisation;
		this.description = description;
		this.financer = financer;
		this.dateBeg = dateBeg;
		this.dateEnd = dateEnd;
		this.dateUpload = dateUpload;
		this.durationHours = durationHours;
		this.prerequisites = prerequisites;
		this.keywords = keywords;
	}
	public Training(String name, String organisation, String description, String financer, Date dateBeg, Date dateEnd,
			Date dateUpload, Long durationHours) {
		super();
		this.name = name;
		this.organisation = organisation;
		this.description = description;
		this.financer = financer;
		this.dateBeg = dateBeg;
		this.dateEnd = dateEnd;
		this.dateUpload = dateUpload;
		this.durationHours = durationHours;
	}
	@Override
	public String toString() {
		return "Training [id=" + id + ", name=" + name + ", organisation=" + organisation + ", description="
				+ description + ", financer=" + financer + ", dateBeg=" + dateBeg + ", dateEnd=" + dateEnd
				+ ", dateUpload=" + dateUpload + ", durationHours=" + durationHours + ", prerequisites="
				+ Arrays.toString(prerequisites) + ", keywords=" + Arrays.toString(keywords) + "]";
	}
	 @Override
	    public int hashCode() {
	        return Objects.hash(id, name, organisation);
	    }
	

}
