package fr.uca.cdr.skillful_network.model.services;

import java.util.List;

import fr.uca.cdr.skillful_network.tools.AutoCompletion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.repositories.QualificationRepository;

@Service(value = "QualificationService")
public class QualificationServiceImpl implements QualificationService {

	@Autowired
	private QualificationRepository qualificationrepository;

	// Autocompletion init
	AutoCompletion<Qualification> completor = new AutoCompletion<>(Qualification.class, "name", "userSet");

	@Override
	public List<Qualification> getAllQualifications() {
		return qualificationrepository.findAll();
	}

	@Override
	public List<Qualification> getQualificationByPrefix(String prefix) {
		return qualificationrepository.search(prefix);
	}

	@Override
	public List<Qualification> getQualificationsByMatch(String match) {
		return completor.findCandidates(qualificationrepository.findAll(), match);
	}

}
