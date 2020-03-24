package fr.uca.cdr.skillful_network.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uca.cdr.skillful_network.model.entities.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {

}
