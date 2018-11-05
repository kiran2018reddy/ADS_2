/**
 * Class for uf.
 */
public class UF {
    /**
     * parent array of integer type.
     */
    private int[] parent;
    /**
     * rank array of byte type.
     */
    private byte[] rank;
    /**
     * integer variable.
     */
    private int count;
    /**
     * Constructs the object.
     *
     * @param      n   integer variable.
     */
    public UF(final int n) {
        if (n < 0) {
            throw new
            IllegalArgumentException();
        }
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    /**
     * Searches for the first match.
     *
     * @param      p     integer variable.
     *
     * @return parent.
     */
    public int find(final int p) {
        validate(p);
        int x = p;
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites
     *  {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(final int p, final int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(final int p, final int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        // make root of smaller rank point to root of larger rank
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }
    /**
     * validate method.
     *
     * @param      p     { parameter_description }
     */
    private void validate(final int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException(
                "index " + p + " is not between 0 and "
                 + (n - 1));
        }
    }
}