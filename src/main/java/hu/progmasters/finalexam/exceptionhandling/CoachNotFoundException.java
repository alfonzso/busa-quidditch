package hu.progmasters.finalexam.exceptionhandling;

public class CoachNotFoundException extends RuntimeException {
    private final int coachId;

    public CoachNotFoundException(int coachId) {
        this.coachId = coachId;
    }

    public int getCoachId() {
        return coachId;
    }
}
