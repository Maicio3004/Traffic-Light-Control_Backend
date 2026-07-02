package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.response.IntersectionResponse;
import co.edu.uis.traffic.services.TrafficService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntersectionController {

    private final TrafficService trafficService;

    @GetMapping("/intersections")
    public ResponseEntity<List<IntersectionResponse>> getIntersections() {
        return ResponseEntity.ok(trafficService.getAllIntersections());
    }
}
