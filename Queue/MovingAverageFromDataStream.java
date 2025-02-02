package Queue;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.ca/all/346.html
Google

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/
public class MovingAverageFromDataStream {

    private int size;
    private int sum;
    private Queue<Integer> queue;

    public MovingAverageFromDataStream(int size) {
        this.size = size;
        this.sum = 0;
        this.queue = new LinkedList<>();
    }

    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.poll();
        }
        queue.offer(val);
        sum += val;
        return (double) sum / queue.size();
    }
}
