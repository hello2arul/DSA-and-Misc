package DP;

import java.util.HashMap;
import java.util.Map;

/*
You have planned some train traveling one year in advance. 
The days of the year in which you will travel are given as an integer array days.
Each day is an integer from 1 to 365.

Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 
2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given 
list of days.

 

Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.
Example 2:

Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total, you spent $17 and covered all the days of your travel.
 

Constraints:

1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000
*/
public class MinimumCostForTickets {

    // O(n) time complexity and O(n) space complexity
    public int mincostTicketsTabulation(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];
        boolean[] isTravelDay = new boolean[lastDay + 1];

        // Mark travel days
        for (int day : days) {
            isTravelDay[day] = true;
        }

        for (int i = 1; i <= lastDay; i++) {
            if (!isTravelDay[i]) {
                dp[i] = dp[i - 1];
                continue;
            }

            int oneDayCost = dp[i - 1] + costs[0];
            int sevenDayCost = dp[Math.max(0, i - 7)] + costs[1];
            int thirtyDayCost = dp[Math.max(0, i - 30)] + costs[2];

            dp[i] = Math.min(oneDayCost, Math.min(sevenDayCost, thirtyDayCost));
        }

        return dp[lastDay];
    }

    public int mincostTickets(int[] days, int[] costs) {
        Map<Integer, Integer> memo = new HashMap<>();
        return mincostTicketsHelper(days, costs, 0, memo);
    }

    // O(nlogn) time complexity and O(n) space complexity
    // n is the number of days in the days array
    private int mincostTicketsHelper(int[] days, int[] costs, int idx, Map<Integer, Integer> memo) {
        if (idx >= days.length)
            return 0;
        if (memo.containsKey(idx))
            return memo.get(idx);

        // Cost of using a 1-day ticket
        int oneDay = costs[0] + mincostTicketsHelper(days, costs, getNextIndex(days, idx, 1), memo);
        // Cost of using a 7-day ticket
        int sevenDay = costs[1] + mincostTicketsHelper(days, costs, getNextIndex(days, idx, 7), memo);
        // Cost of using a 30-day ticket
        int thirtyDay = costs[2] + mincostTicketsHelper(days, costs, getNextIndex(days, idx, 30), memo);

        int minCost = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
        memo.put(idx, minCost);
        return minCost;
    }

    // can be optimized via binary search
    private int getNextIndex(int[] days, int idx, int duration) {
        int targetDay = days[idx] + duration;
        while (idx < days.length && days[idx] < targetDay) {
            idx++;
        }
        return idx;
    }

    private int getNextIdx(int[] days, int idx, int pass) {
        int target = days[idx] + pass;
        int left = idx;
        int right = days.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (days[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
