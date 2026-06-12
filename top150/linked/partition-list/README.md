# Partition List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers`  
**Time:** O(N)  
**Space:** O(1)

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
    public ListNode partition(ListNode head, int x) {
        ListNode smalls = new ListNode(-101);
        ListNode moveSmalls = smalls;
        ListNode bigs = new ListNode(101);
        ListNode moveBigs = bigs;
        while(head!=null){
            if(head.val<x){
                moveSmalls.next = head;
                head = head.next;
                moveSmalls = moveSmalls.next;
                moveSmalls.next = null;
            }
            else{
                moveBigs.next = head;
                head = head.next;
                moveBigs = moveBigs.next;
                moveBigs.next = null;
            } 
            
        }
        moveSmalls.next = bigs.next;
        return smalls.next;
    }
}
```

---

---
## Quick Revision
Given a linked list and a pivot value `x`, rearrange the list such that all nodes less than `x` come before all nodes greater than or equal to `x`.
The solution involves creating two separate lists (one for smaller values, one for larger) and then concatenating them.

## Intuition
The core idea is to separate the nodes into two groups: those less than `x` and those greater than or equal to `x`. We can achieve this by iterating through the original list and appending each node to its respective new list. Once we have these two partitioned lists, we can simply link the tail of the "less than" list to the head of the "greater than or equal to" list. Using dummy nodes at the beginning of each new list simplifies the append operation, as we don't need to handle the special case of inserting into an empty list.

## Algorithm
1. Initialize two dummy nodes, `smalls` and `bigs`, to act as heads for the two partitions.
2. Initialize two pointers, `moveSmalls` and `moveBigs`, to point to `smalls` and `bigs` respectively. These pointers will traverse and build the new lists.
3. Iterate through the original linked list using a `head` pointer.
4. In each iteration:
    a. If the current node's value (`head.val`) is less than `x`:
        i. Append the current node to the `smalls` list: `moveSmalls.next = head`.
        ii. Move `moveSmalls` forward: `moveSmalls = moveSmalls.next`.
    b. Else (if the current node's value is greater than or equal to `x`):
        i. Append the current node to the `bigs` list: `moveBigs.next = head`.
        ii. Move `moveBigs` forward: `moveBigs = moveBigs.next`.
    c. Crucially, advance the `head` pointer to the next node in the original list: `head = head.next`.
    d. To prevent cycles and ensure correct list termination, set the `next` pointer of the node just added to `null`: `moveSmalls.next = null` or `moveBigs.next = null`. This is implicitly handled by the original code's structure where `head` is advanced *after* the assignment, and the `next` pointer of the *newly added node* is then set to `null`.
5. After the loop finishes, connect the tail of the `smalls` list to the head of the `bigs` list: `moveSmalls.next = bigs.next`.
6. Return the head of the partitioned list, which is `smalls.next` (skipping the dummy node).

## Concept to Remember
*   **Linked List Manipulation:** Understanding how to traverse, insert, and modify `next` pointers in a singly linked list.
*   **Dummy Nodes:** Using sentinel or dummy nodes to simplify edge cases (like inserting into an empty list) and make code cleaner.
*   **Two-Pointer Technique:** Employing multiple pointers to manage different parts of a data structure or to track different states during traversal.

## Common Mistakes
*   **Forgetting to break the original list's links:** If you don't set `moveSmalls.next = null` or `moveBigs.next = null` after appending a node, you might create cycles or include unintended nodes from the original list's tail.
*   **Incorrectly connecting the two lists:** Failing to connect `moveSmalls.next` to `bigs.next` will result in two separate lists instead of one partitioned list.
*   **Off-by-one errors with dummy nodes:** Not returning `smalls.next` (and instead returning `smalls`) will include the dummy node in the result.
*   **Modifying `head` before using its value:** If `head` is advanced before checking `head.val`, you'll skip the first node.

## Complexity Analysis
- Time: O(N) - reason The algorithm iterates through the linked list once. Each node is visited and processed a constant number of times.
- Space: O(1) - reason We are only using a few extra pointers and dummy nodes, which do not depend on the size of the input list. The rearrangement is done in-place by re-linking existing nodes.

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
    public ListNode partition(ListNode head, int x) {
        // Create a dummy node for the list of nodes with values less than x.
        // The initial value (-101) is arbitrary and won't be part of the final list.
        ListNode smalls = new ListNode(-101);
        // Initialize a pointer to traverse and build the 'smalls' list.
        ListNode moveSmalls = smalls;

        // Create a dummy node for the list of nodes with values greater than or equal to x.
        // The initial value (101) is arbitrary and won't be part of the final list.
        ListNode bigs = new ListNode(101);
        // Initialize a pointer to traverse and build the 'bigs' list.
        ListNode moveBigs = bigs;

        // Iterate through the original linked list as long as there are nodes.
        while(head!=null){
            // Check if the current node's value is less than the partition value x.
            if(head.val<x){
                // If it's smaller, append it to the 'smalls' list.
                moveSmalls.next = head;
                // Move the 'head' pointer to the next node in the original list.
                head = head.next;
                // Advance the 'moveSmalls' pointer to the newly added node.
                moveSmalls = moveSmalls.next;
                // Crucially, set the 'next' of the newly added node to null to avoid cycles
                // and ensure correct list termination. This is important if this node
                // was the last one in the original list.
                moveSmalls.next = null;
            }
            else{ // If the current node's value is greater than or equal to x.
                // Append it to the 'bigs' list.
                moveBigs.next = head;
                // Move the 'head' pointer to the next node in the original list.
                head = head.next;
                // Advance the 'moveBigs' pointer to the newly added node.
                moveBigs = moveBigs.next;
                // Crucially, set the 'next' of the newly added node to null to avoid cycles
                // and ensure correct list termination.
                moveBigs.next = null;
            }
        }
        // After iterating through all nodes, connect the tail of the 'smalls' list
        // to the head of the 'bigs' list (skipping the dummy 'bigs' node).
        moveSmalls.next = bigs.next;
        // Return the head of the partitioned list, which is the node after the dummy 'smalls' node.
        return smalls.next;
    }
}
```

## Interview Tips
*   **Explain the dummy node strategy:** Clearly articulate why dummy nodes are used and how they simplify the logic for appending to potentially empty lists.
*   **Trace with an example:** Walk through a small linked list (e.g., `[1, 4, 3, 2, 5, 2]`, `x = 3`) to demonstrate how the `smalls` and `bigs` lists are built and then concatenated.
*   **Address edge cases:** Discuss what happens if the list is empty, if all elements are less than `x`, or if all elements are greater than or equal to `x`. The dummy node approach handles these gracefully.
*   **Clarify the `next = null` step:** Emphasize why setting `moveSmalls.next = null` and `moveBigs.next = null` after appending is important for preventing cycles and ensuring correct list termination.

## Revision Checklist
- [ ] Understand the problem statement: partition a linked list around a pivot.
- [ ] Recognize the need to separate nodes into two groups.
- [ ] Implement the two-pointer approach with dummy nodes.
- [ ] Correctly append nodes to the respective partitions.
- [ ] Ensure the `next` pointers of appended nodes are nullified.
- [ ] Properly connect the tail of the "less than" list to the head of the "greater than or equal to" list.
- [ ] Return the correct head of the partitioned list.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Sort List
*   Remove Duplicates from Sorted List II
*   Remove Linked List Elements
*   Merge Two Sorted Lists

## Tags
`Linked List` `Two Pointers`
