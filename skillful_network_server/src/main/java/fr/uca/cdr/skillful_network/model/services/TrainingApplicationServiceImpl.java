package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.Application;
import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.TrainingApplication;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.TrainingApplicationRepository;
import fr.uca.cdr.skillful_network.model.repositories.TrainingRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service(value = "TrainingApplicationService")
public class TrainingApplicationServiceImpl implements TrainingApplicationService {

    @Autowired
    private TrainingApplicationRepository trainingApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public List<TrainingApplication> getAllTrainingApplications() {
        return trainingApplicationRepository.findAll();
    }

    @Override
    public Optional<TrainingApplication> getTrainingApplicationById(Long id) {
        return trainingApplicationRepository.findById(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return trainingApplicationRepository.findById(id)
                .map(Application::getUser);
    }

    @Override
    public Optional<Training> getTrainingById(Long id) {
        return trainingApplicationRepository.findById(id)
                .map(TrainingApplication::getTraining);
    }

    @Override
    public Optional<List<TrainingApplication>> getTrainingApplicationsByUserId(Long userId) {
        return trainingApplicationRepository.findByUserId(userId);
    }

    @Override
    public Optional<List<TrainingApplication>> getTrainingApplicationsByTrainingId(Long trainingId) {
        return trainingApplicationRepository.findByTrainingId(trainingId);
    }

    @Override
    public TrainingApplication saveOrUpdateTrainingApplication(TrainingApplication trainingApplication) {
        return trainingApplicationRepository.save(trainingApplication);
    }

    @Override
    public TrainingApplication saveTrainingApplicationById(Long userId, Long trainingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id : " + userId));
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune formation trouvée avec l'id : " + trainingId));
        return trainingApplicationRepository.save(new TrainingApplication(user, training));
    }

    @Override
    public Optional<User> setUserById(Long id, Long userId) {
        TrainingApplication application = trainingApplicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvé avec l'id : " + id));
        return userRepository.findById(userId)
                .map( user -> {
                    application.setUser(user);
                    return trainingApplicationRepository.save(application).getUser();
                });
    }

    @Override
    public Optional<Training> setTrainingById(Long id, Long trainingId) {
        TrainingApplication application = trainingApplicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune candidature trouvée avec l'id : " + id));
        return trainingRepository.findById(trainingId)
                .map(training -> {
                    application.setTraining(training);
                    return trainingApplicationRepository.save(application).getTraining();
                });
    }

    @Override
    public void deleteTrainingApplication(Long id) {
        trainingApplicationRepository.deleteById(id);
    }
}
