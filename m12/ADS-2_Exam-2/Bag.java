import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */

public class Bag<Item> implements Iterable<Item> {
    /**
     *  number of elements in bag.
     */
    private int num;
    /**
     * beginning of bag.
     */
    private Node first;

    /**
     * Class for node.
     */

    private class Node {
        /**
         * item of type node.
         */
        private Item item;
        /**
         * Node of type node.
         */
        private Node next;
    }

    /**
     * Constructs the object.
     */

    public Bag() {
        first = null;
        num = 0;
    }

    /**
     * Determines if empty.
     * The time complexity is O(1).
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * returns size of the bag.
     * The time complexity is O(1).
     *
     *
     * @return     { int }
     */
    public int size() {
        return num;
    }

    /**
     * adds element into bag.
     * The time complexity is O(1).
     *
     *
     * @param      item  The item
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        num++;
    }
    /**
     * determines if there is vertex or not.
     * The time complexity is O(N).
     *
     *
     * @param      item  The item
     *
     * @return     { true or false }
     */

    public boolean contains(final Item item) {
        Node n = first;
        while (n != null) {
            if (n.item == item) {
                return true;
            }
            n = n.next;
        }
        return false;
    }


    /**
     * Return an iterator that iterates over the items in the bag.
     * The time complexity is O(N).
     *
     *
     * @return     { iterator }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    /**
     * Class for list iterator.
     */

    private class ListIterator implements Iterator<Item> {
        /**
         * Node.
         */
        private Node current = first;
        /**
         * Determines if it has next.
         * The time complexity is O(1).
         *
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext()  {
            return current != null;
        }
        /**
         * removes.
         *The time complexity is O(1).
         *
         *
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * gives next node.
         *The time complexity is O(1).
         *
         *
         * @return     { item }
         */

        public Item next() {
            if (!hasNext()) {
                 throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}



