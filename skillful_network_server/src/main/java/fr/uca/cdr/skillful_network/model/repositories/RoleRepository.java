package fr.uca.cdr.skillful_network.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uca.cdr.skillful_network.model.entities.Role;
import fr.uca.cdr.skillful_network.model.entities.Rolename;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role,Long> {
	Optional<Role> findByName(Rolename roleName);
}
