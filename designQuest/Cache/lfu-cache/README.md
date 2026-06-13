# Lfu Cache

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Design` `Doubly-Linked List`  
**Time:**   
**Space:** 

---

## Solution (java)

```java
class LFUCache {
    class Node{
        int key,value,count; 
        Node next,prev;
        Node(int key, int value){
            this.key = key;
            this.value=value;
            this.count = 1;
        }
    }
    class DLL{
        Node head, tail; 
        DLL(){this.head = null; this.tail = null;}
    }
    int capacity;
    int minFreq;
    HashMap<Integer, Node> ref;
    HashMap<Integer, DLL> freqs;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.ref = new HashMap<>();
        this.freqs = new HashMap<>();
    }
    public int get(int key) {
        if(!ref.containsKey(key)) return -1;
        Node old = ref.get(key);
        increment(old);
        return old.value;
    }
    public void put(int key, int value) {
        if(ref.containsKey(key)){
            Node old = ref.get(key);
            old.value = value;
            increment(old);
        } else {
            if(ref.size()==capacity){
                DLL minList = freqs.get(minFreq);
                Node toRemove = minList.head;
                
                ref.remove(toRemove.key);
                removeNodeFrom(minList,toRemove);
            }
            minFreq = 1; 
            
            Node temp = new Node(key,value);
            ref.put(key,temp); 
            if(!freqs.containsKey(1)) freqs.put(1,new DLL()) ;
    
            putLastIn(freqs.get(1),temp);
        }
    }
    public void removeNodeFrom(DLL list,Node x){
        if(x.prev!=null)x.prev.next = x.next;
        else list.head = x.next;
        
        if(x.next!=null)x.next.prev = x.prev;
        else list.tail = x.prev;
    }
    public void putLastIn(DLL list,Node x){
        x.next = null;
        if(list.tail == null){ 
            list.head = list.tail = x;
            x.prev = null;
        } else{ 
            list.tail.next = x;
            x.prev = list.tail;
            list.tail = list.tail.next; 
        }
    }
    public void increment(Node x){
        int oldFreq = x.count;
        DLL oldList = freqs.get(oldFreq);
        removeNodeFrom(oldList,x);
        if(oldFreq==minFreq && oldList.head == null) minFreq++;
        freqs.putIfAbsent(++x.count, new DLL());
        putLastIn(freqs.get(x.count),x);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 
   // 1 -> [{}, {}, {}, {}, {}]
    //      head            tail
         
    // 2 -> [{} <-> {} <-> {} <->{}]
    //      head               tail
 
     //     freq = {
        //         1 -> { }
        //         2 -> { }
        //     } 
        //     to ese 1 k andr bht saare honge , hum konse p put kr rhe hai ye kese figure our hoga
            
        // 1 ke andr DLL hi hai.
        // DLL jo hai wo sb nodes ko connect krti hai, head aur tail DLL me hai
        
        // Arey meri baat suno hnji
        // if(!freq.containsKey(1)) freq.put(1,putLastIn()) ;
        // toh tum putlastIn se dll m jo already node ban gya hai uske andr value daal rhe ?
        // likh rahi ho ? putLastIn 
        // smjh rha hu , mujhe dll khali kyon jaara pehli baar m vo ni smjha
        // DLL() ek empty list hai. fir humne us list me kaha put krdo.
        // {1,yes} ye hai freqs haato ab hum iss dll m value alg se daal rhe hai kyon ?
 
  // list null ni hai. usme 2 pointer hai head aur tail. wo dono null hai.
        // pr list m koi value kaha hai node ki , bss pointer h , node ka existence kaha gya smjh ni paarha mai 
        // Node ka kya existence. head = null hai, tail = null hai.to iska mtlb list bhi null hai yahi hua na
        
        
        
        // list ko assigned hai space, head aur tail ko nhi hai
        // smjhi fark ? , head aur tail list p hai agr to agr list ko assigned hai space to mtlb head aur tail ko bhi hona chahiye na , list kya hai humara ek dll ka node hai , uspe head aur tail point kr rhe hai bss
        
        // list ek dll ka object hai
        // jisme head aur tail hai ek node
        // ek head hai. ek tail hai. dono null hai kisi ko point nhi krre , to agr list ki dono cheezein null hai , to list bhi null nahi hui ?
        // meh, nhi
        // usko assign hui hai address 
        
        // achha to head aur tail bhi to uss space ka part hai. yes
        // to tumhara kehna ye pad rha ki space ka part hokr bhi vo null hai lekin list ko space h to vo null nahi h, ese kese
        // arey ek location assign hogi constructor call hote hi.
        // head aur tail bn gye hai uss list k liye humein bss ye pta hai, vo kaha pr pade hue h vo nahi ?? hena hena yahi na ??
        // hn us address pr do variables hai jo null hai
        // achha ek baat batao , hum null kisko kehte hai ?
        // lekin head tail to krte hai. nhi wo null hai. unko kuch assigned nhi hai. 0 space.
        // sirf DLL list ko assigned hai. uske andr ke variables ko ni.
        // aur ye kese pta chla humein , new keyword se ? 
        // null mnje koi value exist ni krti
        // shyd mere basics nahi clear h , baad m krte ye ques. m thoda kuchh smjha hu pr still nahi smjha

```

---

---
## Quick Revision
This problem asks to implement a Least Frequently Used (LFU) cache.
The solution involves using a hash map to store key-value pairs and another hash map to group nodes by their frequency, utilizing doubly linked lists for each frequency.

## Intuition
The core idea is to efficiently track both the frequency of access for each key and the recency of use within each frequency group. When the cache is full and a new item needs to be added, we evict the item that has the lowest frequency. If there's a tie in frequency, we evict the least recently used item among those with the lowest frequency.

To achieve this, we need:
1.  A way to quickly find any node by its key (for `get` and `put` updates). A `HashMap<Integer, Node>` (`ref`) is perfect for this.
2.  A way to group nodes by their frequency. A `HashMap<Integer, DLL>` (`freqs`) where the key is the frequency and the value is a Doubly Linked List (DLL) of nodes with that frequency.
3.  Within each frequency group (DLL), we need to maintain the order of usage. The least recently used item should be at the head of the DLL, and the most recently used at the tail. This allows for O(1) removal of the LFU item (from the head) and O(1) addition of a newly used item (to the tail).
4.  We also need to track the minimum frequency present in the cache (`minFreq`) to quickly identify which DLL to evict from.

The "aha moment" comes from realizing that a combination of a hash map for direct key access and a hash map of doubly linked lists (where each DLL represents a frequency group and maintains LRU order) can satisfy all the O(1) time complexity requirements for `get` and `put` operations.

## Algorithm
1.  **Data Structures:**
    *   `Node` class: Stores `key`, `value`, `count` (frequency), `prev`, and `next` pointers for the DLL.
    *   `DLL` class: Represents a Doubly Linked List with `head` and `tail` pointers.
    *   `capacity`: Maximum number of items in the cache.
    *   `minFreq`: The minimum frequency of any item currently in the cache.
    *   `ref`: `HashMap<Integer, Node>` to map keys to their corresponding `Node` objects for O(1) access.
    *   `freqs`: `HashMap<Integer, DLL>` to map frequencies to their respective Doubly Linked Lists.

2.  **Constructor `LFUCache(int capacity)`:**
    *   Initialize `capacity`.
    *   Initialize `minFreq` to 0.
    *   Initialize `ref` and `freqs` hash maps.

3.  **`get(int key)`:**
    *   Check if `key` exists in `ref`. If not, return -1.
    *   Get the `Node` object from `ref`.
    *   Call `increment(node)` to update its frequency and move it to the appropriate DLL.
    *   Return the `node.value`.

4.  **`put(int key, int value)`:**
    *   **If `key` exists in `ref`:**
        *   Get the existing `Node`.
        *   Update its `value`.
        *   Call `increment(node)` to update its frequency.
    *   **If `key` does not exist:**
        *   **Check capacity:** If `ref.size() == capacity`:
            *   Get the `DLL` for `minFreq` from `freqs`.
            *   Get the `Node` at the head of this `DLL` (this is the LFU, LRU item).
            *   Remove this `Node` from `ref`.
            *   Remove this `Node` from its `DLL` using `removeNodeFrom`.
        *   **Add new node:**
            *   Set `minFreq` to 1 (since a new item always starts with frequency 1).
            *   Create a new `Node` with the given `key` and `value`.
            *   Add the new `Node` to `ref`.
            *   If `freqs` does not contain a `DLL` for frequency 1, create a new `DLL` and put it in `freqs`.
            *   Add the new `Node` to the tail of the `DLL` for frequency 1 using `putLastIn`.

5.  **Helper Methods:**
    *   **`removeNodeFrom(DLL list, Node x)`:**
        *   Removes `Node x` from the given `DLL list`.
        *   Handles cases where `x` is the head, tail, or in the middle.
        *   Updates `head` and `tail` pointers of the `DLL` accordingly.
        *   If the `DLL` becomes empty after removal, it's implicitly handled by `head` and `tail` becoming null.

    *   **`putLastIn(DLL list, Node x)`:**
        *   Adds `Node x` to the tail of the given `DLL list`.
        *   Handles the case of an empty `DLL` (setting both `head` and `tail`).
        *   Updates `tail` to point to the new node.

    *   **`increment(Node x)`:**
        *   Gets the `oldFreq` of the node `x`.
        *   Gets the `oldList` (DLL) corresponding to `oldFreq` from `freqs`.
        *   Removes `x` from `oldList` using `removeNodeFrom`.
        *   **Crucial step:** If `oldFreq` was `minFreq` AND `oldList` is now empty (meaning all nodes with `minFreq` have been moved or evicted), increment `minFreq`.
        *   Increment `x.count`.
        *   Ensure a `DLL` exists for the new frequency (`x.count`) in `freqs` (using `putIfAbsent`).
        *   Add `x` to the tail of the `DLL` for its new frequency using `putLastIn`.

## Concept to Remember
*   **Doubly Linked Lists (DLLs):** Essential for O(1) insertion and deletion of nodes at arbitrary positions (specifically, head and tail for LRU/MRU tracking).
*   **Hash Maps:** Provide O(1) average time complexity for key-based lookups, insertions, and deletions, crucial for `ref` and `freqs`.
*   **Frequency Tracking:** Maintaining a count for each item and grouping items by frequency is the core of LFU.
*   **LRU within Frequency:** When multiple items share the same lowest frequency, the Least Recently Used one must be evicted. This is handled by the DLL's order.

## Common Mistakes
*   **Incorrectly updating `minFreq`:** Failing to increment `minFreq` when the last node of the current `minFreq` is removed from its DLL.
*   **Not handling empty DLLs:** When removing a node, if the DLL becomes empty, it needs to be managed correctly (e.g., potentially removed from `freqs` or its `head`/`tail` set to null). The provided solution implicitly handles this by checking `oldList.head == null`.
*   **O(N) operations:** Implementing `removeNodeFrom` or `putLastIn` in a way that iterates through the list instead of using direct pointer manipulation.
*   **Forgetting to update `ref`:** When evicting a node, it must be removed from the `ref` map as well.
*   **Incorrectly managing `head`/`tail` in `DLL`:** Off-by-one errors or incorrect pointer updates when adding/removing nodes, especially for edge cases like empty lists or single-node lists.

## Complexity Analysis
*   **Time Complexity:**
    *   `get(key)`: O(1) - Hash map lookups, DLL operations (remove, add to tail), and frequency map lookups are all O(1) on average.
    *   `put(key, value)`: O(1) - Similar to `get`, all operations are O(1) on average. The eviction process involves O(1) operations on the `minFreq` DLL.
*   **Space Complexity:**
    *   O(N) - Where N is the number of items in the cache.
        *   The `ref` hash map stores N nodes.
        *   The `freqs` hash map stores at most N DLLs (in the worst case, each node has a unique frequency).
        *   The DLLs collectively store N nodes.

## Commented Code
```java
class LFUCache {
    // Inner class to represent a node in the cache.
    // Each node stores its key, value, frequency count, and pointers for a doubly linked list.
    class Node{
        int key,value,count; // key: the key of the node, value: the value of the node, count: frequency of access
        Node next,prev; // next and prev pointers for the doubly linked list
        Node(int key, int value){ // Constructor for Node
            this.key = key; // Initialize key
            this.value=value; // Initialize value
            this.count = 1; // Initialize frequency count to 1 (newly added nodes start with frequency 1)
        }
    }

    // Inner class to represent a Doubly Linked List (DLL).
    // This DLL will store nodes of the same frequency.
    class DLL{
        Node head, tail; // head and tail pointers of the DLL
        DLL(){ // Constructor for DLL
            this.head = null; // Initialize head to null (empty list)
            this.tail = null; // Initialize tail to null (empty list)
        }
    }

    int capacity; // Maximum capacity of the cache
    int minFreq; // Tracks the minimum frequency currently present in the cache
    HashMap<Integer, Node> ref; // HashMap to map keys to their corresponding Node objects for O(1) access
    HashMap<Integer, DLL> freqs; // HashMap to map frequencies to their respective DLLs

    // Constructor for LFUCache
    public LFUCache(int capacity) {
        this.capacity = capacity; // Set the cache capacity
        this.minFreq = 0; // Initialize minFreq to 0 (no elements yet)
        this.ref = new HashMap<>(); // Initialize the reference map
        this.freqs = new HashMap<>(); // Initialize the frequency map
    }

    // Get the value associated with a key.
    public int get(int key) {
        if(!ref.containsKey(key)) return -1; // If key is not in the cache, return -1

        Node old = ref.get(key); // Get the node associated with the key
        increment(old); // Increment the frequency of this node and move it to the correct DLL
        return old.value; // Return the value of the node
    }

    // Put a key-value pair into the cache.
    public void put(int key, int value) {
        if(ref.containsKey(key)){ // If the key already exists in the cache
            Node old = ref.get(key); // Get the existing node
            old.value = value; // Update its value
            increment(old); // Increment its frequency
        } else { // If the key does not exist
            if(ref.size()==capacity){ // If the cache is full
                DLL minList = freqs.get(minFreq); // Get the DLL with the minimum frequency
                Node toRemove = minList.head; // The node to remove is the head of this DLL (LFU, LRU)
                
                ref.remove(toRemove.key); // Remove the node from the reference map
                removeNodeFrom(minList,toRemove); // Remove the node from its DLL
            }
            minFreq = 1; // A new node always starts with frequency 1, so minFreq becomes 1
            
            Node temp = new Node(key,value); // Create a new node
            ref.put(key,temp); // Add the new node to the reference map
            
            // If there's no DLL for frequency 1 yet, create one
            if(!freqs.containsKey(1)) freqs.put(1,new DLL()) ;
    
            putLastIn(freqs.get(1),temp); // Add the new node to the tail of the DLL for frequency 1
        }
    }

    // Helper method to remove a node from a DLL.
    public void removeNodeFrom(DLL list,Node x){
        if(x.prev!=null)x.prev.next = x.next; // If node has a previous node, link previous node's next to current node's next
        else list.head = x.next; // If node is the head, update the list's head
        
        if(x.next!=null)x.next.prev = x.prev; // If node has a next node, link next node's prev to current node's prev
        else list.tail = x.prev; // If node is the tail, update the list's tail
    }

    // Helper method to add a node to the tail of a DLL.
    public void putLastIn(DLL list,Node x){
        x.next = null; // The new node will be the last, so its next is null
        if(list.tail == null){ // If the list is empty
            list.head = list.tail = x; // The new node is both head and tail
            x.prev = null; // The new node has no previous
        } else{ // If the list is not empty
            list.tail.next = x; // Link the current tail's next to the new node
            x.prev = list.tail; // Link the new node's prev to the current tail
            list.tail = list.tail.next; // Update the list's tail to be the new node
        }
    }

    // Helper method to increment the frequency of a node.
    public void increment(Node x){
        int oldFreq = x.count; // Store the current frequency
        DLL oldList = freqs.get(oldFreq); // Get the DLL corresponding to the old frequency
        removeNodeFrom(oldList,x); // Remove the node from its old DLL

        // If the old frequency was the minimum frequency AND its DLL is now empty,
        // it means we need to increment minFreq because there are no more nodes with that frequency.
        if(oldFreq==minFreq && oldList.head == null) minFreq++;
        
        freqs.putIfAbsent(++x.count, new DLL()); // Increment the node's count and ensure a DLL exists for the new frequency
        putLastIn(freqs.get(x.count),x); // Add the node to the tail of the DLL for its new frequency
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
1.  **Explain the Data Structures First:** Before diving into the algorithm, clearly explain why you chose `HashMap` for `ref` and `freqs`, and why `DLL` is necessary for each frequency group. Emphasize the O(1) requirements.
2.  **Walk Through `increment`:** This is the most complex part. Explain step-by-step how a node moves from one frequency DLL to another, and critically, how `minFreq` is updated. Use a small example if needed.
3.  **Handle Edge Cases:** Be prepared to discuss what happens when the cache is empty, full, when a key is updated, when a key is evicted, and when a DLL becomes empty.
4.  **Complexity Justification:** Clearly articulate the time and space complexity for both `get` and `put` operations, linking them back to the chosen data structures.

## Revision Checklist
- [ ] Understand the LFU eviction policy.
- [ ] Design the `Node` structure with key, value, frequency, and pointers.
- [ ] Design the `DLL` structure with `head` and `tail`.
- [ ] Implement `ref` (`HashMap<Integer, Node>`) for O(1) key lookup.
- [ ] Implement `freqs` (`HashMap<Integer, DLL>`) to group nodes by frequency.
- [ ] Implement `minFreq` to track the lowest frequency.
- [ ] Implement `get` operation: lookup, increment frequency, update DLL.
- [ ] Implement `put` operation: handle existing key (update value, increment frequency) and new key (evict if full, add new node, set `minFreq` to 1).
- [ ] Implement `increment` helper: remove from old DLL, update frequency, add to new DLL, manage `minFreq`.
- [ ] Implement `removeNodeFrom` helper for DLL node removal.
- [ ] Implement `putLastIn` helper for DLL node addition to tail.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty cache, full cache, single node DLLs).

## Similar Problems
*   LRU Cache
*   Design HashMap
*   Design Doubly Linked List

## Tags
`Hash Map` `Doubly Linked List` `Design`
