package data;

/**
 *
 * @author OnePoker UN
 */
public class DealingDeck extends AbstractDeck {

    private String name;

    public DealingDeck(String type) {
        super(type);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
