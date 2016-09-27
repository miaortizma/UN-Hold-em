package data;

import static business.HandHelper.compare;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author OnePoker UN
 * @param <T>
 */
public class Hand<T extends Card> implements Comparable<Hand> {

    private final ArrayList<Card> hand;
    private HandRank rank;

    public enum HandRank {

        HIGHCARD(0), PAIR(1), TWOPAIR(2), THREE(3),
        STRAIGHT(4), FLUSH(5), FULLHOUSE(6),
        FOUR(7), STRAIGHTFLUSH(8), ROYAL(9);
        private static final String[] toString = {"High Card", "1 Pair", "2 Pair", "3 of a Kind",
            "Straight", "Flush", "Full House", "4 of a Kind", "Flush", "Royal Flush"};

        private final int value;

        public int getValue() {
            return this.value;
        }

        HandRank(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return toString[this.value];
        }
    }

    public Hand() {
        hand = new ArrayList<>();
        this.rank = null;
    }

    public int[] getCardRanks() {
        int[] ranks = new int[hand.size()];
        for (int i = 0; i < hand.size(); i++) {
            ranks[i] = getCard(i).getValue();
        }
        return ranks;
    }

    public int[] getCardSuits() {
        int[] suits = new int[5];
        for (int i = 0; i < 5; i++) {
            suits[i] = getCard(i).getSuit().getValue();
        }
        return suits;
    }

    /**
     * @return the rank
     */
    public HandRank getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(HandRank rank) {
        this.rank = rank;
    }

    public void addAll(Hand hand) {
        for (int i = 0; i < hand.getCards().size(); i++) {
            this.addCard(hand.getCard(i));
        }
    }

    public void set(int i, Card card) {
        this.getCards().set(i, card);
    }

    public Card getCard(int i) {
        return this.hand.get(i);
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public int size() {
        return hand.size();
    }

    public List<Card> getCards() {
        return this.hand;
    }

    @Override
    public String toString() {
        Collections.sort(getCards());
        String out = "";

        if (getRank() != null) {
            out += getRank().toString() + " ";
        }
        for (int i = 0; i < hand.size(); i++) {
            out += "" + hand.get(i);
        }
        return out;
    }

    /**
     * Uses {@link businessLogic.HandComparator#compare(data.Hand, data.Hand) }
     *
     * @param hand the hand to be compared
     * @return 1 if this ranks higher than hand 0 if they rank equal -1 if hand
     * ranks higher than this(hand)
     */
    @Override
    public int compareTo(Hand hand) {
        return compare(this, hand);
    }

}
