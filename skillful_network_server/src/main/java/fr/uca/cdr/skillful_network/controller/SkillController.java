package fr.uca.cdr.skillful_network.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.services.SkillService;
import fr.uca.cdr.skillful_network.tools.AutoCompletion;

@CrossOrigin("*")
@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	// Autocompletion init
	AutoCompletion<Skill> completor = new AutoCompletion<>(Skill.class, "name", "userList");

	@GetMapping(value = "")
	public ResponseEntity<List<Skill>> getAllSkills() {
		List<Skill> listSkill = this.skillService.getAllSkills();
		return new ResponseEntity<List<Skill>>(listSkill, HttpStatus.OK);
	}

// 
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<Skill> getSkillById(@PathVariable(value = "id") Long id) {
//		Skill skillFromDb = this.skillService.getSkillById(id).orElseThrow(
//				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune compétence trouvée avec l'id : " + id));
//		return new ResponseEntity<Skill>(skillFromDb, HttpStatus.OK);
//	}

	@GetMapping(value = "/{name}")
	public ResponseEntity<Skill> getSkillByName(@PathVariable(value = "name") String name) {
		Skill skillFromDb = this.skillService.getSkillByName(name)
				.orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Aucune compétence trouvée avec le nom " + name)
					);
		return new ResponseEntity<Skill>(skillFromDb, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}/users")
	public ResponseEntity<Set<User>> getAllUserBySkill(@PathVariable(value = "id") Long id) {
		Set<User> listUser = this.skillService.getSkillById(id)
				.map((skill) -> {
					return skill.getUserList();})
				.orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune compétence trouvée avec l'id : " + id));
		
		return new ResponseEntity<Set<User>>(listUser, HttpStatus.OK);

	}
	
	@GetMapping(value = "/search/{keyword}", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<String>> search(@PathVariable("keyword") String keyword) {
		
			List<String> listSkill = this.skillService.searchSkill(keyword)
					.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aucun matching avec le keyword : "+keyword)
					);
			return new ResponseEntity<List<String>>(listSkill, HttpStatus.OK);
		 
			 

	}
	
    // Le changement de RequestBody par RequestParam est par rapport à une limite angular et que surtout ça respecte pas les bonnes pratiques
	@GetMapping(value = "/candidates")
	public List<Skill>  getAutoCompletionByMatch(@RequestParam(required=false , name="contain") String pMatch) {
		// Get subscriptions list
		List<Skill> skills = skillService.getAllSkills();
		
		// looking for completion candidates
		List<Skill> candidates = completor.findCandidates(skills, pMatch);
		return candidates;
	}
}
