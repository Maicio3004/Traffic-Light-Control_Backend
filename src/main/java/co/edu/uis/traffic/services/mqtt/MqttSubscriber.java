package co.edu.uis.traffic.services.mqtt;

import co.edu.uis.traffic.dtos.request.ActivationRequest;
import co.edu.uis.traffic.services.SseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqttSubscriber {

    private final ObjectMapper mapper;
    private final SseService sseService;

    @ServiceActivator(inputChannel = "inboundChannel")
    public void subscribe(Message<?> message) {

        ActivationRequest request;

        Object payload = message.getPayload();

        if(payload instanceof String json) {
            try {
                request = mapper.readValue(json, ActivationRequest.class);
                sseService.sendEvent("semaforo-color", request);
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(payload);
            System.out.println("No es String.\nNo se envía mensaje");
        }


    }

}
