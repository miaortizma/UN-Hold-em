/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import static businessLogic.DealingAssistant.*;
import static businessLogic.HandAnalyser.allPossibleHands;
import data.*;
import java.util.List;
import static ui.UI.*;

/**
 *
 * @author OnePoker UN &
 */
public class RoundHandler {

    public static void reiceveBets(Round round) throws Exception {
        //stub
        int userBet = askInt("Bet: ");
        List<Player> players = round.getPlayers();
        System.out.println("Betting time");
        for (int i = 0; i < players.size(); i++) {
            round.addToPot(25);
        }
    }

    public static void roundMenu() {
        int menu = 0;
        while (true) {
            try {
                printRoundMenu();
                menu = askMenu();
                switch (menu) {
                    case 1: {
                        playRound(new Round());
                        break;
                    }
                    case 2: {
                        printHelp();
                        break;
                    }
                    case 3: {
                    }
                    default: {
                        throw new IllegalArgumentException("Not a valid command", null);
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                printError();
            }
        }
    }

    public static void playRound(Round round) {
        //tratando de imprimir cartas unicode

        DealingDeck dealingDeck = round.getDealingDeck();
        Hand tableHand = round.getTableHand();
        burnCard(dealingDeck);

        dealToPlayers(round);
        System.out.println("Your cards are: ");
        System.out.println("" + round.getPlayers().get(0).getHand());
        for (int i = 0; i < 3; i++) {
            roundMenu();
            switch (i) {
                case 0: {
                    System.out.println("The flop: ");
                    deal(dealingDeck, tableHand, 3);
                    break;
                }
                case 1: {
                    System.out.println("The turn:  ");
                    deal(dealingDeck, tableHand, 1);
                    break;
                }
                case 2: {
                    System.out.println("The river:  ");
                    deal(dealingDeck, tableHand, 1);
                    break;
                }
            }
            System.out.println(tableHand);
        }

        allPossibleHands(round.getPlayers().get(0).getHand(), round.getTableHand());

    }

}
