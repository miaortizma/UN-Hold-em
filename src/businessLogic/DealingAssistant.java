package businessLogic;

import data.*;
import java.util.List;
import java.util.Random;
import static ui.UI.printDeck;

/**
 *
 * @author OnePoker UN OnePoker UN
 */
public class DealingAssistant {

    public static void burnCard(Deck deck) {
        System.out.println("Card burned!");
        deck.getCards().remove(deck.getSize() - 1);
    }

    public static Card deal(Deck deck) {
        return deck.getCards().remove(deck.getSize() - 1);
    }

    /**
     * Deals from deck to player i times
     *
     * @param deck
     * @param player
     * @param i
     */
    public static void deal(Deck deck, Player player, int i) {
        for (int j = 0; j < i; j++) {
            player.receiveCard(deal(deck));
        }
    }

    public static void deal(Deck deck, Hand mano, int i) {
        for (int j = 0; j < i; j++) {
            mano.addCard(deal(deck));
        }
    }

    /**
     * Deals from deck1 to deck2 i times
     *
     * @param deck1
     * @param deck2
     * @param i
     */
    public static void deal(Deck deck1, Deck deck2, int i) {
        for (int j = 0; j < i; j++) {
            deck2.addCard(deal(deck1));
        }
    }

    public static void dealToPlayers(Round round) {
        List<Player> players = round.getPlayers();
        Deck deck = round.getDealingDeck();
        System.out.println("Dealing time");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            deal(deck, player, 2);
            printDeck(player.getDeck());
            //printDeck(dealingDeck);
        }

    }

    public static void shuffleDeck(Deck deck) {
        System.out.println("Shuffling Deck !!");
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
