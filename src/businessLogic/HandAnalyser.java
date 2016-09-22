package businessLogic;

import static businessLogic.DeckFactory.cloneHand;
import static businessLogic.DeckFactory.createHand;
import data.Hand;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class HandAnalyser {

    public static final String[] HANDS = {"4 of a Kind", "Straight Flush", "Straight", "Flush", "High Card", "1 Pair", "2 Pair", "Royal Flush", "3 of a Kind", "Full House"};
    public static final HashMap<String, Integer> RANKS = new HashMap<>();

    //maps HANDS into RANKS
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
     * Reference Author @subskybox See
     * <a href = "http://www.codeproject.com/Articles/569271/A-Poker-hand-analyzer-in-JavaScript-using-bit-math"></a>
     *
     * Sets rankName and rank of the input hand, given it is a 5 cards Hand
     *
     * @param hand the hand to be ranked
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
        v = v % 15 - ((s / (s & -s) == 31) || (s == 0x403c) ? 3 : 1);
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
     * <p>
     * See :
     * <a href = "http://stackoverflow.com/questions/33859993/get-all-possible-5-card-poker-hands-given-7-cards"></a>
     * for the combinatorics Uses {@link data.Hand#compareTo(data.Hand) }
     * Uses {@link  businessLogic.HandComparator#compare(data.Hand, data.Hand)}
     * </p>
     *
     * @param playerHand  the player Hand
     * @param comunitary the tableHand
     * @return bestHand the best hand that can be obtained from
     */
    public static Hand bestHand(Hand playerHand, Hand comunitary) {
        Hand merge = createHand("array");
        Hand bestHand = comunitary;
        rankHand(bestHand);
        merge.addAll(playerHand);
        merge.addAll(comunitary);

        Hand temp = createHand("linked");
        temp.addAll(comunitary);
        int cardSelected = 0;
        for (int firstCard = 0; firstCard < 7; firstCard++) {
            for (int secondCard = firstCard + 1; secondCard < 7; secondCard++) {
                for (int i = 0; i < 7; i++) {
                    if (i != firstCard && i != secondCard) {
                        temp.set(cardSelected++, merge.getCard(i));
                    }
                }
                Collections.sort(temp.getCards());
                rankHand(temp);
                bestHand = HandComparator.compare(bestHand, temp) > 0 ? bestHand : cloneHand(temp);
                cardSelected = 0;
            }
        }
        return bestHand;
    }

}
