package businessLogic;

import static businessLogic.GameEngine.checkCommand;
import static businessLogic.DeckHelper.bestHand;
import static businessLogic.DeckHelper.burnCard;
import static businessLogic.DeckHelper.deal;
import static businessLogic.DeckHelper.dealToPlayers;
import data.*;
import java.util.Collections;
import java.util.List;
import static ui.UI.*;

/**
 *
 * @author OnePoker UN
 */
public class RoundHandler {

    public static void reiceveBets(Round round) throws Exception {
        //stub
        int userBet = askInt("Bet: ");
        List<Player> players = round.getPlayers();
        //System.out.println("Betting time");
        for (int i = 0; i < players.size(); i++) {
            round.addToPot(25);
        }
    }

    public static void roundMenu() {
        int menu = 0;
        while (menu == 0) {
            try {
                printRoundMenu();
                menu = askRoundMenu();
                switch (menu) {
                    case 0: {
                        //do nothing
                        break;
                    }
                    case 1: {
                        //check
                        break;
                    }
                    case 2: {
                        //raise
                        break;
                    }
                    case 3: {
                        //fold
                        break;
                    }
                    case 4: {
                        //all in
                        break;
                    }
                    case 5: {
                        //retire
                        checkCommand("<Exit>", true);
                    }
                    default: {
                        throw new IllegalArgumentException("Not a valid command", null);
                    }

                }
            } catch (Exception ex) {
                //System.out.println(ex.getMessage());
                //ex.printStackTrace();
                printError();
            }
        }
    }

    /**
     * NOTES / TO DO Goes through: -Dealing. -Burn Card -Shows player(0) his
     * cards -Flop,turn and river. (Comunitary hand) -Comparing player hands
     * (Doesn't do nothing about ties and also note that in the way that
     * collections sort to print the players they are sorted in reverse order)
     *
     * @param round
     */
    public static void playRound(Round round) {
        DealingDeck dealingDeck = round.getDealingDeck();
        Hand tableHand = round.getTableHand();
        burnCard(dealingDeck);
        round.getPlayer(0).setHumanPlayer(true);
        dealToPlayers(round);
        printStandings(round);
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0: {
                    deal(dealingDeck, tableHand, 3);
                    break;
                }
                case 1: {
                    deal(dealingDeck, tableHand, 1);
                    break;
                }
                case 2: {
                    deal(dealingDeck, tableHand, 1);
                    break;
                }
            }
            printBoard(i, round);
            roundMenu();
        }
        compareHands(round);
        printStandings(round);

    }

    /**
     * NOTES/ TO DO: -Should add some variable to notify that there is a tie
     * -Should add different method that controls who wins etc. sets each round
     * player hand as its possible Hand after merging its hand and the
     * comunitary hand, sets each player kickers.
     *
     * Sorts the list of players (The criteria is their hands and kickers)
     *
     * @param round
     */
    public static void compareHands(Round round) {
        for (Player plyr : round.getPlayers()) {
            plyr.setHand(bestHand(plyr.getHand(), round.getTableHand()));
        }
        Collections.sort(round.getPlayers(), Collections.reverseOrder());
    }

    /**
     * Assumes players is ordered
     *
     *
     */
    public static void checkTies(Round round) {
        int x = 0;

        //if (round.getPlayer(0)) {
        //
    }

}
