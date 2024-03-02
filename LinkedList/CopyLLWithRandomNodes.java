package LinkedList;

import java.util.HashMap;

import org.w3c.dom.Node;

/*
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 * 1. use hashing
 * 2. insert a copy node inbetween every element and then construct accordingly
 */
public class CopyLLWithRandomNodes {
     public Node copyRandomList(Node head) {
        if(head == null)    return head;
        HashMap<Node, Node> map = new HashMap<>();
        Node trav = head;
        while(trav != null) {
            map.put(trav, new Node(trav.val));
            trav = trav.next;
        }
        trav = head;
        while(trav != null) {
            map.get(trav).next = map.getOrDefault(trav.next, null);
            map.get(trav).random = map.get(trav.random);
            trav = trav.next;
        }
        return map.get(head);
    }
}
