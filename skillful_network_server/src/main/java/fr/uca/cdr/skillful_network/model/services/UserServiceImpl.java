package fr.uca.cdr.skillful_network.model.services;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.tools.PageTool;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@Override
	public Boolean alreadyExists(String mail) {
		Optional<User> oUser = userRepository.findByEmail(mail);
		return oUser.isPresent();
	}

	@Override
	public Boolean existingMailIsValidated(String mail) {
		return userRepository.findByEmail(mail).get().isValidated();
	}

	@Override
	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public User saveOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	public void sendMail(String email, String codeAutoGen) {
		emailService.sendEmail(email, codeAutoGen);
	}

	@Override
	public Boolean createRepoImage() {
		String dossier1 = "WebContent/images";
		if (!new File(dossier1).exists()) {
			new File(dossier1).mkdirs();
		}
		return true;
	}

	@Override
	public Boolean updateImage() {
		String photoprofiljpg = "WebContent/images/iconeprofildefaut.jpg";
		String photoprofiljpeg = "WebContent/images/iconeprofildefaut.jepg";
		if (!new File(photoprofiljpg).exists() || !new File(photoprofiljpg).exists()) {
			new File(photoprofiljpg).mkdirs();
			new File(photoprofiljpeg).mkdirs();
		}
		return true;
	}

	@Override
	public Optional<User> findByEmail(String mail) {
		return userRepository.findByEmail(mail);
	}

	@Override
	public Page<User> getPageOfEntities(int objPerPage, int pageIndex) {
		PageTool pageTool = new PageTool();
		pageTool.setPage(pageIndex);
		pageTool.setNumberOfEntities(objPerPage);
		if (pageTool.requestPage() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la page index en paramètre est non valide");
		} else {
			return userRepository.findAll(pageTool.requestPage().get());

		}

	}

}
