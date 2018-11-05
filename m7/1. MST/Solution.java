import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * { function_description }.
     *
     * @param      args  The arguments.
     */
    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);
        int vertices = Integer.parseInt(input.nextLine());
        int edges = Integer.parseInt(input.nextLine());
        EdgeWeightedGraph edgeW = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
        String[] tokens = input.nextLine().split(" ");
Edge edge = new Edge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
Double.parseDouble(tokens[2]));
        edgeW.addEdge(edge);
        }
        KruskalMST kmst = new KruskalMST(edgeW);
        System.out.format("%.5f", kmst.weight());
    }
}
