package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OnePoker UN 
 */
public class Tournament {

    private String defaultDifficulty;
    private List<Round> rounds;

    public Tournament() {
        rounds = new ArrayList<>();
        rounds.add(new Round());

    }

    public Round getRound() {
        return rounds.get(rounds.size() - 1);
    }

    public void example() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
