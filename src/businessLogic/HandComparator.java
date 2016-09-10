/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Hand;
import data.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Miguel Angel
 */
public class HandComparator {

    public static int compareRank(Hand hand, Hand anotherHand) {
        return 1;
    }

    public static int highCard(Hand hand) {
        int maxCard = hand.getCard(0).getValue();

        for (int i = 0; i < hand.getSize(); i++) {
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

    public static int highestThree(Hand hand, int size) {
        if (size == 3) {
            return hand.getCard(0).getValue();
        } else if (hand.getCard(size - 1).getValue() == hand.getCard(size - 2).getValue() && hand.getCard(size - 1).getValue() == hand.getCard(size - 3).getValue()) {
            return hand.getCard(size - 1).getValue();
        } else {
            return highestThree(hand, size - 1);
        }
    }

    public static int comparePair(Hand hand, Hand anotherHand) {
        int out;
        int thisPair = highestPair(hand, hand.getSize());
        int anotherPair = highestPair(anotherHand, hand.getSize());
        // System.out.println("COMPARING PAIR");
        //System.out.println(hand + "\t" + anotherHand);
        // System.out.println(thisPair + "\t" + anotherPair);
        if (thisPair > anotherPair) {
            out = 1;
        } else if (thisPair == anotherPair) {
            List<Integer> kicker = new ArrayList<>();
            out = compareKicker(hand, anotherHand, kicker);
        } else {
            out = -1;
        }
        //System.out.println(out);
        return out;
    }

    public static int compareThree(Hand hand, Hand anotherHand) {
        int out;
        int thisThree = highestThree(hand, hand.getSize());
        int anotherThree = highestThree(anotherHand, hand.getSize());
        if (thisThree > anotherThree) {
            out = 1;
        } else if (thisThree == anotherThree) {
            List<Integer> kicker = new ArrayList<>();
            out = compareKicker(hand, anotherHand, kicker);
        } else {
            out = -1;
        }
        return out;
    }

    public static int compareKicker(Hand hand, Hand anotherHand, List<Integer> pair) {
        ArrayList<Integer> handKickers = new ArrayList<>();
        Collections.copy(handKickers, pair);
        ArrayList<Integer> anotherKickers = new ArrayList<>();
        Collections.copy(anotherKickers, pair);
        int out = 0;
        while (out == 0) {
            out = Integer.compare(kicker(hand, handKickers), kicker(anotherHand, anotherKickers));
            if (handKickers.size() > 5) {
                break;
            }
            // System.out.println(">");
        }
        return out;
    }

    /**
     *
     * @param hand
     * @param filter
     * @return
     */
    public static int kicker(Hand hand, List<Integer> filter) {
        int kicker = 0;
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
