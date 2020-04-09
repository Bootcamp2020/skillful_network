package fr.uca.cdr.skillful_network.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Question;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.QuestionSet;

@Repository
public interface QuestionSetRepository extends JpaRepository<QuestionSet,Long>  {
	
	Optional<QuestionSet> findById(Long id);
}
