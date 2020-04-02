package fr.uca.cdr.skillful_network.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.tools.PageTool;

public interface JobOfferService {
	
	List<JobOffer> getAllJobOffer();
	
	Optional<JobOffer> getJobOfferById(Long id);
	
	Page<JobOffer> getPageOfEntities(PageTool pageTool);

	Page<JobOffer> searchJobOfferByKeyword(Pageable pageable, String keyword);
	
	JobOffer saveOrUpdateJobOffer(JobOffer jobOffer);
	
	void deleteJobOffer(Long id);
	
	
}
