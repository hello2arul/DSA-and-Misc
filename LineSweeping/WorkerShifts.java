package LineSweeping;

/*
Google
https://leetcode.com/discuss/post/6522571/google-l5-interview-january-2025-by-anon-v92c/
You are given a list of worker shifts, where each shift is
represented as a list [name, start, end]. The name is a string
representing the worker's name, start is an integer representing the
start time of the shift, and end is an integer representing the end
time of the shift. The goal is to generate a schedule that shows the
intervals of time and the workers present during each interval.

Example:
Given the input:
[["Abby", 10, 100], ["Ben", 50, 70], ["Carla", 60, 70], 
["David", 120, 300]]

The output should be:
[[10, 50, ["Abby"]], [50, 60, ["Abby", "Ben"]], 
[60, 70, ["Abby", "Ben", "Carla"]], [70, 100, ["Abby"]], 
[120, 300, ["David"]]]

*/ 

import java.util.*;

public class WorkerShifts {

    public List<List<Object>> generateSchedule(List<List<Object>> shifts) {
        List<Event> events = new ArrayList<>();

        // Create events for each shift
        for (List<Object> shift : shifts) {
            String name = (String) shift.get(0);
            int start = (int) shift.get(1);
            int end = (int) shift.get(2);
            events.add(new Event(start, name, true));
            events.add(new Event(end, name, false));
        }

        // Sort events by time, with end events before start events if times are equal
        Collections.sort(events,
                (a, b) -> a.time != b.time ? Integer.compare(a.time, b.time) : Boolean.compare(a.isStart, b.isStart));

        List<List<Object>> result = new ArrayList<>();
        Set<String> currentWorkers = new HashSet<>();
        int prevTime = -1;

        // Process events
        for (Event event : events) {
            if (prevTime != -1 && prevTime != event.time) {
                result.add(Arrays.asList(prevTime, event.time, new ArrayList<>(currentWorkers)));
            }
            if (event.isStart) {
                currentWorkers.add(event.name);
            } else {
                currentWorkers.remove(event.name);
            }
            prevTime = event.time;
        }

        return result;
    }

    public static void main(String[] args) {
        WorkerShifts ws = new WorkerShifts();
        List<List<Object>> shifts = Arrays.asList(
                Arrays.asList("Abby", 10, 100),
                Arrays.asList("Ben", 50, 70),
                Arrays.asList("Carla", 60, 70),
                Arrays.asList("David", 120, 300));
        List<List<Object>> schedule = ws.generateSchedule(shifts);
        for (List<Object> interval : schedule) {
            System.out.println(interval);
        }
    }

    // Event class to represent start and end times
    private static class Event {
        int time;
        String name;
        boolean isStart;

        Event(int time, String name, boolean isStart) {
            this.time = time;
            this.name = name;
            this.isStart = isStart;
        }
    }
}