package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.TrainingApplication;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.services.TrainingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/applications/trainings")
public class TrainingApplicationController {

    @Autowired
    private TrainingApplicationService trainingApplicationService;

    @GetMapping(value = "")
    public ResponseEntity<List<TrainingApplication>> getAllTrainingApplications() {
        List<TrainingApplication> jobApplications = trainingApplicationService.getAllTrainingApplications();
        return new ResponseEntity<List<TrainingApplication>>(jobApplications, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TrainingApplication> getTrainingApplicationById(@PathVariable(value = "id") Long id) {
        TrainingApplication trainingApplications = trainingApplicationService.getTrainingApplicationById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvé avec l'id : " + id));
        return new ResponseEntity<TrainingApplication>(trainingApplications, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/user")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = trainingApplicationService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun user trouvé avec l'id  de candidature : " + id));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/training")
    public ResponseEntity<Training> getTrainingById(@PathVariable(value = "id") Long id) {
        Training training = trainingApplicationService.getTrainingById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune offre d'emploi trouvée avec l'id  de candidature : " + id));
        return new ResponseEntity<Training>(training, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<TrainingApplication>> getTrainingApplicationByUser(@PathVariable(value = "userId") Long userId) {
        List<TrainingApplication> jobApplications = trainingApplicationService.getTrainingApplicationsByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucunes candidatures trouvés avec le user id : " + userId));
        return new ResponseEntity<List<TrainingApplication>>(jobApplications, HttpStatus.OK);
    }

    @GetMapping(value = "/training/{trainingId}")
    public ResponseEntity<List<TrainingApplication>> getTrainingApplicationByTraining(@PathVariable(value = "trainingId") Long trainingId) {
        List<TrainingApplication> jobApplications = trainingApplicationService.getTrainingApplicationsByTrainingId(trainingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucunes candidatures trouvés avec le joOffer id : " + trainingId));
        return new ResponseEntity<List<TrainingApplication>>(jobApplications, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public TrainingApplication saveTrainingApplication(@Valid @RequestBody TrainingApplication jobApplication) {
        return trainingApplicationService.saveOrUpdateTrainingApplication(jobApplication);
    }

    @PutMapping(value = "/{jobApplicationOfferId}/user/{userId}")
    public ResponseEntity<User> setUserById(@PathVariable(value = "jobApplicationOfferId") Long jobApplicationOfferId, @PathVariable(value = "userId") Long userId) {
        User user = trainingApplicationService.setUserById(jobApplicationOfferId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun user trouvé avec le user id : " + userId));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/{jobApplicationOfferId}/training/{trainingId}")
    public ResponseEntity<Training> setTrainingById(@PathVariable(value = "jobApplicationOfferId") Long jobApplicationOfferId, @PathVariable(value = "trainingId") Long trainingId) {
        Training training = trainingApplicationService.setTrainingById(jobApplicationOfferId, trainingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune offre d'emploi trouvé avec le joOffer id : " + trainingId));
        return new ResponseEntity<Training>(training, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public TrainingApplication updateTrainingApplicationn(@Valid @RequestBody TrainingApplication jobApplication) {
        return trainingApplicationService.saveOrUpdateTrainingApplication(jobApplication);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTrainingApplication(@PathVariable(value = "id") Long id) {
        trainingApplicationService.deleteTrainingApplication(id);
    }
}
