package Searching;

/**
 * https://leetcode.com/problems/minimum-time-to-complete-trips
 */
public class MinimumTimeToCompleteSearch {
    public long minimumTime(int[] time, int totalTrips) {
        long low = Long.MAX_VALUE;
        for (int t: time)
            low = Math.min(low, t);
        long high = low * totalTrips; // worst case

        while (low < high) {
            long mid = low + (high - low) / 2;
            if (isValid(time, totalTrips, mid))
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    private boolean isValid(int[] time, int totalTrips, long mid) {
        long trips = 0;
        for (int t: time) {
            trips += mid / t;
        }
        return trips >= totalTrips;
    }
}
