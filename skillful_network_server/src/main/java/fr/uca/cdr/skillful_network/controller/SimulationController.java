package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Simulation;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exercise;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Keyword;
import fr.uca.cdr.skillful_network.model.repositories.KeywordRepository;
import fr.uca.cdr.skillful_network.model.services.JobOfferService;
import fr.uca.cdr.skillful_network.model.services.SimulationService;
import fr.uca.cdr.skillful_network.model.services.SimulationServiceImpl;
import fr.uca.cdr.skillful_network.model.services.UserService;
import fr.uca.cdr.skillful_network.request.ExerciseForm;
import fr.uca.cdr.skillful_network.request.SimulationForm;
import fr.uca.cdr.skillful_network.security.services.UserPrinciple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
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
	private KeywordRepository keywordRepository;
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
		Simulation simulation = this.simulationService.getSimulationById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune simulation trouvée avec l'id : " + id));
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
	//@GetMapping(value = "/user/{userId}")
	@GetMapping(value = "")
	public ResponseEntity<List<Simulation> > getAllSimulationsByUserId(@RequestParam(name="userid") Long userId) {
		List<Simulation> simulations = this.simulationService.getAllSimulationsByUserId(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Aucune simulation trouvée avec le user id : " + userId));
		return new ResponseEntity<>(simulations, HttpStatus.OK);
	}

	// #########################################################################
	// POST methods
	// #########################################################################

    // start a new simulation based on a provided job goal
    @PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")	
	//@PostMapping(value = "/user/{userId}")
	/*@PostMapping(value = "")
	public ResponseEntity<Simulation> startSimulation(@RequestParam(name="userid") Long userId, @RequestParam(name="goal") String jobGoal) {
		Simulation simulation = this.simulationService.startSimulation(userId, jobGoal)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Une erreur est survenue pendant l'éxécécution de la simulation."));
		return new ResponseEntity<>(simulation, HttpStatus.OK);
	}*/
	@PostMapping(value = "/user/startSimulation")
    public ResponseEntity<ArrayList<Exercise>> startSimulation(@AuthenticationPrincipal UserPrinciple user){
        System.out.println(user);
        System.out.println(user.getCareerGoal());
        // recuperer tous les jobOffer
        List<JobOffer> jobOffer = jobOfferService.getAllJobOffer();
        System.out.println(jobOffer);
        // Matcher l'objectif de carrière(c'est une phrase) avec les mots clés de toute la liste de job-offers
        ArrayList<String> wordMatchCareerGoal = new ArrayList<String>() ;
        wordMatchCareerGoal.addAll(simulationService.MatcherJobOfferJobGoal(user.getCareerGoal(),(ArrayList<JobOffer>) jobOffer ));
        System.out.println(wordMatchCareerGoal);
       // renvoyer toute la liste des job-offers qui match avec l'objectif de carrière
        /* ArrayList<JobOffer> listeJobOfferMatcheJobGoal = new ArrayList<JobOffer>();
        listeJobOfferMatcheJobGoal.addAll(simulationService.ListJobOfferByJobGoal(user.getCareerGoal(),(ArrayList<JobOffer>) jobOffer ));
        System.out.println( listeJobOfferMatcheJobGoal);*/
        /*if(wordMatchCareerGoal.size()==0) {
        	return new ResponseEntity<ArrayList<Keyword>>(HttpStatus.NOT_FOUND);
        }else {*/
        //recupérer tous la liste de mots clés qui match avec les mots clés de job-offers matché dejà avec l'objectif de carrière
        ArrayList<Keyword> listKeywordExo =  (ArrayList<Keyword>) simulationService.findAllKeyWordExo();
        System.out.println("listKeywordExo :" +listKeywordExo);
        ArrayList<Keyword> listeKeyWordsEquals = new ArrayList<Keyword>();
        listeKeyWordsEquals.addAll(simulationService.exerciceMachJoboffer(listKeywordExo,wordMatchCareerGoal));
    	//macher les mots clés (listeKeyWordsEquals) avec les exercices par id 
        ArrayList<Exercise> listExerciseSimulation=new ArrayList<Exercise>();
        for(int i=0; i < listeKeyWordsEquals.size(); i++) {
        	
        	listExerciseSimulation.addAll(listeKeyWordsEquals.get(i).getExercises());
        }
        Set<Exercise> mySet = new HashSet<Exercise>(listExerciseSimulation);
        ArrayList<Exercise>  listExerciseSimulationFinal = new ArrayList<Exercise>(mySet);
        System.out.println(listExerciseSimulationFinal);
        return new ResponseEntity<ArrayList<Exercise>>(listExerciseSimulationFinal, HttpStatus.OK);
    }
    /*Simulation simulation = this.simulationService.startSimulation(userId, user.getCareerGoal())
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Une erreur est survenue pendant l'éxécécution de la simulation."));*/
	@PostMapping(value = "/{id}/answer")
	public float simulationResult(@PathVariable(value = "id") Long simulationId,@Valid @RequestBody SimulationForm simulationForm) {
		float simulationGrade=0;
        Set<ExerciseForm> exercises = simulationForm.getExerciseSet();
        simulationGrade=simulationService.calculateSimulationGrade(exercises, simulationId);
		return simulationGrade; 	
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
