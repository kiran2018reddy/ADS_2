public class KruskalMST {
    /**
     * float.
     */
private static final double FLOATING_POINT_EXCEPTION= 1E-12;
     /**
      * weight.
      */
    private double weight;
    /**
     * queue.
     */// weight of MST
    private Queue<Edge> mst = new Queue<Edge>();
    // edges in MST

    /**
     * Compute a minimum spanning tree (or forest).
     *  of an edge-weighted graph.
     * @param G the edge-weighted graph.
     */
    public KruskalMST(final EdgeWeightedGraph gra) {
        // more efficient to build heap by passing array of edges
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : gra.edges()) {
            pq.insert(e);
        }

        // run greedy algorithm
        UF uf = new UF(gra.V());
        while (!pq.isEmpty() && mst.size() < gra.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += e.weight();
            }
        }

        // check optimality conditions
        assert check(gra);
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights.
     *  in a minimum spanning tree (or forest).
     * @return the sum of the edge.
     *  weights in a minimum spanning tree (or forest).
     */
    public double weight() {
        return weight;
    }


    /**
     * check.
     *
     * @param      gra   The gra
     *
     * @return     { true or false }
     */
    private boolean check(EdgeWeightedGraph gra) {

        // check total weight
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) >
            FLOATING_POINT_EXCEPTION) {

            return false;
        }

        // check that it is acyclic
        UF uf = new UF(gra.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : gra.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println
                ("Not a spanning forest");
                return false;
            }
        }

        // // check that it is a minimal spanning.
        // forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new UF(gra.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) {
                    uf.union(x, y);
                }
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : gra.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " +
                         f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }
}