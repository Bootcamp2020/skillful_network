package fr.uca.cdr.skillful_network.model.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uca.cdr.skillful_network.model.entities.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{
	
//	Méthodes dont nous aurons besoin :
	
	Skill findByName(String name);
}
