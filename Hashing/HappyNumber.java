package Hashing;

import java.util.HashSet;

/*
* https://leetcode.com/problems/happy-number/description/
 */
public class HappyNumber {
     public boolean isHappy(int num) {
        HashSet<Integer> hs = new HashSet<>();
        while(num != 1) {
            int current = num;
            int result = 0;
            while(current > 0) {
                result += Math.pow(current % 10,2);
                current /= 10;
            }
            if(hs.contains(result)) return false;
            hs.add(result);
            num = result;
        }
        return true;
    }
}
