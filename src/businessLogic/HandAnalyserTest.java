/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import static businessLogic.DealingAssistant.deal;
import static businessLogic.DealingAssistant.shuffleDeck;
import static businessLogic.HandAnalyser.highCard;
import static businessLogic.RoundHandler.communitaryHand;
import data.Deck;
import data.Hand;
import data.Round;
import static ui.UI.printDeck;

/**
 *
 * @author OnePoker UN 
 */
public class HandAnalyserTest {

    public static void test() {

        Hand juego = new Hand("Prueba");
        Round ronda = new Round();
        Deck dealer = ronda.getDealingDeck();
        Hand comunitario = ronda.getTableHand();
        shuffleDeck(dealer);
        deal(dealer, juego, 2);

        //printDeck(juego);
        communitaryHand(ronda);
        System.out.println(highCard(juego));
    }
}
