package ru.andreymerkulov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andreymerkulov.model.Ping;
import ru.andreymerkulov.model.Status;

import java.util.List;

@Repository
public interface PingRepository extends JpaRepository<Ping, Integer> {

    public List<Ping> findAllByStatus(Status status);
}