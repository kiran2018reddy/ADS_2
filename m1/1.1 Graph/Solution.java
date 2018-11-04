import java.util.Scanner;

/**
 * Interface for graph.
 */
interface Graph {
    /**
     * { vertices }
     *
     * @return     { description_of_the_return_value }
     */
    int vertices();
    /**
     * { edges }.
     *
     * @return     { description_of_the_return_value }.
     */
    int edges();
    /**
     * Adds an edge.
     *
     * @param      v     { int }
     * @param      w     { int }
     */
    void addEdge(int v, int w);
    /**
     * { iterable }.
     *
     * @param      v     {int}.
     *
     * @return     { int }.
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { int }
     * @param      w     { int }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}

/**
 * Class for newgraph.
 */
class Newgraph implements Graph {
    /**
     * { vertices }.
     */
    private int vertices;
    /**
     * { edges }.
     */
    private int edges;
    /**
     * { bag adj}.
     */
     private Bag<Integer>[] adj;
     /**
      * { string tokens }.
      */
     private String[] tokens;

     /**
      * Constructs the object.
      */
     Newgraph() {
    }
    /**
     * Constructs the object.
     *
     * @param      vert  The vertical
     */
    Newgraph(final int vert) {
        this.vertices = vert;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[vert];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
    Newgraph(final Scanner scan) {
        this.vertices = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
        int edg = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edg; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    /**
     * { vertices }.
     *
     * @return     { description_of_the_return_value }
     */
    public int vertices() {
        return vertices;
    }
    /**
     * {edges }.
     *
     * @return     { description_of_the_return_value }
     */
    public int edges() {
        return edges;
    }
    /**
     * Adds an edge.
     *
     * @param      vertexOne  The vertex one
     * @param      vertexTwo  The vertex two
     */
    public void addEdge(final int vertexOne, final int vertexTwo) {
        if (vertexOne == vertexTwo) {
            return;
        }
        if (!hasEdge(vertexOne, vertexTwo)) {
        adj[vertexOne].add(vertexTwo);
        adj[vertexTwo].add(vertexOne);
        edges++;
        }
    }
    /**
     * { function_description }.
     *
     * @param      vertex  The vertex
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int vertex) {
        return adj[vertex];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int k : adj[v]) {
                if (k == w) {
                    return true;
                }
        } return false;
    }
    /**
     * { show }.
     *
     * @param      vert       The vertical
     * @param      edg        The edg
     * @param      tok        The tok
     *
     * @throws     Exception  { exception_description }
     */
    public void show(final int vert, final int edg,
       final String[] tok) throws Exception {
        if (edg <= 1 && vert <= 1) {
            System.out.println(vertices()
                + " vertices" + ", " + edges() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertices()
                + " vertices" + ", " + edges() + " edges");
            for (int i = 0; i < tok.length; i++) {
            String str = "";
            str = tok[i] + ": ";
            for (int k : adj(i)) {
                str = str + tok[k] + " ";
            }
            System.out.println(str);
            }
        }
    }
    /**
     * shows function of adjacency matrix list.
     *
     * @param      vertex1        int
     * @param      edge1         int
     *
     * @throws     Exception  No edges
     */
    public void show1(final int vertex1,
        final int edge1) throws Exception {
        if (edge1 <= 1 && vertex1 <= 1) {
            System.out.println(vertices()
                + " vertices" + ", " + edges() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertices()
                + " vertices" + ", " + edges() + " edges");
            int[][] disp = new int[vertex1][vertex1];
            for (int i = 0; i  < vertex1; i++) {
                for (int j = 0; j < vertex1; j++) {
                    if (hasEdge(i, j)) {
                        disp[i][j] = 1;
                    }
                }
            }
            for (int i = 0; i < vertex1; i++) {
                for (int j = 0; j < vertex1; j++) {
                    System.out.print(disp[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
/**
 * { class for solution }.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * { main function }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.nextLine();
        int numberofvertices = Integer.parseInt(sc.nextLine());
        int numberofedges = Integer.parseInt(sc.nextLine());
        String[] keys = sc.nextLine().split(",");
        Newgraph g = new Newgraph(numberofvertices);
        while (sc.hasNext()) {
            String[] conn = sc.nextLine().split(" ");
            g.addEdge(Integer.parseInt(conn[0]),
                Integer.parseInt(conn[1]));
        }
        switch (type) {
            case "List":
            try {
                g.show(numberofvertices, numberofedges, keys);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
            case "Matrix":
            try {
                g.show1(numberofvertices, numberofedges);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
            default:
            break;
        }
    }
}

