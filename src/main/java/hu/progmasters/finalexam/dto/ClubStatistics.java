package hu.progmasters.finalexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubStatistics {

    private int clubWin;
    private int playerAvgWin;
    private int maxWin;
    private int minWin;
}
