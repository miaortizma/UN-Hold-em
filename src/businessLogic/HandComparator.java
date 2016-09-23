package businessLogic;

import data.Hand;
import data.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates {@link data.Hand#compareTo(data.Hand) }
 *
 * @author OnePoker UN
 */
public class HandComparator {

    /**
     *
     * @param hand the first hand to compare
     * @param anotherHand the second hand to compare
     * @return 1 if hand ranks higher than anotherHand, 0 if both rank equal, -1
     * if anotherHand ranks higher
     */
    public static int compare(Hand hand, Hand anotherHand) {
        int out;
        out = Integer.compare(hand.getRank(), anotherHand.getRank());
        if (out == 0) {
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
                    out = Integer.compare(thisHighCard, handHighCard);
                    if (out == 0) {
                        return compareKickers(hand, anotherHand, thisHighCard);
                    }
            }
        }
        return out;
    }

    /**
     *
     * @param hand the hand
     * @return highest ranking card in hand
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
     * @param hand the hand
     * @param sizeFilter the position that have been checked
     * @return highestPair in hand
     */
    public static int highestPair(Hand hand, int sizeFilter) {
        if (sizeFilter == 2) {
            return hand.getCard(0).getValue();
        } else if (hand.getCard(sizeFilter - 1).getValue() == hand.getCard(sizeFilter - 2).getValue()) {
            return hand.getCard(sizeFilter - 1).getValue();
        } else {
            return highestPair(hand, sizeFilter - 1);
        }
    }

    /**
     * NOTES/ TO DO: Create unit tests for highestPair and highestThree there
     * may be an error
     */
    /**
     * Only works for a sorted hand
     *
     * @param hand the hand
     * @param sizeFilter the position that have been checked
     * @return highest three of a kind in hand
     */
    public static int highestThree(Hand hand, int sizeFilter) {
        if (sizeFilter == 3) {
            return hand.getCard(0).getValue();
        } else if (hand.getCard(sizeFilter - 1).getValue() == hand.getCard(sizeFilter - 2).getValue() && hand.getCard(sizeFilter - 1).getValue() == hand.getCard(sizeFilter - 3).getValue()) {
            return hand.getCard(sizeFilter - 1).getValue();
        } else {
            return highestThree(hand, sizeFilter - 1);
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
        out = Integer.compare(thisPair, anotherPair);
        if (out == 0) {
            if (hand.getRank() == 2) {
                thisPair = highestPair(hand, 3);
                anotherPair = highestPair(anotherHand, 3);
                out = Integer.compare(thisPair, anotherPair);
                if (out == 0) {
                    return compareKickers(hand, anotherHand, thisPair);
                }
            } else {
                out = compareKickers(hand, anotherHand, thisPair);
            }
        }

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
        out = Integer.compare(thisThree, anotherThree);
        if (out == 0) {
            return compareKickers(hand, anotherHand, thisThree);
        }
        return out;
    }

    /**
     *
     * @param hand the hand
     * @param anotherHand
     * @param startingKicker
     * @return 1 if hand kickers rank higher than anotherHand, 0 if both hands
     * kickers rank equal, -1 if anotherHand kickers rank higher
     */
    public static int compareKickers(Hand hand, Hand anotherHand, int startingKicker) {
        ArrayList<Integer> foo = new ArrayList<>();
        foo.add(startingKicker);
        ArrayList<Integer> handKickers = (ArrayList) foo.clone();
        ArrayList<Integer> anotherKickers = (ArrayList) foo.clone();
        int out = 0;
        while (out == 0) {
            out = Integer.compare(kicker(hand, handKickers), kicker(anotherHand, anotherKickers));
            if (handKickers.get(handKickers.size() - 1) == -1 || anotherKickers.get(anotherKickers.size() - 1) == -1) {
                break;
            }
        }
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
