package co.edu.uis.traffic.dtos.response.events;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusIntersectionEvent{ //[{codeIntersection: "INT-2-1"}, {codeIntersection: "INT-2-2"}]
    private String status;
    private String codeIntersection;
}
