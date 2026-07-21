package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.request.device.DeviceStatus;
import co.edu.uis.traffic.dtos.request.device.StatusRequest;
import co.edu.uis.traffic.dtos.response.ActivationResponse;
import co.edu.uis.traffic.dtos.response.TransactionResponse;
import co.edu.uis.traffic.exceptions.EntityNotFound;
import co.edu.uis.traffic.mappers.TransactionMapper;
import co.edu.uis.traffic.persistence.models.Intersection;
import co.edu.uis.traffic.persistence.models.Transaction;
import co.edu.uis.traffic.persistence.models.enums.TransactionStatus;
import co.edu.uis.traffic.persistence.repositories.TransactionRepository;
import co.edu.uis.traffic.services.mqtt.MqttPublish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService implements CrudService<Transaction> {

    private final TransactionRepository repository;
    private final MqttPublish publisher;

    public void create(Intersection intersection) {
        Transaction savedTransaction = create(TransactionMapper.toEntity(intersection));
        activateTraffic(savedTransaction);
    }

    public void activateTraffic(Transaction transaction) {
        publisher.activateIntersection(ActivationResponse.fromTransaction(transaction));
    }

    public List<TransactionResponse> getAll() {
        return findAll().stream()
                .map(TransactionResponse::toResponse).toList();
    }

    public void update(StatusRequest request) {

        if(request.getStatus().equals(DeviceStatus.ACCEPTED)) {
            return;
        }

        Transaction transaction = findById(request.getIdTransaction());

        if(request.getStatus().equals(DeviceStatus.BLOCKED)) {
            transaction.setStatus(TransactionStatus.CANCELED);
            transaction.setDescription("Transacción rechazada por bloqueo de semaforo");
            transaction.setReturnDate(transaction.getCreatedAt());
        } else if (request.getStatus().equals(DeviceStatus.AVAILABLE)) {
            transaction.setStatus(TransactionStatus.COMPLETED);
            transaction.setDescription("Transacción finalizada");
            transaction.setReturnDate(request.getTimestamp());
        } else if (request.getStatus().equals(DeviceStatus.ACCEPTED)) {
            transaction.setStatus(TransactionStatus.ACCEPTED);
            transaction.setDescription("Semaforo en verde");
        }

        create(transaction);
    }

    @Override
    public Transaction create(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        repository.delete(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll();
    }

    @Override
    public Transaction findById(Number id) {
        return repository.findById(id.longValue())
                .orElseThrow(() -> new EntityNotFound("No Transaction found with id " + id));
    }
}
