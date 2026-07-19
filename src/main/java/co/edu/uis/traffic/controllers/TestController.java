package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.request.device.ColorRequest;
import co.edu.uis.traffic.dtos.response.events.StatusIntersectionEvent;
import co.edu.uis.traffic.services.RouteService;
import co.edu.uis.traffic.services.ScheduleService;
import co.edu.uis.traffic.services.TrafficService;
import co.edu.uis.traffic.services.TransactionService;
import co.edu.uis.traffic.services.mqtt.MqttPublish;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TrafficService trafficService;
    private final ScheduleService scheduleService;
    private final MqttPublish mqttPublisher;
    private final RouteService routeService;
    private final TransactionService transactionService;

    @PostMapping("create/transaction")
    public ResponseEntity<?> createTransaction() {
        transactionService.create(
                routeService.findById(1)
                        .getIntersections().getFirst()
        );
        ResponseEntity.ok().build();
        return null;
    }

    @GetMapping("/get/traffic")
    public ResponseEntity<?> getTraffic() {
        scheduleService.scheduler();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test/color")
    public ResponseEntity<String> testESP32(@RequestBody ColorRequest request) {
        mqttPublisher.test(request);
        return ResponseEntity.ok("Mensaje enviado");
    }

    @PostMapping("/test/status")
    public ResponseEntity<String> testStatusESP32(@RequestBody StatusIntersectionEvent request) {
        mqttPublisher.test(request);
        return ResponseEntity.ok("Mensaje enviado");
    }

}
