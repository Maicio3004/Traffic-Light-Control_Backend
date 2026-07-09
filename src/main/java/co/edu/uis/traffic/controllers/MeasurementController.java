package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.response.MeasurementResponse;
import co.edu.uis.traffic.dtos.response.TransactionResponse;
import co.edu.uis.traffic.services.TrafficService;
import co.edu.uis.traffic.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeasurementController {

    private final TrafficService trafficService;
    private final TransactionService transactionService;

    @GetMapping("/measurements")
    public ResponseEntity<List<MeasurementResponse>> getMeasurements() {
        return ResponseEntity.ok(trafficService.findAllMeasurements());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions() {
        return ResponseEntity.ok(transactionService.getAll());
    }
}
