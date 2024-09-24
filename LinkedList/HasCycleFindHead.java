package LinkedList;

import java.util.HashSet;

import LinkedList.LRUCache.ListNode;

/*
 * https://leetcode.com/problems/linked-list-cycle-ii/description/
 */
public class HasCycleFindHead {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        HashSet<ListNode> set = new HashSet<>();
        
        while(head != null) {
            if(!set.contains(head)) {
                set.add(head);
            } else {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    // unclear
    public ListNode detectCycleII(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
