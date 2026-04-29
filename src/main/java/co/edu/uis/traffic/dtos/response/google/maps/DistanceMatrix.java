package co.edu.uis.traffic.dtos.response.google.maps;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DistanceMatrix {

    private List<Row> rows;
    private String status;
    private LocalDateTime timestamp;

    public DistanceMatrix() {
        this.timestamp = LocalDateTime.now();
    }

    public double getCongestion() {
        float duration = duration();
        float durationInTraffic = durationInTraffic();

        return (double) (durationInTraffic / duration);
    }

    public int duration() {
        return rows.getFirst().getElements().getFirst().getDuration().getValue();
    }

    public int durationInTraffic() {
        return rows.getLast().getElements().getFirst().getDuration_in_traffic().getValue();
    }

}
