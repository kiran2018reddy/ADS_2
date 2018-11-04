import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * hash map.
     */
    private HashMap<String, ArrayList<Integer>> map = new
    HashMap<String, ArrayList<Integer>>();
    /**
     * hashmap.
     */
    private HashMap<Integer, String> map2 = new
    HashMap<Integer, String>();
    /**
     * digraph.
     */
    private Digraph gph;
    /**
     * SAP variable.
     */
    private SAP sap;
    /**
     * has cycle variable.
     */
    private boolean hasCycle = false;
    /**
     * has multiple roots variable.
     */
    private boolean hasMultipleRoots = false;
    /**
     * Gets the digraph.
     *
     * @return     The digraph.
     */
    public Digraph getDigraph() {
        return this.gph;
    }
    /**
     * get hasCycle.
     *
     * @return   boolean.
     */
    public boolean gethasCycle() {
        return this.hasCycle;
    }
    /**
     * hasmultipleroots.
     *
     * @return  boolean.
     */
    public boolean gethasMultipleRoots() {
        return this.hasMultipleRoots;
    }
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms) {
        try {
            File fileOne = new File(
                "Files/" + synsets);
            Scanner fOne = new Scanner(fileOne);
            File fileTwo = new File("Files/" + hypernyms);
            Scanner fTwo = new Scanner(fileTwo);
            while (fOne.hasNextLine()) {
                String[] tokens = fOne.nextLine().split(",");
                map2.put(Integer.parseInt(tokens[0]), tokens[1]);
                String[] words = tokens[1].split(" ");
                for (int i = 0; i < words.length; i++) {
                    if (map.containsKey(words[i])) {
                        ArrayList<Integer> arraylist = map.get(words[i]);
                        arraylist.add(Integer.parseInt(tokens[0]));
                    } else {
                        ArrayList<Integer> arraylist = new ArrayList<Integer>();
                        arraylist.add(Integer.parseInt(tokens[0]));
                        map.put(words[i], arraylist);
                    }
                }
            }
            gph = new Digraph(map.size());
            while (fTwo.hasNextLine()) {
                String[] tokens = fTwo.nextLine().split(",");
                for (int i = 1; i < tokens.length; i++) {
                    gph.addEdge(Integer.parseInt(tokens[0]),
                               Integer.parseInt(tokens[i]));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        DirectedCycle dc = new DirectedCycle(gph);
        if (dc.hasCycle()) {
            hasCycle = true;
        }
    }
    /**
     * check multiple roots method.
     */
    public void checkMultipleRoots() {
        int roots = 0;
        for (int i = 0; i < gph.v(); i++) {
            if (gph.outdegree(i) == 0) {
                roots++;
            }
        }
        if (roots != 1) {
            hasMultipleRoots = true;
            System.out.println("Multiple roots");
        }
    }
    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        return true;
    }
    /**
     * distance.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return distance between nouns.
     */
    public int distance(final String nounA, final String nounB) {
        ArrayList id1 = map.get(nounA);
        ArrayList id2 = map.get(nounB);
        sap = new SAP(gph);
        return sap.length(id1, id2);
    }
    /**
     * sap method.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return string.
     */
    public String sap(final String nounA, final String nounB) {
        ArrayList<Integer> id1 = map.get(nounA);
        ArrayList<Integer> id2 = map.get(nounB);
        sap = new SAP(gph);
        int ans = sap.ancestor(id1, id2);
        return map2.get(ans);
    }

}

