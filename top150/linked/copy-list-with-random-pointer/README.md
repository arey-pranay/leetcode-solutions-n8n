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
   // key pu
}
```

---

---
## Quick Revision
This problem asks to create a deep copy of a linked list where each node has a `next` and a `random` pointer. The solution involves mapping original nodes to their copied counterparts to correctly set the `random` pointers.

## Intuition
The core challenge is handling the `random` pointers. If we simply iterate and create new nodes, when we encounter a `random` pointer, the target node might not have been created yet, or we might create a duplicate. A map is a natural fit to store the correspondence between an original node and its newly created copy. This way, when we process a `random` pointer, we can look up its corresponding new node in the map.

## Algorithm
1.  **Create a mapping:** Initialize a HashMap to store the mapping from original nodes to their corresponding new nodes.
2.  **First Pass (Node Creation):** Iterate through the original linked list. For each node, create a new node with the same value and put it into the HashMap, mapping the original node to the new node.
3.  **Second Pass (Pointer Assignment):** Iterate through the original linked list again. For each original node:
    *   Get its corresponding new node from the HashMap.
    *   Set the `next` pointer of the new node to the new node corresponding to the original node's `next` pointer (obtained from the HashMap).
    *   Set the `random` pointer of the new node to the new node corresponding to the original node's `random` pointer (obtained from the HashMap).
4.  **Return Head:** Return the new node corresponding to the original head node from the HashMap.

## Concept to Remember
*   **Deep Copy:** Creating a completely independent copy of a data structure, including all its elements and their relationships.
*   **Hash Maps (Dictionaries):** Efficient data structures for key-value lookups, crucial for mapping original nodes to their copies.
*   **Linked List Traversal:** Iterating through a linked list by following `next` pointers.

## Common Mistakes
*   **Shallow Copy:** Accidentally copying only the pointers, not creating new nodes, leading to shared references.
*   **Handling Null Pointers:** Not correctly handling cases where `next` or `random` pointers are `null`.
*   **Order of Operations:** Trying to assign `random` pointers before all nodes have been created and mapped.
*   **Duplicate Node Creation:** Creating multiple copies of the same node if not using a mapping.

## Complexity Analysis
*   **Time:** O(N) - We traverse the linked list twice. The HashMap operations (put and get) take O(1) on average.
*   **Space:** O(N) - The HashMap stores a mapping for each of the N nodes in the list.

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

        // Initialize a pointer to traverse the original list, starting from the head.
        Node curr = head;

        // First Pass: Create all the new nodes and populate the HashMap.
        while(curr!=null){
            // For each original node, create a new node with the same value.
            // Put the original node and its new copy into the HashMap.
            oldToNew.put(curr, new Node(curr.val));
            // Move to the next node in the original list.
            curr = curr.next;
        }

        // Reset the current pointer to the head of the original list for the second pass.
        curr = head;

        // Second Pass: Assign the next and random pointers for the new nodes.
        while(curr != null){
            // Get the new node corresponding to the current original node from the HashMap.
            // Set its 'next' pointer:
            // The 'next' of the new node should point to the new node that corresponds to the original node's 'next'.
            // We retrieve this from the HashMap using oldToNew.get(curr.next).
            oldToNew.get(curr).next = oldToNew.get(curr.next);

            // Set its 'random' pointer:
            // The 'random' of the new node should point to the new node that corresponds to the original node's 'random'.
            // We retrieve this from the HashMap using oldToNew.get(curr.random).
            oldToNew.get(curr).random = oldToNew.get(curr.random);

            // Move to the next node in the original list.
            curr = curr.next;
        }

        // Return the head of the newly created copied list.
        // This is the new node corresponding to the original head, retrieved from the HashMap.
        return oldToNew.get(head);
    }
    // key pu (This comment seems incomplete or a typo, likely meant to be "key purpose" or similar)
}
```

## Interview Tips
*   **Explain the HashMap Approach:** Clearly articulate why a HashMap is necessary to manage the `random` pointers and avoid infinite loops or incorrect references.
*   **Two-Pass Strategy:** Emphasize the two-pass approach: first for node creation, then for pointer assignment. Explain the necessity of this order.
*   **Edge Cases:** Discuss handling an empty list (`head == null`). The current solution handles this gracefully as the loops won't execute and `oldToNew.get(null)` would return `null`.
*   **Alternative Solutions (Optional but good):** Briefly mention or be prepared to discuss the O(1) space solution (interweaving nodes) if asked, but focus on the HashMap approach first as it's more intuitive.

## Revision Checklist
- [ ] Understand the problem: deep copy with `next` and `random` pointers.
- [ ] Identify the challenge: correctly mapping `random` pointers.
- [ ] Implement the two-pass HashMap approach.
- [ ] Handle null `next` and `random` pointers.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm.

## Similar Problems
*   Clone Graph
*   Copy List with Random Pointer II (slightly different constraints/node definition)

## Tags
`Linked List` `Hash Map`
