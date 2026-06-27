package co.edu.uis.traffic.persistence.repositories;

import co.edu.uis.traffic.persistence.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> findByUsernameAndPassword(String username, String password);

}
