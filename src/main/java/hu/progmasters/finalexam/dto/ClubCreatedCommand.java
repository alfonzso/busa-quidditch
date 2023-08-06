package hu.progmasters.finalexam.dto;

import hu.progmasters.finalexam.domain.Coach;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubCreatedCommand {

    @NotNull
    @NotBlank(message = "must not be blank")
    private String name;
    @NotNull
    private int wins;
    private Coach coach;
}
