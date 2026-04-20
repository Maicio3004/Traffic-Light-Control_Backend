package co.edu.uis.traffic.persistence.repositories;

import co.edu.uis.traffic.persistence.models.Intersection;
import co.edu.uis.traffic.persistence.models.Route;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntersectionRepository extends JpaRepository<Intersection, Integer> {


    List<Intersection> findByRoute(Route route, Sort sort);

}
