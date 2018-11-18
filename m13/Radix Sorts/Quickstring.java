/**
 * Class for quick 3 string.
 */
class Quickstring {
    /**
     * cutoff to insertion sort.
     */
    private static final int CUTOFF =  15;
    /**
     * Rearranges the array of strings in ascending order.
     * The time complexity is O(1.3WNlogN) -- guarantee.
     * The time complexity is O(2NlogN) -- random.
     *
     *
     * @param      a     { String array }
     */
    public void sort(final String[] a) {
        // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, 0);
        // assert isSorted(a);
    }

    /**
     * return the dth character of s, -1 if d = length of s.
     * The time complexity is O(1).
     *
     *
     * @param      s     { String }
     * @param      a     { index }
     *
     * @return     { ascii value of that character }
     */
    private int charAt(final String s, final int a) {
        assert a >= 0 && a <= s.length();
        if (a == s.length()) {
            return -1;
        }
        return s.charAt(a);
    }


    /**
     * 3-way string quicksort a[lo..hi].
     *  starting at dth character.
     * The time complexity is O(1.3WNlogN) -- guarantee.
     * The time complexity is O(2NlogN) -- random.
     *
     *
     * @param      a     { String array }
     * @param      lo    The lower
     * @param      hi    The higher
     * @param      d     { index }
     */
    private void sort(final String[] a, final int lo,
        final int hi, final int d) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v)  {
                exchange(a, lt++, i++);
            } else if (t > v) {
                exchange(a, i, gt--);
            } else {
                i++;
            }
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1, d);
        if (v >= 0) {
            sort(a, lt, gt, d + 1);
        }
        sort(a, gt + 1, hi, d);
    }

    /**
     * sort from a[lo] to a[hi], starting at the dth character.
     * The time complexity is O(N*N).
     *
     *
     * @param      a     { String array }
     * @param      lo    The lower
     * @param      hi    The higher
     * @param      d     { index }
     */
    private void insertion(final String[] a,
        final int lo, final int hi, final int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    /**
     * exchange a[i] and a[j].
     * The time complexity is O(1).
     *
     *
     * @param      a     { String array }
     * @param      i     { index1 }
     * @param      j     { index2 }
     */
    private void exchange(final String[] a, final int i, final int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * less function.
     * The time complexity is O(W).
     *
     *
     * @param      v     { String a }
     * @param      w     { String b }
     * @param      d     { index }
     *
     * @return     { boolean }
     */
    private boolean less(final String v,
        final String w, final int d) {
        assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i))  {
                return true;
            }
            if (v.charAt(i) > w.charAt(i)) {
                return false;
            }
        }
        return v.length() < w.length();
    }
}