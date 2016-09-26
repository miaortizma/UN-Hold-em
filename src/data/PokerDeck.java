package data;

/**
 *
 * @author OnePoker UN
 */
public class PokerDeck extends Deck<Card> {

    private boolean shuffled;

    public PokerDeck(String type) {
        super(type);
    }

    /**
     * @return the shuffled
     */
    public boolean isShuffled() {
        return shuffled;
    }

    /**
     * @param shuffled the shuffled to set
     */
    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    @Override
    public String toString() {
        String out = shuffled ? "Shuffled deck: " : "Not shuffled Deck";
        out += super.toString();
        return out;
    }

}
