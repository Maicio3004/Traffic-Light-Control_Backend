package co.edu.uis.traffic.dtos.request;

import co.edu.uis.traffic.persistence.models.embeddable.Coordinate;
import co.edu.uis.traffic.persistence.models.enums.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteRequest {

    private String name;
    private Coordinate coordinate;
    private Location location;

}
