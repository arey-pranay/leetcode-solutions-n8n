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
This problem asks us to implement a Least Frequently Used (LFU) cache. We need to evict the least frequently used item when the cache is full, and if there's a tie in frequency, evict the least recently used among them.

The solution uses a combination of a HashMap for key-value lookups and frequency tracking, and a Doubly Linked List (DLL) for each frequency to maintain the Least Recently Used (LRU) order within that frequency.

## Intuition
The core challenge is efficiently tracking both frequency and recency. A simple HashMap won't suffice because we need to know which item is least frequent and, among those, which was used least recently.

The "aha moment" comes from realizing that we can group items by their frequency. For each frequency, we can maintain a separate Doubly Linked List (DLL). This DLL will store nodes in the order they were accessed (most recently used at the tail, least recently used at the head).

When an item's frequency increases, we move it from its current frequency's DLL to the DLL of the next higher frequency. If a frequency's DLL becomes empty, and that frequency was the minimum frequency, we increment our `minFreq` tracker. This allows us to quickly identify the item to evict (the head of the `minFreq` DLL).

## Algorithm
1.  **Data Structures:**
    *   `capacity`: The maximum number of items the cache can hold.
    *   `minFreq`: An integer tracking the current minimum frequency of any item in the cache.
    *   `ref`: A `HashMap<Integer, Node>` to store a mapping from the key to its corresponding `Node`. This allows O(1) access to any node.
    *   `freqs`: A `HashMap<Integer, DLL>` to store a mapping from frequency to a `DLL`. Each `DLL` contains nodes with that specific frequency, ordered by recency.
    *   `Node` class: Represents a cache item with `key`, `value`, `count` (frequency), `next`, and `prev` pointers for the DLL.
    *   `DLL` class: Represents a Doubly Linked List for a specific frequency, with `head` and `tail` pointers.

2.  **Constructor `LFUCache(int capacity)`:**
    *   Initialize `capacity`.
    *   Initialize `minFreq` to 0.
    *   Initialize `ref` and `freqs` HashMaps.

3.  **`get(int key)`:**
    *   Check if `key` exists in `ref`. If not, return -1.
    *   Retrieve the `Node` from `ref`.
    *   Call `increment(node)` to update its frequency and move it to the appropriate DLL.
    *   Return the `node.value`.

4.  **`put(int key, int value)`:**
    *   **If `key` exists in `ref`:**
        *   Get the existing `Node`.
        *   Update its `value`.
        *   Call `increment(node)` to update its frequency.
    *   **If `key` does not exist:**
        *   **Eviction:** If `ref.size() == capacity`:
            *   Get the `DLL` corresponding to `minFreq` from `freqs`.
            *   Get the `Node` at the head of this `DLL` (this is the LFU and LRU item).
            *   Remove this `Node` from `ref`.
            *   Remove this `Node` from its `DLL` using `removeNodeFrom`.
        *   **Add New Node:**
            *   Set `minFreq` to 1 (newly added items always start with frequency 1).
            *   Create a new `Node` with the given `key` and `value`.
            *   Add the new `Node` to `ref`.
            *   If `freqs` does not contain a `DLL` for frequency 1, create a new `DLL` and put it in `freqs`.
            *   Add the new `Node` to the end of the `DLL` for frequency 1 using `putLastIn`.

5.  **`increment(Node x)`:**
    *   Get the `oldFreq` of the node `x`.
    *   Get the `DLL` (`oldList`) corresponding to `oldFreq` from `freqs`.
    *   Remove `x` from `oldList` using `removeNodeFrom`.
    *   **Update `minFreq`:** If `oldFreq` was `minFreq` and `oldList` is now empty (its `head` is null), increment `minFreq`.
    *   Increment `x.count`.
    *   Ensure a `DLL` exists for the new frequency (`x.count`) in `freqs`. If not, create one.
    *   Add `x` to the end of the `DLL` for its new frequency using `putLastIn`.

6.  **`removeNodeFrom(DLL list, Node x)`:**
    *   Handles removing a node `x` from a given `DLL` `list`.
    *   Updates `prev.next` and `next.prev` pointers to bypass `x`.
    *   Handles cases where `x` is the head or tail of the list.

7.  **`putLastIn(DLL list, Node x)`:**
    *   Handles adding a node `x` to the tail of a given `DLL` `list`.
    *   If the list is empty, `head` and `tail` both point to `x`.
    *   Otherwise, appends `x` after the current `tail` and updates `tail`.

## Concept to Remember
*   **Hash Maps:** For O(1) average time complexity for key lookups, insertions, and deletions.
*   **Doubly Linked Lists:** To maintain order (recency) within groups (frequencies) and allow O(1) insertion/deletion of nodes.
*   **Frequency Grouping:** Organizing data by frequency allows efficient identification of the least frequent items.
*   **Minimum Frequency Tracking:** Maintaining `minFreq` helps quickly locate the group from which to evict.

## Common Mistakes
*   **Incorrectly updating `minFreq`:** Forgetting to increment `minFreq` when a frequency group becomes empty and that frequency was the minimum.
*   **Handling edge cases in DLL operations:** Not properly managing `head` and `tail` pointers when removing or adding nodes, especially for empty lists or lists with a single element.
*   **Not removing nodes from `ref` during eviction:** Forgetting to remove the evicted node from the `ref` HashMap, leading to stale entries.
*   **Inefficient `increment` operation:** If `increment` doesn't correctly move the node between DLLs and update frequencies, the cache logic breaks.
*   **Confusing `key` and `value`:** Misplacing or misusing the `key` and `value` fields within the `Node` or HashMaps.

## Complexity Analysis
*   **Time:**
    *   `get(key)`: O(1) on average. Accessing `ref` is O(1). `increment` involves HashMap operations and DLL operations, all of which are O(1).
    *   `put(key, value)`: O(1) on average. Similar to `get`, operations on `ref`, `freqs`, and DLLs are O(1). Eviction also involves O(1) operations.
*   **Space:** O(N), where N is the capacity of the cache. This is because we store each of the N items in the `ref` HashMap, and potentially in the `freqs` HashMap and their associated DLLs.

## Commented Code
```java
class LFUCache {
    // Inner class to represent a node in the cache and in the Doubly Linked List
    class Node{
        int key,value,count; // key, value, and frequency (count) of the node
        Node next,prev; // pointers for the Doubly Linked List
        Node(int key, int value){ // constructor for Node
            this.key = key; // initialize key
            this.value=value; // initialize value
            this.count = 1; // initialize frequency to 1 (newly added nodes)
        }
    }

    // Inner class to represent a Doubly Linked List for a specific frequency
    class DLL{
        Node head, tail; // head and tail pointers of the DLL
        DLL(){ // constructor for DLL
            this.head = null; // initialize head to null
            this.tail = null; // initialize tail to null
        }
    }

    int capacity; // maximum capacity of the cache
    int minFreq; // tracks the minimum frequency currently present in the cache
    HashMap<Integer, Node> ref; // HashMap to map keys to their corresponding Nodes (for O(1) access)
    HashMap<Integer, DLL> freqs; // HashMap to map frequencies to their corresponding DLLs

    // Constructor for LFUCache
    public LFUCache(int capacity) {
        this.capacity = capacity; // set the cache capacity
        this.minFreq = 0; // initialize minFreq to 0
        this.ref = new HashMap<>(); // initialize the reference map
        this.freqs = new HashMap<>(); // initialize the frequency map
    }

    // Get the value of a key from the cache
    public int get(int key) {
        if(!ref.containsKey(key)) return -1; // if key is not in the cache, return -1

        Node old = ref.get(key); // get the node associated with the key
        increment(old); // increment the frequency of the node
        return old.value; // return the value of the node
    }

    // Put a key-value pair into the cache
    public void put(int key, int value) {
        if(ref.containsKey(key)){ // if the key already exists in the cache
            Node old = ref.get(key); // get the existing node
            old.value = value; // update its value
            increment(old); // increment its frequency
        } else { // if the key does not exist
            if(ref.size()==capacity){ // if the cache is full
                DLL minList = freqs.get(minFreq); // get the DLL for the minimum frequency
                Node toRemove = minList.head; // the node to remove is the head of this DLL (LFU and LRU)
                
                ref.remove(toRemove.key); // remove the node from the reference map
                removeNodeFrom(minList,toRemove); // remove the node from its DLL
            }
            minFreq = 1; // reset minFreq to 1 for the new node
            
            Node temp = new Node(key,value); // create a new node
            ref.put(key,temp); // add the new node to the reference map
            
            // ensure a DLL exists for frequency 1, create if not
            freqs.putIfAbsent(1, new DLL()); 
            // add the new node to the end of the DLL for frequency 1
            putLastIn(freqs.get(1),temp); 
        }
    }

    // Helper method to remove a node from a Doubly Linked List
    public void removeNodeFrom(DLL list,Node x){
        if(x.prev!=null)x.prev.next = x.next; // if node has a previous, link previous to next
        else list.head = x.next; // if node is head, update list's head
        
        if(x.next!=null)x.next.prev = x.prev; // if node has a next, link next to previous
        else list.tail = x.prev; // if node is tail, update list's tail
    }

    // Helper method to add a node to the end (tail) of a Doubly Linked List
    public void putLastIn(DLL list,Node x){
        x.next = null; // new node's next is always null as it's the last
        if(list.tail == null){ // if the list is empty
            list.head = list.tail = x; // head and tail both point to the new node
            x.prev = null; // new node's prev is null
        } else{ // if the list is not empty
            list.tail.next = x; // current tail's next points to the new node
            x.prev = list.tail; // new node's prev points to the current tail
            list.tail = list.tail.next; // update list's tail to the new node
        }
    }

    // Helper method to increment the frequency of a node and move it to the correct DLL
    public void increment(Node x){
        int oldFreq = x.count; // get the current frequency of the node
        DLL oldList = freqs.get(oldFreq); // get the DLL for the old frequency
        removeNodeFrom(oldList,x); // remove the node from its old DLL

        // if the old frequency was the minimum and its list is now empty, increment minFreq
        if(oldFreq==minFreq && oldList.head == null) minFreq++; 
        
        freqs.putIfAbsent(++x.count, new DLL()); // increment node's frequency and ensure a DLL exists for it
        putLastIn(freqs.get(x.count),x); // add the node to the end of the DLL for its new frequency
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
1.  **Explain the two-tiered approach:** Clearly articulate why a single HashMap isn't enough and how the combination of `ref` (for O(1) key access) and `freqs` (for frequency grouping and LRU within frequency) is crucial.
2.  **Walk through `increment` and `put` with an example:** Use a small capacity (e.g., 2) and a sequence of `put` and `get` operations to demonstrate how nodes move between DLLs and how `minFreq` is updated. This is where most of the logic resides.
3.  **Discuss edge cases for DLL operations:** Be prepared to explain how `removeNodeFrom` and `putLastIn` handle empty lists, single-node lists, and removing/adding at the head/tail.
4.  **Clarify the role of `minFreq`:** Emphasize that `minFreq` is not just a counter but a pointer to the *group* from which eviction should occur. Explain the condition for incrementing it.

## Revision Checklist
- [ ] Understand the LFU eviction policy (least frequent, then least recently used).
- [ ] Design the `Node` structure with key, value, frequency, and DLL pointers.
- [ ] Design the `DLL` structure with head and tail pointers.
- [ ] Implement `ref` HashMap for O(1) node lookup by key.
- [ ] Implement `freqs` HashMap to map frequency to a `DLL`.
- [ ] Implement `minFreq` to track the current minimum frequency.
- [ ] Implement `get` operation: lookup, increment frequency, update DLLs.
- [ ] Implement `put` operation: handle existing keys (update value, increment frequency) and new keys (evict if full, add new node, set frequency to 1).
- [ ] Implement `increment` helper: remove from old DLL, add to new DLL, update `minFreq` if necessary.
- [ ] Implement `removeNodeFrom` helper for DLL node removal.
- [ ] Implement `putLastIn` helper for DLL node addition to tail.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases: empty cache, full cache, single-element DLLs.

## Similar Problems
*   LRU Cache
*   Design Hit Counter
*   All O(1) Data Structures

## Tags
`Hash Map` `Doubly Linked List` `Design`
