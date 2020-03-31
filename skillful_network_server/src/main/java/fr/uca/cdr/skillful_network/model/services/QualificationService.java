
package fr.uca.cdr.skillful_network.model.services;

import java.util.List;

import fr.uca.cdr.skillful_network.model.entities.Qualification;

public interface QualificationService {

	List<Qualification> getAllQualifications();
	List<Qualification> getQualificationByPrefix(String prefix);
	List<Qualification> getQualificationsByMatch(String match);
}
