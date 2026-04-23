package co.edu.uis.traffic.dtos.response.events;

public record IntersectionCongestionEvent(
        String codeIntersection,
        Double congestionLevel
) {
    public static IntersectionCongestionEvent create(String codeIntersection, Double congestionLevel) {
        return new IntersectionCongestionEvent(
                codeIntersection,
                congestionLevel
        );
    }
}
