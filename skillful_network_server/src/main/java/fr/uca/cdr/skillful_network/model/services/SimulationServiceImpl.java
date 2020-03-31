package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.Application;
import fr.uca.cdr.skillful_network.model.entities.JobApplication;
import fr.uca.cdr.skillful_network.model.entities.Simulation;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.SimulationRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service(value = "SimulationService")
public class SimulationServiceImpl implements SimulationService {

    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Simulation> getAllSimulations() {
        return simulationRepository.findAll();
    }

    @Override
    public Optional<Simulation> getSimulationById(Long id) { return simulationRepository.findById(id); }

    @Override
    public Optional<User> getUserById(Long id) {
        return simulationRepository.findById(id)
                .map(Simulation::getUser);
    }

    @Override
    public Optional<List<Simulation>> getAllSimulationsByUserId(Long userId) {
        return simulationRepository.findByUserId(userId);
    }

    @Override
    public Optional<Simulation> saveOrUpdateSimulation(Simulation simulation) {
        return Optional.of(simulationRepository.save(simulation));
    }
    @Override
    public Optional<Simulation> startSimulation(Long userId, String jobGoal) {
        System.out.println(">>> SimulationServiceImpl.startSimulation(userID: " + userId + ", jobGoal: " + jobGoal +")");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id : " + userId));
        Simulation simulation = new Simulation(jobGoal);
        simulation.setSynthesis("Lorem Ipsum ....");
        simulation.setUser(user);
        System.out.println(">>> new simulation: " + simulation);
        simulation = simulationRepository.save(simulation);
        System.out.println(">>> saved simulation: " + simulation);
        return Optional.of(simulation);
    }

    @Override
    public void deleteSimulation(Long id) {
        simulationRepository.findById(id)
                .map ( simulation -> {
                    simulationRepository.deleteById(id);
                    return simulation;})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune simulation trouvée avec l'id : " + id));
    }
}
