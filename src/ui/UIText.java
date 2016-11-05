package ui;

import business.GameEngine;
import data.Player;
import data.Table;
import java.util.Scanner;

public class UIText implements UI {

    private static final Scanner IN = new Scanner(System.in);
    private static String inputUI;
    private static final String COMMANDS = "\n"
            + "Type <Exit> at any time to exit \n"
            + "Type <Info> to know about this project\n"
            + "Type <Help> if you need some help\n"
            + "Type <Hands> to print Hands";
    static final String DECORATOR = "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/";
    private static final String DEALER = "D";
    private static final String BIGBLIND = "\u0E4F";
    private static final String LITTLEBLIND = "\u263B";

    public static void printTest() {
        System.out.println(DEALER);
        System.out.println(BIGBLIND);
        System.out.println(LITTLEBLIND);
    }

    @Override
    public void printWelcome() {
        //System.out.println("\nWelcome to UN Hold' em");
        String ascciArt = ".------..------..------..------..------..------..------..------..------.\n"
                + "|U.--. ||N.--. ||H.--. ||O.--. ||L.--. ||D.--. ||'.--. ||E.--. ||M.--. |\n"
                + "| (\\/) || :(): || :/\\: || :/\\: || :/\\: || :/\\: || :/\\: || (\\/) || (\\/) |\n"
                + "| :\\/: || ()() || (__) || :\\/: || (__) || (__) || :\\/: || :\\/: || :\\/: |\n"
                + "| '--'U|| '--'N|| '--'H|| '--'O|| '--'L|| '--'D|| '--''|| '--'E|| '--'M|\n"
                + "`------'`------'`------'`------'`------'`------'`------'`------'`------'\n"
                + " ";
        System.out.print(ascciArt);
        printCommands();
    }

    @Override
    public void printError(Exception ex) {
        if ("Command".equals(ex)) {
            return;
        }
        //ex.printStackTrace();
        System.out.print("Error: ");
        System.out.println(ex.getMessage());
    }

    @Override
    public String askMsg(String question) throws Exception {
        System.out.print(question);
        inputUI = IN.nextLine();
        if (GameEngine.getInstance(null).checkCommand(inputUI, true)) {
            throw new Exception("Command");
        }
        return inputUI;
    }

    @Override
    public void printHelp() {
        System.out.println(DECORATOR);
        System.out.println("Poker Hold'em is a game of bets in which each player has two cards and the goal is to assemble the \n best set of five cards between yours and the five comunitary cards.");
        System.out.println("The comunitary cards are showed to all the players in this way: After each round of bets, a card \n of the deck is 'burned'(discarded)");
        System.out.println("and three cards(first round) or one card(other rounds) are showed until five cards were at sight.");
        System.out.println("You can match the bet, backing out, or check (pass) on your turn");
        System.out.println("bet more if you have a good hand in the game and win more points ");
        System.out.println(DECORATOR);
    }

    @Override
    public void printHands() {
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
        System.out.println("* \nA kicker is the highest card on that doesn't determine the type of the hand, it  decides who wins in case of tie");
        System.out.println("P.S.: If two or more player have the same hand, wins the higher one, if the hands are completely the same \n the prize is divided among the winners. Also, if two or more player have hands that are composed of other hands like \n double pair(two pairs) or full house(Three of a kind and a pair) and there is a tie, the sub-hand are compared until \n the kicker if it's necessary and wins the higher of them.");
        System.out.println("We hope you enjoy it! :D");

    }

    @Override
    public void printCommands() {
        System.out.println(COMMANDS);
    }

    @Override
    public void printExit() {
        System.out.println("\nThanks, see you later");
    }

    @Override
    public void printInfo() {
        System.out.println("Wellcome,");
        System.out.println("This is a Object Oriented Implementation of a Poker Game,");
        System.out.println("a project of students of Universidad Nacional de Colombia Project.");
        System.out.println("\t" + "Authors:");
        System.out.println("Miguel Angel Ortiz \t miaortizma@unal.edu.co\n Diana Carolina Guarin \t dcguarina@unal.edu.co \n Daniel Organista Calderon \t jodorganistaca@unal.edu.co");
        System.out.println("\t Course Info:");
        System.out.println("Object Oriented Programming (2016375)/ 2016-2 \n https://sites.google.com/site/oopunal20162/contents");
        System.out.println("Teacher: Ing. Sebastian Alejandro Velasco Dimate \n savelascod@unal.edu.co");
        System.out.print("Developer Team: ");
        System.out.println("One Poker");
    }

    /**
     * Prints the round players and their hands used to test at the start and
     * end of round
     *
     * @param table the round to print must have finished
     * @param winners
     */
    @Override
    public void printRoundStandings(Table table, int winners) {
        System.out.println("At the end of a round a winner is choosen, if there is a tie the pot is splitted");
        System.out.println("Each player can form a \"Best hand\" combining his cards with the comunitary hand");
        System.out.println("The player(s) with the best hand wins");

        System.out.println("\nRound Standings(from best to worst):");
        for (Player plyr : table.getPlayers()) {
            System.out.println("Player " + plyr.getId() + " - " + plyr.getHand().toString());
        }
        System.out.println("\n"
                + "Winners:");
        for (int i = 0; i < winners; i++) {
            System.out.println("Player " + table.getPlayer(i).getId() + " Wins");
        }
        System.out.println("\nType <Hands> to check how Hand are ranked\n");
    }

    @Override
    public void printStandings(Table table) {
        System.out.println("Tournament Standings: ");
        for (int i = 0; i < 8; i++) {
            if (table.getSeats()[i] != null) {
                System.out.println(table.getSeats()[i]);
            }
        }
        System.out.println("");
    }

    @Override
    public void printUser(Table table) {
        System.out.println("***********************");
        System.out.println("Cards\tCredits");
        System.out.println(table.getPlayerHand(0) + "\t" + table.getPlayer(0).getCredits());
        System.out.println("Minimum bet: " + table.getMinBet());
        System.out.println("***********************");
    }

    @Override
    public void printMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public int askMenuOption(String menuName, String message, String[] options) {
        int menu = 0;
        String menuStr = "";
        for (int i = 0; i < options.length; i++) {
            menuStr += "(" + (i + 1) + ") " + options[i] + "\t";
        }
        printMsg(menuStr);

        while (menu == 0) {
            menu = askInt(message);
            if (menu < 1 || menu > options.length) {
                menu = 0;
            }
        }
        return menu;
    }

    @Override
    public int askInt(String question) {
        int out = 0;
        while (out == 0) {
            printMsg(question);
            if (IN.hasNextInt()) {
                out = IN.nextInt();
            } else {
                IN.next();
            }
        }
        return out;
    }
}
