package fr.uca.cdr.skillful_network.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class Application {

    enum ApplicationStatus {INIT, SUBMITTED, INVESTIGATING, WAITING, PAUSED, POSTPONED, ACCEPTED, REJECTED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    protected User user;
    protected ApplicationStatus status;
    protected Date submitDate;

    public Application() {
        super();
        this.status = ApplicationStatus.INIT;
        this.submitDate = new Date();
    }

    public Application(User user, ApplicationStatus status, Date submitDate) {
        super();
        this.user = user;
        this.status = status;
        this.submitDate = submitDate;
    }

    public Application(Long id, User user, ApplicationStatus status, Date submitDate) {
        this(user, status, submitDate);
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
        return Objects.hash(id, user);
    }

    @Override
    public String toString() {
        return "Application[" + id +
                "] user=" + user.getFirstName() + " " + user.getLastName() +
                ", status=" + status +
                ", submitDate= " + submitDate ;
    }

}
