package hu.progmasters.finalexam.domain;

public enum PlayerType {

    CHASER(3), BEATER(2), KEEPER(1),
    SEEKER(1);


    private int maxPlayerFromType;

    private PlayerType(int maxPlayerFromType) {
        this.maxPlayerFromType = maxPlayerFromType;
    }

    public int getMaxPlayerFromType() {
        return maxPlayerFromType;
    }
}
