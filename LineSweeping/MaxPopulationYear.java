package LineSweeping;

import java.util.TreeMap;

/*
 * https://leetcode.com/problems/maximum-population-year/
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] 
 * indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year. 
The ith person is counted in year x's population if x is in the inclusive range 
[birthi, deathi - 1]. Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.
Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation: 
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.
 */
public class MaxPopulationYear {
    // O(nlogn)
    public int maximumPopulation(int[][] logs) {
        TreeMap<Integer, Integer> line = new TreeMap<>();
        int maxPopulation = 0;
        int maxYear = 0;
        int count = 0;

        for (int[] log : logs) {
            line.put(log[0], line.getOrDefault(log[0], 0) + 1);
            line.put(log[1], line.getOrDefault(log[1], 0) - 1);
        }

        for (Integer key : line.keySet()) {
            count += line.get(key);

            if (count > maxPopulation) {
                maxPopulation = count;
                maxYear = key;
            }
        }

        return maxYear;
    }
}
