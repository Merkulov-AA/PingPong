package ru.andreymerkulov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.andreymerkulov.dto.PingDTO;
import ru.andreymerkulov.model.Ping;
import ru.andreymerkulov.model.Status;
import ru.andreymerkulov.repository.PingRepository;

import java.util.List;

import static ru.andreymerkulov.model.Status.NEW;

@Service
@AllArgsConstructor
public class PingService {

    private final PingRepository pingRepository;

    public void save(PingDTO pingDTO){
        pingRepository.save(new Ping(
                pingDTO.getPingId(),
                pingDTO.getPingDateTime(),
                NEW)
        );
    }

    public void sent(Integer id) {
        Ping ping = pingRepository.findById(id).get();
        ping.setStatus(Status.SENT);
        pingRepository.save(ping);
    }

    public List<Ping> getNewEvents() {
        return pingRepository.findAllByStatus(NEW);
    }
}
