package fr.uca.cdr.skillful_network.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "simulations")
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("simulationSet")
    protected User user;

    @NotNull(message = "Job goal cannot be null")
    private String jobGoal;

    @PastOrPresent
    private Date creationDate;
    
    private String synthesis;

    public Simulation() {
    }

    public Simulation(@NotNull(message = "Job goal cannot be null") String jobGoal) {
        this.jobGoal = jobGoal;
        this.creationDate = new Date();
    }

    public Simulation(@NotNull(message = "Job goal cannot be null") String jobGoal, @PastOrPresent Date creationDate, String synthesis) {
        this.jobGoal = jobGoal;
        this.creationDate = creationDate;
        this.synthesis = synthesis;
    }

    public Simulation(long id, @NotNull(message = "Job goal cannot be null") String jobGoal, @PastOrPresent Date creationDate, String synthesis) {
        this.id = id;
        this.jobGoal = jobGoal;
        this.creationDate = creationDate;
        this.synthesis = synthesis;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, jobGoal);
    }

    @Override
    public String toString() {
        return "Simulation [" + "id=" + id +
                "] userID=" + user.getId() +
                ", jobGoal=" + jobGoal +
                ", creationDate=" + creationDate +
                ", synthesis=" + synthesis;
    }
}
