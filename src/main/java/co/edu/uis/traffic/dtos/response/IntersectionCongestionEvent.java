package co.edu.uis.traffic.dtos.response;

public record IntersectionCongestionEvent(
        String codeIntersection,
        Float congestionLevel
) {
    public static IntersectionCongestionEvent create(String codeIntersection, Float congestionLevel) {
        return new IntersectionCongestionEvent(
                codeIntersection,
                congestionLevel
        );
    }
}
