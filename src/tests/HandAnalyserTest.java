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
import data.Round;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("");
        List<Hand> manos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            System.out.println(ronda.getPlayers().get(i).getHand());
        }
        //compareHands(ronda);
    }

}
