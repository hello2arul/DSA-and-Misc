//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

public class BuyAndSellStock {
    public int maxProfit(int[] prices) {
        return maxProfit(prices, 1, 0);
    }

    // recursive, naive
    public int maxProfit(int[] prices, int idx, int profit) {
        if(idx >= prices.length) {
            return profit;
        }
        int maxProfit = 0;

        for(int i = 0; i < idx; i++) {
            if(prices[i] < prices[idx]) {
                maxProfit = Math.max(
                    profit + prices[idx] - prices[i], 
                    profit + maxProfit(prices, idx + 1, prices[idx] - prices[i])
                );
            } else {
                maxProfit = Math.max(
                    profit, 
                    profit + maxProfit(prices, idx + 1, 0)
                );
            }
        }
        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        int minBought = prices[0];
        int profit = 0;

        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < minBought) {
                minBought = prices[i];
            } else {
                profit += (prices[i] - minBought);
                minBought = prices[i];
            }
        }
        
        return profit;
    }
}
