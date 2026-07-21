package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.Route;
import co.edu.uis.traffic.persistence.models.embeddable.Coordinate;

import java.util.List;

public record RouteResponse(
        Integer id,
        String name,
        Coordinate coordinate,
        String location
) {
    public static RouteResponse toResponse(Route route) {
        return new RouteResponse(
                route.getId(),
                route.getName(),
                route.getInitCoordinate(),
                route.getLocation().name()
        );
    }
}
