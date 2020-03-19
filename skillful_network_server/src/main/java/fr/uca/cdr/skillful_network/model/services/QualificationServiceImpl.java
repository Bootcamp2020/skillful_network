package fr.uca.cdr.skillful_network.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.repositories.QualificationRepository;
import fr.uca.cdr.skillful_network.model.repositories.SkillRepository;

@Service(value = "QualificationService")
public class QualificationServiceImpl implements QualificationService{
	@Autowired
	private QualificationRepository qualificationrepository;

	
	@Override
	public List<Qualification> getAllQualifications(){
	  return qualificationrepository.findAll();    
	}
	
	@Override
	public List<Qualification> getQualificationByPrefix(String prefix) {
		
		return qualificationrepository.search(prefix);
	}

	
	
}
