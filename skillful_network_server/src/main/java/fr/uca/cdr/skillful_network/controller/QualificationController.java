package fr.uca.cdr.skillful_network.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class QualificationController {
	 public QualificationController() {
	       
	    }
	 @RequestMapping(value = "/qualifications")
	  public List<String> getQualifications(){
	     List<String> qualifications = new ArrayList<>();   
	     qualifications.add("Bac");
	     qualifications.add("DUT");
		 return qualifications;
	  }

}
