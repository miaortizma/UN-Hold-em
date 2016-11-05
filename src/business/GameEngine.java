package business;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.UI;
import ui.UISwing;
import ui.UIText;

/**
 *
 *
 * @author OnePoker UN
 */
public class GameEngine {

    public static Random RND;
    private static GameEngine instance;
    public static UI ui;
    private static RoundThread round;

    private static void selectUI(String[] args) {
        if (args.length == 0) {
            ui = new UISwing();
        } else if (args[0].equals("text")) {
            ui = new UIText();
        } else {
            ui = new UISwing();
        }
    }

    public static void startRound() {
        round = new RoundThread();
        round.start();
    }

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
        selectUI(args);
        getInstance();
        menu();
    }

    public static void menu() {
        if (ui instanceof UISwing) {
            return;
        }
        int menu = 0;
        ui.printWelcome();
        menu = ui.askMenuOption();

        switch (menu) {
            case 1: {
                startRound();
                break;
            }
            case 2: {
                ui.printHelp();
                break;
            }
            case 3: {
                ui.printCommands();
                break;
            }
            case 4: {
                checkCommand("<Exit>", true);
            }
            default: {
                throw new IllegalArgumentException("Not a valid command");
            }

        }
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
                    ui.printExit();
                }
                System.exit(0);
            }
            case "<Info>": {
                if (print) {
                    ui.printInfo();
                }
                return true;
            }
            case "<Help>": {
                if (print) {
                    ui.printHelp();
                }
                return true;
            }
            case "<Hands>": {
                ui.printHands();
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static void stopRoundThread(){
        try {
            round.wait();
        } catch (InterruptedException ex) {
            ui.printError(ex);
        }
    }
}
