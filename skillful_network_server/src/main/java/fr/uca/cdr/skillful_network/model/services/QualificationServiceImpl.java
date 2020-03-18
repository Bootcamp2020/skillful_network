package fr.uca.cdr.skillful_network.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service(value = "QualificationService")
public class QualificationServiceImpl implements QualificationService{

	
	@Override
	public List<String> getAllQualifications(){
	   List<String> qualifications = new ArrayList<>();   
	   qualifications.add("Bac");
	   qualifications.add("DUT");
	   return qualifications;      
	}
	
	@Override
	 public List<String> getQualificationsByPrefix(String prefix){
		 List<String> qualifications = new ArrayList<>();   
		 qualifications.add("Bac");
		 qualifications.add("DUT");
		 return qualifications;
	}
	
	
}
