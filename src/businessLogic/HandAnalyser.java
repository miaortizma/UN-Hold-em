/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Card;
import data.Hand;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class HandAnalyser {

    private static final String[] HANDS = {"4 of a Kind", "Straight Flush", "Straight", "Flush", "High Card", "1 Pair", "2 Pair", "Royal Flush", "3 of a Kind", "Full House"};

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
     * http://stackoverflow.com/questions/33859993/get-all-possible-5-card-poker-hands-given-7-cards
     *
     * @param playerHand
     * @param comunitary
     */
    public static void allPossibleHands(Hand playerHand, Hand comunitary) {
        Hand merge = new Hand("array");
        merge.setCards(new ArrayList<>());
        merge.addAll(playerHand);
        merge.addAll(comunitary);
        Hand[] allHands = new Hand[21];
        int cardsSelected = 0;
        int hand = 0;
        // select first card not to be in the hand
        for (int firstCard = 0; firstCard < 7; firstCard++) {
            // select first card not to be in the hand
            for (int secondCard = firstCard + 1; secondCard < 7; secondCard++) {
                // every card that is not the first or second will added to the hand
                Hand temp = new Hand("array");
                for (int i = 0; i < 7; i++) {
                    if (i != firstCard && i != secondCard) {
                        temp.addCard(merge.getCard(i));
                    }
                }
                allHands[hand] = temp;
                System.out.println(temp);
                System.out.println((hand) + ": " + rankHand(temp));
                // next hand
                cardsSelected = 0;
                hand++;
            }
        }
    }

}
