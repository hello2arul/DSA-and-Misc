package Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 * https://leetcode.com/problems/reconstruct-itinerary/
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight.
 * Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". 
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 */
public class ReconstructItinerary {
    Map<String, PriorityQueue<String>> adj = new HashMap<>();
    LinkedList<String> itinerary = new LinkedList<>();
    
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            adj.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            adj.get(ticket.get(0)).offer(ticket.get(1));
        }
        dfs("JFK");   
        return itinerary;
    }

    private void dfs(String node) {
        PriorityQueue<String> neighbours = adj.get(node);
        while (neighbours != null && !neighbours.isEmpty())
            dfs(neighbours.poll());
        itinerary.addFirst(node);
    }

    public List<String> findItineraryIterative(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            adj.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            adj.get(ticket.get(0)).offer(ticket.get(1));
        }
        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while (!stack.isEmpty()) {
            while (adj.getOrDefault(stack.peek(), new PriorityQueue<>()).size() > 0) {
                stack.add(adj.get(stack.peek()).poll());
            }
            itinerary.addFirst(stack.pop());
        } 
        return itinerary;
    }
}
