/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static businessLogic.HandAnalyser.handRanks;
import static businessLogic.HandAnalyser.handSuits;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class Hand extends Deck {

    private String rank;
    
    
    public int[] getCardRanks(){
        return handRanks(this);
    
    }
    
    public int[] getCardSuits(){
        return handSuits(this);
    }

    public Hand(String name) {
        super(name);
    }

    /**
     * @return the rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

   

}
