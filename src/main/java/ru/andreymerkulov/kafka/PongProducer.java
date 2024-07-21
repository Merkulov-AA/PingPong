package ru.andreymerkulov.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.andreymerkulov.dto.PingDTO;
import ru.andreymerkulov.dto.PongDTO;
import ru.andreymerkulov.event.PingEvent;
import ru.andreymerkulov.event.PongEvent;
import ru.andreymerkulov.service.PingService;
import ru.andreymerkulov.service.PongService;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class PongProducer {

    private final PongService pongService;
    private final KafkaTemplate<String, PingDTO> kafkaPing;

    @Transactional
    public void send(PongEvent pongEvent) {
        Integer id = pongEvent.getPongDTO().getPongId();
        pongService.sent(id);

        PingDTO pingDTO = new PingDTO();
        pingDTO.setPingId(id+1);
        pingDTO.setPingDateTime(LocalDateTime.now());

        kafkaPing.send("ping", pingDTO);
    }
}
