package hu.progmasters.finalexam.exceptionhandling;

public class ClubNotFoundException extends RuntimeException {
    private final int clubId;

    public ClubNotFoundException(int clubId) {
        this.clubId = clubId;
    }

    public int getClubId() {
        return clubId;
    }
}
