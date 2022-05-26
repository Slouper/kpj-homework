package cz.inventi.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ServiceDto {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String port;
    @NotNull
    private LocalDateTime registerTime;
}
