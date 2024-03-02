package LinkedList;

/*
 * https://leetcode.com/problems/swap-nodes-in-pairs/description/
 * 1. swap data
 * 2. 
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)    return head;
        ListNode next = head.next;
        head.next = swapPairs(head.next.next);
        next.next = head;
        return next;
    }
}
