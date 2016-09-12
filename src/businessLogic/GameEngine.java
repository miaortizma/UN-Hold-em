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

        //tests();
    }

    public static void startGame() {
        int menu = 0;
        printWelcome();
        while (true) {
            try {
                printMainMenu();
                menu = askMainMenu();
                switch (menu) {
                    case 0: {
                        //do nothing
                        break;
                    }
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
                        break;
                    }
                    case 4: {
                        checkCommand("<Exit>", true);
                    }
                    default: {
                        throw new IllegalArgumentException("Not a valid command", null);
                    }

                }
            } catch (Exception ex) {
                //System.out.println(ex.getMessage());
                //ex.printStackTrace();
                printError();
            }
        }
    }

    private static void tests() {
        HandAnalyserTest.test();
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
