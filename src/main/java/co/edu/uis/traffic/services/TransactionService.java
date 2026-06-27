package co.edu.uis.traffic.services;

import co.edu.uis.traffic.dtos.response.ActivationResponse;
import co.edu.uis.traffic.exceptions.EntityNotFound;
import co.edu.uis.traffic.mappers.TransactionMapper;
import co.edu.uis.traffic.persistence.models.Intersection;
import co.edu.uis.traffic.persistence.models.Transaction;
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
        Transaction transaction = TransactionMapper.toEntity(intersection);
        repository.save(transaction);
        activateTraffic(transaction);
    }

    public void activateTraffic(Transaction transaction) {
        publisher.activateIntersection(ActivationResponse.fromTransaction(transaction));
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
