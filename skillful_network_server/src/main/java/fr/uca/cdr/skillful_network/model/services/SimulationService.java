package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.Simulation;
import fr.uca.cdr.skillful_network.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface SimulationService {

	List<Simulation> getAllSimulations();
	Optional<Simulation> getSimulationById(Long id);
	Optional<User> getUserById(Long id);
	Optional<List<Simulation>> getAllSimulationsByUserId(Long userId);
	Optional<Simulation> saveOrUpdateSimulation(Simulation simulation);
	Optional<Simulation> startSimulation(Long userId, String jobGoal);
	void deleteSimulation(Long id);

}
