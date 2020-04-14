package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.JobApplication;
import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // #########################################################################
    // Get methods
    // #########################################################################

    // Provide all applications
    @PreAuthorize("hasRole('ENTREPRISE')")
    @GetMapping(value = "")
    public ResponseEntity<List<JobApplication>> getAllJobApplications() {
        List<JobApplication> applications = jobApplicationService.getAllJobApplications();
        return new ResponseEntity<List<JobApplication>>(applications, HttpStatus.OK);
    }

    // Provide application by its id
    @PreAuthorize("hasRole('ENTREPRISE')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<JobApplication> getAllJobApplicationsById(@PathVariable(value = "id") Long id) {
        JobApplication application = jobApplicationService.getJobApplicationById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvé avec l'id : " + id));
        return new ResponseEntity<JobApplication>(application, HttpStatus.OK);
    }

    // Provide user of an application by its id
    @PreAuthorize("hasRole('ENTREPRISE')")
    @GetMapping(value = "/{id}/user")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = jobApplicationService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id de candidature : " + id));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Provide job offer of an application by its id
    @PreAuthorize("hasAnyRole('ENTREPRISE','USER')")
    @GetMapping(value = "/{id}/joboffer")
    public ResponseEntity<JobOffer> getJobOfferById(@PathVariable(value = "id") Long id) {
        JobOffer jobOffer = jobApplicationService.getJobOfferById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune offre d'emploi trouvée avec l'id  de candidature : " + id));
        System.out.println("hello");
        return new ResponseEntity<JobOffer>(jobOffer, HttpStatus.OK);
    }

    // Provide all applications for a user by his id
    //@PreAuthorize("hasRole('ENTREPRISE')")
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<JobApplication>> getJobApplicationByUser(@PathVariable(value = "userId") Long userId) {
        List<JobApplication> applications = jobApplicationService.getJobApplicationsByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.OK, "Aucune candidature trouvée avec l'id d'utilisateur : " + userId));
        return new ResponseEntity<List<JobApplication>>(applications, HttpStatus.OK);
    }
    /*public ResponseEntity<Set<JobApplication>> getJobApplicationByUser(@PathVariable(value = "userId") Long userId) {
		Set<JobApplication> listapplications = this.userService.getUserById(userId).map((user) -> {
			return user.getJobApplicationSet();
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvée avec l'id d'utilisateur : " + userId));
		return new ResponseEntity<Set<JobApplication>>(listapplications, HttpStatus.OK);
	}*/

    // Provide all applications for a training by its id
    @PreAuthorize("hasRole('ENTREPRISE')")
    @GetMapping(value = "/joboffer/{jobOfferId}")
    public ResponseEntity<List<JobApplication>> getJobApplicationByJobOffer(@PathVariable(value = "jobOfferId") Long jobOfferId) {
        List<JobApplication> applications = jobApplicationService.getJobApplicationsByJobOfferId(jobOfferId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvée avec l'id de l'offre d'emploi : " + jobOfferId));
        return new ResponseEntity<List<JobApplication>>(applications, HttpStatus.OK);
    }

    // #########################################################################
    // Post methods
    // #########################################################################

    // Create a new application
    @PreAuthorize("hasRole('ENTREPRISE')")
    @PostMapping(value = "")
    public JobApplication saveJobApplicationn(@Valid @RequestBody JobApplication application) {
        return jobApplicationService.saveOrUpdateJobApplication(application);
    }

    // Create a new application with a user and a jobOffer
    @PreAuthorize("hasAnyRole('ENTREPRISE','USER')")
    @PostMapping(value = "/user/{userId}/joboffer/{jobOfferId}")
    public JobApplication saveTrainingApplication(@PathVariable(value = "userId") Long userId, @PathVariable(value = "jobOfferId") Long jobOfferId) {
        return jobApplicationService.saveJobApplicationById(userId, jobOfferId);
    }

    // #########################################################################
    // Put methods
    // #########################################################################

    // Update an application
    @PreAuthorize("hasRole('ENTREPRISE')")
    @PutMapping(value = "")
    public JobApplication updateJobApplicationn(@Valid @RequestBody JobApplication application) {
        return jobApplicationService.saveOrUpdateJobApplication(application);
    }

    // Set application's associated user by their ids
    @PreAuthorize("hasRole('ENTREPRISE')")
    @PutMapping(value = "/{id}/user/{userId}")
    public ResponseEntity<User> setUserById(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        User user = jobApplicationService.setUserById(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id : " + userId));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Set application's associated jobOffer by their ids
    @PreAuthorize("hasRole('ENTREPRISE')")
    @PutMapping(value = "/{id}/joboffer/{jobOfferId}")
    public ResponseEntity<JobOffer> setJobOfferById(@PathVariable(value = "id") Long id, @PathVariable(value = "jobOfferId") Long jobOfferId) {
        JobOffer jobOffer = jobApplicationService.setJobOfferById(id, jobOfferId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune offre d'emploi trouvé avec l'id : " + jobOfferId));
        return new ResponseEntity<JobOffer>(jobOffer, HttpStatus.OK);
    }

    // #########################################################################
    // Delete methods
    // #########################################################################

    // Delete an application
    @PreAuthorize("hasRole('ENTREPRISE')")
    @DeleteMapping(value = "/{id}")
    public void deleteJobApplication(@PathVariable(value = "id") Long id) {
        jobApplicationService.deleteJobApplication(id);
    }
}
