package co.edu.uis.traffic.persistence.models.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Coordinate {

    private Float latitude;
    private Float longitude;

    public Coordinate() {}

    public Coordinate(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }
}