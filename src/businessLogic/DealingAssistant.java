package businessLogic;

import data.*;
import java.util.List;
import java.util.Random;

/**
 *
 * @author OnePoker UN OnePoker UN
 */
public class DealingAssistant {

    public static void burnCard(AbstractDeck deck) {
        //System.out.println("Card burned!");
        deck.pop();
    }

    public static Card deal(DealingDeck deck) {
        if (deck.getCards().isEmpty()) {
            throw new IllegalArgumentException("Empty deck", null);
        }
        return deck.getCards().remove(deck.getSize() - 1);
    }

    public static void deal(DealingDeck deck, Hand mano, int i) {
        for (int j = 0; j < i; j++) {
            mano.addCard(deal(deck));
        }
    }

    public static void dealToPlayers(Round round) {
        List<Player> players = round.getPlayers();
        DealingDeck deck = round.getDealingDeck();
        System.out.println("Dealing time");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            deal(deck, player.getHand(), 2);
        }

    }

    public static void shuffleDeck(DealingDeck deck) {
        //System.out.println("Shuffling DealingDeck !!");
        Random rnd = new Random();
        List<Card> cards = deck.getCards();
        int index;
        Card temp;
        for (int i = cards.size() - 1; i > 0; i--) {
            index = rnd.nextInt(i + 1);
            temp = cards.get(index);
            cards.set(index, cards.get(i));
            cards.set(i, temp);
        }
    }

}
