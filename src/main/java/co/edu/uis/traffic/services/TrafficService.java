package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.response.events.IntersectionCongestionEvent;
import co.edu.uis.traffic.dtos.response.events.RouteCongestionEvent;
import co.edu.uis.traffic.dtos.response.google.maps.DistanceMatrix;
import co.edu.uis.traffic.persistence.models.Intersection;
import co.edu.uis.traffic.persistence.models.Route;
import co.edu.uis.traffic.persistence.models.TrafficMeasurement;
import co.edu.uis.traffic.persistence.repositories.IntersectionRepository;
import co.edu.uis.traffic.persistence.repositories.RouteRepository;
import co.edu.uis.traffic.persistence.repositories.TrafficMeasurementRepository;
import co.edu.uis.traffic.services.clients.GoogleAPIClient;
import co.edu.uis.traffic.dtos.response.IntersectionResponse;
import co.edu.uis.traffic.dtos.response.MeasurementResponse;
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
    private final TrafficMeasurementRepository trafficMeasurementRepository;

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

            //Guardamos la respuesta en la base de datos
            saveResponse(response, intersections, i);

            double congestion = response.getCongestion();

            log.info("Congestion: {}, Intersection: {}", congestion, intersections.get(i).getCode());

            //Enviamos el nivel de congestion de la intersección
            sseService.sendEvent(
                    INTERSECTION_CONGESTION,
                    IntersectionCongestionEvent.create(intersections.get(i).getCode(), congestion)
            );

            if(congestion > CONGESTION_THRESHOLD) {
                reduceCongestion(intersections, i);
                i = intersections.size();
            }

        }
        logRouteSummary(route, isBeforeNoon, sort);
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

    private void logRouteSummary(Route route, boolean isBeforeNoon, Sort sort) {

        String origin;
        String finish;

        List<Intersection> intersections = intersectionRepository
                .findByRoute(route, sort);

        if(isBeforeNoon) {
            origin = intersections.getFirst().getCoordinate().toString();
            finish = route.getInitCoordinate().toString();
        } else {
            origin = route.getInitCoordinate().toString();
            finish =  intersections.getLast().getCoordinate().toString();
        }

        double congestion = googleAPIClient
                .verifyCongestion(origin, finish).getCongestion();

        log.info("Congestion global en la 27: {}", congestion);
        log.info("Origen de medición: {}", intersections.getFirst().getCode());
        log.info("Destindo de medición: {}", intersections.getLast().getCode());

        //Enviamos la congestión global de la ruta 1.0
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

    public void saveResponse(DistanceMatrix response, List<Intersection> intersections, int index) {

        TrafficMeasurement traffic = new TrafficMeasurement();
        traffic.setDuration(response.duration());
        traffic.setDurationInTraffic(response.durationInTraffic());
        traffic.setCongestionLevel(response.getCongestion());
        traffic.setCreatedAt(response.getTimestamp());
        traffic.setCodeResponse(response.getStatus());
        traffic.setIntersection(intersections.get(index));

        trafficMeasurementRepository.save(traffic);
    }
    public List<IntersectionResponse> getAllIntersections() {
        return intersectionRepository.findAll()
                .stream()
                .map(IntersectionResponse::fromEntity)
                .toList();
    }
    public List<MeasurementResponse> findAllMeasurements() {
        return trafficMeasurementRepository.findAll()
                .stream()
                .map(MeasurementResponse::toResponse)
                .toList();
    }

}