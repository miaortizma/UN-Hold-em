package business;

import data.Hand;
import data.Player;
import data.Table;

/**
 *
 * @author OnePoker UN
 */
public class AI {

    public static int holdCardsValue(Hand hand) {
        int[] rank = hand.getCardRanks();
        int[] suit = hand.getCardSuits();
        int value = rank[0] + rank[1];
        if (suit[0] == suit[1]) {
            value += 3;
        }
        if (rank[1] - rank[0] == 2 || rank[0] - rank[1] == 2) {
            value += 2;
        }
        if (rank[1] - rank[0] == 1 || rank[0] - rank[1] == 1) {
            value += 3;
        }
        if (rank[1] - rank[0] == 0 || rank[0] - rank[1] == 0) {
            value += 3;
        }
        return value;
    }

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
