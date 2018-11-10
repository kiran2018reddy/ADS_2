/**
 * Class for dijkstra undirected sp.
 */
class DijkstraUndirectedSP {
    /**
     * dist.
     */
    private double[] distTo;
    /**
     * edge.
     */
    private Edge[] edgeTo;
    /**
     * pq.
     */
    private IndexMinPQ<Double> pq;
    /**
     * Constructs the object.
     *
     * @param      g     { parameter_description }
     * @param      s     { parameter_description }
     */
    DijkstraUndirectedSP(final EdgeWeightedGraph g,
                         final int s) {
        for (Edge e : g.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException(
                    "edge " + e + " has negative weight");
            }
        }
        distTo = new double[g.edges()];
        edgeTo = new Edge[g.edges()];

        validateVertex(s);

        for (int v = 0; v < g.edges(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(g.edges());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : g.adj(v)) {
                relax(e, v);
            }
        }

        // check optimality conditions
        //assert check(g, s);
    }

    /**
     * { function_description }.
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
     * distTo.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double distTo(final int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Determines if it has path to.
     *
     * @param      v     { parameter_description }
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
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
     * { function_description }.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        int ver = distTo.length;
        if (v < 0 || v >= ver) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (ver - 1));
        }
    }

}