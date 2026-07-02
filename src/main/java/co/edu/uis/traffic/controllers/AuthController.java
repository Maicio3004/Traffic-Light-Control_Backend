package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.request.LoginRequest;
import co.edu.uis.traffic.dtos.response.LoginResponse;
import co.edu.uis.traffic.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
