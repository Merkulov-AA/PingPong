package ru.andreymerkulov;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.andreymerkulov.dto.PingDTO;
import ru.andreymerkulov.dto.PongDTO;
import ru.andreymerkulov.event.PingEvent;
import ru.andreymerkulov.event.PongEvent;
import ru.andreymerkulov.repository.PingRepository;
import ru.andreymerkulov.service.PingService;

import java.time.LocalDateTime;

import static org.apache.kafka.common.utils.Utils.sleep;

@Component
@AllArgsConstructor
public class Pinger {

    private PingService pingService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @KafkaListener(topics = "ping", groupId = "ping-pong-group")
    @Transactional
    public void listen(PingDTO pingDTO) {
        System.out.println("Received ping: " + pingDTO);
        pingService.save(pingDTO);

        sleep(3000);

        PingEvent pingEvent = new PingEvent(this, pingDTO);
        applicationEventPublisher.publishEvent(pingEvent);
    }
}
