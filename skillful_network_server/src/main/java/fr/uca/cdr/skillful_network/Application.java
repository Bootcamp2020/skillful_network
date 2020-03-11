package fr.uca.cdr.skillful_network;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.JobOfferRepository;
import fr.uca.cdr.skillful_network.model.repositories.TrainingRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;



@SpringBootApplication
public class Application {

    // lance le serveur
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initUserRepository(UserRepository userRepository) {
        // Ici on initialise le dépôt des utilisateurs avec des utilisateurs codés en dur.
        // on devrait ici charger la base de donn�es (fichiers json dans une première version)
        return args -> {
            for (int i = 0; i < 5; i++) {
                userRepository.save(new User());
            }
            userRepository.findAll().forEach(System.out::println);
        };
    }
    
    @Bean
    CommandLineRunner initJobOfferRepository(JobOfferRepository jobOfferRepository) {
        return args -> {
        	Gson gson = new Gson();
        	String url = "src/main/resources/data/job-offers.json";
        	JsonReader reader = new JsonReader(new FileReader(url));
        	
        	List<JobOffer> jobOffers = Arrays.asList(gson.fromJson(reader, JobOffer[].class));
			jobOfferRepository.saveAll(jobOffers);
			reader.close();
			
        	jobOfferRepository.findAll().forEach(System.out::println);
        };
    }
        
    @Bean
    CommandLineRunner initTrainingRepository(TrainingRepository trainingRepository) {
        return args -> {
        	Gson gson = new Gson();
        	String url = "src/main/resources/data/trainings.json";
        	JsonReader reader = new JsonReader(new FileReader(url));
        	
        	List<Training> trainings = Arrays.asList(gson.fromJson(reader, Training[].class));
			trainingRepository.saveAll(trainings);
			reader.close();
			
        	trainingRepository.findAll().forEach(System.out::println);
        	
        };
    }
}
