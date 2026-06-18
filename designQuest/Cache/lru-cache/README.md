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

    public LRUCache(int capacity) {
        
    }
    
    public int get(int key) {
        
    }
    
    public void put(int key, int value) {
        
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
Implement an LRU (Least Recently Used) cache with a given capacity. Solve it by using a combination of a hash map and a doubly linked list.

## Intuition
The "aha moment" is to realize that we can use a hash map to store the keys and their corresponding values, and a doubly linked list to keep track of the order in which the elements are accessed. When a get or put operation is performed, we update the position of the key-value pair in the linked list.

## Algorithm
1. Initialize a hash map `cache` to store key-value pairs.
2. Initialize a doubly linked list `dll` to keep track of the order.
3. In the constructor, set the capacity of the cache and initialize the dll with dummy nodes.
4. In the get method:
	* Check if the key exists in the cache. If it does, move its corresponding node to the front of the dll and return the value.
	* If the key doesn't exist, return -1.
5. In the put method:
	* If the key already exists in the cache, update its value and move the node to the front of the dll.
	* If the key doesn't exist, add it to the cache with a new node at the end of the dll.
	* If the cache is full (i.e., size reaches capacity), remove the last element from the dll.

## Concept to Remember
* Hash Map: allows for efficient lookups and insertions.
* Doubly Linked List: enables efficient addition, removal, and movement of nodes.

## Common Mistakes
* Not initializing the dummy node correctly in the constructor.
* Failing to move the updated node to the front of the dll in the get method.
* Not removing the last element from the dll when the cache is full.

## Complexity Analysis
- Time: O(1) for get and put operations, assuming hash map lookups and insertions are constant time.
- Space: O(capacity) for storing key-value pairs in the hash map and the linked list.

## Commented Code
```java
class LRUCache {
    private int capacity;
    private Map<Integer, Node> cache;
    private Node head; // dummy node at the front of dll
    private Node tail; // dummy node at the end of dll

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head = new Node(); // initialize dummy nodes
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node); // move the node to the front of dll
            add(node);
            return node.value; // return the value associated with the key
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) { // update existing key-value pair
            Node node = cache.get(key);
            node.value = value;
            remove(node); // move the updated node to the front of dll
            add(node);
        } else { // insert new key-value pair
            Node node = new Node(key, value);
            cache.put(key, node);
            add(node);

            if (cache.size() > capacity) { // remove last element from dll
                Node lastNode = tail.prev;
                remove(lastNode); // remove the last element
                cache.remove(lastNode.key); // remove from hash map
            }
        }
    }

    private void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void add(Node node) {
        Node prevNode = head;
        node.prev = prevNode;
        node.next = head.next;
        head.next = node;
        head.next.prev = node;
    }
}
```

## Interview Tips
* Make sure to explain the data structures used and their implications.
* Practice edge cases, such as when the cache is full or a key-value pair is updated.
* Review the hash map and linked list operations to ensure they are implemented correctly.

## Revision Checklist
- [ ] Implement hash map lookups and insertions in get and put methods.
- [ ] Initialize dummy nodes correctly in constructor.
- [ ] Update node positions in dll after each operation.
- [ ] Remove last element from dll when cache is full.

## Similar Problems
* Design a data structure to store the most frequently used words in a stream of text.
* Implement a least frequently used (LFU) cache system.
