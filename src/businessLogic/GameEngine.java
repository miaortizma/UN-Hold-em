package businessLogic;

import static businessLogic.DeckHelper.deal;
import static businessLogic.DeckHelper.dealCard;
import static businessLogic.DeckHelper.dealToPlayers;
import static businessLogic.HandHelper.*;
import tests.HandAnalyserTest;
import data.*;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ui.UI.*;

/**
 *
 *
 * @author OnePoker UN
 */
public class GameEngine {

    public static Random RND;
    private static GameEngine instance;
    private static Table table;

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
        getInstance();
        startGame();
    }

    public static void startGame() {
        int menu = 0;
        printWelcome();
        while (true) {
            try {
                printMainMenu(table);
                menu = askInt("\nOption: ");
                if (menu < 1 || menu > 4) {
                    throw new IllegalArgumentException("Not a menu option");
                }
                switch (menu) {
                    case 1: {
                        if (table == null) {
                            table = new Table();
                        } else {
                            table = new Table(table);
                        }
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
                        checkCommand("<Exit>", true);
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
            case "<Exit>": {
                if (print) {
                    printExit();
                }
                System.exit(0);
            }
            case "<Info>": {
                if (print) {
                    printInfo();
                }
                return true;
            }
            case "<Help>": {
                if (print) {
                    printHelp();
                }
                return true;
            }
            case "<Test>": {
                HandAnalyserTest.test();
                return true;
            }
            case "<Hands>": {
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
        plyr.setCredits(plyr.getCredits() - bet);
        table.addToPot(bet);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void checkBet(Player plyr) {
        if (plyr.getCredits() > table.getMinBet()) {
            printMsg("Player " + plyr.getId() + " Checks");
            addBet(plyr, table.getMinBet());
        } else {
            allIn(plyr);
        }
    }

    public static void raiseBet(Player plyr, int bet) {
        if (bet < table.getMinBet()) {
            throw new IllegalArgumentException("Raise must be higher than minimum bet");
        } else {
            printMsg("Player " + plyr.getId() + " raises " + (bet - table.getMinBet()));
            table.setMinBet(bet);
            addBet(plyr, bet);
        }
    }

    public static void fold(Player plyr) {
        plyr.setElo(plyr.getElo() - 10);
        table.removePlayer(plyr);
        printMsg("Player " + plyr.getId() + " folds\n");
    }

    public static void allIn(Player plyr) {
        printMsg("Player " + plyr.getId() + " goes all in!!");
        addBet(plyr, plyr.getCredits());
    }

    public static void roundMenu() {
        int menu = 0;
        while (menu == 0) {
            try {
                printRoundMenu();
                menu = askInt("Option: ");
                if (menu < 1 || menu > 5) {
                    throw new IllegalArgumentException("Not a menu option");
                }
                switch (menu) {
                    case 1: {
                        checkBet(table.getPlayer(0));
                        break;
                    }
                    case 2: {
                        int raise = 0;
                        while (raise == 0) {
                            try {
                                raise = askInt("Raise: ");
                                raiseBet(table.getPlayer(0), raise);
                            } catch (Exception ex) {
                                raise = 0;
                                printError(ex);
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
                printError(ex);
            }
        }
    }

    public static void preFlop() {
        printMsg("Big blind and small blind open the betting round ");
        addBet(table.getPlayer(0), table.getMinBet() / 2);
        addBet(table.getPlayer(1), table.getMinBet());
        table.addToPot(table.getMinBet() + table.getMinBet() / 2);
        fold(table.getPlayer(2));
        raiseBet(table.getPlayer(3), 75);
    }

    public static int holdCardsValue(Hand hand) {
        return 0;
    }

    public static void getPlayerOption(Player plyr) {
        if (plyr.getCredits() <= 0) {
            printMsg("Player " + plyr.getId() + " Retires...");
            for (int i = 0; i < 8; i++) {
                if (plyr == table.getSeats()[i]) {
                    table.getSeats()[i] = null;
                    table.removePlayer(plyr);
                }
            }
        } else if (plyr.isHumanPlayer()) {
            roundMenu();
        } else {
            int handValue = holdCardsValue(plyr.getHand());
            checkBet(plyr);
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
        printMsg("Dealt hold cards(You are player " + table.getPlayer(0).getId() + ")");
        printUser(table);
        preFlop();
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0: {
                    printMsg("The flop:");
                    deal(dealingDeck, tableHand, 3);
                    printMsg(table.getTableHand() + "\n");
                    break;
                }
                case 1: {
                    printMsg("The turn:");
                    deal(dealingDeck, tableHand, 1);
                    printMsg(table.getTableHand() + "\n");
                    break;
                }
                case 2: {
                    printMsg("The river:");
                    deal(dealingDeck, tableHand, 1);
                    printMsg(table.getTableHand() + "\n");
                    break;
                }
            }
            printUser(table);
            for (int j = 0; j < 8; j++) {
                if (table.getSeats()[j] != null) {
                    getPlayerOption(table.getSeats()[j]);
                }
            }
        }
        dealRewards();
        printStandings(table);
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
        compareHands(table);
        printRoundStandings(table);
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
        } else {
            Player winner = table.getPlayer(0);
            winner.setCredits(winner.getCredits() + table.getPot());
            winner.setElo(winner.getElo() + 25);
            for (int i = 1; i < table.getPlayersSize(); i++) {
                table.getPlayer(i).setElo(table.getPlayer(i).getElo() - 5);
            }
        }
        table.setPot(0);
    }
}
