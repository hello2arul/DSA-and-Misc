package Searching;

/*
Meta

We have N different apps with different user growth rates. 
At a given time t, measured in days, the number of users using an app is g^t 
(for simplicity we'll allow fractional users), where g is the growth rate for that app. .
These apps will all be launched at the same time and no user ever uses more than one of the apps. 
We want to know how many total users there are when you add together the number of users from each app.
After how many full days will we have 1 billion total users across the N apps?

Signature
int getBillionUsersDay(float[] growthRates)

Input
1.0 < growthRate < 2.0 for all growth rates
1 <= N <= 1,000

Output
Return the number of full days it will take before we have a total of 1 billion users across all N apps.

Example 1
growthRates = [1.5]
output = 52

Example 2
growthRates = [1.1, 1.2, 1.3]
output = 79

Example 3
growthRates = [1.01, 1.02]
output = 1047

*/
public class IBillionUsers {
    
    public int getBillionUsersDay(float[] growthRates) {
        double target = 1_000_000_000;
        int left = 1;
        int right = 2000; // A large enough number to ensure we cover the possible range

        while (left < right) {
            int mid = left + (right - left) / 2;
            double total = totalUsers(growthRates, mid);
            if (total == target) {
                return mid;
            } else if (total > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private double totalUsers(float[] growthRates, int days) {
        double total = 0;
        for (float rate : growthRates) {
            total += Math.pow(rate, days);
        }
        return total;
    }
}
