/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author OnePoker UN
 */
public class Deck {

    private final List<Card> cards;
    private String name;

    private Deck() {
        //System.out.print("Deck Constructor: ");
        this.cards = new ArrayList<>();
    }

    public Deck(String name) {
        this();
        String[] legalConstructorName = {"Dealing deck", "Hand", "Royal Flush"};
        //System.out.println(name);
        if (Arrays.asList(legalConstructorName).contains(name)) {
            //System.out.println("Created " + name);
            this.name = name;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addCard(Card card) {
        cards.add(card);

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
