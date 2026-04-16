package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.dtos.request.ActivationRequest;
import co.edu.uis.traffic.services.TrafficService;
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
    private final MqttPublish mqttPublisher;

    @GetMapping("/get/traffic")
    public ResponseEntity<?> getTraffic() {
        trafficService.verifyCongestion();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test/color")
    public ResponseEntity<String> testESP32(@RequestBody ActivationRequest request) {
        mqttPublisher.test(request);
        return ResponseEntity.ok("Mensaje enviado");
    }

}
