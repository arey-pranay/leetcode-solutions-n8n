# Lru Cache

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:** See complexity section  
**Space:** O(capacity)

---

## Solution (java)

```java
class LRUCache {
    class Node{
        Node next;
        Node prev;
        int key;
        int value;
        Node(int key, int value){
            this.value = value;
            this.key = key;
            this.next = null;
            this.prev = null;
        }
    }
    HashMap<Integer,Node> hm;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity; 
        hm= new HashMap<>();
        
    }
    
    public int get(int key) {
        if(!hm.containsKey(key)) return -1;
        Node x = hm.get(key);
        remove(x);
        putLast(x);
        return x.value;
    }
    
    public void put(int key, int value) {
        if(hm.containsKey(key)){
            Node old = hm.get(key);
            old.value = value;
            remove(old);
            putLast(old);
        } else {
            if(hm.size()==capacity){
                hm.remove(head.key);   
                remove(head);
            }
            Node temp =new Node(key,value);
            putLast(temp);
            hm.put(key,temp);
        }
    }
    public void remove(Node x){
        if(x.prev==null) head = x.next;
        else x.prev.next = x.next;

        if(x.next==null) tail = x.prev;
        else x.next.prev = x.prev;
    }
    public void putLast(Node x){
        x.next = null;
        if(tail==null){
            head = tail = x;
            x.prev = null;
        }else{
            tail.next = x;
            x.prev = tail;
            tail = tail.next;
        }
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
A Least Recently Used (LRU) cache evicts the least recently accessed item when full.
This is solved using a combination of a HashMap for O(1) lookups and a Doubly Linked List to maintain access order.

## Intuition
The core idea is to efficiently track which item was used least recently. A HashMap allows us to quickly find any item by its key. However, to know which item is the *least* recently used, we need an ordered structure. A Doubly Linked List is perfect for this because we can move any accessed node to the "most recently used" end (e.g., the tail) in O(1) time, and also remove the "least recently used" node (e.g., the head) in O(1) time.

## Algorithm
1.  **Initialization**:
    *   Create a `HashMap` to store `key -> Node` mappings for O(1) access.
    *   Create a Doubly Linked List to maintain the order of usage. Use `head` and `tail` pointers. `head` will point to the least recently used item, and `tail` to the most recently used.
    *   Store the `capacity` of the cache.
2.  **`get(key)` Operation**:
    *   Check if the `key` exists in the `HashMap`. If not, return -1.
    *   If the `key` exists, retrieve the corresponding `Node` from the `HashMap`.
    *   **Crucially**: Move this `Node` to the end of the Doubly Linked List (making it the most recently used). This involves removing it from its current position and appending it to the tail.
    *   Return the `value` of the `Node`.
3.  **`put(key, value)` Operation**:
    *   Check if the `key` already exists in the `HashMap`.
        *   If it exists:
            *   Update the `value` of the existing `Node`.
            *   Move this `Node` to the end of the Doubly Linked List (making it the most recently used).
        *   If it does not exist:
            *   Check if the `HashMap` is already at `capacity`.
                *   If it is full:
                    *   Remove the `head` node (least recently used) from the `HashMap`.
                    *   Remove the `head` node from the Doubly Linked List.
            *   Create a new `Node` with the given `key` and `value`.
            *   Add this new `Node` to the end of the Doubly Linked List (making it the most recently used).
            *   Add the new `Node` to the `HashMap` with its `key`.

## Concept to Remember
*   **Doubly Linked List**: Essential for O(1) insertion and deletion at arbitrary positions (when you have a reference to the node) and at the ends.
*   **HashMap (or Dictionary)**: Provides O(1) average time complexity for key-value lookups, insertions, and deletions.
*   **Cache Eviction Policies**: Understanding LRU as a common strategy to manage limited memory.
*   **Time-Space Tradeoff**: Using extra space (HashMap and Doubly Linked List) to achieve faster operations.

## Common Mistakes
*   **Incorrect Doubly Linked List Manipulation**: Forgetting to update `prev` and `next` pointers correctly for both nodes involved in a removal or insertion, leading to broken links.
*   **Not Handling Edge Cases in Linked List**: Failing to correctly manage `head` and `tail` pointers when the list is empty, has one element, or when removing/adding the head/tail.
*   **Forgetting to Update HashMap**: When moving a node in the linked list, forgetting to update its reference in the HashMap (though in this specific implementation, the node reference itself doesn't change, only its position).
*   **Not Removing from HashMap on Eviction**: When the cache is full and a new item is added, failing to remove the least recently used item's key from the HashMap.
*   **Inefficient `get` or `put`**: Implementing linked list operations that are not O(1) (e.g., searching for a node to remove).

## Complexity Analysis
*   **Time**:
    *   `get(key)`: O(1) - HashMap lookup is O(1) on average. Removing and adding to the Doubly Linked List (given the node reference) are O(1) operations.
    *   `put(key, value)`: O(1) - HashMap operations are O(1) on average. Doubly Linked List operations are O(1).
*   **Space**: O(capacity) - The HashMap stores up to `capacity` key-node pairs, and the Doubly Linked List stores up to `capacity` nodes.

## Commented Code
```java
class LRUCache {
    // Inner class to represent a node in the Doubly Linked List
    class Node{
        Node next; // Pointer to the next node in the list
        Node prev; // Pointer to the previous node in the list
        int key;   // The key of the cache entry
        int value; // The value of the cache entry

        // Constructor for a Node
        Node(int key, int value){
            this.value = value; // Initialize value
            this.key = key;     // Initialize key
            this.next = null;   // Initially, no next node
            this.prev = null;   // Initially, no previous node
        }
    }

    // HashMap to store key -> Node mappings for O(1) access
    HashMap<Integer,Node> hm;
    // The maximum capacity of the cache
    int capacity;
    // Pointer to the head of the Doubly Linked List (least recently used)
    Node head;
    // Pointer to the tail of the Doubly Linked List (most recently used)
    Node tail;

    // Constructor for LRUCache
    public LRUCache(int capacity) {
        this.capacity = capacity; // Set the cache capacity
        hm = new HashMap<>();     // Initialize the HashMap
        // head and tail are null initially, indicating an empty list
    }
    
    // Retrieves the value associated with a key
    public int get(int key) {
        // If the key is not in the HashMap, it's not in the cache
        if(!hm.containsKey(key)) return -1;
        
        // Get the Node associated with the key from the HashMap
        Node x = hm.get(key);
        
        // Remove the node from its current position in the Doubly Linked List
        remove(x);
        // Add the node to the end of the Doubly Linked List (making it most recently used)
        putLast(x);
        
        // Return the value of the retrieved node
        return x.value;
    }
    
    // Inserts or updates a key-value pair in the cache
    public void put(int key, int value) {
        // Check if the key already exists in the HashMap
        if(hm.containsKey(key)){
            // If it exists, get the old node
            Node old = hm.get(key);
            // Update its value
            old.value = value;
            // Remove the old node from its current position
            remove(old);
            // Add it to the end of the list (making it most recently used)
            putLast(old);
        } else {
            // If the key does not exist, check if the cache is full
            if(hm.size()==capacity){
                // If full, remove the least recently used item (head) from the HashMap
                hm.remove(head.key);   
                // Remove the head node from the Doubly Linked List
                remove(head);
            }
            // Create a new Node for the new key-value pair
            Node temp =new Node(key,value);
            // Add the new node to the end of the Doubly Linked List
            putLast(temp);
            // Add the new node to the HashMap
            hm.put(key,temp);
        }
    }

    // Helper method to remove a node from the Doubly Linked List
    public void remove(Node x){
        // If the node to remove is the head
        if(x.prev==null) head = x.next; // Update head to the next node
        else x.prev.next = x.next;      // Link previous node's next to the node after x

        // If the node to remove is the tail
        if(x.next==null) tail = x.prev; // Update tail to the previous node
        else x.next.prev = x.prev;      // Link next node's prev to the node before x
    }

    // Helper method to add a node to the end (tail) of the Doubly Linked List
    public void putLast(Node x){
        x.next = null; // The new node will be the last, so its next is null
        // If the list is empty
        if(tail==null){
            head = tail = x; // The new node is both head and tail
            x.prev = null;   // It has no previous node
        } else {
            // If the list is not empty
            tail.next = x;   // The current tail's next points to the new node
            x.prev = tail;   // The new node's prev points to the current tail
            tail = tail.next; // The new node becomes the new tail
        }
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
1.  **Explain the Data Structures**: Clearly articulate why a HashMap and a Doubly Linked List are chosen. Emphasize the O(1) access of the HashMap and the O(1) insertion/deletion of the Doubly Linked List.
2.  **Walk Through `get` and `put`**: Verbally trace the execution of both `get` and `put` operations, especially when an item is accessed (moved to tail) or when eviction occurs. Use a small example.
3.  **Handle Edge Cases**: Be prepared to discuss what happens when the cache is empty, has only one element, or when the `head` or `tail` node is being removed/added.
4.  **Code Structure**: Ensure your `Node` class is well-defined and your helper methods (`remove`, `putLast`) are clean and correctly handle pointer manipulations.

## Revision Checklist
- [ ] Understand the LRU eviction policy.
- [ ] Implement a Doubly Linked List with `head` and `tail` pointers.
- [ ] Implement `get` operation: lookup, move to tail, return value.
- [ ] Implement `put` operation: update if exists (move to tail), add if new (evict if full, add to tail).
- [ ] Correctly handle `head` and `tail` pointer updates in `remove` and `putLast`.
- [ ] Ensure HashMap and Doubly Linked List are kept in sync.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LFU Cache (LeetCode 460)
*   Design Browser History (LeetCode 1472)
*   Design Circular Queue (LeetCode 622)

## Tags
`Linked List` `Hash Map` `Design` `Doubly Linked List`
