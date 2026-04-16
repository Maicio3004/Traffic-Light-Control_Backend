package co.edu.uis.traffic.persistence.models;

import co.edu.uis.traffic.persistence.models.enums.Direction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficLight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "intersection_id")
    private Intersection intersection;

}
