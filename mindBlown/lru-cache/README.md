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
    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        
        // Constructor
        public Node(int x, int y) {
            this.key = x;
            this.value = y;
            this.next = null; // next pointer initially null
            this.prev = null; // previous pointer initially null
        }
    }

    HashMap <Integer,Node> map ;
    int capacity ; 
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;        
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node needed = map.get(key);
            if(needed.next == null) return needed.value;
            removeNode(needed);
            addAtTail(needed);
            return needed.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node toUpdate = map.get(key);
            toUpdate.value = value;
            removeNode(toUpdate);
            addAtTail(toUpdate);
        }
        else {
           if(capacity<=map.size()){
            map.remove(head.key);
            removeNode(head);
           }
           Node toAdd = new Node(key,value);
           map.put(key,toAdd);
           addAtTail(toAdd);
        }
       
    }
    
    public void addAtTail(Node n){
        n.next=null;
        // We get a lawaris node, we mark its prev as current tail, attach tail's next to the lawaris node
        // and now our tail moves ahead and that lawaris node is our tail
        if (head == null) {
            head = n;
            tail = n;
        } else{
            n.prev = tail;
            tail.next = n;
            tail = tail.next;
        }
    }
    public void removeNode(Node needed) { 
        if(needed.prev == null) head = needed.next;
        else needed.prev.next = needed.next;
        if(needed.next == null) tail = needed.prev;
        else needed.next.prev = needed.prev;
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
Implement a cache that evicts the least recently used item when it reaches capacity.
Use a hash map for O(1) lookups and a doubly linked list to maintain the order of usage.

## Intuition
The core idea is to keep track of which items were used most recently and which were used least recently. A hash map allows us to quickly find any item by its key. However, a hash map alone doesn't tell us the order of usage. To maintain this order efficiently, a doubly linked list is perfect. The head of the list will represent the least recently used item, and the tail will represent the most recently used. When an item is accessed (`get`) or added/updated (`put`), we move it to the tail of the list. If the cache is full and we need to add a new item, we remove the item at the head of the list.

## Algorithm
1.  **Data Structures**:
    *   A `HashMap` to store `key -> Node` mappings for O(1) access.
    *   A doubly linked list to maintain the order of usage. Each node in the list will store the key and value.
    *   `head` and `tail` pointers for the doubly linked list. `head` points to the least recently used, `tail` to the most recently used.

2.  **`LRUCache(int capacity)` Constructor**:
    *   Initialize the `capacity`.
    *   Initialize the `HashMap`.
    *   Initialize `head` and `tail` to `null`.

3.  **`get(int key)` Method**:
    *   Check if the `key` exists in the `HashMap`.
    *   If not, return `-1`.
    *   If it exists:
        *   Retrieve the `Node` from the `HashMap`.
        *   **Crucially**: Move this `Node` to the tail of the doubly linked list (making it the most recently used). This involves removing it from its current position and adding it to the tail.
        *   Return the `value` of the `Node`.

4.  **`put(int key, int value)` Method**:
    *   Check if the `key` already exists in the `HashMap`.
    *   If it exists:
        *   Retrieve the `Node`.
        *   Update its `value`.
        *   Move this `Node` to the tail of the doubly linked list.
    *   If it does not exist:
        *   Check if the `HashMap` size is equal to `capacity`.
        *   If the cache is full:
            *   Remove the `head` node (least recently used) from the `HashMap`.
            *   Remove the `head` node from the doubly linked list.
        *   Create a new `Node` with the given `key` and `value`.
        *   Add the new `Node` to the `HashMap`.
        *   Add the new `Node` to the tail of the doubly linked list.

5.  **Helper Methods**:
    *   `addAtTail(Node n)`: Appends a node `n` to the end of the doubly linked list. Handles the case where the list is empty.
    *   `removeNode(Node n)`: Removes a node `n` from its current position in the doubly linked list. Updates `head` and `tail` pointers if necessary.

## Concept to Remember
*   **Hash Map**: For O(1) average time complexity for key-value lookups, insertions, and deletions.
*   **Doubly Linked List**: To maintain the order of elements and allow O(1) insertion/deletion at arbitrary positions (once the node is found).
*   **Cache Eviction Policy**: Understanding LRU as a common strategy to manage limited cache space.
*   **Sentinel Nodes (Optional but good practice)**: Using dummy `head` and `tail` nodes can simplify edge case handling (e.g., empty list, removing head/tail). The provided solution doesn't use sentinel nodes, which makes `addAtTail` and `removeNode` slightly more complex.

## Common Mistakes
*   **Forgetting to update the doubly linked list**: When a key is accessed or updated, it must be moved to the tail of the list to signify it's the most recently used.
*   **Incorrectly handling `head` and `tail` pointers**: Especially when removing nodes or when the list is empty or has only one element.
*   **Not removing the LRU item from the `HashMap`**: When evicting an item due to capacity, it must be removed from both the linked list and the hash map.
*   **Inefficient linked list operations**: If linked list operations are not O(1) (e.g., searching for a node to remove), the overall performance degrades.
*   **Not handling edge cases**: Empty cache, cache with one element, accessing/putting the head/tail element.

## Complexity Analysis
*   **Time**:
    *   `get(key)`: O(1) - Hash map lookup is O(1) on average. Moving a node in a doubly linked list (remove + add) is O(1).
    *   `put(key, value)`: O(1) - Hash map operations are O(1) on average. Linked list operations are O(1).
*   **Space**: O(N) - Where N is the capacity of the cache. This is due to storing up to N key-value pairs in the `HashMap` and the corresponding nodes in the doubly linked list.

## Commented Code
```java
class LRUCache {
    // Inner class to represent a node in the doubly linked list
    class Node {
        int key; // The key of the cache entry
        int value; // The value of the cache entry
        Node next; // Pointer to the next node in the list
        Node prev; // Pointer to the previous node in the list
        
        // Constructor for a Node
        public Node(int x, int y) {
            this.key = x; // Initialize key
            this.value = y; // Initialize value
            this.next = null; // Initially, no next node
            this.prev = null; // Initially, no previous node
        }
    }

    HashMap <Integer,Node> map ; // HashMap to store key -> Node mappings for O(1) access
    int capacity ; // The maximum capacity of the cache
    Node head; // Pointer to the head of the doubly linked list (Least Recently Used)
    Node tail; // Pointer to the tail of the doubly linked list (Most Recently Used)

    // Constructor for LRUCache
    public LRUCache(int capacity) {
        this.capacity = capacity; // Set the cache capacity
        this.map = new HashMap<>(); // Initialize the hash map
        this.head = null; // Initially, the list is empty
        this.tail = null; // Initially, the list is empty        
    }
    
    // Method to retrieve a value from the cache
    public int get(int key) {
        // Check if the key exists in the map
        if(map.containsKey(key)){
            Node needed = map.get(key); // Get the node associated with the key
            // If the node is already the tail (most recently used), no need to move it
            if(needed.next == null) return needed.value; 
            // Remove the node from its current position in the list
            removeNode(needed);
            // Add the node to the tail of the list (making it most recently used)
            addAtTail(needed);
            // Return the value of the node
            return needed.value;
        }
        // If the key is not found, return -1
        return -1;
    }

    // Method to add or update a key-value pair in the cache
    public void put(int key, int value) {
        // Check if the key already exists in the map
        if(map.containsKey(key)){
            Node toUpdate = map.get(key); // Get the existing node
            toUpdate.value = value; // Update its value
            // Remove the node from its current position
            removeNode(toUpdate);
            // Add the node to the tail of the list (making it most recently used)
            addAtTail(toUpdate);
        }
        else { // If the key does not exist
           // Check if the cache is at its capacity
           if(capacity <= map.size()){
            // If full, remove the least recently used item (head)
            map.remove(head.key); // Remove from hash map
            removeNode(head); // Remove from linked list
           }
           // Create a new node for the new key-value pair
           Node toAdd = new Node(key,value);
           // Add the new node to the hash map
           map.put(key,toAdd);
           // Add the new node to the tail of the list (making it most recently used)
           addAtTail(toAdd);
        }
    }
    
    // Helper method to add a node to the tail of the doubly linked list
    public void addAtTail(Node n){
        n.next=null; // Ensure the new node's next pointer is null
        // If the list is empty (head is null)
        if (head == null) {
            head = n; // The new node becomes both head and tail
            tail = n;
        } else{ // If the list is not empty
            n.prev = tail; // Set the new node's previous pointer to the current tail
            tail.next = n; // Link the current tail's next pointer to the new node
            tail = tail.next; // Move the tail pointer to the new node
        }
    }

    // Helper method to remove a node from the doubly linked list
    public void removeNode(Node needed) { 
        // If the node to be removed is the head
        if(needed.prev == null) head = needed.next; // Update head to the next node
        else needed.prev.next = needed.next; // Otherwise, link previous node's next to the node after 'needed'
        
        // If the node to be removed is the tail
        if(needed.next == null) tail = needed.prev; // Update tail to the previous node
        else needed.next.prev = needed.prev; // Otherwise, link next node's prev to the node before 'needed'
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
1.  **Explain the Trade-offs**: Clearly articulate why a hash map and a doubly linked list are used together. Mention the O(1) access of the hash map and the O(1) insertion/deletion of the linked list.
2.  **Walk Through Examples**: Use a small capacity (e.g., 2) and walk through a sequence of `put` and `get` operations. Show how the linked list and hash map change. This demonstrates your understanding of the LRU logic.
3.  **Handle Edge Cases**: Be prepared to discuss what happens when the cache is empty, has only one element, or when you `get` or `put` the head or tail element.
4.  **Discuss Sentinel Nodes (Optional but good)**: Mention that using dummy `head` and `tail` nodes can simplify the `addAtTail` and `removeNode` logic by eliminating checks for `null` head/tail. The provided solution doesn't use them, so be ready to explain its logic thoroughly.
5.  **Clarify `removeNode` and `addAtTail`**: These are the core linked list manipulation functions. Ensure you can explain them clearly and correctly, especially how `head` and `tail` pointers are updated.

## Revision Checklist
- [ ] Understand the LRU eviction policy.
- [ ] Implement a `Node` class for a doubly linked list.
- [ ] Use a `HashMap` for O(1) key lookups.
- [ ] Implement `addAtTail` to move/add nodes to the end of the list.
- [ ] Implement `removeNode` to detach nodes from the list.
- [ ] Correctly update `head` and `tail` pointers in `removeNode` and `addAtTail`.
- [ ] Handle cache capacity in `put` by evicting the LRU item.
- [ ] Ensure `get` moves accessed items to the tail.
- [ ] Test with edge cases: empty cache, full cache, single element cache.

## Similar Problems
*   LFU Cache (LeetCode 460)
*   Design Browser History (LeetCode 1472)
*   Design Circular Deque (LeetCode 641)

## Tags
`Linked List` `Hash Map` `Design` `Doubly-Linked List`

## My Notes
Using Doubly Linked List. Amazing
