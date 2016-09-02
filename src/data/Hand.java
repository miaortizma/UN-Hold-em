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

    private String rank;
    private int rankLevel;
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

    /**
     * @return the rankLevel
     */
    public int getRankLevel() {
        return rankLevel;
    }

    /**
     * @param rankLevel the rankLevel to set
     */
    public void setRankLevel(int rankLevel) {
        this.rankLevel = rankLevel;
    }
    
    

}
