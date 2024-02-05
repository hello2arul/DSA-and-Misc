package Hashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
* https://leetcode.com/problems/intersection-of-two-arrays/description/
* */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        HashSet<Integer> hs = new HashSet<>();
        for(int i : nums1)
            hs.add(i);
        for(int i : nums2) {
            if(hs.contains(i)) {
                result.add(i);
                hs.remove(i);
            }
        }
        int arr[] = new int[result.size()];
        int in =0;
        for(Integer i : result) {
            arr[in] = i;
            in++;
        }
        return arr;
    }
}

