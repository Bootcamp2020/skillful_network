package fr.uca.cdr.skillful_network.model.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.uca.cdr.skillful_network.model.entities.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    Optional<Training> findById(Long id);

	Page<Training> findByNameContainsOrOrganisationContainsAllIgnoreCase(Pageable pageable, String keyword1, String keyword2);

	Training save(Optional<Training> trainingToUpdate);

}

