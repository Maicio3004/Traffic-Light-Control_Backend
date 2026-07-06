package co.edu.uis.traffic.dtos.request;

import co.edu.uis.traffic.persistence.models.enums.Role;
import lombok.Getter;

@Getter
public class RegisterRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Role role;
}
