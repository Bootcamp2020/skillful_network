package fr.uca.cdr.skillful_network.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.model.services.QualificationService;


@RestController
@CrossOrigin(origins = "*")
public class QualificationController {
	
	
    private final QualificationService qualificationservice;
	 public QualificationController(QualificationService qualificationservice) {
		 this.qualificationservice = qualificationservice;
	       
	    }
	 
	 @RequestMapping(value = "/qualifications")
	  public List<String> getQualifications(@RequestParam(name = "prefix", required = false) String prefix){
		 if(prefix == null) {
			return qualificationservice.getAllQualifications();
		 }else {
			 return qualificationservice.getQualificationsByPrefix(prefix);
		 }
	
	  }
	 

	 
}
