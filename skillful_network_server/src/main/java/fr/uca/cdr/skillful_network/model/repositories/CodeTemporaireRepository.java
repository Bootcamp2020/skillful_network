package fr.uca.cdr.skillful_network.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uca.cdr.skillful_network.model.entities.CodeTemporaire;



public interface CodeTemporaireRepository extends JpaRepository<CodeTemporaire, Long> {

//	Méthode pour chercher mot de passe temporaire par ses caractères entrés en paramètres
	CodeTemporaire findByCodeTempo(String codeTempo);
}
