/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static businessLogic.DeckFactory.createDeck;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OnePoker UN &
 */
public class Round {

    private DealingDeck dealingDeck;
    private Hand tableHand;
    private List<Player> players;
    private int pot;

    public Round() {
        //System.out.println("Starting new round");
        players = new ArrayList<>();
        tableHand = new Hand("Table deck");
        dealingDeck = createDeck("dealingDeck");

        for (int i = 0; i < 5; i++) {
            players.add(new Player());
        }
        this.pot = 0;
    }

    public void addToPot(int bet) {
        this.pot += bet;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getPlayersSize() {
        return players.size();
    }

    public DealingDeck getDealingDeck() {
        return dealingDeck;
    }

    public Hand getTableHand() {
        return tableHand;
    }

    public Round(Round previousRound) {
        //stub
        //Crear ronda "Heredando" los valores de una ronda anterior
        //Permitiria guardar en memoria rondas pasadas 
    }
}
