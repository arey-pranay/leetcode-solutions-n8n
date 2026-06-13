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
The solution involves using hash maps to store key-value pairs and frequency-linked lists to manage eviction.

## Intuition
The core idea is to track not only the key-value pairs but also their access frequencies. When the cache is full and a new item needs to be added, we must evict the item that has been accessed the least frequently. If there's a tie in frequency, we evict the least recently used (LRU) among those with the minimum frequency.

To efficiently manage this, we can use two hash maps:
1. `ref`: Maps a key to its corresponding `Node`. This allows O(1) access to any node.
2. `freqs`: Maps a frequency count to a Doubly Linked List (DLL) of nodes that have that frequency. This allows us to quickly find all nodes with a specific frequency.

We also need to maintain `minFreq`, the minimum frequency currently present in the cache. This helps us identify the list from which to evict when the cache is full.

The `Node` class will store the key, value, frequency count, and pointers for the DLL. The `DLL` class will manage a list of nodes with the same frequency, using `head` and `tail` pointers.

When an item is accessed (`get` or `put` for an existing key), its frequency increases. We need to move it from its current frequency's DLL to the DLL of the next higher frequency. If its old frequency list becomes empty and it was the `minFreq`, we increment `minFreq`.

When adding a new item (`put` for a new key) and the cache is full, we evict the head of the `minFreq` DLL.

## Algorithm
1.  **Data Structures**:
    *   `Node` class: `key`, `value`, `count` (frequency), `prev`, `next` (for DLL).
    *   `DLL` class: `head`, `tail` (pointers to `Node`s).
    *   `capacity`: Maximum size of the cache.
    *   `minFreq`: Current minimum frequency in the cache.
    *   `ref`: `HashMap<Integer, Node>` mapping keys to their nodes.
    *   `freqs`: `HashMap<Integer, DLL>` mapping frequency counts to their respective DLLs.

2.  **Constructor `LFUCache(int capacity)`**:
    *   Initialize `capacity`.
    *   Initialize `minFreq` to 0.
    *   Initialize `ref` and `freqs` hash maps.

3.  **`get(int key)`**:
    *   If `key` is not in `ref`, return -1.
    *   Get the `Node` from `ref`.
    *   Call `increment(node)` to update its frequency and move it to the appropriate DLL.
    *   Return the `node.value`.

4.  **`put(int key, int value)`**:
    *   If `capacity` is 0, do nothing.
    *   If `key` is already in `ref`:
        *   Get the existing `Node`.
        *   Update its `value`.
        *   Call `increment(node)`.
    *   If `key` is not in `ref`:
        *   If `ref.size() == capacity` (cache is full):
            *   Get the `DLL` for `minFreq` from `freqs`.
            *   Get the `Node` at the head of this `minFreq` DLL (this is the LFU/LRU node).
            *   Remove this node from `ref`.
            *   Remove this node from its `minFreq` DLL using `removeNodeFrom`.
        *   Set `minFreq` to 1 (newly added items always start with frequency 1).
        *   Create a new `Node` with the given `key` and `value`.
        *   Add the new `Node` to `ref`.
        *   If `freqs` does not contain key 1, create a new `DLL` for frequency 1.
        *   Add the new `Node` to the end of the DLL for frequency 1 using `putLastIn`.

5.  **`increment(Node x)`**:
    *   Get the `oldFreq` of the node `x`.
    *   Get the `DLL` corresponding to `oldFreq` from `freqs`.
    *   Remove `x` from its `oldList` using `removeNodeFrom`.
    *   If `oldFreq` was `minFreq` AND `oldList` is now empty (its `head` is null), increment `minFreq`.
    *   Increment `x.count`.
    *   Get the `newFreq` (`x.count`).
    *   Use `freqs.putIfAbsent(newFreq, new DLL())` to ensure a DLL exists for the `newFreq`.
    *   Add `x` to the end of the `DLL` for `newFreq` using `putLastIn`.

6.  **`removeNodeFrom(DLL list, Node x)`**:
    *   Handles removing a node `x` from a given `DLL` `list`.
    *   Updates `prev.next` and `next.prev` pointers.
    *   Updates `list.head` and `list.tail` if `x` was the head or tail.

7.  **`putLastIn(DLL list, Node x)`**:
    *   Handles adding a node `x` to the end of a given `DLL` `list`.
    *   If the list is empty, `head` and `tail` both point to `x`.
    *   Otherwise, appends `x` after the current `tail` and updates `tail`.

## Concept to Remember
*   **Hash Maps**: For O(1) average time complexity lookups, insertions, and deletions of key-value pairs. Used for `ref` (key to Node) and `freqs` (frequency to DLL).
*   **Doubly Linked Lists (DLLs)**: For O(1) insertion and deletion of nodes. Used within `freqs` to maintain order of nodes with the same frequency (LRU within frequency).
*   **Frequency Tracking**: Maintaining counts of how many times each item has been accessed.
*   **Eviction Policy (LFU + LRU tie-breaker)**: The core logic of removing the least frequently used item, and if there's a tie, removing the least recently used among them.

## Common Mistakes
*   **Incorrectly updating `minFreq`**: Forgetting to increment `minFreq` when the current `minFreq` list becomes empty after removing a node.
*   **DLL manipulation errors**: Bugs in `removeNodeFrom` or `putLastIn` leading to broken links or incorrect head/tail pointers.
*   **Handling edge cases**: Not properly managing empty lists, capacity 0, or the first insertion into a frequency list.
*   **Forgetting to update `ref`**: When evicting a node, failing to remove it from the `ref` map.
*   **Complexity of `increment`**: Ensuring that moving a node between DLLs and updating frequency maps is done efficiently.

## Complexity Analysis
*   **Time**:
    *   `get(key)`: O(1) - Hash map lookups and DLL operations are O(1).
    *   `put(key, value)`: O(1) - Hash map operations and DLL operations are O(1).
*   **Space**: O(N) - Where N is the number of items in the cache. This is due to storing each key-value pair in `ref` and potentially distributing them across various DLLs in `freqs`.

## Commented Code
```java
class LFUCache {
    // Inner class to represent a node in the cache.
    // Each node stores its key, value, frequency count, and pointers for a doubly linked list.
    class Node{
        int key,value,count; // key, value, and frequency count of the node
        Node next,prev; // pointers for the doubly linked list
        // Constructor for Node
        Node(int key, int value){
            this.key = key; // initialize key
            this.value=value; // initialize value
            this.count = 1; // initialize frequency count to 1 (newly added nodes start with frequency 1)
        }
    }

    // Inner class to represent a Doubly Linked List (DLL).
    // This DLL will store nodes that have the same frequency.
    class DLL{
        Node head, tail; // head and tail pointers of the DLL
        // Constructor for DLL
        DLL(){
            this.head = null; // initialize head to null (empty list)
            this.tail = null; // initialize tail to null (empty list)
        }
    }

    int capacity; // maximum capacity of the cache
    int minFreq; // stores the minimum frequency currently present in the cache
    HashMap<Integer, Node> ref; // map to store key -> Node, for O(1) access to any node
    HashMap<Integer, DLL> freqs; // map to store frequency -> DLL, for O(1) access to nodes of a specific frequency

    // Constructor for LFUCache
    public LFUCache(int capacity) {
        this.capacity = capacity; // set the cache capacity
        this.minFreq = 0; // initialize minFreq to 0 (no elements yet)
        this.ref = new HashMap<>(); // initialize the reference map
        this.freqs = new HashMap<>(); // initialize the frequency map
    }

    // Get the value of a key from the cache.
    public int get(int key) {
        // if the key is not present in the cache, return -1
        if(!ref.containsKey(key)) return -1;

        // get the node associated with the key
        Node old = ref.get(key);
        // increment the frequency of the node and move it to the appropriate DLL
        increment(old);
        // return the value of the node
        return old.value;
    }

    // Put a key-value pair into the cache.
    public void put(int key, int value) {
        // if capacity is 0, we cannot add anything, so return
        if(capacity == 0) return;

        // if the key already exists in the cache
        if(ref.containsKey(key)){
            // get the existing node
            Node old = ref.get(key);
            // update its value
            old.value = value;
            // increment its frequency and move it to the appropriate DLL
            increment(old);
        } else { // if the key does not exist
            // if the cache is full
            if(ref.size()==capacity){
                // get the DLL corresponding to the minimum frequency
                DLL minList = freqs.get(minFreq);
                // get the node at the head of this list (this is the LFU/LRU node to be removed)
                Node toRemove = minList.head;

                // remove the node from the reference map
                ref.remove(toRemove.key);
                // remove the node from its current DLL
                removeNodeFrom(minList,toRemove);
            }
            // when a new item is added, its frequency is 1, so minFreq becomes 1
            minFreq = 1;

            // create a new node with the given key and value
            Node temp = new Node(key,value);
            // add the new node to the reference map
            ref.put(key,temp);
            // if there's no DLL for frequency 1 yet, create one
            if(!freqs.containsKey(1)) freqs.put(1,new DLL()) ;

            // add the new node to the end of the DLL for frequency 1
            putLastIn(freqs.get(1),temp);
        }
    }

    // Helper method to remove a node 'x' from a given DLL 'list'.
    public void removeNodeFrom(DLL list,Node x){
        // if the node has a previous node, update its next pointer
        if(x.prev!=null)x.prev.next = x.next;
        // if the node was the head, update the list's head
        else list.head = x.next;

        // if the node has a next node, update its previous pointer
        if(x.next!=null)x.next.prev = x.prev;
        // if the node was the tail, update the list's tail
        else list.tail = x.prev;
    }

    // Helper method to add a node 'x' to the end of a given DLL 'list'.
    public void putLastIn(DLL list,Node x){
        x.next = null; // the new node will be the last, so its next is null
        // if the list is empty
        if(list.tail == null){
            list.head = list.tail = x; // head and tail both point to the new node
            x.prev = null; // the new node has no previous node
        } else{ // if the list is not empty
            list.tail.next = x; // the current tail's next points to the new node
            x.prev = list.tail; // the new node's previous points to the current tail
            list.tail = list.tail.next; // update the list's tail to be the new node
        }
    }

    // Helper method to increment the frequency of a node 'x'.
    public void increment(Node x){
        int oldFreq = x.count; // store the current frequency
        DLL oldList = freqs.get(oldFreq); // get the DLL for the old frequency
        // remove the node from its old DLL
        removeNodeFrom(oldList,x);

        // if the old frequency was the minimum frequency AND its list is now empty,
        // it means the minimum frequency in the cache has increased.
        if(oldFreq==minFreq && oldList.head == null) minFreq++;

        // increment the node's frequency count
        freqs.putIfAbsent(++x.count, new DLL()); // ensure a DLL exists for the new frequency, create if not
        // add the node to the end of the DLL for its new frequency
        putLastIn(freqs.get(x.count),x);
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
1.  **Explain the Data Structures**: Clearly articulate why you chose two hash maps and doubly linked lists. Explain how `ref` provides fast access to nodes and `freqs` groups nodes by frequency.
2.  **Walk Through `increment`**: This is the most complex part. Explain step-by-step how a node moves from one frequency list to another, and how `minFreq` is updated. Use a small example if needed.
3.  **Handle Eviction Logic**: Detail how you identify the node to evict (head of `minFreq` DLL) and the steps involved in removing it from both `ref` and its DLL.
4.  **Edge Cases**: Be prepared to discuss edge cases like `capacity = 0`, an empty cache, or when the cache becomes full.

## Revision Checklist
- [ ] Understand the LFU eviction policy.
- [ ] Design the `Node` and `DLL` structures.
- [ ] Implement `ref` map for O(1) node access.
- [ ] Implement `freqs` map for O(1) frequency-based DLL access.
- [ ] Correctly implement `removeNodeFrom` for DLLs.
- [ ] Correctly implement `putLastIn` for DLLs.
- [ ] Implement `get` with frequency increment.
- [ ] Implement `put` for new and existing keys.
- [ ] Handle cache full condition and eviction.
- [ ] Accurately manage `minFreq`.
- [ ] Consider `capacity = 0` edge case.

## Similar Problems
*   LRU Cache
*   Design Hit Counter
*   All Oone Data Structure

## Tags
`Hash Map` `Doubly Linked List` `Design`
