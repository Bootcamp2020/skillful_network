package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.JobApplication;
import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "JobApplicationService")
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Override
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    @Override
    public Optional<JobApplication> getJobApplicationById(Long id) {
        return jobApplicationRepository.findById(id);
        //return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return jobApplicationRepository.findById(id)
                .map( jobApplication -> jobApplication.getUser());
    }

    @Override
    public Optional<JobOffer> getJobOfferById(Long id) {
        return jobApplicationRepository.findById(id)
                .map( jobApplication -> jobApplication.getJobOffer());
    }

    @Override
    public Optional<List<JobApplication>> getJobApplicationsByUserId(Long userId) {
        return jobApplicationRepository.findByUserId(userId);
        //return Optional.empty();
    }

    @Override
    public Optional<List<JobApplication>> getJobApplicationsByJobOfferId(Long jobOfferId) {
        return jobApplicationRepository.findByJobOfferId(jobOfferId);
        //return Optional.empty();
    }

    @Override
    public JobApplication saveOrUpdateJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public void deleteJobApplication(Long id) {
        jobApplicationRepository.deleteById(id);
    }
}
