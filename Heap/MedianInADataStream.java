package Heap;

/*
https://leetcode.com/problems/find-median-from-data-stream/editorial/
Google
median != average
*/
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianInADataStream {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    double median;

    public MedianInADataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        median = 0;
    }

    public void addNum(int num) {
        if (minHeap.isEmpty() || num > minHeap.peek())
            minHeap.add(num);
        else
            maxHeap.add(num);
        rebalanceHeaps();
        updateMedian();
    }

    private void rebalanceHeaps() {
        if (minHeap.size() - maxHeap.size() == 2)
            maxHeap.add(minHeap.remove());
        else if (maxHeap.size() - minHeap.size() == 2)
            minHeap.add(maxHeap.remove());
    }

    private void updateMedian() {
        if (minHeap.size() == maxHeap.size())
            median = (minHeap.peek() + maxHeap.peek()) / 2.0;
        else if (minHeap.size() > maxHeap.size())
            median = minHeap.peek();
        else
            median = maxHeap.peek();
    }

    public double findMedian() {
        return median;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */