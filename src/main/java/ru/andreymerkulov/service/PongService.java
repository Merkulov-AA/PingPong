package ru.andreymerkulov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.andreymerkulov.dto.PongDTO;
import ru.andreymerkulov.model.Pong;
import ru.andreymerkulov.model.Status;
import ru.andreymerkulov.repository.PongRepository;

import java.util.List;

import static ru.andreymerkulov.model.Status.NEW;

@Service
@AllArgsConstructor
public class PongService {

    private final PongRepository pongRepository;

    public void save(PongDTO pongDTO){
        pongRepository.save(new Pong(
                pongDTO.getPongId(),
                pongDTO.getPongDateTime(),
                NEW)
        );
    }

    public void sent(Integer id) {
        Pong pong = pongRepository.findById(id).get();
        pong.setStatus(Status.SENT);
        pongRepository.save(pong);
    }

    public List<Pong> getNewEvents() {
        return pongRepository.findAllByStatus(NEW);
    }
}
