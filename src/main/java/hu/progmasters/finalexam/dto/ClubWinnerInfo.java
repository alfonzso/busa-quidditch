package hu.progmasters.finalexam.dto;

import hu.progmasters.finalexam.domain.Coach;
import hu.progmasters.finalexam.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubWinnerInfo {
    private int id;
    private String name;
    private int wins;
    private Coach coach;
    private List<Player> players;
}
