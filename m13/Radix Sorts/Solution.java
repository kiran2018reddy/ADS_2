import java.util.Scanner;
import java.util.Arrays;
/**
 * class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * r
     *  O(1.3WNlogN) -- guarantee.
     *  -- random.
     *
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine());
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextLine();
        }
        Quickstring q = new Quickstring();
        q.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

