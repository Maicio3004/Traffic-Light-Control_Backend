package co.edu.uis.traffic.persistence.models;

import co.edu.uis.traffic.persistence.models.embeddable.Coordinate;
import co.edu.uis.traffic.persistence.models.enums.Location;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String name;

    @Embedded
    @Column(nullable=false, name = "init_coordinates")
    private Coordinate initCoordinate;

    @Enumerated(EnumType.STRING)
    private Location location;

    @OneToMany(mappedBy = "route")
    private List<Intersection> intersections;

}
