package co.edu.uis.traffic.dtos.response.events;

public record RouteCongestionEvent(
        Double congestionLevel,
        Integer routeId
) {
    public static RouteCongestionEvent create(Double congestionLevel, Integer routeId) {
        return new RouteCongestionEvent(congestionLevel, routeId);
    }
}
