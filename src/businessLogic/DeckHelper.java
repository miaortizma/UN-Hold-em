/*
 * The MIT License
 *
 * Copyright 2016 Miguel Angel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package businessLogic;

import data.Card;
import data.Deck;
import data.Hand;
import data.Player;
import data.Table;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Miguel Angel
 */
public class DeckHelper {

    public static Card dealCard(Deck deck) {
        if (deck.getDeck().isEmpty()) {
            throw new IllegalArgumentException("Empty deck", null);
        }
        return (Card)deck.getDeck().remove(deck.getDeck().size() - 1);
    }

    public static void deal(Deck deck, Hand mano, int i) {
        for (int j = 0; j < i; j++) {
            mano.addCard(dealCard(deck));
        }
    }

    public static void dealToPlayers(Table round) {
        List<Player> players = round.getPlayers();
        Deck deck = round.getDealingDeck();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            deal(deck, player.getHand(), 2);
        }
    }

    public static void shuffleDeck(Deck deck) {
        Random rnd = new Random();
        int index;
        Card temp;
        for (int i = deck.size() - 1; i > 0; i--) {
            index = rnd.nextInt(i + 1);
            temp = deck.getCard(i);
            deck.set(index, deck.getCard(i));
            deck.set(i, temp);
        }
    }

}
