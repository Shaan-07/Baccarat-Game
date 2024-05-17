import java.util.Collections;

public class Shoe extends CardCollection {

    private final int numDecks;

    public Shoe(int numDecks) {
        super();
        if (numDecks < 6 || numDecks > 8 || numDecks == 7) {
            throw new CardException("Number of decks must be either 6 or 8");
        }
        this.numDecks = numDecks;
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < numDecks; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    add(new BaccaratCard(rank, suit));
                }
            }
        }
    }

    // A method to shuffle all the ccards
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // A method to deal the cards
    public BaccaratCard deal() {
        if (!cards.isEmpty()) {
            return (BaccaratCard) cards.remove(0);
        }
        throw new CardException("Shoe is empty, cannot deal");
    }

    public int size() {
        return cards.size();
    }
}
