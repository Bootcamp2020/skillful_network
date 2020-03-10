package fr.uca.cdr.skillful_network;

import java.util.ArrayList;
import java.util.Date;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("kw1");
		keywords.add("kw2");
		keywords.add("kw3");
		
		JobOffer j1 = new JobOffer("nom","entreprise","description","type",new Date("2019/05/01"),new Date("2020/12/25"),new Date("2020/01/30"),keywords);

		System.out.println(j1.toString());
	}

}
