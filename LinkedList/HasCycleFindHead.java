package LinkedList;

import java.util.HashSet;

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

    //unclear
    public ListNode detectCycle(ListNode head) {
        if(head == null|| head.next == null || head.next.next == null)  return null;
        ListNode first = head.next;
        ListNode second = head.next.next;
        while(first != second) {
            if(second.next == null || second.next.next == null) return null;
            first = first.next;
            second = second.next.next;
        }
        first = head;
        while(first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
    }
}
