/*
 * Village.java
 * Bill Xia
 *
 * Created: 1/16/23
 * Updated: 1/18/23
 *
 * Purpose: Extends the Inventory superclass, implementing all methods that a
 *          Village may need.
 */

/* Imports */
import java.util.*;

/* Class */
public class Village extends Inventory {

    /* MEMBER VARIABLES - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*
     * Produces
     * A List of raw materials that the vVillage can produce.
     */
    private List<Item> produces;

    /*
     * Population
     * Represents the amount of workers in the Village. Assume that everyone
     * living in the Village is a worker.
     */
    private Integer population;

    /* CONSTRUCTORS - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /* Village Constructor */
    Village(String name, Map<Item, Integer> inventory, int population,
            List<Item> produces) {

        this.name = name;

        if (inventory != null) {
            for (Item item : inventory.keySet()) {
                this.addToInventory(item, inventory.get(item));
            }
        }

        this.population = population;

        if (produces == null) {
            this.produces = new ArrayList<>();
        } else {
            this.produces = produces;
        }

    }

    /* GETTER METHODS - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    public Integer getPopulation()
    {
        return this.population;
    }

    /* ONSTEP - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*
     * onStep()
     * Every "step" in the program, this function gets called. It handles the
     * following operations:
     *    - Production of resources.
     *    - Consumption of resources.
     *    - Trading of resources.
     * Input: A Map that tells the Village how much of a given raw material a
     *        single villager can produce.
     */
    public void onStep(Map<Item, Float> vRatio)
    {
        /* Produce raw materials */
        for (Item material : produces) {
            Integer produced = (int) Math.floor(vRatio.get(material) * population.floatValue());
            addToInventory(material, produced);
        }

        /* Consume finished goods */
        for (Item i : this.inventory.keySet()) {
            if (i.itemType().equals(ItemType.MADE)) {
                try {
                    remFromInventory(i, population);
                } catch (Exception e) {
                    System.err.println(
                        "Village " + this.name + " ran out of " + i.toString()
                    );
                    throw new RuntimeException();
                }
            }
        }
    }

}
