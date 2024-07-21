package ru.andreymerkulov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andreymerkulov.model.Pong;
import ru.andreymerkulov.model.Status;

import java.util.List;

@Repository
public interface PongRepository extends JpaRepository<Pong, Integer> {

    public List<Pong> findAllByStatus(Status status);
}