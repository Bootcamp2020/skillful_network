package fr.uca.cdr.skillful_network.controller;
import fr.uca.cdr.skillful_network.model.services.JobOfferService;
import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin("*")
@RestController
@RequestMapping("/joboffer")
public class JobOfferControler {


    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping(value = "/getOne/{id}")
    public ResponseEntity<JobOffer> getAllJobOfferById(@PathVariable(value = "id") Long id) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune offre trouvé avec l'id : " + id));
        return new ResponseEntity<JobOffer>(jobOffer, HttpStatus.OK);
    }

}
