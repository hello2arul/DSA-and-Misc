package LinkedList;

/*
 * https://leetcode.com/problems/linked-list-cycle/
 * 1. O(n^2) -> use two loops
 * 2. hashing
 * 3. two pointers
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        
        while(slow != fast) {
            if(fast == null || fast.next == null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
