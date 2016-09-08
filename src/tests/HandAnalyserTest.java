/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import static businessLogic.DealingAssistant.deal;
import static businessLogic.DeckFactory.CreateDeck;
import static businessLogic.DeckFactory.CreateHand;
import static businessLogic.HandAnalyser.allPossibleHands;
import static businessLogic.HandAnalyser.rankHand;
import data.DealingDeck;
import data.Hand;
import static ui.UI.printDeck;
import static businessLogic.DealingAssistant.deal;
import static businessLogic.DeckFactory.CreateDeck;

/**
 *
 * @author OnePoker UN
 */
public class HandAnalyserTest {

    public static void test() {

        Hand juego = (Hand) CreateHand("playerhand");
        Hand comunitario = (Hand) CreateHand("playerhand");
        DealingDeck dealer = CreateDeck("dealingdeck");
        Hand royal = CreateHand("royal");

        deal(dealer, juego, 2);
        //printDeck(dealer);
        deal(dealer, comunitario, 5);
        System.out.println("Tu juego:\n" + juego);
        System.out.println("Comunitario: \n" + comunitario);
        System.out.println("");
        allPossibleHands(juego, comunitario);
    }

}
