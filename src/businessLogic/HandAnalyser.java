/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Card;
import data.Hand;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class HandAnalyser {

    public static void pair(Hand hand) {
        //asume que recibe una mano ordenada
        for (int i = hand.getSize(); i > 0; i--) {

        }
    }

    public static int[] rankFrequency(Hand hand) {
        int[] frequency = new int[13];
        for (int i = 0; i < hand.getSize(); i++) {
            frequency[hand.getCard(i).getValue()]++;
        }
        return frequency;
    }

    /**
     *
     * @param hand
     * @return indice de la carta màs alta
     */
    public static Card highCard(Hand hand) {
        int maxCard = hand.getCard(0).getValue(), cardIndex = 0, cardValue = 0;
        for (int i = 0; i < hand.getSize(); i++) {
            cardValue = hand.getCard(i).getValue();
            if (cardValue == 0) {
                return hand.getCard(i);
            }
            if (hand.getCard(i).getValue() > maxCard) {
                cardIndex = i;
            }
        }
        return hand.getCard(cardIndex);
    }

}
