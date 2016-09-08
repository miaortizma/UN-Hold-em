/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class Hand extends AbstractDeck {

    private String rank;

    public Hand(String type) {
        super(type);
    }

    public int[] getCardRanks() {
        int[] ranks = new int[5];
        for (int i = 0; i < 5; i++) {
            ranks[i] = getCard(i).getValue();
        }
        return ranks;

    }

    public int[] getCardSuits() {
        int[] suits = new int[5];
        for (int i = 0; i < 5; i++) {
            suits[i] = getCard(i).getSuit();
        }
        return suits;
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

    public void addAll(Hand hand) {
        for (int i = 0; i < hand.getCards().size(); i++) {
            this.addCard(hand.getCard(i));
        }
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
