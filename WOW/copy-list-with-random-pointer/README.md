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
   // key purana version , uski value uska naya waala node hai 
   // address 
}
```

---

---
## Quick Revision
This problem asks to create a deep copy of a linked list where each node has a `next` pointer and a `random` pointer. The solution involves mapping original nodes to their newly created copies to correctly set both `next` and `random` pointers.

## Intuition
The core challenge is handling the `random` pointers. If we simply iterate and create new nodes, we won't know which new node corresponds to the `random` pointer of an original node. A hash map is a natural fit to store this mapping: `original_node -> new_node`. This allows us to look up the corresponding new node for any original node, whether it's accessed via `next` or `random`.

## Algorithm
1.  **Create a mapping:** Initialize an empty hash map `oldToNew` to store the mapping from original nodes to their corresponding new nodes.
2.  **First Pass (Node Creation):** Iterate through the original linked list. For each node encountered, create a new node with the same value and put it into the `oldToNew` map, with the original node as the key and the new node as the value.
3.  **Second Pass (Pointer Assignment):** Iterate through the original linked list again. For each original node `curr`:
    *   Get its corresponding new node from `oldToNew.get(curr)`.
    *   Set the `next` pointer of the new node: `oldToNew.get(curr).next = oldToNew.get(curr.next)`. This works because `curr.next` is also an original node, and its corresponding new node will already be in the map.
    *   Set the `random` pointer of the new node: `oldToNew.get(curr).random = oldToNew.get(curr.random)`. Similar to the `next` pointer, `curr.random` is an original node, and its new counterpart is retrievable from the map. Handle the case where `curr.random` might be `null`.
4.  **Return Head:** Return the new head node, which is `oldToNew.get(head)`.

## Concept to Remember
*   **Deep Copy:** Creating an entirely new structure with independent copies of all elements, not just references.
*   **Hash Maps (Dictionaries):** Efficient data structures for key-value lookups, crucial for mapping original nodes to their copies.
*   **Linked List Traversal:** Iterating through nodes using `next` pointers.
*   **Handling Null Pointers:** Properly managing cases where `next` or `random` pointers might be `null`.

## Common Mistakes
*   **Shallow Copy:** Simply copying node values and linking them without creating new `Node` objects, leading to shared nodes between original and copied lists.
*   **Incorrect Random Pointer Assignment:** Trying to assign `random` pointers before all new nodes are created and mapped, resulting in `null` or incorrect references.
*   **Not Handling Null `random` Pointers:** Failing to check if `curr.random` is `null` before attempting to get its mapping, which can lead to `NullPointerException`.
*   **Inefficient Mapping:** Using nested loops or other less efficient methods to find corresponding nodes instead of a hash map.

## Complexity Analysis
*   **Time:** O(N) - We traverse the linked list twice. The first pass to create nodes takes O(N) time. The second pass to assign pointers also takes O(N) time. Hash map operations (put and get) take average O(1) time.
*   **Space:** O(N) - The hash map `oldToNew` stores a mapping for each of the N nodes in the linked list.

## Commented Code
```java
/*
// Definition for a Node.
class Node {
    int val; // The value of the node.
    Node next; // Pointer to the next node in the list.
    Node random; // Pointer to any node in the list or null.

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // Create a HashMap to store the mapping from original nodes to their new copies.
        // Key: Original Node, Value: New Copied Node.
        HashMap<Node,Node> oldToNew = new HashMap<>();

        // Initialize a pointer 'curr' to the head of the original list.
        Node curr = head;

        // First Pass: Iterate through the original list to create new nodes and populate the map.
        while(curr!=null){
            // For each original node, create a new node with the same value.
            // Store this mapping in the HashMap.
            oldToNew.put(curr, new Node(curr.val));
            // Move to the next node in the original list.
            curr = curr.next;
        }

        // Reset 'curr' to the head of the original list for the second pass.
        curr = head;

        // Second Pass: Iterate through the original list again to set the 'next' and 'random' pointers of the new nodes.
        while(curr != null){
            // Get the new node corresponding to the current original node.
            Node newNode = oldToNew.get(curr);
            // Set the 'next' pointer of the new node.
            // The 'next' of the new node should point to the new node corresponding to the original node's 'next'.
            // If curr.next is null, oldToNew.get(null) will correctly return null.
            newNode.next = oldToNew.get(curr.next);
            // Set the 'random' pointer of the new node.
            // The 'random' of the new node should point to the new node corresponding to the original node's 'random'.
            // If curr.random is null, oldToNew.get(null) will correctly return null.
            newNode.random = oldToNew.get(curr.random);
            // Move to the next node in the original list.
            curr = curr.next;
        }

        // Return the head of the newly created copied list.
        // This is the new node corresponding to the original head.
        return oldToNew.get(head);
    }
   // key purana version , uski value uska naya waala node hai
   // address
}
```

## Interview Tips
*   **Explain the Two-Pass Approach:** Clearly articulate why two passes are necessary: one to create all nodes and establish the mapping, and a second to correctly link the `next` and `random` pointers using that mapping.
*   **Handle Edge Cases:** Discuss how `null` `head`, `null` `next` pointers, and `null` `random` pointers are handled by the algorithm and the hash map.
*   **Alternative Solutions (Optional but good):** Briefly mention or be prepared to discuss the O(1) space solution (interweaving nodes) if asked, but focus on the hash map approach first as it's more intuitive.
*   **Clarify "Deep Copy":** Ensure you understand and can explain the difference between a shallow copy and a deep copy in the context of this problem.

## Revision Checklist
- [ ] Understand the problem: deep copy of a linked list with random pointers.
- [ ] Recognize the need for mapping original nodes to new nodes.
- [ ] Implement the two-pass approach using a HashMap.
- [ ] Correctly handle `next` and `random` pointer assignments.
- [ ] Consider edge cases like an empty list or null pointers.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   Clone Graph
*   Serialize and Deserialize Binary Tree
*   Copy List with Random Pointer II (slightly different constraints or approach)

## Tags
`Linked List` `Hash Map` `Depth-First Search` `Breadth-First Search`
