package co.edu.uis.traffic.dtos.request;

import co.edu.uis.traffic.persistence.models.enums.Mode;
import lombok.Getter;

@Getter
public class OperationRequest {

    private Mode mode;
    private String description;


}
