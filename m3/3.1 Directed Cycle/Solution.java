import java.util.Scanner;
/**
 * Class for directed cycle.
 */
class DirectedCycle {
    /**
     * marked boolean array.
     */
    private boolean[] marked;
    /**
     * edgeTo array.
     */
    private int[] edgeTo;
    /**
     * boolean array.
     */
    private boolean[] onStack;
    /**
     * cycle  - stack.
     */
    private Stack<Integer> cycle;
     /**
      * Constructs the object.
      *
      * @param      diaGraph     { Digraph }
      */

    DirectedCycle(final Digraph diaGraph) {
        marked  = new boolean[diaGraph.vertex()];
        onStack = new boolean[diaGraph.vertex()];
        edgeTo  = new int[diaGraph.vertex()];
        for (int v = 0; v < diaGraph.vertex(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(diaGraph, v);
            }
        }
    }

    /**
     * creates dfs and fills marked array and edgeto array.
     * Thhe time complexity is O(E).
     *
     *
     * @param      diaGraph     { digraph }
     * @param      v     { source vertex }
     */
    private void dfs(final Digraph diaGraph, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : diaGraph.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(diaGraph, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                // assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Determines if it has cycle.
     * Thhe time complexity is O(1).
     *
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }
}
/**
 * class Solution.
 */
final class Solution {
    /**
     * Constructs the object.
     * The time complexity id O(N).
     *
     */
    private Solution() {

    }
    /**
     * reads input.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);
        int vertices = Integer.parseInt(input.nextLine());
        int edges = Integer.parseInt(input.nextLine());
        Digraph dg = new Digraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = input.nextLine().split(" ");
            dg.addEdge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]));
        }

        DirectedCycle dc = new DirectedCycle(dg);
        if (dc.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }

    }
}

