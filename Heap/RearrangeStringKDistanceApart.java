package Heap;

import java.util.*;

/*
 * https://leetcode.ca/all/358.html

Input: s = "aabbcc", k = 3
Output: "abcabc"
Explanation: The same letters are at least distance 3 from each other.

Input: s = "aaabc", k = 3
Output: ""
Explanation: It is not possible to rearrange the string.

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.


O(nlogn), O(n)
 */
public class RearrangeStringKDistanceApart {
    public String rearrange(String s, int k) {
        StringBuilder res = new StringBuilder();
        Map<Character, Integer> count = new HashMap<>();
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(count.getOrDefault(b, 0), count.getOrDefault(a, 0)));
        Queue<Character> waitQueue = new LinkedList<>();


        for (int i = 0; i < s.length(); i++) {
            count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
        }

        maxHeap.addAll(count.keySet());

        while (!maxHeap.isEmpty()) {
            char cur = maxHeap.poll();
            res.append(cur);
            count.put(cur, count.get(cur) - 1);
            waitQueue.offer(cur);
            
            if (waitQueue.size() >= k) {
                char polled = waitQueue.poll();
                if (count.get(polled) > 0)
                    maxHeap.offer(polled);
            }
        }
        return res.length() == s.length() ? res.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println(new RearrangeStringKDistanceApart().rearrange("aaadbbcc",2));
    }
}
