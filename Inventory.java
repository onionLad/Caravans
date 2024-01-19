/*
 * Inventory.java
 * Bill Xia
 *
 * Created: 1/10/23
 * Updated: 1/16/23
 *
 * Purpose: A superclass meant to be extended by the Village, Town, and
 *          Caravan classes.
 */

/* Imports */
import java.util.*;

/* Class */
public class Inventory {

    /* MEMBER VARIABLES - - - - - - - - - - - - - - - - - - - - - - - - - - */

    protected String name;

    /*
     * The Inventory
     * This data structure maps item names to their integer quantities.
     */
    protected Map<Item, Integer> inventory = new HashMap<>();

    /*
     * Silver
     * This integer value represents how much silver (currency) the Inventory
     * has.
     */
    private Integer silver = 0;

    /* CONSTRUCTORS - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /* Default Constructor */
    Inventory() { this.name = ""; }

    /* String Constructor */
    Inventory(String s) { this.name = s; }

    /* GETTER METHODS - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*
     * toString()
     * Returns the name of the Inventory.
     */
    public String toString()
    {
        return this.name;
    }

    /*
     * checkAmountOf()
     * Returns how many of the queried item exist in inventory.
     */
    public Integer checkAmountOf(Item item)
    {
        if (this.inventory.containsKey(item)) {
            return this.inventory.get(item);
        } else {
            return 0;
        }
    }

    /*
     * checkBalance()
     * Returns how much silver the Inventory has.
     */
    public Integer checkBalance()
    {
        return this.silver;
    }

    /* PRIVATE HELPER METHODS - - - - - - - - - - - - - - - - - - - - - - - */

    /* Adds n items to the Inventory. */
    protected void addToInventory(Item item, Integer n)
    {
        if (this.inventory.containsKey(item)) {
            Integer prev = this.inventory.get(item);
            this.inventory.put(item, prev + n);
        } else {
            this.inventory.put(item, n);
        }
    }

    /* Removes n items from the Inventory. */
    protected void remFromInventory(Item item, Integer n)
    {
        if (this.inventory.containsKey(item) && this.inventory.get(item) > 0) {
            Integer prev = this.inventory.get(item);
            this.inventory.put(item, prev - n);
        } else if (!n.equals(Integer.valueOf(0))) {
            throw new RuntimeException();
        }
    }

    /* SETTER METHODS - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*
     * buys_of_for_()
     * Adds some number of a given item to inventory while subtracting some
     * amount from silver. The cost is for the entire purchase.
     */
    public void buys_of_for_(Integer n, Item item, Integer cost)
    {
        if (cost > this.silver) {
            throw new RuntimeException(
                this.name + " does not have at least " + cost.toString() + " silver."
            );
        } else {
            this.silver -= cost;
        }

        this.addToInventory(item, n);
    }

    /*
     * sells_of_for_()
     * Subtracts some number of a given item from inventory while adding some
     * amount to silver. The cost is for the entire purchase.
     */
    public void sells_of_for_(Integer n, Item item, Integer cost)
    {
        try {
            remFromInventory(item, n);
        } catch (Exception e) {
            throw new RuntimeException(
                this.name + " has no " + item + " to sell."
            );
        }

        this.silver += cost;
    }

}

