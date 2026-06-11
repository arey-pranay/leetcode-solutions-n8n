# Remove Duplicates From Sorted List Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next == null) return head;
        
        if(head.val == head.next.val){
            int val = head.val;
            while(head!=null && head.val == val) head = head.next;
            return deleteDuplicates(head);
        }
        
        head.next = deleteDuplicates(head.next);
        return head;
    }
}
```

---

---
## Quick Revision
Given a sorted linked list, remove all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
This is solved using recursion by identifying and skipping duplicate sequences.

## Intuition
The core idea is that if we encounter a node whose value is the same as the next node's value, then this node (and all subsequent nodes with the same value) must be removed. We can recursively solve the problem for the rest of the list. If the current node's value is unique (i.e., different from the next node), then we keep it and recursively solve for the rest of the list starting from the next node. A dummy node can simplify edge cases, especially when the head itself needs to be removed.

## Algorithm
1. **Base Case:** If the list is empty or has only one node, return the head as there are no duplicates to remove.
2. **Check for Duplicates at Head:** If the current node's value is the same as the next node's value:
    a. Store the duplicate value.
    b. Traverse the list, skipping all nodes with this duplicate value until a different value is found or the list ends.
    c. Recursively call `deleteDuplicates` on the remaining list (starting from the first node with a different value).
3. **No Duplicates at Head:** If the current node's value is different from the next node's value:
    a. Recursively call `deleteDuplicates` on the rest of the list (`head.next`).
    b. Assign the result of the recursive call back to `head.next`. This ensures that any duplicates removed from the tail of the list are correctly linked.
    c. Return the current `head`.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Linked List Manipulation:** Understanding how to traverse and modify linked list nodes.
*   **Handling Edge Cases:** Specifically, dealing with duplicates at the beginning of the list and empty/single-node lists.
*   **Sorted Input:** The sorted nature of the list is crucial for efficiently identifying duplicates.

## Common Mistakes
*   **Not handling the case where the head node itself is a duplicate:** The recursive approach needs to correctly skip over initial duplicate sequences.
*   **Modifying the list while iterating without proper care:** Incorrectly updating `next` pointers can lead to lost nodes or infinite loops.
*   **Forgetting the base case for recursion:** This can lead to `NullPointerException` or infinite recursion.
*   **Not correctly re-linking the list after recursive calls:** When a duplicate sequence is removed, the preceding node's `next` pointer must be updated to point to the first non-duplicate node.

## Complexity Analysis
*   **Time:** O(N) - Each node is visited at most a constant number of times (once for checking, and potentially again if it's part of a duplicate sequence being skipped).
*   **Space:** O(N) - In the worst case (e.g., a list with no duplicates), the recursion depth can be equal to the number of nodes, leading to O(N) space complexity due to the call stack.

## Commented Code
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val; // The value of the node
 *     ListNode next; // Pointer to the next node in the list
 *     ListNode() {} // Default constructor
 *     ListNode(int val) { this.val = val; } // Constructor with value
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; } // Constructor with value and next node
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Base case: if the list is empty or has only one node, no duplicates to remove.
        if(head==null || head.next == null) return head;
        
        // Check if the current node's value is a duplicate of the next node's value.
        if(head.val == head.next.val){
            // Store the value that is duplicated.
            int val = head.val;
            // Traverse the list, skipping all nodes that have this duplicate value.
            while(head!=null && head.val == val) {
                head = head.next; // Move head to the next node
            }
            // Recursively call deleteDuplicates on the rest of the list starting from the first non-duplicate node.
            return deleteDuplicates(head);
        }
        
        // If the current node's value is not a duplicate of the next node's value,
        // recursively call deleteDuplicates on the rest of the list (starting from head.next).
        head.next = deleteDuplicates(head.next);
        // Return the current head node, as it's distinct and should be kept.
        return head;
    }
}
```

## Interview Tips
*   **Explain the Recursive Approach:** Clearly articulate how the problem is broken down into smaller subproblems and how the base cases are handled.
*   **Discuss Edge Cases:** Be prepared to talk about how you handle an empty list, a list with one node, and a list where all nodes are duplicates or the head is a duplicate.
*   **Consider an Iterative Solution:** While the recursive solution is elegant, an interviewer might ask for an iterative approach using a dummy node to handle the head case more explicitly.
*   **Trace an Example:** Walk through a small example (e.g., `[1, 2, 3, 3, 4, 4, 5]`) to demonstrate your understanding of the algorithm's execution.

## Revision Checklist
- [ ] Understand the problem statement: remove *all* nodes with duplicate values.
- [ ] Identify the recursive structure: solve for the rest of the list.
- [ ] Handle the base case: empty or single-node list.
- [ ] Implement logic to skip entire duplicate sequences.
- [ ] Correctly re-link the list after recursive calls.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Remove Duplicates From Sorted List
*   Remove Nth Node From End of List
*   Swap Nodes in Pairs
*   Reverse Nodes in k-Group

## Tags
`Linked List` `Recursion` `Two Pointers`
