package LinkedList;

/*
 * 
 * https://leetcode.com/problems/odd-even-linked-list/description/
 */
public class OddEvenIndex {
    public ListNode oddEvenList(ListNode head) {
        if(head == null)    return null;
        ListNode even = head.next;
        ListNode odd = head;
        ListNode evenHead = even;
        while(even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
