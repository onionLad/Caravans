/*
 * UnitTests.java
 * Bill Xia
 *
 * Created: 1/16/23
 * Updated: 1/16/23
 *
 * Purpose: Stores all the unit tests for the Caravans program.
 */

/* Imports */
import java.util.*;
import java.lang.reflect.*;

/* Class */
public class UnitTests {

    /* TESTING VARS - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    private static Item testItem;
    private static Item grain;
    private static Item bread;
    private static Map<Item, Integer> testInventory = new HashMap<>();
    private static List<Item>         testProduces  = new ArrayList<>();
    private static Map<Item, Float>   testPRatio    = new HashMap<>();

    /* CONSTRUCTORS - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    UnitTests() {
        testItem = new Item("test", ItemType.RAW);
        grain    = new Item("Grain", ItemType.RAW);
        bread    = new Item("Bread", ItemType.MADE);

        testInventory.put(grain, 100);
        testInventory.put(bread, 100);

        testProduces.add(grain);

        testPRatio.put(grain, 1.5f);
    }

    /* INVENTORY - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test
    public static void initInventory() {
        Inventory i = new Inventory("test");
    }

    @Test
    public static void verify_initInventory() {
        Inventory i = new Inventory("test");

        assert(i.toString() == "test");
        assert(i.checkAmountOf(testItem).equals(0));
        assert(i.checkBalance().equals(0));
    }

    @Test
    public static void verify_buysOfFor() {
        Inventory i = new Inventory("test");

        i.buys_of_for_(10, testItem, 0);
        assert(i.checkAmountOf(testItem).equals(10));
        assert(i.checkBalance().equals(0));
    }

    @Test
    public static void verify_sellsOfFor() {
        Inventory i = new Inventory("test");

        i.sells_of_for_(0, testItem, 10);
        assert(i.checkAmountOf(testItem).equals(0));
        assert(i.checkBalance().equals(10));
    }

    /* VILLAGE - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test
    public static void verify_initVillage() {
        Village v = new Village("test", testInventory, 10, testProduces);

        assert(v.toString().equals("test"));
        assert(v.checkAmountOf(grain).equals(100));
        assert(v.checkBalance().equals(0));
        assert(v.getPopulation().equals(10));
    }

    @Test
    public static void verify_onStep1() {
        Village v = new Village("test", testInventory, 10, testProduces);

        v.onStep(testPRatio);
        assert(v.checkAmountOf(grain).equals(115));
        assert(v.checkAmountOf(bread).equals(90));
    }

    /* TEST MAIN - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    public static void main(String[] args) {

        /* Run all your tests. */
        Class<?> c  = UnitTests.class;
        Method[] ms = c.getMethods();
        UnitTests t = new UnitTests();

        for (Method m : ms) {
            if (m.isAnnotationPresent(Test.class)) {
                // System.out.println("  " + m.toString());
                String mName = m.toString().split(" ")[3];
                try {
                    m.invoke(t);
                    System.out.println("  " + mName + " passed");
                } catch (Exception e) {
                    System.out.println("An error occured while testing " + mName + ":");
                    if (e instanceof InvocationTargetException) {
                        System.out.println("  " + e.getCause());
                    }
                }
            }
        }

        /* If all the tests passed, print message. */
        System.out.println("All Tests Passed");

    }

}
