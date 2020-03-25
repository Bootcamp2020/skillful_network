package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.TrainingApplication;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.TrainingApplicationRepository;
import fr.uca.cdr.skillful_network.model.repositories.TrainingRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .map(trainingApplication -> trainingApplication.getUser());
    }

    @Override
    public Optional<Training> getTrainingById(Long id) {
        return trainingApplicationRepository.findById(id)
                .map(trainingApplication -> trainingApplication.getTraining());
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
    public Optional<User> setUserById(Long trainingApplicationId, Long userId) {
        return trainingApplicationRepository.findById(trainingApplicationId)
                .map(trainingApplication -> {
                    if (!userRepository.findById(userId).isPresent()) {
                        return null;
                    }
                    trainingApplication.setUser(userRepository.findById(userId).get());
                    return trainingApplicationRepository.save(trainingApplication).getUser();
                });
    }

    @Override
    public Optional<Training> setTrainingById(Long trainingApplicationId, Long trainingId) {
        return trainingApplicationRepository.findById(trainingApplicationId)
                .map(trainingApplication -> {
                    if (!trainingRepository.findById(trainingId).isPresent()) {
                        return null;
                    }
                    trainingApplication.setTraining(trainingRepository.findById(trainingId).get());
                    return trainingApplicationRepository.save(trainingApplication).getTraining();
                });
    }

    @Override
    public void deleteTrainingApplication(Long id) {
        trainingApplicationRepository.deleteById(id);
    }
}
