package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.Transaction;

public record ActivationResponse(
    Boolean payload,
    String endTopic
) {
    public static ActivationResponse fromTransaction(Transaction transaction) {
        return new ActivationResponse(
            transaction.isValid(),
            transaction.getIntersection().getCode()
        );
    }
}
