// An element is a leader if it is greater than all the elements to its right side
// https://www.geeksforgeeks.org/leaders-in-an-array/
// O(n) O(n)

import java.util.ArrayList;

public class LeadersInAnArray {
    public List<Integer> leader(int[] nums) {
        List<Integer> leaders = new ArrayList<>();
        leaders.add(nums[nums.length - 1]);
        int currentLeader = nums[length - 1];

        for(int j = nums.length - 2; j >= 0; j--) {
            if (currentLeader < nums[i]) {
                currentLeader = nums[i];
                leaders.add(currentLeader);
            }
        }
        return leaders;
    }
}
