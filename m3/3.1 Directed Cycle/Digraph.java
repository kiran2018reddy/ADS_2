/**
 * Class for digraph.
 */
public class Digraph {

    /**
     * number of vertices.
     */
    private final int vertex;
    /**
     * Number of edges.
     */
    private int edge;
    /**
     * array of bag class.
     */
    private Bag<Integer>[] adj;
    /**
     * number of incoming edges.
     */
    private int[] indegree;
    /**
     * Constructs the object.
     * Thhe time complexity is O(N).
     *
     * @param      vertex1     { vertices of int type }
     */
    public Digraph(final int vertex1) {
        this.vertex = vertex1;
        this.edge = 0;
        indegree = new int[vertex];
        adj = (Bag<Integer>[]) new Bag[vertex];
        for (int v = 0; v < vertex; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     * Number of vertices.
     * Thhe time complexity is O(1).
     *
     *
     * @return     { integer }
     */

    public int vertex() {
        return vertex;
    }
    /**
     * Number of edges.
     * Thhe time complexity is O(1).
     *
     *
     * @return     { int }
     */

    public int edge() {
        return edge;
    }
    /**
     * Adds an edge.
     * Thhe time complexity is O(1).
     *
     *
     * @param      v     { vertex 1 }
     * @param      w     { vertex 2 }
     */

    public void addEdge(final int v, final int w) {
        adj[v].add(w);
        indegree[w]++;
        edge++;
    }
    /**
     * iterable.
     * Thhe time complexity is O(E).
     *
     *
     * @param      v     { vertex }
     *
     * @return     { integer of adj list }
     */


    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }

}
