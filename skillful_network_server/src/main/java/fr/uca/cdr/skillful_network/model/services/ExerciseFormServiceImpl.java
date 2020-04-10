package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.request.*;
import fr.uca.cdr.skillful_network.model.repositories.ExerciseFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service(value = "ExerciseFormService")
public class ExerciseFormServiceImpl implements ExerciseFormService {

    @Autowired
    private ExerciseFormRepository exerciseFormRepository;

    @Override
    public List<ExerciseForm> getAllExerciseForms() {
        return exerciseFormRepository.findAll();
    }

    @Override
    public Optional<ExerciseForm> getExerciseFormById(Long id) {
        return exerciseFormRepository.findById(id);
    }

    @Override
    public ExerciseForm saveOrUpdateExerciseForm(ExerciseForm exerciseForm) {
        return exerciseFormRepository.save(exerciseForm);
    }

}
