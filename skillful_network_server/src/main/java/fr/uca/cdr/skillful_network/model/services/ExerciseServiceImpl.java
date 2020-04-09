package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.*;
import fr.uca.cdr.skillful_network.model.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service(value = "ExerciseService")
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public Exercise saveOrUpdateExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

}
