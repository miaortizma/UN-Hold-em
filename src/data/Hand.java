package data;

import static businessLogic.HandHelper.compare;
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
    private int rank;
    private String rankName;

    public Hand() {
        hand = new ArrayList<>();
        this.rank = -1;
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
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
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

        if (getRank() > -1) {
            out += getRankName() + " ";
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

    /**
     * @return the rankName
     */
    public String getRankName() {
        return rankName;
    }

    /**
     * @param rankName the rankName to set
     */
    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

}
