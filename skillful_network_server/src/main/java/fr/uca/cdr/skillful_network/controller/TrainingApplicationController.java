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

    // #########################################################################
    // Get methods
    // #########################################################################

    // Provide all applications
    @GetMapping(value = "")
    public ResponseEntity<List<TrainingApplication>> getAllTrainingApplications() {
        List<TrainingApplication> applications = trainingApplicationService.getAllTrainingApplications();
        return new ResponseEntity<List<TrainingApplication>>(applications, HttpStatus.OK);
    }

    // Provide application by its id
    @GetMapping(value = "/{id}")
    public ResponseEntity<TrainingApplication> getTrainingApplicationById(@PathVariable(value = "id") Long id) {
        TrainingApplication application = trainingApplicationService.getTrainingApplicationById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvée avec l'id : " + id));
        return new ResponseEntity<TrainingApplication>(application, HttpStatus.OK);
    }

    // Provide user of an application by its id
    @GetMapping(value = "/{id}/user")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = trainingApplicationService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id de candidature : " + id));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Provide training of an application by its id
    @GetMapping(value = "/{id}/training")
    public ResponseEntity<Training> getTrainingById(@PathVariable(value = "id") Long id) {
        Training training = trainingApplicationService.getTrainingById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune formation trouvée avec l'id de candidature : " + id));
        return new ResponseEntity<Training>(training, HttpStatus.OK);
    }

    // Provide all applications for a user by his id
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<TrainingApplication>> getTrainingApplicationByUser(@PathVariable(value = "userId") Long userId) {
        List<TrainingApplication> applications = trainingApplicationService.getTrainingApplicationsByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvée avec l'id d'utilisateur : " + userId));
        return new ResponseEntity<List<TrainingApplication>>(applications, HttpStatus.OK);
    }

    // Provide all applications for a training by its id
    @GetMapping(value = "/training/{trainingId}")
    public ResponseEntity<List<TrainingApplication>> getTrainingApplicationByTraining(@PathVariable(value = "trainingId") Long trainingId) {
        List<TrainingApplication> applications = trainingApplicationService.getTrainingApplicationsByTrainingId(trainingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvée avec l'id de formation : " + trainingId));
        return new ResponseEntity<List<TrainingApplication>>(applications, HttpStatus.OK);
    }

    // #########################################################################
    // Post methods
    // #########################################################################

    // Create a new application
    @PostMapping(value = "")
    public TrainingApplication saveTrainingApplication(@Valid @RequestBody TrainingApplication application) {
        return trainingApplicationService.saveOrUpdateTrainingApplication(application);
    }

    // Create a new application with a user and a training
    @PostMapping(value = "/user/{userId}/training/{trainingId}")
    public TrainingApplication saveTrainingApplication(@PathVariable(value = "userId") Long userId, @PathVariable(value = "trainingId") Long trainingId) {
        return trainingApplicationService.saveTrainingApplicationById(userId, trainingId);
    }

    // #########################################################################
    // Put methods
    // #########################################################################

    // Update an application
    @PutMapping(value = "")
    public TrainingApplication updateTrainingApplicationn(@Valid @RequestBody TrainingApplication application) {
        return trainingApplicationService.saveOrUpdateTrainingApplication(application);
    }

    // Set application's associated user by their ids
    @PutMapping(value = "/{id}/user/{userId}")
    public ResponseEntity<User> setUserById(@PathVariable(value = "id") Long id, @PathVariable(value = "userId") Long userId) {
        User user = trainingApplicationService.setUserById(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id : " + userId));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Set application's associated training by their ids
    @PutMapping(value = "/{id}/training/{trainingId}")
    public ResponseEntity<Training> setTrainingById(@PathVariable(value = "id") Long id, @PathVariable(value = "trainingId") Long trainingId) {
        Training training = trainingApplicationService.setTrainingById(id, trainingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune formation trouvée avec l'id : " + trainingId));
        return new ResponseEntity<Training>(training, HttpStatus.OK);
    }

    // #########################################################################
    // Delete methods
    // #########################################################################

    // Delete an application
    @DeleteMapping(value = "/{id}")
    public void deleteTrainingApplication(@PathVariable(value = "id") Long id) {
        trainingApplicationService.deleteTrainingApplication(id);
    }
}
