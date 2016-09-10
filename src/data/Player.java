/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author OnePoker UN &
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
        this.id = +count;
        hand = new Hand("Player " + this.id + " Deck");
        //System.out.println("Creating player with id: " + this.id);
        count++;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "#" + getId() + "  " + getHand();
    }

    @Override
    public int compareTo(Player player) {
        return this.getHand().compareTo(player.getHand());
    }
}
