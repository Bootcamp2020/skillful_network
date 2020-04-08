package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.Simulation;
import fr.uca.cdr.skillful_network.model.entities.Training;
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

	@Autowired
	private TrainingService trainingService;

	private final float JOBACCESSSCORE = 0.80f;

	private final float TRAININGSCORE = 0.4f;

	@Override
	public List<Simulation> getAllSimulations() {
		return simulationRepository.findAll();
	}

	@Override
	public Optional<Simulation> getSimulationById(Long id) {
		return simulationRepository.findById(id);
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return simulationRepository.findById(id).map(Simulation::getUser);
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
		System.out
				.println(">>> SimulationServiceImpl.startSimulation(userID: " + userId + ", jobGoal: " + jobGoal + ")");
		User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Aucun utilisateur trouvé avec l'id : " + userId));
		Simulation simulation = new Simulation(jobGoal);
		simulation.setUser(user);

		// *******************************************************************
		// analyse des criteres des objectifs par rapport aux joboffer / questionnaire /
		// training
		// *******************************************************************

		simulation.setSynthesis("Lorem Ipsum ....");
		simulation = simulationRepository.save(simulation);
		System.out.println(">>> saved simulation: " + simulation);
		return Optional.of(simulation);
	}

	@Override
	public void deleteSimulation(Long id) {
		simulationRepository.findById(id).map(simulation -> {
			simulationRepository.deleteById(id);
			return simulation;
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune simulation trouvée avec l'id : " + id));
	}

	@Override
	public Optional<Simulation> evaluateSimulation(Set<ExerciseForm> exercises, Long examId) {
		Simulation simulation = simulationRepository.findByExamId(examId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucune simulation trouvée avec l'id : " + examId));
		simulation.setTraining(null);
		float simulationGrade = calculateSimulationGrade(exercises, simulation);
		accessToJobOffer(simulationGrade, simulation);
		if (!simulation.isJobAccess()) {
			Training training = accessToJobOfferViaTraining(simulationGrade);
			if(training != null) {
				simulation.setTraining(training);
				simulation.setJobAccess(true);
			}
		}
		simulationRepository.save(simulation);
		return Optional.of(simulation);
	}
    
    @Override
    public Optional<Simulation> getSimulationByExamId(Long examId){
    	return simulationRepository.findByExamId(examId);
    }
    
    private float calculateSimulationGrade(Set<ExerciseForm> exercises, Simulation simulation) {
		float simulationGrade = 0;
		Set<Result> results = new HashSet<Result>();
		float weightByExercice = 0.8f / exercises.size();
		for (ExerciseForm exerciseForm : exercises) {
			float exerciceResult = questionSetService.calculateGrade(exerciseForm, weightByExercice);
			Result result = new Result(exerciseForm.getId(), exerciceResult);
			results.add(result);
			simulationGrade += exerciceResult;
		}
		simulation.setResults(results);
		return simulationGrade;
	}

	private void accessToJobOffer(float simulationGrade, Simulation simulation) {
		if (simulationGrade >= JOBACCESSSCORE) {
			simulation.setJobAccess(true);
		} else {
			simulation.setJobAccess(false);
		}
	}

	private Training accessToJobOfferViaTraining(float simulationGrade) {
		Training training=null;
		if ((simulationGrade + TRAININGSCORE) >= JOBACCESSSCORE) {
			training = trainingService.getTrainingById(2L).orElseThrow(//Ici on passe l'Id du training(l'exemple de la demo: formation developpeur full stack) en dur en attendant future optimisation 
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun training trouvé avec l'id: " + 2L));
		}
		return training;
	}	
}
