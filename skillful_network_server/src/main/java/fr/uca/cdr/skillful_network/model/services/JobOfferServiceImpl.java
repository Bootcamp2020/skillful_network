package fr.uca.cdr.skillful_network.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.repositories.JobOfferRepository;
import fr.uca.cdr.skillful_network.tools.PageTool;

@Service(value = "JobOfferService")
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	JobOfferRepository jobOfferRepository;

	@Override
	public Optional<JobOffer> getJobOfferById(Long id) {
		return jobOfferRepository.findById(id);
	}

	@Override
	public Page<JobOffer> getPageOfEntities(PageTool pageTool) {
		return jobOfferRepository.findAll(pageTool.requestPage());
	}

	@Override
	public Page<JobOffer> searchJobOfferByKeyword(Pageable pageable, String keyword) {
		return jobOfferRepository.findByNameContainsOrCompanyContainsAllIgnoreCase(pageable, keyword, keyword);
	}

	@Override
	public List<JobOffer> getAllJobOffer() {
		return jobOfferRepository.findAll();
	}

}
