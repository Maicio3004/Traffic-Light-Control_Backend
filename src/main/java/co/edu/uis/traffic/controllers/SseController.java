package co.edu.uis.traffic.controllers;

import co.edu.uis.traffic.services.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class SseController {

    private final SseService sseService;

    @GetMapping(value = "/stream/transactions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamTransactions() {
        return sseService.subscribe();
    }

}


