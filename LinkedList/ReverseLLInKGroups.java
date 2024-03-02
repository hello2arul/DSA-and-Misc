package LinkedList;

/*
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * 1. use a stack of size k
 * 2. recursive
 * 3. iterative
 */
public class ReverseLLInKGroups {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        ListNode prev = null, next = null;
        int i = 0;
        while(cur != null) {
            cur = cur.next;
            i++;
        }
        if(i < k)  {
            return head;
        }

        i = 0;
        cur = head;
        while(cur != null && i < k) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            i++;
        }
        if(next != null) {
            head.next = reverseKGroup(next, k);
        }
        return prev;
    }
}
