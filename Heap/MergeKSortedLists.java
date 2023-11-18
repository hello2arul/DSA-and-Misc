//https://leetcode.com/problems/merge-k-sorted-lists

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
   class ListNode {
      int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

 public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>( 
            new Comparator<>() {
                public int compare(ListNode n1, ListNode n2) {
                    return Integer.compare(n1.val, n2.val);
                }
            }
        );

        for(ListNode node: lists) {
            if(node != null)
                minHeap.offer(node);
        }
        ListNode head = null;
        ListNode cur = null;

        while(!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            ListNode next = node.next;
            if(head == null) {
                head = node;
                cur = node;
                cur.next = null;
            } else {
                cur.next = node;
                cur = node;
                node.next = null;
            }
            if(next != null)
                minHeap.offer(next);
        }
        return head;
    }
}
