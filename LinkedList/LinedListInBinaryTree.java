package LinkedList;

/*
 * https://leetcode.com/problems/linked-list-in-binary-tree/
Given a binary tree root and a linked list with head as the first node. 
Return True if all the elements in the linked list starting from the head 
correspond to some downward path connected in the binary tree otherwise return False.

In this context downward path means a path that starts at some node and goes downwards.
 */
class LinkedListInBinaryTree {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;

        if (dfs(head, root))
            return true;
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;
        return head.val == root.val && (dfs(head.next, root.left) || dfs(head.next, root.right));
    }
}