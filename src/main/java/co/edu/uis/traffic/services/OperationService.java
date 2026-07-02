package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.request.OperationRequest;
import co.edu.uis.traffic.dtos.request.ScheduleRequest;
import co.edu.uis.traffic.dtos.response.ScheduleResponse;
import co.edu.uis.traffic.exceptions.EntityNotFound;
import co.edu.uis.traffic.persistence.models.OperationMode;
import co.edu.uis.traffic.persistence.models.Schedule;
import co.edu.uis.traffic.persistence.repositories.OperationRepository;
import co.edu.uis.traffic.persistence.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService implements CrudService<OperationMode> {

    private final OperationRepository operationRepository;
    private final ScheduleRepository scheduleRepository;

    public void createMode(OperationRequest request) {

        OperationMode operationMode = new OperationMode();
        operationMode.setModeOperation(request.getMode());
        operationMode.setDescription(request.getDescription());

        create(operationMode);
    }

    @Override
    public OperationMode create(OperationMode mode) {
        return operationRepository.save(mode);
    }

    @Override
    public void delete(OperationMode mode) {
        operationRepository.delete(mode);
    }

    @Override
    public List<OperationMode> findAll() {
        return operationRepository.findAll();
    }

    @Override
    public OperationMode findById(Number id) {
        return operationRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFound("No operation mode found by id: " + id));
    }

    public ScheduleResponse createSchedule(ScheduleRequest request) {

        OperationMode mode = findById(request.getModeId());

        Schedule schedule = new Schedule();
        schedule.setMode(mode);
        schedule.setDaysOfWeek(request.getDayOfWeeks());
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());

        return ScheduleResponse.createResponse(scheduleRepository.save(schedule));
    }
    public List<ScheduleResponse> findAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponse::createResponse)
                .toList();
    }
    public void deleteSchedule(Integer id) {
        scheduleRepository.deleteById(id);
    }

}
