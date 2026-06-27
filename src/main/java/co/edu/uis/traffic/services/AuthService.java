package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.request.LoginRequest;
import co.edu.uis.traffic.exceptions.EntityNotFound;
import co.edu.uis.traffic.persistence.models.User;
import co.edu.uis.traffic.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User login(LoginRequest request) {
        return userRepository.findByUsernameAndPassword(
                request.getUsername(), request.getPassword()
        ).orElseThrow(() -> new EntityNotFound("User not found"));
    }

}
