package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Simulation;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exam;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exercise;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Keyword;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Question;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.QuestionSet;
import fr.uca.cdr.skillful_network.model.repositories.KeywordRepository;
import fr.uca.cdr.skillful_network.model.services.ExerciseService;
import fr.uca.cdr.skillful_network.model.services.JobOfferService;

import fr.uca.cdr.skillful_network.model.repositories.SimulationRepository;

import fr.uca.cdr.skillful_network.model.services.SimulationService;
import fr.uca.cdr.skillful_network.model.services.SimulationServiceImpl;
import fr.uca.cdr.skillful_network.model.services.UserService;
import fr.uca.cdr.skillful_network.request.ExerciseForm;
import fr.uca.cdr.skillful_network.request.SimulationForm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.sound.midi.Soundbank;
import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/simulations")
public class SimulationController {

	@Autowired
	private SimulationService simulationService;
	@Autowired
	private UserService userService;
	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private ExerciseService exerciseService;
	@Autowired
	private KeywordRepository keywordRepository;

	private SimulationRepository simulationRepository;


	// #########################################################################
	// GET methods
	// #########################################################################

	// Provide all simulations
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "/all")
	public ResponseEntity<List<Simulation>> getAllsimulations() {
		return new ResponseEntity<>(this.simulationService.getAllSimulations(), HttpStatus.OK);
	}

	// Provide simulation by ID
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Simulation> getSimulationById(@PathVariable(value = "id") Long id) {
		Simulation simulation = this.simulationService.getSimulationById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune simulation trouvée avec l'id : " + id));
		return new ResponseEntity<Simulation>(simulation, HttpStatus.OK);
	}

//	 NOT NECESSARY ANYMORE SINCE JSON INFINITE RECURSIVE LOOP IS SOLVED
//	// Provide user of a simulation by simulation ID
//	@PreAuthorize("hasRole('ENTREPRISE')")
//	@GetMapping(value = "/{id}/user")
//	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
//		User user = simulationService.getUserById(id)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id de candidature : " + id));
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}

	// Provide all simulations by user ID
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	// @GetMapping(value = "/user/{userId}")
	@GetMapping(value = "")
	public ResponseEntity<List<Simulation>> getAllSimulationsByUserId(@RequestParam(name = "userid") Long userId) {
		List<Simulation> simulations = this.simulationService.getAllSimulationsByUserId(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucune simulation trouvée avec le user id : " + userId));
		return new ResponseEntity<>(simulations, HttpStatus.OK);
	}

	// #########################################################################
	// POST methods
	// #########################################################################

	// start a new simulation based on a provided job goal

	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@PostMapping(value = "/user/{userId}")
	public ResponseEntity<Exam>startSimulation(@AuthenticationPrincipal User user) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User currentUser = (User) authentication.getPrincipal();
		Exam exam =this.simulationService.startSimulation(user.getId()) .orElseThrow(() ->
	  new ResponseStatusException(HttpStatus.NOT_FOUND,"Une erreur est survenue pendant l'éxécécution de la simulation."));
	  return new ResponseEntity<>(exam, HttpStatus.OK);
	}
	 

	

	@PostMapping(value = "/{id}/answer")
	public ResponseEntity<Simulation> simulationResult(@PathVariable(value = "id") Long examId,
			@Valid @RequestBody SimulationForm simulationForm) {
		Simulation simulation = simulationService.evaluateSimulation(simulationForm.getExerciseSet(), examId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Une erreur est survenue pendant l'évaluation de la simulation."));
		return new ResponseEntity<>(simulation, HttpStatus.OK);

	}

	// #########################################################################
	// DEL methods
	// #########################################################################

	// Delete a simulation
	@PreAuthorize("hasRole('ENTREPRISE')")
	@DeleteMapping(value = "/{id}")
	public void deleteSimulation(@PathVariable(value = "id") Long id) {
		simulationService.deleteSimulation(id);
	}

}
