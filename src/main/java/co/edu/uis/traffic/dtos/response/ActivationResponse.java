package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.Transaction;

public record ActivationResponse(
    Payload payload,
    String endTopic
) {
    public static ActivationResponse fromTransaction(Transaction transaction) {
        return new ActivationResponse(
            Payload.create(
                    transaction.isValid(),
                    transaction.getId()
            ),
            transaction.getIntersection().getCode()
        );
    }
}
