package LinkedList;

import org.w3c.dom.Node;

/*
 * https://leetcode.ca/all/708.html
 */
public class InsertIntoASortedCircularLinkedList {
    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        
        // Case 1: The list is empty
        if (head == null) {
            newNode.next = newNode; // Point to itself
            return newNode;
        }
        
        Node prev = head;
        Node curr = head.next;
        boolean toInsert = false;

        while (true) {
            // Case 2: Found the correct spot to insert
            if (prev.value <= insertVal && insertVal <= curr.value) {
                toInsert = true;
            }
            // Case 3: We are at the boundary (max/min)
            if (prev.value > curr.value) {
                if (insertVal >= prev.value || insertVal <= curr.value) {
                    toInsert = true;
                }
            }
            
            if (toInsert) {
                prev.next = newNode;
                newNode.next = curr;
                return head;
            }
            
            prev = curr;
            curr = curr.next;
            // If we have looped around the list
            if (prev == head) break;
        }

        // If we reach here, it means we have to insert at the end
        prev.next = newNode;
        newNode.next = curr;
        
        return head;
    }
}
