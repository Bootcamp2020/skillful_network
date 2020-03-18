
package fr.uca.cdr.skillful_network.model.services;

import java.util.List;

public interface QualificationService {
	
	List<String> getAllQualifications();
	List<String> getQualificationsByPrefix(String prefix);


}
