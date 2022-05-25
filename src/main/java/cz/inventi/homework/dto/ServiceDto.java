package cz.inventi.homework.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ServiceDto {
    private int id;
    @NotNull
    private final String name;
    @NotNull
    private final String port;
    @NotNull
    private final LocalDateTime registerTime;
}
