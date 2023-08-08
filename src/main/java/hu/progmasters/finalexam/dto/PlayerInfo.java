package hu.progmasters.finalexam.dto;

import hu.progmasters.finalexam.domain.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInfo {
    private int id;
    private String name;
    private LocalDate joined;
    private PlayerType playerType;
    private int wins;
    private String clubName;

}
