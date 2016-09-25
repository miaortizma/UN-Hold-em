package businessLogic;

import tests.HandAnalyserTest;
import static businessLogic.TableHandler.*;
import data.*;
import java.util.Random;
import static ui.UI.*;

/**
 *
 *
 * @author OnePoker UN
 */
public class GameEngine {

    public static Random RND;
    private static GameEngine instance;
    private static Tournament tournament;

    private GameEngine() {
        Random RND = new Random();
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
        getInstance();
        startGame();
    }

    public static void startGame() {
        int menu = 0;
        printWelcome();
        while (true) {
            try {
                printMainMenu();
                menu = askInt("\nOption: ");
                if (menu < 1 || menu > 4) {
                    throw new IllegalArgumentException("Not a menu option");
                }
                switch (menu) {
                    case 1: {
                        playRound(new Table());
                        break;
                    }
                    case 2: {
                        printHelp();
                        break;
                    }
                    case 3: {
                        printCommands();
                        break;
                    }
                    case 4: {
                        checkCommand("<Exit>", true);
                    }
                    default: {
                        throw new IllegalArgumentException("Not a valid command");
                    }

                }
            } catch (Exception ex) {
                printError(ex);
            }
        }
    }

    private static void tests() {
        HandAnalyserTest.test();
    }

    /**
     * Checks if input is a command
     *
     * @param input the input to be tested
     * @param print sometimes commands are called by system internals, so there
     * is no need to print commands(Subject to change)
     * @return true if input is a command, false otherwise
     */
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
            case "<Test>": {
                HandAnalyserTest.test();
                return true;
            }
            case "<Hands>": {
                printHands();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
