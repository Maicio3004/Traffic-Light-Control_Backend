package co.edu.uis.traffic.dtos.request.device;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReturnDateRequest {
    LocalDateTime returnDate;
    Long idTransaction;
}
