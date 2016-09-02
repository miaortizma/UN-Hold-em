/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Card;
import data.Hand;
import java.util.Arrays;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class HandAnalyser {

    private static String[] hands = {"4 of a Kind", "Straight Flush", "Straight", "Flush", "High Card",
        "1 Pair", "2 Pair", "Royal Flush", "3 of a Kind", "Full House"};

    private static String rankHand(Hand hand) {
        int[] ranks = hand.getCardRanks();
        int[] suits = hand.getCardSuits();

        System.out.println(Arrays.toString(ranks));
        long s = 0;
        for (int i = 0; i < ranks.length; i++) {
            s += 1 << ranks[i];
        }

        long v = 0, o;
        for (int i = 0; i < 5; i++) {
            o = (long) Math.pow(2, (ranks[i] - 2) * 4);
            v += o * (((v / o) & 15) + 1);
        }
        //0x403c Ace low Straight
        //(s / (s & -s) == 31) Straight
        v = v % 15 - ((s / (s & -s) == 31) || (s == 0x403c) ? 3 : 1);
        //0x7c00 Royal Flush
        v -= (allEqual(suits) ? 1 : 0) * ((s == 0x7c00) ? -5 : 1);
        return getHands()[(int) v];

    }

    public static void pair(Hand hand) {
        //asume que recibe una mano ordenada
        for (int i = hand.getSize(); i > 0; i--) {

        }
    }

    public static String[] getHands() {
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
