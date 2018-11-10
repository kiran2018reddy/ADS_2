import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph edgeGraph = new EdgeWeightedGraph(vertices);
		int i = edges;
		while (i > 0) {
			String[] edString = scan.nextLine().split(" ");
			Edge e = new Edge(Integer.parseInt(edString[0]),
Integer.parseInt(edString[1]),Integer.parseInt(edString[2]));
			edgeGraph.addEdge(e);
			i = i - 1;
		}

		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(edgeGraph);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] directedPaths = scan.nextLine().split(" ");
			int p = Integer.parseInt(directedPaths[0]);
			int q = Integer.parseInt(directedPaths[1]);
			DijkstrasSP shtpath = new DijkstrasSP(edgeGraph, p);
			if (shtpath.hasPathTo(q)) {
				System.out.println(shtpath.distTo(q));
			} else {
				System.out.println("No Path Found.");
			}
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] viaPaths = scan.nextLine().split(" ");
			p = Integer.parseInt(viaPaths[0]);
			int via = Integer.parseInt(viaPaths[1]);
			q = Integer.parseInt(viaPaths[viaPaths.length - 1]);
			DijkstrasSP dshtpath = new DijkstrasSP(edgeGraph, p);
			if (dshtpath.hasPathTo(q)) {
				Qaueue<Integer> que = new Qaueue<Integer>();
				for (Edge e : dshtpath.pathTo(via)) {
                    String[] line = e.toString().split(" ");
                    String[] k = line[0].split("-");
                    System.out.println(e.other(Integer.parseInt(k[1])));

                }
                DijkstrasSP two = new DijkstrasSP(edgeGraph, via);
                for (Edge e : two.pathTo(q)) {
                    String[] line = e.toString().split(" ");
                    String[] k = line[0].split("-");
                    System.out.println(e.other(Integer.parseInt(k[1])));
                }
				System.out.println(dshtpath.distTo(via) + two.distTo(q));
			} else {
				System.out.println("No Path Found");
			}
			break;

		default:
			break;
		}

	}
}