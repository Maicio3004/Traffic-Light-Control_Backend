package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.response.google.maps.DistanceMatrix;
import co.edu.uis.traffic.dtos.response.google.maps.Element;
import co.edu.uis.traffic.persistence.models.Intersection;
import co.edu.uis.traffic.persistence.models.Route;
import co.edu.uis.traffic.persistence.repositories.RouteRepository;
import co.edu.uis.traffic.services.clients.GoogleAPIClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrafficService {

    private final RouteRepository routeRepository;
    private final GoogleAPIClient googleAPIClient;
    private final TransactionService transactionService;

    /**
     * <p>
     *     Verifica los niveles de congestion de cada tramo de la interseccion
     *     si supera un umbral establecido se da más tiempo desde ese tramo
     *     hasta el final de la vía para poder descongestionar.
     *     Envia la señal a cada uno de los semaforos
     * </p>
     */
    public void verifyCongestion() {

        List<Route> routes = routeRepository.findAll();

        routes.forEach(route -> {

            List<Intersection> intersections = route.getIntersections();
            int counter = 0;
            int j;

            for(int i = 0; i < intersections.size(); i++) {

                Intersection intersection = intersections.get(i);

                j = i;

                String destination = i == intersections.size() - 1 ? route.getInitCoordinate().toString()
                        : intersections.get(++j).getCoordinate().toString();

                String origin = intersection.getCoordinate().toString();

                DistanceMatrix response = googleAPIClient.verifyCongestion(origin, destination);

                System.out.println("Google cloud response: " + response);

                List<Element> elements = response.getRows().getFirst().getElements();

                float duration = elements.getFirst().getDuration().getValue();
                float durationInTraffic = elements.getFirst().getDuration_in_traffic().getValue();

                float congestion = ((durationInTraffic - duration) / duration) * 100;
                System.out.println("Congestion: " + congestion + "%");

                if(congestion > 50) {
                    reduceCongestion(intersections, i);
                    i = intersections.size();
                    System.out.println("Todo en verde");
                }
                counter++;
            }
            System.out.println("Total de peticiones: " + counter + "\nPara la Ruta: " + route.getName());
        });


    }

    public void reduceCongestion(List<Intersection> intersections, int iterator) {
        for (int i = iterator; i < intersections.size(); i++) {
            transactionService.create(intersections.get(i));
        }
    }

}
