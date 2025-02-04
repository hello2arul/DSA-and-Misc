package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
https://leetcode.com/discuss/interview-question/6352084/Google-or-L4-or-Phone-Screening-Round
Google
 
Given an origin airport, destination airport, and series of flights determine 
whether it is possible for a package at the origin to reach the destination. 
A flight is represented as departure airport, arrival airport, departure time, and arrival time.
During the transportation, the time that the package leaves the airport needs to be greater 
than or equal to the time it arrives at the airport. Please determine whether it is possible 
for a package transfer from s to t. The package arrived at s at time 0.


E.g. 1
Origin: "NYC"
Destination: "SFO"
Flights: NYC → LAX, Departure: 0, Arrival: 4
LAX - SFO, Departure: 5, Arrival: 7
Output: True


E.g 2
Origin: "NYC" Destination: "SFO"
Flights: NYC →> LAX, Departure: 0, Arrival: 4
LAX -> SFO, Departure: 3, Arrival: 5
Output: False
*/
public class CanPackageBeTransported {

    static class Flight {
        String departure;
        String arrival;
        int departureTime;
        int arrivalTime;

        Flight(String departure, String arrival, int departureTime, int arrivalTime) {
            this.departure = departure;
            this.arrival = arrival;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
        }
    }

    public boolean canPackageBeTransported(String origin, String destination, List<Flight> flights) {
        Map<String, List<Flight>> graph = new HashMap<>();

        // Build the graph
        for (Flight flight : flights) {
            graph.computeIfAbsent(flight.departure, k -> new ArrayList<>()).add(flight);
        }

        // BFS to find the path
        Queue<Pair> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Pair(origin, 0));
        visited.add(origin);

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            String currentAirport = current.airport;
            int currentTime = current.time;

            if (currentAirport.equals(destination)) {
                return true;
            }

            if (!graph.containsKey(currentAirport)) {
                continue;
            }

            for (Flight flight : graph.get(currentAirport)) {
                if (flight.departureTime >= currentTime && !visited.contains(flight.arrival)) {
                    queue.offer(new Pair(flight.arrival, flight.arrivalTime));
                    visited.add(flight.arrival);
                }
            }
        }

        return false;
    }

    static class Pair {
        String airport;
        int time;

        Pair(String airport, int time) {
            this.airport = airport;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        CanPackageBeTransported solver = new CanPackageBeTransported();
        List<Flight> flights1 = Arrays.asList(
            new Flight("NYC", "LAX", 0, 4),
            new Flight("LAX", "SFO", 5, 7)
        );
        System.out.println(solver.canPackageBeTransported("NYC", "SFO", flights1)); // Output: true

        List<Flight> flights2 = Arrays.asList(
            new Flight("NYC", "LAX", 0, 4),
            new Flight("LAX", "SFO", 3, 5)
        );
        System.out.println(solver.canPackageBeTransported("NYC", "SFO", flights2)); // Output: false
    }
}