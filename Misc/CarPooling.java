package Misc;

import java.util.Map;
import java.util.TreeMap;

/*
 * Line Sweeping algorithm: https://en.wikipedia.org/wiki/Sweep_line_algorithm
 * https://leetcode.com/problems/car-pooling/description/
 */
public class CarPooling {
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
