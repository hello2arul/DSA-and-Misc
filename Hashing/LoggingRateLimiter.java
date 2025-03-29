package Hashing;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

/*
 * Google
Imagine you have a robot that sends status messages that humans will read in real time.
The raw messages are hard to read for a human because there are often many messages produced in short periods of time.
One idea to make them more readable is to remove the duplicate messages over a 10 second window.
Design and implement a program to hide duplicates of any message that has already been displayed within the past 10 seconds.

Example Messages Received, with Timestamps:


10 solar panel activated
11 low battery warning
12 tire one: low air pressure
13 solar panel activated
14 low battery warning
21 solar panel activated
35 solar panel activated
Example Messages Shown to User:


10 solar panel activated
11 low battery warning
12 tire one: low air pressure
21 solar panel activated
35 solar panel activated

https://leetcode.ca/2016-11-23-359-Logger-Rate-Limiter/
 */
public class LoggingRateLimiter {
    HashMap<String, Integer> messages = new HashMap<>();

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!messages.containsKey(message) || timestamp - messages.get(message) >= 10) {
            messages.put(message, timestamp);
            return true;
        }
        return false;
    }

    private Deque<Pair<Integer, String>> messageQueue;
    private HashSet<String> recentMessages;
    private int windowSize;

    public void addNewMessage(int timestamp, String message) {
        // Remove old messages that are outside the time window
        while (!messageQueue.isEmpty() && messageQueue.peekFirst().getKey() <= timestamp - windowSize) {
            recentMessages.remove(messageQueue.pollFirst().getValue());
        }

        // Add new message if it hasn't been recently added
        if (!recentMessages.contains(message)) {
            System.out.println(timestamp + ": " + message);
            messageQueue.addLast(new Pair<>(timestamp, message));
            recentMessages.add(message);
        }
    }
}
