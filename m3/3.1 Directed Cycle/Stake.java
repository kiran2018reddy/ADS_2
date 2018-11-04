/**
 * class list.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> {
    /**
     * size of the stack.
     */
    private int n;
    /**
     * Node type.
     */
    private Node first;

    /**
     * Class for node.
     */
    private class Node {
        /**
         * item of type item.
         */
        private Item item;
        /**
         * Node next.
         */
        private Node next;
    }

    /**
     * Constructs the object.
     */
    public Stack() {
        first = null;
        n = 0;
    }

    /**
     * Determines if empty.
     * Thhe time complexity is O(1).
     *
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * size of the stack.
     * Thhe time complexity is O(1).
     *
     *
     * @return     { int }
     */
    public int size() {
        return n;
    }
    /**
     * pushes element.
     * Thhe time complexity is O(1).
     *
     *
     * @param      item  The item
     */

    public void push(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
}

