package Heap;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/longest-happy-string/description/
A string s is called happy if it satisfies the following conditions:

    s only contains the letters 'a', 'b', and 'c'.
    s does not contain any of "aaa", "bbb", or "ccc" as a substring.
    s contains at most a occurrences of the letter 'a'.
    s contains at most b occurrences of the letter 'b'.
    s contains at most c occurrences of the letter 'c'.

Given three integers a, b, and c, return the longest possible happy string.
If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

A substring is a contiguous sequence of characters within a string.

Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.

Example 2:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It is the only correct answer in this case.

 */

public class LongestHappyString {

     public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[0], x[0]));
        if (a > 0)  pq.offer(new int[] {a, 'a'});
        if (b > 0)  pq.offer(new int[] {b, 'b'});
        if (c > 0)  pq.offer(new int[] {c, 'c'});
        StringBuilder res = new StringBuilder();

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            char ch = (char) cur[1];

            if (res.length() >= 2 && res.charAt(res.length() - 1) == ch && 
                res.charAt(res.length() - 2) == ch) {
                if (!pq.isEmpty()) {
                    int[] next = pq.poll();
                    res.append((char) next[1]);

                    if (--next[0] > 0)
                        pq.offer(next);
                    pq.offer(cur);
                }
            } else {
                res.append((char) cur[1]);
                if (--cur[0] > 0)
                    pq.offer(cur);
            }
        }
        return res.toString();
    }
}