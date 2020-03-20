package fr.uca.cdr.skillful_network.model.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.entities.Skill;


@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long>{
	
//	Méthode pour chercher une qualification en utilisant le prefix(auto-complete)
	
	@Query(value="select * from qualification where name like %:prefix%", nativeQuery = true)
	List<Qualification> search(@Param("prefix")String prefix);
}
