import java.util.ArrayList;
import java.util.List;

/**
 * ip: [10, 5, 2, 3, 6], sum = 8
 * op: 2
 */

public class SubSetSum {
    public void subSetSum(int[] input, int sum, List<List<Integer>> results, 
        List<Integer> current, int idx) {
        if(sum == 0) {
            results.add(new ArrayList<>(current));
            System.out.println(current);
            return;
        }
        if(idx >= input.length)
            return;
        subSetSum(input, sum, results, current, idx + 1);
        current.add(input[idx]);
        subSetSum(input, sum - input[idx], results, current, idx + 1);
        current.remove(current.size() - 1);
    }

    public static void main(String[] args) {
        // System.out.println(
            new SubSetSum().subSetSum(new int[]{10,5,2,3,6}, 8, new ArrayList<>(), new ArrayList<>(), 0);
        // );
    }
}
