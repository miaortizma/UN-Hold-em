/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import static businessLogic.DealingAssistant.deal;
import static businessLogic.DeckFactory.CreateDeck;
import static businessLogic.HandAnalyser.rankHand;
import data.Deck;
import data.Hand;
import static ui.UI.printDeck;

/**
 *
 * @author OnePoker UN
 */
public class HandAnalyserTest {

    public static void test() {

        Hand juego = (Hand) CreateDeck("playerhand");
        Hand comunitario = (Hand) CreateDeck("playerhand");
        Deck dealer = CreateDeck("dealingdeck");
        deal(dealer, juego, 2);
        deal(dealer, comunitario, 5);
        printDeck(comunitario);
        

        System.out.println(rankHand(comunitario));
    }

}
