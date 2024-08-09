package Hashing;

import java.util.HashSet;

/*
* https://leetcode.com/problems/happy-number/description/
* A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.

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
