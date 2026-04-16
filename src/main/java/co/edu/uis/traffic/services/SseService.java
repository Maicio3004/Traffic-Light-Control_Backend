package co.edu.uis.traffic.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class SseService {

    private final Executor executor = Executors.newCachedThreadPool();
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(0L);

        emitters.add(emitter);

        emitter.onTimeout(() -> {
            emitter.complete();
            emitters.remove(emitter);
        });

        emitter.onError(err -> {
            emitter.complete();
            emitters.remove(emitter);
        });

        emitter.onCompletion(() -> emitters.remove(emitter));

        return emitter;
    }

    public void sendEvent(String eventName, Object data) {

        executor.execute(() -> {
            List<SseEmitter> deadEmitters = new ArrayList<>();

            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(
                            SseEmitter.event()
                                    .name(eventName)
                                    .data(data)
                    );
                } catch (IOException | IllegalStateException e) {
                    deadEmitters.add(emitter);
                }
            }

            emitters.removeAll(deadEmitters);
        });
    }

    @Scheduled(fixedRate = 5000) // cada 5 segundos
    public void sendPeriodicData() {
        try {
            // ejemplo de dato dinámico
            Map<String, Object> data = new HashMap<>();
            data.put("timestamp", System.currentTimeMillis());
            data.put("status", "ok");

            sendEvent("periodic-data", data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
