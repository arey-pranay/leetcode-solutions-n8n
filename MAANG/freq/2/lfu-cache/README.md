# Lfu Cache

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:**   
**Space:** O(N)

---

## Solution (java)

```java
class LFUCache {
    int capacity;
    int minFreq;
    
    class Node{
        int key, value, count;
        Node next, prev;
        Node(int key, int value){
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }
    class DLL{
        Node head,tail;
        DLL(Node node){
            this.head = node;
            this.tail = node;
        }
    }
    
    Map<Integer,Node> map = new HashMap<>(); // node of a given key
    Map<Integer,DLL> freqs = new HashMap<>(); // list of nodes of a given freqt
    
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
    }
  

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        increment(node);
        return node.value;
    }
    
    public void put(int key, int value) {        
        if(map.containsKey(key)){
            Node oldNode = map.get(key);
            oldNode.value = value;
            increment(oldNode);
        } else {
            if(map.size()==capacity){
                DLL minDLL = freqs.get(minFreq);
                Node toRemove = minDLL.head;
                map.remove(toRemove.key);
                removeNodeFrom(minDLL,toRemove);
                if(minDLL.head==null)freqs.remove(minFreq);
            }
            minFreq=1;
            Node node = new Node(key,value);
            map.put(key,node);
            if(!freqs.containsKey(1)) freqs.put(1,new DLL(node));
            else putAtEnd(freqs.get(1),node);            
        } 
    }
    
    public void increment(Node node){
        int oldFreq = node.count;
        DLL oldDLL = freqs.get(oldFreq);
        removeNodeFrom(oldDLL, node);
        if(oldDLL.head==null) {
            freqs.remove(oldFreq); 
            if(oldFreq == minFreq) minFreq++;
        }
        node.count++;
        if(!freqs.containsKey(oldFreq+1)) freqs.put(node.count,new DLL(node));
        else putAtEnd(freqs.get(node.count),node);
    }
    
    public void putAtEnd(DLL dll , Node node){
        node.next = null;
        dll.tail.next = node;
        node.prev = dll.tail;
        dll.tail = node;
    }
    
    public void removeNodeFrom(DLL dll, Node node){
        if(node.prev==null) dll.head = node.next;
        else node.prev.next = node.next;
        if(node.next==null) dll.tail = node.prev;
        else node.next.prev = node.prev;
        node.prev = null; node.next=null;
    }
}



/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

---

---
## Quick Revision
This problem requires implementing a Least Frequently Used (LFU) cache.
We solve it by using a hash map to store nodes and another hash map to group nodes by their frequency, utilizing doubly linked lists for each frequency.

## Intuition
The core challenge is efficiently tracking both the frequency of access for each key and evicting the least frequently used item when the cache is full. A naive approach of iterating through all items to find the LFU item would be too slow. We need a way to quickly identify the LFU item.

The "aha moment" comes from realizing that we can maintain separate lists (or groups) of items for each frequency count. When an item's frequency increases, we move it from its current frequency list to the next higher frequency list. This allows us to directly access the list of items with the minimum frequency and, within that list, pick the least recently used one (which is the head of the DLL).

## Algorithm
1.  **Data Structures:**
    *   `map`: A `HashMap<Integer, Node>` to store key-to-node mappings for O(1) access.
    *   `freqs`: A `HashMap<Integer, DLL>` to store frequency-to-Doubly Linked List mappings. Each DLL will contain nodes with the same frequency.
    *   `Node` class: Stores `key`, `value`, `count` (frequency), `next`, and `prev` pointers.
    *   `DLL` class: Represents a Doubly Linked List with `head` and `tail` pointers.
    *   `capacity`: The maximum number of items the cache can hold.
    *   `minFreq`: An integer to track the current minimum frequency among all items in the cache.

2.  **Constructor `LFUCache(int capacity)`:**
    *   Initialize `capacity`.
    *   Initialize `minFreq` to 0 (or 1, depending on how you handle initial insertions).

3.  **`get(int key)`:**
    *   Check if `key` exists in `map`. If not, return -1.
    *   Retrieve the `Node` from `map`.
    *   Call `increment(node)` to update its frequency and move it to the appropriate frequency list.
    *   Return the `node.value`.

4.  **`put(int key, int value)`:**
    *   **If `key` exists:**
        *   Get the existing `Node` from `map`.
        *   Update its `value`.
        *   Call `increment(node)`.
    *   **If `key` does not exist:**
        *   **Check capacity:** If `map.size() == capacity`:
            *   Get the `DLL` for `minFreq` from `freqs`.
            *   Get the `Node` at the head of this `DLL` (this is the LFU and LRU item).
            *   Remove this `Node` from `map`.
            *   Remove this `Node` from its `DLL` using `removeNodeFrom`.
            *   If the `DLL` becomes empty after removal, remove the `minFreq` entry from `freqs`.
        *   **Create new node:** Create a new `Node` with the given `key` and `value`. Its frequency (`count`) will be 1.
        *   Add the new `Node` to `map`.
        *   Set `minFreq` to 1 (since a new item always starts with frequency 1).
        *   If `freqs` does not contain frequency 1, create a new `DLL` for frequency 1 and add the new `Node` to it.
        *   If `freqs` already contains frequency 1, add the new `Node` to the end of the existing `DLL` for frequency 1 using `putAtEnd`.

5.  **`increment(Node node)`:**
    *   Get the `oldFreq` of the `node`.
    *   Get the `DLL` corresponding to `oldFreq` from `freqs`.
    *   Remove the `node` from its `oldFreq` `DLL` using `removeNodeFrom`.
    *   **Update `minFreq`:** If the `oldFreq` `DLL` becomes empty and `oldFreq` was equal to `minFreq`, increment `minFreq`.
    *   If the `oldFreq` `DLL` becomes empty, remove the entry for `oldFreq` from `freqs`.
    *   Increment `node.count`.
    *   Get the `newFreq` (which is `node.count`).
    *   If `freqs` does not contain `newFreq`, create a new `DLL` for `newFreq` and add the `node` to it.
    *   If `freqs` already contains `newFreq`, add the `node` to the end of the existing `DLL` for `newFreq` using `putAtEnd`.

6.  **`putAtEnd(DLL dll, Node node)`:**
    *   Set `node.next` to `null`.
    *   Link the current `dll.tail` to `node` (`dll.tail.next = node`).
    *   Set `node.prev` to the current `dll.tail` (`node.prev = dll.tail`).
    *   Update `dll.tail` to be the new `node`.

7.  **`removeNodeFrom(DLL dll, Node node)`:**
    *   Handle the case where `node` is the head: If `node.prev == null`, update `dll.head = node.next`.
    *   Otherwise, link `node.prev.next` to `node.next`.
    *   Handle the case where `node` is the tail: If `node.next == null`, update `dll.tail = node.prev`.
    *   Otherwise, link `node.next.prev` to `node.prev`.
    *   Set `node.prev` and `node.next` to `null` to detach the node.

## Concept to Remember
*   **Hash Maps for O(1) Lookups:** Essential for quickly finding nodes by key and frequency lists by frequency.
*   **Doubly Linked Lists for Efficient Removal/Insertion:** Allows O(1) removal and insertion of nodes within a frequency list, crucial for moving nodes as their frequency changes.
*   **Maintaining Minimum Frequency:** The `minFreq` variable is key to quickly identifying which frequency list to evict from.
*   **Frequency Grouping:** Organizing nodes by frequency allows direct access to the LFU items.

## Common Mistakes
*   **Incorrectly updating `minFreq`:** Forgetting to update `minFreq` when the current `minFreq` list becomes empty after an eviction or node movement.
*   **Handling edge cases in DLL operations:** Not properly managing `head` and `tail` pointers when removing or adding nodes, especially when the list becomes empty or has only one element.
*   **Not detaching nodes properly:** Failing to set `node.prev` and `node.next` to `null` after removal, which can lead to memory leaks or incorrect list behavior.
*   **Inefficient eviction:** If the eviction logic doesn't correctly identify the LFU *and* LRU item within the LFU group, the cache might not behave as expected.
*   **Forgetting to remove empty frequency lists:** If a frequency list becomes empty, it should be removed from the `freqs` map to avoid unnecessary lookups and potential errors.

## Complexity Analysis
*   **Time:**
    *   `get(key)`: O(1) - Hash map lookups and DLL operations (add/remove) are O(1).
    *   `put(key, value)`: O(1) - Hash map lookups, DLL operations, and `minFreq` updates are all O(1).
*   **Space:** O(N) - Where N is the capacity of the cache. We store up to N nodes in the `map`, and potentially N nodes distributed across various DLLs in `freqs`.

## Commented Code
```java
class LFUCache {
    // The maximum number of key-value pairs the cache can hold.
    int capacity;
    // Tracks the minimum frequency of any item currently in the cache.
    int minFreq;
    
    // Inner class representing a node in the doubly linked list.
    class Node{
        int key, value, count; // key, value, and frequency (count) of the node.
        Node next, prev; // Pointers for the doubly linked list.
        
        // Constructor for a new Node. Initializes with frequency 1.
        Node(int key, int value){
            this.key = key;
            this.value = value;
            this.count = 1; // New nodes start with a frequency of 1.
        }
    }
    
    // Inner class representing a Doubly Linked List (DLL) to group nodes of the same frequency.
    class DLL{
        Node head,tail; // Pointers to the head and tail of the DLL.
        
        // Constructor for a new DLL. Initializes with a single node.
        DLL(Node node){
            this.head = node;
            this.tail = node;
        }
    }
    
    // Map to store key -> Node. Allows O(1) access to any node by its key.
    Map<Integer,Node> map = new HashMap<>(); 
    // Map to store frequency -> DLL. Groups nodes by their frequency.
    // Each DLL contains nodes with the same frequency, ordered by recency (LRU at head, MRU at tail).
    Map<Integer,DLL> freqs = new HashMap<>(); 
    
    
    // Constructor for the LFUCache.
    public LFUCache(int capacity) {
        this.capacity = capacity; // Set the cache capacity.
        this.minFreq = 0; // Initialize minFreq. It will be updated upon first insertion.
    }
  
    // Retrieves the value associated with a key.
    public int get(int key) {
        // If the key is not in the cache, return -1.
        if(!map.containsKey(key)) return -1;
        
        // Get the node associated with the key.
        Node node = map.get(key);
        // Increment the frequency of the node and update its position in frequency lists.
        increment(node);
        // Return the value of the node.
        return node.value;
    }
    
    // Inserts or updates a key-value pair in the cache.
    public void put(int key, int value) {        
        // If the key already exists in the cache.
        if(map.containsKey(key)){
            // Get the existing node.
            Node oldNode = map.get(key);
            // Update its value.
            oldNode.value = value;
            // Increment its frequency and reposition it.
            increment(oldNode);
        } else { // If the key does not exist.
            // Check if the cache is full.
            if(map.size()==capacity){
                // Get the DLL for the minimum frequency.
                DLL minDLL = freqs.get(minFreq);
                // The node to remove is the head of the minFreq DLL (LFU and LRU).
                Node toRemove = minDLL.head;
                // Remove the node from the main map.
                map.remove(toRemove.key);
                // Remove the node from its current DLL.
                removeNodeFrom(minDLL,toRemove);
                // If the minFreq DLL becomes empty after removal, remove it from the freqs map.
                if(minDLL.head==null)freqs.remove(minFreq);
            }
            // A new node always starts with frequency 1.
            minFreq=1;
            // Create a new node.
            Node node = new Node(key,value);
            // Add the new node to the main map.
            map.put(key,node);
            // If there's no DLL for frequency 1 yet, create one.
            if(!freqs.containsKey(1)) freqs.put(1,new DLL(node));
            // Otherwise, add the new node to the end of the existing DLL for frequency 1.
            else putAtEnd(freqs.get(1),node);            
        } 
    }
    
    // Increments the frequency of a node and moves it to the appropriate frequency list.
    public void increment(Node node){
        // Store the old frequency.
        int oldFreq = node.count;
        // Get the DLL associated with the old frequency.
        DLL oldDLL = freqs.get(oldFreq);
        // Remove the node from its current DLL.
        removeNodeFrom(oldDLL, node);
        
        // If the old frequency DLL becomes empty after removal.
        if(oldDLL.head==null) {
            // Remove the empty DLL from the freqs map.
            freqs.remove(oldFreq); 
            // If the removed frequency was the minimum frequency, update minFreq.
            if(oldFreq == minFreq) minFreq++;
        }
        
        // Increment the node's frequency.
        node.count++;
        // Get the new frequency.
        int newFreq = node.count;
        
        // If there's no DLL for the new frequency yet, create one.
        if(!freqs.containsKey(newFreq)) freqs.put(newFreq,new DLL(node));
        // Otherwise, add the node to the end of the existing DLL for the new frequency.
        else putAtEnd(freqs.get(newFreq),node);
    }
    
    // Adds a node to the end of a given DLL.
    public void putAtEnd(DLL dll , Node node){
        node.next = null; // Ensure the new node is the last one.
        dll.tail.next = node; // Link the current tail's next to the new node.
        node.prev = dll.tail; // Link the new node's prev to the current tail.
        dll.tail = node; // Update the DLL's tail to be the new node.
    }
    
    // Removes a node from a given DLL.
    public void removeNodeFrom(DLL dll, Node node){
        // If the node to remove is the head of the DLL.
        if(node.prev==null) dll.head = node.next;
        // Otherwise, link the previous node's next to the node's next.
        else node.prev.next = node.next;
        
        // If the node to remove is the tail of the DLL.
        if(node.next==null) dll.tail = node.prev;
        // Otherwise, link the next node's prev to the node's prev.
        else node.next.prev = node.prev;
        
        // Detach the node by setting its prev and next pointers to null.
        node.prev = null;
        node.next=null;
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
1.  **Explain the Data Structures First:** Before diving into the algorithm, clearly explain the purpose of `map`, `freqs`, `Node`, and `DLL`. Emphasize how they work together to achieve O(1) operations.
2.  **Walk Through `increment`:** This is the most complex part. Clearly explain how a node moves between frequency lists and how `minFreq` is updated. Use a small example if necessary.
3.  **Handle Edge Cases Explicitly:** When discussing `put` and `increment`, specifically mention what happens when a frequency list becomes empty, or when the cache is full and an eviction occurs.
4.  **Focus on Time Complexity:** Be ready to justify why each operation is O(1) by referring to the properties of hash maps and doubly linked lists.

## Revision Checklist
- [ ] Understand the LFU eviction policy.
- [ ] Design the `Node` and `DLL` structures.
- [ ] Implement `map` for key-to-node lookup.
- [ ] Implement `freqs` for frequency-to-DLL mapping.
- [ ] Correctly manage `minFreq`.
- [ ] Implement `get` with frequency increment.
- [ ] Implement `put` handling both existing and new keys.
- [ ] Implement eviction logic when capacity is reached.
- [ ] Implement `increment` to move nodes between frequency lists.
- [ ] Ensure `DLL` operations (`putAtEnd`, `removeNodeFrom`) are correct for all edge cases (head, tail, single node, empty list).
- [ ] Analyze time and space complexity.

## Similar Problems
*   LRU Cache
*   Design Hit Counter
*   All O(1) Operations on a Dictionary

## Tags
`Hash Map` `Doubly Linked List` `Design`
