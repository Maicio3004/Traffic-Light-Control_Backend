package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.Schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public record ScheduleResponse(
        Integer id,
        List<DayOfWeek> dayOfWeeks,
        LocalTime starTime,
        LocalTime endTime,
        String nameMode
) {
    public static ScheduleResponse createResponse(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getDaysOfWeek(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getMode().getModeOperation().name()
        );
    }
}
