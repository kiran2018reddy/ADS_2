import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * main.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph edgeGraph
            = new EdgeWeightedGraph(vertices);
        int i = edges;
        while (i > 0) {
            String[] edgeString
                = scan.nextLine().split(" ");
            Edge edge = new Edge(Integer.parseInt(edgeString[0]),
Integer.parseInt(edgeString[1]),Integer.parseInt(edgeString[2]));
            edgeGraph.addEdge(edge);
            i--;
        }

        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            //Print the Graph Object.
            System.out.println(edgeGraph);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths,
            // where two integers are given.
            // First is the source and second is
            // the destination.
            // If the path exists print the distance
            // between them.
            // Other wise print "No Path Found."
            String[] dirPaths = scan.nextLine().split(" ");
            int p = Integer.parseInt(dirPaths[0]);
            int q = Integer.parseInt(dirPaths[1]);
            DijkstraUndirectedSP sp
                = new DijkstraUndirectedSP(edgeGraph, p);
            if (sp.hasPathTo(q)) {
                System.out.println(sp.distTo(q));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths,
            // where three integers are given.
            // First is the source and second
            // is the via is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] viaPaths = scan.nextLine().split(" ");
            p = Integer.parseInt(viaPaths[0]);
            int via = Integer.parseInt(viaPaths[1]);
            q = Integer.parseInt(viaPaths[viaPaths.length - 1]);
            DijkstraUndirectedSP dsp
                = new DijkstraUndirectedSP(edgeGraph, p);
            if (dsp.hasPathTo(q)) {
                Qaueue<Integer> que = new Qaueue<Integer>();
                for (Edge e : dsp.pathTo(via)) {
                    int ver = e.either();
                    int other = e.other(ver);
                    int v = 0;
                    int w = 0;
                    for (Integer j : que) {
                        if (ver == j) {
                            v = 1;
                        }
                        if (other == j) {
                            w = 1;
                        }

                    }
                    if (w == 0) {
                        que.enqueue(other);
                    }
                    if (v == 0) {
                        que.enqueue(ver);
                    }
                }
                DijkstraUndirectedSP two
                    = new DijkstraUndirectedSP(edgeGraph, via);
                for (Edge e : two.pathTo(q)) {
                    int ver = e.either();
                    int other = e.other(ver);
                    int v = 0;
                    int w = 0;
                    for (Integer j : que) {
                        if (ver == j) {
                            v = 1;
                        }
                        if (other == j) {
                            w = 1;
                        }

                    }
                    if (v == 0) {
                        que.enqueue(ver);
                    }
                    if (w == 0) {
                        que.enqueue(other);
                    }
                }
                System.out.println(dsp.distTo(via) + two.distTo(q));
                while (!que.isEmpty()) {
                    System.out.print(que.dequeue() + " ");
                }
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}