package co.edu.uis.traffic.persistence.models;

import co.edu.uis.traffic.persistence.models.enums.Mode;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_operation")
    private Mode modeOperation;

    @Column(nullable = false, length = 355)
    private String description;

    @OneToMany(mappedBy = "mode")
    private List<Schedule> schedules;
}
