package DP;

/*
 * https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
 * https://leetcode.com/problems/predict-the-winner/description/
 */
public class OptimalStrategyForAGame {
    public int optimize(int[] arr, int i, int j, int sum) {
        if(i + 1 == j) {
            return Math.max(arr[i], arr[j]);
        }
        return Mat.max(
            sum - optimize(arr, i + 1, j, sum - arr[i]),
            sum - optimize(arr, i, j - 1, sum - arr[j])
        );
    }
}
