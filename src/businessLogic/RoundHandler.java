/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import static businessLogic.DealingAssistant.*;
import data.*;
import java.util.List;
import static ui.UI.printDeck;

/**
 *
 * @author OnePoker UN &
 */
public class RoundHandler {

    public static void reiceveBets(Round round) {
        //stub

        List<Player> players = round.getPlayers();
        System.out.println("Betting time");
        for (int i = 0; i < players.size(); i++) {
            round.addToPot(25);
        }

    }

    public static void playRound(Round round) {
        //tratando de imprimir cartas unicode

        Deck dealingDeck = round.getDealingDeck();
        Hand tableHand = round.getTableHand();
        
        printDeck(dealingDeck);
        burnCard(dealingDeck);

        reiceveBets(round);

        dealToPlayers(round);
        for (int i = 0; i < 3; i++) {
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

    }

}
