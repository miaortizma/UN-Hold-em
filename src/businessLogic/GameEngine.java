/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import tests.HandAnalyserTest;
import static businessLogic.RoundHandler.*;
import data.*;
import java.util.Random;
import static ui.UI.*;

/**
 *
 * @author OnePoker UN
 */
public class GameEngine {

    public static final Random RND = new Random();
    private static GameEngine instance = null;
    private static Tournament tournament;

    private GameEngine() {
    }

    private static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //startGame();
        tests();
    }

    public static void startGame() {
        int menu = 0;
        printWelcome();

        while (true) {
            try {
                printMenu();
                menu = askMenu();
                switch (menu) {
                    case 1: {
                        playRound(new Round());
                        break;
                    }
                    case 2: {
                        printHelp();
                        break;
                    }
                    case 3: {
                        printCommands();
                    }
                    default: {
                        throw new IllegalArgumentException("Not a valid command", null);
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                printError();
            }
        }
    }

    private static void tests() {
        HandAnalyserTest.test();
    }

    public static void askUser() {
        while (true) {
            try {

            } catch (Exception e) {
                System.out.println(e.getMessage());
                printError();
            }
        }
    }

    public static boolean checkCommand(String input, boolean print) {
        switch (input) {
            case "<Exit>": {
                if (print) {
                    printExit();
                }
                System.exit(0);
            }
            case "<Info>": {
                if (print) {
                    printInfo();
                }
                return true;
            }
            case "<Help>": {
                if (print) {
                    printHelp();
                }
                return true;
            }
            default: {
                return false;
            }
        }
    }

}
