package fr.uca.cdr.skillful_network;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.JobOfferRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    // lance le serveur
    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        
        //Test instanciation Joboffer
        ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("kw1");
		keywords.add("kw2");
		keywords.add("kw3");
		JobOffer j1 = new JobOffer("nom","entreprise","description","type",new Date("2019/05/01"),new Date("2020/12/25"),new Date("2020/01/30"),keywords);
		System.out.println(j1.toString());
		//Fin du test
    }

    @Bean
    CommandLineRunner initUserRepository(UserRepository userRepository) {
        // Ici on initialise le dÃ©pÃ´t des utilisateurs avec des utilisateurs codÃ©s en dur.
        // on devrait ici charger la base de données (fichiers json dans une premiÃ¨re version)
        return args -> {
            for (int i = 0; i < 5; i++) {
                userRepository.save(new User());
            }
            userRepository.findAll().forEach(System.out::println);
        };
    }
    
    @Bean
    CommandLineRunner initJobOfferRepository(JobOfferRepository jobOfferRepository) {
        // Ici on initialise le dépot des JobOffer avec des JobOffer codés en dur.
        // on devrait ici charger la base de données (fichiers json dans une première version)
        return args -> {
            for (int i = 0; i < 5; i++) {
            	jobOfferRepository.save(new JobOffer());
            }
            jobOfferRepository.findAll().forEach(System.out::println);
        };
    }
}
