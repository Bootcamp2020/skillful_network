package fr.uca.cdr.skillful_network.model.entities;

import java.util.HashMap;
import java.util.Map;

import com.sun.istack.Nullable;

public enum Status {
	CHERCHEUR_EMPLOI(1, "Chercheur d'emploi"),
	ETUDIANT(2, "Etudiant");
	
	private int id;
	private String description;

	
	private Status(int id, String description) {
		this.id=id;
		this.description= description;
	}
	

	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	@Nullable
	  public static String fromId(int id) {
	    for (Status st : Status.values()) {
	      if (st.getId()== id) {
	        return st.description;
	      }
	    }
	    return null;
	  }

	 public static int getId(String description) {
	     return Status.valueOf(description).getId();
	  }
}
