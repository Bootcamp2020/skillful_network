package fr.uca.cdr.skillful_network;

import fr.uca.cdr.skillful_network.model.entities.JobOffer;
import fr.uca.cdr.skillful_network.model.entities.Training;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.JobOfferRepository;
import fr.uca.cdr.skillful_network.model.repositories.TrainingRepository;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    // lance le serveur
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner initUserRepository(UserRepository userRepository) {
        return args -> {
            if (userRepository.findAll().isEmpty()) {
                List<User> users =
                    new JSONLoader<>(
                            "src/main/resources/data/users.json",
                            User[].class,
                            userRepository
                    ).load();
                users.forEach(user -> user.setValidated(true));
            }
        };
    }

    @Bean
    ApplicationRunner initJobOfferRepository(JobOfferRepository jobOfferRepository) {
        return args -> {
            if (jobOfferRepository.findAll().isEmpty()) {
                new JSONLoader<>(
                    "src/main/resources/data/job-offers.json",
                    JobOffer[].class,
                    jobOfferRepository
                ).load();
            }
        };
    }

    @Bean
    ApplicationRunner initTrainingRepository(TrainingRepository trainingRepository) {
        return args -> {
            if (trainingRepository.findAll().isEmpty()) {
                new JSONLoader<>(
                        "src/main/resources/data/trainings.json",
                        Training[].class,
                        trainingRepository
                ).load();
            }
        };
    }
}
