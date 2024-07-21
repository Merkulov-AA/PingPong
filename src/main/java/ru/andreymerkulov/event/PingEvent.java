package ru.andreymerkulov.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.andreymerkulov.dto.PingDTO;

@Getter
public class PingEvent extends ApplicationEvent {

    private final PingDTO pingDTO;

    public PingEvent(Object source, PingDTO pingDTO) {
        super(source);
        this.pingDTO = pingDTO;
    }
}
