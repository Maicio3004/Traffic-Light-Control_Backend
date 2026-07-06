package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.response.events.CurrentModeEvent;
import co.edu.uis.traffic.persistence.models.Schedule;
import co.edu.uis.traffic.persistence.models.enums.Mode;
import co.edu.uis.traffic.persistence.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService implements CrudService<Schedule> {

    private static final String CURRENT_MODE_EVENT = "current-mode";

    private final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    private final ScheduleRepository scheduleRepository;
    private final TrafficService trafficService;
    private final SseService sseService;

    private LocalDateTime lastExecution = null;


    @Scheduled(fixedRate = 60000)
    public void scheduler() {

        Schedule schedule = getCurrentSchedule();

        logger.info("Horario actual: {}", schedule);

        if (schedule == null) {
            sseService.sendEvent(CURRENT_MODE_EVENT, CurrentModeEvent.create(Mode.OFF.name()));
            return;
        }

        Mode mode = schedule.getMode().getModeOperation();

        sseService.sendEvent(CURRENT_MODE_EVENT, CurrentModeEvent.create(mode.name()));

        switch (mode) {
            case OFF:
                return;
            case NORMAL:
                executeIfDue(5);
                break;
            case PEAK:
                executeIfDue(3);
                break;
        }
    }


    public void executeIfDue(int minutes) {

        LocalDateTime now = LocalDateTime.now();

        if(lastExecution == null ||
                Duration.between(lastExecution, now).toMinutes() >= minutes) {

            trafficService.verifyCongestion();
            lastExecution = now;
        }
    }

    public Schedule getCurrentSchedule() {
        LocalTime now = LocalTime.now();
        DayOfWeek today = LocalDate.now().getDayOfWeek();

        List<Schedule> schedules = scheduleRepository.findCurrentSchedule(now);

        for(Schedule schedule : schedules) {
            for(DayOfWeek day: schedule.getDaysOfWeek()) {
                if(day.equals(today)) {
                    return schedule;
                }
            }
        }
        return null;
    }

    @Override
    public Schedule create(Schedule schedule) {
        return null;
    }

    @Override
    public void delete(Schedule schedule) {

    }

    @Override
    public List<Schedule> findAll() {
        return List.of();
    }

    @Override
    public Schedule findById(Number id) {
        return null;
    }
}
