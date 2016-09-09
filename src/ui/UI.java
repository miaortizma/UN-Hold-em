/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import static businessLogic.GameEngine.checkCommand;
import data.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author OnePoker UN Estudiante
 */
public class UI {

    public static final Scanner in = new Scanner(System.in);
    private static String inputUI;
    private static final String COMMANDS = "\nType <Exit> at any time to exit \nType <Info> to know about this project\nType <Help> if you need some help\n";

    public static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public static final String[] SUITS = {"\u2660", "\u2663", "\u2764", "\u2666"};

    public static void printWelcome() {
        System.out.println("\nWelcome to UN Hold' em");
        String ascci = ".------..------..------..------..------..------..------..------..------.\n"
                + "|U.--. ||N.--. ||H.--. ||O.--. ||L.--. ||D.--. ||'.--. ||E.--. ||M.--. |\n"
                + "| (\\/) || :(): || :/\\: || :/\\: || :/\\: || :/\\: || :/\\: || (\\/) || (\\/) |\n"
                + "| :\\/: || ()() || (__) || :\\/: || (__) || (__) || :\\/: || :\\/: || :\\/: |\n"
                + "| '--'U|| '--'N|| '--'H|| '--'O|| '--'L|| '--'D|| '--''|| '--'E|| '--'M|\n"
                + "`------'`------'`------'`------'`------'`------'`------'`------'`------'\n"
                + " ";
        System.out.println(ascci);
    }

    public static void printDeck(DealingDeck deck) {
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

    public static int askMenu() throws Exception {
        if (in.hasNextInt()) {
            int x = in.nextInt();
            in.nextLine();
            if (x < 1 || x > 2) {
                throw new Exception();
            }
            return x;
        } else {
            inputUI = in.nextLine();
            checkCommand(inputUI, true);
            throw new Exception();
        }
    }

    public static String askMsg(String question) {

        System.out.print(question);
        inputUI = in.nextLine();
        checkCommand(inputUI, true);
        return inputUI;

    }

    public static int askInt(String question) throws Exception {
        System.out.print(question);
        if (in.hasNextInt()) {
            int x = in.nextInt();
            in.nextLine();
            return x;
        } else {
            checkCommand(in.next(), true);
            throw new Exception("Not a number");
        }
    }

    public static void printMenu() {
        System.out.println("Menu: ");
        System.out.println("(1) - Start a round? \t (2) - Never played poker before? \t (3) - Command List");

    }

    public static void printExit() {

        System.out.println("\nThanks, see you later");
    }

    public static void printError() {
        System.out.println("Please try again");
    }

    public static void printInfo() {
        System.out.println("Developer Team :");
        System.out.println("One Poker");
    }

    public static void printCommands() {
        System.out.println(COMMANDS);
    }

    public static void printRoundMenu() {
        System.out.println("(1) - Check \t (2) - Raise  \t (3) - Fold \t (4) - All in \t (5)- Retire");

    }

    public static void printHelp() {
        System.out.println("Poker is a family of gambling card games.\n"
                + "All poker variants involve betting as an intrinsic part of play, and determine the winner of each hand according to the combinations of players' cards,\n"
                + " at least some of which remain hidden until the end of the hand.\n"
                + "Poker games vary in the number of cards dealt, the number of shared or \"community\" cards, \n"
                + "the number of cards that remain hidden, and the betting procedures.");
    }

    /**
     *
     * @param ronda
     */
    public static void printPlayers(Round ronda) {
        System.out.println("PLAYERS SIZE: " + ronda.getPlayersSize());
        for (Player plyr : ronda.getPlayers()) {
            System.out.println(plyr);
        }
    }

}
