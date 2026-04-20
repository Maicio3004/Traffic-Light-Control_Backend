package co.edu.uis.traffic.dtos.response.google.maps;

import lombok.Data;

import java.util.List;

@Data
public class DistanceMatrix {

    private List<Row> rows;

    public float getCongestion() {
        float duration = rows.getFirst().getElements().getFirst().getDuration().getValue();
        float durationInTraffic = rows.getLast().getElements().getLast().getDuration().getValue();

        return (durationInTraffic / duration);
    }
}
