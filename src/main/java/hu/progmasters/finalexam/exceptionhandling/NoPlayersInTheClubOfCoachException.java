package hu.progmasters.finalexam.exceptionhandling;

public class NoPlayersInTheClubOfCoachException extends RuntimeException {
    private final int coachId;

    public NoPlayersInTheClubOfCoachException(int coachId) {
        this.coachId = coachId;
    }

    public int getCoachId() {
        return coachId;
    }
}
