package data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author OnePoker UN
 */
public abstract class AbstractDeck {

    private final List<Card> cards;

    public AbstractDeck(String type) {
        if (type.equalsIgnoreCase("linked")) {
            cards = new LinkedList<>();
        } else if (type.equalsIgnoreCase("array")) {
            cards = new ArrayList<>();
        } else {
            System.out.println("INVALID DECK CONSTRUCTOR TYPE");
            cards = null;
        }
    }



    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int getSize() {
        return cards.size();
    }

    public Card pop() {
        return cards.remove(cards.size() - 1);
    }

}
