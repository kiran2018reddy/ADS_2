/**
 *the class for dijkstra's algorithm.
 *to find the shortest path.
 */
class DijkstrasSP {
    /**
     *the distTo array to store.
     *distance from one vertex to another.
     */
    private Double[] distTo;
    /**
     *edge to is to store the edge connected.
     */
    private Edge[] edgeTo;
    /**
     *indexed minpq to store the key value.
     *pair.
     */
    private IndexMinPQ<Double> pq;
    /**
     *the graph object.
     */
    private EdgeWeightedGraph graph;
    /**
     *the constructor to initialize the objects.
     *the time complexity is O(E + V).
     * @param      g  graph object.
     * @param      source  The source
     */
    DijkstrasSP(final EdgeWeightedGraph g,
    final int source) {
        graph = g;
        distTo = new Double[graph.vertex()];
        edgeTo = new Edge[graph.vertex()];
        for (int i = 0; i < graph.vertex(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        pq = new IndexMinPQ<Double>(graph.vertex());
        pq.insert(source, distTo[source]);
        while (!pq.isEmpty()) {
            int vertex = pq.delMin();
            for (Edge edge : graph.adj(vertex)) {
                relax(edge, vertex);
            }
        }
    }
    /**
     * vertex is connected to edges.
     * The time complexity is O(logE).
     *
     * @param      edge    The edge
     * @param      vertex  The vertex
     */

    private void relax(final Edge edge, final int vertex) {
        int vertexTwo = edge.other(vertex);
        if (distTo[vertexTwo] > distTo[vertex] + edge.weight()) {
            distTo[vertexTwo] = distTo[vertex] + edge.weight();
            edgeTo[vertexTwo] = edge;
            if (pq.contains(vertexTwo)) {
                pq.decreaseKey(vertexTwo, distTo[vertexTwo]);
            } else {
                pq.insert(vertexTwo, distTo[vertexTwo]);
            }
        }
    }
    /**
     * distace array.
     * The time complexity is O(1).
     *
     *
     * @param      v     { vertex }
     *
     * @return     { distance }
     */
    public double distTo(final int v) {
        return distTo[v];
    }
    /**
     * Determines if it has path to.
     * The time complexity is O(1).
     *
     *
     * @param      v     { vertex }
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     * path to the edge.
     * The time complexity is O(ElogV).
     *
     *
     * @param      v     { vertex }
     *
     * @return     { iterable edge }
     */
    public Iterable<Edge> pathTo(final int v) {
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
     * minimum path distance.
     * The time complexity is O(E).
     *
     *
     * @param      vertex  The vertex
     *
     * @return     { double }
     */
    public double distance(final int vertex) {
        double sum = 0;
        for (Edge each : pathTo(vertex)) {
            sum += each.weight();
        }
        return sum;
    }
}