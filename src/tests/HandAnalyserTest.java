package tests;

import static business.GameEngine.compareHands;
import data.Deck;
import data.Hand;
import data.Table;
import static business.DeckHelper.deal;
import static business.DeckHelper.dealToPlayers;

/**
 *
 * @author OnePoker UN
 */
public class HandAnalyserTest {

    public static void test() {

        Table table = new Table();
        //UI ui = GameEngine.getUI();
        dealToPlayers(table);
        Deck dealer = table.getDealingDeck();
        Hand comunitario = table.getTableHand();
        //printDeck(dealer);
        deal(dealer, comunitario, 5);
        System.out.println("Comunitario: \n" + comunitario);
        //bestHand(table.getPlayerHand(0),comunitario);
        //ui.printStandings(table);
        compareHands(table);
        //ui.printStandings(table);
        //printTest();
        System.out.println("\u2620");
        //printHands();
    }

}
