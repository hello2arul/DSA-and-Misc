package Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * https://leetcode.com/problems/insert-delete-getrandom-o1
 * insert, remove works with hashing, how about without and what about getRandom?
 * use a list + hashMap(val, index), to delete a val from list in constant time,
 * get the idx from the hashMap, swap it with the last element and popLast()
 * 1. insert
 * 2. remove
 * 3. getRandom - https://www.freecodecamp.org/news/random-number-generator/
 */
public class InsertDeleteGetRandomO1 {
     List<Integer> list;
    Map<Integer, Integer> map;

    public InsertDeleteGetRandomO1() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;

        int idx = map.get(val);
        map.put(list.get(list.size() - 1), idx);
        list.set(idx, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        map.remove(val);

        return true;
    }
    
    public int getRandom() {
        Random random  = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
