package co.edu.uis.traffic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.messaging.MessageChannel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class IntegrationConfig {

    @Bean
    public Executor mqttExecutor() {
        return Executors.newFixedThreadPool(10);
    }

    // Este será el canal asincrono de entrada
    @Bean
    public MessageChannel inboundChannel(Executor mqttExecutor) {
        return new ExecutorChannel(mqttExecutor);
    }

}
