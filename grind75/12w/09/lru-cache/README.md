# Lru Cache

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:** O(1)  
**Space:** O(capacity)

---

## Solution (java)

```java
class LRUCache {
  class Node{
    int key;
    int value;
    Node prev;
    Node next;
    Node(int k, int v){
      this.key = k;
      this.value = v;
      this.next =null;
      this.prev =null;
    }
  }
    int capacity;
    HashMap<Integer,Node> hm;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hm = new HashMap<>();
    }
    
    public int get(int key) {
        if(!hm.containsKey(key)) return -1;
        Node node = hm.get(key);
        removeNode(node);
        addAtLast(node);
        return node.value;
    }
    public void put(int key, int value) {
        if(hm.containsKey(key)){ 
            Node node = hm.get(key);
            removeNode(node);
            node.value = value;
            addAtLast(node);
            return;
        }
        if(hm.size()==capacity) removeFirst();
        Node newNode = new Node(key,value);
        addAtLast(newNode);
    }
  public void removeNode(Node node){
    hm.remove(node.key);
    if(node==head) head = node.next; else node.prev.next = node.next;
    if(node==tail) tail = node.prev; else node.next.prev = node.prev;
  }
  public void addAtLast(Node node){
   //first node 
    if(tail==null) head=tail=node;
    else{
      //node already exist
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
    hm.put(node.key,node);
  }
  public void removeFirst(){
    if(head==null)return;
    hm.remove(head.key);
    if(head==tail){head= tail=null; return;}
    head = head.next;
    head.prev = null;
  }
}
```

---

---
## Quick Revision
This problem asks to implement a cache with a fixed capacity that evicts the least recently used item when full.
We solve this using a combination of a HashMap for O(1) lookups and a Doubly Linked List to maintain the order of usage.

## Intuition
The core idea is that we need to quickly find an item (for `get` and `put` updates) and also quickly know which item is the least recently used (for eviction). A HashMap is perfect for fast lookups by key. However, a HashMap doesn't inherently maintain an order. To track usage order, a Doubly Linked List is ideal. The head of the list will represent the most recently used item, and the tail will represent the least recently used. When an item is accessed (`get` or `put`), we move it to the front of the list. When the cache is full and we need to add a new item, we remove the item at the tail.

## Algorithm
1.  **Initialization**:
    *   Create a `HashMap` to store key-node mappings for O(1) access.
    *   Create a Doubly Linked List to maintain the order of usage. Use dummy `head` and `tail` nodes to simplify edge cases (empty list, single node list).
    *   Store the `capacity`.

2.  **`get(key)` Operation**:
    *   Check if the `key` exists in the `HashMap`.
    *   If not, return -1.
    *   If it exists, retrieve the corresponding `Node` from the `HashMap`.
    *   **Move to Front**: Remove this `Node` from its current position in the Doubly Linked List.
    *   **Move to Front**: Add this `Node` to the front of the Doubly Linked List (making it the most recently used).
    *   Return the `value` of the `Node`.

3.  **`put(key, value)` Operation**:
    *   Check if the `key` already exists in the `HashMap`.
    *   If it exists:
        *   Retrieve the `Node`.
        *   Update its `value`.
        *   **Move to Front**: Remove this `Node` from its current position.
        *   **Move to Front**: Add this `Node` to the front of the Doubly Linked List.
    *   If it does not exist:
        *   Check if the `HashMap` size equals the `capacity`.
        *   If full:
            *   **Evict**: Remove the least recently used item (the node right after the dummy `head` in the Doubly Linked List) from both the `HashMap` and the Doubly Linked List.
        *   Create a new `Node` with the given `key` and `value`.
        *   Add the new `Node` to the front of the Doubly Linked List.
        *   Add the new `Node` to the `HashMap`.

4.  **Helper Functions**:
    *   `removeNode(node)`: Removes a given `Node` from the Doubly Linked List by updating `prev` and `next` pointers of its neighbors.
    *   `addNodeToFront(node)`: Adds a given `Node` to the front of the Doubly Linked List.
    *   `removeTail()`: Removes the least recently used node (the one before the dummy `tail`).

## Concept to Remember
*   **HashMap**: For O(1) average time complexity for key-value lookups, insertions, and deletions.
*   **Doubly Linked List**: To maintain the order of elements and allow O(1) insertion/deletion at any point (especially at the ends).
*   **Cache Eviction Policies**: Understanding LRU (Least Recently Used) as a common strategy.
*   **Dummy Nodes**: Using sentinel nodes (dummy head and tail) in a linked list simplifies boundary condition handling (e.g., empty list, single node list, removing head/tail).

## Common Mistakes
*   **Incorrectly updating linked list pointers**: Forgetting to update both `prev` and `next` pointers, or not handling edge cases like removing the head or tail.
*   **Not removing from HashMap when evicting**: When the cache is full and an item is evicted from the linked list, it must also be removed from the HashMap.
*   **Not moving accessed nodes to the front**: Forgetting to update the linked list order when an item is accessed via `get` or `put` (if it already exists).
*   **Inefficient linked list operations**: Implementing linked list operations that are not O(1) (e.g., searching for a node to remove).
*   **Not handling the initial empty state correctly**: When the cache is empty, `head` and `tail` might be null, requiring careful checks.

## Complexity Analysis
*   **Time**: O(1) for both `get` and `put` operations.
    *   Reason: HashMap operations (`containsKey`, `get`, `put`, `remove`) are O(1) on average. Doubly Linked List operations (adding to front, removing from anywhere, removing from end) are also O(1) because we have direct access to nodes via the HashMap and the list structure.
*   **Space**: O(capacity)
    *   Reason: The HashMap stores up to `capacity` key-node pairs, and the Doubly Linked List stores up to `capacity` nodes.

## Commented Code
```java
class LRUCache {
    // Inner class to represent a node in the Doubly Linked List
    class Node {
        int key; // The key of the cache entry
        int value; // The value of the cache entry
        Node prev; // Pointer to the previous node in the list
        Node next; // Pointer to the next node in the list

        // Constructor for a new Node
        Node(int k, int v) {
            this.key = k; // Initialize key
            this.value = v; // Initialize value
            this.next = null; // Initialize next pointer to null
            this.prev = null; // Initialize prev pointer to null
        }
    }

    int capacity; // The maximum number of items the cache can hold
    HashMap<Integer, Node> hm; // HashMap to store key -> Node mappings for O(1) access
    Node head; // Pointer to the most recently used node (front of the list)
    Node tail; // Pointer to the least recently used node (end of the list)

    // Constructor for LRUCache
    public LRUCache(int capacity) {
        this.capacity = capacity; // Set the cache capacity
        this.hm = new HashMap<>(); // Initialize the HashMap
        // Initialize dummy head and tail nodes to simplify list operations
        // head.next will point to the MRU, tail.prev will point to the LRU
        this.head = new Node(0, 0); // Dummy head node
        this.tail = new Node(0, 0); // Dummy tail node
        this.head.next = this.tail; // Link head to tail initially
        this.tail.prev = this.head; // Link tail to head initially
    }

    // Get the value of the key if the key exists, otherwise return -1
    public int get(int key) {
        // Check if the key is present in the HashMap
        if (!hm.containsKey(key)) {
            return -1; // Key not found, return -1
        }
        // Retrieve the node associated with the key
        Node node = hm.get(key);
        // Move the accessed node to the front of the list (most recently used)
        removeNode(node); // Remove from its current position
        addNodeToFront(node); // Add to the front
        return node.value; // Return the value of the node
    }

    // Put a key-value pair into the cache. If the key already exists, update the value.
    // If the number of keys exceeds the capacity from this operation, evict the least recently used key.
    public void put(int key, int value) {
        // Check if the key already exists in the HashMap
        if (hm.containsKey(key)) {
            Node node = hm.get(key); // Get the existing node
            removeNode(node); // Remove it from its current position
            node.value = value; // Update its value
            addNodeToFront(node); // Add it back to the front (MRU)
        } else {
            // If the cache is full, evict the least recently used item
            if (hm.size() == capacity) {
                removeTail(); // Remove the node at the tail (LRU)
            }
            // Create a new node for the new key-value pair
            Node newNode = new Node(key, value);
            addNodeToFront(newNode); // Add the new node to the front (MRU)
            hm.put(key, newNode); // Add the new node to the HashMap
        }
    }

    // Helper method to remove a node from the Doubly Linked List
    private void removeNode(Node node) {
        // Update the next pointer of the previous node
        node.prev.next = node.next;
        // Update the prev pointer of the next node
        node.next.prev = node.prev;
    }

    // Helper method to add a node to the front of the Doubly Linked List (after dummy head)
    private void addNodeToFront(Node node) {
        // Link the new node to the current first node (head.next)
        node.next = head.next;
        node.prev = head; // Link new node's prev to head
        // Update the previous node's next pointer to point to the new node
        head.next.prev = node;
        // Update the head's next pointer to point to the new node
        head.next = node;
    }

    // Helper method to remove the least recently used node (the one before dummy tail)
    private void removeTail() {
        // Get the node to be removed (the one before the dummy tail)
        Node nodeToRemove = tail.prev;
        // Remove this node from the HashMap
        hm.remove(nodeToRemove.key);
        // Remove the node from the Doubly Linked List
        removeNode(nodeToRemove);
    }
}
```

## Interview Tips
1.  **Explain the Trade-offs**: Clearly articulate why a HashMap and a Doubly Linked List are used together. Mention the O(1) lookup of HashMap and O(1) insertion/deletion of DLL.
2.  **Walk Through Edge Cases**: Discuss how your implementation handles an empty cache, a full cache, adding a new item, getting an existing item, and updating an existing item.
3.  **Visualize the Linked List**: When explaining `get` and `put`, draw or describe how the nodes move within the Doubly Linked List. Emphasize that accessed items become the "most recently used" (front) and evicted items are the "least recently used" (back).
4.  **Dummy Nodes**: Explain the benefit of using dummy `head` and `tail` nodes. They simplify the logic for `addNodeToFront`, `removeNode`, and `removeTail` by eliminating checks for `null` pointers when dealing with an empty list or when removing the actual head/tail.

## Revision Checklist
- [ ] Understand the LRU eviction policy.
- [ ] Implement a HashMap for O(1) key lookups.
- [ ] Implement a Doubly Linked List for O(1) order maintenance.
- [ ] Correctly handle `get` operation: lookup, move to front.
- [ ] Correctly handle `put` operation: update, add new, evict if full.
- [ ] Implement helper methods for linked list manipulation (`removeNode`, `addNodeToFront`, `removeTail`).
- [ ] Consider and handle edge cases (empty cache, full cache, single element).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution verbally.

## Similar Problems
*   Least Number of Unique Integers after K Removals
*   Design Browser History
*   LFUCache (Least Frequently Used Cache)

## Tags
`Array` `Hash Map` `Linked List` `Doubly-Linked List` `Design`
