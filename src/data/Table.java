package data;

import static business.DeckFactory.createDealingDeck;
import static business.DeckFactory.createHand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OnePoker UN
 */
public class Table {
    
    private final Deck dealingDeck;
    private final Hand tableHand;
    private final List<Player> players;
    private final Player[] Seats;
    private int dealerPos;
    private int pot;
    private int minBet;
    private boolean openBet;
    
    public Table() {
        players = new ArrayList<>();
        tableHand = createHand("array");
        dealingDeck = createDealingDeck();
        dealerPos = 0;
        minBet = 50;
        pot = 0;
        Seats = new Player[8];
        for (int i = 0; i < 5; i++) {
            players.add(new Player());
            Seats[i] = players.get(i);
            players.get(i).setPositon(i);
        }
        openBet = false;
    }
    
    public Table(Table oldTable) {
        this.Seats = oldTable.getSeats();
        this.players = new ArrayList<>();
        for (int i = 0; i < oldTable.getSeats().length; i++) {
            if (oldTable.getSeats()[i] != null) {
                players.add(new Player(oldTable.getSeats()[i]));
                Seats[i] = players.get(players.size() - 1);
            }
        }
        this.dealingDeck = createDealingDeck();
        this.tableHand = createHand("array");
        this.dealerPos = 0;
        this.minBet = 50;
        this.pot = 0;
        this.openBet = false;
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
    
    public Deck getDealingDeck() {
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

    /**
     * @return the minBet
     */
    public int getMinBet() {
        return minBet;
    }

    /**
     * @param minBet the minBet to set
     */
    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    /**
     * @return the Seats
     */
    public Player[] getSeats() {
        return Seats;
    }

    /**
     * @return the openBet
     */
    public boolean isOpenBet() {
        return openBet;
    }

    /**
     * @param openBet the openBet to set
     */
    public void setOpenBet(boolean openBet) {
        this.openBet = openBet;
    }
}
