package LineSweeping;

import java.util.List;
import java.util.TreeMap;

/*
 * https://leetcode.com/problems/points-that-intersect-with-cars
 * You are given a 0-indexed 2D integer array nums representing the 
 * coordinates of the cars parking on a number line. For any index i, 
 * nums[i] = [starti, endi] where starti is the starting point of the ith car and endi
 * is the ending point of the ith car.

Return the number of integer points on the line that are covered with any part of a car.
Input: nums = [[3,6],[1,5],[4,7]]
Output: 7
Explanation: All the points from 1 to 7 intersect at least one car,
therefore the answer would be 7.
 */
public class PointsThatIntersectWithCar {
    public int numberOfPoints(List<List<Integer>> nums) {
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        int count = 0;
        int res = 0;
        TreeMap<Integer, Integer> tree = new TreeMap<>();

        for (List<Integer> num : nums) {
            tree.put(num.get(0), tree.getOrDefault(num.get(0), 0) + 1);
            tree.put(num.get(1) + 1, tree.getOrDefault(num.get(1) + 1, 0) - 1);
            start = Math.min(start, num.get(0));
            end = Math.max(end, num.get(1));
        }

        for (int i = start; i <= end; i++) {
            count += tree.getOrDefault(i, 0);
            if (count > 0)
                res++;
        }
        return res;
    }
}
