package business;

import data.Player;
import data.Table;

/**
 *
 * @author OnePoker UN
 */
public class AI {

    public static boolean call(Player plyr, Table table) {
        return (plyr.getCredits() > 300) || (table.getPot() < 500);
    }

    public static boolean raise(Player plyr, Table table) {
        return ((plyr.getCredits() > 300) && (table.getDealerPos() == plyr.getId()));
    }

    public static boolean all(Player plyr, Table table) {
        return ((plyr.getCredits() < 200) && (table.getPot() > 500));

    }

}
