package co.edu.uis.traffic.persistence.models;

import co.edu.uis.traffic.persistence.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", indexes = {
         @Index(
                 name = "idx_email",
                 columnList = "email",
                 unique = true
         )
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(length = 400)
    private String email;

    @Column(nullable = false, length = 355)
    private String password;

    @Column(nullable = false, length = 12, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

}
