package fr.uca.cdr.skillful_network.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "jobApplications")
public class JobApplication extends Application {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_offer_id")
    @JsonIgnore
    private JobOffer jobOffer;

    public JobApplication(User user, JobOffer jobOffer, ApplicationStatus status, Date submitDate) {
        super(user, status, submitDate);
        this.jobOffer = jobOffer;
    }

    public JobApplication(Long id, User user, JobOffer jobOffer, ApplicationStatus status, Date submitDate) {
        super(id, user, status, submitDate);
        this.jobOffer = jobOffer;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.id, super.user, jobOffer);
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + super.id +
                ", user=" + super.user.getFirstName() + " " + super.user.getLastName() +
                ", jobOffer=" + jobOffer.getCompany() + "/" + jobOffer.getName() +
                ", status=" + super.status +
                '}';
    }
}
