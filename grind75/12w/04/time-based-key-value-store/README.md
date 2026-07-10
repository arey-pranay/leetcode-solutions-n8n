# Time Based Key Value Store

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `String` `Binary Search` `Design`  
**Time:** O(1)  
**Space:** O(n)

---

## Solution (java)

```java
class TimeMap {
    HashMap<String, TreeMap<Integer,String>> hm;
    public TimeMap() {
        this.hm = new HashMap<>();
    }
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer,String> tm = hm.getOrDefault(key,new TreeMap<>());
        tm.put(timestamp,value);
        hm.put(key,tm);
    }
    public String get(String key, int timestamp) {
        if(!hm.containsKey(key)) return "";
        TreeMap<Integer,String> tm = hm.get(key);        
        if(tm.containsKey(timestamp)) return tm.get(timestamp);
        if(tm.lowerKey(timestamp) == null) return "";
        return tm.get(tm.lowerKey(timestamp));
    }
}

```

---

---

## Quick Revision
This problem is about designing a data structure to store key-value pairs with timestamps, allowing efficient retrieval of values at specific timestamps. The solution uses a combination of HashMap and TreeMap for efficient storage and lookup.

## Intuition
The idea is to use a nested data structure where each key in the outer HashMap points to a TreeMap of timestamp-value pairs. This allows for fast insertion and retrieval of values at specific timestamps, as well as handling cases where no value exists at the exact timestamp.

## Algorithm

1. Initialize an empty HashMap `hm` with keys of type String and values of type TreeMap.
2. In the `set` method:
	* Get the current TreeMap for the given key from the HashMap, or create a new one if it doesn't exist.
	* Add the (timestamp, value) pair to the TreeMap.
	* Update the HashMap with the modified TreeMap.
3. In the `get` method:
	* Check if the HashMap contains the given key; if not, return an empty string.
	* Get the TreeMap for the given key and check if it contains the exact timestamp; if so, return the corresponding value.
	* If no exact match is found, but there's a lower timestamp in the TreeMap, return the value associated with that lower timestamp.

## Concept to Remember
* **Hash Maps**: efficient storage and lookup of key-value pairs.
* **Tree Maps**: ordered storage of key-value pairs by keys (or timestamps).
* **Nested Data Structures**: using multiple data structures together for efficient storage and retrieval.

## Common Mistakes
* Failing to consider the possibility of no value existing at a specific timestamp.
* Not initializing the TreeMap correctly, leading to incorrect results or errors.
* Not properly handling edge cases like empty strings or null values.

## Complexity Analysis
- Time: O(1) for `set` (average case), O(log n) for `get` (worst-case scenario with multiple lower timestamps).
- Space: O(n) for storing all key-value pairs and timestamps.

## Commented Code

```java
class TimeMap {
    // HashMap to store keys pointing to TreeMap of timestamp-value pairs
    HashMap<String, TreeMap<Integer,String>> hm;
    
    public TimeMap() {
        this.hm = new HashMap<>();
    }
    
    // Set a value at a specific timestamp for a given key
    public void set(String key, String value, int timestamp) {
        // Get the current TreeMap for the key; if not present, create a new one
        TreeMap<Integer,String> tm = hm.getOrDefault(key,new TreeMap<>());
        // Add the (timestamp, value) pair to the TreeMap
        tm.put(timestamp,value);
        // Update the HashMap with the modified TreeMap
        hm.put(key,tm);
    }
    
    // Get the value at a specific timestamp for a given key
    public String get(String key, int timestamp) {
        // Check if the key exists in the HashMap; if not, return an empty string
        if(!hm.containsKey(key)) return "";
        
        // Get the TreeMap for the key and check if it contains the exact timestamp
        TreeMap<Integer,String> tm = hm.get(key);
        if(tm.containsKey(timestamp)) return tm.get(timestamp);
        
        // If no exact match is found, but there's a lower timestamp in the TreeMap,
        // return the value associated with that lower timestamp
        if(tm.lowerKey(timestamp) == null) return "";
        return tm.get(tm.lowerKey(timestamp));
    }
}
```

## Interview Tips

* Be prepared to explain why you chose this data structure and how it allows for efficient storage and retrieval.
* Anticipate edge cases like empty strings or null values, and be ready to handle them correctly.
* Practice explaining your solution clearly and concisely.

## Revision Checklist
- [ ] Understand the problem requirements thoroughly.
- [ ] Design an efficient data structure to store key-value pairs with timestamps.
- [ ] Implement the `set` and `get` methods correctly.
- [ ] Test the implementation with various edge cases.

## Similar Problems

* LeetCode: [Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/)
* LeetCode: [Design an LRU Cache](https://leetcode.com/problems/design-an-lru-cache/)

## Tags
`Hash Map` `Tree Map`
