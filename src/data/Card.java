/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import ui.UI;

/**
 *
 * @author OnePoker UN &
 */
public class Card implements Comparable<Card> {

    /**
     * Notes/ To DO
     *
     *
     */
    private final int value;
    private final int suit;

    /**
     * 0 < value < 13 0 < i < 13 suit -> Spades 13 < i < 26 suit -> Clubs 26 < i
     * < 39 suit -> Hearts 39 < i < 52 suit -> Diamonds
     *
     * @param i, -1 < i < 52
     */
    public Card(int i) {
        // System.out.println("Attempt to create card with value: "  + i);
        int newValue;
        this.suit = (i < 13) ? 0 : (i < 26) ? 1 : (i < 39) ? 2 : (i < 52) ? 3 : 4;
        //System.out.println("Suit calculated as :" + this.suit);
        switch (this.suit) {
            case (0): {
                newValue = i;
                break;
            }
            case (1): {
                newValue = i % 13;
                break;
            }
            case (2): {
                newValue = i % 26;
                break;
            }
            case (3): {
                newValue = i % 39;
                break;
            }
            default: {
                System.out.println("Not a valid card");
                newValue = -1;
            }
        }
        if (newValue < 0 || newValue > 13) {
            throw new IllegalArgumentException();
        }
        if (newValue == 0) {
            newValue = 14;
        } else {
            newValue++;
        }
        this.value = newValue;
    }

    public Card(int value, int suit) {
        if (1 < value && value < 15) {
            this.value = value;
        } else {
            this.value = -1;
            System.out.println("Invalid value");
        }
        if (-1 < suit && suit < 4) {
            this.suit = suit;
        } else {
            this.suit = -1;
            System.out.println("Invalid suit");
        }

    }

    public int getValue() {
        return this.value;
    }

    public int getSuit() {
        return this.suit;
    }

    @Override
    public String toString() {
        return UI.RANKS[this.value - 2] + "" + UI.SUITS[this.suit] + "\t";
    }

    @Override
    public int compareTo(Card card) {
        return getValue() > card.getValue() ? 1 : -1;
    }
}
