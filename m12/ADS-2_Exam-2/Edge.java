/******************************************************************************
 *  Compilation:  javac Edge.java
 *  Execution:    java Edge
 *  Dependencies: StdOut.java
 *
 *  Immutable weighted edge.
 *
 ******************************************************************************/

/**
 *  The {@code Edge} class represents a weighted edge in an
 *  {@link EdgeWeightedGraph}. Each edge consists of two integers
 *  (naming the two vertices) and a real-value weight. The data type
 *  provides methods for accessing the two endpoints of the edge and
 *  the weight. The natural order for this data type is by
 *  ascending order of weight.
 *  <p>
 *  For additional documentation, see <a href="https://
 *  algs4.cs.princeton.edu/43mst">Section 4.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
/**
 * Class for edge.
 */
public class Edge implements Comparable<Edge> {
   /**
    * vertex v.
    */
    private final int v;
    /**
     * vertex w.
     */
    private final int w;
    /**
     * weight.
     */
    private final double weight;

    /**
     * Initializes an edge between vertices {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param  v1 one vertex
     * @param  w1 the other vertex
     * @param  weight1 the weight of this edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *         is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public Edge(final int v1, final int w1, final double weight1) {

        this.v = v1;
        this.w = w1;
        this.weight = weight1;
    }

    /**
     * Returns the weight of this edge.
     * The time complexity is O(1).
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns either endpoint of this edge.
     * The time complexity is O(1).
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is different.
     * The time complexity is O(1).
     *
     * from the given vertex.
     *
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    public int other(final int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }
    /**
     * Compares two edges by weight.
     * The time complexity is O(1).
     *
     * Note that {@code compareTo()} is not consistent with {@code equals()},
     * which uses the reference equality implementation
     * inherited from {@code Object}.
     *
     * @param  that the other edge
     * @return a negative integer, zero, or positive
     * integer depending on whether
     *         the weight of this is less than, equal to, or greater than the
     *         argument edge
     */
    @Override
    public int compareTo(final Edge that) {
        return Double.compare(this.weight, that.weight);
    }
}