package co.edu.uis.traffic.dtos.request;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Getter
public class ScheduleRequest {

    private List<DayOfWeek> dayOfWeeks;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer modeId;

}
