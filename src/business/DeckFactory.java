package business;

import static business.DeckHelper.shuffleDeck;
import data.Card;
import data.Deck;
import data.Hand;

/**
 *
 * @author One Poker UN
 */
public class DeckFactory {
    
    public static Deck createDealingDeck() {
        Deck dealingDeck = new Deck<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.CardRank rank : Card.CardRank.values()) {
                dealingDeck.addCard(new Card(rank, suit));
            }
        }
        shuffleDeck(dealingDeck);
        return dealingDeck;
    }
    
    public static Hand createHand(String handType) {
        return new Hand();
    }
    
    public static Hand cloneHand(Hand hand) {
        //System.out.println(hand.getCards().getClass().toString());
        Hand clone = new Hand();
        clone.setRank(hand.getRank());
        clone.addAll(hand);
        return clone;
    }
}
