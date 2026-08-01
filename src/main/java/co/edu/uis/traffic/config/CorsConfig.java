package co.edu.uis.traffic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${config.cors.allowed-origins}")
    private String origins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("CORS configuraddos: " + origins);
        registry.addMapping("/**")
                .allowedOrigins(origins)
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
