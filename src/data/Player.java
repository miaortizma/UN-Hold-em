/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static businessLogic.DeckFactory.createHand;

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
    private Hand kickers;
    private Round round;
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
        hand = createHand("array");
        count++;
    }

    public int getId() {
        return this.id;
    }

    public void setKickers(Hand hand) {
        this.kickers = hand;
    }

    public Hand getKickers() {
        return this.kickers;
    }

    @Override
    public String toString() {
        return "#" + getId() + "\t" + getHand();
    }

    @Override
    public int compareTo(Player player) {
        int out = this.getHand().compareTo(player.getHand());
        //System.out.println(this.hand + "" + player.getHand());
        if (out == 0) {
            // System.out.println("DECISIVE KICKERS");
            //System.out.println(this.getKickers() + "" + player.getKickers());
            out = this.getKickers().compareTo(player.getKickers());
        }
        if (out == 0) {
            System.out.println("TIE");
        }

        //System.out.println(out);
        return out;
    }
}
