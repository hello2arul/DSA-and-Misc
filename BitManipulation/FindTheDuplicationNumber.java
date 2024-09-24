package BitManipulation;

/*
* https://leetcode.com/problems/find-the-duplicate-number/

Given an array of integers nums containing n + 1 integers where each integer is in
the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and using only constant extra space.

1. Hashing
2. Marking visited
3. Sorting
4. Index Sort
5. Fast Slow pointers

*/
public class FindTheDuplicationNumber {
    public int findDuplicateMarkingVisited(int[] nums) {
        for (int num : nums) {
            int idx = Math.abs(num);

            if (nums[idx] < 0)
                return idx;

            nums[idx] = -nums[idx];
        }
        return -1;
    }

    public int findDuplicateIndexSort(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len;) {
            int num = nums[i];

            if (num == i + 1) {
                i++;
            } else if (num == nums[num - 1]) {
                return num;
            } else {
                nums[i] = nums[num - 1];
                nums[num - 1] = num;
            }
        }
        return -1;
    }

    public ListNode detectCycleFastSlow(ListNode head) {
        if(head == null|| head.next == null || head.next.next == null)  return null;
        ListNode first = head.next;
        ListNode second = head.next.next;
        while(first != second) {
            if(second.next == null || second.next.next == null) return null;
            first = first.next;
            second = second.next.next;
        }
        first = head;
        while(first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
    }
}
