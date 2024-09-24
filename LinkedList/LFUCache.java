package LinkedList;


/*
 * https://leetcode.com/problems/lfu-cache/
 * TODO: 
 */
public class LFUCache {
    class ListNode {
        int key;
        int val;
        int freq;
        ListNode next;
        ListNode prev; 

        public ListNode(int key, int val) {
            this.val = val;
            this.key = key;
            this.freq = 1;
        }
    }

    public LFUCache(int capacity) {
        
    }
    
    public int get(int key) {
        
    }
    
    public void put(int key, int value) {
        
    }
}
