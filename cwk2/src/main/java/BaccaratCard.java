public class BaccaratCard extends Card {

    public BaccaratCard(Rank r, Suit s) {
        super(r, s);
    }

    public BaccaratCard(String name) {
        super(name);
    }
    
    // Assigning value to cards
    @Override
    public int value() {
        if (getRank() == Rank.ACE) {
            return 1;
        } else if (getRank() == Rank.TWO) {
            return 2;
        } else if (getRank() == Rank.THREE) {
            return 3;
        } else if (getRank() == Rank.FOUR) {
            return 4;
        } else if (getRank() == Rank.FIVE) {
            return 5;
        } else if (getRank() == Rank.SIX) {
            return 6;
        } else if (getRank() == Rank.SEVEN) {
            return 7;
        } else if (getRank() == Rank.EIGHT) {
            return 8;
        } else if (getRank() == Rank.NINE) {
            return 9;
        } else {
            return 0; // For TEN, JACK, QUEEN, KING
        }
    }

    public int baccaratValue() {
        int value = value();
        return value >= 10 ? 0 : value; // Baccarat values are from 0-9
    }
    
    // Thrwoing required exceptions
    public int getValue() {
        throw new UnsupportedOperationException("Unimplemented method 'getValue'");
    }

    public int getBaccaratValue() {
        throw new UnsupportedOperationException("Unimplemented method 'getBaccaratValue'");
    }
}

