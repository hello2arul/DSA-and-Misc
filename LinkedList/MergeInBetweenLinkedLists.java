package LinkedList;

/*
 * https://leetcode.com/problems/merge-in-between-linked-lists/description/
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.

Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 */
public class MergeInBetweenLinkedLists {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode prev = list1;
        ListNode next = list1;

        for (int i = 1; i < a; i++) {
            prev = prev.next;
        } 

        for (int i = 0; i < b; i++) {
            next = next.next;
        }
        
        if (prev != null)
            prev.next = list2;

        while (list2.next != null)
            list2 = list2.next;

        list2.next = next.next;

        return list1;
    }
}
