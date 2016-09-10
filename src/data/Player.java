package data;

import static businessLogic.DeckFactory.createHand;

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
    //rango
    private int elo;

    public Player(int id, String name) {
        this.id = id;
        this.elo = 1200;
        this.credits = 1000;
        this.name = name;

    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Player() {
        this.id = count++;
        hand = createHand("array");
    }

    public int getId() {
        return this.id;
    }


    @Override
    public String toString() {
        return "#" + getId() + "\t" + getHand();
    }

    @Override
    public int compareTo(Player player) {
        int out = this.getHand().compareTo(player.getHand());
        return out;
    }

}
