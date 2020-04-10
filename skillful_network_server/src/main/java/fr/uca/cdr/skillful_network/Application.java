package fr.uca.cdr.skillful_network;


import fr.uca.cdr.skillful_network.tools.json.ExerciseAdapter;
import fr.uca.cdr.skillful_network.tools.json.JSONLoader;
import fr.uca.cdr.skillful_network.tools.json.UserAdapter;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

import fr.uca.cdr.skillful_network.model.entities.Role;
import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exercise;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Keyword;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Choice;
import fr.uca.cdr.skillful_network.model.repositories.ChoiceRepository;
import fr.uca.cdr.skillful_network.model.repositories.ExerciseRepository;
import fr.uca.cdr.skillful_network.model.repositories.JobOfferRepository;
import fr.uca.cdr.skillful_network.model.repositories.KeywordRepository;
import fr.uca.cdr.skillful_network.model.repositories.RoleRepository;
import fr.uca.cdr.skillful_network.model.repositories.TrainingRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;

@SpringBootApplication
@EnableAsync
public class Application {

	

	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	@Bean
	@Profile({"dev", "test"})
	ApplicationRunner initChoiceRepository(ChoiceRepository choiceRepository) {
		return args -> {
			if (choiceRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/choices.json", Choice[].class, choiceRepository).load();
			}
		};
	}
	@Bean
	@Profile({"dev", "test"})
	ApplicationRunner initKeywordRepository(KeywordRepository keywordRepository) {
		return args -> {
			if (keywordRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/keywords.json", Keyword[].class, keywordRepository).load();
			}
		};
	}
	
	@Bean
	@Profile({"dev", "test"})
	ApplicationRunner initJobOfferRepository(JobOfferRepository jobOfferRepository) {
		return args -> {
			if (jobOfferRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/job-offers.json", JobOffer[].class, jobOfferRepository)
						.load();
			}
		};
	}

	@Bean
	@Profile({"dev", "test"})
	ApplicationRunner initRoleRepository(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/roles.json", Role[].class, roleRepository).load();
			}
		};
	}

	@Bean
	@Profile({"dev", "test"})
	ApplicationRunner initUserRepository(UserRepository userRepository) {
		return args -> {
			if (userRepository.findAll().isEmpty()) {
				new JSONLoader<>(
						"src/main/resources/data/users.json", 
						User[].class,
						User.class,
						userRepository,
						new UserAdapter()
						).load();
				
			}
		};
	}

	@Bean
	@Profile({"dev", "test"})
	ApplicationRunner initTrainingRepository(TrainingRepository trainingRepository) {
		return args -> {
			if (trainingRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/trainings.json", Training[].class, trainingRepository).load();
			}
		};
	}


	
	
	
	@Bean
	@Profile({"dev", "test"})
	ApplicationRunner initExercises(ExerciseRepository exerciseRepository) {
		return args -> {
			if (exerciseRepository.findAll().isEmpty()) {
				new JSONLoader<>(
						"src/main/resources/data/exercises.json",
						Exercise[].class,
						Exercise.class,
						exerciseRepository,
						new ExerciseAdapter()
						).load();
			}
		};
	}

}

