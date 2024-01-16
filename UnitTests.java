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
import java.lang.reflect.*;

/* Class */
public class UnitTests {

    /* INVENTORY - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test
    static void initInventory() {
        Inventory i = new Inventory("test");
    }

    @Test
    static void verify_initInventory() {
        Inventory i = new Inventory("test");

        assert(i.toString() == "test");
        assert(i.checkAmountOf("thing").equals(0));
        assert(i.checkBalance().equals(0));
    }

    @Test
    static void verify_buysOfFor() {
        Inventory i = new Inventory("test");

        i.buys_of_for_(10, "thing", 0);
        assert(i.checkAmountOf("thing").equals(10));
        assert(i.checkBalance().equals(0));
    }

    @Test
    static void verify_sellsOfFor() {
        Inventory i = new Inventory("test");

        i.sells_of_for_(0, "thing", 10);
        assert(i.checkAmountOf("thing").equals(0));
        assert(i.checkBalance().equals(10));
    }

    /* TEST MAIN - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    public static void main(String[] args) {

        /* Run all your tests. */
        Class<?> c  = UnitTests.class;
        Method[] ms = c.getMethods();
        UnitTests t = new UnitTests();
        for (Method m : ms) {
            if (m.getAnnotation(Test.class) != null) {
                try {
                    m.invoke(t);
                } catch (Exception e) {
                    System.out.println("An error occured while testing " + m.toString());
                }
            }
        }

        /* If all the tests passed, print message. */
        System.out.println("All Tests Passed");

    }

}
