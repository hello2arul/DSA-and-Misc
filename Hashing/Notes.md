## Collision Handling

## 1. Perfect Hashing
- If we know the keys in advance, then we can use Perfect Hashing.

## 2. If we do not know the keys, then

### 2.1 Chaining
- Whenever collision happens, insert the element at the end of linked list.
- **Data structures for storing changes**:
  1. **LinkedList** \(O(l)\)
  2. **Dynamic Sized Arrays** \(O(l)\)
  3. **Self Balancing BST (Java8 uses) (AVL, RedBlack)** \(O(\log l)\) (not cache friendly)

### 2.2 Open Addressing
- Use a single array, don't chain.
- Number of slots in hash table >= number of keys to be inserted
- Cache friendly
  1. **Linear Probing**
     - Linearly search for next empty slot when there is a collision.
     - Note: while deletion, mark entry as deleted otherwise search would see an empty slot and cause failures.
     - **Problem**: Clustering happens during collision resolution because any key that maps to the clustered location would be again placed in the next free slot.
  2. **Quadratic Probing**
     - Uses quadratic function to probe for the next available slot, aiming to reduce clustering.
  3. **Double Hashing**
     - Uses secondary hash function to calculate interval for probing.

## LinkedHashMap
- Maintains the order of insertion of keys.
