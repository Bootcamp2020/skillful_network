package fr.uca.cdr.skillful_network.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "jobApplications")
public class JobApplication extends Application{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_offer_id")
    @JsonIgnore
    private JobOffer jobOffer;

    public JobApplication() {
        super();
    }

    public JobApplication(User user, JobOffer jobOffer) {
        super();
        this.user = user;
        this.jobOffer = jobOffer;
        this.status = ApplicationStatus.SUBMITTED;
    }

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
        return Objects.hash(id, user, jobOffer);
    }

    @Override
    public String toString() {
        return super.toString() +
            ", jobOffer=" + jobOffer.getCompany() + "/" + jobOffer.getName();
    }
}
