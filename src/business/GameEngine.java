package business;

import data.Player;
import data.Table;
import java.util.Collections;
import java.util.Random;
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
    private static GameEngine engine;
    public static UI ui;
    private static RoundThread round;
    private int menu;
    private int hands;

    private GameEngine() {
        RND = new Random();
        hands = 0;
    }

    private void selectUI(String[] args) {
        if (args.length == 0) {
            ui = new UISwing();
        } else if (args[0].equals("text")) {
            ui = new UIText();
        } else {
            ui = new UISwing();
        }
    }

    public void startRound() {
        round = new RoundThread();
        round.start();
    }

    public void notifyWinner() {
        Table table = round.getTable();
        int winners = dealRewards();
        ui.printRoundStandings(table, winners);
        ui.printStandings(table);
        table.setPot(0);
        menu();
    }

    public static GameEngine getInstance() {
        if (engine == null) {
            engine = new GameEngine();
        }
        return engine;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        getInstance();
        engine.selectUI(args);
        ui.printWelcome();
        engine.menu();
    }

    public void menu() {
        while (true) {
            if (ui instanceof UISwing) {
                return;
            }
            String[] options = {"Start a Tournament", "Never played poker before?", "Command List", "Retire"};
            if (hands > 0) {
                options[0] = "Next hand";
            }
            menu = 0;
            ui.printMenuOption("Main menu", "Option:", options);
            ui.notifyMainMenu();
            switch (menu) {
                case 1: {
                    System.out.println("Start");
                    hands++;
                    startRound();
                    return;
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
                    checkCommand("Exit", true);
                }
                default: {
                    throw new IllegalArgumentException("Not a valid command");
                }

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
    public boolean checkCommand(String input, boolean print) {
        switch (input) {
            case "Exit": {
                if (print) {
                    ui.printExit();
                }
                System.exit(0);
            }
            case "Info": {
                if (print) {
                    ui.printInfo();
                }
                return true;
            }
            case "Help": {
                if (print) {
                    ui.printHelp();
                }
                return true;
            }
            case "Hands": {
                ui.printHands();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public RoundThread getRoundThread() {
        return round;
    }

    /**
     * Assumes players is ordered from best to worst
     *
     * Returns the numbers of players who won e.g 2 means tie
     *
     * @return
     */
    public static int dealRewards() {
        Table table = round.getTable();
        int playersSize = table.getPlayersSize();
        if (!(playersSize == 1)) {
            engine.compareHands(table);
        } else {
            Player winner = table.getPlayer(0);
            winner.setCredits(winner.getCredits() + table.getPot());
            winner.setElo(winner.getElo() + 25);
            for (int i = 1; i < table.getPlayersSize(); i++) {
                table.getPlayer(i).setElo(table.getPlayer(i).getElo() - 5);
            }
            table.setPot(0);
            return 1;
        }
        //checks tie
        if (table.getPlayer(1).compareTo(table.getPlayer(0)) == 0) {
            Player tie1 = table.getPlayer(0), tie2 = table.getPlayer(1);
            tie1.setCredits(tie1.getCredits() + table.getPot() / 2);
            tie2.setCredits(tie2.getCredits() + table.getPot() / 2);
            tie1.setElo(tie1.getElo() + 10);
            tie2.setElo(tie2.getElo() + 10);
            for (int i = 2; i < table.getPlayersSize(); i++) {
                table.getPlayer(i).setElo(table.getPlayer(i).getElo() - 5);
            }
            return 2;
        } else {
            Player winner = table.getPlayer(0);
            winner.setCredits(winner.getCredits() + table.getPot());
            winner.setElo(winner.getElo() + 25);
            for (int i = 1; i < table.getPlayersSize(); i++) {
                table.getPlayer(i).setElo(table.getPlayer(i).getElo() - 5);
            }
            return 1;
        }
    }

    /**
     * NOTES/ TO DO: -Should add some variable to notify that there is a tie
     * -Should add different method that controls who wins etc. sets each table
     * player hand as its possible Hand after merging its hand and the
     * comunitary hand, sets each player kickers.
     *
     * Sorts the list of players (The criteria is their hands and kickers)
     *
     * @param table
     */
    public void compareHands(Table table) {
        table.getPlayers().stream().forEach((plyr) -> {
            plyr.setHand(HandHelper.bestHand(plyr.getHand(), table.getTableHand()));
        });
        Collections.sort(table.getPlayers(), Collections.reverseOrder());
    }

    /**
     * @return the menu
     */
    public int getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(int menu) {
        this.menu = menu;
    }
}
