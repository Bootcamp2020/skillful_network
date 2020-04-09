package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.request.*;
import java.util.List;
import java.util.Optional;

public interface ExerciseFormService {

    List<ExerciseForm> getAllExerciseForms();
    Optional<ExerciseForm> getExerciseFormById(Long id);
    ExerciseForm saveOrUpdateExerciseForm(ExerciseForm exerciseForm);

}
