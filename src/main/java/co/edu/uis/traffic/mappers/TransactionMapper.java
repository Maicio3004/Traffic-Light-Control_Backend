package co.edu.uis.traffic.mappers;

import co.edu.uis.traffic.persistence.models.Intersection;
import co.edu.uis.traffic.persistence.models.Transaction;
import co.edu.uis.traffic.persistence.models.enums.TransactionStatus;

import java.time.LocalDateTime;

public class TransactionMapper {

    public static Transaction toEntity(Intersection intersection) {

        Transaction transaction = new Transaction();
        transaction.setIntersection(intersection);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setDescription("Transaction created successfully");

        return transaction;
    }

}
