package ru.andreymerkulov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.andreymerkulov.event.PongEvent;
import ru.andreymerkulov.kafka.PongProducer;

@Component
@AllArgsConstructor
public class PongEventListener {

    private final PongProducer pongProducer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handlePongEvent(PongEvent pongEvent) {
        pongProducer.send(pongEvent);
    }
}