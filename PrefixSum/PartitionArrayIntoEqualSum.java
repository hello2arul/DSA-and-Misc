package PrefixSum;

//https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/

public class PartitionArrayIntoEqualSum {
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for(int num: arr) {
            sum += num;
        }


        if(sum % 3 != 0)
            return false;
        int target = 3;
        int currentSum = 0;

        for(int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            if(currentSum == sum / 3) {
                currentSum = 0;
                target--;
               
            }
        }
        return target <= 0;
    }
}
