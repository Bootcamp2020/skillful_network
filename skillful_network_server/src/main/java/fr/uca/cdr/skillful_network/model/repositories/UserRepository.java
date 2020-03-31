package fr.uca.cdr.skillful_network.model.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uca.cdr.skillful_network.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String mail);

	Optional<User> findById(Long id);

	Page<User> findByLastNameContainsOrFirstNameContainsAllIgnoreCase(Pageable pageable, String keyword1, String keyword2);
}
