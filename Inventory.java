/*
 * Inventory.java
 * Bill Xia
 *
 * Created: 1/10/23
 * Updated: 1/10/23
 *
 * Purpose: A superclass meant to be extended by the Village, Town, and
 *          Caravan classes.
 */

 /* Imports */
import java.util.*;

/* Class */
public class Inventory {

    /* MEMBER VARIABLES - - - - - - - - - - - - - - - - - - - - - - - - - - */

    private String name = "_";

    /*
     * The Inventory
     * This data structure maps item names to their integer quantities.
     */
    private Map<String, Integer> inventory = new HashMap<>();

    /*
     * Silver
     * This integer value represents how much silver (currency) the Inventory
     * has.
     */
    private Integer silver = 0;

    /* CONSTRUCTORS - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /* Default Constructor */
    Inventory(String s) { this.name = s; }

    /* GETTER METHODS - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*
     * checkAmountOf()
     * Returns how many of the queried item exist in inventory.
     */
    public Integer checkAmountOf(String item)
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

    /* SETTER METHODS - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*
     * buys_of_for_()
     * Adds some number of a given item to inventory while subtracting some
     * amount from silver. The cost is for the entire purchase.
     */
    public void buys_of_for_(Integer n, String item, Integer cost)
    {
        if (cost > this.silver) {
            throw new RuntimeException(
                this.name + " does not have at least " + cost.toString() + " silver."
            );
        } else {
            this.silver -= cost;
        }

        if (this.inventory.containsKey(item)) {
            Integer prev = this.inventory.get(item);
            this.inventory.put(item, prev + n);
        } else {
            this.inventory.put(item, n);
        }
    }

    /*
     * sells_of_for_()
     * Subtracts some number of a given item from inventory while adding some
     * amount to silver. The cost is for the entire purchase.
     */
    public void sells_of_for_(Integer n, String item, Integer cost)
    {
        if (this.inventory.containsKey(item)) {
            Integer prev = this.inventory.get(item);
            this.inventory.put(item, prev - n);
        } else {
            throw new RuntimeException(
                this.name + " has no " + item + " to sell."
            );
        }

        this.silver += cost;
    }

}
