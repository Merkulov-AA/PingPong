package ru.andreymerkulov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pong {
    @Id
    private Integer pongId;
    private LocalDateTime pongDateTime;
    @Enumerated(EnumType.STRING)
    private Status status;
}