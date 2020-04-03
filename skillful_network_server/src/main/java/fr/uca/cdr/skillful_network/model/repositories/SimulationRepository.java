package fr.uca.cdr.skillful_network.model.repositories;

import fr.uca.cdr.skillful_network.model.entities.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SimulationRepository extends JpaRepository<Simulation, Long>{

    Optional<List<Simulation>> findByUserId(Long userId);
}
