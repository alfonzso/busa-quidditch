package hu.progmasters.finalexam.dto;

import hu.progmasters.finalexam.domain.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCreatedCommand {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Past
    private LocalDate joined;
    @NotNull
    private PlayerType playerType;

    private int wins;
    @NotNull
    private Integer clubId;
}
