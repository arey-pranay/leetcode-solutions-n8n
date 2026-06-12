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
This problem asks to implement a Least Recently Used (LRU) cache with a fixed capacity.
We solve it using a combination of a HashMap for O(1) lookups and a Doubly Linked List to maintain the order of usage.

## Intuition
The core idea is that when we access or add an item, it becomes the "most recently used." Conversely, when the cache is full and we need to evict an item, we remove the "least recently used" one. A HashMap allows us to quickly find any item by its key. However, a HashMap alone doesn't maintain order. A Doubly Linked List is perfect for this because we can efficiently move nodes to the end (most recently used) and remove nodes from the beginning (least recently used) in O(1) time. The HashMap will store references to the nodes in the Doubly Linked List.

## Algorithm
1.  **Initialization**:
    *   Create a `HashMap` to store `key -> Node` mappings.
    *   Initialize `capacity`.
    *   Initialize `head` and `tail` pointers for the Doubly Linked List (initially `null`).
2.  **`get(key)` operation**:
    *   Check if the `key` exists in the `HashMap`. If not, return `-1`.
    *   If the `key` exists, retrieve the corresponding `Node` from the `HashMap`.
    *   **Move to front**: Remove this `Node` from its current position in the Doubly Linked List.
    *   **Add to front**: Add this `Node` to the end (tail) of the Doubly Linked List.
    *   Return the `value` of the `Node`.
3.  **`put(key, value)` operation**:
    *   Check if the `key` already exists in the `HashMap`.
        *   If yes:
            *   Retrieve the existing `Node`.
            *   Update its `value`.
            *   **Move to front**: Remove this `Node` from its current position in the Doubly Linked List.
            *   **Add to front**: Add this `Node` to the end (tail) of the Doubly Linked List.
        *   If no:
            *   Check if the `HashMap` size equals `capacity`.
                *   If yes (cache is full):
                    *   Get the `key` of the `head` node (least recently used).
                    *   Remove the `head` node from the `HashMap`.
                    *   **Remove from front**: Remove the `head` node from the Doubly Linked List.
            *   Create a new `Node` with the given `key` and `value`.
            *   **Add to front**: Add this new `Node` to the end (tail) of the Doubly Linked List.
            *   Add the new `Node` to the `HashMap` with its `key`.
4.  **Helper function `remove(Node x)`**:
    *   Handles removing a `Node` from the Doubly Linked List by updating `prev` and `next` pointers of its neighbors.
    *   Special handling for removing `head` or `tail`.
5.  **Helper function `putLast(Node x)`**:
    *   Handles adding a `Node` to the end (tail) of the Doubly Linked List.
    *   Updates `head` and `tail` pointers accordingly.

## Concept to Remember
*   **HashMap**: For efficient O(1) key-value lookups.
*   **Doubly Linked List**: For O(1) insertion and deletion at arbitrary positions (especially head/tail) to maintain usage order.
*   **Cache Eviction Policy**: Understanding LRU as a common strategy.
*   **Sentinel Nodes (Optional but good practice)**: Using dummy `head` and `tail` nodes can simplify edge cases in linked list operations. (The provided solution doesn't use sentinels, but it's a valuable concept).

## Common Mistakes
*   **Incorrectly updating `head` and `tail` pointers**: Especially when removing or adding nodes at the boundaries of the list.
*   **Forgetting to remove the least recently used item from the `HashMap`**: When the cache is full and a new item is added.
*   **Not handling edge cases**: Such as an empty cache, a cache with only one element, or operations on the `head` or `tail` nodes.
*   **Inefficient linked list operations**: If `remove` or `putLast` are not O(1), the overall performance degrades.
*   **Memory leaks**: Not properly dereferencing nodes or forgetting to remove from the HashMap when evicting.

## Complexity Analysis
*   **Time**:
    *   `get(key)`: O(1) - HashMap lookup is O(1), and Doubly Linked List operations (remove, add to end) are O(1).
    *   `put(key, value)`: O(1) - HashMap operations are O(1), and Doubly Linked List operations are O(1).
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

        // Constructor for the Node
        Node(int key, int value){
            this.value = value; // Initialize value
            this.key = key;     // Initialize key
            this.next = null;   // Initialize next pointer to null
            this.prev = null;   // Initialize prev pointer to null
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
        this.capacity = capacity; // Set the capacity
        hm = new HashMap<>();     // Initialize the HashMap
        // head and tail are implicitly null initially
    }

    // Method to get the value associated with a key
    public int get(int key) {
        // If the key is not in the HashMap, it's not in the cache
        if(!hm.containsKey(key)) return -1;

        // Get the node from the HashMap
        Node x = hm.get(key);
        // Remove the node from its current position in the list
        remove(x);
        // Move the accessed node to the end of the list (most recently used)
        putLast(x);
        // Return the value of the accessed node
        return x.value;
    }

    // Method to put a key-value pair into the cache
    public void put(int key, int value) {
        // If the key already exists in the cache
        if(hm.containsKey(key)){
            // Get the existing node
            Node old = hm.get(key);
            // Update its value
            old.value = value;
            // Remove the node from its current position
            remove(old);
            // Move the updated node to the end of the list (most recently used)
            putLast(old);
        } else { // If the key does not exist in the cache
            // Check if the cache is full
            if(hm.size()==capacity){
                // If full, remove the least recently used item (head)
                // Remove from HashMap
                hm.remove(head.key);
                // Remove from Doubly Linked List
                remove(head);
            }
            // Create a new node for the new key-value pair
            Node temp = new Node(key,value);
            // Add the new node to the end of the list (most recently used)
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
        x.next = null; // Ensure the new tail's next is null

        // If the list is empty
        if(tail==null){
            head = tail = x; // The new node is both head and tail
            x.prev = null;   // Ensure new head's prev is null
        } else { // If the list is not empty
            tail.next = x;   // Link current tail's next to the new node
            x.prev = tail;   // Link new node's prev to the current tail
            tail = tail.next; // Update tail to be the new node
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
1.  **Explain the Data Structures**: Clearly articulate *why* you're using a HashMap and a Doubly Linked List together. Emphasize the strengths of each and how they complement each other.
2.  **Walk Through Operations**: Verbally (or on a whiteboard) trace `get` and `put` operations with a small example, showing how the HashMap and the Doubly Linked List change. Pay special attention to edge cases like adding to a full cache or accessing the head/tail.
3.  **Handle Edge Cases**: Be prepared to discuss what happens when the cache is empty, has only one element, or when operations involve the `head` or `tail` nodes. The `remove` and `putLast` helper functions are crucial here.
4.  **Discuss Sentinel Nodes (Optional but good)**: If you have time or are asked for optimizations, mention how using dummy `head` and `tail` nodes can simplify the `remove` and `putLast` logic by eliminating null checks for `head` and `tail` themselves.

## Revision Checklist
- [ ] Understand the LRU eviction policy.
- [ ] Implement a Doubly Linked List with O(1) insertion/deletion.
- [ ] Implement a HashMap for O(1) key lookups.
- [ ] Combine HashMap and Doubly Linked List correctly.
- [ ] Handle `get` operation: lookup, move to end.
- [ ] Handle `put` operation: update existing, add new.
- [ ] Handle cache eviction when capacity is reached.
- [ ] Implement helper methods `remove` and `putLast` robustly.
- [ ] Consider edge cases: empty cache, single element, head/tail operations.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LFU Cache (LeetCode 460)
*   Design Browser History (LeetCode 1472)
*   Design Circular Deque (LeetCode 641)

## Tags
`Linked List` `Hash Map` `Design`
