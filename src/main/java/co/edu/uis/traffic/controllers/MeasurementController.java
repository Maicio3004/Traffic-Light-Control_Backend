package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.response.MeasurementResponse;
import co.edu.uis.traffic.services.TrafficService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeasurementController {

    private final TrafficService trafficService;

    @GetMapping("/measurements")
    public ResponseEntity<List<MeasurementResponse>> getMeasurements() {
        return ResponseEntity.ok(trafficService.findAllMeasurements());
    }
}
