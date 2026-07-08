package co.edu.uis.traffic.mappers;

import co.edu.uis.traffic.dtos.request.IntersectionRequest;
import co.edu.uis.traffic.persistence.models.Intersection;
import co.edu.uis.traffic.persistence.models.Route;

public class IntersectionMapper {

    public static Intersection toEntity(IntersectionRequest request, Route route){

        Intersection intersection = new Intersection();
        intersection.setCode(request.getCode());
        intersection.setCoordinate(request.getCoordinate());
        intersection.setPosition(request.getPosition());
        intersection.setRoute(route);
        intersection.setLocation(request.getLocation());

        return intersection;
    }

}
