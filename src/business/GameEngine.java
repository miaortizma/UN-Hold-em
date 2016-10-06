package business;

import static business.AI.all;
import static business.AI.raise;
import static business.DeckHelper.deal;
import static business.DeckHelper.dealCard;
import static business.DeckHelper.dealToPlayers;
import static business.HandHelper.*;
import tests.HandAnalyserTest;
import data.*;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ui.UI.*;
import static business.AI.call;

/**
 *
 *
 * @author OnePoker UN
 */
public class GameEngine {

    public static Random RND;
    private static GameEngine instance;
    private static Table table;
    private static int handCount;
    private static int j;

    /**
     * @return the handCount
     */
    public static int getHandCount() {
        return handCount;
    }

    /**
     * @param aHandCount the handCount to set
     */
    public static void setHandCount(int aHandCount) {
        handCount = aHandCount;
    }

    /**
     * @return the table
     */
    public static Table getTable() {
        return table;
    }

    private GameEngine() {

        Random RND = new Random();
    }

    private static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        setHandCount(0);
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        getInstance();
        startGame();
    }

    public static void startGame() {
        int menu = 0;
        printWelcome();
        while (true) {
            try {
                printMainMenu(getTable());
                menu = askInt("\nOption: ");
                if (menu < 1 || menu > 4) {
                    throw new IllegalArgumentException("Not a menu option");
                }
                switch (menu) {
                    case 1: {
                        if (getTable() == null) {
                            table = new Table();
                        } else {
                            table = new Table(getTable());
                        }
                        setHandCount(getHandCount() + 1);
                        playRound();
                        break;
                    }
                    case 2: {
                        printHelp();
                        break;
                    }
                    case 3: {
                        printCommands();
                        break;
                    }
                    case 4: {
                        checkCommand("Exit", true);
                    }
                    default: {
                        throw new IllegalArgumentException("Not a valid command");
                    }

                }
            } catch (Exception ex) {
                printError(ex);
            }
        }
    }

    private static void tests() {
        HandAnalyserTest.test();
    }

    /**
     * Checks if input is a command
     *
     * @param input the input to be tested
     * @param print sometimes commands are called by system internals, so there
     * is no need to print commands(Subject to change)
     * @return true if input is a command, false otherwise
     */
    public static boolean checkCommand(String input, boolean print) {
        switch (input) {
            case "Exit": {
                if (print) {
                    printExit();
                }
                System.exit(0);
            }
            case "Info": {
                if (print) {
                    printInfo();
                }
                return true;
            }
            case "Help": {
                if (print) {
                    printHelp();
                }
                return true;
            }
            case "Test": {
                HandAnalyserTest.test();
                return true;
            }
            case "Hands": {
                printHands();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    /**
     * Adds bet to table pot
     *
     * @param plyr
     * @param bet
     */
    public static void addBet(Player plyr, int bet) {
        printMsg("Player " + plyr.getId() + " adds " + bet + " to the pot!!\n");
        if (bet > plyr.getCredits()) {
            allIn(plyr);
            //throw new IllegalArgumentException("Bet is higher than player credits");
        }
        plyr.setCredits(plyr.getCredits() - bet);
        getTable().addToPot(bet);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void callBet(Player plyr) {
        if (plyr.getCredits() > getTable().getMinBet()) {
            printMsg("Player " + plyr.getId() + " Calls");
            addBet(plyr, getTable().getMinBet());
        } else {
            allIn(plyr);
        }
    }

    public static void raiseBet(Player plyr, int bet) {
        if (bet <= getTable().getMinBet()) {
            throw new IllegalArgumentException("Raise must be higher than minimum bet");
        } else {
            printMsg("Player " + plyr.getId() + " raises " + (bet - getTable().getMinBet()));
            getTable().setMinBet(bet);
            addBet(plyr, bet);
        }
    }

    public static void fold(Player plyr) {
        plyr.setElo(plyr.getElo() - 10);
        getTable().removePlayer(plyr);
        j--;
        printMsg("Player " + plyr.getId() + " folds\n");
    }

    public static void allIn(Player plyr) {
        printMsg("Player " + plyr.getId() + " goes all in!!");
        plyr.setAllIn(true);
        getTable().setMinBet(plyr.getCredits() + getTable().getMinBet());
        addBet(plyr, plyr.getCredits());
    }

    public static void roundMenu() {
        int menu = 0;
        while (menu == 0) {
            try {
                printRoundMenu();
                menu = askInt("Option: ");
                if (menu < 1 || menu > 5) {
                    menu = 0;
                    throw new IllegalArgumentException("Not a menu option");
                }
                switch (menu) {
                    case 1: {
                        callBet(getTable().getPlayer(0));
                        break;
                    }
                    case 2: {
                        int raise = 0;
                        while (raise == 0) {
                            try {
                                raise = askInt("Raise: ");
                                raiseBet(getTable().getPlayer(0), raise);
                            } catch (Exception ex) {
                                raise = 0;
                                printError(ex);
                            }
                        }
                        break;
                    }
                    case 3: {
                        fold(getTable().getPlayer(0));
                        break;
                    }
                    case 4: {
                        allIn(getTable().getPlayer(0));
                        break;
                    }
                    case 5: {
                        checkCommand("Exit", true);
                    }
                    default: {
                        throw new IllegalArgumentException("Not a valid command", null);
                    }

                }
            } catch (Exception ex) {
                printError(ex);
            }
        }
    }

    public static void preFlop() {
        printMsg("Big blind and small blind open the betting round (Forced bets)");
        addBet(getTable().getPlayer(0), getTable().getMinBet() / 2);
        addBet(getTable().getPlayer(1), getTable().getMinBet());
    }

    public static int holdCardsValue(Hand hand) {
        int[] rank = hand.getCardRanks();
        int[] suit = hand.getCardSuits();
        int value = rank[0] + rank[1];
        if (suit[0] == suit[1]) {
            value += 3;
        }
        if (rank[1] - rank[0] == 2 || rank[0] - rank[1] == 2) {
            value += 2;
        }
        if (rank[1] - rank[0] == 1 || rank[0] - rank[1] == 1) {
            value += 3;
        }
        if (rank[1] - rank[0] == 0 || rank[0] - rank[1] == 0) {
            value += 3;
        }
        return value;
    }

    public static void getPlayerOption(Player plyr) {
        if (plyr.getCredits() <= 0) {
            if (!plyr.isAllIn()) {
                for (int i = 0; i < 8; i++) {
                    if (plyr == getTable().getSeats()[i]) {
                        table.getSeats()[i] = null;
                        getTable().removePlayer(plyr);
                    }
                }
                printMsg("Player " + plyr.getId() + " Retires...");
            } else {
                printMsg("Player " + plyr.getId() + " Is all in...");
            }

        } else if (plyr.isHumanPlayer()) {
            roundMenu();
            return;
        }
        int handValue = holdCardsValue(plyr.getHand());
        if (handValue <= 26 && getTable().getMinBet() > plyr.getCredits()) {
            fold(plyr);
        } else if (all(plyr, getTable()) && handValue > 26) {
            allIn(plyr);
        } else if (raise(plyr, getTable()) && handValue > 14) {
            raiseBet(plyr, plyr.getCredits() / 10 + getTable().getMinBet());
        } else if (call(plyr, getTable()) && handValue >= 4) {
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
        Deck dealingDeck = getTable().getDealingDeck();
        Hand tableHand = getTable().getTableHand();
        dealCard(dealingDeck);
        getTable().getPlayer(0).setHumanPlayer(true);
        dealToPlayers(getTable());
        printMsg("Dealt hold cards(You are player " + getTable().getPlayer(0).getId() + ")");
        printUser(getTable());
        preFlop();
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0: {
                    printMsg("The flop:");
                    deal(dealingDeck, tableHand, 3);
                    printMsg(getTable().getTableHand() + "\n");
                    break;
                }
                case 1: {
                    printMsg("The turn:");
                    deal(dealingDeck, tableHand, 1);
                    printMsg(getTable().getTableHand() + "\n");
                    break;
                }
                case 2: {
                    printMsg("The river:");
                    deal(dealingDeck, tableHand, 1);
                    printMsg(getTable().getTableHand() + "\n");
                    break;
                }
            }
            printUser(getTable());
            if (getTable().getPlayersSize() == 1) {
                break;
            }
            for (j = 0; j < getTable().getPlayersSize(); j++) {
                if (getTable().getSeats()[j].isAllIn()) {
                    printMsg("Player " + getTable().getSeats()[j].getId() + " is all in...\n");
                } else {
                    getPlayerOption(getTable().getPlayer(j));
                }
            }
        }
        dealRewards();
        printStandings(getTable());
        if (getTable().getSeats()[0].getCredits() == 0) {
            printMsg("You ran out of credits!!, better luck next time");
            checkCommand("Exit", true);
        }
        int count = 0;
        Player X = null;
        for (int i = 0; i < 8; i++) {
            if (table.getSeats()[i] != null) {
                count++;
            }else{
            X = table.getSeats()[i];
            }
        }
        if (count == 1) {
            printMsg("Player " + X.getId() +  "wins ");
            checkCommand("Exit", true);
            
        }
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
     *
     */
    public static void dealRewards() {
        if (!(table.getPlayersSize() == 1)) {
            compareHands(getTable());
        }
        printRoundStandings(getTable());
        if (getTable().getPlayersSize() == 1) {
            Player winner = getTable().getPlayer(0);
            winner.setCredits(winner.getCredits() + getTable().getPot());
            winner.setElo(winner.getElo() + 25);
            for (int i = 1; i < getTable().getPlayersSize(); i++) {
                getTable().getPlayer(i).setElo(getTable().getPlayer(i).getElo() - 5);
            }
            getTable().setPot(0);
            return;
        }
        if (getTable().getPlayer(1).compareTo(getTable().getPlayer(0)) == 0) {
            Player tie1 = getTable().getPlayer(0);
            Player tie2 = getTable().getPlayer(1);
            tie1.setCredits(tie1.getCredits() + getTable().getPot() / 2);
            tie2.setCredits(tie2.getCredits() + getTable().getPot() / 2);
            tie1.setElo(tie1.getElo() + 10);
            tie2.setElo(tie2.getElo() + 10);
            for (int i = 2; i < getTable().getPlayersSize(); i++) {
                getTable().getPlayer(i).setElo(getTable().getPlayer(i).getElo() - 5);
            }
        } else {
            Player winner = getTable().getPlayer(0);
            winner.setCredits(winner.getCredits() + getTable().getPot());
            winner.setElo(winner.getElo() + 25);
            for (int i = 1; i < getTable().getPlayersSize(); i++) {
                getTable().getPlayer(i).setElo(getTable().getPlayer(i).getElo() - 5);
            }
        }
        getTable().setPot(0);
    }
}
