# Merge Two Sorted Lists

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Linked List` `Recursion`  
**Time:** O(N + M)  
**Space:** O(N + M)

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null) return null;
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        if(list2.val < list1.val) return mergeTwoLists(list2,list1);
        ListNode head = list1;
        list1 = list1.next;
        head.next = mergeTwoLists(list1,list2);
        return head;
    }
}
```

---

---
## Quick Revision
Given two sorted linked lists, merge them into a single sorted linked list.
This is solved recursively by picking the smaller head and merging the rest.

## Intuition
The core idea is to always pick the smaller element from the heads of the two lists to build the merged list. If `list1`'s head is smaller, it becomes the next node in the merged list, and we then recursively merge the rest of `list1` with `list2`. If `list2`'s head is smaller, we swap them to ensure `list1` always has the smaller head, simplifying the logic. This recursive approach naturally builds the sorted list from the front.

## Algorithm
1. **Base Cases:**
   - If both `list1` and `list2` are null, return null.
   - If `list1` is null, return `list2`.
   - If `list2` is null, return `list1`.
2. **Comparison and Recursion:**
   - If the value of `list2`'s head is less than the value of `list1`'s head, swap `list1` and `list2` to ensure `list1` always points to the node with the smaller value.
   - Set the current node of the merged list (`head`) to `list1`.
   - Advance `list1` to its next node.
   - Recursively call `mergeTwoLists` with the updated `list1` and `list2`. The result of this recursive call will be the `next` node for our current `head`.
   - Return `head`.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Linked Lists:** Understanding node structure, traversal, and manipulation (specifically `next` pointers).
*   **Base Cases:** Essential for terminating recursive functions and preventing infinite loops.
*   **In-place Modification:** Modifying the existing list structures rather than creating entirely new nodes (though this recursive solution effectively does this by re-linking).

## Common Mistakes
*   **Incorrect Base Cases:** Missing or improperly handled null checks can lead to NullPointerExceptions.
*   **Infinite Recursion:** Not properly advancing the pointers (`list1 = list1.next`) or not having a clear termination condition.
*   **Modifying Original Lists Incorrectly:** Accidentally losing track of the original heads or messing up the `next` pointers.
*   **Not Handling the Swap:** Forgetting to ensure `list1` always has the smaller head can complicate the recursive step.

## Complexity Analysis
- Time: O(N + M) - reason: Each node from both lists is visited and processed exactly once. N is the length of list1, M is the length of list2.
- Space: O(N + M) - reason: Due to the recursion depth. In the worst case, the recursion stack can grow up to the total number of nodes if one list is much longer than the other or if elements are interleaved perfectly.

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Base case: If both lists are empty, the merged list is empty.
        if(list1 == null && list2 == null) return null;
        // Base case: If list1 is empty, the merged list is just list2.
        if(list1 == null) return list2;
        // Base case: If list2 is empty, the merged list is just list1.
        if(list2 == null) return list1;

        // Ensure list1 always points to the node with the smaller value.
        // If list2's head is smaller, swap them to simplify the recursive step.
        if(list2.val < list1.val) return mergeTwoLists(list2,list1);

        // The current node for the merged list will be the head of list1 (which is guaranteed to be the smaller one).
        ListNode head = list1;
        // Move list1 pointer to the next node to consider for the rest of the merge.
        list1 = list1.next;
        // Recursively merge the rest of list1 with list2.
        // The result of this recursive call becomes the 'next' pointer for our current 'head'.
        head.next = mergeTwoLists(list1,list2);
        // Return the head of the merged list.
        return head;
    }
}
```

## Interview Tips
*   **Explain the Recursive Approach:** Clearly articulate how the problem is broken down and how the base cases handle termination.
*   **Trace an Example:** Walk through a small example (e.g., `[1,3,5]` and `[2,4,6]`) to show your understanding of the pointer manipulation and recursion.
*   **Discuss Iterative vs. Recursive:** Be prepared to discuss the trade-offs between this recursive solution and an iterative one (e.g., space complexity).
*   **Handle Edge Cases:** Explicitly mention how null lists and empty lists are handled by your base cases.

## Revision Checklist
- [ ] Understand the problem statement: merging two sorted linked lists.
- [ ] Identify base cases for recursion (null lists).
- [ ] Implement the comparison logic to pick the smaller head.
- [ ] Ensure the recursive call correctly advances the list pointers.
- [ ] Verify the `next` pointer is correctly set for the current node.
- [ ] Analyze time and space complexity.
- [ ] Consider iterative alternatives.

## Similar Problems
*   Merge k Sorted Lists
*   Sort List
*   Remove Duplicates from Sorted List II

## Tags
`Recursion` `Linked List`
