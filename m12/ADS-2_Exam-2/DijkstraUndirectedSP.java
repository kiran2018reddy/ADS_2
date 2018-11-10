/**
 * Class for dijkstra undirected sp.
 * Complexity: O(ElogV)
 */
public class DijkstraUndirectedSP {
    /**
     * { distTo array }.
     */
    // distTo[v] = distance of shortest s->v path.
    private double[] distTo;
    /**
     * { edgeTo array }.
     */
    // edgeTo[v] = last edge on shortest s->v path.
    private Edge[] edgeTo;
    /**
     * { Index queue }.
     */
    // priority queue of vertices
    private IndexMinPQ<Double> pq;
    /**
     * Constructs the object.
     *
     * @param      graph  The graph
     * @param      s      { parameter_description }
     */
    public DijkstraUndirectedSP(final EdgeWeightedGraph graph, final int s) {
        for (Edge e : graph.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException(
                    "edge " + e + " has negative weight");
            }
        }

        distTo = new double[graph.vertexcount()];
        edgeTo = new Edge[graph.vertexcount()];

        validateVertex(s);

        for (int v = 0; v < graph.vertexcount(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(graph.vertexcount());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : graph.adj(v)) {
                relax(e, v);
            }
        }

        // check optimality conditions
        assert check(graph, s);
    }

    /**
     * { relaxing edge }.
     * Complexity: O(1).
     *
     * @param      e     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void relax(final Edge e, final int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }
    /**
     * { To compute distance }.
     * Complexity: O(1).
     * @param      v     { Destination }
     *
     * @return     { double value }
     */
    public double distTo(final int v) {
        validateVertex(v);
        return distTo[v];
    }
    /**
     * Determines if it has path to.
     *Complexity: O(1)
     * @param      v     { destination }
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     * { PathTo function }.
     *
     * @param      v     { destination }
     *
     * @return     { Iterable }
     */
    public Iterable<Edge> pathTo(final int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
    /**
     * { check function }.
     *Complexity: O(E)
     * @param      graph  The graph
     * @param      s      { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(final EdgeWeightedGraph graph, final int s) {

        // check that edge weights are nonnegative
        for (Edge e : graph.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < graph.vertexcount(); v++) {
            if (v == s) {
                continue;
            }
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges
        // e = v-w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < graph.vertexcount(); v++) {
            for (Edge e : graph.adj(v)) {
                int w = e.other(v);
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges
        // e = v-w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < graph.vertexcount(); w++) {
            if (edgeTo[w] == null) {
                continue;
            }
            Edge e = edgeTo[w];
            if (w != e.either() && w != e.other(e.either())) {
                return false;
            }
            int v = e.other(w);
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }
    /**
     * { validating function }.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        int val = distTo.length;
        if (v < 0 || v >= val) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (
                    val - 1));
        }
    }
}