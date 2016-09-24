package data;

import static businessLogic.DeckHelper.compare;
import java.util.Collections;

/**
 *
 * @author OnePoker UN
 */
public class Hand extends AbstractDeck implements Comparable<Hand> {

    private int rank;
    private String rankName;

    public Hand(String type) {
        super(type);
        this.rank = -1;
    }

    public int[] getCardRanks() {
        int[] ranks = new int[5];
        for (int i = 0; i < 5; i++) {
            ranks[i] = getCard(i).getValue();
        }
        return ranks;

    }

    public int[] getCardSuits() {
        int[] suits = new int[5];
        for (int i = 0; i < 5; i++) {
            suits[i] = getCard(i).getSuit();
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

    @Override
    public String toString() {
        Collections.sort(getCards());
        String out = "";

        if (getRank() > -1) {
            out += getRankName() + " ";
        }
        out += super.toString();
        return out;
    }

    @Override
    public int compareTo(Hand hand) {
        int out = compare(this, hand);
        /**
         * System.out.println("COMPARING:"); System.out.println(this + "\t" +
         * hand); System.out.println(this.getRank() + " " + hand.getRank());
         * System.out.println(out); System.out.println("\n\n\n");
         *
         */
        if (hand.getRank() == 1) {
            //System.out.println(out);
        }
        //System.out.println(out);
        return out;
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
