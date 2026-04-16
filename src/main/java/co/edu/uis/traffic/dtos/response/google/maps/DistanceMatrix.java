package co.edu.uis.traffic.dtos.response.google.maps;

import lombok.Data;

import java.util.List;

@Data
public class DistanceMatrix {

    private List<Row> rows;

}
