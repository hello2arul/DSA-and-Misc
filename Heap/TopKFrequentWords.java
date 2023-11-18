
//https://leetcode.com/problems/top-k-frequent-words

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();

        for(String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> maxHeap = new PriorityQueue<>(
            new Comparator<>() {
                public int compare(String a, String b) {
                    return map.get(b) - map.get(a) == 0 ?
                           a.compareTo(b) : Integer.compare(map.get(b), map.get(a));
                }
            }
        );
        for(String key: map.keySet()) {
            maxHeap.offer(key);
        }
        while(k-- > 0)  res.add(maxHeap.poll());
        return res;
    }
}
