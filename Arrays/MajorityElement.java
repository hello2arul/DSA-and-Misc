import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/majority-element/
public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int half = nums.length / 2;

        for(int num: nums) {
            if(map.getOrDefault(num, 0) + 1 > half) {
                return num;
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        return -1;
    }

    //https://www.cs.utexas.edu/~moore/best-ideas/mjrty/
    public int majorityVoteAlgorithm(int[] nums) {
        int res = nums[0];
        int count = 1;
        
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == res) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                res = nums[i];
                count = 1;
            }
        }

        count = 0;
        for (int num: nums) {
            if(num == res) {
                count++;
            }
        }
        return count > nums.length / 2 ? res : - 1;
    }
}
