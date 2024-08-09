package Design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Design.AllO1DataStructure.ListNode;


/*
 * https://leetcode.com/problems/all-oone-data-structure/
 * HARD
 * Also refer LRUCache
 */
public class AllO1DataStructure {
    class ListNode {
        int count;
        Set<String> keys;
        ListNode next;
        ListNode prev; 

        public ListNode(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private ListNode head;
    private ListNode tail;
    private Map<String, ListNode> map;

    public AllO1DataStructure() {
        head = null;
        tail = null;
        map = new HashMap<>();
    }
    
    public void inc(String key) {
        if (map.containsKey(key)) {
            ListNode cur = map.get(key);
            cur.keys.remove(key);
            
            if (cur == tail) {
                ListNode next = new ListNode(cur.count + 1);
                next.prev = cur;
                cur.next = next;
                tail = next;
                cur.next.keys.add(key);
                map.put(key, cur.next); 
            } else {
                ListNode next = cur.next;

                if (cur.count + 1 == next.count) {
                    next.keys.add(key);
                    map.put(key, next);
                } else {
                    ListNode newNode = new ListNode(cur.count + 1);
                    cur.next.prev = newNode;
                    newNode.next = cur.next;
                    newNode.prev = cur;
                    cur.next = newNode;
                    newNode.keys.add(key);
                    map.put(key, newNode);
                }
            }


            if (cur.keys.isEmpty()) {
                removeNode(cur);
            }
        } else {
            if (head == null) {
                head = new ListNode(1);
                tail = head;
            } else {
                if (head.count != 1) {
                    ListNode newNode = new ListNode(1);
                    newNode.keys.add(key);
                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                }
            }
            head.keys.add(key);
            map.put(key, head);
        }
    }
    
    public void dec(String key) {
        if (!map.containsKey(key))
            return;

        ListNode cur = map.get(key);
        cur.keys.remove(key); 

        if (cur.count == 1) {
            map.remove(key);
        } else if (cur == head) {
            ListNode newNode = new ListNode(cur.count - 1);
            newNode.keys.add(key);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            map.put(key, head);
        } else {
            ListNode prev = cur.prev;

            if (cur.count - 1 == prev.count) {
                prev.keys.add(key);
                map.put(key, prev);
            } else {
                ListNode newNode = new ListNode(cur.count - 1);
                prev.next = newNode;
                newNode.prev = prev;
                newNode.next = cur;
                cur.prev = newNode;
                newNode.keys.add(key);
                map.put(key, newNode);
            }
        }

        if (cur.keys.isEmpty()) {
            removeNode(cur);
        }
    }
    
    public String getMaxKey() {
        if (head == null)
            return "";
        return tail.keys.iterator().next();
    }
    
    public String getMinKey() {
        if (head == null)
            return "";
        return head.keys.iterator().next();
    }

    private void removeNode(ListNode node) {
        if(node == head) {
            head = head.next;
            node.next = null;
            if (head != null) {
                head.prev = null;
            }
        } else if(node == tail) {
            node.prev.next = null;
            tail = node.prev;
            node.prev = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = node.next = null;
        }
    }
}
