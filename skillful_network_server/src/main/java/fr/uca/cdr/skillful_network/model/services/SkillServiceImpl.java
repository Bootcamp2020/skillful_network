package fr.uca.cdr.skillful_network.model.services;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.repositories.SkillRepository;

@Service(value = "skillService")
public class SkillServiceImpl implements SkillService{
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Override
	public Optional<List<String>> search(String keyword) {
		
		return this.skillRepository.search(keyword);
	}

	@Override
	public Optional<Skill> getByName(String name) {
		// TODO Auto-generated method stub
		return this.skillRepository.findByName(name);
	}

	@Override
	public Optional<Skill> getById(Long id) {
		// TODO Auto-generated method stub
		return this.skillRepository.findById(id);
	}

	@Override
	public List<Skill> getAll() {
		// TODO Auto-generated method stub
		return this.skillRepository.findAll();
	}

}
