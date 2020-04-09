package fr.uca.cdr.skillful_network.controller;


import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import fr.uca.cdr.skillful_network.exceptions.ResourceNotFoundException;
import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.model.services.SkillService;
import fr.uca.cdr.skillful_network.model.services.UserService;
import fr.uca.cdr.skillful_network.request.UserForm;
import fr.uca.cdr.skillful_network.request.UserPwdUpdateForm;
import fr.uca.cdr.skillful_network.security.services.UserPrinciple;
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
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "/users")
	public List<User> getUsers() {
		return (List<User>) this.repository.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
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
	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "/users/search")
	public ResponseEntity<Page<User>> getUsersBySearch(@Valid PageTool pageTool,
			@RequestParam(name = "keyword", required = false) String keyword) {
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
			
		System.out.println(userRequest);
		if (userService.getUserById(id).isPresent()) {
			User userToUpdate = userService.getUserById(id).get();
			if (userRequest != null) {
				userToUpdate.setLastName(userRequest.get_lastName());
				userToUpdate.setFirstName(userRequest.get_firstName());
				userToUpdate.setBirthDate(userRequest.get_birthDate());
				userToUpdate.setEmail(userRequest.get_email());
				userToUpdate.setMobileNumber(userRequest.get_mobileNumber());
				userToUpdate.setSkillSet(userRequest.get_skillSet());
				userToUpdate.setQualificationSet(userRequest.get_qualificationSet());
				userToUpdate.setSubscriptionSet(userRequest.get_subscriptionSet());
				userToUpdate.setCareerGoal(userRequest.get_careerGoal());
				User userUpdated = userService.saveOrUpdateUser(userToUpdate);
				return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aucune données en paramètre");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé");

		}
	}
	
//	@PreAuthorize("hasRole('USER')")
//	@Transactional
//	@PutMapping(value = "/usersModifPassword/{id}")
//	public ResponseEntity<User> updateUserPassword(@PathVariable(value = "id") long id,
//			@Valid @RequestBody UserPwdUpdateForm userModifPwd) {
//
//		User userToUpdate = userService.getUserById(id).orElseThrow(
//				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + id));
//		
//		String userNewPwd = passwordEncoder.encode(userModifPwd.getPassword());
//		userToUpdate.setPassword(userNewPwd);
//		User userUpdated = userService.saveOrUpdateUser(userToUpdate);
//		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
//	}
	//Utilisation du current User pour la modification
	
//	@PreAuthorize("hasRole('USER')")
//	@Transactional
//	@PutMapping(value = "/usersModifPassword")
//	public ResponseEntity<User> updateUserPassword(@AuthenticationPrincipal User user,
//			@Valid @RequestBody UserPwdUpdateForm userModifPwd) {
//	
//		String userNewPwd = passwordEncoder.encode(userModifPwd.getPassword());
//		user.setPassword(userNewPwd);
//		User userUpdated = userService.saveOrUpdateUser(user);
//		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
//	}
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@PutMapping(value = "/usersModifPassword")
	public ResponseEntity<User> updateUserPassword(@AuthenticationPrincipal User user,
			@Valid @RequestBody UserPwdUpdateForm userModifPwd) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User userUpdated = (User) authentication.getPrincipal();


		String userNewPwd = passwordEncoder.encode(userModifPwd.getPassword());
		userUpdated.setPassword(userNewPwd);
		userService.saveOrUpdateUser(userUpdated);
		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
	}

	@GetMapping(value = "/testCreationRepo")
	public ResponseEntity<Boolean> testCreationRepo() {
		this.userService.createRepoImage();

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	

	@SuppressWarnings("resource")
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@RequestMapping(value = "/users/uploadImage", method = RequestMethod.POST)
	public ResponseEntity<String> fileUpload(@AuthenticationPrincipal User user,
			@RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		// On impose la liste des extention autorisées : .JPG, .JPEG, PNG :
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		
		Long id = user.getId();
		
//		User userforPhoto = userService.getUserById(id).orElseThrow(
//				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + id));
		
		List<String> listOfExtensions = new ArrayList<String>(3);
		listOfExtensions.add("jpg");
		listOfExtensions.add("jpeg");
		listOfExtensions.add("png");
		String imageName = image.getOriginalFilename().toLowerCase();
		File filecreating = new File(this.userService.createRepoImage());// "WebContent/images/"
		
		String newImageName = "WebContent/images/" + id + ".png";// DONE mettre à jour par I
		
		// on récupère l'extension du fichier qui est uploader
		String fileExtensionName = Files.getFileExtension(imageName);
		
		if (image.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Veillez selectionner une photo profil");
			
			return new ResponseEntity<String>("Veuillez entrer une image valide", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		}
		try {
			File convertFile = new File(newImageName);
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(image.getBytes());
			
			if (image.getBytes().length > 1000 * 500) {
				return new ResponseEntity<String>("La taille de l'image doit etre inferieure ou egale 500 ko",
						HttpStatus.UNSUPPORTED_MEDIA_TYPE);
			}
			// onverifie que l'extension du fichier qui est uploader correspondent à celle
			// de la liste imposée
			else if (!listOfExtensions.contains(fileExtensionName)) {
				return new ResponseEntity<String>("Votre format de l'image n'est pas prit en charge",
						HttpStatus.UNSUPPORTED_MEDIA_TYPE);
			}
			System.out.println(currentUser.isPhoto());
			fout.close();
			currentUser.setPhoto(true);
			System.out.println(currentUser.isPhoto());
			userService.saveOrUpdateUser(currentUser);
//	   currentUser = userService.saveOrUpdateUser(currentUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("OK", HttpStatus.OK);

	//@PreAuthorize("hasRole('USER')")
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
	
///// Upload Imgae avec restriction extention+taille de l'image:
	@SuppressWarnings("resource")
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@RequestMapping(value = "/users/uploadImage", method = RequestMethod.POST)
	public ResponseEntity<String> fileUpload( @AuthenticationPrincipal UserPrinciple user, @RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
		// On impose la liste des extention autorisées : .JPG, .JPEG, PNG :
		Long id= user.getId();
		User userforPhoto = userService.getUserById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + id));
		List<String> listOfExtensions = new ArrayList<String>(3);
		listOfExtensions.add("jpg");
		listOfExtensions.add("jpeg");
		listOfExtensions.add("png"); 
		String imageName = image.getOriginalFilename().toLowerCase();
		File filecreating= new File(this.userService.createRepoImage());//"WebContent/images/"	
		
		String newImageName = "WebContent/images/"+id+".png";// DONE mettre à jour par I
		
		// on récupère l'extension du fichier qui est uploader
		String fileExtensionName = Files.getFileExtension(imageName);

		if (image.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Veillez selectionner une photo profil");
			return new ResponseEntity<String>("Veuillez entrer une image valide", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		}
		try {
			File convertFile = new File(newImageName);
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(image.getBytes());
			
			if (image.getBytes().length > 1000*500) {
				return new ResponseEntity<String>("La taille de l'image doit etre inferieure ou egale 500 ko", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
			}
			// onverifie que  l'extension du fichier qui est uploader correspondent à celle de la liste imposée
			else if (!listOfExtensions.contains(fileExtensionName)) {
				return new ResponseEntity<String>("Votre format de l'image n'est pas prit en charge", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
			}
			System.out.println(userforPhoto.isPhoto());
	   fout.close();
	   	userforPhoto.setPhoto(true);
	   System.out.println(userforPhoto.isPhoto());
	   userforPhoto = userService.saveOrUpdateUser(userforPhoto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	
////////////Methode pour que l'utilasteur affiche sa photo profil///////////////////////
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE) // @RequestParam("file") MultipartFile file,
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id, HttpServletResponse response)
			throws IOException {
		Optional<User> userforphoto = userService.getUserById(id); // this just gets the data from a database//
																	// response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		if (!userforphoto.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id :" + id);
		}

		System.out.println(id);
		System.out.println("WebContent/images/"+id+".png");
		File f = new File("WebContent/images/"+id+".png");
		BufferedImage o = ImageIO.read(f);
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ImageIO.write(o, "png", b);
		b.flush();
		byte[] img = b.toByteArray();
		b.close();
		
		/*InputStream in = getClass()
			      .getResourceAsStream("WebContent/images/"+id+".png");

		byte[] img = IOUtils.toByteArray(in);*/

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);

	}
	
	//@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/usersbyId/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {

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
//	@PreAuthorize("hasRole('USER')")
//	@Transactional
//	@DeleteMapping("/users/{userId}/skills/{skillId}")
//	public ResponseEntity<Skill> deleteSkillById(@PathVariable(value = "userId") Long id,
//			@PathVariable(value = "skillId") Long skillId) {
//
////		On vérifie que l'utilisateur existe bien
//		User userToUpdate = this.userService.getUserById(id).orElseThrow(
//				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune user trouvee avec l'id : " + id));
//
////		On vérifie que la compétence existe bien
//		Skill skillToDelete = this.skillService.getSkillById(skillId)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//						"Aucune competence trouvee avec l'id : " + skillId));
//
////		On récupère le liste de compétences de l'utilisateur
//		Set<Skill> listSkill = userToUpdate.getSkillSet();
//
////      Si la compétence à enlever est bien dans la liste de compétences de l'utilisateur alors on le mets à jours 
//		if (listSkill.contains(skillToDelete)) {
//			listSkill.remove(skillToDelete);
//			userToUpdate.setSkillSet(listSkill);
//			this.userService.saveOrUpdateUser(userToUpdate);
//			return new ResponseEntity<Skill>(skillToDelete, HttpStatus.OK);
//		}
////		Dans le cas contraire on renvoie une exception
//		else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La compétence demandée avec l'id : " + skillId
//					+ " n'est pas dans la liste de compétences de l'utilisateur avec l'id : " + id);
//		}
//	}
	
	//Utilisation du current User pour la suppression des skills
	@PreAuthorize("hasRole('USER')")
	@Transactional
	@DeleteMapping("/users/skills/{skillId}")
	public ResponseEntity<Skill> deleteSkillById(@AuthenticationPrincipal User user,
			@PathVariable(value = "skillId") Long skillId) {

//		On vérifie que la compétence existe bien
		Skill skillToDelete = this.skillService.getSkillById(skillId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucune competence trouvee avec l'id : " + skillId));

//		On récupère le liste de compétences de l'utilisateur
		Set<Skill> listSkill = user.getSkillSet();

//      Si la compétence à enlever est bien dans la liste de compétences de l'utilisateur alors on la supprime 
		if (listSkill.contains(skillToDelete)) {
			listSkill.remove(skillToDelete);
			user.setSkillSet(listSkill);
			this.userService.saveOrUpdateUser(user);
			return new ResponseEntity<Skill>(skillToDelete, HttpStatus.OK);
		}
//		Dans le cas contraire on renvoie une exception
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La compétence demandée avec l'id : " + skillId
					+ " n'est pas dans la liste de compétences de l'utilisateur avec l'id : " + user.getId());
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
	//@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "users/{id}/skills")
	public ResponseEntity<Set<Skill>> getAllSkillByUserSkills(@PathVariable(value = "id") Long id) {
		Set<Skill> listSkills = this.userService.getUserById(id).map((user) -> {
			return user.getSkillSet();
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune compétence trouvée avec l'id : " + id));
		return new ResponseEntity<Set<Skill>>(listSkills, HttpStatus.OK);
	}

}
