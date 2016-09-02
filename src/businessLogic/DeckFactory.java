/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import static businessLogic.DealingAssistant.shuffleDeck;
import data.Card;
import data.Deck;
import data.Hand;
import data.Player;

/**
 *
 * @author One Poker
 */
public class DeckFactory {

    public static Deck CreateDeck(String deckType, Player... args) {
        if (deckType.equalsIgnoreCase("DEALINGDECK")) {
            Deck dealingDeck = new Deck("Dealing deck");
            for (int i = 0; i < 52; i++) {
                dealingDeck.addCard(new Card(i));
            }
            shuffleDeck(dealingDeck);
            return dealingDeck;
        } else if (deckType.equalsIgnoreCase("PLAYERHAND")) {
            if (args.length > 0) {
                return new Hand("Hand", args[0]);
            } else {
                return new Hand("Hand");

            }
        } else {
            return null;
        }

    }

}
