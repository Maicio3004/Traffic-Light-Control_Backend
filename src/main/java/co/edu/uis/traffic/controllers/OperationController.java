package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.request.IntersectionRequest;
import co.edu.uis.traffic.dtos.request.OperationRequest;
import co.edu.uis.traffic.dtos.request.RouteRequest;
import co.edu.uis.traffic.dtos.request.ScheduleRequest;
import co.edu.uis.traffic.dtos.response.IntersectionResponse;
import co.edu.uis.traffic.dtos.response.RouteResponse;
import co.edu.uis.traffic.dtos.response.ScheduleResponse;
import co.edu.uis.traffic.persistence.models.OperationMode;
import co.edu.uis.traffic.persistence.models.Route;
import co.edu.uis.traffic.services.OperationService;
import co.edu.uis.traffic.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;
    private final RouteService routeService;

    @PostMapping("/create/mode")
    public ResponseEntity<Void> createMode(@RequestBody OperationRequest request) {
        operationService.createMode(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/operation/get")
    public ResponseEntity<List<OperationMode>> getAll() {
        return ResponseEntity.ok(operationService.findAll());
    }

    @PostMapping("create/schedule")
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleRequest request) {
        return ResponseEntity.created(URI.create("/config/schedule")).body(operationService.createSchedule(request));
    }
    @GetMapping("/schedule/get")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        return ResponseEntity.ok(operationService.findAllSchedules());
    }
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) {
        operationService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("create/route")
    public ResponseEntity<RouteResponse> createRoute(@RequestBody RouteRequest request) {
        return ResponseEntity.created(URI.create("/config/create/route"))
                .body(RouteResponse.toResponse(routeService.create(request)));
    }

    @PostMapping("/create/intersection")
    public ResponseEntity<List<IntersectionResponse>> createIntersections(
            @RequestBody List<IntersectionRequest> intersectionRequests
    ){
        return ResponseEntity.created(URI.create("/config/create/intersections"))
                .body(routeService.saveIntersections(intersectionRequests));
    }

}
