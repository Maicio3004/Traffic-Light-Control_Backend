package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.response.events.IntersectionCongestionEvent;
import co.edu.uis.traffic.dtos.response.events.RouteCongestionEvent;
import co.edu.uis.traffic.dtos.response.google.maps.DistanceMatrix;
import co.edu.uis.traffic.persistence.models.Intersection;
import co.edu.uis.traffic.persistence.models.Route;
import co.edu.uis.traffic.persistence.repositories.IntersectionRepository;
import co.edu.uis.traffic.persistence.repositories.RouteRepository;
import co.edu.uis.traffic.services.clients.GoogleAPIClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrafficService {

    private static final float CONGESTION_THRESHOLD = 2.0f;

    /**
     * Nombre del evento para enviar la congestión de cada intersección
     */
    private static final String INTERSECTION_CONGESTION = "intersection-congestion";

    /**
     * Nombre del evento para enviar la congestión completa de una ruta
     */
    private static final String ROUTE_CONGESTION = "route-congestion";

    private static final Logger log = LoggerFactory.getLogger(TrafficService.class);

    private final RouteRepository routeRepository;
    private final IntersectionRepository intersectionRepository;
    private final GoogleAPIClient googleAPIClient;
    private final TransactionService transactionService;
    private final SseService sseService;

    /**
     * <p>
     *     Verifica los niveles de congestion de cada tramo de la interseccion
     *     si supera un umbral establecido se da más tiempo desde ese tramo
     *     hasta el final de la vía para poder descongestionar.
     *     Envia la señal a cada uno de los semaforos
     * </p>
     */
    public void verifyCongestion() {

        boolean isBeforeNoon = LocalTime.now().isBefore(LocalTime.NOON);
        List<Route> routes = routeRepository.findAll();

        routes.forEach(route -> processRoute(route, isBeforeNoon));
    }

    private void processRoute(Route route, boolean isBeforeNoon) {

        boolean congestionHandled = false;
        Sort sort = isBeforeNoon
                ? Sort.by(Sort.Direction.ASC, "position")
                : Sort.by(Sort.Direction.DESC, "position");

        List<Intersection> intersections =
                intersectionRepository.findByRoute(route, sort);

        for (int i = 0; i < intersections.size(); i++) {

            Pair<String, String> segment =
                    calculateSegment(intersections, route, i, isBeforeNoon);

            DistanceMatrix response = googleAPIClient.verifyCongestion(
                    segment.getFirst(),
                    segment.getSecond()
            );

            double congestion = response.getCongestion();

            log.info("Congestion: {}, Intersection: {}", congestion, intersections.get(i).getCode());

            //Enviamos el nivel de congestion de la intersección
            sseService.sendEvent(
                    INTERSECTION_CONGESTION,
                    IntersectionCongestionEvent.create(intersections.get(i).getCode(), congestion)
            );

            if(congestion > CONGESTION_THRESHOLD && !congestionHandled) {
                reduceCongestion(intersections, i);
                congestionHandled = true;
            }

        }
        logRouteSummary(route, isBeforeNoon);
    }

    private Pair<String, String> calculateSegment(
            List<Intersection> intersections,
            Route route,
            int index,
            boolean isBeforeNoon
    ) {

        int nextIndex = index + 1;
        int prevIndex = index - 1;
        String origin;
        String finish;

        if(isBeforeNoon) {

            origin = intersections.get(index).getCoordinate().toString();

            finish = (index == intersections.size() - 1)
                    ? route.getInitCoordinate().toString()
                    : intersections.get(nextIndex).getCoordinate().toString();

        } else {

            origin = (index == 0)
                    ? route.getInitCoordinate().toString()
                    : intersections.get(prevIndex).getCoordinate().toString();

            finish = intersections.get(index).getCoordinate().toString();

        }
        return Pair.of(origin, finish);
    }

    private void logRouteSummary(Route route, boolean isBeforeNoon) {

        String origin;
        String finish;

        if(isBeforeNoon) {
            origin = route.getIntersections().getFirst().getCoordinate().toString();
            finish = route.getInitCoordinate().toString();
        } else {
            origin = route.getInitCoordinate().toString();
            finish =  route.getIntersections().getLast().getCoordinate().toString();
        }

        double congestion = googleAPIClient.verifyCongestion(origin, finish).getCongestion();

        log.info("Congestion global en la 27: {}", congestion);

        //Enviamos la congestión global de la ruta
        sseService.sendEvent(
                ROUTE_CONGESTION,
                RouteCongestionEvent.create(congestion, route.getId())
        );
    }

    private void reduceCongestion(List<Intersection> intersections, int iterator) {
        for (int i = iterator; i < intersections.size(); i++) {
            transactionService.create(intersections.get(i));
        }
    }

}