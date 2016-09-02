/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Card;
import data.Hand;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class HandAnalyser {

    private String[] hands = {"4 of a Kind", "Straight Flush", "Straight", "Flush", "High Card",
        "1 Pair", "2 Pair", "Royal Flush", "3 of a Kind", "Full House"};

    public static void pair(Hand hand) {
        //asume que recibe una mano ordenada
        for (int i = hand.getSize(); i > 0; i--) {

        }
    }

    public String[] getHands() {
        return hands;
    }

    public static int[] rankFrequency(Hand hand) {
        int[] frequency = new int[13];
        for (int i = 0; i < hand.getSize(); i++) {
            frequency[hand.getCard(i).getValue()]++;
        }
        return frequency;
    }

    public static int[] handSuits(Hand hand) {
        int[] suits = new int[hand.getSize()];
        for (int i = 0; i < hand.getSize(); i++) {
            suits[i] = hand.getCard(i).getSuit();
        }
        return suits;
    }

    public static int[] handRanks(Hand hand) {
        int[] ranks = new int[hand.getSize()];
        for (int i = 0; i < hand.getSize(); i++) {
            ranks[i] = hand.getCard(i).getValue();
        }
        return ranks;
    }

    public static boolean allEqual(int[] x) {
        int first = x[0];
        for (int i = 0; i < x.length; i++) {
            if (x[i] != first) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param hand
     * @return indice de la carta màs alta
     */
    public static Card highCard(Hand hand) {
        int maxCard = hand.getCard(0).getValue(), cardIndex = 0;
        for (int i = 0; i < hand.getSize(); i++) {
            if (hand.getCard(i).getValue() > maxCard) {
                cardIndex = i;
            }
        }
        return hand.getCard(cardIndex);
    }

}
