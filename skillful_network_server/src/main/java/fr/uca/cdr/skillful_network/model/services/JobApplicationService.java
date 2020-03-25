package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.JobApplication;
import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface JobApplicationService {

    List<JobApplication> getAllJobApplications();

    Optional<JobApplication> getJobApplicationById(Long id);

    Optional<User> getUserById(Long id);

    Optional<JobOffer> getJobOfferById(Long id);

    Optional<List<JobApplication>> getJobApplicationsByUserId(Long userId);

    Optional<List<JobApplication>> getJobApplicationsByJobOfferId(Long jobOfferId);

    Optional<User> setUserById(Long jobApplicationOfferId, Long userId);

    Optional<JobOffer> setJobOfferById(Long jobApplicationOfferId, Long jobOfferId);

    JobApplication saveOrUpdateJobApplication(JobApplication jobApplication);

    void deleteJobApplication(Long id);
}
