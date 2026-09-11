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
This problem asks for a data structure that supports incrementing/decrementing key counts and retrieving keys with the minimum/maximum count in O(1) time. It's solved using a doubly linked list of frequency buckets, where each bucket contains keys with the same count.

## Intuition
The core challenge is to maintain O(1) time complexity for all operations, especially `getMaxKey` and `getMinKey`. A simple hash map storing `key -> count` wouldn't allow O(1) min/max retrieval. We need a way to group keys by their counts and efficiently access the groups with the smallest and largest counts.

A doubly linked list where each node represents a frequency bucket (containing a set of keys with that frequency) seems promising. The list will be ordered by frequency. `head` will point to the bucket with the minimum frequency, and `tail` will point to the bucket with the maximum frequency.

When a key's count is incremented:
- If the key is new, it goes into a frequency 1 bucket.
- If the key exists, we remove it from its current frequency bucket.
- We then try to add it to the next higher frequency bucket. If such a bucket doesn't exist, we create it.
- If the original bucket becomes empty, we remove it from the list.

When a key's count is decremented:
- We remove it from its current frequency bucket.
- We then try to add it to the previous lower frequency bucket. If such a bucket doesn't exist, we create it.
- If the original bucket becomes empty and its frequency was 1, we remove the key from the map. If its frequency was greater than 1 and the bucket becomes empty, we remove the bucket from the list.

This structure allows us to always know the min/max keys by looking at the `head` and `tail` nodes of the linked list.

## Algorithm
1.  **Data Structures:**
    *   A `HashMap<String, Node>` named `map` to store each `key` and a reference to the `Node` (frequency bucket) it belongs to.
    *   A doubly linked list of `Node` objects. Each `Node` will store:
        *   `freq`: The frequency count for keys in this node.
        *   `keys`: A `HashSet<String>` containing all keys with this `freq`.
        *   `prev`: Pointer to the previous `Node` in the linked list.
        *   `next`: Pointer to the next `Node` in the linked list.
    *   `head`: Pointer to the `Node` with the minimum frequency.
    *   `tail`: Pointer to the `Node` with the maximum frequency.

2.  **`AllOne()` Constructor:**
    *   Initialize `map` as an empty `HashMap`.
    *   Initialize `head` and `tail` to `null`.

3.  **`inc(String key)`:**
    *   **Case 1: `key` is not in `map` (new key):**
        *   If the list is empty (`head == null`), create a new `Node` with `freq = 1`, add `key` to its `keys` set, set `head` and `tail` to this new node, and put `key -> newNode` into `map`.
        *   If the list is not empty:
            *   Check if `head.freq` is 1. If not, create a new `Node` with `freq = 1`, link it before `head`, and update `head` to this new node.
            *   Add `key` to `head.keys`.
            *   Put `key -> head` into `map`.
    *   **Case 2: `key` is in `map`:**
        *   Get the `oldNode` for `key` from `map`.
        *   Remove `key` from `oldNode.keys`.
        *   Let `oldFreq = oldNode.freq`.
        *   Find or create the `nextNode` for `oldFreq + 1`:
            *   If `oldNode.next` exists and `oldNode.next.freq == oldFreq + 1`, use `oldNode.next`.
            *   Otherwise, create a new `Node` with `freq = oldFreq + 1`, link it after `oldNode`, and update `tail` if `oldNode.next` was `null`.
        *   Add `key` to `nextNode.keys`.
        *   Update `map` to point `key` to `nextNode`.
        *   If `oldNode.keys` is now empty, remove `oldNode` from the linked list.

4.  **`dec(String key)`:**
    *   Get the `oldNode` for `key` from `map`.
    *   Remove `key` from `oldNode.keys`.
    *   Let `oldFreq = oldNode.freq`.
    *   **Case 1: `oldFreq == 1`:**
        *   Remove `key` from `map`.
        *   If `oldNode.keys` is now empty, remove `oldNode` from the linked list.
    *   **Case 2: `oldFreq > 1`:**
        *   Find or create the `prevNode` for `oldFreq - 1`:
            *   If `oldNode.prev` exists and `oldNode.prev.freq == oldFreq - 1`, use `oldNode.prev`.
            *   Otherwise, create a new `Node` with `freq = oldFreq - 1`, link it before `oldNode`, and update `head` if `oldNode.prev` was `null`.
        *   Add `key` to `prevNode.keys`.
        *   Update `map` to point `key` to `prevNode`.
        *   If `oldNode.keys` is now empty, remove `oldNode` from the linked list.

5.  **`getMaxKey()`:**
    *   If `tail` is `null` (empty structure), return `""`.
    *   Otherwise, return any key from `tail.keys` (e.g., `tail.keys.iterator().next()`).

6.  **`getMinKey()`:**
    *   If `head` is `null` (empty structure), return `""`.
    *   Otherwise, return any key from `head.keys` (e.g., `head.keys.iterator().next()`).

7.  **Helper Methods:**
    *   `removeNode(Node node)`: Removes a node from the doubly linked list, updating `head` and `tail` if necessary.
    *   `createAndAttach(String key, int freq, Node prev, Node next)`: Creates a new `Node` with the given `freq` and `key`, links it between `prev` and `next`, and returns the new node.

## Concept to Remember
*   **Doubly Linked List:** Essential for O(1) insertion and deletion of nodes, allowing efficient management of frequency buckets.
*   **Hash Map:** Used for O(1) average time lookup of keys to their corresponding frequency nodes.
*   **Bucketing/Grouping:** Grouping elements (keys) by a common property (frequency) is a common pattern for optimizing range queries or min/max operations.
*   **Sentinel Nodes (Implicit):** While not explicitly using sentinel nodes, `head` and `tail` pointers act similarly to mark the boundaries of the ordered list.

## Common Mistakes
*   **Incorrectly handling `head` and `tail` updates:** Forgetting to update `head` or `tail` when the first/last node is added or removed can lead to incorrect `getMinKey`/`getMaxKey` results.
*   **Not handling empty buckets properly:** When a key is moved from a bucket, if that bucket becomes empty, it must be removed from the linked list to maintain efficiency and correctness.
*   **Off-by-one errors in frequency updates:** Incorrectly calculating the new frequency or failing to create a new bucket when needed for `freq + 1` or `freq - 1`.
*   **Forgetting to update the `map`:** After moving a key to a new node, the `map` must be updated to point to the new node.
*   **Edge cases with empty structure:** Not handling the initial empty state or when the structure becomes empty after operations.

## Complexity Analysis
*   **Time:**
    *   `inc(String key)`: O(1) on average. Hash map operations are O(1) average. Linked list node creation/insertion/deletion is O(1). Finding the correct next/prev node might involve traversing a few nodes in the worst case if frequencies are very sparse, but on average, it's O(1) because we are either using an existing adjacent node or creating a new one right next to the current one.
    *   `dec(String key)`: O(1) on average. Similar reasoning to `inc`.
    *   `getMaxKey()`: O(1). Directly accesses the `tail` node.
    *   `getMinKey()`: O(1). Directly accesses the `head` node.
*   **Space:** O(N), where N is the number of unique keys. The `map` stores N entries, and the `HashSet`s within the nodes collectively store N keys.

## Commented Code
```java
class AllOne {
    // Inner class representing a node in the doubly linked list.
    // Each node stores keys with the same frequency.
    class Node{
        Node prev; // Pointer to the previous node in the frequency list.
        Node next; // Pointer to the next node in the frequency list.
        int freq; // The frequency count this node represents.
        HashSet<String> keys; // Set of keys that have this frequency.

        // Constructor for a Node.
        Node(int freq){
            this.freq = freq; // Initialize frequency.
            this.keys = new HashSet<>(); // Initialize an empty set for keys.
        }
    }

    // Map to store each key and a reference to the Node it belongs to.
    // Allows O(1) average time lookup of a key's current frequency node.
    HashMap<String,Node> map;

    // Pointers to the head and tail of the doubly linked list.
    // head points to the node with the minimum frequency.
    // tail points to the node with the maximum frequency.
    Node head,tail;

    // Constructor for the AllOne object.
    public AllOne() {
        head = null; // Initialize head to null (empty list).
        tail = null; // Initialize tail to null (empty list).
        map = new HashMap<>(); // Initialize the map.
    }

    // Increments the count of the given key.
    public void inc(String key) {
        // If the list is completely empty, create the first node for frequency 1.
        if(head==null && tail==null) {
            // Create a new node with frequency 1.
            head = createAndAttach(key,1,null,null);
            // This new node is also the tail.
            tail = head;
            // Map the key to this new head node.
            map.put(key,head);
            // Operation complete.
            return;
        }

        // If the key is not yet in the map (i.e., it's a new key).
        if(!map.containsKey(key)){
            // We need to add this key with frequency 1.
            // Check if the current head node has frequency 1.
            if(head.freq!=1) {
                // If head's frequency is not 1, we need to create a new node for frequency 1
                // and insert it before the current head.
                head = createAndAttach(key,1,null,head);
            }
            // Add the key to the keys set of the head node (which now has freq 1).
            head.keys.add(key);
            // Map the key to the head node.
            map.put(key,head);

            // Operation complete.
            return;
        }

        // If the key already exists in the map.
        // Get the node where the key currently resides.
        Node oldNode = map.get(key);
        // Remove the key from its current frequency set.
        oldNode.keys.remove(key);
        // Store the old frequency.
        int oldFreq = oldNode.freq;

        // Get the next node in the linked list.
        Node nextNode = oldNode.next;
        // Check if a node for the next frequency (oldFreq + 1) already exists.
        // It exists if nextNode is not null AND its frequency is indeed oldFreq + 1.
        if(nextNode==null || nextNode.freq != oldFreq+1) {
            // If no such node exists, create a new node for frequency oldFreq + 1.
            // This new node will be inserted after oldNode.
            nextNode = createAndAttach(key,oldFreq+1,oldNode,nextNode);
        }
        // If the newly created or found nextNode is the last node in the list, update tail.
        if(nextNode.next==null) tail=nextNode;

        // Add the key to the keys set of the next frequency node.
        nextNode.keys.add(key);
        // Update the map to point the key to this new node.
        map.put(key,nextNode);

        // If the old node's keys set is now empty, it means this frequency bucket is no longer needed.
        if(oldNode.keys.isEmpty()) {
            // Remove the old node from the linked list.
            removeNode(oldNode);
        }
    }

    // Decrements the count of the given key.
    public void dec(String key) {
        // Get the node where the key currently resides.
        Node oldNode = map.get(key);
        // Remove the key from its current frequency set.
        oldNode.keys.remove(key);
        // Store the old frequency.
        int oldFreq = oldNode.freq;

        // If the old frequency was 1, we are effectively removing the key from the structure.
        if(oldFreq==1){
            // Remove the key from the map.
            map.remove(key);
            // If the old node's keys set is now empty, remove the node itself.
            if(oldNode.keys.isEmpty()) removeNode(oldNode);
            // Operation complete.
            return;
        }

        // If the old frequency was greater than 1, we need to move the key to a lower frequency bucket.
        // Get the previous node in the linked list.
        Node prevNode = oldNode.prev;
        // Check if a node for the previous frequency (oldFreq - 1) already exists.
        // It exists if prevNode is not null AND its frequency is indeed oldFreq - 1.
        if(prevNode == null || prevNode.freq != oldFreq-1) {
            // If no such node exists, create a new node for frequency oldFreq - 1.
            // This new node will be inserted before oldNode.
            prevNode = createAndAttach(key,oldFreq-1,prevNode,oldNode);
        }
        // If the newly created or found prevNode is the first node in the list, update head.
        if(prevNode.prev==null) head=prevNode;

        // Add the key to the keys set of the previous frequency node.
        prevNode.keys.add(key);
        // Update the map to point the key to this new node.
        map.put(key,prevNode);

        // If the old node's keys set is now empty, it means this frequency bucket is no longer needed.
        if(oldNode.keys.isEmpty()) {
            // Remove the old node from the linked list.
            removeNode(oldNode);
        }
    }

    // Returns a key that has the maximum count.
    public String getMaxKey() {
        // If the structure is empty (tail is null), return an empty string.
        if(tail==null) return "";
        // Otherwise, return any key from the tail node's keys set.
        // The iterator().next() method gets the first element from the set.
        return tail.keys.iterator().next();
    }

    // Returns a key that has the minimum count.
    public String getMinKey() {
        // If the structure is empty (head is null), return an empty string.
        if(head==null) return "";
        // Otherwise, return any key from the head node's keys set.
        // The iterator().next() method gets the first element from the set.
        return head.keys.iterator().next();
    }

    // Helper method to remove a node from the doubly linked list.
    public void removeNode(Node node){
        // Case 1: The node to be removed is the only node in the list.
        if(node.prev==null && node.next==null){
            head = null; // Set head to null.
            tail=null; // Set tail to null.
            return; // Exit.
        }

        // Case 2: The node to be removed is the head.
        if(node.prev==null){
            node.next.prev = null; // The next node's previous pointer should be null.
            head = node.next; // Update head to the next node.
            node.next=null; // Detach the removed node.
            return; // Exit.
        }

        // Case 3: The node to be removed is the tail.
        if(node.next==null){
            node.prev.next = null; // The previous node's next pointer should be null.
            tail = node.prev; // Update tail to the previous node.
            node.prev=null; // Detach the removed node.
            return; // Exit.
        }

        // Case 4: The node to be removed is in the middle of the list.
        node.prev.next = node.next; // Link previous node to the next node.
        node.next.prev = node.prev; // Link next node to the previous node.
        node.prev = null; // Detach the removed node.
        node.next = null; // Detach the removed node.
    }

    // Helper method to create a new node and attach it into the linked list.
    // It handles linking the new node between 'prev' and 'next' pointers.
    public Node createAndAttach(String key,int freq, Node prev , Node next){
        // Create a new node with the specified frequency.
        Node temp = new Node(freq);
        // Add the given key to this new node's keys set.
        temp.keys.add(key);
        // Set the previous pointer of the new node.
        temp.prev = prev;
        // Set the next pointer of the new node.
        temp.next = next;

        // If there is a 'next' node, update its 'prev' pointer to point to the new node.
        if(next!=null)next.prev = temp;
        // If there is a 'prev' node, update its 'next' pointer to point to the new node.
        if(prev!=null)prev.next = temp;

        // Return the newly created and attached node.
        return temp;
    }

    // Helper method for debugging: prints the linked list of frequency nodes.
    public void printList(){
        Node curr = head; // Start from the head.
        while(curr!=null){ // Traverse until the end of the list.
            System.out.print(curr.freq+": "+curr.keys+ " --> "); // Print frequency and keys.
            curr=curr.next; // Move to the next node.
        }
        System.out.println(); // Print a newline at the end.
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
*   **Clarify O(1) constraints:** Emphasize that "O(1)" here means average time complexity due to hash map operations. Worst-case hash collisions could degrade performance, but that's usually ignored in interviews unless specifically asked.
*   **Draw the data structure:** Before coding, draw the doubly linked list of frequency buckets and how keys move between them during `inc` and `dec`. This helps visualize the logic.
*   **Handle edge cases meticulously:** Pay close attention to `head`/`tail` updates, empty lists, and removing the last element from a bucket. Walk through examples like `inc("a")`, `inc("b")`, `dec("a")`, `inc("c")`, `getMaxKey()`.
*   **Explain the trade-offs:** Discuss why a simple hash map isn't enough and how the linked list structure addresses the O(1) min/max requirement.

## Revision Checklist
- [ ] Understand the problem requirements: O(1) for `inc`, `dec`, `getMaxKey`, `getMinKey`.
- [ ] Identify the need for a structure that groups keys by frequency and allows ordered access.
- [ ] Design the `Node` class: `freq`, `keys` (HashSet), `prev`, `next`.
- [ ] Design the main class: `map` (HashMap), `head`, `tail`.
- [ ] Implement `inc()`: handle new keys, existing keys, creating/finding next frequency nodes, updating `head`/`tail`, removing empty buckets.
- [ ] Implement `dec()`: handle `freq=1` case, `freq>1` case, creating/finding previous frequency nodes, updating `head`/`tail`, removing empty buckets.
- [ ] Implement `getMaxKey()` and `getMinKey()`: simple access to `tail` and `head`.
- [ ] Implement helper methods: `removeNode`, `createAndAttach`.
- [ ] Test edge cases: empty structure, single element, moving between buckets, removing last element.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LRU Cache (LeetCode 146) - Uses a doubly linked list and hash map for O(1) get/put.
*   LFU Cache (LeetCode 460) - Similar to LRU but based on frequency, often uses a doubly linked list of frequency lists.
*   Design Hit Counter (LeetCode 362) - Might involve frequency counting, though typically simpler.

## Tags
`Doubly Linked List` `Hash Map` `Design`
