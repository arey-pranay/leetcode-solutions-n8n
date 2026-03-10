# Lru Cache

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:** See complexity section  
**Space:** O(N)

---

## Solution (java)

```java
class LRUCache {
    HashMap <Integer,Integer> map ;
    int capacity ; 
    LinkedHashSet<Integer> set;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.set = new LinkedHashSet<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            set.remove(key);
            set.add(key);
            return map.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))set.remove(key);
        else if(capacity==map.size()){
            int temp = set.iterator().next();
            set.remove(temp);
            map.remove(temp);
        }
        set.add(key);
        map.put(key,value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

---

---
## Quick Revision
A cache that evicts the least recently used item when full.
Implemented using a HashMap for O(1) access and a LinkedHashSet to maintain order.

## Intuition
The core idea is to efficiently track both the key-value pairs and the order of usage. A HashMap provides fast lookups for key-value pairs. To maintain the "least recently used" order, we need a data structure that allows fast insertion, deletion, and iteration to find the oldest element. A `LinkedHashSet` is perfect for this because it maintains insertion order and allows O(1) average time complexity for `add`, `remove`, and iteration (to get the first element). When an item is accessed (`get`) or added/updated (`put`), it becomes the most recently used, so we move it to the end of our ordered structure. When the cache is full and we need to add a new item, we remove the item at the beginning of our ordered structure (the least recently used).

## Algorithm
1.  **Initialization**:
    *   Create a `HashMap` to store `key -> value` mappings.
    *   Create a `LinkedHashSet` to store keys in the order of their usage (most recently used at the end).
    *   Store the `capacity` of the cache.
2.  **`get(key)` Operation**:
    *   Check if the `key` exists in the `HashMap`.
    *   If it exists:
        *   Remove the `key` from the `LinkedHashSet` (to re-add it at the end, marking it as most recently used).
        *   Add the `key` back to the `LinkedHashSet`.
        *   Return the `value` associated with the `key` from the `HashMap`.
    *   If it does not exist, return `-1`.
3.  **`put(key, value)` Operation**:
    *   Check if the `key` already exists in the `HashMap`.
    *   If it exists:
        *   Remove the `key` from the `LinkedHashSet`.
        *   Add the `key` back to the `LinkedHashSet`.
        *   Update the `value` for the `key` in the `HashMap`.
    *   If it does not exist:
        *   Check if the cache is full (i.e., `map.size() == capacity`).
        *   If the cache is full:
            *   Get the least recently used key (the first element in the `LinkedHashSet`) using `set.iterator().next()`.
            *   Remove this least recently used key from the `LinkedHashSet`.
            *   Remove this least recently used key from the `HashMap`.
        *   Add the new `key` to the `LinkedHashSet`.
        *   Add the `key` and `value` to the `HashMap`.

## Concept to Remember
*   **Hash Maps**: For O(1) average time complexity for key-value lookups, insertions, and deletions.
*   **Linked Hash Sets**: To maintain insertion order and provide O(1) average time complexity for adding, removing, and iterating to find the first/last element.
*   **Cache Eviction Policies**: Understanding LRU as a common strategy to manage limited cache space.
*   **Data Structure Synergy**: Combining multiple data structures to achieve desired performance characteristics.

## Common Mistakes
*   **Incorrectly updating the order**: Forgetting to move an accessed or updated key to the "most recently used" position in the ordered data structure.
*   **Not handling cache full condition**: Failing to evict the least recently used item when adding a new item to a full cache.
*   **Confusing key and value in the ordered structure**: The `LinkedHashSet` should store keys, not values, to track usage order.
*   **Inefficient eviction**: Using a data structure that doesn't allow O(1) retrieval of the least recently used item.
*   **Not handling duplicate `put` operations correctly**: Ensuring that if a key already exists, its value is updated and its recency is refreshed.

## Complexity Analysis
*   **Time**:
    *   `get(key)`: O(1) on average. `HashMap.containsKey`, `HashMap.get`, `LinkedHashSet.remove`, and `LinkedHashSet.add` are all O(1) on average.
    *   `put(key, value)`: O(1) on average. `HashMap.containsKey`, `HashMap.remove`, `HashMap.put`, `LinkedHashSet.remove`, `LinkedHashSet.add`, and `set.iterator().next()` are all O(1) on average.
*   **Space**: O(N), where N is the capacity of the cache. This is because both the `HashMap` and `LinkedHashSet` will store up to N key-value pairs/keys.

## Commented Code
```java
class LRUCache {
    // HashMap to store key-value pairs for O(1) average time access.
    HashMap <Integer,Integer> map ;
    // The maximum number of key-value pairs the cache can hold.
    int capacity ;
    // LinkedHashSet to maintain the order of keys based on their usage.
    // The head of the set is the least recently used, and the tail is the most recently used.
    LinkedHashSet<Integer> set;

    // Constructor to initialize the LRUCache with a given capacity.
    public LRUCache(int capacity) {
        // Set the capacity of the cache.
        this.capacity = capacity;
        // Initialize the HashMap.
        this.map = new HashMap<>();
        // Initialize the LinkedHashSet.
        this.set = new LinkedHashSet<>();
    }

    // Retrieves the value associated with the given key.
    public int get(int key) {
        // Check if the key exists in the cache (i.e., in the map).
        if(map.containsKey(key)){
            // If the key exists, it means it's being accessed, so it becomes the most recently used.
            // Remove it from its current position in the set.
            set.remove(key);
            // Add it back to the set, which places it at the end (most recently used).
            set.add(key);
            // Return the value associated with the key from the map.
            return map.get(key);
        }
        // If the key does not exist in the cache, return -1.
        return -1;
    }

    // Inserts or updates a key-value pair in the cache.
    public void put(int key, int value) {
        // Check if the key already exists in the cache.
        if(map.containsKey(key)){
            // If the key exists, we are updating its value and making it the most recently used.
            // Remove it from its current position in the set.
            set.remove(key);
        }
        // If the key does not exist, we need to check if the cache is full before adding a new entry.
        else if(capacity==map.size()){
            // If the cache is full, we need to evict the least recently used item.
            // Get the least recently used key (which is the first element in the LinkedHashSet).
            int temp = set.iterator().next();
            // Remove the least recently used key from the set.
            set.remove(temp);
            // Remove the least recently used key-value pair from the map.
            map.remove(temp);
        }
        // Now, add the new or updated key to the set, marking it as the most recently used.
        set.add(key);
        // Put/update the key-value pair in the map.
        map.put(key,value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

## Interview Tips
*   **Explain the data structures**: Clearly articulate why a `HashMap` and a `LinkedHashSet` are chosen and how they work together.
*   **Walk through edge cases**: Discuss what happens when the cache is empty, full, or when a key is accessed multiple times.
*   **Consider alternative implementations**: Briefly mention that a Doubly Linked List combined with a HashMap is another common and efficient approach, and be prepared to discuss its trade-offs.
*   **Focus on time complexity**: Emphasize the O(1) average time complexity for both `get` and `put` operations.

## Revision Checklist
- [ ] Understand the LRU eviction policy.
- [ ] Implement `get` operation correctly, updating recency.
- [ ] Implement `put` operation, handling updates and evictions.
- [ ] Ensure O(1) average time complexity for both operations.
- [ ] Verify space complexity.
- [ ] Test with edge cases (empty cache, full cache, repeated operations).

## Similar Problems
*   Least Number of Unique Integers after K Removals
*   Design Browser History
*   All O'one Data Structure

## Tags
`Hash Map` `Linked List` `Design`

## My Notes
Imp very. 1 hashmap and 1 linkedhashset
