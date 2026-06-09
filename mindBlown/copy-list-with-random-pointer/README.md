# Copy List With Random Pointer

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `Linked List`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node,Node> oldToNew = new HashMap<>();
        Node curr = head;
        while(curr!=null){
            oldToNew.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr = head;
        while(curr != null){
            oldToNew.get(curr).next = oldToNew.get(curr.next);
            oldToNew.get(curr).random = oldToNew.get(curr.random);
            curr = curr.next;
        }
        return oldToNew.get(head);
    } 
    // public Node copyRandomList(Node head) {
    //     Node root = head;
    //     while(head!=null){
    //         Node copy = new Node(head.val);
    //         Node near = head.next;
    //         head.next = copy;
    //         copy.next = near;
    //         head = near;
    //         near = near.next;
    //     }
    // }
}
```

---

---
## Quick Revision
This problem asks to create a deep copy of a linked list where each node can also point to any node in the list (or null) via a `random` pointer. The solution involves mapping original nodes to their newly created copies to correctly set both `next` and `random` pointers.

## Intuition
The core challenge is handling the `random` pointers. If we create new nodes first and then try to set their `random` pointers, we'll run into a problem: the target node for the `random` pointer might not have been created yet, or we won't have an easy way to find its corresponding new node. The "aha moment" is realizing that we need a way to associate each original node with its newly created copy. A hash map is a natural fit for this mapping.

## Algorithm
1. **Create Copies and Map:** Iterate through the original linked list. For each node, create a new node with the same value. Store a mapping from the original node to its new copy in a hash map.
2. **Assign Pointers:** Iterate through the original linked list again. For each original node:
    * Get its corresponding new node from the hash map.
    * Set the `next` pointer of the new node to the new node corresponding to the original node's `next` pointer (obtained from the hash map).
    * Set the `random` pointer of the new node to the new node corresponding to the original node's `random` pointer (obtained from the hash map).
3. **Return Head of Copy:** Return the new node corresponding to the original head node from the hash map.

## Concept to Remember
*   **Deep Copy:** Creating an entirely new structure with new objects, not just copying references.
*   **Hash Maps (Dictionaries):** Efficient key-value storage for lookups, crucial for mapping original nodes to their copies.
*   **Linked List Traversal:** Iterating through nodes using `next` pointers.
*   **Handling Cycles/Interdependencies:** The `random` pointers can create complex relationships, requiring careful pointer assignment.

## Common Mistakes
*   **Shallow Copy:** Accidentally copying references instead of creating new nodes, leading to the copied list being modified when the original is.
*   **Null Pointer Exceptions:** Not handling cases where `next` or `random` pointers are `null` when trying to access their corresponding new nodes in the map.
*   **Incorrect Mapping:** Failing to correctly associate original nodes with their new counterparts, especially when setting `random` pointers.
*   **Inefficient Iteration:** Performing more than two passes when a two-pass approach with a hash map is sufficient.

## Complexity Analysis
*   **Time:** O(N) - We iterate through the linked list twice. The hash map operations (put and get) take average O(1) time.
*   **Space:** O(N) - We use a hash map to store the mapping between N original nodes and N new nodes.

## Commented Code
```java
/*
// Definition for a Node.
class Node {
    int val; // The value of the node.
    Node next; // Pointer to the next node in the list.
    Node random; // Pointer to any node in the list, or null.

    public Node(int val) {
        this.val = val; // Initialize the node's value.
        this.next = null; // Initialize next pointer to null.
        this.random = null; // Initialize random pointer to null.
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // Create a HashMap to store the mapping from original nodes to their new copies.
        // Key: Original Node, Value: New Copied Node.
        HashMap<Node,Node> oldToNew = new HashMap<>();
        
        // Initialize a pointer to traverse the original list.
        Node curr = head;
        
        // First pass: Create a copy of each node and populate the HashMap.
        while(curr!=null){
            // For each original node, create a new node with the same value.
            // Put the original node and its new copy into the HashMap.
            oldToNew.put(curr, new Node(curr.val));
            // Move to the next node in the original list.
            curr = curr.next;
        }
        
        // Reset the current pointer to the head of the original list for the second pass.
        curr = head;
        
        // Second pass: Assign the next and random pointers for the new nodes.
        while(curr != null){
            // Get the new node corresponding to the current original node.
            // Set its 'next' pointer:
            // The 'next' of the new node should point to the new node that corresponds to the original node's 'next'.
            // We retrieve this new node from the HashMap using the original node's 'next' as the key.
            oldToNew.get(curr).next = oldToNew.get(curr.next);
            
            // Set its 'random' pointer:
            // The 'random' of the new node should point to the new node that corresponds to the original node's 'random'.
            // We retrieve this new node from the HashMap using the original node's 'random' as the key.
            oldToNew.get(curr).random = oldToNew.get(curr.random);
            
            // Move to the next node in the original list.
            curr = curr.next;
        }
        
        // The head of the copied list is the new node corresponding to the original head.
        // Retrieve it from the HashMap.
        return oldToNew.get(head);
    } 
}
```

## Interview Tips
*   **Explain the Two-Pass Approach:** Clearly articulate why two passes are necessary: one to create nodes and map them, and another to set the pointers using the map.
*   **Handle Edge Cases:** Mention how the code handles an empty list (`head == null`) gracefully because the loops won't execute, and `oldToNew.get(null)` would return `null` if `head` was `null` (though `oldToNew.get(head)` would correctly return `null` if `head` is `null` and the map is empty).
*   **Discuss Space Optimization (Optional but good):** If asked about space complexity, mention the O(N) space used by the hash map. You can then briefly discuss an alternative O(1) space solution that interweaves the copied nodes with the original list (though this is more complex to implement and explain).
*   **Clarify "Deep Copy":** Emphasize that you are creating entirely new `Node` objects, not just copying references.

## Revision Checklist
- [ ] Understand the problem: deep copy of a linked list with random pointers.
- [ ] Recognize the need for mapping original nodes to new nodes.
- [ ] Implement the two-pass approach using a HashMap.
- [ ] Correctly handle `next` and `random` pointer assignments.
- [ ] Consider edge cases like an empty list.
- [ ] Analyze time and space complexity.
- [ ] Be prepared to discuss alternative O(1) space solutions.

## Similar Problems
*   Clone Graph
*   Copy List with Random Pointer (This is the problem itself)

## Tags
`Linked List` `Hash Map` `Depth-First Search` `Recursion`
