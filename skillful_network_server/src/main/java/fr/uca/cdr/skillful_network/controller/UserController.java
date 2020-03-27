package fr.uca.cdr.skillful_network.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.exceptions.ResourceNotFoundException;
import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.model.services.SkillService;
import fr.uca.cdr.skillful_network.model.services.UserService;
import fr.uca.cdr.skillful_network.request.UserForm;
import fr.uca.cdr.skillful_network.request.UserPwdUpdateForm;
import fr.uca.cdr.skillful_network.tools.PageTool;

/**
 * Cette classe est responsable du traitement des requêtes liées aux
 * utilisateurs comme /users.
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private final UserRepository repository;
	@Autowired
	private UserService userService;
	@Autowired
	private SkillService skillService;

	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/users")
	public List<User> getUsers() {
		return (List<User>) this.repository.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/users/")
	public ResponseEntity<Page<User>> getUsersPerPage(@Valid PageTool pageTool) {
		if (pageTool != null) {
			Page<User> listUserByPage = userService.getPageOfEntities(pageTool);
			return new ResponseEntity<Page<User>>(listUserByPage, HttpStatus.OK);
		} else {
			System.out.println(pageTool);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données en paramètre non valide");
		}
	}

	@GetMapping(value = "/users/search")
	public ResponseEntity<Page<User>> getUsersBySearch(@Valid PageTool pageTool, @RequestParam(name = "keyword", required = false) String keyword) {
		if (pageTool != null && keyword != null) {
			Page<User> listUsersSeachByPage = userService.searchUsersByKeyword(pageTool.requestPage(), keyword);
			return new ResponseEntity<Page<User>>(listUsersSeachByPage, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données en paramètre non valide");
		}
	}
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@PutMapping(value = "/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") long id,
			@Valid @RequestBody UserForm userRequest) {

		if (userService.getUserById(id).isPresent()) {
			User userToUpdate = userService.getUserById(id).get();
			if (userRequest != null) {
				userToUpdate.setLastName(userRequest.getLastName());
				userToUpdate.setFirstName(userRequest.getFirstName());
				userToUpdate.setBirthDate(userRequest.getBirthDate());
				userToUpdate.setEmail(userRequest.getEmail());
				userToUpdate.setMobileNumber(userRequest.getMobileNumber());
				userToUpdate.setSkillSet(userRequest.getSkillSet());
				userToUpdate.setQualificationSet(userRequest.getQualificationSet());
				userToUpdate.setSubscriptionSet(userRequest.getSubscriptionSet());
				userToUpdate.setCareerGoal(userRequest.getCareerGoal());
				User userUpdated = userService.saveOrUpdateUser(userToUpdate);
				return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aucune données en paramètre");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé");

		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@PutMapping(value = "/usersModifPassword/{id}")
	public ResponseEntity<User> updateUserPassword(@PathVariable(value = "id") long id,
			@Valid @RequestBody UserPwdUpdateForm userModifPwd) {

		User userToUpdate = userService.getUserById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + id));

		userToUpdate.setPassword(userModifPwd.getPassword());
		User userUpdated = userService.saveOrUpdateUser(userToUpdate);
		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
	}

	@GetMapping(value = "/testCreationRepo")
	public ResponseEntity<Boolean> testCreationRepo() {
		this.userService.createRepoImage();

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = { MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE })
	public String fileUpload(@RequestParam("image") MultipartFile image) throws IOException {

		File convertFile = new File("WebContent/images/" + image.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(image.getBytes());
		fout.close();
		return "File is upload successfully" + image.getOriginalFilename();
	}
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/usersbyId/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {

		Optional<User> user = userService.getUserById(id);
		if (!user.isPresent()) {

			throw new ResourceNotFoundException("User not found with id : " + id);

		}
		return ResponseEntity.ok().body(user.get());
	}

//	@GetMapping(value = "/users/{userId}/skills/{skillId}")
//	public ResponseEntity<Skill> getOneSkillByUser(@PathVariable(value = "userId")Long userId, @PathVariable(value = "skillId")Long skillId) {
//		
////		On vérifie que l'utilisateur existe bien
//		User userFromDb = userService.getUserById(userId)
//						  .orElseThrow(
//								  () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + userId)
//								  );
////		On vérifie que la compétence existe bien
//		Skill SkillFromDb = skillService.getSkillById(skillId)
//				  			.orElseThrow(
//				  					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune compétence trouvée avec l'id " + skillId)
//				  				);
////		On récupère le liste de compétences de l'utilisateur
//		Set<Skill> userSkills = userFromDb.getSkillSet();
////		Si la compétence de la bdd est contenue dans la liste de l'utilisateur, on la renvoie
//		if (userSkills.contains(SkillFromDb)) {
//			return new ResponseEntity<Skill> ( SkillFromDb,HttpStatus.OK);
//		}
////		Dans le cas contraire on renvoie une exception
//		else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La compétence demandée avec l'id : "+skillId+" n'est pas dans la liste de compétences de l'utilisateur avec l'id : "+userId);
//		}
//	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/users/{userId}/skills/{skillName}")
	public ResponseEntity<Skill> getOneSkillByNameByUser(@PathVariable(value = "userId") Long userId,
			@PathVariable(value = "skillName") String skillName) {

//		On vérifie que l'utilisateur existe bien
		User userFromDb = userService.getUserById(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucun utilisateur trouvé avec l'id " + userId));

//		On vérifie que la compétence existe bien
		Skill SkillFromDb = skillService.getSkillByName(skillName)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucune compétence trouvée avec le nom : " + skillName));

//		On récupère la liste de compétences de l'utilisateur
		Set<Skill> userSkills = userFromDb.getSkillSet();

//		Si la compétence de la bdd est contenue dans la liste de l'utilisateur, on la renvoie
		if (userSkills.contains(SkillFromDb)) {
			return new ResponseEntity<Skill>(SkillFromDb, HttpStatus.OK);
		}
//		Dans le cas contraire on renvoie une exception
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La compétence demandée avec le nom : " + skillName
					+ " n'est pas dans la liste de compétences de l'utilisateur avec l'id : " + userId);
		}
	}
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@DeleteMapping("/users/{userId}/skills/{skillId}")
	public ResponseEntity<Skill> deleteSkillById(@PathVariable(value = "userId") Long id,
			@PathVariable(value = "skillId") Long skillId) {

//		On vérifie que l'utilisateur existe bien
		User userToUpdate = this.userService.getUserById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune user trouvee avec l'id : " + id));

//		On vérifie que la compétence existe bien
		Skill skillToDelete = this.skillService.getSkillById(skillId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucune competence trouvee avec l'id : " + skillId));

//		On récupère le liste de compétences de l'utilisateur
		Set<Skill> listSkill = userToUpdate.getSkillSet();

//      Si la compétence à enlever est bien dans la liste de compétences de l'utilisateur alors on le mets à jours 
		if (listSkill.contains(skillToDelete)) {
			listSkill.remove(skillToDelete);
			userToUpdate.setSkillSet(listSkill);
			this.userService.saveOrUpdateUser(userToUpdate);
			return new ResponseEntity<Skill>(skillToDelete, HttpStatus.OK);
		}
//		Dans le cas contraire on renvoie une exception
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La compétence demandée avec l'id : " + skillId
					+ " n'est pas dans la liste de compétences de l'utilisateur avec l'id : " + id);
		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@PostMapping("/users/{userId}/skills/{skillId}")
	public ResponseEntity<Skill> setSkillbyId(@PathVariable(value = "userId") Long id,
			@PathVariable(value = "skillId") Long skillId) {

        //On vérifie que l'utilisateur existe bien
		User userToUpdate = this.userService.getUserById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune user trouvee avec l'id : " + id));

       //On vérifie que la compétence existe bien
		Skill skillToAdd = this.skillService.getSkillById(skillId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucune competence trouvee avec l'id : " + skillId));

        //	On récupère le liste de compétences de l'utilisateur
		Set<Skill> listSkill = userToUpdate.getSkillSet();

       //Si la compétence à ajouter n'est pas dans la liste de compétences de l'utilisateur, alors on le mets à jours 
		if (!(listSkill.contains(skillToAdd))) {
			listSkill.add(skillToAdd);
			userToUpdate.setSkillSet(listSkill);
			this.userService.saveOrUpdateUser(userToUpdate);
			return new ResponseEntity<Skill>(skillToAdd, HttpStatus.OK);
		}
			//Dans le cas contraire on renvoie une exception
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La compétence demandée avec l'id : " + skillId
					+ " est déjà dans la liste de compétences de l'utilisateur avec l'id : " + id);
		}
	}
		@GetMapping(value = "/users/{id}/skills")
		public ResponseEntity<Set<Skill>> getAllSkillByUser(@PathVariable(value = "id") Long id) {
			Set<Skill> listSkills = this.userService.getUserById(id)
					.map((user) -> {
						return user.getSkillSet();})
					.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune compétence trouvée avec l'id : " + id));
			return new ResponseEntity<Set<Skill>>(listSkills, HttpStatus.OK);
		}
		@GetMapping(value = "/users/{id}/Qualifications")
		public ResponseEntity<Set<Qualification>> getAllQualificationByUser(@PathVariable(value = "id") Long id) {
			Set<Qualification> listQualifications = this.userService.getUserById(id)
					.map((user) -> {
						return user.getQualificationSet();})
					.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune compétence trouvée avec l'id : " + id));
			return new ResponseEntity<Set<Qualification>>(listQualifications, HttpStatus.OK);
		}
		@GetMapping(value = "/users/{id}/Subscription")
		public ResponseEntity<Set<Subscription>> getAllSubscriptionByUser(@PathVariable(value = "id") Long id) {
			Set<Subscription> listSubscription = this.userService.getUserById(id)
					.map((user) -> {
						return user.getSubscriptionSet();})
					.orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune compétence trouvée avec l'id : " + id));
			return new ResponseEntity<Set<Subscription>>(listSubscription, HttpStatus.OK);
		}
		
//	@GetMapping(value = "users/{id}/skills")
//	public ResponseEntity<Set<Skill>> getAllSkillByUser1(@PathVariable(value = "id") Long id) {
//		Set<Skill> listSkills = this.userService.getUserById(id).map((user) -> {
//			return user.getSkillSet();
//		}).orElseThrow(
//				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune compétence trouvée avec l'id : " + id));
//		return new ResponseEntity<Set<Skill>>(listSkills, HttpStatus.OK);
//	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "users/{id}/skills")
	public ResponseEntity<Set<Skill>> getAllSkillByUserSkills(@PathVariable(value = "id") Long id) {
		Set<Skill> listSkills = this.userService.getUserById(id).map((user) -> {
			return user.getSkillSet();
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune compétence trouvée avec l'id : " + id));
		return new ResponseEntity<Set<Skill>>(listSkills, HttpStatus.OK);
	}

}
