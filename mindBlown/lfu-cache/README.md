# Lfu Cache

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:** O(1)  
**Space:** O(N)

---

## Solution (java)

```java
class LFUCache {
    
    int capacity; 
    HashMap <Integer,Integer> map;
    TreeMap <Integer, LinkedHashSet<Integer>> freq;
    HashMap <Integer, Integer> count;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.freq = new TreeMap<>();
        this.count = new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        updateCountAndFreq(key);
        return map.get(key);
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key) && map.size()==capacity) delete();
        updateCountAndFreq(key);
        map.put(key,value); 
    }
    
    public void delete(){
        int countOfsetToRemove = freq.entrySet().iterator().next().getKey();
        LinkedHashSet<Integer> setToRemove = freq.get(countOfsetToRemove);
        int keyToRemove = setToRemove.iterator().next();
        setToRemove.remove(keyToRemove);
        if(setToRemove.size() == 0) freq.remove(countOfsetToRemove);
        count.remove(keyToRemove);
        map.remove(keyToRemove);
    }
    
    public void updateCountAndFreq(int key){
        int freqKey = 1;
        if(!count.containsKey(key)) count.put(key,1);
        else{
            int olderCount = count.get(key);
            count.put(key,olderCount+1);
            freq.get(olderCount).remove(key);
            if(freq.get(olderCount).size()==0) freq.remove(olderCount);
            freqKey = olderCount+1;
        }
        updateFreq(freqKey,key);
    }
    
    public void updateFreq(int freqKey, int key){
        LinkedHashSet<Integer> destSet = freq.getOrDefault(freqKey, new LinkedHashSet<>());
        destSet.add(key);
        freq.put(freqKey,destSet);
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);)
 * obj.put(key,value);
 */
```

---

---
## Quick Revision
Implement a cache that evicts the least frequently used item. If there's a tie in frequency, evict the least recently used among them.
This is solved using a combination of hash maps and a tree map to manage frequencies and linked hash sets for LRU within frequencies.

## Intuition
The core challenge is efficiently tracking both the frequency of access for each key and the recency of use within each frequency group.
We need a way to quickly find the least frequently used item. If multiple items have the same minimum frequency, we need to find the least recently used among *those*. This suggests a data structure that can group items by frequency and maintain order within those groups. A `TreeMap` where keys are frequencies and values are `LinkedHashSet`s (to maintain LRU order) seems appropriate. We also need quick lookups for key values and their current frequencies, which `HashMap`s excel at.

## Algorithm
1.  **Initialization**:
    *   `capacity`: The maximum number of items the cache can hold.
    *   `map`: A `HashMap` to store `key -> value` mappings.
    *   `freq`: A `TreeMap` where keys are frequencies (integers) and values are `LinkedHashSet<Integer>`s. The `LinkedHashSet` stores keys that have that specific frequency, maintaining insertion order (LRU). `TreeMap` keeps frequencies sorted, so the smallest frequency is always at the beginning.
    *   `count`: A `HashMap` to store `key -> frequency` mappings.

2.  **`get(key)`**:
    *   If `key` is not in `map`, return -1.
    *   Otherwise, call `updateCountAndFreq(key)` to increment its frequency and reposition it.
    *   Return the value associated with `key` from `map`.

3.  **`put(key, value)`**:
    *   If the cache is full (`map.size() == capacity`) and the `key` is new, call `delete()` to evict the LFU item.
    *   Call `updateCountAndFreq(key)` to handle frequency updates (or initial insertion).
    *   Put/update the `key -> value` in `map`.

4.  **`delete()`**:
    *   Get the smallest frequency from `freq.entrySet().iterator().next().getKey()`.
    *   Get the `LinkedHashSet` associated with this smallest frequency.
    *   Get the least recently used key from this set (`setToRemove.iterator().next()`).
    *   Remove this key from the `LinkedHashSet`.
    *   If the `LinkedHashSet` becomes empty, remove the frequency entry from `freq`.
    *   Remove the key from `count` and `map`.

5.  **`updateCountAndFreq(key)`**:
    *   Determine the `freqKey` (the new frequency).
    *   If `key` is not in `count` (new key):
        *   Set its frequency to 1 in `count`.
        *   `freqKey` remains 1.
    *   If `key` is already in `count` (existing key):
        *   Get its `olderCount`.
        *   Increment its count in `count` to `olderCount + 1`.
        *   Remove `key` from the `LinkedHashSet` associated with `olderCount` in `freq`.
        *   If the `LinkedHashSet` for `olderCount` becomes empty, remove `olderCount` from `freq`.
        *   Set `freqKey` to `olderCount + 1`.
    *   Call `updateFreq(freqKey, key)` to add the key to its new frequency group.

6.  **`updateFreq(freqKey, key)`**:
    *   Get the `LinkedHashSet` for `freqKey` from `freq`, creating a new one if it doesn't exist.
    *   Add `key` to this `LinkedHashSet`.
    *   Put the updated `LinkedHashSet` back into `freq` with `freqKey`.

## Concept to Remember
*   **Least Frequently Used (LFU) Eviction Policy**: Prioritizing removal of items accessed the fewest times.
*   **Least Recently Used (LRU) within Frequency**: When frequencies tie, the oldest item among them is removed.
*   **Data Structure Choice**: Using `TreeMap` for sorted frequencies and `LinkedHashSet` for LRU order within frequencies.
*   **Hash Map Efficiency**: `HashMap`s for O(1) average time complexity for key-value lookups and frequency tracking.

## Common Mistakes
*   **Incorrect LRU handling**: Not properly removing an item from its old frequency set and adding it to the new one, or not maintaining the LRU order within `LinkedHashSet`.
*   **Empty frequency set cleanup**: Forgetting to remove a frequency from `freq` if its `LinkedHashSet` becomes empty after an item is moved or evicted.
*   **Capacity check logic**: Incorrectly handling the eviction when `put` is called on a full cache with an existing key (it should update frequency, not evict).
*   **Off-by-one errors in frequency updates**: Miscalculating the new frequency or incorrectly handling the initial frequency of a new key.
*   **Handling `TreeMap` iteration**: Ensuring the iterator correctly points to the minimum frequency entry.

## Complexity Analysis
*   **Time**:
    *   `get(key)`: O(1) on average. `HashMap` lookups and `TreeMap` operations (getting the minimum frequency, `LinkedHashSet` operations) are amortized O(1) because the number of distinct frequencies is at most `capacity`.
    *   `put(key, value)`: O(1) on average. Similar reasoning as `get`. Eviction (`delete`) also involves constant time operations on average.
    *   `delete()`: O(1) on average. Accessing the first entry of `TreeMap` and `LinkedHashSet` is O(1).
    *   `updateCountAndFreq(key)`: O(1) on average. Involves `HashMap` operations and `LinkedHashSet` operations.
    *   `updateFreq(freqKey, key)`: O(1) on average. `HashMap` and `LinkedHashSet` operations.
*   **Space**: O(N), where N is the capacity of the cache. This is because `map`, `count`, and `freq` (specifically the `LinkedHashSet`s within it) will store up to N keys and their associated data.

## Commented Code
```java
class LFUCache {
    
    // The maximum number of key-value pairs the cache can hold.
    int capacity; 
    // Stores the key-value pairs. Key: Integer, Value: Integer.
    HashMap <Integer,Integer> map;
    // Stores frequencies. Key: Frequency (Integer), Value: LinkedHashSet of keys with that frequency.
    // LinkedHashSet maintains insertion order (LRU) for keys with the same frequency.
    // TreeMap keeps frequencies sorted, so the minimum frequency is always at the beginning.
    TreeMap <Integer, LinkedHashSet<Integer>> freq;
    // Stores the current frequency of each key. Key: Integer, Value: Frequency (Integer).
    HashMap <Integer, Integer> count;

    // Constructor to initialize the LFU cache with a given capacity.
    public LFUCache(int capacity) {
        this.capacity = capacity; // Set the cache capacity.
        this.map = new HashMap<>(); // Initialize the key-value map.
        this.freq = new TreeMap<>(); // Initialize the frequency map (TreeMap for sorted frequencies).
        this.count = new HashMap<>(); // Initialize the key-to-frequency map.
    }
    
    // Retrieves the value associated with a key.
    public int get(int key) {
        // If the key is not present in the cache, return -1.
        if(!map.containsKey(key)) return -1;
        // Update the frequency and recency of the key.
        updateCountAndFreq(key);
        // Return the value associated with the key.
        return map.get(key);
    }
    
    // Inserts or updates a key-value pair in the cache.
    public void put(int key, int value) {
        // If the key is new and the cache is at full capacity, evict the LFU item.
        if(!map.containsKey(key) && map.size()==capacity) delete();
        // Update the frequency and recency of the key (or add it if new).
        updateCountAndFreq(key);
        // Put or update the key-value pair in the main map.
        map.put(key,value); 
    }
    
    // Deletes the least frequently used (and least recently used among them) item from the cache.
    public void delete(){
        // Get the smallest frequency from the TreeMap. The first entry is the minimum frequency.
        int countOfsetToRemove = freq.entrySet().iterator().next().getKey();
        // Get the LinkedHashSet of keys that have this minimum frequency.
        LinkedHashSet<Integer> setToRemove = freq.get(countOfsetToRemove);
        // Get the least recently used key from this set (it's the first element due to LinkedHashSet's order).
        int keyToRemove = setToRemove.iterator().next();
        // Remove the key from the set of keys with this frequency.
        setToRemove.remove(keyToRemove);
        // If the set for this frequency becomes empty, remove the frequency entry from the TreeMap.
        if(setToRemove.size() == 0) freq.remove(countOfsetToRemove);
        // Remove the evicted key from the count map.
        count.remove(keyToRemove);
        // Remove the evicted key from the main key-value map.
        map.remove(keyToRemove);
    }
    
    // Updates the frequency count and repositions the key in the frequency structure.
    public void updateCountAndFreq(int key){
        int freqKey = 1; // Default new frequency is 1.
        // If the key is not yet in the count map, it's a new key.
        if(!count.containsKey(key)) {
            // Initialize its frequency to 1.
            count.put(key,1);
        }
        else{ // If the key already exists.
            // Get its current frequency.
            int olderCount = count.get(key);
            // Increment its frequency in the count map.
            count.put(key,olderCount+1);
            // Remove the key from its old frequency set in the freq TreeMap.
            freq.get(olderCount).remove(key);
            // If the old frequency set becomes empty, remove that frequency from the TreeMap.
            if(freq.get(olderCount).size()==0) freq.remove(olderCount);
            // The new frequency will be olderCount + 1.
            freqKey = olderCount+1;
        }
        // Add the key to its new frequency set.
        updateFreq(freqKey,key);
    }
    
    // Adds a key to the LinkedHashSet corresponding to its frequency.
    public void updateFreq(int freqKey, int key){
        // Get the LinkedHashSet for the given frequency, or create a new one if it doesn't exist.
        LinkedHashSet<Integer> destSet = freq.getOrDefault(freqKey, new LinkedHashSet<>());
        // Add the key to this set. This also marks it as the most recently used for this frequency.
        destSet.add(key);
        // Update the freq TreeMap with the modified (or new) LinkedHashSet.
        freq.put(freqKey,destSet);
    }

}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

## Interview Tips
*   **Explain the trade-offs**: Discuss why `TreeMap` and `LinkedHashSet` are chosen over simpler structures like just `HashMap`s. Emphasize the need for ordered frequencies and LRU within frequencies.
*   **Walk through an example**: Use a small capacity (e.g., 2) and a sequence of `put` and `get` operations to demonstrate how the data structures change and how eviction works.
*   **Clarify edge cases**: Be prepared to discuss what happens when the cache is empty, when `capacity` is 0, or when `put` is called on an existing key.
*   **Focus on complexity**: Clearly articulate the time and space complexity and justify why each operation is O(1) on average.

## Revision Checklist
- [ ] Understand the LFU eviction policy.
- [ ] Understand the LRU tie-breaking rule.
- [ ] Identify the need for multiple data structures: key-value store, frequency tracker, and ordered frequency groups.
- [ ] Implement `HashMap` for key-value and key-frequency lookups.
- [ ] Implement `TreeMap` to store frequencies and keep them sorted.
- [ ] Implement `LinkedHashSet` within `TreeMap` values to maintain LRU order for keys of the same frequency.
- [ ] Correctly handle `get` operation: update frequency and recency.
- [ ] Correctly handle `put` operation: update frequency, handle capacity, and evict if necessary.
- [ ] Implement the `delete` (eviction) logic: find LFU, then LRU among LFUs.
- [ ] Ensure proper cleanup of empty frequency sets in `TreeMap`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LRU Cache
*   Design Hit Counter
*   All Oone Data Structure

## Tags
`Hash Map` `Tree Map` `Linked List` `Doubly Linked List` `Design`

## My Notes
Amazing.There's another approach with linked lists but this is so intuitive but difficult,
