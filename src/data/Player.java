package data;

import static business.DeckFactory.createHand;

/**
 *
 * @author OnePoker UN
 */
public class Player implements Comparable<Player> {

    private static int count;

    private int id;
    private String name;
    private int credits;
    private Hand hand;
    private boolean humanPlayer;
    private boolean allIn;
    //rango
    private int elo;

    public Player(int id, String name) {
        this.id = id;
        this.elo = 1200;
        this.credits = 1000;
        this.name = name;

    }

    public Player(Player oldPlayer) {
        this.id = oldPlayer.getId();
        this.elo = oldPlayer.getElo();
        this.credits = oldPlayer.getCredits();
        this.name = oldPlayer.getName();
        this.hand = createHand("array");
    }

    public Player() {
        this(++count, "");
        hand = createHand("array");
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Player " + this.getId() + " Credits: " + this.getCredits() + "\tElo: " + this.elo;
    }

    @Override
    public int compareTo(Player player) {
        int out = this.getHand().compareTo(player.getHand());
        return out;
    }

    /**
     * @return the humanPlayer
     */
    public boolean isHumanPlayer() {
        return humanPlayer;
    }

    /**
     * @param humanPlayer the humanPlayer to set
     */
    public void setHumanPlayer(boolean humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param credits the credits to set
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * @return the elo
     */
    public int getElo() {
        return elo;
    }

    /**
     * @param elo the elo to set
     */
    public void setElo(int elo) {
        this.elo = elo;
    }

    /**
     * @return the allIn
     */
    public boolean isAllIn() {
        return allIn;
    }

    /**
     * @param allIn the allIn to set
     */
    public void setAllIn(boolean allIn) {
        this.allIn = allIn;
    }
}
