package Recursion;

import java.util.*;

/*
https://www.geeksforgeeks.org/find-nested-list-weight-sum-ii/
https://leetcode.ca/all/339.html
Google

Input: [1,[4,[6]]]
Output: 27 
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */
public class NestedListWeightSum {
    public int getDepthSum(List<Object> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<Object> nestedList, int depth) {
        int depthSum = 0;

        for (Object obj : nestedList) {
            if (obj instanceof Integer) {
                depthSum += ((Integer) obj * depth);
            } else if (obj instanceof List) {
                depthSum += dfs((List<Object>) obj, depth + 1);
            }
        }
        return depthSum;
    }

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();

        // Adding the first element
        list.add(1);

        // Creating the nested ArrayList
        ArrayList<Object> nestedList1 = new ArrayList<>();
        nestedList1.add(4);

        // Creating another nested ArrayList
        ArrayList<Object> nestedList2 = new ArrayList<>();
        nestedList2.add(6);

        // Adding the second nested list to the first nested list
        nestedList1.add(nestedList2);

        // Adding the first nested list to the main list
        list.add(nestedList1);
        System.out.println(list);

        System.out.println(new NestedListWeightSum().getDepthSum(list));
    }
}
