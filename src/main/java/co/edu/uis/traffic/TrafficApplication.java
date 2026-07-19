package co.edu.uis.traffic;

import co.edu.uis.traffic.utils.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Set;

@EnableScheduling
@SpringBootApplication
public class TrafficApplication {

	public static void main(String[] args) {

        EnvLoader envLoader = new EnvLoader();

        Set<String> keys = Set.of("DB_HOST", "DB_PORT", "DB_USERNAME", "DB_PASSWORD", "DB_DEFAULT",
                "BACKEND_PORT", "MQTT_BROKER_URL", "MQTT_CLIENT_ID", "MQTT_USERNAME", "MQTT_PASSWORD",
                "INBOUND_COLOR_TOPIC", "INBOUND_STATUS_TOPIC", "OUTBOUND_ACTIVATE_TOPIC",
                "ACTIVATE_TOPIC", "GOOGLE_MAPS_BASE_URL", "GOOGLE_MAPS_API_KEY",
                "INBOUND_STATUS_DEVICE_TOPIC");

        envLoader.loadSelected(keys);

		SpringApplication.run(TrafficApplication.class, args);
	}

}
