package fr.uca.cdr.skillful_network.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uca.cdr.skillful_network.model.entities.JobApplication;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>{

}
