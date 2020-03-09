package fr.uca.cdr.skillful_network;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
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
    CommandLineRunner initUserRepository(UserRepository userRepository) {
        // Ici on initialise le dépôt des utilisateurs avec des utilisateurs codés en dur.
        // on devrait ici charger la base de données (fichiers json dans une première version)
        return args -> {
            for (int i = 0; i < 5; i++) {
                userRepository.save(new User());
            }
            userRepository.findAll().forEach(System.out::println);
        };
    }
}
