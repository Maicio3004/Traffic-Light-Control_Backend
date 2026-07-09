package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.request.IntersectionRequest;
import co.edu.uis.traffic.dtos.request.RouteRequest;
import co.edu.uis.traffic.dtos.response.IntersectionResponse;
import co.edu.uis.traffic.exceptions.EntityNotFound;
import co.edu.uis.traffic.mappers.IntersectionMapper;
import co.edu.uis.traffic.persistence.models.Intersection;
import co.edu.uis.traffic.persistence.models.Route;
import co.edu.uis.traffic.persistence.repositories.IntersectionRepository;
import co.edu.uis.traffic.persistence.repositories.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService implements CrudService<Route> {

    private final RouteRepository routeRepository;
    private final IntersectionRepository intersectionRepository;

    public Route create(RouteRequest routeRequest) {

        Route route = new Route();
        route.setName(routeRequest.getName());
        route.setInitCoordinate(routeRequest.getCoordinate());

        return create(route);
    }

    public List<IntersectionResponse> saveIntersections(
            List<IntersectionRequest> request
    ) {

        List<IntersectionResponse> intersectionResponses = new ArrayList<>();

        request.forEach(inter -> {
            Intersection intersection =  intersectionRepository.save(
                    IntersectionMapper.toEntity(inter, findById(inter.getRouteId()))
            );
            intersectionResponses.add(IntersectionResponse.fromEntity(intersection));
        });
        return intersectionResponses;
    }

    @Override
    public Route create(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public void delete(Route route) {

    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route findById(Number id) {
        return routeRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFound("Route with id " + id + " not found"));
    }

    public void deleteRoute(Integer id) {
        routeRepository.deleteById(id);
    }

    public void deleteIntersection(String code) {
        Intersection intersection = intersectionRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFound("Intersection not found: " + code));
        intersectionRepository.delete(intersection);
    }
}
