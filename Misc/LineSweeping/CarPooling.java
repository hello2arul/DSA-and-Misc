package Misc.LineSweeping;

import java.util.Map;
import java.util.TreeMap;

/*
 * Line Sweeping algorithm: https://en.wikipedia.org/wiki/Sweep_line_algorithm
 * https://leetcode.com/problems/car-pooling/description/
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi]
indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off
are fromi and toi respectively. The locations are given as the number of kilometers due east
from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 */
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new TreeMap<>();

        for(int[] trip: trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }

        for(int key: map.keySet()) {
            capacity -= map.get(key);
            if(capacity < 0)
                return false;
        }
        return true;
    }
}
