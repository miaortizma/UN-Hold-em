package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OnePoker UN
 * @param <T>
 */
public class Deck<T extends Card> {

    private final List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
    }

    /**
     * @return the deck
     */
    public List<Card> getDeck() {
        return deck;
    }

    public void addCard(Card c) {
        deck.add(c);
    }

    public int size() {
        return deck.size();
    }

    public Card remove(int i) {
        return deck.remove(i);
    }

    public Card getCard(int i) {
        return deck.get(i);
    }

    public void set(int i, Card c) {
        deck.set(i, c);
    }

}
