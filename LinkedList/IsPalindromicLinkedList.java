package LinkedList;

import java.util.Stack;

import LinkedList.LRUCache.ListNode;

/*
 * https://leetcode.com/problems/palindrome-linked-list
 */
public class IsPalindromicLinkedList {
     public boolean isPalindromeStack(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;

        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        node = head;
        while (!stack.isEmpty()) {
            if (stack.peek() != null && node.val != stack.pop().val)
                return false;

            node = node.next;
        }
        return stack.isEmpty();
    }
}
