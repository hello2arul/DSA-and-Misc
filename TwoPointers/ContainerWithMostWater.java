package TwoPointers;

/*
 * https://leetcode.com/problems/container-with-most-water/description/
 * You are given an integer array height of length n. There are n vertical lines 
 * drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, 
such that the container contains the most water.

Return the maximum amount of water a container can store.
 */
public class ContainerWithMostWater {
    public int maxAreaBruteForce(int[] height) {
        int n = height.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                res = Math.max(res, (j - i) * Math.min(height[i], height[j]));
            }   
        }
        return res;
    }

    public int maxArea(int[] height) {
        int n = height.length;
        int res = 0;
        int i = 0;
        int j = n - 1;

        while (i < j) {
            res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return res;
    }
}
