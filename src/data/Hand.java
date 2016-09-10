/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static businessLogic.HandComparator.comparePair;
import static businessLogic.HandComparator.compareThree;
import static businessLogic.HandComparator.highCard;
import java.util.Collections;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class Hand extends AbstractDeck implements Comparable<Hand> {

    private int rank;
    private String rankName;

    public Hand(String type) {
        super(type);
        this.rank = -1;
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
        Collections.sort(getCards());
        String out = "";

        if (getRank() > -1) {
            out += getRankName() + " \t";
        }
        for (int i = 0; i < getSize(); i++) {
            out += getCard(i).toString();
        }
        return out;
    }

    @Override
    public int compareTo(Hand hand) {
        int out;
        if (hand.getRank() > this.getRank()) {
            return -1;
        } else if (hand.getRank() == this.getRank()) {
            switch (rank) {
                case 1:
                case 2:
                    return comparePair(this, hand);
                case 3:
                    return compareThree(this, hand);
                default:
                    int thisHighCard = highCard(this);
                    int handHighCard = highCard(hand);
                    if (thisHighCard >= handHighCard) {
                        out = thisHighCard == handHighCard ? 0 : 1;
                    } else {
                        out = -1;
                    }
                    break;
            }
        } else {
            return 1;
        }

        return out;
    }

    /**
     * @return the rankName
     */
    public String getRankName() {
        return rankName;
    }

    /**
     * @param rankName the rankName to set
     */
    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

}
