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
This problem asks to implement a Least Frequently Used (LFU) cache with a given capacity.
We solve it by using a hash map to store nodes and another hash map to group nodes by their frequency, utilizing doubly linked lists for efficient removal and insertion within frequency groups.

## Intuition
The core challenge is to efficiently track both the frequency of access for each key and to quickly identify and evict the least frequently used item when the cache is full. A simple frequency count isn't enough; we need to know *which* items have that frequency. When multiple items share the same lowest frequency, we must evict the one that was least recently used among them.

This suggests a structure where we can:
1.  **Quickly find a node by its key:** A hash map (`map`) is ideal for this.
2.  **Group nodes by their frequency:** Another hash map (`freqs`) where the key is the frequency and the value is a collection of nodes with that frequency.
3.  **Efficiently add/remove nodes from a frequency group:** Since we need to evict the *least recently used* among the least frequently used, the collection for each frequency should support O(1) addition and removal from both ends. A Doubly Linked List (DLL) is perfect for this.
4.  **Track the minimum frequency:** We need a variable (`minFreq`) to quickly know which frequency group to evict from.

The "aha moment" comes from realizing that each frequency can be represented by its own DLL. When a node's frequency increases, it moves from its current DLL to the DLL of the next frequency. If a DLL becomes empty, and it was the `minFreq` DLL, we update `minFreq`.

## Algorithm
1.  **Initialization (`LFUCache(int capacity)`):**
    *   Store the `capacity`.
    *   Initialize `minFreq` to 0 (or 1, depending on how you handle initial insertions).
    *   Initialize `map` (key to Node) and `freqs` (frequency to DLL) hash maps.

2.  **`get(int key)`:**
    *   Check if the `key` exists in `map`. If not, return -1.
    *   Retrieve the `Node` from `map`.
    *   Call `increment(node)` to update its frequency and move it to the appropriate frequency list.
    *   Return the `node.value`.

3.  **`put(int key, int value)`:**
    *   **If `key` exists:**
        *   Get the existing `Node` from `map`.
        *   Update its `value`.
        *   Call `increment(node)`.
    *   **If `key` does not exist:**
        *   **Check capacity:** If `map.size() == capacity`:
            *   Get the DLL for `minFreq` from `freqs`.
            *   Get the head node (`toRemove`) from this `minFreq` DLL.
            *   Remove `toRemove` from `map`.
            *   Remove `toRemove` from its DLL using `removeNodeFrom`.
            *   If the `minFreq` DLL becomes empty after removal, remove it from `freqs`.
        *   **Create new node:**
            *   Set `minFreq` to 1 (as a new node always starts with frequency 1).
            *   Create a new `Node` with the given `key` and `value`.
            *   Add the new `Node` to `map`.
            *   **Add to frequency 1 DLL:**
                *   If `freqs` does not contain key 1, create a new DLL for frequency 1 and add the node.
                *   Otherwise, add the node to the existing DLL for frequency 1 using `putAtEnd`.

4.  **`increment(Node node)`:**
    *   Get the `oldFreq` of the `node`.
    *   Get the `oldDLL` corresponding to `oldFreq` from `freqs`.
    *   Remove the `node` from `oldDLL` using `removeNodeFrom`.
    *   **Update `minFreq`:** If `oldDLL` becomes empty and `oldFreq` was equal to `minFreq`, increment `minFreq`.
    *   If `oldDLL` is now empty, remove it from `freqs`.
    *   Increment `node.count`.
    *   **Add to new frequency DLL:**
        *   If `freqs` does not contain `node.count` (the new frequency), create a new DLL for this frequency and add the node.
        *   Otherwise, add the node to the existing DLL for `node.count` using `putAtEnd`.

5.  **`putAtEnd(DLL dll, Node node)`:**
    *   Helper to add a `node` to the tail of a `dll`.
    *   Set `node.next = null`.
    *   Link `dll.tail.next` to `node`.
    *   Link `node.prev` to `dll.tail`.
    *   Update `dll.tail` to `node`.

6.  **`removeNodeFrom(DLL dll, Node node)`:**
    *   Helper to remove a `node` from a `dll`.
    *   Handle cases where the node is the head, tail, or in the middle.
    *   Update `dll.head` and `dll.tail` if necessary.
    *   Set `node.prev` and `node.next` to null to detach it.

## Concept to Remember
*   **Hash Maps:** For O(1) average time complexity lookups, insertions, and deletions by key. Used for mapping keys to nodes and frequencies to DLLs.
*   **Doubly Linked Lists (DLLs):** For O(1) time complexity insertion and deletion of nodes at arbitrary positions (specifically, at the head/tail of frequency lists). This is crucial for maintaining LRU order within each frequency.
*   **Frequency Tracking:** Maintaining counts for each item and grouping items by their frequency.
*   **LRU Eviction within LFU:** When multiple items have the same minimum frequency, the least recently used among them must be evicted. The DLL structure naturally handles this.

## Common Mistakes
*   **Incorrectly updating `minFreq`:** Forgetting to update `minFreq` when the current `minFreq` DLL becomes empty, or incorrectly incrementing it.
*   **Not handling edge cases in DLL operations:** Failing to correctly update `head` and `tail` pointers in `removeNodeFrom` and `putAtEnd` when dealing with single-node DLLs or removing the head/tail.
*   **Inefficient eviction:** If the DLL operations are not O(1), the overall complexity will suffer. For example, using an `ArrayList` instead of a DLL for frequency groups.
*   **Forgetting to remove nodes from `map` during eviction:** When evicting a node, it must be removed from the `map` to prevent stale entries.
*   **Not properly detaching nodes:** After removing a node from a DLL, its `prev` and `next` pointers should be nullified to avoid dangling references.

## Complexity Analysis
*   **Time:**
    *   `get(key)`: O(1) on average. Accessing `map` is O(1). `increment` involves DLL operations (remove, add) which are O(1), and hash map operations which are O(1) on average.
    *   `put(key, value)`: O(1) on average. If capacity is reached, eviction involves O(1) DLL operations and O(1) hash map operations. If the key exists, it's similar to `get` followed by an update. New node insertion involves O(1) DLL and hash map operations.
    *   **Reason:** All operations rely on hash map lookups/insertions/deletions and DLL manipulations, which are all O(1) on average.

*   **Space:** O(N), where N is the capacity of the cache.
    *   **Reason:** The `map` stores up to N nodes. The `freqs` map stores DLLs, and in the worst case, each node might be in a different frequency DLL, or all nodes could be in a few DLLs. The total number of nodes stored across all DLLs is N.

## Commented Code
```java
class LFUCache {
    // The maximum number of key-value pairs the cache can hold.
    int capacity;
    // Tracks the minimum frequency among all nodes currently in the cache.
    // This is used to identify which frequency list to evict from.
    int minFreq;
    
    // Inner class representing a node in the cache.
    // Each node stores its key, value, and its current access frequency (count).
    // It also has pointers for a doubly linked list.
    class Node{
        int key, value, count; // key, value, and frequency count
        Node next, prev; // pointers for doubly linked list
        
        // Constructor for a new node. Initializes count to 1.
        Node(int key, int value){
            this.key = key;
            this.value = value;
            this.count = 1; // New nodes start with frequency 1
        }
    }
    
    // Inner class representing a Doubly Linked List (DLL).
    // Each DLL will store nodes of the same frequency.
    // It has pointers to the head and tail of the list.
    class DLL{
        Node head,tail; // head and tail of the doubly linked list
        
        // Constructor for a new DLL. Initializes head and tail to the given node.
        DLL(Node node){
            this.head = node;
            this.tail = node;
        }
    }
    
    // Map to store all nodes, keyed by their integer key.
    // Allows O(1) average time access to any node given its key.
    Map<Integer,Node> map = new HashMap<>(); // node of a given key
    
    // Map to store DLLs, keyed by their frequency count.
    // Allows O(1) average time access to the DLL for a given frequency.
    // Each DLL contains nodes that have that specific frequency.
    Map<Integer,DLL> freqs = new HashMap<>(); // list of nodes of a given freqt
    
    
    // Constructor for the LFUCache.
    public LFUCache(int capacity) {
        this.capacity = capacity; // Set the cache capacity
        this.minFreq = 0; // Initialize minFreq. It will be updated to 1 on first put.
    }
  
    // Retrieves the value associated with a given key.
    public int get(int key) {
        // If the key is not in the cache, return -1.
        if(!map.containsKey(key)) return -1;
        
        // Get the node associated with the key.
        Node node = map.get(key);
        // Increment the frequency of this node and move it to the appropriate frequency list.
        increment(node);
        // Return the value of the node.
        return node.value;
    }
    
    // Inserts or updates a key-value pair in the cache.
    public void put(int key, int value) {        
        // If the key already exists in the cache:
        if(map.containsKey(key)){
            // Get the existing node.
            Node oldNode = map.get(key);
            // Update its value.
            oldNode.value = value;
            // Increment its frequency and re-organize its position.
            increment(oldNode);
        } else { // If the key does not exist:
            // Check if the cache is full.
            if(map.size()==capacity){
                // Get the DLL for the minimum frequency.
                DLL minDLL = freqs.get(minFreq);
                // Get the head node of this DLL (this is the LFU and LRU node to be evicted).
                Node toRemove = minDLL.head;
                // Remove the node from the main map.
                map.remove(toRemove.key);
                // Remove the node from its current DLL.
                removeNodeFrom(minDLL,toRemove);
                // If the DLL for minFreq becomes empty after removal, remove it from the freqs map.
                if(minDLL.head==null)freqs.remove(minFreq);
            }
            // A new node is always inserted with frequency 1.
            minFreq=1;
            // Create a new node with the given key and value.
            Node node = new Node(key,value);
            // Add the new node to the main map.
            map.put(key,node);
            // Add the new node to the DLL for frequency 1.
            // If no DLL exists for frequency 1, create a new one.
            if(!freqs.containsKey(1)) freqs.put(1,new DLL(node));
            // Otherwise, add the node to the end of the existing DLL for frequency 1.
            else putAtEnd(freqs.get(1),node);            
        } 
    }
    
    // Increments the frequency of a given node and moves it to the appropriate frequency list.
    public void increment(Node node){
        int oldFreq = node.count; // Store the current frequency.
        DLL oldDLL = freqs.get(oldFreq); // Get the DLL associated with the old frequency.
        
        // Remove the node from its current DLL.
        removeNodeFrom(oldDLL, node);
        
        // If the old DLL becomes empty after removing the node:
        if(oldDLL.head==null) {
            // Remove the empty DLL from the freqs map.
            freqs.remove(oldFreq); 
            // If the removed node was the only one with the minimum frequency,
            // then the new minimum frequency must be the next higher frequency.
            if(oldFreq == minFreq) minFreq++;
        }
        
        // Increment the node's frequency count.
        node.count++;
        
        // Add the node to the DLL for its new frequency.
        // If no DLL exists for the new frequency, create a new one.
        if(!freqs.containsKey(node.count)) freqs.put(node.count,new DLL(node));
        // Otherwise, add the node to the end of the existing DLL for the new frequency.
        else putAtEnd(freqs.get(node.count),node);
    }
    
    // Helper method to add a node to the end (tail) of a DLL.
    public void putAtEnd(DLL dll , Node node){
        node.next = null; // The new node will be the last, so its next is null.
        dll.tail.next = node; // Link the current tail's next pointer to the new node.
        node.prev = dll.tail; // Link the new node's prev pointer to the current tail.
        dll.tail = node; // Update the DLL's tail to be the new node.
    }
    
    // Helper method to remove a node from a DLL.
    public void removeNodeFrom(DLL dll, Node node){
        // If the node to be removed is the head of the DLL.
        if(node.prev==null) dll.head = node.next; // Update head to the next node.
        else node.prev.next = node.next; // Otherwise, link the previous node's next to the node after the current one.
        
        // If the node to be removed is the tail of the DLL.
        if(node.next==null) dll.tail = node.prev; // Update tail to the previous node.
        else node.next.prev = node.prev; // Otherwise, link the next node's prev to the node before the current one.
        
        // Detach the node by nullifying its pointers.
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
1.  **Explain the Data Structures:** Clearly articulate why a hash map for key-to-node mapping and another hash map for frequency-to-DLL mapping are chosen. Emphasize the role of DLLs in maintaining LRU order within frequency groups.
2.  **Walk Through `increment`:** This is the most complex part. Explain step-by-step how a node moves from one frequency list to another, and how `minFreq` is updated. Use a small example if needed.
3.  **Handle Edge Cases:** Be prepared to discuss edge cases like an empty cache, a full cache, removing the head/tail of a DLL, and a DLL becoming empty.
4.  **Complexity Justification:** Be ready to explain the O(1) time complexity for `get` and `put` by referencing the O(1) operations of hash maps and DLLs.

## Revision Checklist
- [ ] Understand the LFU eviction policy (Least Frequently Used, then Least Recently Used).
- [ ] Design data structures: `map` (key -> Node), `freqs` (frequency -> DLL).
- [ ] Implement `Node` and `DLL` classes with necessary pointers.
- [ ] Implement `get` operation: retrieve, increment frequency, update position.
- [ ] Implement `put` operation: handle existing keys (update value, increment frequency) and new keys (evict if full, create new node, add to frequency 1).
- [ ] Implement `increment` logic: remove from old DLL, add to new DLL, update `minFreq` if necessary.
- [ ] Implement `removeNodeFrom` for DLLs, handling head/tail/middle cases.
- [ ] Implement `putAtEnd` for DLLs.
- [ ] Consider edge cases: capacity 0, empty cache, full cache, single-node DLLs.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LRU Cache
*   Design Hit Counter
*   All O(1) Data Structure problems

## Tags
`Hash Map` `Doubly Linked List` `Design`
