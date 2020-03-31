package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.Simulation;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.services.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/simulations")
public class SimulationController {

	@Autowired
	private SimulationService simulationService;

	// #########################################################################
	// Get methods
	// #########################################################################

	// Provide all simulations
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER)")
	@GetMapping(value = "")
	public ResponseEntity<List<Simulation>> getAllsimulations() {
		return new ResponseEntity<>(this.simulationService.getAllSimulations(), HttpStatus.OK);
	}

	// Provide simulation by ID
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER)")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Simulation> getSimulationById(@PathVariable(value = "id") Long id) {
		Simulation simulation = this.simulationService.getSimulationById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune simulation trouvée avec l'id : " + id));
		return new ResponseEntity<Simulation>(simulation, HttpStatus.OK);
	}

	// Provide user of a simulation by simulation ID
	@PreAuthorize("hasRole('ENTREPRISE')")
	@GetMapping(value = "/{id}/user")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
		User user = simulationService.getUserById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id de candidature : " + id));
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// Provide all simulations by user ID
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<List<Simulation> > getAllSimulationsByUserId(@PathVariable(value = "userId") Long userId) {
		List<Simulation> simulations = this.simulationService.getAllSimulationsByUserId(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Aucune simulation trouvée avec le user id : " + userId));
		return new ResponseEntity<>(simulations, HttpStatus.OK);
	}

	// #########################################################################
	// Post methods
	// #########################################################################

	// Create a new simulation
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "")
	public ResponseEntity<Simulation> saveSimulation(@Valid @RequestBody Simulation simulation) {
		simulation = this.simulationService.saveOrUpdateSimulation(simulation)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La simulation ne peut pas être sauvegardée."));
		return new ResponseEntity<>(simulation, HttpStatus.OK);
	}
	
    // start a new simulation based on a provided job goal
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@PostMapping(value = "/user/{userId}")
	public ResponseEntity<Simulation> startSimulation(@PathVariable(value = "userId") Long userId, @RequestParam(name="goal") String jobGoal) {
		Simulation simulation = this.simulationService.startSimulation(userId, jobGoal)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Une erreur est survenue pendant l'éxécécution de la simulation."));
		return new ResponseEntity<>(simulation, HttpStatus.OK);
	}

	// #########################################################################
	// Del methods
	// #########################################################################

	// Delete a simulation
	@PreAuthorize("hasRole('ENTREPRISE')")
	@DeleteMapping(value = "/{id}")
	public void deleteSimulation(@PathVariable(value = "id") Long id) {
		simulationService.deleteSimulation(id);
	}

}
