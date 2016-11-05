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

import static business.AI.all;
import static business.AI.call;
import static business.AI.holdCardsValue;
import static business.AI.raise;
import static business.DeckHelper.deal;
import static business.DeckHelper.dealCard;
import static business.DeckHelper.dealToPlayers;
import static business.GameEngine.ui;
import static business.HandHelper.bestHand;
import data.Deck;
import data.Hand;
import data.Player;
import data.Table;
import java.util.Collections;
import ui.UISwing;

/**
 *
 * @author Miguel Angel
 */
public class RoundThread extends Thread {

    private static Table table;

    public RoundThread() {
        table = new Table();
    }

    public static void getPlayerOption(Player plyr) {
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
    public static void playRound() {
        Deck dealingDeck = table.getDealingDeck();
        Hand tableHand = table.getTableHand();
        dealCard(dealingDeck);
        table.getPlayer(0).setHumanPlayer(true);
        dealToPlayers(table);
        ui.printMsg("Dealt hold cards(You are player " + table.getPlayer(0).getId() + ")");
        ui.printUser(table);

        preFlop();
        for (int i = 0; i < 3; i++) {
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
            for (int j = 0; j < 8; j++) {
                if (table.getSeats()[j] != null) {
                    getPlayerOption(table.getSeats()[j]);
                }
            }
        }
        int winners = dealRewards();
        ui.printRoundStandings(table, winners);
        ui.printStandings(table);
        table.setPot(0);
    }

    public static void preFlop() {
        ui.printMsg("Big blind and small blind open the betting round (Forced bets)");
        addBet(table.getPlayer(0), table.getMinBet() / 2);
        addBet(table.getPlayer(1), table.getMinBet());
    }

    public static void addBet(Player plyr, int bet) {
        ui.printMsg("Player " + plyr.getId() + " adds " + bet + " to the pot!!\n");
        if (bet > plyr.getCredits()) {
            allIn(plyr);
            //throw new IllegalArgumentException("Bet is higher than player credits");
        }
        plyr.setCredits(plyr.getCredits() - bet);
        table.addToPot(bet);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
    }

    public static void callBet(Player plyr) {
        if (plyr.getCredits() > table.getMinBet()) {
            ui.printMsg("Player " + plyr.getId() + " Calls");
            addBet(plyr, table.getMinBet());
        } else {
            allIn(plyr);
        }
    }

    public static void raiseBet(Player plyr, int bet) {
        if (bet <= table.getMinBet()) {
            throw new IllegalArgumentException("Raise must be higher than minimum bet");
        } else {
            ui.printMsg("Player " + plyr.getId() + " raises " + (bet - table.getMinBet()));
            table.setMinBet(bet);
            addBet(plyr, bet);
        }
    }

    public static void fold(Player plyr) {
        plyr.setElo(plyr.getElo() - 10);
        table.removePlayer(plyr);
        ui.printMsg("Player " + plyr.getId() + " folds\n");
    }

    public static void allIn(Player plyr) {
        ui.printMsg("Player " + plyr.getId() + " goes all in!!");
        plyr.setAllIn(true);
        table.setMinBet(plyr.getCredits() + table.getMinBet());
        addBet(plyr, plyr.getCredits());
    }

    /**
     * NOTES/ TO DO: -Should add some variable to notify that there is a tie
     * -Should add different method that controls who wins etc. sets each table
     * player hand as its possible Hand after merging its hand and the
     * comunitary hand, sets each player kickers.
     *
     * Sorts the list of players (The criteria is their hands and kickers)
     *
     * @param table
     */
    public static void compareHands(Table table) {
        for (Player plyr : table.getPlayers()) {
            plyr.setHand(bestHand(plyr.getHand(), table.getTableHand()));
        }
        Collections.sort(table.getPlayers(), Collections.reverseOrder());
    }

    /**
     * Assumes players is ordered from best to worst
     *
     * Returns the numbers of players who won e.g 2 means tie
     */
    public static int dealRewards() {
        int playersSize = table.getPlayersSize();
        if (!(playersSize == 1)) {
            compareHands(table);
        }
        if (table.getPlayersSize() == 1) {
            Player winner = table.getPlayer(0);
            winner.setCredits(winner.getCredits() + table.getPot());
            winner.setElo(winner.getElo() + 25);
            for (int i = 1; i < table.getPlayersSize(); i++) {
                table.getPlayer(i).setElo(table.getPlayer(i).getElo() - 5);
            }
            table.setPot(0);
            return 1;
        }
        if (table.getPlayer(1).compareTo(table.getPlayer(0)) == 0) {
            Player tie1 = table.getPlayer(0);
            Player tie2 = table.getPlayer(1);
            tie1.setCredits(tie1.getCredits() + table.getPot() / 2);
            tie2.setCredits(tie2.getCredits() + table.getPot() / 2);
            tie1.setElo(tie1.getElo() + 10);
            tie2.setElo(tie2.getElo() + 10);
            for (int i = 2; i < table.getPlayersSize(); i++) {
                table.getPlayer(i).setElo(table.getPlayer(i).getElo() - 5);
            }
            return 2;
        } else {
            Player winner = table.getPlayer(0);
            winner.setCredits(winner.getCredits() + table.getPot());
            winner.setElo(winner.getElo() + 25);
            for (int i = 1; i < table.getPlayersSize(); i++) {
                table.getPlayer(i).setElo(table.getPlayer(i).getElo() - 5);
            }
            return 1;
        }
    }

    public static void roundMenu() {

        int menu = ui.askRoundMenu();

        switch (menu) {
            case 1: {
                callBet(table.getPlayer(0));
                break;
            }
            case 2: {
                int raise = ui.askInt("Raise: ");
                raiseBet(table.getPlayer(0), raise);
                break;
            }
            case 3: {
                fold(table.getPlayer(0));
                break;
            }
            case 4: {
                allIn(table.getPlayer(0));
                break;
            }
            case 5: {
                GameEngine.checkCommand("Exit", true);
            }
            default: {
                throw new IllegalArgumentException("Not a valid command", null);
            }
        }
    }

    @Override
    public void run() {
        playRound();
    }
}
