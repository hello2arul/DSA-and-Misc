package DP;

import java.util.Arrays;

/*
 * 
 * https://leetcode.com/problems/coin-change/description/
 * 1. try greedily picking from the max coins, doesn't work for all cases
 */
public class CoinChangeMinCoins {
    public int coinChange(int[] coins, int amount) {
        if(coins.length == 1 && amount % coins[0] != 0) return -1;
        if(amount == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for(int coin: coins) {
            if(amount - coin >= 0) {
                int subRes = coinChange(coins, amount - coin);
                if(subRes != Integer.MAX_VALUE)
                    res = 1 + Math.min(res, subRes);
            }
        } 
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int coinChange(int[] denoms, int change) {
		int[] noOfCoins = new int[change + 1];
		Arrays.fill(noOfCoins, change + 1);
		noOfCoins[0] = 0;
		for (int denom : denoms) {
			for (int amount = 0; amount < noOfCoins.length; amount++) {
				if (denom <= amount)
					noOfCoins[amount] = Math.min(noOfCoins[amount], 1 + noOfCoins[amount - denom]);
			}
		}
		return noOfCoins[change] > change ? -1 : noOfCoins[change];
    }
}
