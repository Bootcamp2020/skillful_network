package fr.uca.cdr.skillful_network.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exam;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Result;
@Repository
public interface ResultRepository extends JpaRepository<Result,Long>{

}
