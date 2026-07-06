package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.User;
import co.edu.uis.traffic.persistence.models.enums.Role;

public record UserResponse(
        Integer id,
        String name,
        String lastName,
        String email,
        String phone,
        Role role
) {
    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );
    }
}
