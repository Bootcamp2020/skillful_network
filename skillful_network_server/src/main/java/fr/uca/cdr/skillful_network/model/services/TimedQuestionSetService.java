package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.*;
import java.util.List;
import java.util.Optional;

public interface TimedQuestionSetService {

    List<TimedQuestionSet> getAllTimedQuestionSets();
    Optional<TimedQuestionSet> getTimedQuestionSetById(Long id);
    TimedQuestionSet saveOrUpdateJobApplication(TimedQuestionSet timedQuestionSet);

}
