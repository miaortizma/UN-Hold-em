package data;

/**
 *
 * @author OnePoker UN
 */
public class Card implements Comparable<Card> {

    private final Rank rank;
    private final Suit suit;

    public enum Rank {

        DEUCE(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

        private final int value;
        private static final String[] toString = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "Q", "K", "A"};

        public int getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return toString[value - 2];
        }

        Rank(int value) {
            this.value = value;
        }
    }

    public enum Suit {
        DIAMONDS(0),
        CLUBS(1),
        HEARTS(2),
        SPADES(3);

        private final int value;
        private static final String[] toString = {"\u2666", "\u2663", "\u2764", "\u2660"};

        public int getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return toString[value];
        }

        Suit(int value) {
            this.value = value;
        }
    }

    /**
     *
     * @param rank
     * @param suit
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue() {
        return this.rank.getValue();
    }

    public Suit getSuit() {
        return this.suit;
    }

    @Override
    public String toString() {
        return this.rank.toString() + "" + this.suit.toString();
    }

    @Override
    public int compareTo(Card card) {
        return this.getValue() > card.getValue() ? 1 : -1;
    }
}
