package LinkedList;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;

/*
 * https://leetcode.com/problems/lfu-cache/
 * TODO: 
 * 
 * what if there are multiple keys with the same frequency?
 *  - Evict the LRU key among them
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

    // key, freq, val
    private Map<Integer, Pair<Integer, Integer>> cache;
    // freq, keys
    private Map<Integer, LinkedHashSet<Integer>> freq;
    private int minf;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        minf = 0;
        freq = new HashMap<>();
        cache = new HashMap<>();
    }
    
    public int get(int key) {
        Pair<Integer, Integer> fqVal = cache.get(key);
        if (fqVal == null)
            return -1;
        int fq = fqVal.getKey();
        Set<Integer> keys = freq.get(fq);
        keys.remove(key);

        if (keys.isEmpty()) {
            freq.remove(fq);

            if (minf == fq)
                ++minf;
        }

        int val = fqVal.getValue();
        insert(key, fq + 1, val);
        return val;
    }

    private void insert(int key, int fq, int val) {
        cache.put(key, new Pair<>(fq, val));
        freq.putIfAbsent(fq, new LinkedHashSet<>());
        freq.get(fq).add(key);
    }
    
    public void put(int key, int value) {
        if (capacity <= 0)
            return;
        Pair<Integer, Integer> fqVal = cache.get(key);

        if (fqVal != null) {
            cache.put(key, new Pair<>(fqVal.getKey(), value));
            get(key);
            return;
        }
        if (capacity == cache.size()) {
            Set<Integer> keys = freq.get(minf);
            int keyToDelete = keys.iterator().next();
            cache.remove(keyToDelete);
            keys.remove(keyToDelete);
            
            if (keys.isEmpty()) {
                freq.remove(minf);
            }
        }
        minf = 1;
        insert(key, 1, value);
    }
}
