package fr.uca.cdr.skillful_network.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long>{

}
