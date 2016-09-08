/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import static businessLogic.DeckFactory.createDeck;
import static businessLogic.DeckFactory.createHand;
import static businessLogic.HandAnalyser.allPossibleHands;
import data.DealingDeck;
import data.Hand;
import static businessLogic.DealingAssistant.deal;

/**
 *
 * @author OnePoker UN
 */
public class HandAnalyserTest {

    public static void test() {

        Hand juego = (Hand) createHand("playerhand");
        Hand comunitario = (Hand) createHand("playerhand");
        DealingDeck dealer = createDeck("dealingdeck");
        Hand royal = createHand("royal");

        deal(dealer, juego, 2);
        //printDeck(dealer);
        deal(dealer, comunitario, 5);
        System.out.println("Tu juego:\n" + juego);
        System.out.println("Comunitario: \n" + comunitario);
        System.out.println("");
        allPossibleHands(juego, comunitario);
    }

}
