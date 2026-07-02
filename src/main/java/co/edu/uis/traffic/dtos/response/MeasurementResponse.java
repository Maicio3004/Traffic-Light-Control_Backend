package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.TrafficMeasurement;
import java.time.LocalDateTime;

public record MeasurementResponse(
        Long id,
        String intersectionCode,
        Integer duration,
        Integer durationInTraffic,
        Double congestionLevel,
        LocalDateTime createdAt,
        String codeResponse
) {
    public static MeasurementResponse toResponse(TrafficMeasurement m) {
        return new MeasurementResponse(
                m.getId(),
                m.getIntersection().getCode(),
                m.getDuration(),
                m.getDurationInTraffic(),
                m.getCongestionLevel(),
                m.getCreatedAt(),
                m.getCodeResponse()
        );
    }
}
