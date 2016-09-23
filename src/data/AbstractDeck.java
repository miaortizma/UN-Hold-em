package data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author OnePoker UN
 */
public abstract class AbstractDeck {

    private List<Card> cards;

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
        return this.getCards().get(i);
    }

    public void addCard(Card card) {
        this.getCards().add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int getSize() {
        return getCards().size();
    }

    public Card pop() {
        return getCards().remove(getCards().size() - 1);
    }

    /**
     * @param cards the cards to set
     */
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < getSize(); i++) {
            out += getCard(i).toString();
        }
        return out;
    }

}
