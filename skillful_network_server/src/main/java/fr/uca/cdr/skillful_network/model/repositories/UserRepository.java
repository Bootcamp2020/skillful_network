package fr.uca.cdr.skillful_network.model.repositories;

import fr.uca.cdr.skillful_network.model.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
      Optional<User> findByEmail(String mail);
      Optional<User> findById(Long id);
}
