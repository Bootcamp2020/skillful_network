package fr.uca.cdr.skillful_network.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exam;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Result;
import fr.uca.cdr.skillful_network.request.SimulationForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "simulations")
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("simulationSet")
    private User user;

    @NotNull(message = "Job goal cannot be null")
    private String jobGoal;

    @PastOrPresent
    private Date creationDate;
    
    private String synthesis;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private Set<Result> results; 
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobOffer_id")
    private JobOffer jobOffer;
    
    private boolean jobAccess;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    private Training training;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Transient
    private SimulationForm simulationForm;
    
    public Simulation() {
    }

    public Simulation(@NotNull(message = "Job goal cannot be null") String jobGoal) {
        this.jobGoal = jobGoal;
        this.creationDate = new Date();
        this.jobAccess = false;
    }

    public Simulation(@NotNull(message = "Job goal cannot be null") String jobGoal, @PastOrPresent Date creationDate, String synthesis) {
        this.jobGoal = jobGoal;
        this.creationDate = creationDate;
        this.synthesis = synthesis;
        this.jobAccess = false;
    }

    public Simulation(long id, @NotNull(message = "Job goal cannot be null") String jobGoal, @PastOrPresent Date creationDate, String synthesis) {
        this.id = id;
        this.jobGoal = jobGoal;
        this.creationDate = creationDate;
        this.synthesis = synthesis;
        this.jobAccess = false;
    }
    
    public Simulation(@NotNull(message = "Job goal cannot be null") String jobGoal, JobOffer jobOffer) {
    	this.jobGoal = jobGoal;
    	this.jobOffer = jobOffer;
        this.creationDate = new Date();
        this.jobAccess = false;
    }
    
    public Simulation(@NotNull(message = "Job goal cannot be null") String jobGoal, JobOffer jobOffer, Exam exam) {
    	this.jobGoal = jobGoal;
    	this.jobOffer = jobOffer;
        this.creationDate = new Date();
        this.jobAccess = false;
        this.exam = exam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getJobGoal() {
        return jobGoal;
    }

    public void setJobGoal(String jobGoal) {
        this.jobGoal = jobGoal;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getSynthesis() {
        return synthesis;
    }

    public void setSynthesis(String synthesis) {
        this.synthesis = synthesis;
    }

    public Set<Result> getResults() {
		return results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}

	@Override
    public int hashCode() {
        return Objects.hash(id, jobGoal);
    }
	
    public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

	public boolean isJobAccess() {
		return jobAccess;
	}

	public void setJobAccess(boolean jobAccess) {
		this.jobAccess = jobAccess;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public SimulationForm getSimulationForm() {
		return simulationForm;
	}

	public void setSimulationForm(SimulationForm simulationForm) {
		this.simulationForm = simulationForm;
	}

	@Override
	public String toString() {
		return "Simulation [id=" + id + ", user=" + user + ", jobGoal=" + jobGoal + ", creationDate=" + creationDate
				+ ", synthesis=" + synthesis + ", results=" + results + ", jobOffer=" + jobOffer + ", jobAccess="
				+ jobAccess + ", training=" + training + ", exam=" + exam + ", simulationForm=" + simulationForm + "]";
	}

//	@Override
//    public String toString() {
//        return "Simulation [" + "id=" + id +
//                "] userID=" + user.getId() +
//                ", jobGoal=" + jobGoal +
//                ", creationDate=" + creationDate +
//                ", synthesis=" + synthesis;
//    }
	
}
