import java.util.Arrays;
public class CircularSuffixArray {
    private Suffix[] suffixes;
    private static int[] indices;

    public CircularSuffixArray(String s) {
        int n = s.length();
        indices = new int[s.length()];
        this.suffixes = new Suffix[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(s, i);

        }

        Arrays.sort(suffixes);
        // System.out.println(Arrays.toString(suffixes));
    }


    private static class Suffix implements Comparable<Suffix> {
        private final String text;
        private final int index;

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }
        private int length() {
            return text.length() - index;
        }
        private char charAt(int i) {
            return text.charAt(index + i);
        }

        public int compareTo(Suffix that) {
            if (this == that) return 0;  // optimization
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return this.length() - that.length();
        }

        public String toString() {
            return text.substring(index)+text.substring(0, index);
        }
    }

    public int length() {
         return suffixes.length;
    }

    /**
     * returns index of ith sorted suffix
     *
     * @param i
     *            the index of the ith sorted suffix
     * @return
     */
    public int index(int i) {
        if (i < 0 || i >= suffixes.length) throw new IllegalArgumentException();
        return suffixes[i].index;
    }

    public int[] index() {
        return indices;
    }

    public char suffixChar(int i) {
        String s =  suffixes[i].toString();
        return s.charAt(s.length()-1);

    }

}