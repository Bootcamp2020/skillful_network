package fr.uca.cdr.skillful_network.model.repositories;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
