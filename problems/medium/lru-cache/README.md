# Lru Cache

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:** O(1)  
**Space:** O(n)

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

## Problem Summary
The problem is to implement an LRU (Least Recently Used) cache. The cache should be able to store a maximum of `capacity` number of items. Each item is associated with a key and a value. The `get` method should return the value associated with the key if it exists in the cache. If the key does not exist, it should return `-1`. The `put` method should add or update the value associated with the key in the cache.

## Approach and Intuition
The problem can be solved using a combination of a `HashMap` and a `TreeMap`. The `HashMap` is used to store the keys and values of the cache. The `TreeMap` is used to store the levels of the cache, where the keys are the levels and the values are the keys of the cache. The `freq` array is used to keep track of the frequency of each key.

## Complexity Analysis
- Time: O(1) - reason: All operations (get, put, remove) are performed in constant time, as they involve basic operations on the data structures (HashMap, TreeMap, array).
- Space: O(n) - reason: The space complexity is O(n) because in the worst case, we need to store all elements in the cache.

## Code Walkthrough
The code consists of four methods: `LRUCache`, `get`, `put`, and `remove`. The `LRUCache` constructor initializes the data structures. The `get` method returns the value associated with the key if it exists in the cache. The `put` method adds or updates the value associated with the key in the cache. The `remove` method is used to remove the least recently used item from the cache.

### LRUCache Constructor
```java
public LRUCache(int capacity) {
    lru = new HashMap<>();
    levels = new TreeMap<>();
    freq = new int[(int)1e6 + 1];
    cap = capacity;
    max = 0;
}
```

### get Method
```java
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
```

### put Method
```java
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
```

### remove Method
```java
public void remove() {
    int toRemoveLevel = levels.firstKey();
    int toRemoveKey = levels.get(toRemoveLevel);

    levels.remove(toRemoveLevel);
    lru.remove(toRemoveKey);

    freq[toRemoveKey] = -1;
}
```

## Interview Tips
- Make sure to understand the requirements of the problem.
- Choose the right data structures for the problem.
- Implement the code carefully and test it thoroughly.

## Optimization and Alternatives
- The current implementation uses a combination of a `HashMap` and a `TreeMap`. This is the most efficient way to implement an LRU cache.
- The `remove` method can be optimized to use a single line of code to remove the least recently used item.

## Revision Checklist
- [ ] Implement the `get` and `put` methods.
- [ ] Implement the `remove` method.
- [ ] Test the code thoroughly.

## Similar Problems
- Design a cache system.

## Tags
`Array` `Hash Map`
