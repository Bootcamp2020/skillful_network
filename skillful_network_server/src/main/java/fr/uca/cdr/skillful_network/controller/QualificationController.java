package fr.uca.cdr.skillful_network.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.repositories.QualificationRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.model.services.QualificationService;
import fr.uca.cdr.skillful_network.tools.AutoCompletion;

@RestController
@CrossOrigin(origins = "*")
public class QualificationController {

	@Autowired
	private final QualificationService qualificationservice;

	// Autocompletion init
	AutoCompletion<Qualification> completor = new AutoCompletion<>(Qualification.class, "name", "userSet");
	
	public QualificationController(QualificationService qualificationservice) {
		this.qualificationservice = qualificationservice;
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/qualifications")
	public List<Qualification> getQualifications(@RequestParam(name = "prefix", required = false) String prefix) {
		if (prefix == null) {
			return qualificationservice.getAllQualifications();
		} else {
			return qualificationservice.getQualificationByPrefix(prefix);
		}
	}

	// Le changement de RequestBody par RequestParam est par rapport à une limite angular 
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/qualifications/candidates")
	public ResponseEntity<List<Qualification>>  getCandidatesByMatch(@RequestParam(required=false , name="contain") String match) {
		return new ResponseEntity<List<Qualification>>(qualificationservice.getQualificationsByMatch(match), HttpStatus.OK);
	}
}
