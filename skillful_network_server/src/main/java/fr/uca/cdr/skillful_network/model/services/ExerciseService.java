package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.*;
import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    List<Exercise> getAllExercises();
    Optional<Exercise> getExerciseById(Long id);
    Exercise saveOrUpdateExercise(Exercise exercise);

}
