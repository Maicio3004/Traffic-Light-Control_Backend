package co.edu.uis.traffic.dtos.response;

public record RouteCongestionEvent(
        Float congestionLevel,
        Integer routeId
) {
    public static RouteCongestionEvent create(Float congestionLevel, Integer routeId) {
        return new RouteCongestionEvent(congestionLevel, routeId);
    }
}
