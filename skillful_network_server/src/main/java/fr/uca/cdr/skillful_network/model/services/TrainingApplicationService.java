package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.TrainingApplication;
import fr.uca.cdr.skillful_network.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface TrainingApplicationService {

    List<TrainingApplication> getAllTrainingApplications();

    Optional<TrainingApplication> getTrainingApplicationById(Long id);

    Optional<User> getUserById(Long id);

    Optional<Training> getTrainingById(Long id);

    Optional<List<TrainingApplication>> getTrainingApplicationsByUserId(Long userId);

    Optional<List<TrainingApplication>> getTrainingApplicationsByTrainingId(Long trainingId);

    Optional<User> setUserById(Long trainingApplicationId, Long userId);

    Optional<Training> setTrainingById(Long trainingApplicationId, Long trainingId);

    TrainingApplication saveOrUpdateTrainingApplication(TrainingApplication trainingApplication);

    void deleteTrainingApplication(Long id);
}
