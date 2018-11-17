import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Class for solution.
 */
public class Solution {
    /**
     * { function_description }.
     *
     * @param      args  The arguments.
     */
public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    /**
     * { function_description }.
     *
     * @param      file  The file.
     *
     * @return     { description_of_the_return_value }.
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }
/**
 * Loads a dictionary.
 *
 * @param      file  The file.
 *
 * @return     { description_of_the_return_value }.
 */
public static BinarySearchST<String, Integer>
loadDictionary(final String file) {
BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
        // your code goes here
        String[] dictionary = toReadFile(file);
        for (int i = 0; i < dictionary.length; i++) {
            String word = dictionary[i].toLowerCase();
            if (st.contains(word)) {
                st.put(word ,st.get(word) + 1);
            } else {
                st.put(word, 1);
            }
        }
        return st;
    }

}
/**
 * Class for t 9.
 */
class T9 {
    /**
     * { var_description }.
     */
    TST<Integer> tst;
    /**
     * Constructs the object.
     *
     * @param      st    { parameter_description }.
     */
    public T9(BinarySearchST<String, Integer> st) {
        // your code goes here
        tst = new TST<>();
        for (String each : st.keys()) {
            tst.put(each, st.get(each));
        }
    }
/**
 * Gets all words.
 *
 * @param      prefix  The prefix.
 *
 * @return     All words.
 */
    public final Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }
/**
 * { function_description }.
 *
 * @param      t9Signature  The t 9 signature.
 *
 * @return     { description_of_the_return_value }.
 */
    public final  Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
    return null;
 }

    /**
     * Gets the suggestions.
     *
     * @param      words  The words.
     * @param      k      { parameter_description }.
     *
     * @return     The suggestions.
     */
public final Iterable<String>
getSuggestions(final Iterable<String> words, final int k) {
        // your code goes here
        ArrayList<String> arr = new ArrayList<>();
        MaxPQ<Integer> max = new MaxPQ<>();
        for (String each : words) {
            max.insert(tst.get(each));
        }
        for (int i = 0; i < k; i++) {
            int j = max.delMax();
            for (String word : words) {
                if (j == tst.get(word)) {
                    arr.add(word);
                }
            }
        }
        Collections.sort(arr);
        return arr;
    }

    /**
     * { function_description }.
     *
     * @param      t9Signature  The t 9 signature.
     * @param      k            { parameter_description }.
     *
     * @return     { description_of_the_return_value }.
     */
    public final  Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
