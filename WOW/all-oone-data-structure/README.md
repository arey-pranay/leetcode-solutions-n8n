# All Oone Data Structure

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:**   
**Space:** O(N)

---

## Solution (java)

```java
class AllOne {
    class Node{
        Node prev;
        Node next;
        int freq;
        HashSet<String> keys;
        Node(int freq){
            this.freq = freq;
            this.keys = new HashSet<>();
        }
    }
    HashMap<String,Node> map;
    Node head,tail;
    public AllOne() {
        head = null;
        tail = null;
        map = new HashMap<>();
    }
    
    public void inc(String key) {
        if(head==null && tail==null) { head = createAndAttach(key,1,null,null); tail = head; map.put(key,head); return; }
        
        if(!map.containsKey(key)){ // freq = 1;
            if(head.freq!=1) head = createAndAttach(key,1,null,head);                
            
            head.keys.add(key);
            map.put(key,head);
            
            return;
        }
        
        Node oldNode = map.get(key);
        oldNode.keys.remove(key);
        int oldFreq = oldNode.freq;
        
        Node nextNode = oldNode.next;
        if(nextNode==null || nextNode.freq != oldFreq+1) nextNode = createAndAttach(key,oldFreq+1,oldNode,nextNode);
        if(nextNode.next==null) tail=nextNode;    //nextNode is supposed to be tail kyuki uske baad me nhi hai na kuch    
        
        nextNode.keys.add(key);
        map.put(key,nextNode);
        
        if(oldNode.keys.isEmpty()) removeNode(oldNode);
    }
    
    public void dec(String key) {
        
        Node oldNode = map.get(key);
        oldNode.keys.remove(key);
        int oldFreq = oldNode.freq;
        
        if(oldFreq==1){
            map.remove(key);
            if(oldNode.keys.isEmpty()) removeNode(oldNode);
            return;
        }
        
        Node prevNode = oldNode.prev;
        if(prevNode == null || prevNode.freq != oldFreq-1) prevNode = createAndAttach(key,oldFreq-1,prevNode,oldNode);
        
        if(prevNode.prev==null) head=prevNode; //prevNode is supposed to be head kyuki uske pehle me nhi hai na kuch
        
        prevNode.keys.add(key);
        map.put(key,prevNode);
        
        if(oldNode.keys.isEmpty()) removeNode(oldNode);
    }
    
    public String getMaxKey() {
        if(tail==null) return "";
        return tail.keys.iterator().next();
    }
    
    public String getMinKey() {
        if(head==null) return "";
        return head.keys.iterator().next();
    }
    
    public void removeNode(Node node){        
        if(node.prev==null && node.next==null){
            head = null; 
            tail=null;
            return;
        }
        
        if(node.prev==null){
            node.next.prev = null; 
            head = node.next; 
            node.next=null; 
            return;
        }
        
        if(node.next==null){
            node.prev.next = null; 
            tail = node.prev; 
            node.prev=null; 
            return;
        }
        
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
    
    public Node createAndAttach(String key,int freq, Node prev , Node next){
        Node temp = new Node(freq);
        temp.keys.add(key);
        temp.prev = prev;
        temp.next = next;
        if(next!=null)next.prev = temp;
        if(prev!=null)prev.next = temp;
        return temp;
    }
    
    public void printList(){
        Node curr = head;
        while(curr!=null){
            System.out.print(curr.freq+": "+curr.keys+ " --> ");   
            curr=curr.next;         
        }        
        System.out.println();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
```

---

---
## Quick Revision
This problem asks for a data structure that supports O(1) time complexity for incrementing/decrementing a key's count, and retrieving the key with the maximum/minimum count.
The solution uses a doubly linked list of frequency buckets, where each bucket contains keys with the same count, and a hash map to quickly access a key's current frequency node.

## Intuition
The core challenge is to maintain the minimum and maximum keys efficiently. If we simply used a hash map to store key-frequency pairs, finding the min/max would take O(N) time, where N is the number of unique keys. To achieve O(1) for min/max, we need a structure that keeps track of the extreme frequencies.

A hash map alone isn't enough. We need to group keys by their frequencies. A doubly linked list is a good candidate for this, where each node in the list represents a specific frequency. Keys with the same frequency would be stored in a set within that linked list node.

When a key's frequency is incremented, it might need to move from its current frequency bucket to the next higher frequency bucket. Similarly, decrementing might move it to a lower frequency bucket. This movement needs to be O(1).

The "aha moment" comes from realizing that we can maintain a doubly linked list of "frequency nodes." Each frequency node stores a set of keys that currently have that specific frequency. The linked list itself is ordered by frequency. This allows us to:
1. Quickly find the node for the current frequency of a key using a separate hash map (key -> frequency node).
2. Move a key to the next/previous frequency node in O(1) time by manipulating the linked list pointers.
3. Easily access the minimum and maximum keys by looking at the head and tail of the linked list, respectively.

## Algorithm
1.  **Data Structures:**
    *   `Node` class: Represents a frequency bucket. It contains:
        *   `prev`, `next`: Pointers for the doubly linked list.
        *   `freq`: The frequency count this node represents.
        *   `keys`: A `HashSet` to store all keys that currently have this `freq`.
    *   `map`: A `HashMap<String, Node>` to store each key and a reference to the `Node` (frequency bucket) it belongs to. This allows O(1) lookup of a key's current frequency node.
    *   `head`, `tail`: Pointers to the first and last `Node` in the doubly linked list, representing the minimum and maximum frequencies, respectively.

2.  **`AllOne()` Constructor:**
    *   Initialize `map` as an empty `HashMap`.
    *   Initialize `head` and `tail` to `null`.

3.  **`inc(String key)`:**
    *   **Case 1: Data structure is empty (`head == null`)**
        *   Create a new `Node` with `freq = 1`.
        *   Add `key` to this new node's `keys` set.
        *   Set `head` and `tail` to this new node.
        *   Put `key` and the new node into `map`.
        *   Return.
    *   **Case 2: `key` is not in `map` (new key)**
        *   If the `head` node's frequency is not 1, create a new `Node` with `freq = 1`, link it before the current `head`, and update `head`.
        *   Add `key` to the `head` node's `keys` set.
        *   Put `key` and the `head` node into `map`.
        *   Return.
    *   **Case 3: `key` is already in `map`**
        *   Get the `oldNode` (current frequency node) for `key` from `map`.
        *   Remove `key` from `oldNode.keys`.
        *   Let `oldFreq = oldNode.freq`.
        *   **Find/Create the `nextNode`:**
            *   If `oldNode.next` exists and its frequency is `oldFreq + 1`, then `nextNode` is `oldNode.next`.
            *   Otherwise, create a new `Node` with `freq = oldFreq + 1`, link it after `oldNode`, and set `nextNode` to this new node.
        *   If `nextNode` becomes the new `tail` (i.e., `nextNode.next` is null after creation/linking), update `tail`.
        *   Add `key` to `nextNode.keys`.
        *   Update `map` to point `key` to `nextNode`.
        *   **Clean up `oldNode`:** If `oldNode.keys` is now empty, remove `oldNode` from the doubly linked list using `removeNode()`.

4.  **`dec(String key)`:**
    *   Get the `oldNode` (current frequency node) for `key` from `map`.
    *   Remove `key` from `oldNode.keys`.
    *   Let `oldFreq = oldNode.freq`.
    *   **Case 1: `oldFreq` is 1**
        *   Remove `key` from `map`.
        *   If `oldNode.keys` is now empty, remove `oldNode` from the doubly linked list using `removeNode()`.
        *   Return.
    *   **Case 2: `oldFreq` > 1**
        *   **Find/Create the `prevNode`:**
            *   If `oldNode.prev` exists and its frequency is `oldFreq - 1`, then `prevNode` is `oldNode.prev`.
            *   Otherwise, create a new `Node` with `freq = oldFreq - 1`, link it before `oldNode`, and set `prevNode` to this new node.
        *   If `prevNode` becomes the new `head` (i.e., `prevNode.prev` is null after creation/linking), update `head`.
        *   Add `key` to `prevNode.keys`.
        *   Update `map` to point `key` to `prevNode`.
        *   **Clean up `oldNode`:** If `oldNode.keys` is now empty, remove `oldNode` from the doubly linked list using `removeNode()`.

5.  **`getMaxKey()`:**
    *   If `tail` is `null` (empty structure), return `""`.
    *   Return any key from `tail.keys` (e.g., `tail.keys.iterator().next()`).

6.  **`getMinKey()`:**
    *   If `head` is `null` (empty structure), return `""`.
    *   Return any key from `head.keys` (e.g., `head.keys.iterator().next()`).

7.  **`removeNode(Node node)`:**
    *   Handles removing a `Node` from the doubly linked list.
    *   Updates `head` and `tail` if the removed node was the first or last.
    *   Adjusts `prev` and `next` pointers of adjacent nodes.
    *   Sets the removed node's `prev` and `next` to `null`.

8.  **`createAndAttach(String key, int freq, Node prev, Node next)`:**
    *   A helper method to create a new `Node` with a given frequency and key, and attach it into the doubly linked list at the specified `prev` and `next` positions.
    *   It also adds the `key` to the new node's `keys` set.

## Concept to Remember
*   **Doubly Linked List:** Essential for maintaining an ordered sequence of frequency buckets and allowing O(1) insertion/deletion of nodes.
*   **Hash Map:** Crucial for O(1) lookup of a key's current frequency node, enabling efficient updates.
*   **Frequency Bucketing:** Grouping elements by their counts to manage min/max efficiently.
*   **Amortized Analysis:** While some operations might involve creating new nodes, the overall performance remains efficient due to the linked list structure.

## Common Mistakes
*   **Incorrectly handling edge cases:** Forgetting to update `head` and `tail` when adding/removing nodes, especially when the list is empty or has only one node.
*   **Not removing empty frequency nodes:** If a `Node`'s `keys` set becomes empty after an `inc` or `dec` operation, it must be removed from the linked list to maintain correctness and efficiency.
*   **Off-by-one errors in frequency updates:** Incorrectly calculating the `oldFreq + 1` or `oldFreq - 1` when creating new nodes or moving keys.
*   **Forgetting to update the `map`:** After moving a key to a new frequency node, the `map` must be updated to reflect this change.
*   **Inefficient `getMaxKey`/`getMinKey`:** If the linked list is not maintained correctly, these operations might not be O(1).

## Complexity Analysis
*   **Time:**
    *   `AllOne()`: O(1) - Initialization of data structures.
    *   `inc(String key)`: O(1) amortized. In the worst case, a new node might be created, but this happens infrequently. Most operations involve hash map lookups/updates and linked list pointer manipulations, which are O(1).
    *   `dec(String key)`: O(1) amortized. Similar to `inc`, operations are typically O(1) with occasional node creation/deletion.
    *   `getMaxKey()`: O(1) - Directly accesses the `tail` node.
    *   `getMinKey()`: O(1) - Directly accesses the `head` node.
    *   **Reason:** Hash map operations (`containsKey`, `get`, `put`, `remove`) are O(1) on average. Doubly linked list operations (insertion, deletion, pointer updates) are O(1). Creating a new node is O(1). Removing an empty node is O(1).

*   **Space:** O(N), where N is the total number of unique keys.
    *   **Reason:** The `map` stores an entry for each unique key. The `HashSet`s within the `Node`s collectively store all unique keys. The number of `Node`s in the linked list can be at most N (if each key has a unique frequency), but it's often much less.

## Commented Code
```java
class AllOne {
    // Inner class to represent a node in the doubly linked list.
    // Each node corresponds to a specific frequency and holds a set of keys with that frequency.
    class Node{
        Node prev; // Pointer to the previous node in the frequency list.
        Node next; // Pointer to the next node in the frequency list.
        int freq; // The frequency count this node represents.
        HashSet<String> keys; // A set of keys that currently have this 'freq'.

        // Constructor for the Node.
        Node(int freq){
            this.freq = freq; // Initialize the frequency.
            this.keys = new HashSet<>(); // Initialize an empty set for keys.
        }
    }

    // HashMap to store each key and a reference to the Node (frequency bucket) it belongs to.
    // This allows O(1) lookup of a key's current frequency node.
    HashMap<String,Node> map;

    // Pointers to the head and tail of the doubly linked list.
    // 'head' points to the node with the minimum frequency.
    // 'tail' points to the node with the maximum frequency.
    Node head,tail;

    // Constructor for the AllOne object.
    public AllOne() {
        head = null; // Initialize head to null (empty list).
        tail = null; // Initialize tail to null (empty list).
        map = new HashMap<>(); // Initialize the map.
    }

    // Increments the count of the given key.
    public void inc(String key) {
        // If the data structure is completely empty, create the first node with frequency 1.
        if(head==null && tail==null) {
            // Create a new node with frequency 1.
            head = createAndAttach(key,1,null,null);
            // This new node is also the tail.
            tail = head;
            // Map the key to this new node.
            map.put(key,head);
            return; // Operation complete.
        }

        // If the key is not yet in the map (i.e., it's a new key).
        if(!map.containsKey(key)){
            // If the head node's frequency is not 1, we need to create a new node for frequency 1.
            // This ensures that frequency 1 is always represented by the head node if it exists.
            if(head.freq!=1) {
                // Create a new node for frequency 1, linking it before the current head.
                head = createAndAttach(key,1,null,head);
            }
            // Add the key to the keys set of the head node (which now has frequency 1).
            head.keys.add(key);
            // Map the key to the head node.
            map.put(key,head);

            return; // Operation complete.
        }

        // If the key already exists in the map.
        Node oldNode = map.get(key); // Get the current node for this key.
        oldNode.keys.remove(key); // Remove the key from its old frequency set.
        int oldFreq = oldNode.freq; // Store the old frequency.

        Node nextNode = oldNode.next; // Get the potential next node in the frequency list.

        // Check if a node for the next frequency (oldFreq + 1) already exists.
        // If not, or if the next node is null, we need to create a new node for oldFreq + 1.
        if(nextNode==null || nextNode.freq != oldFreq+1) {
            // Create a new node for frequency (oldFreq + 1), linking it after oldNode.
            nextNode = createAndAttach(key,oldFreq+1,oldNode,nextNode);
        }
        // If the newly created or existing nextNode is the last in the list, it becomes the new tail.
        if(nextNode.next==null) tail=nextNode;

        // Add the key to the keys set of the next frequency node.
        nextNode.keys.add(key);
        // Update the map to point the key to this new node.
        map.put(key,nextNode);

        // If the old node's keys set is now empty, remove the old node from the linked list.
        if(oldNode.keys.isEmpty()) removeNode(oldNode);
    }

    // Decrements the count of the given key.
    public void dec(String key) {
        // Get the current node for this key.
        Node oldNode = map.get(key);
        oldNode.keys.remove(key); // Remove the key from its current frequency set.
        int oldFreq = oldNode.freq; // Store the old frequency.

        // If the old frequency was 1, the key is effectively removed from the structure.
        if(oldFreq==1){
            map.remove(key); // Remove the key from the map.
            // If the old node's keys set is now empty, remove the old node from the linked list.
            if(oldNode.keys.isEmpty()) removeNode(oldNode);
            return; // Operation complete.
        }

        // If the old frequency was greater than 1.
        Node prevNode = oldNode.prev; // Get the potential previous node in the frequency list.

        // Check if a node for the previous frequency (oldFreq - 1) already exists.
        // If not, or if the previous node is null, we need to create a new node for oldFreq - 1.
        if(prevNode == null || prevNode.freq != oldFreq-1) {
            // Create a new node for frequency (oldFreq - 1), linking it before oldNode.
            prevNode = createAndAttach(key,oldFreq-1,prevNode,oldNode);
        }
        // If the newly created or existing prevNode is the first in the list, it becomes the new head.
        if(prevNode.prev==null) head=prevNode;

        // Add the key to the keys set of the previous frequency node.
        prevNode.keys.add(key);
        // Update the map to point the key to this new node.
        map.put(key,prevNode);

        // If the old node's keys set is now empty, remove the old node from the linked list.
        if(oldNode.keys.isEmpty()) removeNode(oldNode);
    }

    // Returns a key that has the maximum count.
    public String getMaxKey() {
        // If the list is empty, return an empty string.
        if(tail==null) return "";
        // The tail node contains keys with the maximum frequency. Return any one of them.
        return tail.keys.iterator().next();
    }

    // Returns a key that has the minimum count.
    public String getMinKey() {
        // If the list is empty, return an empty string.
        if(head==null) return "";
        // The head node contains keys with the minimum frequency. Return any one of them.
        return head.keys.iterator().next();
    }

    // Helper method to remove a node from the doubly linked list.
    public void removeNode(Node node){
        // If the node is the only node in the list.
        if(node.prev==null && node.next==null){
            head = null; // Set head to null.
            tail=null; // Set tail to null.
            return; // Operation complete.
        }

        // If the node is the head of the list.
        if(node.prev==null){
            node.next.prev = null; // The next node's previous pointer should be null.
            head = node.next; // The next node becomes the new head.
            node.next=null; // Detach the removed node from the list.
            return; // Operation complete.
        }

        // If the node is the tail of the list.
        if(node.next==null){
            node.prev.next = null; // The previous node's next pointer should be null.
            tail = node.prev; // The previous node becomes the new tail.
            node.prev=null; // Detach the removed node from the list.
            return; // Operation complete.
        }

        // If the node is in the middle of the list.
        node.prev.next = node.next; // Link the previous node to the next node.
        node.next.prev = node.prev; // Link the next node to the previous node.
        node.prev = null; // Detach the removed node from the list.
        node.next = null; // Detach the removed node from the list.
    }

    // Helper method to create a new node and attach it into the doubly linked list.
    // It also adds the given key to the new node's keys set.
    public Node createAndAttach(String key,int freq, Node prev , Node next){
        Node temp = new Node(freq); // Create a new node with the specified frequency.
        temp.keys.add(key); // Add the key to the new node's keys set.
        temp.prev = prev; // Set the previous pointer.
        temp.next = next; // Set the next pointer.

        // Update the next pointer of the previous node if it exists.
        if(next!=null)next.prev = temp;
        // Update the previous pointer of the next node if it exists.
        if(prev!=null)prev.next = temp;

        return temp; // Return the newly created and attached node.
    }

    // Helper method for debugging: prints the linked list of frequencies.
    public void printList(){
        Node curr = head; // Start from the head.
        while(curr!=null){ // Iterate through the list.
            System.out.print(curr.freq+": "+curr.keys+ " --> "); // Print frequency and keys.
            curr=curr.next; // Move to the next node.
        }
        System.out.println(); // Newline at the end.
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
```

## Interview Tips
*   **Explain the trade-offs:** Clearly articulate why a simple hash map isn't sufficient and why the doubly linked list of frequency buckets is necessary for O(1) min/max operations.
*   **Walk through `inc` and `dec` with examples:** Use a small set of keys and walk through the `inc` and `dec` operations step-by-step, showing how keys move between frequency nodes and how the linked list is modified. This demonstrates a deep understanding.
*   **Focus on edge cases:** Be prepared to discuss how you handle an empty data structure, adding the first key, removing the last key from a frequency bucket, and the special case of frequency 1 in `dec`.
*   **Clarify "O(1) amortized":** Understand and be able to explain why the operations are amortized O(1) due to the potential creation of new nodes, but on average, they are constant time.

## Revision Checklist
- [ ] Understand the problem requirements: O(1) for inc, dec, getMaxKey, getMinKey.
- [ ] Identify the need for a structure that tracks min/max efficiently.
- [ ] Design the `Node` class: `freq`, `keys` (HashSet), `prev`, `next`.
- [ ] Design the main class: `map` (String to Node), `head`, `tail`.
- [ ] Implement `inc()`: handle empty list, new key, existing key, node creation/movement, empty node removal.
- [ ] Implement `dec()`: handle frequency 1, existing key, node creation/movement, empty node removal.
- [ ] Implement `getMaxKey()` and `getMinKey()` using `tail` and `head`.
- [ ] Implement helper methods: `removeNode()`, `createAndAttach()`.
- [ ] Consider edge cases: empty list, single node list, removing last key from a bucket.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and walking through examples.

## Similar Problems
*   LRU Cache (LeetCode 146) - Uses a doubly linked list and hash map for O(1) get/put.
*   LFU Cache (LeetCode 460) - More complex, involves multiple linked lists or a similar structure to track frequency.
*   Design Hit Counter (LeetCode 362) - Simpler, but involves tracking counts over time.

## Tags
`Doubly Linked List` `Hash Map` `Design`
