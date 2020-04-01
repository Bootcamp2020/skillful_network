package fr.uca.cdr.skillful_network.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.services.JobOfferService;
import fr.uca.cdr.skillful_network.tools.PageTool;

@CrossOrigin("*")
@RestController
@RequestMapping("/offers")
public class JobOfferController {

	@Autowired
	JobOfferService jobOfferService;

	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "")
	public List<JobOffer> getOffers() {
		return jobOfferService.getAllJobOffer();
	}

	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME')")
	@GetMapping(value = "/")
	public ResponseEntity<Page<JobOffer>> getOffersPerPage(@Valid PageTool pageTool) {
		if (pageTool != null) {
			Page<JobOffer> listOffersByPage = jobOfferService.getPageOfEntities(pageTool);
			return new ResponseEntity<Page<JobOffer>>(listOffersByPage, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données en paramètre non valides");
		}
	}

	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<JobOffer> getOfferById(@PathVariable("id") Long id) {
		Optional<JobOffer> offerFromDb = jobOfferService.getJobOfferById(id);
		if (offerFromDb.isPresent()) {
			return new ResponseEntity<JobOffer>(offerFromDb.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job offer n'a pas été trouvé avec l'id : " + id);
		}
	}

	@PreAuthorize("hasAnyRole('ENTREPRISE','ORGANISME','USER')")
	@GetMapping(value = "/search")
	public ResponseEntity<Page<JobOffer>> getOffersBySearch(@Valid PageTool pageTool,
			@RequestParam(name = "keyword", required = false) String keyword) {
		if (pageTool != null && keyword != null) {
			Page<JobOffer> listOffersbySearch = jobOfferService.searchJobOfferByKeyword(pageTool.requestPage(),
					keyword);
			return new ResponseEntity<Page<JobOffer>>(listOffersbySearch, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données en paramètre non valides");
		}
	}

}
