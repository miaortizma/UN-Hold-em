/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import data.Player;
import data.Round;
import java.util.Scanner;

public class UI {

    public static Scanner in = new Scanner(System.in);
    private static final String COMMANDS = "\nType <Exit> at any time to exit \nType <Info> to know about this project\nType <Help> if you need some help\n";
    static String userMenu;
    static boolean onGame = true;
    public static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public static final String[] SUITS = {"\u2660", "\u2663", "\u2764", "\u2666"};
    static final String Decorator = "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/";

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

    public static void printHelp() {
        System.out.println(Decorator);
        System.out.println("Poker Hold'em is a game of bets in which each player has two cards and the goal is to assemble the \n best set of five cards between yours and the five comunitary cards.");
        System.out.println("The comunitary cards are showed to all the players in this way: After each round of bets, a card \n of the deck is 'burned'(discarded)");
        System.out.println("and three cards(first round) or one card(other rounds) are showed until five cards were at sight.");
        System.out.println("You can match the bet, backing out, or check (pass) on your turn");
        System.out.println("bet more if you have a good hand in the game and win more points ");
        System.out.println(Decorator);
        printHands();
    }

    public static void printHands() {
        System.out.println("These are the hands you can form on the game, they are sorted upward by value (1 = higher value):");
        System.out.println("1) Royal Flush: 10" + "\u2660 " + "J" + "\u2660 " + "Q" + "\u2660 " + "K" + "\u2660 " + "A" + "\u2660 " + "(five higher cards from the same suit )");
        System.out.println("2) Straight Flush: 4\u2663 5\u2663 6\u2663 7\u2663 8\u2663 (Five cards consecutive from the same suit)");
        System.out.println("3) Four of a Kind: A\u2660 A\u2663 A\u2764 A\u2666 8\u2663 (Four A's and a kicker*, )");
        System.out.println("4) Full House: 10\u2666 10\u2764 10\u2663 J\u2660 J\u2663 (A pair and a Three of a Kind, in tie case wins the one who has the higher Three of a Kind)");
        System.out.println("5) Flush: 5\u2660 7\u2660 2\u2660 9\u2660 Q\u2660 (Five cards in disorder of the same suit)");
        System.out.println("6) Straight: 3\u2764 4\u2663 5\u2764 6\u2666 7\u2660 (Five consecutive card from different suits)");
        System.out.println("7) Three of a Kind: 7\u2666 7\u2663 7\u2764 A\u2666 (Three card of the same value, the last two have to be different between them)");
        System.out.println("8) Double Pair: Q\u2764 Q\u2663 J\u2663 J\u2666 4\u2764 (two pairs of cards , in case of tie wins the one who has the higher pair and so on)");
        System.out.println("9) Pair: 8\u2666 8\u2660 K\u2663 7\u2666 3\u2764");
        System.out.println("10) High Card: A\u2764 J\u2663 10\u2666 5\u2764 6\u2666 (When anybody have some of the previous hands, wins the one who has the higher kicker*)");
        System.out.println("* A kicker is the higher card on the hand, it helps to decide who wins in case of tie with the hands");
        System.out.println("P.S.: If two or more player have the same hand, wins the higher one, if the hands are completely the same \n the prize is divided among the winners. Also, if two or more player have hands that are composed of other hands like \n double pair(two pairs) or full house(Three of a kind and a pair) and there is a tie, the sub-hand are compared until \n the kicker if it's necessary and wins the higher of them.");
        System.out.println("We hope you enjoy it! :D");
        System.out.println(Decorator);

    }

    public static void printCommands() {
        System.out.println(COMMANDS);
    }

    public static void printMenu() {
        System.out.println("ººººººººMenuºººººººº ");
        System.out.println("(1) - Start a round? \t (2) - Never played poker before? \t (3) - Command List");
        System.out.print("Your option here:");
        userMenu = in.nextLine();
        userOpAnaliser();

    }

    public static void userOpAnaliser() {
        if (userMenu.equalsIgnoreCase("EXIT")) {
            onGame = false;
            printExit();
        } else if (userMenu.equalsIgnoreCase("INFO")) {
            printInfo();
        } else if (userMenu.equalsIgnoreCase("HELP")) {
            printHelp();
        } else {
            menuHandler(userMenu);
        }
    }

    public static void menuHandler(String userMenu) {
        switch (Integer.parseInt(userMenu)) {

            case 1:
                //Empezar una ronda
                break;
            case 2:
                printHelp();
                printMenu();
                break;

            case 3:
                printCommands();
                break;
            default:
                System.out.println("Please choose an option :)");
                printMenu();
                break;
        }

    }

    public static void printExit() {

        System.out.println("\nThanks, see you later");
    }

    public static void printInfo() {
        System.out.print("Developer Team :");
        System.out.println("One Poker");
    }

    public static void startGame() {
        printWelcome();
        while (onGame) {

            printMenu();

        }

    }

    /**
     *
     * @param ronda
     */
    public static void printPlayers(Round ronda) {
        System.out.println("PLAYERS SIZE: " + ronda.getPlayersSize());
        for (Player plyr : ronda.getPlayers()) {
            System.out.print(plyr);
            if (plyr.getKickers() != null) {
                System.out.print("Kickers: ");
                System.out.println(plyr.getKickers());
            }
            System.out.println("");
        }
    }
}
