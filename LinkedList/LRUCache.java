package LinkedList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import LinkedList.LRUCache.ListNode;

/*
 * https://leetcode.com/problems/lru-cache/description/
 * 1. using LikedHashMap
 * 2. DLL + Hashing
 * if hit, move node to the front
 * else insert new node at the head the update hashtable
 */
public class LRUCache {
    /*
     * private int capacity;
     * private LinkedHashMap<Integer, Integer> cache;
     * 
     * public LRUCache(int capacity) {
     * this.capacity = capacity;
     * this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
     * 
     * @Override
     * protected boolean removeEldestEntry(Map.Entry e) {
     * return size() > capacity;
     * }
     * };
     * }
     * 
     * public int get(int key) {
     * return cache.getOrDefault(key, -1);
     * }
     * 
     * public void put(int key, int value) {
     * cache.put(key, value);
     * }
     */

    class ListNode {
        int key;
        int val;
        ListNode next;
        ListNode prev;

        public ListNode(int key, int val) {
            this.val = val;
            this.key = key;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    ListNode head;
    ListNode tail;
    int capacity;
    Map<Integer, ListNode> cache;

    public LRUCache(int capacity) {
        head = tail = null;
        cache = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        ListNode cur = cache.get(key);
        moveNodeToFront(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.get(key).val = value;
            moveNodeToFront(cache.get(key));
        } else {
            ListNode cur = new ListNode(key, value);
            insertNodeAtFront(cur);
            cache.put(key, cur);

            if (cache.size() > capacity) {
                removeNode(cache.remove(tail.key));
            }
        }
    }

    private void insertNodeAtFront(ListNode node) {
        if (head == null) {
            head = tail = node;
        } else {
            head.prev = node;
            node.next = head;
            node.prev = null;
            head = node;
        }
    }

    private void moveNodeToFront(ListNode node) {
        if (node == head) {
            return;
        } else if (node == tail) {
            ListNode newTail = node.prev;
            newTail.next = null;
            tail = newTail;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        insertNodeAtFront(node);
    }

    private void removeNode(ListNode node) {
        if (node == head) {
            head = tail = null;
        } else if (node == tail) {
            node.prev.next = null;
            tail = node.prev;
            node.prev = null;
        } else {
            // System.out.println(head + "" + tail + node);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = node.next = null;
        }
    }
}
