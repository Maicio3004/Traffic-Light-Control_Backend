package co.edu.uis.traffic.dtos.response.google.maps;

import lombok.Data;

import java.util.List;

@Data
public class DistanceMatrix {

    private List<Row> rows;

    public double getCongestion() {
        float duration = rows.getFirst().getElements().getFirst().getDuration().getValue();
        float durationInTraffic = rows.getLast().getElements().getFirst().getDuration_in_traffic().getValue();

        return (double) (durationInTraffic / duration);
    }


}
