/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import data.DealingDeck;
import data.Hand;
import static businessLogic.DealingAssistant.deal;
import static businessLogic.DealingAssistant.dealToPlayers;
import static businessLogic.RoundHandler.compareHands;
import data.Round;
import static ui.UI.printPlayers;

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
        printPlayers(ronda);
        compareHands(ronda);
        printPlayers(ronda);
    }

}
