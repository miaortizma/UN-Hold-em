package data;

import static businessLogic.DeckFactory.createDealingDeck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author OnePoker UN
 */
public class Round {

    private DealingDeck dealingDeck;
    private Hand tableHand;
    private List<Player> players;
    private HashMap<Player, Integer> position;
    private HashMap<Player, Integer> bets;
    private int pot;
    private boolean tie;

    public Round() {
        //System.out.println("Starting new round");
        players = new ArrayList<>();
        tableHand = new Hand("array");
        dealingDeck = createDealingDeck("dealingdeck");
        bets = new HashMap<>();
        position = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            players.add(new Player());
            System.out.println(players.get(i));
            position.put(players.get(i), i);
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

    public Player getPlayer(int i) {
        return players.get(i);
    }

    public Hand getPlayerHand(int i) {
        return players.get(i).getHand();
    }

    public Round(Round previousRound) {
        //stub
        //Crear ronda "Heredando" los valores de una ronda anterior
        //Permitiria guardar en memoria rondas pasadas 
    }

    /**
     * @return the tie
     */
    public boolean isTie() {
        return tie;
    }

    /**
     * @param tie the tie to set
     */
    public void setTie(boolean tie) {
        this.tie = tie;
    }
}
