package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.request.LoginRequest;
import co.edu.uis.traffic.dtos.response.LoginResponse;
import co.edu.uis.traffic.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.uis.traffic.dtos.request.RegisterRequest;
import co.edu.uis.traffic.dtos.response.UserResponse;
import co.edu.uis.traffic.persistence.models.enums.Role;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(
                LoginResponse.toResponse(authService.login(loginRequest))
        );
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(authService.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        authService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserResponse> updateRole(@PathVariable Integer id, @RequestBody String role) {
        return ResponseEntity.ok(authService.updateRole(id, Role.valueOf(role.toUpperCase())));
    }


}
