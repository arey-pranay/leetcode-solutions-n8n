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
We solve it using a combination of a hash map for key-value lookups and a hash map of doubly linked lists to manage frequencies and eviction.

## Intuition
The core idea is to efficiently track both the frequency of access for each key and the recency of use within each frequency group. When the cache is full and we need to evict an item, we must remove the *least frequently used* item. If there's a tie in frequency, we evict the *least recently used* among those with the minimum frequency.

To achieve this, we need:
1.  A way to quickly find a node by its key (a hash map `ref`).
2.  A way to group nodes by their frequency. For each frequency, we need to maintain a list of nodes that have that frequency. Since we need to evict the least recently used within a frequency group, a Doubly Linked List (DLL) is ideal for each frequency.
3.  A way to quickly find the minimum frequency currently present in the cache.

The "aha moment" comes from realizing that we can use a hash map where keys are frequencies and values are DLLs. Each DLL will store nodes with that specific frequency, ordered by recency. When a node's frequency increases, we move it from its old frequency's DLL to the new frequency's DLL. We also need to track the `minFreq` to know which DLL to evict from.

## Algorithm
1.  **Data Structures:**
    *   `capacity`: The maximum number of items the cache can hold.
    *   `minFreq`: An integer tracking the current minimum frequency of any item in the cache.
    *   `ref`: A `HashMap<Integer, Node>` to map keys to their corresponding `Node` objects for O(1) access.
    *   `freqs`: A `HashMap<Integer, DLL>` where keys are frequencies and values are `DLL` objects. Each `DLL` stores `Node`s with that frequency, ordered by recency (most recently used at the tail).
    *   `Node` class: Represents a cache entry with `key`, `value`, `count` (frequency), `prev`, and `next` pointers for the DLL.
    *   `DLL` class: Represents a Doubly Linked List to store nodes of a particular frequency. It has `head` and `tail` pointers.

2.  **Constructor `LFUCache(int capacity)`:**
    *   Initialize `capacity`.
    *   Initialize `minFreq` to 0.
    *   Initialize `ref` and `freqs` hash maps.

3.  **`get(int key)`:**
    *   Check if `key` exists in `ref`. If not, return -1.
    *   If it exists, retrieve the `Node` from `ref`.
    *   Call `increment(Node)` to update its frequency and move it to the appropriate DLL.
    *   Return the `Node`'s `value`.

4.  **`put(int key, int value)`:**
    *   **If `key` already exists in `ref`:**
        *   Retrieve the existing `Node`.
        *   Update its `value`.
        *   Call `increment(Node)` to update its frequency.
    *   **If `key` does not exist:**
        *   **Check capacity:** If `ref.size() == capacity`:
            *   Get the `DLL` corresponding to `minFreq` from `freqs`.
            *   Get the `head` node from this `DLL` (this is the LFU and LRU node to evict).
            *   Remove this node from `ref`.
            *   Remove this node from its `DLL` using `removeNodeFrom`.
            *   If the `DLL` for `minFreq` becomes empty after removal, we don't need to explicitly remove it from `freqs` as it will be overwritten or ignored later.
        *   **Add new node:**
            *   Set `minFreq` to 1 (since this is a new item, its frequency starts at 1).
            *   Create a new `Node` with the given `key` and `value`.
            *   Add the new `Node` to `ref`.
            *   Get or create the `DLL` for frequency 1 in `freqs`.
            *   Add the new `Node` to the tail of this `DLL` using `putLastIn`.

5.  **Helper Method `increment(Node x)`:**
    *   Get the `oldFreq` of the node `x`.
    *   Get the `DLL` (`oldList`) associated with `oldFreq` from `freqs`.
    *   Remove `x` from `oldList` using `removeNodeFrom`.
    *   **Update `minFreq`:** If `oldFreq` was equal to `minFreq` AND `oldList` is now empty (meaning no nodes have `minFreq` anymore), increment `minFreq`.
    *   Increment `x.count`.
    *   Get or create the `DLL` for the new frequency (`x.count`) in `freqs`.
    *   Add `x` to the tail of this new frequency's `DLL` using `putLastIn`.

6.  **Helper Method `removeNodeFrom(DLL list, Node x)`:**
    *   Handles removing a node `x` from a `DLL` `list`.
    *   Updates `prev` and `next` pointers of adjacent nodes.
    *   Updates `list.head` and `list.tail` if `x` was the head or tail.

7.  **Helper Method `putLastIn(DLL list, Node x)`:**
    *   Handles adding a node `x` to the tail of a `DLL` `list`.
    *   If the list is empty, `x` becomes both `head` and `tail`.
    *   Otherwise, appends `x` to the current `tail` and updates `tail`.

## Concept to Remember
*   **Least Frequently Used (LFU) Eviction Policy:** Prioritize removing items that have been accessed the fewest times.
*   **Tie-breaking with Least Recently Used (LRU):** When multiple items share the minimum frequency, evict the one that hasn't been accessed for the longest time.
*   **Doubly Linked Lists (DLLs):** Essential for maintaining order within frequency groups and enabling O(1) insertion/deletion of nodes.
*   **Hash Maps:** Crucial for O(1) average time complexity for key lookups (`ref`) and for mapping frequencies to their respective DLLs (`freqs`).

## Common Mistakes
*   **Incorrectly updating `minFreq`:** Forgetting to increment `minFreq` when the last node of the current `minFreq` is removed, or incorrectly incrementing it when other nodes still exist at that frequency.
*   **Handling empty DLLs:** Not properly managing the `head` and `tail` pointers of DLLs when nodes are added or removed, especially when a DLL becomes empty or has only one element.
*   **Order of operations in `increment`:** Removing the node from the old frequency list *before* updating its frequency and adding it to the new list is critical.
*   **Capacity check and eviction logic:** Ensuring that eviction happens *before* adding a new item when the cache is full, and correctly identifying the node to evict (LFU and LRU).
*   **Edge cases for `putLastIn` and `removeNodeFrom`:** Not handling cases where the list is empty, or the node being removed is the head or tail.

## Complexity Analysis
*   **Time:**
    *   `get(key)`: O(1) on average. This involves hash map lookups and operations on DLLs (remove, add), all of which are O(1).
    *   `put(key, value)`: O(1) on average. Similar to `get`, it involves hash map operations and DLL manipulations.
*   **Space:** O(N), where N is the capacity of the cache. This is because we store each key-value pair in the `ref` hash map, and potentially all nodes are distributed across various DLLs in the `freqs` hash map.

## Commented Code
```java
class LFUCache {
    // Inner class to represent a node in the cache.
    // Each node stores its key, value, frequency count, and pointers for a doubly linked list.
    class Node{
        int key,value,count; // key: the key of the cache entry, value: the value of the cache entry, count: frequency of access
        Node next,prev; // pointers for the doubly linked list
        Node(int key, int value){ // Constructor for Node
            this.key = key; // Initialize key
            this.value=value; // Initialize value
            this.count = 1; // Initialize frequency count to 1 for a new node
        }
    }

    // Inner class to represent a Doubly Linked List (DLL).
    // This DLL will store nodes of a specific frequency, ordered by recency.
    class DLL{
        Node head, tail; // head and tail pointers of the DLL
        DLL(){ // Constructor for DLL
            this.head = null; // Initialize head to null
            this.tail = null; // Initialize tail to null
        }
    }

    int capacity; // Maximum capacity of the cache
    int minFreq; // Tracks the minimum frequency currently present in the cache
    HashMap<Integer, Node> ref; // HashMap to map keys to their corresponding Node objects for O(1) access
    HashMap<Integer, DLL> freqs; // HashMap to map frequencies to their corresponding DLLs

    // Constructor for LFUCache
    public LFUCache(int capacity) {
        this.capacity = capacity; // Set the cache capacity
        this.minFreq = 0; // Initialize minFreq to 0 (no elements initially)
        this.ref = new HashMap<>(); // Initialize the reference map
        this.freqs = new HashMap<>(); // Initialize the frequency map
    }

    // Get the value of the key if the key exists, otherwise return -1.
    public int get(int key) {
        if(!ref.containsKey(key)) return -1; // If key is not in the cache, return -1

        Node old = ref.get(key); // Get the node associated with the key
        increment(old); // Increment the frequency of this node and update its position
        return old.value; // Return the value of the node
    }

    // Put a key-value pair into the cache.
    public void put(int key, int value) {
        if(ref.containsKey(key)){ // If the key already exists in the cache
            Node old = ref.get(key); // Get the existing node
            old.value = value; // Update its value
            increment(old); // Increment its frequency and update its position
        } else { // If the key does not exist in the cache
            if(ref.size()==capacity){ // If the cache is full
                DLL minList = freqs.get(minFreq); // Get the DLL for the minimum frequency
                Node toRemove = minList.head; // The node to remove is the head of the minFreq DLL (LFU and LRU)
                
                ref.remove(toRemove.key); // Remove the node from the reference map
                removeNodeFrom(minList,toRemove); // Remove the node from its DLL
                // Note: If minList becomes empty, it will be handled implicitly by future operations or garbage collection.
            }
            minFreq = 1; // A new item always starts with frequency 1
            
            Node temp = new Node(key,value); // Create a new node for the new key-value pair
            ref.put(key,temp); // Add the new node to the reference map
            
            // Ensure there's a DLL for frequency 1. If not, create one.
            freqs.putIfAbsent(1, new DLL()); 
            // Add the new node to the end of the DLL for frequency 1.
            putLastIn(freqs.get(1),temp);
        }
    }

    // Helper method to remove a node 'x' from a given DLL 'list'.
    public void removeNodeFrom(DLL list,Node x){
        // If the node has a previous node, link its previous node to the next node.
        if(x.prev!=null)x.prev.next = x.next;
        else list.head = x.next; // If it's the head, update the list's head.
        
        // If the node has a next node, link its next node to the previous node.
        if(x.next!=null)x.next.prev = x.prev;
        else list.tail = x.prev; // If it's the tail, update the list's tail.
    }

    // Helper method to add a node 'x' to the end (tail) of a given DLL 'list'.
    public void putLastIn(DLL list,Node x){
        x.next = null; // The new node will be the last, so its next pointer is null.
        if(list.tail == null){ // If the list is currently empty
            list.head = list.tail = x; // The new node becomes both head and tail.
            x.prev = null; // The new node has no previous node.
        } else{ // If the list is not empty
            list.tail.next = x; // Link the current tail's next pointer to the new node.
            x.prev = list.tail; // Link the new node's previous pointer to the current tail.
            list.tail = list.tail.next; // Update the list's tail to be the new node.
        }
    }

    // Helper method to increment the frequency of a node 'x'.
    public void increment(Node x){
        int oldFreq = x.count; // Store the current frequency of the node.
        DLL oldList = freqs.get(oldFreq); // Get the DLL associated with the old frequency.
        removeNodeFrom(oldList,x); // Remove the node from its old frequency's DLL.

        // If the old frequency was the minimum frequency AND its DLL is now empty,
        // it means we need to update minFreq to the next higher frequency.
        if(oldFreq==minFreq && oldList.head == null) minFreq++;
        
        freqs.putIfAbsent(++x.count, new DLL()); // Increment the node's count and ensure a DLL exists for this new frequency.
        putLastIn(freqs.get(x.count),x); // Add the node to the end of the DLL for its new frequency.
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
1.  **Explain the Data Structures First:** Before diving into the algorithm, clearly explain the purpose of `ref` (key-to-node mapping) and `freqs` (frequency-to-DLL mapping), and why DLLs are used for each frequency.
2.  **Walk Through `get` and `put` with an Example:** Use a small capacity (e.g., 2) and a sequence of `put` and `get` operations to demonstrate how `minFreq` changes, how nodes move between DLLs, and how eviction works.
3.  **Focus on Edge Cases:** Be prepared to discuss how your code handles:
    *   Cache being full during `put`.
    *   Evicting the last node of `minFreq`.
    *   Adding the very first node.
    *   Updating an existing node's value.
    *   `get`ting a non-existent key.
4.  **Clarify `minFreq` Logic:** The logic for updating `minFreq` is often tricky. Be ready to explain precisely when and why `minFreq` is incremented.

## Revision Checklist
- [ ] Understand the LFU eviction policy.
- [ ] Understand the LRU tie-breaking rule.
- [ ] Implement `Node` and `DLL` classes correctly.
- [ ] Use `HashMap<Integer, Node>` for O(1) key lookup.
- [ ] Use `HashMap<Integer, DLL>` to group nodes by frequency.
- [ ] Implement `get` method: lookup, increment frequency, update position.
- [ ] Implement `put` method: handle existing keys, handle new keys, capacity check, eviction.
- [ ] Implement `increment` helper: remove from old list, update frequency, add to new list, manage `minFreq`.
- [ ] Implement `removeNodeFrom` helper: handle head, tail, and middle node removals.
- [ ] Implement `putLastIn` helper: handle empty list and non-empty list additions.
- [ ] Analyze time and space complexity.
- [ ] Test with edge cases (full cache, empty cache, single element cache).

## Similar Problems
*   LRU Cache (LeetCode 146)
*   Design Hit Counter (LeetCode 362)
*   All Oone Data Structure (LeetCode 432)

## Tags
`Hash Map` `Doubly-Linked List` `Design`
