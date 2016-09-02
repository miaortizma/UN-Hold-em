/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import static businessLogic.DealingAssistant.shuffleDeck;
import data.Card;
import data.Deck;

/**
 *
 * @author One Poker
 */
public class DeckFactory {

    public static Deck CreateDeck(String deckType) {
        if (deckType.equalsIgnoreCase("DEALINGDECK")) {
            Deck dealingDeck = new Deck("Dealing deck");
            for (int i = 0; i < 52; i++) {
                dealingDeck.addCard(new Card(i));
            }
            shuffleDeck(dealingDeck);
            return dealingDeck;
        } else if (deckType.equalsIgnoreCase("PLAYERHAND")) {
            return new Deck("Hand");
        } else {
            return null;
        }

    }

}
