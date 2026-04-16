package co.edu.uis.traffic.persistence.models.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public class Coordinate {

    private Float latitude;
    private Float longitude;

    @Override
    public String toString(){
        return latitude + ", " + longitude;
    }

}
