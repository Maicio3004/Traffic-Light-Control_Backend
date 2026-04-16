package co.edu.uis.traffic.persistence.repositories;

import co.edu.uis.traffic.persistence.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
}
