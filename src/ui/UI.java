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

    private String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    /*for (int i = 0; i < 13; i++) {
            if (i != 12) {
                System.out.print(1 + "000");
            } else {
                System.out.println("1");
            }
        }
        for (int i = 0; i < 13; i++) {
            System.out.print(rank[12 - i] + "   ");
        }*/
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
