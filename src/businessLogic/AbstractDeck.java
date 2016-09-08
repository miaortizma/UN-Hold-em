/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Card;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Miguel Angel
 */
public abstract class AbstractDeck {

    private List<Card> cards;

    public AbstractDeck(String type) {
        if (type.equalsIgnoreCase("linked")) {
            cards = new LinkedList<>();
        } else if (type.equalsIgnoreCase("array")) {
            cards = new ArrayList<>();
        }
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
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

}
