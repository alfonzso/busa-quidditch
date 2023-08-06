package hu.progmasters.finalexam.exceptionhandling;

public class PlayerNotFoundException extends RuntimeException {

    private final int playerId;

    public PlayerNotFoundException(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }


}


