package Queue;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/numbers-at-most-n-given-digit-set/
public class NumbersAscFromGivenSet {
     public int atMostNGivenDigitSet(String[] digits, int n) {
        Queue<String> q = new LinkedList<>();
        for(String digit: digits) q.offer(digit);
        int res = 0;

        while(!q.isEmpty()) {
            String cur = q.poll();
            if(Long.valueOf(cur) > n)
                break;
            res++;
            for(String digit: digits) {
                q.offer(cur + digit);
            }
        }
        return res;
    }
}
