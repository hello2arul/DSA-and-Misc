package Searching;

/*
https://leetcode.com/problems/koko-eating-bananas
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. 
The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, 
she chooses some pile of bananas and eats k bananas from that pile. 
If the pile has less than k bananas, she eats all of them instead and 
will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

 

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23
*/
class CocoEatingBananas {
        // O(max(p) * p * k)
    public int minEatingSpeed(int[] piles, int h) {
        outerLoop:
        for (int i = 1; ;i++) {
            int hours = 0;
            for (int pile: piles) {
                while (pile > 0) {
                    hours++;
                    pile -= i;

                    if (hours > h)
                        continue outerLoop;
                }
  
            }
            return i;
        }
    }

    // O(max(p) * p)
    public int minEatingSpeedWithoutWhile(int[] piles, int h) {
        outerLoop:
        for (int i = 1; ;i++) {
            int hours = 0;
            for (int pile: piles) {
                hours += Math.ceil(pile / (double)i);
                if (hours > h)
                    continue outerLoop;
  
            }
            return i;
        }
    }

    // O(nlogn(max(P)) * P)
    public int minEatingSpeedBinarySearch(int[] piles, int h) {
        int left = 1;
        int right = getMax(piles);

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isEatable(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        
        for (int num: arr) {
            max = Math.max(num, max);
        }
        return max;
    }

    private boolean isEatable(int[] arr, int h, int k) {
        int hours = 0;
        
        for (int pile: arr) {
            hours += Math.ceil(pile / (double) k);
            if (hours > h)
                return false;
        }
        return true;
    }
}