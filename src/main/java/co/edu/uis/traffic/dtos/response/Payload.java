package co.edu.uis.traffic.dtos.response;

import co.edu.uis.traffic.persistence.models.Transaction;

public record Payload(
        Boolean payload,
        Long idTransaction,
        String location
) {
    public static Payload create(Transaction transaction) {
        return new Payload(
                true,
                transaction.getId(),
                transaction.getIntersection().getRoute().getLocation().name()
        );
    }

    @Override
    public String toString() {
        return "Payload{" +
                "payload=" + payload +
                ", idTransaction=" + idTransaction +
                '}';
    }
}
