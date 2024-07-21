package ru.andreymerkulov.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.andreymerkulov.dto.PingDTO;
import ru.andreymerkulov.dto.PongDTO;
import ru.andreymerkulov.event.PingEvent;
import ru.andreymerkulov.service.PingService;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class PingProducer {

    private final PingService pingService;
    private final KafkaTemplate<String, PongDTO> kafkaPong;

    @Transactional
    public void send(PingEvent pingEvent) {
        Integer id = pingEvent.getPingDTO().getPingId();
        pingService.sent(id);

        PongDTO pongDTO = new PongDTO();
        pongDTO.setPongId(id+1);
        pongDTO.setPongDateTime(LocalDateTime.now());

        kafkaPong.send("pong", pongDTO);
    }
}
