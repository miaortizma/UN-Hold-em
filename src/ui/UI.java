/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import data.*;
import java.util.List;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class UI {

    public static void printWelcome() {
        System.out.println("Welcome to UN Hold' em");
        System.out.println("Start a new tournament?");

    }

    public static void printDeck(Deck deck) {
        System.out.println(deck.getName() + ": ");
        List<Card> cards = deck.getCards();
        int count = 0;
        for (int i = 0; i < cards.size(); i++, count++) {
            if (count == 13) {
                System.out.println("");
                count = 0;
            }
            System.out.print(cards.get(i));

        }
        System.out.println("");
    }

    public static void printDeck(Deck deck, boolean printName) {
        if (printName) {
            System.out.println(deck.getName() + ": ");
        }
        List<Card> cards = deck.getCards();
        int count = 0;
        for (int i = 0; i < cards.size(); i++, count++) {
            if (count == 13) {
                System.out.println("");
                count = 0;
            }
            System.out.print(cards.get(i));

        }
        System.out.println("");
    }

}
