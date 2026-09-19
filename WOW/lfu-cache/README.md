# Lfu Cache

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:** See complexity section  
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
Implements a Least Frequently Used (LFU) cache that evicts the least frequently used item when capacity is reached.
Uses a combination of hash maps and doubly linked lists to efficiently track frequencies and node positions.

## Intuition
The core challenge is to efficiently find and evict the LFU item. If multiple items have the same minimum frequency, we need to evict the Least Recently Used (LRU) among them. This suggests we need to track not only the frequency of each item but also its recency within that frequency group.

A hash map (`map`) can store `key -> Node` for O(1) access to any node. To manage frequencies, we can use another hash map (`freqs`) where `frequency -> DoublyLinkedList`. Each doubly linked list will store nodes with the same frequency, ordered by recency (most recently used at the tail). This allows us to quickly find the LFU list (by tracking `minFreq`) and the LRU node within that list.

When an item is accessed (`get` or `put` for existing key), its frequency increases. We need to move it from its current frequency list to the next higher frequency list. If its old frequency list becomes empty and it was the `minFreq`, we update `minFreq`. When the cache is full and we need to `put` a new item, we evict the head of the `minFreq` list.

## Algorithm
1.  **Initialization**:
    *   Store `capacity`.
    *   Initialize `minFreq` to 0.
    *   Create a `map` to store `key -> Node`.
    *   Create a `freqs` map to store `frequency -> DoublyLinkedList`.

2.  **Node Structure**:
    *   Each `Node` should store `key`, `value`, `count` (frequency), `next`, and `prev` pointers.

3.  **Doubly Linked List (DLL) Structure**:
    *   Each `DLL` should have `head` and `tail` pointers. It will hold nodes of the same frequency.

4.  **`get(key)` Operation**:
    *   If `key` is not in `map`, return -1.
    *   Retrieve the `Node` from `map`.
    *   Call `increment(node)` to update its frequency and move it to the appropriate DLL.
    *   Return `node.value`.

5.  **`put(key, value)` Operation**:
    *   **If `key` exists**:
        *   Get the `Node` from `map`.
        *   Update its `value`.
        *   Call `increment(node)`.
    *   **If `key` does not exist**:
        *   **Check capacity**: If `map.size() == capacity`:
            *   Get the `DLL` for `minFreq` from `freqs`.
            *   Get the `head` node from this `DLL` (this is the LFU and LRU node).
            *   Remove this node from `map`.
            *   Remove this node from its `DLL`.
            *   If the `DLL` becomes empty, remove it from `freqs`.
        *   **Create new node**:
            *   Set `minFreq = 1` (since a new node always starts with frequency 1).
            *   Create a new `Node` with the given `key` and `value`.
            *   Add the new `Node` to `map`.
            *   If `freqs` does not contain `1`, create a new `DLL` for frequency 1 and add the node.
            *   Otherwise, add the node to the end of the existing `DLL` for frequency 1 using `putAtEnd`.

6.  **`increment(node)` Helper Function**:
    *   Get the `oldFreq` of the `node`.
    *   Get the `DLL` corresponding to `oldFreq` from `freqs`.
    *   Remove the `node` from its `oldDLL` using `removeNodeFrom`.
    *   **Update `minFreq`**: If `oldDLL` is now empty AND `oldFreq == minFreq`, increment `minFreq`.
    *   If `oldDLL` is empty, remove `oldFreq` from `freqs`.
    *   Increment `node.count`.
    *   **Add to new frequency list**:
        *   If `freqs` does not contain `node.count` (the new frequency), create a new `DLL` for this frequency and add the `node`.
        *   Otherwise, add the `node` to the end of the existing `DLL` for `node.count` using `putAtEnd`.

7.  **`putAtEnd(dll, node)` Helper Function**:
    *   Set `node.next = null`.
    *   Link `dll.tail.next` to `node`.
    *   Link `node.prev` to `dll.tail`.
    *   Update `dll.tail` to `node`.

8.  **`removeNodeFrom(dll, node)` Helper Function**:
    *   Handle `node` being the `head`: If `node.prev == null`, update `dll.head = node.next`.
    *   Otherwise, link `node.prev.next` to `node.next`.
    *   Handle `node` being the `tail`: If `node.next == null`, update `dll.tail = node.prev`.
    *   Otherwise, link `node.next.prev` to `node.prev`.
    *   Set `node.prev = null` and `node.next = null` to detach the node.

## Concept to Remember
*   **Hash Maps**: For O(1) average time complexity for key lookups, insertions, and deletions.
*   **Doubly Linked Lists**: To maintain order (recency) within frequency groups and allow O(1) removal of any node given its reference.
*   **Frequency Tracking**: Maintaining counts for each element and grouping elements by their frequency.
*   **LRU within LFU**: When multiple elements share the minimum frequency, the LRU element is evicted.

## Common Mistakes
*   **Incorrectly updating `minFreq`**: Forgetting to update `minFreq` when the current `minFreq` list becomes empty after removing a node.
*   **Handling edge cases in DLL operations**: Not properly managing `head` and `tail` pointers when removing or adding nodes, especially when the list has only one element or the node being removed is the head/tail.
*   **Inefficient node removal from DLL**: If node removal is not O(1) (e.g., searching for the node first), the overall complexity will suffer.
*   **Not handling capacity correctly**: Failing to evict an item when the cache is full before adding a new one.
*   **Forgetting to remove from `map`**: When evicting a node, it must be removed from both the DLL and the `map`.

## Complexity Analysis
*   **Time**:
    *   `get(key)`: O(1) - Hash map lookups, DLL operations (remove, add) are O(1) given node reference.
    *   `put(key, value)`: O(1) - Hash map operations and DLL operations are O(1). Eviction involves O(1) DLL operations.
    *   `increment(node)`: O(1) - Hash map operations and DLL operations are O(1).
    *   **Reason**: All operations rely on hash map lookups and direct manipulation of doubly linked list nodes (which are O(1) when the node reference is known).

*   **Space**: O(N) - where N is the capacity of the cache.
    *   **Reason**: The `map` stores up to N nodes. The `freqs` map stores DLLs, and in the worst case, each node could be in its own DLL (e.g., all nodes have unique frequencies), or all N nodes could be in a single DLL. The total number of nodes stored across all DLLs is N.

## Commented Code
```java
class LFUCache {
    // The maximum number of key-value pairs the cache can hold.
    int capacity;
    // Tracks the minimum frequency among all nodes currently in the cache.
    int minFreq;
    
    // Inner class representing a node in the cache.
    class Node{
        // The key of the node.
        int key;
        // The value associated with the key.
        int value;
        // The frequency of access for this node (how many times it's been accessed).
        int count;
        // Pointer to the next node in its doubly linked list.
        Node next;
        // Pointer to the previous node in its doubly linked list.
        Node prev;
        
        // Constructor for a new Node. Initializes with key, value, and frequency of 1.
        Node(int key, int value){
            this.key = key;
            this.value = value;
            this.count = 1; // New nodes start with a frequency of 1.
        }
    }
    
    // Inner class representing a Doubly Linked List (DLL).
    // Each DLL will store nodes with the same frequency, ordered by recency.
    class DLL{
        // Pointer to the head of the DLL.
        Node head;
        // Pointer to the tail of the DLL.
        Node tail;
        
        // Constructor for a new DLL. Initializes with a single node.
        DLL(Node node){
            this.head = node;
            this.tail = node;
        }
    }
    
    // Map to store key -> Node. Allows O(1) access to any node by its key.
    Map<Integer,Node> map = new HashMap<>(); 
    // Map to store frequency -> DLL. Allows O(1) access to the list of nodes for a given frequency.
    Map<Integer,DLL> freqs = new HashMap<>(); 
    
    
    // Constructor for the LFUCache.
    public LFUCache(int capacity) {
        this.capacity = capacity; // Set the cache capacity.
        this.minFreq = 0; // Initialize minFreq to 0. It will be updated to 1 when the first element is added.
    }
  
    // Retrieves the value associated with a key.
    public int get(int key) {
        // If the key is not present in the cache, return -1.
        if(!map.containsKey(key)) return -1;
        
        // Get the node associated with the key.
        Node node = map.get(key);
        // Increment the frequency of the node and move it to the appropriate DLL.
        increment(node);
        // Return the value of the node.
        return node.value;
    }
    
    // Adds or updates a key-value pair in the cache.
    public void put(int key, int value) {        
        // If the key already exists in the cache.
        if(map.containsKey(key)){
            // Get the existing node.
            Node oldNode = map.get(key);
            // Update its value.
            oldNode.value = value;
            // Increment its frequency and re-position it in the DLL structure.
            increment(oldNode);
        } else { // If the key is new.
            // Check if the cache is at full capacity.
            if(map.size()==capacity){
                // Get the DLL corresponding to the minimum frequency.
                DLL minDLL = freqs.get(minFreq);
                // The node to remove is the head of this DLL (LFU and LRU).
                Node toRemove = minDLL.head;
                // Remove the node from the main map.
                map.remove(toRemove.key);
                // Remove the node from its current DLL.
                removeNodeFrom(minDLL,toRemove);
                // If the DLL for minFreq becomes empty after removal, remove it from the freqs map.
                if(minDLL.head==null)freqs.remove(minFreq);
            }
            // A new node always starts with frequency 1.
            minFreq=1;
            // Create a new node with the given key and value.
            Node node = new Node(key,value);
            // Add the new node to the main map.
            map.put(key,node);
            // If there's no DLL for frequency 1 yet, create one.
            if(!freqs.containsKey(1)) freqs.put(1,new DLL(node));
            // Otherwise, add the new node to the end of the existing DLL for frequency 1.
            else putAtEnd(freqs.get(1),node);            
        } 
    }
    
    // Helper function to increment the frequency of a node.
    public void increment(Node node){
        // Store the old frequency of the node.
        int oldFreq = node.count;
        // Get the DLL associated with the old frequency.
        DLL oldDLL = freqs.get(oldFreq);
        // Remove the node from its current DLL.
        removeNodeFrom(oldDLL, node);
        
        // If the old DLL becomes empty after removing the node, and its frequency was the minimum frequency,
        // then we need to update minFreq to the next higher frequency.
        if(oldDLL.head==null) {
            freqs.remove(oldFreq); // Remove the empty DLL from the freqs map.
            if(oldFreq == minFreq) minFreq++; // If the removed node was the only one at minFreq, increment minFreq.
        }
        
        // Increment the node's frequency count.
        node.count++;
        // Check if a DLL for the new frequency already exists.
        if(!freqs.containsKey(node.count)) {
            // If not, create a new DLL for this new frequency and add the node to it.
            freqs.put(node.count,new DLL(node));
        } else {
            // If a DLL for the new frequency exists, add the node to the end of it.
            putAtEnd(freqs.get(node.count),node);
        }
    }
    
    // Helper function to add a node to the end of a DLL.
    public void putAtEnd(DLL dll , Node node){
        node.next = null; // The new node will be the tail, so its next pointer is null.
        dll.tail.next = node; // Link the current tail's next pointer to the new node.
        node.prev = dll.tail; // Link the new node's prev pointer to the current tail.
        dll.tail = node; // Update the DLL's tail to be the new node.
    }
    
    // Helper function to remove a node from a DLL.
    public void removeNodeFrom(DLL dll, Node node){
        // If the node to be removed is the head of the DLL.
        if(node.prev==null) dll.head = node.next; // Update the head to the next node.
        else node.prev.next = node.next; // Otherwise, link the previous node's next to the current node's next.
        
        // If the node to be removed is the tail of the DLL.
        if(node.next==null) dll.tail = node.prev; // Update the tail to the previous node.
        else node.next.prev = node.prev; // Otherwise, link the next node's prev to the current node's prev.
        
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
1.  **Explain the Data Structures**: Clearly articulate why you chose a combination of `HashMap` and `Doubly Linked Lists`. Emphasize how they work together to achieve O(1) complexity for both `get` and `put`.
2.  **Trace `increment` and `put` with an Example**: Walk through a small example, showing how `minFreq` changes, how nodes move between DLLs, and how eviction works. This is crucial for demonstrating understanding.
3.  **Discuss Edge Cases**: Be prepared to discuss what happens when the cache is empty, full, when a node is the only one at `minFreq`, or when a DLL becomes empty.
4.  **Complexity Justification**: Be ready to explain why each operation is O(1) time and O(N) space, referencing the data structures used.

## Revision Checklist
- [ ] Understand the LFU eviction policy.
- [ ] Implement `Node` and `DLL` structures correctly.
- [ ] Use `HashMap` for O(1) key-to-node lookup.
- [ ] Use `HashMap<Integer, DLL>` to group nodes by frequency.
- [ ] Track `minFreq` accurately.
- [ ] Implement `get` operation with frequency increment.
- [ ] Implement `put` operation, handling existing keys and new keys.
- [ ] Implement eviction logic when capacity is reached.
- [ ] Implement `increment` helper function to move nodes between DLLs.
- [ ] Implement `putAtEnd` and `removeNodeFrom` for DLL manipulation.
- [ ] Handle edge cases: empty cache, full cache, empty DLLs.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LRU Cache
*   Design Hit Counter
*   All O(1) Data Structures problems

## Tags
`Hash Map` `Doubly Linked List` `Design`
