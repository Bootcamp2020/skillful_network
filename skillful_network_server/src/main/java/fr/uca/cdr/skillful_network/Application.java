package fr.uca.cdr.skillful_network;

import java.util.List;

import fr.uca.cdr.skillful_network.tools.json.JSONLoader;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Qualification;
import fr.uca.cdr.skillful_network.model.entities.Skill;
import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.repositories.SubscriptionRepository;
import fr.uca.cdr.skillful_network.model.repositories.JobOfferRepository;
import fr.uca.cdr.skillful_network.model.repositories.QualificationRepository;
import fr.uca.cdr.skillful_network.model.repositories.SkillRepository;
import fr.uca.cdr.skillful_network.model.repositories.TrainingRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;

@SpringBootApplication
@EnableAsync
public class Application {

	// lance le serveur
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);

	}
}

	/*@Bean
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
	ApplicationRunner initJobOfferRepository(JobOfferRepository jobOfferRepository) {
		return args -> {
			if (jobOfferRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/job-offers.json", JobOffer[].class, jobOfferRepository)
						.load();
			}
		};
	}

	@Bean
	ApplicationRunner initTrainingRepository(TrainingRepository trainingRepository) {
		return args -> {
			if (trainingRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/trainings.json", Training[].class, trainingRepository).load();
			}
		};
	}

	@Bean
	ApplicationRunner initSkillRepository(SkillRepository SkillRepository) {
		return args -> {
			if (SkillRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/skills.json", Skill[].class, SkillRepository).load();
			}
		};
	}

	@Bean
	ApplicationRunner initQualificationRepository(QualificationRepository QualificationRepository) {
		return args -> {
			if (QualificationRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/qualifications.json", Qualification[].class,
						QualificationRepository).load();
			}
		};
	}

	@Bean
	ApplicationRunner initSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
		return args -> {
			if (subscriptionRepository.findAll().isEmpty()) {
				new JSONLoader<>("src/main/resources/data/subscriptions.json", Subscription[].class,
						subscriptionRepository).load();

			}
		};
	}

	@Bean
	ApplicationRunner initExercises(ExerciseRepository exerciseRepository) {
		return args -> {
			if (exerciseRepository.findAll().isEmpty()) {
				new JSONLoader<>(
						"src/main/resources/data/exercises.json",
						Exercise[].class,
						Exercise.class,
						exerciseRepository,						new ExerciseAdapter()
						).load();
			}
		};
	}

}*/
