package ru.andreymerkulov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.andreymerkulov.event.PingEvent;
import ru.andreymerkulov.kafka.PingProducer;

@Component
@AllArgsConstructor
public class PingEventListener {

    private final PingProducer pingProducer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handlePingEvent(PingEvent pingEvent) {
        pingProducer.send(pingEvent);
    }
}