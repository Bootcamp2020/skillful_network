package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.JobApplication;
import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/applications/jobs")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @GetMapping(value = "")
    public ResponseEntity<List<JobApplication>> getAllJobApplications() {
        List<JobApplication> jobApplications = jobApplicationService.getAllJobApplications();
        return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JobApplication> getAllJobApplicationsById(@PathVariable(value = "id") Long id) {
        JobApplication jobApplications = jobApplicationService.getJobApplicationById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvé avec l'id : " + id));
        return new ResponseEntity<JobApplication>(jobApplications, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/user")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = jobApplicationService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun user trouvé avec l'id  de candidature : " + id));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/joboffer")
    public ResponseEntity<JobOffer> getJobOfferById(@PathVariable(value = "id") Long id) {
        JobOffer jobOffer = jobApplicationService.getJobOfferById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune offre d'emploi trouvée avec l'id  de candidature : " + id));
        return new ResponseEntity<JobOffer>(jobOffer, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<JobApplication>> getJobApplicationByUser(@PathVariable(value = "userId") Long userId) {
        List<JobApplication> jobApplications = jobApplicationService.getJobApplicationsByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucunes candidatures trouvés avec le user id : " + userId));
        return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
    }

    @GetMapping(value = "/joboffer/{jobOfferId}")
    public ResponseEntity<List<JobApplication>> getJobApplicationByJobOffer(@PathVariable(value = "jobOfferId") Long jobOfferId) {
        List<JobApplication> jobApplications = jobApplicationService.getJobApplicationsByJobOfferId(jobOfferId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucunes candidatures trouvés avec le joOffer id : " + jobOfferId));
        return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public JobApplication saveJobApplicationn(@Valid @RequestBody JobApplication jobApplication) {
        return jobApplicationService.saveOrUpdateJobApplication(jobApplication);
    }

    @PutMapping(value = "/{jobApplicationOfferId}/user/{userId}")
    public ResponseEntity<User> setUserById(@PathVariable(value = "jobApplicationOfferId") Long jobApplicationOfferId, @PathVariable(value = "userId")Long userId) {
        User user = jobApplicationService.setUserById(jobApplicationOfferId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun user trouvé avec le user id : " + userId));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/{jobApplicationOfferId}/joboffer/{jobOfferId}")
    public ResponseEntity<JobOffer> setJobOfferById(@PathVariable(value = "jobApplicationOfferId") Long jobApplicationOfferId, @PathVariable(value = "jobOfferId")Long jobOfferId) {
        JobOffer jobOffer = jobApplicationService.setJobOfferById(jobApplicationOfferId, jobOfferId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune offre d'emploi trouvé avec le joOffer id : " + jobOfferId));
        return new ResponseEntity<JobOffer>(jobOffer, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public JobApplication updateJobApplicationn(@Valid @RequestBody JobApplication jobApplication) {
        return jobApplicationService.saveOrUpdateJobApplication(jobApplication);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteJobApplication(@PathVariable(value = "id") Long id) {
        jobApplicationService.deleteJobApplication(id);
    }
}
