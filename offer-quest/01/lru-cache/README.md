# Lru Cache

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:** See complexity section  
**Space:** O(M + K)

---

## Solution (java)

```java
class LRUCache {

    HashMap<Integer, Integer> lru;
    TreeMap<Integer, Integer> levels;
    int[] freq;
    int cap;
    int max;

    public LRUCache(int capacity) {

        lru = new HashMap<>();
        levels = new TreeMap<>();
        freq = new int[(int)1e6 + 1];
        cap = capacity;
        max = 0;
    }

    public int get(int key) {

        if (!lru.containsKey(key))
            return -1;

        // remove old level
        int oldLevel = freq[key];
        levels.remove(oldLevel);

        // assign new level
        max++;
        levels.put(max, key);
        freq[key] = max;

        return lru.get(key);
    }

    public void put(int key, int value) {

        // if key already exists
        if (lru.containsKey(key)) {

            // update value
            lru.put(key, value);

            // remove old level
            int oldLevel = freq[key];
            levels.remove(oldLevel);

            // assign new level
            max++;
            levels.put(max, key);
            freq[key] = max;

            return;
        }

        // if capacity full → remove LRU
        if (lru.size() == cap) {
            remove();
        }

        // insert new key
        max++;
        lru.put(key, value);
        levels.put(max, key);
        freq[key] = max;
    }

    public void remove() {

        int toRemoveLevel = levels.firstKey();
        int toRemoveKey = levels.get(toRemoveLevel);

        levels.remove(toRemoveLevel);
        lru.remove(toRemoveKey);

        freq[toRemoveKey] = -1;
    }
}
```

---

---
## Quick Revision
A cache that evicts the least recently used item when full.
Implemented using a HashMap for O(1) access and a data structure to track usage order.

## Intuition
The core idea is to efficiently track which item was used least recently. When we access an item (get or put), it becomes the most recently used. When the cache is full and we need to add a new item, we must remove the least recently used one.

A `HashMap` is perfect for O(1) `get` and `put` operations by key. However, it doesn't inherently maintain order. To track usage order, we need another data structure.

The provided solution uses a `TreeMap` to store usage "levels" (timestamps or counters) and a `freq` array to map keys to their current usage level. The `TreeMap` keeps the levels sorted, allowing us to easily find the minimum (least recently used) level.

The "aha moment" is realizing that we can simulate recency by assigning an increasing counter (`max`) to each access. The smallest counter value will always represent the least recently used item.

## Algorithm
1.  **Initialization**:
    *   Create a `HashMap` (`lru`) to store `key -> value` mappings.
    *   Create a `TreeMap` (`levels`) to store `usage_level -> key` mappings. This will keep track of usage order, with smaller keys being older.
    *   Create an integer array `freq` to map `key -> usage_level`. This allows quick lookup of a key's current usage level.
    *   Store the `capacity` (`cap`).
    *   Initialize a counter `max` to 0, which will represent the latest usage level.

2.  **`get(key)` operation**:
    *   Check if the `key` exists in the `lru` map. If not, return -1.
    *   If the `key` exists:
        *   Retrieve its `oldLevel` from the `freq` array.
        *   Remove the `oldLevel` entry from the `levels` `TreeMap`.
        *   Increment `max` to get a new, higher usage level.
        *   Add the `key` to the `levels` `TreeMap` with the new `max` level.
        *   Update the `freq` array for the `key` with the new `max` level.
        *   Return the `value` associated with the `key` from the `lru` map.

3.  **`put(key, value)` operation**:
    *   Check if the `key` already exists in the `lru` map.
    *   **If `key` exists**:
        *   Update the `value` in the `lru` map.
        *   Retrieve its `oldLevel` from the `freq` array.
        *   Remove the `oldLevel` entry from the `levels` `TreeMap`.
        *   Increment `max`.
        *   Add the `key` to the `levels` `TreeMap` with the new `max` level.
        *   Update the `freq` array for the `key` with the new `max` level.
        *   Return.
    *   **If `key` does not exist**:
        *   Check if the `lru` map's size has reached `cap`.
        *   If `lru.size() == cap`, call the `remove()` helper method to evict the LRU item.
        *   Increment `max`.
        *   Add the new `key` and `value` to the `lru` map.
        *   Add the `key` to the `levels` `TreeMap` with the new `max` level.
        *   Update the `freq` array for the `key` with the new `max` level.

4.  **`remove()` helper operation**:
    *   Get the smallest key (least recently used level) from the `levels` `TreeMap` using `firstKey()`.
    *   Get the `toRemoveKey` associated with this `toRemoveLevel`.
    *   Remove the entry from the `levels` `TreeMap`.
    *   Remove the `toRemoveKey` from the `lru` map.
    *   Optionally, set `freq[toRemoveKey]` to -1 or some indicator that it's no longer in the cache (though not strictly necessary if `lru.containsKey` is the primary check).

## Concept to Remember
*   **Hash Map**: For O(1) average time complexity for key-value lookups, insertions, and deletions.
*   **Ordered Data Structures**: To maintain the order of usage (recency). `TreeMap` provides sorted keys, allowing efficient retrieval of the minimum element.
*   **Time-Based Eviction**: Simulating recency using an incrementing counter or timestamp.
*   **Auxiliary Data Structures**: Using multiple data structures (HashMap, TreeMap, Array) to achieve desired performance characteristics.

## Common Mistakes
*   **Incorrectly updating usage order**: Forgetting to update the usage level of an item when it's accessed via `get` or `put`.
*   **Inefficient eviction**: Using a linear scan to find the LRU item instead of an ordered data structure.
*   **Handling capacity**: Not correctly checking for and enforcing the cache capacity, leading to exceeding the limit.
*   **Off-by-one errors**: Issues with the `max` counter or indices when updating levels or removing items.
*   **Not handling existing keys in `put`**: Treating an update to an existing key the same way as inserting a new key, which can lead to incorrect LRU tracking.

## Complexity Analysis
*   **Time**:
    *   `get(key)`: O(log N) - Due to `TreeMap.remove()` and `TreeMap.put()`, where N is the number of items in the cache. `HashMap` operations are O(1) on average.
    *   `put(key, value)`: O(log N) - Similar to `get`, due to `TreeMap` operations.
    *   `remove()`: O(log N) - Due to `TreeMap.firstKey()`, `TreeMap.remove()`.
    *   *Note*: If a Doubly Linked List and HashMap were used, `get` and `put` would be O(1). The provided solution uses `TreeMap` which introduces a logarithmic factor.
*   **Space**: O(M + K) - Where M is the capacity of the cache (for `lru` and `freq` array up to capacity) and K is the maximum possible key value for the `freq` array (1e6+1 in this case). If `freq` was sized dynamically or based on actual keys, it would be O(M). The `TreeMap` also stores up to M entries.

## Commented Code
```java
class LRUCache {

    // HashMap to store key-value pairs for O(1) average time access.
    HashMap<Integer, Integer> lru;
    // TreeMap to store usage levels (timestamps/counters) mapped to keys.
    // It keeps entries sorted by level, allowing O(log N) retrieval of the LRU item.
    TreeMap<Integer, Integer> levels;
    // Array to map each key to its current usage level for quick lookup.
    // Size 1e6+1 is a potential issue if keys can be larger or sparse.
    int[] freq;
    // The maximum capacity of the cache.
    int cap;
    // A counter that increments with each access, representing the latest usage level.
    int max;

    // Constructor to initialize the cache with a given capacity.
    public LRUCache(int capacity) {
        // Initialize the HashMap.
        lru = new HashMap<>();
        // Initialize the TreeMap.
        levels = new TreeMap<>();
        // Initialize the frequency array. The size is fixed and potentially large.
        freq = new int[(int)1e6 + 1]; // Assuming keys are within this range.
        // Set the cache capacity.
        cap = capacity;
        // Initialize the maximum usage level counter.
        max = 0;
    }

    // Retrieves the value associated with a key.
    public int get(int key) {
        // If the key is not present in the cache, return -1.
        if (!lru.containsKey(key))
            return -1;

        // If the key is present, it means it's being accessed, so it becomes the most recently used.
        // First, get its old usage level from the freq array.
        int oldLevel = freq[key];
        // Remove the old entry from the levels TreeMap because its level is changing.
        levels.remove(oldLevel);

        // Assign a new, higher usage level to this key.
        max++; // Increment the global max level counter.
        levels.put(max, key); // Add the key to the TreeMap with the new max level.
        freq[key] = max; // Update the freq array to point to the new usage level for this key.

        // Return the value associated with the key from the lru HashMap.
        return lru.get(key);
    }

    // Inserts or updates a key-value pair in the cache.
    public void put(int key, int value) {

        // Check if the key already exists in the cache.
        if (lru.containsKey(key)) {

            // If the key exists, update its value in the lru HashMap.
            lru.put(key, value);

            // Since the key is accessed, it becomes the most recently used.
            // Remove its old usage level from the levels TreeMap.
            int oldLevel = freq[key];
            levels.remove(oldLevel);

            // Assign a new, higher usage level.
            max++; // Increment the global max level counter.
            levels.put(max, key); // Add the key to the TreeMap with the new max level.
            freq[key] = max; // Update the freq array for this key.

            // The operation is complete for an existing key.
            return;
        }

        // If the key does not exist, we are inserting a new entry.
        // First, check if the cache is already at its maximum capacity.
        if (lru.size() == cap) {
            // If capacity is full, we need to evict the least recently used item.
            remove(); // Call the helper method to remove the LRU item.
        }

        // Now, insert the new key-value pair.
        max++; // Increment the global max level counter for the new entry.
        lru.put(key, value); // Add the new key-value pair to the lru HashMap.
        levels.put(max, key); // Add the key to the levels TreeMap with the new max level.
        freq[key] = max; // Update the freq array for this new key.
    }

    // Helper method to remove the least recently used item from the cache.
    public void remove() {

        // Get the smallest key from the levels TreeMap, which represents the LRU usage level.
        int toRemoveLevel = levels.firstKey();
        // Get the key associated with this LRU level.
        int toRemoveKey = levels.get(toRemoveLevel);

        // Remove the LRU entry from the levels TreeMap.
        levels.remove(toRemoveLevel);
        // Remove the LRU key-value pair from the lru HashMap.
        lru.remove(toRemoveKey);

        // Optionally, mark the removed key in the freq array as invalid or not present.
        // This is not strictly necessary if lru.containsKey is always checked first.
        freq[toRemoveKey] = -1; // Using -1 to indicate it's not in cache.
    }
}
```

## Interview Tips
*   **Clarify Constraints**: Ask about the range of keys and values, and the maximum capacity. The fixed-size `freq` array is a potential point of discussion if key ranges are large or unknown.
*   **Explain Trade-offs**: Discuss why a `HashMap` alone isn't enough and why an ordered structure is needed. Explain the time complexity implications of using `TreeMap` versus a Doubly Linked List.
*   **Walk Through Examples**: Be prepared to trace `get` and `put` operations with a small capacity cache (e.g., capacity 2) to demonstrate your understanding of the eviction process.
*   **Edge Cases**: Consider what happens when the cache is empty, when `capacity` is 1, or when all items are accessed repeatedly.

## Revision Checklist
- [ ] Understand the LRU eviction policy.
- [ ] Implement O(1) `get` and `put` using `HashMap`.
- [ ] Implement a mechanism to track usage order (e.g., `TreeMap` or Doubly Linked List).
- [ ] Handle cache capacity and eviction correctly.
- [ ] Consider time and space complexity.
- [ ] Test with edge cases (empty cache, capacity 1, repeated access).

## Similar Problems
*   Least Number of Unique Integers after K Removals
*   Design Hit Counter
*   LFU Cache (Least Frequently Used)

## Tags
`HashMap` `TreeMap` `Design` `Data Structure`

## My Notes
there is another more efficient appraoch without maps, using linked list type data structure
