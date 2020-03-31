package fr.uca.cdr.skillful_network;

import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.QuestionSet;
import fr.uca.cdr.skillful_network.model.repositories.*;
import fr.uca.cdr.skillful_network.tools.json.ExerciseAdapter;
import fr.uca.cdr.skillful_network.tools.json.JSONLoader;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

import fr.uca.cdr.skillful_network.model.entities.Role;
import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.entities.simulation.exercise.Exercise;
import fr.uca.cdr.skillful_network.model.entities.Subscription;

import static fr.uca.cdr.skillful_network.tools.json.JSONLoader.SKILLFUL_NETWORK_SERVER_PATH;

@SpringBootApplication
@EnableAsync
public class Application {

	// lance le serveur
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}


	@Bean
	@Profile("dev")
	ApplicationRunner initUserRepository(UserRepository userRepository) {
		return args -> {
			if (userRepository.findAll().isEmpty()) {
				List<User> users = new JSONLoader<>("src/main/resources/data/users.json", User[].class, userRepository)
						.load();
				users.forEach(user -> user.setValidated(true));
			}
		};
	}
	
	@Bean
	@Profile("dev")
	ApplicationRunner initRoleRepository(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/roles.json", Role[].class, roleRepository).load();				
			}
		};
	}

	@Bean
	@Profile("dev")
	ApplicationRunner initJobOfferRepository(JobOfferRepository jobOfferRepository) {
		return args -> {
			if (jobOfferRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/job-offers.json", JobOffer[].class, jobOfferRepository)
						.load();
			}
		};
	}

	@Bean
	@Profile("dev")
	ApplicationRunner initTrainingRepository(TrainingRepository trainingRepository) {
		return args -> {
			if (trainingRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/trainings.json", Training[].class, trainingRepository).load();
			}
		};
	}

	@Bean
	@Profile("dev")
	ApplicationRunner initSkillRepository(SkillRepository SkillRepository) {
		return args -> {
			if (SkillRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/skills.json", Skill[].class, SkillRepository).load();
			}
		};
	}

	@Bean
	@Profile("dev")
	ApplicationRunner initQualificationRepository(QualificationRepository QualificationRepository) {
		return args -> {
			if (QualificationRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/qualifications.json", Qualification[].class,
						QualificationRepository).load();
			}
		};
	}

	@Bean
	@Profile("dev")
	ApplicationRunner initSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
		return args -> {
			if (subscriptionRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/subscriptions.json", Subscription[].class,
						subscriptionRepository).load();

			}
		};
	}

	@Bean
	@Profile("dev")
	ApplicationRunner initExercises(ExerciseRepository exerciseRepository, QuestionRepository questionRepository) {
		return args -> {
			if (exerciseRepository.findAll().isEmpty()) {
				final String path = "src/main/resources/data/exercises.json";
				final String resourceDir = this.getClass().getResource("/").getPath();
				final String cwdDirectory = resourceDir.substring(0, resourceDir.lastIndexOf(SKILLFUL_NETWORK_SERVER_PATH));
				final String url = cwdDirectory + SKILLFUL_NETWORK_SERVER_PATH + path;
				try (final JsonReader reader = new JsonReader(new FileReader(url))) {
					final Gson gson =  new GsonBuilder().setPrettyPrinting()
							.registerTypeAdapter(Exercise.class, new ExerciseAdapter())
							.create();
					Exercise[] elements = gson.fromJson(reader, Exercise[].class);
					for (Exercise exercise : elements) {
						if (exercise instanceof QuestionSet) {
							((QuestionSet) exercise).getQuestions().removeAll(
									((QuestionSet) exercise).getQuestions()
											.stream()
											.filter(question -> question.getChoices() != null && question.getFeedback() != null)
											.filter(question -> 
//													question.getChoices().length() > 255 ||
															question.getFeedback().length() > 255
											).collect(Collectors.toList())
							);
							((QuestionSet) exercise).setQuestions(((QuestionSet) exercise).getQuestions());
							questionRepository.saveAll(((QuestionSet) exercise).getQuestions());
						}
						exerciseRepository.saveAndFlush(exercise);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
}

