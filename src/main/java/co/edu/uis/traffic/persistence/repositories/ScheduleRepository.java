package co.edu.uis.traffic.persistence.repositories;

import co.edu.uis.traffic.persistence.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("""
        SELECT s FROM Schedule s
        WHERE s.startTime <= :now  AND s.endTime > :now
    """)
    List<Schedule> findCurrentSchedule(@Param("now") LocalTime now);

}
