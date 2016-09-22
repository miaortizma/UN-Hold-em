package ui;

import static businessLogic.GameEngine.checkCommand;
import data.Player;
import data.Round;
import java.util.Scanner;

public class UI {

    private static final Scanner IN = new Scanner(System.in);
    private static String inputUI;
    private static final String COMMANDS = "\n"
            + "Type <Exit> at any time to exit \n"
            + "Type <Info> to know about this project\n"
            + "Type <Help> if you need some help\n"
            + "Type <Hands> to print Hands";
    public static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public static final String[] SUITS = {"\u2660", "\u2663", "\u2764", "\u2666"};
    static final String DECORATOR = "/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/";
    public static final String DEALER = "D";
    public static final String BIGBLIND = "\u0E4F";
    public static final String LITTLEBLIND = "\u263B";

    public static void printTest() {
        System.out.println(DEALER);
        System.out.println(BIGBLIND);
        System.out.println(LITTLEBLIND);
    }

    public static void printWelcome() {
        System.out.println("\nWelcome to UN Hold' em");
        String ascci = ".------..------..------..------..------..------..------..------..------.\n"
                + "|U.--. ||N.--. ||H.--. ||O.--. ||L.--. ||D.--. ||'.--. ||E.--. ||M.--. |\n"
                + "| (\\/) || :(): || :/\\: || :/\\: || :/\\: || :/\\: || :/\\: || (\\/) || (\\/) |\n"
                + "| :\\/: || ()() || (__) || :\\/: || (__) || (__) || :\\/: || :\\/: || :\\/: |\n"
                + "| '--'U|| '--'N|| '--'H|| '--'O|| '--'L|| '--'D|| '--''|| '--'E|| '--'M|\n"
                + "`------'`------'`------'`------'`------'`------'`------'`------'`------'\n"
                + " ";
        System.out.print(ascci);
        printCommands();
    }

    public static void printError(Exception ex) {
        if ("Command".equals(ex.getMessage())) {
            return;
        }
        System.out.print("Error: ");
        System.out.println(ex.getMessage());
    }

    public static String askMsg(String question) throws Exception {
        System.out.print(question);
        inputUI = IN.nextLine();
        if (checkCommand(inputUI, true)) {
            throw new Exception("Command");
        }
        return inputUI;
    }

    public static int askInt(String question) throws Exception {
        askMsg(question);
        try {
            return Integer.parseInt(inputUI);
        } catch (Exception e) {
            throw new Exception("Not a number");
        }
    }

    public static void printHelp() {
        System.out.println(DECORATOR);
        System.out.println("Poker Hold'em is a game of bets in which each player has two cards and the goal is to assemble the \n best set of five cards between yours and the five comunitary cards.");
        System.out.println("The comunitary cards are showed to all the players in this way: After each round of bets, a card \n of the deck is 'burned'(discarded)");
        System.out.println("and three cards(first round) or one card(other rounds) are showed until five cards were at sight.");
        System.out.println("You can match the bet, backing out, or check (pass) on your turn");
        System.out.println("bet more if you have a good hand in the game and win more points ");
        System.out.println(DECORATOR);
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
        System.out.println("* \nA kicker is the highest card on that doesn't determine the type of the hand, it  decides who wins in case of tie");
        System.out.println("P.S.: If two or more player have the same hand, wins the higher one, if the hands are completely the same \n the prize is divided among the winners. Also, if two or more player have hands that are composed of other hands like \n double pair(two pairs) or full house(Three of a kind and a pair) and there is a tie, the sub-hand are compared until \n the kicker if it's necessary and wins the higher of them.");
        System.out.println("We hope you enjoy it! :D");
        System.out.println(DECORATOR);

    }

    public static void printCommands() {
        System.out.println(COMMANDS);
    }

    public static void printMainMenu() {
        System.out.println("ººººººººMenuºººººººº ");
        System.out.println("(1) - Start a round? \t (2) - Never played poker before? \t (3) - Command List\n(4) - Exit");
        System.out.print("Your option here:");
    }

    public static void printRoundMenu() {
        System.out.println("(1) - Check \t (2) - Raise  \t (3) - Fold \t (4) - All in \t (5)- Retire");
        System.out.println("Note: currently only option (5) is functional\n(Type any from 1 to 4 to check the progress of a poker round)");

    }

    public static void printExit() {
        System.out.println("\nThanks, see you later");
    }

    public static void printInfo() {
        System.out.print("Developer Team :");
        System.out.println("One Poker");
    }

    /**
     * Prints the round players and their hands used to test at the start and
     * end of round
     *
     * @param ronda the round to print must have finished
     */
    public static void printStandings(Round ronda) {
        //System.out.println("PLAYERS SIZE: " + ronda.getPlayersSize());
        System.out.println("At the end of a round a winner is choosen, if there is a tie the pot is splitted");
        System.out.println("Each player can form a \"Best hand\" combining his cards with the comunitary hand");
        System.out.println("The player(s) with the best hand wins");

        System.out.println("\nStandings(from best to worst):");
        for (Player plyr : ronda.getPlayers()) {
            System.out.print("Player ");
            System.out.print(plyr);
            System.out.println("");
        }
        if (ronda.getPlayer(0).getId() % 5 == 0) {
            System.out.println("You win");
        } else if (ronda.getPlayer(1).compareTo(ronda.getPlayer(0)) == 0) {
            System.out.println("Tie");
        } else {
            System.out.println("You lose");
        }
        System.out.println("Type <Hands> to check how Hand are ranked");
    }

    /**
     * Prints the board with the bottoms
     *
     * @param pos rank of between 0 to 7
     * @param ronda the round
     *
     */
    public static void printBoard(int pos, Round ronda) {
        Player[] players = new Player[8];
        for (int i = 0; i < ronda.getPlayersSize(); i++) {
            players[i] = ronda.getPlayer(i);
        }
        String[] botones = new String[8];
        /*boolean bigBlind = false;
        boolean littleBlind = false;
        

        for (int i = pos; i > pos - botones.length; i--) {
            if (!(players[(i + 8) % 8] == (null))) {
                if (i == pos) {
                    System.out.println("HERE: " + i);
                    botones[(i + 8) % 8] = BIGBLIND;
                    bigBlind = true;
                } else if (bigBlind) {
                    
                    System.out.println("HERE: >" + i);
                    botones[(i + 8) % 8] = LITTLEBLIND;
                    bigBlind = false;
                    littleBlind = true;
                } else if (littleBlind) {
                    
                    System.out.println("HERE:>> " + i);
                    botones[(i + 8) % 8] = DEALER;
                    littleBlind = false;
                } else {
                    botones[(i + 8) % 8] = " ";
                }
            } else {
                botones[(i + 8) % 8] = " ";
            }
        }*/
        switch (pos) {
            case 0: {
                System.out.println("YOU ARE PLAYER #" + ronda.getPlayer(0).getId());
                System.out.println("2 cards are dealt to each player");
                System.out.println("The flop: ");
                break;
            }
            case 1: {
                System.out.println("The turn:  ");
                break;
            }
            case 2: {
                System.out.println("The river:  ");
                break;
            }
        }
        botones[0] = BIGBLIND;
        botones[1] = LITTLEBLIND;
        botones[2] = DEALER;
        for (int i = 3; i < 8; i++) {
            botones[i] = "";
        }

        String handTableStr = "";
        int count = 5;
        for (int i = 0; i < ronda.getTableHand().getSize(); i++, count--) {
            handTableStr += ronda.getTableHand().getCard(i).toString();
        }
        for (int i = 0; i < count; i++) {
            handTableStr += "  ";
        }
        String foo = handTableStr.length() > 16 ? "\t" : "\t\t";
        System.out.println("  ############################################## ");
        System.out.println(" #\t  " + printPlayer(players[0]) + "\t" + printPlayer(players[1]) + "\t" + printPlayer(players[2]) + "\t\t#");
        System.out.println("# \t\t" + botones[0] + " \t" + botones[1] + "\t" + botones[2] + "\t\t #");
        System.out.println("#" + printPlayer(players[7]) + "  " + botones[7] + "\t  " + handTableStr + " " + botones[3] + foo + printPlayer(players[3]) + "\t #"
        );
        System.out.println("#  \t" + botones[6] + "\t" + botones[5] + "\t " + botones[4] + "\t\t\t #");
        System.out.println(" #\t" + printPlayer(players[6]) + "\t" + printPlayer(players[5]) + "\t\t" + printPlayer(players[4]) + "\t\t# ");
        System.out.println("  ##############################################");
    }

    public static String printPlayer(Player player) {
        if (player == null) {
            return "   ";
        } else if (player.isHumanPlayer()) {
            return player.toString();
        } else {
            return "#" + player.getId();
        }
    }

}
