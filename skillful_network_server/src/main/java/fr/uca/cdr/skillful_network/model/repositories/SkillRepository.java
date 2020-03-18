package fr.uca.cdr.skillful_network.model.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.uca.cdr.skillful_network.model.entities.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{
	
//	Méthodes dont nous aurons besoin :
	
//	Méthode pour l'auto-complétion
	@Query(value="select name from skills where name like %:keyword%", nativeQuery = true)
	Optional<List<String>> search(@Param("keyword")String keyword);
	
//	Méthode pour chercher une compétence par son nom
	Optional<Skill> findByName(String name);
}
