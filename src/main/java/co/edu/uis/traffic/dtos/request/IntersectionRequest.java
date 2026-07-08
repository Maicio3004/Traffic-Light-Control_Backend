package co.edu.uis.traffic.dtos.request;

import co.edu.uis.traffic.persistence.models.embeddable.Coordinate;
import lombok.Getter;

@Getter
public class IntersectionRequest {

    private String location;
    private String code;
    private Integer position;
    private Coordinate coordinate;
    private Integer routeId;

}
