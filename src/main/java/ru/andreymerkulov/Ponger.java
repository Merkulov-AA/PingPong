package ru.andreymerkulov;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.andreymerkulov.dto.PongDTO;
import ru.andreymerkulov.event.PongEvent;
import ru.andreymerkulov.service.PongService;


import static org.apache.kafka.common.utils.Utils.sleep;

@Component
@AllArgsConstructor
public class Ponger {

    private PongService pongService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @KafkaListener(topics = "pong", groupId = "ping-pong-group")
    @Transactional
    public void listen(PongDTO pongDTO) {
        System.out.println("Received pong: " + pongDTO);
        pongService.save(pongDTO);

        sleep(3000);

        PongEvent pongEvent = new PongEvent(this, pongDTO);
        applicationEventPublisher.publishEvent(pongEvent);
    }
}
