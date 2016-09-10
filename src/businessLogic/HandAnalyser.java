/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import static businessLogic.DeckFactory.cloneHand;
import static businessLogic.DeckFactory.createHand;
import static businessLogic.DeckFactory.createKicker;
import data.Hand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class HandAnalyser {

    public static final String[] HANDS = {"4 of a Kind", "Straight Flush", "Straight", "Flush", "High Card", "1 Pair", "2 Pair", "Royal Flush", "3 of a Kind", "Full House"};
    public static final HashMap<String, Integer> RANKS = new HashMap<>();

    static {
        //HIGH CARD
        RANKS.put(HANDS[4], 0);
        //1 PAIR
        RANKS.put(HANDS[5], 1);
        //2 PAIR
        RANKS.put(HANDS[6], 2);
        //ROYAL FLUSH
        RANKS.put(HANDS[7], 9);
        //STRAIGHT
        RANKS.put(HANDS[2], 4);
        //FLUSH
        RANKS.put(HANDS[3], 5);
        //FULL HOUSE
        RANKS.put(HANDS[9], 6);
        //4 OF A KIND
        RANKS.put(HANDS[0], 7);
        //STRAIGHT FLUSH
        RANKS.put(HANDS[1], 8);
        //3 OF A KIND
        RANKS.put(HANDS[8], 3);

    }

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
    public static void rankHand(Hand hand) {
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
        hand.setRankName(HANDS[(int) v]);
        hand.setRank(RANKS.get(HANDS[(int) v]));
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
     */
    /**
     * http://stackoverflow.com/questions/33859993/get-all-possible-5-card-poker-hands-given-7-cards
     *
     * @param playerHand
     * @param comunitary
     * @return
     */
    public static List<Hand> bestHand(Hand playerHand, Hand comunitary) {
        Hand merge = createHand("array");
        Hand bestHand = comunitary;
        rankHand(bestHand);
        merge.addAll(playerHand);
        merge.addAll(comunitary);

        Hand temp = createHand("linked");
        temp.addAll(comunitary);
        int cardSelected = 0;
        // select first card not to be in the hand
        for (int firstCard = 0; firstCard < 7; firstCard++) {
            // select first card not to be in the hand
            for (int secondCard = firstCard + 1; secondCard < 7; secondCard++) {
                // every card that is not the first or second will added to the hand

                for (int i = 0; i < 7; i++) {
                    if (i != firstCard && i != secondCard) {
                        temp.set(cardSelected++, merge.getCard(i));
                        //temp.addCard(merge.getCard(i));
                        //System.out.println("TEMP:" + temp);
                        //System.out.println("BEST:" + bestHand);
                    }
                }
                //System.out.println("TEMP:" + temp);
                //System.out.println("BEST:" + bestHand);
                Collections.sort(temp.getCards());
                rankHand(temp);
                bestHand = bestHand.compareTo(temp) > 0 ? bestHand : cloneHand(temp);

                // next hand
                cardSelected = 0;
            }
        }
        //System.out.println(bestHand);
        //System.out.println("\n\n\n\n");
        List<Hand> out = new ArrayList<>();
        out.add(bestHand);
        out.add(createKicker(merge, bestHand));
        return out;
    }

}
