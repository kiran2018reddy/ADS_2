import java.util.HashMap;
import java.util.Arrays;

public class BurrowsWheeler {
	 CircularSuffixArray suffix;


	BurrowsWheeler(String s) {
        suffix = new CircularSuffixArray(s);

	}


    public void transform(String s) {
    	for(int i = 0; i<s.length(); i++) {
    		if(suffix.index(i) == 0) {
    			BinaryStdOut.write(i);
    			break;
    		}
    	}
    	for(int i = 0; i<s.length(); i++) {
    		BinaryStdOut.write(suffix.suffixChar(i));

    	}
    BinaryStdOut.close();

    }

    public static void inverseTransform() {

         int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        char[] t = s.toCharArray();
        HashMap<Character, Queue<Integer>> table
        = new HashMap<Character, Queue<Integer>>();
        for (int i = 0; i < t.length; ++i) {
            if (!table.containsKey(t[i])) {
                table.put(t[i], new Queue<Integer>());
            }
            table.get(t[i]).enqueue(i);
        }

        Arrays.sort(t);
        int[] next = new int[t.length];
        for (int i = 0; i < next.length; ++i) {
            next[i] = table.get(t[i]).dequeue();
        }

        for (int i = 0; i < next.length; ++i) {
            BinaryStdOut.write(t[first], 8);
            first = next[first];
        }
        BinaryStdOut.close();

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Illegal command line argument");
        }

        if (args[0].equals("-")) {
        String s = StdIn.readAll().replaceAll("\\s+", " ").trim();
        BurrowsWheeler bw = new BurrowsWheeler(s);
        bw.transform(s);
        }
        else if (args[0].equals("+")) {
            inverseTransform();
        }
        else {
            throw new IllegalArgumentException("Illegal command line argument");
        }

    }
}