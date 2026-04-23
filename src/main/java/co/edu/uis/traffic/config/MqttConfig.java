package co.edu.uis.traffic.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

    @Value("${mqtt.broker-url}")
    private String brokerUrl;

    @Value("${mqtt.client-id}")
    private String clientId;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.topics.inbound-color-topic}")
    private String inboundColorTopic;

    @Value("${mqtt.topics.inbound-status-topic}")
    private String inboundStatusTopic;

    /**
     * Creamos el Factory para crear canales de entrada y salida
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory(){
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{brokerUrl});
        if(!username.isEmpty()) {
            options.setUserName(username);
            options.setPassword(password.toCharArray());
        }
        factory.setConnectionOptions(options);
        return factory;
    }

    /**
     * Adapter que se suscribe a uno o varios topico y reenvia a {@code inboundChannel}
     * @param inboundChannel Este es el canal asincrono que creamos en la clase {@code IntegrationConfig}
     * para poder enviar mensajes al front sin salir del hilo de ejecución
     */
    @Bean
    public MqttPahoMessageDrivenChannelAdapter inboundAdapter(MqttPahoClientFactory factory, MessageChannel inboundChannel) {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        clientId + "_in",
                        factory,
                        inboundColorTopic, inboundStatusTopic
                );

        adapter.setOutputChannel(inboundChannel);
        return adapter;
    }

    /**
     * Creamos el canal para publicar mensajes
     */
    @Bean
    public MessageChannel outboundChannel(){
        return new DirectChannel();
    }

    /**
     * Handler que publica mensajes desde {@code outboundChannel} al broker
     */
    @Bean
    @ServiceActivator(inputChannel = "outboundChannel")
    public MessageHandler messageHandler(MqttPahoClientFactory factory){
        MqttPahoMessageHandler handler =
                new MqttPahoMessageHandler(clientId + "_out", factory);

        handler.setAsync(true);
        return handler;
    }
}
