package hu.progmasters.finalexam.exceptionhandling;

import hu.progmasters.finalexam.domain.PlayerType;

public class PlayerAlreadyJoinedException extends RuntimeException {
    private final int clubId;
    private final int playerId;

    public PlayerAlreadyJoinedException(int clubId, int playerId) {
        this.clubId = clubId;
        this.playerId = playerId;
    }

    public int getClubId() {
        return clubId;
    }

    public int getPlayerId() {
        return playerId;
    }
}
