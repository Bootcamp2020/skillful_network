package fr.uca.cdr.skillful_network.controller;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.model.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 * Cette classe est responsable du traitement des requêtes liées aux
 * utilisateurs comme /users.
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 *
	 */
	private final UserRepository repository;

	public UserController(UserRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = "/users")
	public List<User> getUsers() {
		return (List<User>) this.repository.findAll();
	}

	@GetMapping(value = "/testCreationRepo")
	public ResponseEntity<Boolean> testCreationRepo() {
		this.userService.createRepoImage();

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

//   // Methode AMAL pour afficher l'image 
//    @RequestMapping(value = "/image/{id:.+}", method = RequestMethod.GET)
//    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id, HttpServletResponse response) {
//        byte[] image = userService.getdImagebyId();  //this just gets the data from a database//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        return ResponseEntity.ok()
//        		.contentType(MediaType.IMAGE_JPEG)
//                .body(image);
//    }

//  // Methode AMAL pour afficher l'image 

	@RequestMapping(value = "/image/{id:.+}", method = RequestMethod.GET) // @RequestParam("file") MultipartFile file,
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id, HttpServletResponse response)
			throws IOException {
		Optional<User> userforphoto = userService.getUserbyId(id); // this just gets the data from a database//
																	// response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		if (userforphoto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé" + id);
		}

		File f = new File("WebContent/images/iconeprofildefaut.jpg");
		BufferedImage o = ImageIO.read(f);
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ImageIO.write(o, "jpg", b);
		byte[] img = b.toByteArray();

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);

	}
//	@RequestMapping(value = "/image", method = RequestMethod.GET)
//	public @ResponseBody void getImage(HttpServletResponse response) throws IOException, URISyntaxException {
//
//		BufferedImage image = ImageIO.read(new File("WebContent/images/iconeprofildefaut.jpg"));
//
//		response.setContentType("image/jpg");
//		OutputStream out;
//
//		out = response.getOutputStream();
//		ImageIO.write(image, "jpg", out);
//
//	}
//       		       
//	@Transactional
//	@PutMapping(value = "/usersModifImage/{id}")
//	public ResponseEntity<User> updateUserImage(@PathVariable(value = "id") long id,
//			@Valid @RequestBody UserModifImage userModifImage) {
//		User userToUpdate = userService.getUserById(id).orElseThrow(
//				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouvé avec l'id " + id));
//		userToUpdate.setId(userModifImage.getId());
//		User userUpdated = userService.saveOrUpdateUser(userToUpdate);
//		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
//	}

////    // Methode Sihem Douja Mazen pour uploader l'image 
	/*
	 * @RequestMapping(value = "/updatePhotoProfil", method =
	 * RequestMethod.POST,produces = {MediaType.IMAGE_JPEG_VALUE
	 * ,MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE}) public
	 * ResponseEntity<byte[]> updatePhotoProfil(@RequestParam("file") MultipartFile
	 * file) {
	 * 
	 * if(userService.updateImage()== true) { //this.userService.findImage();
	 * ClassPathResource imgFile = new
	 * ClassPathResource("WebContent/images/iconeprofildefaut.jpg"); byte[] bytes =
	 * StreamUtils.copyToByteArray(imgFile.getInputStream());
	 * 
	 * return ResponseEntity .ok() .contentType(MediaType.IMAGE_JPEG)
	 * .contentType(MediaType.IMAGE_PNG) .body(bytes); } throw new
	 * ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune image charg�e"); }
	 */

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = { MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE })
	public String fileUpload(@RequestParam("image") MultipartFile image) throws IOException {

		File convertFile = new File("WebContent/images/" + image.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(image.getBytes());
		fout.close();
		return "File is upload successfully";

	}

}
