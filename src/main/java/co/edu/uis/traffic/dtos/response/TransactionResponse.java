package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.Transaction;

import java.time.LocalDateTime;

public record TransactionResponse(
    Long id,
    LocalDateTime createdAt,
    LocalDateTime returnDate,
    String codeIntersection,
    String description
) {
    public static TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getCreatedAt(),
                transaction.getReturnDate(),
                transaction.getIntersection().getCode(),
                transaction.getDescription()
        );
    }
}
