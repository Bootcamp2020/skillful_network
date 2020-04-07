package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.*;
import fr.uca.cdr.skillful_network.model.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service(value = "ExamService")
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Optional<Exam> getExamById(Long id) {
        return examRepository.findById(id);
    }

    @Override
    public Exam saveOrUpdateJobApplication(Exam exam) {
        return examRepository.save(exam);
    }

}
