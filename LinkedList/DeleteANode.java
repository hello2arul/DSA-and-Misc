package LinkedList;

/*
 * https://leetcode.com/problems/delete-node-in-a-linked-list/description/
 */
public class DeleteANode {
    //doesn't work for last node
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
