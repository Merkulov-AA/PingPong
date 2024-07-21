package ru.andreymerkulov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PingDTO {
    private Integer pingId;
    private LocalDateTime pingDateTime;
}