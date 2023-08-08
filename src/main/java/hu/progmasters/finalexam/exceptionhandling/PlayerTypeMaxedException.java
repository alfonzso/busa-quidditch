package hu.progmasters.finalexam.exceptionhandling;

import hu.progmasters.finalexam.domain.PlayerType;

public class PlayerTypeMaxedException extends RuntimeException {
    private final int clubId;
    private final PlayerType playerType;

    public PlayerTypeMaxedException(int clubId, PlayerType playerType) {
        this.clubId = clubId;
        this.playerType = playerType;
    }

    public int getClubId() {
        return clubId;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
