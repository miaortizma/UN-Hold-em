/*
 * The MIT License
 *
 * Copyright 2016 Miguel Angel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package business;

import static business.AI.*;
import static business.DeckHelper.*;
import static business.GameEngine.ui;
import data.Deck;
import data.Hand;
import data.Player;
import data.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Angel
 */
public class RoundThread extends Thread {

    private Table table;
    private List<Integer> checkPlayers = new ArrayList<>();
    private List<Integer> raisePlayers = new ArrayList<>();
    String msg;
    int j;

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    public RoundThread() {
        table = new Table();
    }

    public void getPlayerOption(Player plyr) {
        if (plyr.getCredits() <= 0) {
            if (!plyr.isAllIn()) {
                for (int i = 0; i < 8; i++) {
                    if (plyr == table.getSeats()[i]) {
                        table.getSeats()[i] = null;
                        table.removePlayer(plyr);
                    }
                }
                ui.printMsg("Player " + plyr.getId() + " Retires...");
            } else {
                ui.printMsg("Player " + plyr.getId() + " Is all in...");
                return;
            }
        } else if (plyr.isHumanPlayer()) {
            roundMenu();
            return;
        }
        int handValue = holdCardsValue(plyr.getHand());
        if (handValue <= 26 && table.getMinBet() > plyr.getCredits()) {
            fold(plyr);
        } else if (all(plyr, table) && handValue > 26) {
            allIn(plyr);
        } else if (raise(plyr, table) && handValue > 14) {
            raiseBet(plyr, plyr.getCredits() / 10 + table.getMinBet());
        } else if (call(plyr, table) && handValue >= 4) {
            callBet(plyr);
        } else {
            fold(plyr);
        }
    }

    /**
     * NOTES / TO DO Goes through: -Dealing. -Burn Card -Shows player(0) his
     * cards -Flop,turn and river. (Comunitary hand) -Comparing player hands
     * (Doesn't do nothing about ties and also note that in the way that
     * collections sort to print the players they are sorted in reverse order)
     *
     */
    public void playRound() {
        Deck dealingDeck = table.getDealingDeck();
        Hand tableHand = table.getTableHand();
        dealCard(dealingDeck);
        table.getPlayer(0).setHumanPlayer(true);
        dealToPlayers(table);
        ui.printMsg("Dealt hold cards(You are player " + table.getPlayer(0).getId() + ")");
        ui.printUser(table);

        preFlop();
        for (int i = 0; i < 3; i++) {
            table.setOpenBet(false);
            switch (i) {
                case 0: {
                    ui.printMsg("The flop:");
                    deal(dealingDeck, tableHand, 3);
                    ui.printMsg(table.getTableHand() + "\n");
                    break;
                }
                case 1: {
                    ui.printMsg("The turn:");
                    deal(dealingDeck, tableHand, 1);
                    ui.printMsg(table.getTableHand() + "\n");
                    break;
                }
                case 2: {
                    ui.printMsg("The river:");
                    deal(dealingDeck, tableHand, 1);
                    ui.printMsg(table.getTableHand() + "\n");
                    break;
                }
            }
            ui.printUser(table);
            //bettings
            msg = "Your turn!";
            for (j = 0; j < table.getPlayersSize(); j++) {
                getPlayerOption(table.getPlayer(j));

            }
            if (checkPlayers.size() > 0 && table.isOpenBet()) {
                msg = "Betting round was opened, you must bet!";
                for (j = 0; j < checkPlayers.size(); j++) {
                    getPlayerOption(table.getSeats()[j]);
                }
            }
            checkPlayers.clear();
        }
        GameEngine.getInstance(null).notifyWinner();
    }

    public void preFlop() {
        ui.printMsg("Preflop:");
        ui.printMsg("Big blind and small blind have forced bets");
        int pos = table.getDealerPos();
        addBet(table.getPlayer(pos), table.getMinBet() / 2);
        addBet(table.getPlayer(pos + 1), table.getMinBet());
        for (int i = pos + 2; i < 8; i++) {
            if (table.getSeats()[i] != null) {
                getPlayerOption(table.getSeats()[i]);
            }
        }
    }

    private void checkBet(Player player) {
        checkPlayers.add(player.getPositon());
        ui.printMsg("Player " + player.getId() + " checks...\n");
    }

    public void addBet(Player plyr, int bet) {
        table.setOpenBet(true);
        ui.printMsg("Player " + plyr.getId() + " adds " + bet + " to the pot!!\n");
        if (bet > plyr.getCredits()) {
            allIn(plyr);
        }
        plyr.setCredits(plyr.getCredits() - bet);
        table.addToPot(bet);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
    }

    public void callBet(Player plyr) {
        if (plyr.getCredits() > table.getMinBet()) {
            ui.printMsg("Player " + plyr.getId() + " Calls");
            addBet(plyr, table.getMinBet());
        } else {
            allIn(plyr);
        }
    }

    /**
     * Assumes bet to be raised higher than minbet of table
     *
     * @param plyr
     * @param bet
     */
    public void raiseBet(Player plyr, int bet) {
        raisePlayers.add(plyr.getPositon());
        ui.printMsg("Player " + plyr.getId() + " raises " + (bet - table.getMinBet()));
        table.setMinBet(bet);
        addBet(plyr, bet);
    }

    public void fold(Player plyr) {
        plyr.setElo(plyr.getElo() - 10);
        table.removePlayer(plyr);
        ui.printMsg("Player " + plyr.getId() + " folds\n");
        j--;
    }

    public void allIn(Player plyr) {
        ui.printMsg("Player " + plyr.getId() + " goes all in!!");
        plyr.setAllIn(true);
        table.setMinBet(plyr.getCredits() + table.getMinBet());
        addBet(plyr, plyr.getCredits());
    }

    public void roundMenu() {
        String[] options = {"Check", "Call", "Raise", "Fold", "All in", "Retire"};
        int menu = 0;
        while (menu == 0) {
            menu = ui.askMenuOption("Round Menu", msg, options);
            switch (menu) {
                case 1: {
                    if (table.isOpenBet()) {
                        ui.printError(new IllegalArgumentException("Cannot check when betting round is open"));
                        menu = 0;
                        break;
                    }
                    //Check is equivalent to passing
                    checkBet(table.getPlayer(0));
                    break;
                }
                case 2: {
                    callBet(table.getPlayer(0));
                    break;
                }
                case 3: {
                    int raise = ui.askInt("Raise: ");
                    if (raise < table.getMinBet()) {
                        ui.printError(new IllegalArgumentException("Raise must be higher than minimum bet"));
                        menu = 0;
                    }
                    raiseBet(table.getPlayer(0), raise);
                    break;
                }
                case 4: {
                    fold(table.getPlayer(0));
                    break;
                }
                case 5: {
                    allIn(table.getPlayer(0));
                    break;
                }
                case 6: {
                    GameEngine.getInstance(null).checkCommand("Exit", true);
                    break;
                }
                default: {
                    menu = 0;
                    ui.printError(new IllegalArgumentException("Not a valid command", null));
                    break;
                }
            }
        }
    }

    @Override
    public void run() {
        playRound();
    }
}
