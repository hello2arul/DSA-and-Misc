package BackTracking;
/*
 * https://leetcode.com/problems/24-game/description/
 * Google
 * You are given an integer array cards of length 4. You have four cards, 
 * each containing a number in the range [1, 9]. You should arrange the numbers 
 * on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] 
 * and the parentheses '(' and ')' to get the value 24.
 * The division operator '/' represents real division, not integer division
Example 1:

Input: cards = [4,1,8,7]
Output: true
Explanation: (8-4) * (7-1) = 24

Example 2:

Input: cards = [1,2,1,2]
Output: false
 */

public class 24Game {
    private static final double DIFF_TOLERANT = 0.1;
    
    public boolean judgePoint24(int[] nums) {
        List<Double> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add((double)num);
        }
        return judgePoint24(numList);
    }
    // O(1) since nums.size() == 4
    private boolean judgePoint24(List<Double> nums) {
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24) <= DIFF_TOLERANT;
        } else {
            for (int i = 0; i < nums.size(); i++) {
                for (int j = 0; j < i; j++) {
                    double a = nums.get(i), b = nums.get(j);
                    
                    List<Double> vals = new ArrayList<>(); // possible results by adding operator between a and b
                    // invariant: no 0 in nums
                    vals.addAll(Arrays.asList(a + b, a - b, b - a, a * b, a / b, b / a));
                    
                    List<Double> copyNums = new ArrayList<>(nums); // deep copy of nums
                    copyNums.remove(i);
                    copyNums.remove(j);
                    for (double val : vals) {
                        copyNums.add(val);
                        if (judgePoint24(copyNums)) {
                            return true;
                        }
                        copyNums.remove(copyNums.size() - 1);
                    }
                }
            }
            return false;
        }
    }
}
