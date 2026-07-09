package co.edu.uis.traffic.dtos.response;

public record Payload(
        Boolean payload,
        Long idTransaction
) {
    public static Payload create(Boolean isValid, Long idTransaction) {
        return new Payload(isValid, idTransaction);
    }

    @Override
    public String toString() {
        return "Payload{" +
                "payload=" + payload +
                ", idTransaction=" + idTransaction +
                '}';
    }
}
