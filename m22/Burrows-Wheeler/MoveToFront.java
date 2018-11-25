import java.util.Arrays;
import java.util.ArrayList;
public class MoveToFront {
	final static int R = 256;
	static ArrayList<Character> array;




	MoveToFront() {

		// System.out.println(Arrays.toString(array));
	}

    public static void encode() {
    	array = new ArrayList<Character>();
    	for(int i = 0; i<R; i++) {
		    array.add((char)i);
		}
    	String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        for(int i = 0; i<input.length; i++) {
        	int j = array.indexOf(input[i]);
        	BinaryStdOut.write((char)j);
        	array.remove(input[i]);
        	array.add(0, input[i]);
        }

        BinaryStdOut.close();

    }

    public static void decode() {
    	array = new ArrayList<Character>();
    	for(int i = 0; i<R; i++) {
		    array.add((char)i);
		}
    	String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        for(char c : input) {
        	char charc = array.get(c);
        	System.out.println(charc);
        	BinaryStdOut.write(charc);
        	array.remove(charc);
        	array.add(0, charc);
        }

    BinaryStdOut.close();

    }

    public static void main(String[] args) {
        MoveToFront mtf = new MoveToFront();
        if  (args[0].equals("-")) mtf.encode();
        else if (args[0].equals("+")) mtf.decode();
        else throw new IllegalArgumentException("Illegal command line argument");

    }
}