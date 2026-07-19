package co.edu.uis.traffic.dtos.request.device;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StatusRequest {
    private DeviceStatus status;
    private Long idTransaction;
    private LocalDateTime timestamp;
}
