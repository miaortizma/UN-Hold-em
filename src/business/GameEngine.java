package business;

import static business.AI.all;
import static business.AI.call;
import static business.AI.raise;
import static business.DeckHelper.deal;
import static business.DeckHelper.dealCard;
import static business.DeckHelper.dealToPlayers;
import static business.HandHelper.*;
import tests.HandAnalyserTest;
import data.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.UI;
import ui.UISwing;
import ui.UIText;

/**
 *
 *
 * @author OnePoker UN
 */
public class GameEngine {

    public static Random RND;
    private static GameEngine instance;
    private static Table table;
    private static UI ui;
    private static int handCount;
    private static int j;
    private static PrintStream sout;
    private static ByteArrayOutputStream baos;

    private static void selectUI(String[] args) {
        if (args.length == 0) {
            ui = new UISwing();
        } else if (args[0].equals("text")) {
            ui = new UIText();
        } else {
            ui = new UISwing();
        }
    }

    private static int getHandCount() {
        return handCount;
    }

    private static void setHandCount(int i) {
        handCount = i;
    }

    private GameEngine() {
        Random RND = new Random();
    }

    private static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        selectUI(args);
        getInstance();
        startProgram();
    }

    public static void newRound() {
        if (table == null) {
            table = new Table();
        } else {
            table = new Table(table);
        }
        setHandCount(getHandCount() + 1);
        playRound();
    }

    public static void startProgram() {
        int menu = 0;
        ui.printWelcome();
        if (ui instanceof UISwing) {
            //do nothing
        } else {
            while (true) {
                try {
                    ui.printMainMenu(table);
                    menu = ui.AskMenuOption();
                    if (menu < 1 || menu > 4) {
                        throw new IllegalArgumentException("Not a menu option");
                    }
                    switch (menu) {
                        case 1: {
                            newRound();
                        }
                        case 2: {
                            ui.printHelp();
                            break;
                        }
                        case 3: {
                            ui.printCommands();
                            break;
                        }
                        case 4: {
                            checkCommand("<Exit>", true);
                        }
                        default: {
                            throw new IllegalArgumentException("Not a valid command");
                        }

                    }
                } catch (Exception ex) {
                    ui.printError(ex);
                }
            }
        }
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
            case "<Exit>": {
                if (print) {
                    ui.printExit();
                }
                System.exit(0);
            }
            case "<Info>": {
                if (print) {
                    ui.printInfo();
                }
                return true;
            }
            case "<Help>": {
                if (print) {
                    ui.printHelp();
                }
                return true;
            }
            case "<Test>": {
                HandAnalyserTest.test();
                return true;
            }
            case "<Hands>": {
                ui.printHands();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static void addBet(Player plyr, int bet) {
        ui.printMsg("Player " + plyr.getId() + " adds " + bet + " to the pot!!\n");
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
            ui.printMsg("Player " + plyr.getId() + " Calls");
            addBet(plyr, getTable().getMinBet());
        } else {
            allIn(plyr);
        }
    }

    public static void raiseBet(Player plyr, int bet) {
        if (bet <= getTable().getMinBet()) {
            throw new IllegalArgumentException("Raise must be higher than minimum bet");
        } else {
            ui.printMsg("Player " + plyr.getId() + " raises " + (bet - getTable().getMinBet()));
            getTable().setMinBet(bet);
            addBet(plyr, bet);
        }
    }

    public static void fold(Player plyr) {
        plyr.setElo(plyr.getElo() - 10);
        getTable().removePlayer(plyr);
        j--;
        ui.printMsg("Player " + plyr.getId() + " folds\n");
    }

    public static void allIn(Player plyr) {
        ui.printMsg("Player " + plyr.getId() + " goes all in!!");
        plyr.setAllIn(true);
        getTable().setMinBet(plyr.getCredits() + getTable().getMinBet());
        addBet(plyr, plyr.getCredits());
    }

    public static void roundMenu() {
        int menu = 0;
        while (menu == 0) {
            try {
                ui.printRoundMenu();
                menu = ui.askInt("Option: ");
                if (menu < 1 || menu > 5) {
                    throw new IllegalArgumentException("Not a menu option");
                }
                switch (menu) {
                    case 1: {
                        callBet(table.getPlayer(0));
                        break;
                    }
                    case 2: {
                        int raise = 0;
                        while (raise == 0) {
                            try {
                                raise = ui.askInt("Raise: ");
                                raiseBet(table.getPlayer(0), raise);
                            } catch (Exception ex) {
                                raise = 0;
                                ui.printError(ex);
                            }
                        }
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
                        checkCommand("<Exit>", true);
                    }
                    default: {
                        throw new IllegalArgumentException("Not a valid command", null);
                    }

                }
            } catch (Exception ex) {
                ui.printError(ex);
            }
        }
    }

    public static void preFlop() {
        ui.printMsg("Big blind and small blind open the betting round ");
        addBet(table.getPlayer(0), table.getMinBet() / 2);
        addBet(table.getPlayer(1), table.getMinBet());
        table.addToPot(table.getMinBet() + table.getMinBet() / 2);
        fold(table.getPlayer(2));
        raiseBet(table.getPlayer(3), 75);
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
                ui.printMsg("Player " + plyr.getId() + " Retires...");
            } else {
                ui.printMsg("Player " + plyr.getId() + " Is all in...");
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
        getTable().setPot(0);
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
        if (!(table.getPlayersSize() == 1)) {
            compareHands(getTable());
        }
        if (getTable().getPlayersSize() == 1) {
            Player winner = getTable().getPlayer(0);
            winner.setCredits(winner.getCredits() + getTable().getPot());
            winner.setElo(winner.getElo() + 25);
            for (int i = 1; i < getTable().getPlayersSize(); i++) {
                getTable().getPlayer(i).setElo(getTable().getPlayer(i).getElo() - 5);
            }
            getTable().setPot(0);
            return 1;
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
            return 2;
        } else {
            Player winner = getTable().getPlayer(0);
            winner.setCredits(winner.getCredits() + getTable().getPot());
            winner.setElo(winner.getElo() + 25);
            for (int i = 1; i < getTable().getPlayersSize(); i++) {
                getTable().getPlayer(i).setElo(getTable().getPlayer(i).getElo() - 5);
            }
            return 1;
        }
    }

    public static Table getTable() {
        return table;
    }

    public static Object[] redirectSout() {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        sout = System.out;
        System.setOut(ps);
        return new Object[]{sout, baos};
    }

    public static String getSout() {
        System.out.flush();
        System.setOut(sout);
        return baos.toString();
    }

}
