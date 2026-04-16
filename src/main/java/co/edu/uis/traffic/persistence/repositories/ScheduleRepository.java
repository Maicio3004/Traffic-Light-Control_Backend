package co.edu.uis.traffic.persistence.repositories;

import co.edu.uis.traffic.persistence.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
