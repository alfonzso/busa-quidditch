package hu.progmasters.finalexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubStatistics {

    private int clubWins;
    private double averagePlayerWins;
    private int maxPlayerWins;
    private int minPlayerWins;
}
