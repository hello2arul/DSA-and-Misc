package String;

/*
https://leetcode.com/discuss/interview-question/5969543/Google-India-screening-round-Interview-question-or-L3-or-Reject
Google

Given two strings of equal length made up of 'x', 'y', and 'z', 
with no consecutive characters the same, determine the minimum number of
operations needed to transform the first string into the second. 
In one operation, you can change any character in the first string, 
ensuring no consecutive characters become identical.


for ex:
str1: zxyz
str2: zyxz

zxyz → yxyz → yzyz → yzxz → zxzx → zxyz → zyxz

result: 6

ex #2:
str1: xyzyzyxyzx
str2: xzyzyzyxzy

result: 15
ex #3:
str1: xyxyxyxyxy
str2: xzyxyxzyxz

result: 13
ex #4:
str1: xyxyzyzyxy
str2: zyzyxzyzyz

result: 9
ex #5
str1: xzxyxyzyzyxyzx
str2: zyzyxzyzyzyxzy

res: 20
I tried BFS, but it will not work. The expected time complexity was linear, O(length of the string).
 */
import java.util.*;

public class Transform {

    static class State {
        String str;
        int ops;

        State(String str, int ops) {
            this.str = str;
            this.ops = ops;
        }
    }

    public static int minOperationsToTransform(String source, String target) {
        int n = source.length();

        // BFS queue
        Queue<State> q = new LinkedList<>();
        q.offer(new State(source, 0));

        // Keep track of visited states to avoid cycles
        Set<String> visited = new HashSet<>();
        visited.add(source);

        char[] chars = { 'x', 'y', 'z' };

        while (!q.isEmpty()) {
            State curr = q.poll();

            if (curr.str.equals(target)) {
                return curr.ops;
            }

            // Try changing each position
            for (int i = 0; i < n; i++) {
                char[] next = curr.str.toCharArray();
                char prev = (i > 0) ? next[i - 1] : '#';
                char nextChar = (i < n - 1) ? next[i + 1] : '#';

                // Try each possible character
                for (char c : chars) {
                    if (c != prev && c != nextChar) { // Check consecutive constraint
                        next[i] = c;
                        String nextStr = new String(next);
                        if (!visited.contains(nextStr)) {
                            visited.add(nextStr);
                            q.offer(new State(nextStr, curr.ops + 1));
                        }
                    }
                }
            }
        }

        return -1; // No solution found
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(minOperationsToTransform("zxyz", "zyxz")); // Output: 6
        System.out.println(minOperationsToTransform("xyzyzyxyzx", "xzyzyzyxzy")); // Output: 15
        System.out.println(minOperationsToTransform("xyxyxyxyxy", "xzyxyxzyxz")); // Output: 13
        System.out.println(minOperationsToTransform("xyxyzyzyxy", "zyzyxzyzyz")); // Output: 9
        System.out.println(minOperationsToTransform("xzxyxyzyzyxyzx", "zyzyxzyzyzyxzy")); // Output: 20
    }
}
