/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author OnePoker UN  & 
 */
public class Player {

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

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public Deck getDeck() {
        return this.hand;
    }

    public Player() {
        this.id = +count;
        hand = new Hand("Player " + this.id + " Deck");
        System.out.println("Creating player with id: " + this.id);
        count++;
    }

    public int getId() {
        return this.id;
    }
}
