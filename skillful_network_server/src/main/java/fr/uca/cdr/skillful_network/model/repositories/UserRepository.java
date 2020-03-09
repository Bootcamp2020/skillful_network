package fr.uca.cdr.skillful_network.model.repositories;


import fr.uca.cdr.skillful_network.model.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
