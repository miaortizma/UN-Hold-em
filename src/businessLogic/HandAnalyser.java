/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Card;
import data.Hand;
import java.util.List;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class HandAnalyser {

    private static final String[] HANDS = {"4 of a Kind", "Straight Flush", "Straight", "Flush", "High Card",
        "1 Pair", "2 Pair", "Royal Flush", "3 of a Kind", "Full House"};

    /**
     * Reference Author @subskybox
     * http://stackoverflow.com/questions/2829883/7-card-poker-hand-evaluator
     * http://www.codeproject.com/Articles/569271/A-Poker-hand-analyzer-in-JavaScript-using-bit-math
     *
     *
     *
     * @param hand
     * @return
     */
    public static String rankHand(Hand hand) {
        int[] ranks = hand.getCardRanks();
        int[] suits = hand.getCardSuits();
        long s = 0, v = 0, o;
        for (int i = 0; i < 5; i++) {
            s += 1 << ranks[i];
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

    public static String[] getHands() {
        return HANDS;
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
        for (int i = 0; i < 5; i++) {
            if (hand.getCard(i).getValue() > maxCard) {
                cardIndex = i;
            }
        }
        return hand.getCard(cardIndex);
    }

    /**
     * Heap's algorithm
     */
    public static void generate(int n, List<Card> cards) {
        if (n == 1) {
            System.out.println(cards.toString());
        } else {
            for (int i = 0; i < n - 1; i++) {
                generate(n - 1, cards);
                if (n % 2 == 0) {
                    swap(cards, i, -n - 1);
                } else {
                    swap(cards, 0, n - 1);
                }
            }
            generate(n - 1, cards);
        }
    }

    public static void swap(List<Card> cards, int i, int j) {
        Card temp = cards.get(j);
        cards.set(j, cards.get(i));
        cards.set(i, temp);
    }
}
