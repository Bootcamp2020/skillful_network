package fr.uca.cdr.skillful_network.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exercise;
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long>{

}
