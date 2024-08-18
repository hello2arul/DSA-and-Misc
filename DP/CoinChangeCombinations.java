package DP;

import java.util.Map;

/*
 * 
 * https://leetcode.com/problems/coin-change-ii/
 */
public class CoinChangeCombinations {
    private int coinChangeCombination(int[] coins, int amount, int idx) {
        if(amount == 0)
            return 1;
        if(idx == coins.length)
            return 0;
        int res = coinChangeCombination(coins, amount, idx + 1);
        if(coins[idx] <= amount) {
            res += coinChangeCombination(coins, amount - coins[idx], idx);
        }
        return res;
    }
    
    private int coinChangeCombinationMemo(int[] coins, int amount, int idx, Map<String, Integer> memo) {
        if(amount == 0)
            return 1;
        if(idx == coins.length)
            return 0;
        String key = idx + "," + amount;
        if(memo.containsKey(key))
            return memo.get(key);
        
        int res = coinChangeCombinationMemo(coins, amount, idx + 1, memo);
        if(coins[idx] <= amount) {
            res += coinChangeCombinationMemo(coins, amount - coins[idx], idx, memo);
        }
        memo.put(key, res);
        return res;
    }

    public int changeTabulation(int amount, int[] coins) {
        int[][] dp = new int[amount + 1][coins.length + 1];

        for(int j = 0; j <= coins.length; j++) {
            //amount = 0 
            dp[0][j] = 1;
        }
        for(int i = 1; i <= amount; i++) {
            for(int j = 1; j <= coins.length; j++) {
                dp[i][j] = dp[i][j - 1];
                if(coins[j - 1] <= i) {
                    dp[i][j] += dp[i - coins[j - 1]][j];
                } 
            }
        }
        return dp[amount][coins.length];
    }
}
