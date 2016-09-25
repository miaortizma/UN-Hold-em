package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OnePoker UN 
 */
public class Tournament {

    private String defaultDifficulty;
    private List<Table> rounds;

    public Tournament() {
        rounds = new ArrayList<>();
        rounds.add(new Table());

    }

    public Table getRound() {
        return getRounds().get(getRounds().size() - 1);
    }

    public void example() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * @return the defaultDifficulty
     */
    public String getDefaultDifficulty() {
        return defaultDifficulty;
    }

    /**
     * @param defaultDifficulty the defaultDifficulty to set
     */
    public void setDefaultDifficulty(String defaultDifficulty) {
        this.defaultDifficulty = defaultDifficulty;
    }

    /**
     * @return the rounds
     */
    public List<Table> getRounds() {
        return rounds;
    }

    /**
     * @param rounds the rounds to set
     */
    public void setRounds(List<Table> rounds) {
        this.rounds = rounds;
    }
}
