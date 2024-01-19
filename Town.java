/*
 * Town.java
 * Bill Xia
 *
 * Created: 1/18/23
 * Updated: 1/18/23
 *
 * Purpose: Extends the Inventory superclass, implementing all methods that a
 *          Town may need.
 */

/* Imports */
import java.util.*;

/* Class */
public class Town extends Inventory {

    /* MEMBER VARIABLES - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*
     * Produces
     * A List of finished goods that the Town can produce.
     */
    private List<Item> produces;

    /*
     * Population
     * Represents the amount of workers in the Town. Assume that everyone
     * living in the Village is a worker.
     */
    private Integer population;

    /* CONSTRUCTORS - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /* Town Constructor */
    Town(String name, Map<Item, Integer> inventory, int population,
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
     *    - Manufacturing of resources.
     *    - Consumption of resources.
     *    - Trading of resources.
     * Input: A Map of finished goods to their respective recipes.
     */
    public void onStep(Map<Item, Map<Item, Integer>> recipeMap)
    {
        /* Manufacture finished goods */
        Integer count = 0;
        while (count < population) {
            for (Item good : produces) {
    
                Map<Item, Integer> currRecipe = recipeMap.get(good);
    
                /* Confirm that all necessary components exist */
                try {
                    for (Item component : currRecipe.keySet()) {
                        if (this.checkAmountOf(component) < currRecipe.get(component)) {
                            throw new RuntimeException();
                        }
                    }
                } catch (RuntimeException e) {
                    continue;
                }
    
                /* Remove necessary items */
                for (Item component : recipeMap.keySet()) {
                    this.remFromInventory(component, currRecipe.get(component));
                }
    
                /* Add new material to inventory */
                this.addToInventory(good, 1);
    
                /* Only 1 item can be manufactured per worker */
                count += 1;
                if (count >= this.population) {
                    break;
                }

            }
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
