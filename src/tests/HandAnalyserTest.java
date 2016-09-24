package tests;

import data.DealingDeck;
import data.Hand;
import static businessLogic.HandAnalyser.deal;
import static businessLogic.HandAnalyser.dealToPlayers;
import static businessLogic.RoundHandler.compareHands;
import data.Round;
import static ui.UI.printStandings;
import static ui.UI.printTest;

/**
 *
 * @author OnePoker UN
 */
public class HandAnalyserTest {

    public static void test() {

        Round ronda = new Round();
        dealToPlayers(ronda);
        DealingDeck dealer = ronda.getDealingDeck();
        Hand comunitario = ronda.getTableHand();
        //printDeck(dealer);
        deal(dealer, comunitario, 5);
        System.out.println("Comunitario: \n" + comunitario);
        //bestHand(ronda.getPlayerHand(0),comunitario);
        printStandings(ronda);
        compareHands(ronda);
        printStandings(ronda);
        printTest();
        System.out.println("\u2620");
        //printHands();
    }

}
