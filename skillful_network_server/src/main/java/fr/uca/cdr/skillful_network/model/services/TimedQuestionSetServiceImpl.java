package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.*;
import fr.uca.cdr.skillful_network.model.repositories.TimedQuestionSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service(value = "TimedQuestionSetService")
public class TimedQuestionSetServiceImpl implements TimedQuestionSetService {

    @Autowired
    private TimedQuestionSetRepository timedQuestionSetRepository;

    @Override
    public List<TimedQuestionSet> getAllTimedQuestionSets() {
        return timedQuestionSetRepository.findAll();
    }

    @Override
    public Optional<TimedQuestionSet> getTimedQuestionSetById(Long id) {
        return timedQuestionSetRepository.findById(id);
    }

    @Override
    public TimedQuestionSet saveOrUpdateTimedQuestionSet(TimedQuestionSet timedQuestionSet) {
        return timedQuestionSetRepository.save(timedQuestionSet);
    }

}
