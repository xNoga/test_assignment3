package fixFile;

import java.util.ArrayList;

/**
 * Class with bugs.
 *
 *
 */
public class MyArrayListWithBugs {

    private Object[] list;
    private ArrayList<Object> list2 = new ArrayList<>();
    // int nextFree; this is stupid and should be removed

    // Creates a new empty list
    public MyArrayListWithBugs()
    {
        list = new Object[5];
        // nextFree = 0; this is stupid and should be removed
    }

    public int getNextFree(){
        int result = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null){
                result = i;
                break;
            }
        }
        return result;
    }

    // Inserts object at the end of list
    public void add(Object o)
    {
        int nextFree = getNextFree();
        // check capacity
        if (list.length <= nextFree)
            list = getLongerList(0);

        list[nextFree] = o;
        // nextFree++; this is stupid and should be removed
    }

    // Returns the number of objects in the list
    public int size()
    {
        int result = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null){
                result ++;
            }
        }
        return result;
    }

    // Returns a reference to the object at position index
    // Throws IndexOutOfBoundsException
    public Object get(int index)
    {
        if(index < 0 || index > list.length) // remove '=' from first check and add check for index bigger than list.length
            throw new IndexOutOfBoundsException("Error (get): Invalid index" +
                    index);

        return list[index]; // we can live with returning null if no object exists on that index
    }

    // Inserts object at position index
    // Throws IndexOutOfBoundsException
    public void add(int index, Object o)
    {
        if(index < 0) // remove nextFree check.
            throw new IndexOutOfBoundsException("Error (add): Invalid index" +
                    index);

        // check capacity
        if (list.length <= index) // should check if index is bigger than list.length, and then increase size.
            list = getLongerList(index);

        // this doesn't make any sense to me. Let's remove it.

        // Shift elements upwards to make position index free
        // Start with last element and move backwards
        //for (int i = nextFree-1; i > index; i--) {
          //  list[i] = list[i-1];
        //}

        // we just add at whatever index. If something already exists at that index, then override.
        list[index] = o;
    }

    // Removes object at position index
    // Returns a reference to the removed object
    // Throws IndexOutOfBoundsException
    public void remove(int index) // debugger can see that the returnvalue is never used, so we should use void instead
    {
        if (index < 0 || index > list.length) // should instead check if index is bigger than list.length
            throw new IndexOutOfBoundsException("Error (remove): Invalid index"
                    + index);

        // this doesn't make any sense to me. Let's remove it.

        // Shift elements down to fill indexed position
        // Start with first element
        //for (int i = index; i < nextFree-1; i++) {
        //    list[i] = list[i+1];
        //}
        // -nextFree--;


        list[index] = null; // just set the object to null at index
    }

    //============== private helper methods ==========
    // create a list with double capacity and
    // copy all elements to this
    private Object[] getLongerList(int index) { // add index as input
        Object[] tempList;
        if (index != 0){ // if we give an index, then just make the new list as big as the index
            tempList = new Object[index+1];
        } else { // if we just run out of space keep the code from before refactor
            tempList = new Object[list.length*2];

        }
        // debugger is telling us we are manually are copying array. We should instead use arraycopy
        //for (int i=0; i< list.length;i++) {
        //    tempList[i] = list[i];
        //}

        System.arraycopy(list, 0, tempList, 0, list.length);

        return tempList;
    }


}