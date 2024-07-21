package ru.andreymerkulov.service;


import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.andreymerkulov.dto.PingDTO;
import ru.andreymerkulov.dto.PongDTO;
import ru.andreymerkulov.event.PingEvent;
import ru.andreymerkulov.event.PongEvent;
import ru.andreymerkulov.model.Ping;
import ru.andreymerkulov.model.Pong;

import java.util.List;

@Component("Resender")
@AllArgsConstructor
public class Resender {

    private PingService pingService;
    private PongService pongService;
    private final ApplicationEventPublisher applicationEventPublisher;

    void resendPing(){
        List<Ping> pings = pingService.getNewEvents();
        List<PingEvent> pingEvents = pings.stream()
                .map(ping -> new PingEvent(this, new PingDTO(ping.getPingId(), ping.getPingDateTime())))
                .toList();
        for (PingEvent pingEvent : pingEvents) {
            applicationEventPublisher.publishEvent(pingEvent);
        }
    }

    void resendPong(){
        List<Pong> pongs = pongService.getNewEvents();
        List<PongEvent> pongEvents = pongs.stream()
                .map(ping -> new PongEvent(this, new PongDTO(ping.getPongId(), ping.getPongDateTime())))
                .toList();
        for (PongEvent pongEvent : pongEvents) {
            applicationEventPublisher.publishEvent(pongEvent);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void listenerContext() {
        resendPing();
        resendPong();
    }
}
