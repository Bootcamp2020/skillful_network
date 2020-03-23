package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.JobApplication;
import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.entities.Subscription;

import java.util.List;
import java.util.Optional;

public interface JobApplicationService {

    List<JobApplication> getAllJobApplications();
    Optional<JobApplication> getJobApplicationById(Long id);
    Optional<List<JobApplication>> getJobApplicationsByUserId(Long id);
    Optional<List<JobApplication>> getJobApplicationsByJobOfferId(Long id);
    JobApplication saveOrUpdateJobApplication(JobApplication jobApplication);
    void deleteJobApplication(Long id);
}
