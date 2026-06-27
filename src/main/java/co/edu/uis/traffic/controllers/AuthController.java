package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.request.LoginRequest;
import co.edu.uis.traffic.dtos.response.LoginResponse;
import co.edu.uis.traffic.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok().body(
                LoginResponse.toResponse(authService.login(loginRequest))
        );
    }


}
