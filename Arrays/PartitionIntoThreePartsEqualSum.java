package Arrays;

/*
 * https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/description/
 */
public class PartitionIntoThreePartsEqualSum {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        int avg = 0;
        for(int i : A)
            sum += i;
        if (sum % 3 != 0)
            return false;
        avg = sum / 3;
        int count = 0;
        int part = 0;
        for(int i : A) {
            part += i;
            if(part == avg) {
                part = 0;
                count++;
            }
        }
        return count >= 3;
    }
}
