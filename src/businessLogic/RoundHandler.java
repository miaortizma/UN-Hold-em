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
 * @author OnePoker UN  &
 */
public class RoundHandler {

    public static void reiceveBets(Round round, List<Player> players) {
        //stub

        System.out.println("Betting time");
        for (int i = 0; i < players.size(); i++) {
            round.addToPot(25);
        }

    }

    public static void playRound(Round round) {
        //tratando de imprimir cartas unicode
        
        Deck dealingDeck = round.getDealingDeck();
        System.out.println((char) 0x1F0B8);
        printDeck(dealingDeck);
        shuffleDeck(dealingDeck);
        printDeck(dealingDeck);
        burnCard(dealingDeck);
        //printDeck(dealingDeck);

        List<Player> players = round.getPlayers();
        dealToPlayers(dealingDeck, players);

    }

    public static void communitaryHand(Round round) {
        
        
        Deck dealingDeck = round.getDealingDeck();
        
        Deck tableDeck = round.getTableHand();
        for (int i = 0; i < 5; i++) {

            switch (i) {
                case 0: {
                    System.out.println("The flop: ");
                    deal(dealingDeck, tableDeck, 3);
                    i = 2;
                    break;
                }
                case 3: {
                    System.out.println("The turn:  ");
                    deal(dealingDeck, tableDeck, 1);
                    break;
                }
                case 4: {
                    System.out.println("The river:  ");
                    deal(dealingDeck, tableDeck, 1);
                    break;
                }

            }
            //doesn't print name
            printDeck(tableDeck, false);
        }
    }

}
