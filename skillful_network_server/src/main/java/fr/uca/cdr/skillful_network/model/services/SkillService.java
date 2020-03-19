package fr.uca.cdr.skillful_network.model.services;

import java.util.*;


import fr.uca.cdr.skillful_network.model.entities.Skill;

public interface SkillService {
	
	Optional<List<String>> search(String keyword);
	Optional<Skill> getByName(String name);
	Optional<Skill> getById(Long id);
	List<Skill> getAll();

}
