package fr.uca.cdr.skillful_network.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "jobApplications")
public class JobApplication {

    enum ApplicationStatus { INIT, SUBMITTED, INVESTIGATING, WAITING, PAUSED, POSTPONED, ACCEPTED, REJECTED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "joboffer_id")
    @JsonIgnore
    private JobOffer jobOffer;
    private ApplicationStatus status;
    private Date submitDate;

    public JobApplication() { super(); }

    public JobApplication(User user, JobOffer jobOffer, ApplicationStatus status, Date submitDate) {
        super();
        this.user = user;
        this.jobOffer = jobOffer;
        this.status = status;
        this.submitDate = submitDate;
    }

    public JobApplication(Long id, User user, JobOffer jobOffer, ApplicationStatus status, Date submitDate) {
        super();
        this.id = id;
        this.user = user;
        this.jobOffer = jobOffer;
        this.status = status;
        this.submitDate = submitDate;
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

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
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
        return Objects.hash(id, user, jobOffer);
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + id +
                ", user=" + user.getFirstName() + " " + user.getLastName() +
                ", jobOffer=" + jobOffer.getCompany() + "/" + jobOffer.getName() +
                ", status=" + status +
                '}';
    }
}
