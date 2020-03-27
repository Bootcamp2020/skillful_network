package fr.uca.cdr.skillful_network.model.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.uca.cdr.skillful_network.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String mail);

	Optional<User> findById(Long id);

	@Query(value = "select * from User u where u.first_name like %:keyword% or u.last_name like %:keyword%", nativeQuery = true)
	Page<User> findByLastNameOrFirstNameContaining(@Param("keyword") String keyword, Pageable pageable);
}
