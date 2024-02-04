package BackTracking;

/*
* https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
* */
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num: nums)
            sum += num;
        if(sum % k != 0)
            return false;
        return canPartition(nums, sum / k, new boolean[nums.length], k, 0, 0, 0);
    }

    public boolean canPartition(int[] nums, int target, boolean[] visited, int k,
                                int idx, int currentSum, int numsIncluded) {
        if(k == 1)  return true;
        if(currentSum == target && numsIncluded > 0) {
            return canPartition(nums, target, visited, k - 1, 0, 0, 0);
        }
        for(int i = idx; i < nums.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(canPartition(nums, target, visited, k, i + 1, currentSum + nums[i],
                        numsIncluded + 1))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }
}
