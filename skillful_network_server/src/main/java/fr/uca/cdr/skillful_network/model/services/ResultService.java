package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.*;
import java.util.List;
import java.util.Optional;

public interface ResultService {

    List<Result> getAllResults();
    Optional<Result> getResultById(Long id);
    Result saveOrUpdateJobApplication(Result result);

}
