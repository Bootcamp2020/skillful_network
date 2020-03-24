package fr.uca.cdr.skillful_network.model.services;

import java.util.*;


import fr.uca.cdr.skillful_network.model.entities.Skill;

public interface SkillService {
	
	Optional<List<String>> searchSkill(String keyword);
	Optional<Skill> getSkillByName(String name);
	Optional<Skill> getSkillById(Long id);
	List<Skill> getAllSkills();

}
