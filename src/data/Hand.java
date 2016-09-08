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
 * @author OnePoker UN Estudiante
 */
public class Hand {

    private List<Card> cards;
    private String rank;

    public int[] getCardRanks() {
        int[] ranks = new int[5];
        for (int i = 0; i < 5; i++) {
            ranks[i] = cards.get(i).getValue();
        }
        return ranks;

    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public int[] getCardSuits() {
        int[] suits = new int[5];
        for (int i = 0; i < 5; i++) {
            suits[i] = cards.get(i).getSuit();
        }
        return suits;
    }

    public Hand(String name) {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    /**
     * @return the rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < cards.size(); i++) {
            out += cards.get(i).toString();
        }
        return out;
    }

}
