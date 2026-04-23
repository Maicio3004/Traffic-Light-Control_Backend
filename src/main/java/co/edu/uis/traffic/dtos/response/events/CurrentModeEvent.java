package co.edu.uis.traffic.dtos.response.events;

public record CurrentModeEvent(
        String currentMode
) {
    public static CurrentModeEvent create(String currentMode) {
        return new CurrentModeEvent(currentMode);
    }
}
