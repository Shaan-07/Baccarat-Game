public class BaccaratHand extends CardCollection {

    public BaccaratHand() {
        super();
    }

    public int baccaratTotal() {
        int total = 0;
        for (Card card : cards) {
            BaccaratCard baccaratCard = (BaccaratCard) card;
            total += baccaratCard.baccaratValue();
        }
        return total % 10; // Baccarat total is modulo 10
    }

    public int value() {
        return baccaratTotal();
    }

    public boolean isNatural() {
        return size() == 2 && (baccaratTotal() == 8 || baccaratTotal() == 9);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card.toString()).append(" ");
        }
        return stringBuilder.toString().trim(); // Trim any trailing space
    }

    // Throwing all the required exceptions
    public Object get(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    public Object[] getCards() {
        throw new UnsupportedOperationException("Unimplemented method 'getCards'");
    }

    public void remove(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}


