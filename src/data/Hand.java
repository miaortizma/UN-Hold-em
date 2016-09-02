/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class Hand extends Deck {

    private Player player;

    public Hand(String name) {
        super(name);
    }

    public Hand(String name, Player player) {
        super(name);
        this.player = player;
    }

}
