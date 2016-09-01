/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OnePoker UN  & 
 */
public class Deck {

    private final List<Card> cards;
    private String name;

    public Deck() {
        System.out.print("Deck Constructor: ");
        this.cards = new ArrayList<>();
    }

    public Deck(String name) {
        this();
        System.out.println("Created " + name);
        this.name = name;
    }

    public void add(Card card) {
        cards.add(card);

    }

    public String getName() {
        return this.name;
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int getSize() {
        return cards.size();
    }

}
