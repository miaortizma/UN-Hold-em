package businessLogic;

import static businessLogic.DeckFactory.cloneHand;
import static businessLogic.DeckFactory.createHand;
import data.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class DeckHelper {

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
     * Reference Author @subskybox
     * http://stackoverflow.com/questions/2829883/7-card-poker-hand-evaluator
     * http://www.codeproject.com/Articles/569271/A-Poker-hand-analyzer-in-JavaScript-using-bit-math
     *
     * Sets rankName and rank of the input hand, given it is a 5 cards Hand
     *
     *
     * //0x403c Ace low Straight //(s / (s & -s) == 31) Straight //0x7c00
     * RoyalFlush
     *
     * @param hand
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
     * http://stackoverflow.com/questions/33859993/get-all-possible-5-card-poker-hands-given-7-cards
     *
     * @param playerHand
     * @param comunitary
     * @return bestHand
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
        return bestHand;
    }
    
    /**
     *
     * @param hand
     * @param anotherHand
     * @return 1 if hand ranks higher than anotherHand, 0 if both rank equal, -1
     * if anotherHand ranks higher
     */
    public static int compare(Hand hand, Hand anotherHand) {
        /*System.out.println("COMPARING:");
        System.out.println(hand + "\t" + anotherHand);
        System.out.println(hand.getRank() + "\t" + anotherHand.getRank());
         */
        int out;
        if (hand.getRank() < anotherHand.getRank()) {
            return -1;
        } else if (hand.getRank() == anotherHand.getRank()) {
            switch (hand.getRank()) {
                // 1 pair
                case 1:
                 //2 pair
                case 2:
                    return comparePair(hand, anotherHand);
                case 3:
                    return compareThree(hand, anotherHand);
                case 6:
                    return compareThree(hand, anotherHand);
                default:
                    int thisHighCard = highCard(hand);
                    int handHighCard = highCard(hand);
                    if (thisHighCard > handHighCard) {
                        return 1;
                    } else if (thisHighCard == handHighCard) {
                        ArrayList<Integer> kicker = new ArrayList<>();
                        kicker.add(thisHighCard);
                        //System.out.println(kicker.toString());
                        out = compareKickers(hand, anotherHand, kicker);
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
     *
     * @param hand
     * @return highest ranking card
     */
    public static int highCard(Hand hand) {
        int maxCard = hand.getCard(0).getValue();
        for (int i = 1; i < hand.getSize(); i++) {
            maxCard = hand.getCard(i).getValue() > maxCard ? hand.getCard(i).getValue() : maxCard;
        }
        return maxCard;
    }

    /**
     * Only works for a sorted hand
     *
     * @param clone
     * @param size
     * @return
     */
    public static int highestPair(Hand clone, int size) {
        // System.out.println(clone.getCard(size - 1) + "" + clone.getCard(size - 2));
        if (size == 2) {
            return clone.getCard(0).getValue();
        } else if (clone.getCard(size - 1).getValue() == clone.getCard(size - 2).getValue()) {
            return clone.getCard(size - 1).getValue();
        } else {
            return highestPair(clone, size - 1);
        }
    }

    /**
     * Only works for a sorted hand
     *
     * @param hand
     * @param size
     * @return
     */
    public static int highestThree(Hand hand, int size) {
        if (size == 3) {
            return hand.getCard(0).getValue();
        } else if (hand.getCard(size - 1).getValue() == hand.getCard(size - 2).getValue() && hand.getCard(size - 1).getValue() == hand.getCard(size - 3).getValue()) {
            return hand.getCard(size - 1).getValue();
        } else {
            return highestThree(hand, size - 1);
        }
    }

    /**
     * hand and anotherHand rankName is "1 Pair" or "2 Pair"
     *
     * @param hand
     * @param anotherHand
     * @return 1 if hand ranks higher than anotherHand, 0 if both rank equal, -1
     * if anotherHand ranks higher
     */
    public static int comparePair(Hand hand, Hand anotherHand) {
        int out;
        int thisPair = highestPair(hand, hand.getSize());
        int anotherPair = highestPair(anotherHand, hand.getSize());
        /*System.out.println("COMPARING PAIR");
        System.out.println(hand + "\t" + anotherHand);
        System.out.println(thisPair + "\t" + anotherPair);
         */ if (thisPair > anotherPair) {
            out = 1;
        } else if (thisPair == anotherPair) {
            // System.out.println("SAME RANK");
            List<Integer> kicker = new ArrayList<>();
            out = compareKickers(hand, anotherHand, kicker);
        } else {
            out = -1;
        }
        //System.out.println(out);
        return out;
    }

    /**
     * hand and anotherHand rankName is "Three of a kind"
     *
     * @param hand
     * @param anotherHand
     * @return 1 if hand ranks higher than anotherHand, 0 if both rank equal, -1
     * if anotherHand ranks higher
     */
    public static int compareThree(Hand hand, Hand anotherHand) {
        int out;
        int thisThree = highestThree(hand, hand.getSize());
        int anotherThree = highestThree(anotherHand, hand.getSize());
        if (thisThree > anotherThree) {
            out = 1;
        } else if (thisThree == anotherThree) {
            List<Integer> kicker = new ArrayList<>();
            out = compareKickers(hand, anotherHand, kicker);
        } else {
            out = -1;
        }
        return out;
    }

    /**
     *
     * @param hand
     * @param anotherHand
     * @param pair
     * @return 1 if hand kickers rank higher than anotherHand, 0 if both hands
     * kickers rank equal, -1 if anotherHand kickers rank higher
     */
    public static int compareKickers(Hand hand, Hand anotherHand, List<Integer> pair) {
        //System.out.println(">");
        ArrayList<Integer> handKickers = new ArrayList<>();
        ArrayList<Integer> anotherKickers = new ArrayList<>();
        //System.out.println(pair.toString());
        handKickers.addAll(pair);
        anotherKickers.addAll(pair);
        int out = 0;
        while (out == 0) {
            out = Integer.compare(kicker(hand, handKickers), kicker(anotherHand, anotherKickers));
            if (handKickers.get(handKickers.size() - 1) == -1 || anotherKickers.get(anotherKickers.size() - 1) == -1) {
                break;
            }
        }
        // System.out.println("KICKERS");
        //System.out.println(handKickers.toString() + "\t" + anotherKickers.toString());
        return out;
    }

    /**
     *
     * @param hand
     * @param filter
     * @return highest next kicker that is not in filter
     */
    public static int kicker(Hand hand, List<Integer> filter) {
        //if kicker returns -1 then no comparation was made, then compareKickers should terminate
        int kicker = -1;
        for (int i = 0; i < hand.getSize(); i++) {
            Card temp = hand.getCard(i);
            if (temp.getValue() > kicker && !filter.contains(temp.getValue())) {
                kicker = temp.getValue();
            }
        }
        filter.add(kicker);
        return kicker;
    }
    
    public static void burnCard(AbstractDeck deck) {
        //System.out.println("Card burned!");
        deck.pop();
    }

    public static Card deal(DealingDeck deck) {
        if (deck.getCards().isEmpty()) {
            throw new IllegalArgumentException("Empty deck", null);
        }
        return deck.getCards().remove(deck.getSize() - 1);
    }

    public static void deal(DealingDeck deck, Hand mano, int i) {
        for (int j = 0; j < i; j++) {
            mano.addCard(deal(deck));
        }
    }

    public static void dealToPlayers(Round round) {
        List<Player> players = round.getPlayers();
        DealingDeck deck = round.getDealingDeck();
        //System.out.println("Dealing time");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            deal(deck, player.getHand(), 2);
        }

    }

    public static void shuffleDeck(DealingDeck deck) {
        deck.setShuffled(true);
        //System.out.println("Shuffling DealingDeck !!");
        Random rnd = new Random();
        List<Card> cards = deck.getCards();
        int index;
        Card temp;
        for (int i = cards.size() - 1; i > 0; i--) {
            index = rnd.nextInt(i + 1);
            temp = cards.get(index);
            cards.set(index, cards.get(i));
            cards.set(i, temp);
        }
    }

}
