package fr.uca.cdr.skillful_network.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.uca.cdr.skillful_network.exceptions.ResourceNotFoundException;
import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.services.TrainingService;
import fr.uca.cdr.skillful_network.tools.PageTool;

@CrossOrigin("*")
@RestController
@RequestMapping("/trainings")
public class TrainingController {

	@Autowired
	TrainingService trainingService;

	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "")
	public List<Training> getTrainings() {
		return trainingService.getAllTraining();
	}

	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/")
	public ResponseEntity<Page<Training>> getTrainingsPerPage(@Valid PageTool pageTool) {
		if (pageTool != null) {
			Page<Training> listTrainingsByPage = trainingService.getPageOfEntities(pageTool);
			return new ResponseEntity<Page<Training>>(listTrainingsByPage, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données en paramètre non valides");
		}
	}

	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Training> getTrainingById(@PathVariable("id") Long id) {
		Optional<Training> trainingFromDb = trainingService.getTrainingById(id);
		if (trainingFromDb.isPresent()) {
			return new ResponseEntity<Training>(trainingFromDb.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training n'a pas été trouvé avec l'id : " + id);
		}
	}

	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "/search")
	public ResponseEntity<Page<Training>> getTrainingsBySearch(@Valid PageTool pageTool,
			@RequestParam(name = "keyword", required = false) String keyword) {
		if (pageTool != null && keyword != null) {
			Page<Training> lisTrainingsbySearch = trainingService.searchTrainingByKeyword(pageTool.requestPage(),
					keyword);
			return new ResponseEntity<Page<Training>>(lisTrainingsbySearch, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données en paramètre non valides");
		}
	}

	// suppression d'une formation
	@PreAuthorize("hasRole('ORGANISME')")
	@DeleteMapping(value = "/training/{id}")
	public void deleteTraining(@PathVariable(value = "id") Long id) {
		trainingService.deleteTraining(id);
	}

	// Update d'une formation
	@PreAuthorize("hasRole('ORGANISME')")
	@PutMapping(value = "/training/{id}")
	@Transactional
	public ResponseEntity<Training> updateTraining(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Training training) {
		Optional<Training> trainingToUpdate = trainingService.getTrainingById(id);
		if (trainingToUpdate == null) {
			throw new ResourceNotFoundException("formation non trouvée : " + id);
		}
		trainingToUpdate.get().setName(training.getName());
		trainingToUpdate.get().setOrganisation(training.getOrganisation());
		trainingToUpdate.get().setDescription(training.getDescription());
		trainingToUpdate.get().setFinancer(training.getFinancer());
		trainingToUpdate.get().setDateBeg(training.getDateBeg());
		trainingToUpdate.get().setDateEnd(training.getDateEnd());
		trainingToUpdate.get().setDateUpload(training.getDateUpload());
		trainingToUpdate.get().setDurationHours(training.getDurationHours());
		trainingToUpdate.get().setPrerequisites(training.getPrerequisites());
		trainingToUpdate.get().setKeywords(training.getKeywords());
		Training trainingUpdated = trainingService.saveOrUpdateTraining(trainingToUpdate);
		return new ResponseEntity<Training>(trainingUpdated, HttpStatus.OK);
	}
}
