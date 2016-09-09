/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static businessLogic.HandAnalyser.highCard;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class Hand extends AbstractDeck implements Comparable<Hand> {

    private int rank;

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
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
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

    @Override
    public int compareTo(Hand hand) {
        return hand.getRank() > this.getRank() ? (hand.getRank() == this.getRank() ? (highCard(hand) > highCard(this) ? 1 : -1) : -1) : -1;
    }

}
