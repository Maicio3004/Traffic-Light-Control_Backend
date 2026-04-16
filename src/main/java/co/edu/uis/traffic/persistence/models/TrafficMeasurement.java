package co.edu.uis.traffic.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "average_time")
    private Integer averageTime;
    @Column(name = "current_time_app")
    private Integer currentTime;
    @Column(name = "congestion_level")
    private Float congestionLevel;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "code_response")
    private String codeResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intersection_id", nullable = false)
    private Intersection intersection;

}
