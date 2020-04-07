package fr.uca.cdr.skillful_network.model.services;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Simulation;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Keyword;
import fr.uca.cdr.skillful_network.request.ExerciseForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SimulationService {

	List<Simulation> getAllSimulations();
	Optional<Simulation> getSimulationById(Long id);
	Optional<User> getUserById(Long id);
	Optional<List<Simulation>> getAllSimulationsByUserId(Long userId);
	Optional<Simulation> saveOrUpdateSimulation(Simulation simulation);
	Optional<Simulation> startSimulation(Long userId, String jobGoal);
	Optional<Simulation> getSimulationByExamId(Long idExam);
	void deleteSimulation(Long id);
	float calculateSimulationGrade(Set<ExerciseForm>exercises, Long examId);
	ArrayList<String> MatcherJobOfferJobGoal(String careerGoal, ArrayList<JobOffer> jobOffer);
	ArrayList<JobOffer> ListJobOfferByJobGoal(String careerGoal, ArrayList<JobOffer> jobOffer);
	Optional<Keyword> getKeyWordExoById(Long id);
	List<Keyword> findAllKeyWordExo();
	ArrayList<Keyword> exerciceMachJoboffer(ArrayList<Keyword> keyExo, ArrayList<String> keyJob);
}
