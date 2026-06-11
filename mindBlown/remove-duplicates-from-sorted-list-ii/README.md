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
The core idea is that if the current node's value is the same as the next node's value, then this current node (and all subsequent nodes with the same value) must be removed. We can recursively call the function on the rest of the list after skipping all duplicates. If the current node's value is unique (i.e., different from the next node), we keep it and recursively process the rest of the list.

## Algorithm
1. Handle base cases: If the list is empty or has only one node, return the head as there are no duplicates to remove.
2. Check for duplicates at the head: If the current node's value is equal to the next node's value:
    a. Store the duplicate value.
    b. Traverse the list, advancing `head` as long as `head` is not null and its value matches the duplicate value. This effectively skips all nodes with the duplicate value.
    c. Recursively call `deleteDuplicates` on the new `head` (which is now the first node after the duplicate sequence).
3. If no duplicates at the head:
    a. Recursively call `deleteDuplicates` on `head.next`.
    b. Assign the result of the recursive call back to `head.next`. This ensures that the rest of the list is processed and any duplicates found there are removed.
    c. Return `head`.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Linked List Manipulation:** Understanding how to traverse and modify linked list nodes.
*   **Sorted Input:** Leveraging the sorted property to efficiently identify duplicates.

## Common Mistakes
*   **Incorrectly handling the head node:** Failing to properly advance the `head` pointer when duplicates are found at the beginning of the list.
*   **Not handling the case where all nodes are duplicates:** The recursive calls might lead to an empty list, which needs to be handled.
*   **Modifying the list while iterating without proper care:** This can lead to lost nodes or infinite loops.
*   **Off-by-one errors in loop conditions:** Incorrectly checking `head.next` or `head.val == val` conditions.

## Complexity Analysis
*   **Time:** O(N) - Each node is visited at most a constant number of times (once for checking duplicates, and potentially once more if it's part of a duplicate sequence being skipped).
*   **Space:** O(N) - In the worst case (e.g., a list with no duplicates), the recursion depth can be equal to the number of nodes, leading to O(N) space complexity for the call stack.

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
        // Base case: if the list is empty or has only one node, no duplicates can exist.
        if(head==null || head.next == null) return head;

        // Check if the current node's value is a duplicate of the next node's value.
        if(head.val == head.next.val){
            // Store the value that is duplicated.
            int val = head.val;
            // Traverse the list and skip all nodes that have this duplicate value.
            // 'head' will be advanced to the first node *after* the sequence of duplicates.
            while(head!=null && head.val == val) head = head.next;
            // Recursively call deleteDuplicates on the rest of the list starting from the new 'head'.
            // This handles cases where the duplicates were at the beginning of the list.
            return deleteDuplicates(head);
        }

        // If the current node's value is not a duplicate of the next node's value,
        // it means this node should potentially be kept.
        // Recursively call deleteDuplicates on the rest of the list (from head.next onwards).
        // The result of this recursive call (the processed tail of the list) is assigned back to head.next.
        head.next = deleteDuplicates(head.next);
        // Return the current head node, which is now correctly linked to the processed rest of the list.
        return head;
    }
}
```

## Interview Tips
*   **Explain the recursive approach clearly:** Walk through an example to show how the function calls itself and how the list is rebuilt.
*   **Discuss the base cases:** Emphasize why `head == null` or `head.next == null` are important stopping conditions.
*   **Consider an iterative solution:** While the provided solution is recursive, be prepared to discuss or implement an iterative approach using a dummy head node for comparison.
*   **Trace edge cases:** Be ready to trace the execution with lists like `[1,1,1,2,3]`, `[1,2,3,3,4,4,5]`, or `[1,1,1,1]`.

## Revision Checklist
- [ ] Understand the problem statement: remove *all* nodes with duplicate values.
- [ ] Recognize the sorted nature of the input list.
- [ ] Grasp the recursive strategy: handle duplicates at the head, then recurse.
- [ ] Implement base cases correctly.
- [ ] Trace the algorithm with various examples.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Remove Duplicates from Sorted List (LeetCode 82) - This is the "I" version, where only one instance of each number is kept.
*   Remove Duplicates from Sorted Array II (LeetCode 80) - Similar logic but for arrays.

## Tags
`Linked List` `Recursion` `Two Pointers`
