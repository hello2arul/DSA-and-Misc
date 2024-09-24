package Trie;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/lexicographical-numbers/description/
https://leetcode.com/problems/k-th-smallest-in-lexicographical-order
Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.

You must write an algorithm that runs in O(n) time and uses O(1) extra space. 

Example 1:

Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
TODO: trie
*/
public class LexicographicalNumbers {
    public List<Integer> lexicalOrderIterative(int n) {
        List<Integer> res = new ArrayList<>();
        int cur = 1;

        while (res.size() < n) {
            res.add(cur);
            
            if (cur * 10 <= n) {
                cur *= 10;
            } else {
                while (cur == n || cur % 10 == 9)
                    cur /= 10;
                cur += 1;
            }
        }
        return res;
    }


    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            dfs(i, n, res);
        }
        return res;
    }

    private void dfs(int i, int n, List<Integer> res) {
        if (i > n)
            return;
        res.add(i);
        i *= 10;
        for (int j = 0; j < 10; j++)
            dfs(i + j, n, res);
    }
}
