package co.edu.uis.traffic.services.mqtt;

import co.edu.uis.traffic.dtos.request.device.ColorRequest;
import co.edu.uis.traffic.dtos.response.ActivationResponse;
import co.edu.uis.traffic.dtos.response.events.StatusIntersectionEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqttPublish {

    private final MessageChannel outboundChannel;
    private final ObjectMapper objectMapper;

    @Value("${mqtt.topics.outbound-activate-topic}")
    private String BASE_ACTIVATE_TOPIC;

    public void activateIntersection(ActivationResponse response) {

        String json = writeValueAsString(response.payload());
        String topic = BASE_ACTIVATE_TOPIC + response.endTopic();

        Message<String> message = MessageBuilder
                .withPayload(json)
                .setHeader(MqttHeaders.TOPIC, topic)
                .build();

        outboundChannel.send(message);

        System.out.println("Message sent: " + message);

    }

    public void test(ColorRequest request) {

        String json = writeValueAsString(request);
        String topic = "intersection/status";

        Message<String> message = MessageBuilder
                .withPayload(json)
                .setHeader(MqttHeaders.TOPIC, topic)
                .build();

        outboundChannel.send(message);
    }

    public void test(StatusIntersectionEvent request) {

        String json = writeValueAsString(request);
        String topic = "intersection/status";

        Message<String> message = MessageBuilder
                .withPayload(json)
                .setHeader(MqttHeaders.TOPIC, topic)
                .build();

        outboundChannel.send(message);
    }

    private String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
