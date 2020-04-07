package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.*;
import java.util.List;
import java.util.Optional;

public interface ExamService {

    List<Exam> getAllExams();
    Optional<Exam> getExamById(Long id);
    Exam saveOrUpdateExam(Exam exam);

}
