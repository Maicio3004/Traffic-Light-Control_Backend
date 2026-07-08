package co.edu.uis.traffic.services.mqtt;

import co.edu.uis.traffic.dtos.response.events.ColorTrafficEvent;
import co.edu.uis.traffic.dtos.response.events.StatusIntersectionEvent;
import co.edu.uis.traffic.services.SseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqttSubscriber {

    private static final String TRAFFIC_LIGHT_COLOR = "traffic-light-color";
    private static final String INTERSECTION_STATUS = "intersection-status";
    private static final Logger log = LoggerFactory.getLogger(MqttSubscriber.class);

    @Value("${mqtt.topics.inbound-color-topic}")
    private String INBOUND_COLOR_TOPIC;

    @Value("${mqtt.topics.inbound-status-topic}")
    private String INBOUND_STATUS_TOPIC;

    @Value("${mqtt.topics.inbound-return-date-topic}")
    private String INBOUND_RETURN_DATE_TOPIC;

    private final ObjectMapper mapper;
    private final SseService sseService;

    @ServiceActivator(inputChannel = "inboundChannel")
    public void subscribe(Message<?> message) {

        String receivedTopic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);

        Object payload = message.getPayload();

        try {
            if(payload instanceof String json) {

                if(receivedTopic.equals(INBOUND_COLOR_TOPIC)) {

                    ColorTrafficEvent request = mapper.readValue(json, ColorTrafficEvent.class);
                    sseService.sendEvent(TRAFFIC_LIGHT_COLOR, request);

                } else if(receivedTopic.equals(INBOUND_STATUS_TOPIC)) {

                    StatusIntersectionEvent request = mapper.readValue(json, StatusIntersectionEvent.class);
                    sseService.sendEvent(INTERSECTION_STATUS, request);
                } else if(receivedTopic.equals(INBOUND_RETURN_DATE_TOPIC)) {



                }

            } else {
                log.warn("Payload received {}. This is no String", payload);
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }




    }

}
