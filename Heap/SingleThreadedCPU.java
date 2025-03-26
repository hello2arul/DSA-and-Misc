package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/single-threaded-cpu/
Google
You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, 
where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be 
available to process at enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will 
act in the following way:

If the CPU is idle and there are no available tasks to process, the CPU remains idle.
If the CPU is idle and there are available tasks, the CPU will choose the one
with the shortest processing time. If multiple tasks have the same shortest processing time, 
it will choose the task with the smallest index.
Once a task is started, the CPU will process the entire task without stopping.
The CPU can finish a task then start a new one instantly.
Return the order in which the CPU will process the tasks.

Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
Explanation: The events go as follows: 
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. 
Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest.
Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.
TODO:
*/
public class SingleThreadedCPU {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] res = new int[n];

        // this is needed because we sort this and the indexing is lost
        int[][] extTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            extTasks[i][0] = tasks[i][0]; // enqueueTime
            extTasks[i][1] = tasks[i][1]; // processingTime
            extTasks[i][2] = i; // original index
        }

        // Step 2: Sort tasks by enqueueTime
        Arrays.sort(extTasks, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 3: Use a priority queue to manage available tasks
        PriorityQueue<int[]> availableTasks = new PriorityQueue<>(
                (a, b) -> a[1] != b[1] ? Integer.compare(a[1], b[1]) : Integer.compare(a[2], b[2]));

        int time = 0; // Current time
        int taskIdx = 0; // Index to iterate over tasks
        int resIdx = 0; // Index to fill the result array

        // Step 4: Process tasks
        while (resIdx < n) {
            // Add all tasks that are available at the current time to the priority queue
            while (taskIdx < n && extTasks[taskIdx][0] <= time) {
                availableTasks.offer(extTasks[taskIdx++]);
            }

            // If no tasks are available, move the time to the next task's enqueueTime
            if (availableTasks.isEmpty()) {
                time = extTasks[taskIdx][0];
                continue;
            }

            // Process the task with the shortest processing time (or smallest index)
            int[] curTask = availableTasks.poll();
            res[resIdx++] = curTask[2]; // Add the original index of the task to the result
            time += curTask[1]; // Increment the time by the task's processing time
        }

        return res;
    }
}
