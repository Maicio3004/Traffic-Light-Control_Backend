package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.Intersection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntersectionResponse {

    private String code;
    private String location;
    private Float latitude;
    private Float longitude;

    public static IntersectionResponse fromEntity(Intersection intersection) {
        return new IntersectionResponse(
                intersection.getCode(),
                intersection.getLocation(),
                intersection.getCoordinate() != null ? intersection.getCoordinate().getLatitude() : null,
                intersection.getCoordinate() != null ? intersection.getCoordinate().getLongitude() : null
        );
    }
}
