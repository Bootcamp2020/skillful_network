package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.JobApplication;
import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<JobApplication>> getJobApplicationByUser(@PathVariable(value = "userId")Long userId) {
        List<JobApplication> jobApplications = jobApplicationService.getJobApplicationsByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucunes candidatures trouvés avec le user id : " + userId));
        return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
    }

    @GetMapping(value = "/joboffer/{jobOfferId}")
    public ResponseEntity<List<JobApplication>> getJobApplicationByJobOffer(@PathVariable(value = "jobOfferId")Long jobOfferId) {
        List<JobApplication> jobApplications = jobApplicationService.getJobApplicationsByJobOfferId(jobOfferId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucunes candidatures trouvés avec le le joOffer id : " + jobOfferId));
        return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public JobApplication saveJobApplicationn(@Valid @RequestBody JobApplication jobApplication) {
        return jobApplicationService.saveOrUpdateJobApplication(jobApplication);
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
