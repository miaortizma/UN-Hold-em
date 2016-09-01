/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Card;
import data.Hand;
import java.util.List;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class HandAnalyser {

    /**
     *
     * @param hand
     * @return indice de la carta màs alta
     */
    public static Card highCard(Hand hand) {
        // el tamaño de todas las manos es 5
        List<Card> cards = hand.getCards();
        int maxCard = cards.get(0).getValue(), cardIndex = 0, cardValue = 0;
        for (int i = 0; i < cards.size(); i++) {
            cardValue = cards.get(i).getValue();
            if (cardValue == 0) {
                return cards.get(i);
            }
            if (cards.get(i).getValue() > maxCard) {
                cardIndex = i;
            }
        }
        return cards.get(cardIndex);
    }

}
