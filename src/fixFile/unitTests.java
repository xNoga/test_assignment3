package fixFile;

import org.junit.Test;
import static org.junit.Assert.*;

public class unitTests {

    MyArrayListWithBugs test = new MyArrayListWithBugs();
    // BEFORE REFACTOR ---------------------------------------------------------------------------
    @Test
    public void addTest(){
        test.add(new Object());
        System.out.println("Testing the add() method.");
        assertEquals("Size of the list should be 1", 1, test.size());
    }

    // We are expecting that we get an exception.
    // After refactoring the test should fail because we now can get element at index 0 (might be null though)
    @Test (expected = IndexOutOfBoundsException.class)
    public void getTest(){
        System.out.println("Testing the get() method on an empty list.");
        test.get(0);
    }

    // almost the same as addTest()
    @Test
    public void getSizeTest(){
        System.out.println("Testing the size() method.");
        test.add(new Object());
        test.add(new Object());
        test.add(new Object());
        assertEquals("The result should be 3.", 3, test.size());
    }

    // should succeed before refactor. The current code cannot add to the list if the index is higher than nextFree
    // even though there's a free slot at index 3. We expect an exception.
    // Should fail after refactor when this logical mistake is fixed
    @Test (expected = IndexOutOfBoundsException.class)
    public void addAtIndexTest(){
        System.out.println("Testing the add(int index) method.");
        Object t = new Object();
        test.add(3, t);
    }

    // Remove doesn't really remove the object, but just decrement nextFree, so that
    // when size() is called we get 0.
    // This will still work after refactor, but only because the refactored version actually knows whether size()
    // return the actual amount of objects in the list, and not just nextFree
    @Test
    public void removeTest(){
        System.out.println("Testing the remove(int index) method.");
        test.add(new Object());
        test.remove(0);
        assertEquals("Size of the list should be 0", 0, test.size());

    }
    // --------------------------------------------------------------------------------------------------

    // AFTER REFACTOR ---------------------------------------------------------------------------

    @Test
    public void addAtIndexTestRefactor(){
        System.out.println("Testing the add(int index) method after refactor.");
        Object t = new Object();
        test.add(13, t);
        assertEquals("We should now be able add at whatever index we like. t and test.get(13) should be the same object", t, test.get(13));
    }

    @Test
    public void getTestRefactor(){
        System.out.println("Testing the get() method on an empty list after refactor.");
        assertEquals("We should now be able to get even though the list is null at index", null, test.get(0));
    }

    @Test
    public void removeTestRefactor(){
        System.out.println("Testing the remove(int index) method after refactor.");
        test.add(4, new Object());
        test.remove(4);

        assertEquals("Size of the list should be 0", 0, test.size());

    }

}
