package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.request.LoginRequest;
import co.edu.uis.traffic.exceptions.EntityNotFound;
import co.edu.uis.traffic.persistence.models.User;
import co.edu.uis.traffic.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.uis.traffic.dtos.request.RegisterRequest;
import co.edu.uis.traffic.dtos.response.UserResponse;
import co.edu.uis.traffic.persistence.models.enums.Role;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;

    public User login(LoginRequest request) {
       return repository.findByEmailAndPassword(
               request.getEmail(), request.getPassword()
       ).orElseThrow(()-> new EntityNotFound("User not found") );
    }
    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(UserResponse::toResponse)
                .toList();
    }

    public UserResponse register(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        return UserResponse.toResponse(repository.save(user));
    }

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }

    public UserResponse updateRole(Integer id, Role role) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("User not found: " + id));
        user.setRole(role);
        return UserResponse.toResponse(repository.save(user));
    }

}
