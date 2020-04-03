package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.Simulation;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Result;
import fr.uca.cdr.skillful_network.model.repositories.SimulationRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.request.ExerciseForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service(value = "SimulationService")
public class SimulationServiceImpl implements SimulationService {

    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	QuestionSetService questionSetService;

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
        simulation.setUser(user);

        // *******************************************************************
        // analyse des criteres des objectifs par rapport aux joboffer / questionnaire / training
        // *******************************************************************

        simulation.setSynthesis("Lorem Ipsum ....");
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
    
    @Override
	public float calculateSimulationGrade(Set<ExerciseForm> exercises, Long simulationId) {
		float totalGrade = 0;
		float simulationGrade = 0;
		Set<Result> results=new HashSet<Result>();
		for (ExerciseForm exerciseForm : exercises) {
			float exerciceResult=questionSetService.calculateGrade(exerciseForm);
			Result result=new Result(exerciseForm.getId(),exerciceResult);
			results.add(result);
			totalGrade += exerciceResult;
		}
		simulationGrade = (float) (totalGrade * (0.8 / (exercises.size())));
		Simulation simulation = simulationRepository.findById(simulationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune simulation trouvée avec l'id : " + simulationId));
		simulation.setResults(results);
		return simulationGrade;
	}
}
