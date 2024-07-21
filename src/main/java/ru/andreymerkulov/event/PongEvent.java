package ru.andreymerkulov.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.andreymerkulov.dto.PongDTO;

@Getter
public class PongEvent extends ApplicationEvent {

    private final PongDTO pongDTO;

    public PongEvent(Object source, PongDTO pongDTO) {
        super(source);
        this.pongDTO = pongDTO;
    }
}
