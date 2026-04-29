package co.edu.uis.traffic.persistence.repositories;

import co.edu.uis.traffic.persistence.models.TrafficMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficMeasurementRepository extends JpaRepository<TrafficMeasurement, Long> {
}
