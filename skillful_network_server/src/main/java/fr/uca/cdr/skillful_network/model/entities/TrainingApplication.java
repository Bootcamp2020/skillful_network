package fr.uca.cdr.skillful_network.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "trainingApplications")
public class TrainingApplication extends Application {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_id")
    @JsonIgnore
    private Training training;

    public TrainingApplication() {
        super();
    }

    public TrainingApplication(User user, Training training) {
        super();
        this.user = user;
        this.training = training;
        this.status = ApplicationStatus.SUBMITTED;
    }

    public TrainingApplication(User user, Training training, ApplicationStatus status, Date submitDate) {
        super(user, status, submitDate);
        this.training = training;
    }

    public TrainingApplication(Long id, User user, Training training, ApplicationStatus status, Date submitDate) {
        super(id, user, status, submitDate);
        this.training = training;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, training);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", training=" + training.getOrganisation() + "/" + training.getName();
    }
}
