package data;

import static businessLogic.DeckFactory.createDealingDeck;
import static businessLogic.DeckFactory.createHand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OnePoker UN
 */
public class Table {

    private final DealingDeck dealingDeck;
    private final Hand tableHand;
    private final List<Player> players;
    private int dealerPos;
    private int pot;
    private boolean tie;

    public Table() {
        //System.out.println("Starting new round");
        players = new ArrayList<>();
        tableHand = createHand("array");
        dealingDeck = createDealingDeck("dealingdeck");
        dealerPos = 1;
        for (int i = 0; i < 5; i++) {
            players.add(new Player());

            this.pot = 0;
        }
    }

    public void addToPot(int bet) {
        this.setPot(this.getPot() + bet);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
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

    public Table(Table previousRound) {
        this.players = previousRound.getPlayers();
        this.pot = previousRound.getPot();
        this.dealingDeck = createDealingDeck("array");
        this.tableHand = createHand("array");
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

    /**
     * @return the pot
     */
    public int getPot() {
        return pot;
    }

    /**
     * @param pot the pot to set
     */
    public void setPot(int pot) {
        this.pot = pot;
    }

    @Override
    public String toString() {
        String out = "";
        out += "Players: ";
        for (Player plyr : players) {
            out += "" + plyr + "|";
        }
        out += "\nPot: " + getPot();
        return out;
    }

    /**
     * @return the dealerPos
     */
    public int getDealerPos() {
        return dealerPos;
    }

    /**
     * @param dealerPos the dealerPos to set
     */
    public void setDealerPos(int dealerPos) {
        this.dealerPos = dealerPos;
    }
}
