package co.edu.uis.traffic.persistence.repositories;

import co.edu.uis.traffic.persistence.models.OperationMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationMode, Integer> {

}
