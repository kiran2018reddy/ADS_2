/**
 * Class for cc.
 */
public class CC {
    /**
     * marked[v] = has vertex v been marked?.
     */
    private boolean[] marked;
    /**
     * id[v] = id of connected component containing v.
     */
    private int[] id;
    /**
     * size[id] = number of vertices in given component.
     */
    private int[] size;
    /**
     * number of connected components.
     */
    private int count;

    /**
     * Constructs the object.
     *
     * @param      g     { parameter_description }
     */
    public CC(final Graph g) {
        marked = new boolean[g.ve()];
        id = new int[g.ve()];
        size = new int[g.ve()];
        for (int v = 0; v < g.ve(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    /**
     * { function_description }.
     *
     * @param      g     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

   /**
    * Returning the values in id.
    *
    * @param      v     { parameter_description }
    *
    * @return     { description_of_the_return_value }
    */
    public int id(final int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * Size of the.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int size(final int v) {
        validateVertex(v);
        return size[id[v]];
    }

   /**
    * Count.
    *
    * @return     { description_of_the_return_value }
    */
    public int count() {
        return count;
    }

    /**
     * Connected check.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public boolean connected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }
    /**
     * Validate vertex.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        int v1 = marked.length;
        if (v < 0 || v >= v1) {
            throw new IllegalArgumentException("vertex "
                + v + " is not between 0 and " + (v1 - 1));
        }
    }
}

