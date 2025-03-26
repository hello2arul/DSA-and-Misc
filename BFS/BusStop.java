package BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/bus-routes/
Uber

You are given an array routes representing bus routes where routes[i] is a bus route that the ith
bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 
1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go 
to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

 

Example 1:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Example 2:

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1
 */

public class BusStop {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0; // Already at the target
        }

        // Step 1: Build the stop-to-bus map
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                adj.putIfAbsent(stop, new ArrayList<>());
                adj.get(stop).add(i); // Add bus route i to the stop
            }
        }

        // Step 2: BFS initialization
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedBuses = new HashSet<>();
        queue.offer(source);
        visitedStops.add(source);
        int busesTaken = 0;

        // Step 3: Perform BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            busesTaken++; // Increment the bus count for each level of BFS

            for (int i = 0; i < size; i++) {
                int currentStop = queue.poll();

                // Get all buses passing through the current stop
                for (int bus : adj.getOrDefault(currentStop, new ArrayList<>())) {
                    if (visitedBuses.contains(bus)) {
                        continue; // Skip if this bus has already been taken
                    }
                    visitedBuses.add(bus); // Mark the bus as visited

                    // Add all stops on this bus route to the queue
                    for (int stop : routes[bus]) {
                        if (stop == target) {
                            return busesTaken; // Found the target stop
                        }
                        if (!visitedStops.contains(stop)) {
                            visitedStops.add(stop);
                            queue.offer(stop);
                        }
                    }
                }
            }
        }

        return -1; // Target is not reachable
    }

    public static void main(String[] args) {
        BusStop busStop = new BusStop();

        int[][] routes1 = {{1, 2, 7}, {3, 6, 7}};
        System.out.println(busStop.numBusesToDestination(routes1, 1, 6)); // Output: 2

        int[][] routes2 = {{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}};
        System.out.println(busStop.numBusesToDestination(routes2, 15, 12)); // Output: -1
    }
}
