package fr.uca.cdr.skillful_network.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "trainingApplications")
public class TrainingApplication {

    enum ApplicationStatus {INIT, SUBMITTED, INVESTIGATING, WAITING, PAUSED, POSTPONED, ACCEPTED, REJECTED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    @JsonIgnore
    private Training training;
    private ApplicationStatus status;
    private Date submitDate;

    public TrainingApplication() {
        super();
        this.status = ApplicationStatus.INIT;
        this.submitDate = new Date();
    }

    public TrainingApplication(User user, Training training) {
        super();
        this.user = user;
        this.training = training;
        this.status = ApplicationStatus.SUBMITTED;
        this.submitDate = new Date();
    }
    public TrainingApplication(User user, Training training, ApplicationStatus status, Date submitDate) {
        super();
        this.user = user;
        this.training = training;
        this.status = status;
        this.submitDate = submitDate;
    }

    public TrainingApplication(Long id, User user, Training training, ApplicationStatus status, Date submitDate) {
        this(user, training, status, submitDate);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, training);
    }

    @Override
    public String toString() {
        return "TrainingApplication[" +
                "id=" + id +
                ", user=" + user.getFirstName() + " " + user.getLastName() +
                ", training=" + training.getOrganisation() + "/" + training.getName() +
                ", status=" + status +
                '}';
    }
}
