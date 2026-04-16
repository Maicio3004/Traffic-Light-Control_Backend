package co.edu.uis.traffic.services.clients;

import co.edu.uis.traffic.dtos.response.google.maps.DistanceMatrix;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GoogleAPIClient {

    @Value("${google.apis.maps.api-key}")
    private String API_KEY;
    @Value("${google.apis.maps.base-url}")
    private String BASE_URL;

    private final RestTemplate restTemplate;

    public DistanceMatrix verifyCongestion(String origin, String destination) {
        final String URL = String.format(BASE_URL, origin, destination, API_KEY);

        return restTemplate.getForObject(URL, DistanceMatrix.class);
    }

}
