/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Hand;
import data.Card;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Angel
 */
public class HandComparator {

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
                case 1:
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
}
