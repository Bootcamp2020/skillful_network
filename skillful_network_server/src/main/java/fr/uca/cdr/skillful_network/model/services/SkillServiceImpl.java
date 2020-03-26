package fr.uca.cdr.skillful_network.model.services;


import java.util.*;

import fr.uca.cdr.skillful_network.tools.AutoCompletion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.repositories.SkillRepository;

@Service(value = "skillService")
public class SkillServiceImpl implements SkillService{
	
	@Autowired
	private SkillRepository skillRepository;

	// Autocompletion init
	AutoCompletion<Skill> completor = new AutoCompletion<>(Skill.class, "name", "userList");

	@Override
	public Optional<List<String>> searchSkill(String keyword) {
		
		return this.skillRepository.search(keyword);
	}

	@Override
	public Optional<Skill> getSkillByName(String name) {
		// TODO Auto-generated method stub
		return this.skillRepository.findByName(name);
	}

	@Override
	public Optional<Skill> getSkillById(Long id) {
		// TODO Auto-generated method stub
		return this.skillRepository.findById(id);
	}

	@Override
	public List<Skill> getAllSkills() {
		// TODO Auto-generated method stub
		return this.skillRepository.findAll();
	}

	@Override
	public List<Skill> getSkillsByMatch(String match) {
		return completor.findCandidates(skillRepository.findAll(), match);
	}
}
