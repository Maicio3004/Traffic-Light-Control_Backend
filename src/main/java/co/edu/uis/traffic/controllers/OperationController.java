package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.request.OperationRequest;
import co.edu.uis.traffic.dtos.request.ScheduleRequest;
import co.edu.uis.traffic.dtos.response.ScheduleResponse;
import co.edu.uis.traffic.persistence.models.OperationMode;
import co.edu.uis.traffic.services.OperationService;
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

}
