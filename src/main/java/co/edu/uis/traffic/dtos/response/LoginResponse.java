package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.User;

public record LoginResponse(
        String name,
        String email,
        String role
) {
    public static LoginResponse toResponse(User user) {
        return new LoginResponse(
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
