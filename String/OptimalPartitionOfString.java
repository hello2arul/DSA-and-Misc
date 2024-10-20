package Strings;
//https://leetcode.com/problems/optimal-partition-of-string/

import java.util.HashSet;
import java.util.Set;

public class OptimalPartitionOfString {
    public int partitionString(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if(set.contains(s.charAt(i))) {
                res++;
                set.clear();
            }
            set.add(s.charAt(i));
        }
        return ++res;
    }
}
