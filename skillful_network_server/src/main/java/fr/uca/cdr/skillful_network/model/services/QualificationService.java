
package fr.uca.cdr.skillful_network.model.services;

import java.util.List;
import java.util.Optional;

import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.repositories.QualificationRepository;

public interface QualificationService {
	
	List<Qualification> getAllQualifications();
	List<Qualification> getQualificationByPrefix(String prefix);


}
