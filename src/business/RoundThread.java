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
import data.Seat;
import data.Table;
import java.util.Arrays;
import ui.UISwing;

/**
 *
 * @author Miguel Angel
 */
public class RoundThread extends Thread {

    private Table table;
    String msg;
    int checkCount;
    int j;
    private String status;
    private int menu;
    private boolean ended;

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    public RoundThread() {
        ended = false;
        table = new Table();
        status = "";
    }

    public void getPlayerOption(Seat seat) {
        if (!seat.isOccupied()) {
            return;
        }
        Player plyr = seat.getPlayer();
        if (plyr.getCredits() <= 0) {
            if (!seat.isAllin()) {
                seat.unSeatPlayer();
                ui.printMsg("Player " + plyr.getId() + " Retires...\n");
            } else {
                ui.printMsg("Player " + plyr.getId() + " Is all in...\n");
            }
            return;
        }
        if (plyr.isHumanPlayer()) {
            roundMenu();
            return;
        }
        int handValue = holdCardsValue(plyr.getHand());
        if (handValue <= 26 && table.getMinBet() > plyr.getCredits()) {
            fold(seat);
        } else if (all(plyr, table) && handValue > 26) {
            allIn(seat);
        } else if (raise(plyr, table) && handValue > 14) {
            raiseBet(seat, plyr.getCredits() / 10 + table.getMinBet());
        } else if (call(plyr, table) && handValue >= 4) {
            callBet(seat);
        } else {
            fold(seat);
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
        table.getPlayer(0).setName("User");
        for (int i = 1; i < table.getPlayersSize(); i++) {
            table.getPlayer(i).setName("Player " + i);
        }
        dealToPlayers(table);
        ui.printMsg("Dealt hold cards\n");
        ui.printTable(table,status + "\n");

        
        this.setStatus("Pre - Flop");
        preFlop();
        ui.printTable(table,status + "\n");

        
        for (int i = 0; i < 3; i++) {
            table.setOpenBet(false);
            switch (i) {
                case 0: {
                    this.setStatus("The Flop");
                    deal(dealingDeck, tableHand, 3);
                    break;
                }
                case 1: {
                    this.setStatus("The Turn");
                    deal(dealingDeck, tableHand, 1);
                    break;
                }
                case 2: {
                    this.setStatus("The River");
                    deal(dealingDeck, tableHand, 1);
                    break;
                }
            }
            ui.printTable(table,status + "\n");
            //bettings
            msg = "Your turn!";
            for (j = 0; j < 8; j++) {
                if (!table.getSeat(i).isFolded()) {
                    getPlayerOption(table.getSeat(j));
                }
            }
            if (checkCount > 0 && table.isOpenBet()) {
                msg = "Betting round was opened, you must bet!";
                for (j = 0; j < 8; j++) {
                    if (table.getSeat(j).isCheck()) {
                        getPlayerOption(table.getSeat(j));
                        table.getSeat(j).setCheck(false);
                    }
                }
            }
            checkCount = 0;
        }
        GameEngine.getInstance().notifyWinner();
    }

    public void preFlop() {
        ui.printMsg("Preflop:");
        ui.printMsg("Big blind and small blind have forced bets");
        int pos = table.getDealerPos();
        addBet(table.getSeat(pos), table.getMinBet() / 2);
        addBet(table.getSeat(pos + 1), table.getMinBet());
        for (int i = pos + 2; i < 8; i++) {
            if (table.getSeats()[i] != null) {
                getPlayerOption(table.getSeat(i));
            }
        }
    }

    public void checkBet(Seat seat) {
        if (table.isOpenBet()) {
            ui.printError(new IllegalArgumentException("Cannot check when betting round is open"));
            menu = 0;
            return;
        }
        checkCount++;
        seat.setCheck(true);
        ui.printMsg("Player " + seat.getPlayer().getId() + " checks...\n");
    }

    public void addBet(Seat seat, int bet) {
        Player plyr = seat.getPlayer();
        table.setOpenBet(true);
        ui.printMsg(plyr.getName() + " adds " + bet + " to the pot!!\n");
        if (bet > plyr.getCredits()) {
            allIn(seat);
        }
        plyr.setCredits(plyr.getCredits() - bet);
        table.addToPot(bet);
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
        }
    }

    public void callBet(Seat seat) {
        Player plyr = seat.getPlayer();
        if (plyr.getCredits() > table.getMinBet()) {
            ui.printMsg(plyr.getName() + " Calls\n");
            addBet(seat, table.getMinBet());
        } else {
            allIn(seat);
        }
    }

    /**
     * Assumes bet to be raised higher than minbet of table
     *
     * @param seat
     * @param bet
     */
    public void raiseBet(Seat seat, int bet) {
        ui.printMsg(seat.getPlayer().getName() + " raises " + (bet - table.getMinBet()) + "\n");
        table.setMinBet(bet);
        addBet(seat, bet);
    }

    public void fold(Seat seat) {
        Player plyr = seat.getPlayer();
        plyr.setElo(plyr.getElo() - 10);
        seat.setFolded(true);
        ui.printMsg(plyr.getName() + " folds\n");
        j--;
    }

    public void allIn(Seat seat) {
        Player plyr = seat.getPlayer();
        ui.printMsg(plyr.getName() + " goes all in!!\n");
        seat.setAllin(true);
        table.setMinBet(plyr.getCredits() + table.getMinBet());
        addBet(seat, plyr.getCredits());
    }

    public void roundMenu() {

        String[] options = {"Check", "Call", "Raise", "Fold", "All in", "Retire"};
        if (table.isOpenBet()) {
            options = Arrays.copyOfRange(options, 1, 6);
        }
        menu = 0;

        if (ui instanceof UISwing) {
            ui.notifyRoundMenu();
            while (menu == 0) {
                synchronized (ui) {
                    try {
                        ui.wait();
                    } catch (InterruptedException ex) {
                    }
                }
            }
        } else {
            ui.printMenuOption("Round Menu", msg, options);
            ui.notifyRoundMenu();
        }

        if (table.isOpenBet()) {
            menu++;
        }
        switch (menu) {
            case 1: {
                //Check is equivalent to passing
                checkBet(table.getSeat(0));
                break;
            }
            case 2: {
                callBet(table.getSeat(0));
                break;
            }
            case 3: {
                int raise = ui.askInt("Raise: ");
                if (raise < table.getMinBet()) {
                    ui.printError(new IllegalArgumentException("Raise must be higher than minimum bet"));
                    menu = 0;
                }
                raiseBet(table.getSeat(0), raise);
                break;
            }
            case 4: {
                fold(table.getSeat(0));
                break;
            }
            case 5: {
                allIn(table.getSeat(0));
                break;
            }
            case 6: {
                GameEngine.getInstance().checkCommand("Exit", true);
                break;
            }
            default: {
                menu = 0;
                ui.printError(new IllegalArgumentException("Not a valid command", null));
                break;
            }

        }
    }

    @Override
    public void run() {
        playRound();
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the menu
     */
    public int getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(int menu) {
        this.menu = menu;
    }

    /**
     * @return the ended
     */
    public boolean isEnded() {
        return ended;
    }
}
