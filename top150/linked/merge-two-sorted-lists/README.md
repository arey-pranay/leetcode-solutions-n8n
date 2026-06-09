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
This is solved recursively by comparing the heads and merging the rest.

## Intuition
The core idea is to always pick the smaller of the two current head nodes and make it the next node in the merged list. Then, recursively merge the rest of the lists. The base cases are when one or both lists are empty.

## Algorithm
1. Handle base cases: If both lists are null, return null. If one list is null, return the other list.
2. Compare the values of the head nodes of `list1` and `list2`.
3. If `list2.val` is smaller than `list1.val`, swap `list1` and `list2` to ensure `list1` always points to the smaller head. This simplifies the recursive step.
4. Set the `head` of the merged list to `list1`.
5. Advance `list1` to its next node.
6. Recursively call `mergeTwoLists` with the updated `list1` and `list2`. The result of this recursive call becomes the `next` of the current `head`.
7. Return the `head`.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Linked Lists:** Understanding node manipulation, pointers, and traversal.
*   **Base Cases:** Crucial for terminating recursive functions and handling edge scenarios.
*   **In-place Modification:** Modifying the existing list structures rather than creating entirely new ones.

## Common Mistakes
*   **Not handling null lists:** Forgetting to check if `list1` or `list2` are null, leading to NullPointerExceptions.
*   **Incorrect base case logic:** Mishandling the scenarios where one or both lists are empty.
*   **Infinite recursion:** Failing to advance the pointers correctly, causing the recursion to never terminate.
*   **Modifying the wrong node:** Incorrectly assigning `next` pointers, breaking the sorted order.
*   **Not considering the initial swap:** The recursive solution relies on `list1` always having the smaller head for simplicity. Forgetting this swap can complicate the logic.

## Complexity Analysis
- Time: O(N + M) - reason: Each node from both lists is visited and processed exactly once. N is the length of list1, M is the length of list2.
- Space: O(N + M) - reason: In the worst case, the recursion depth can be proportional to the total number of nodes if the lists are interleaved. This is due to the call stack.

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
        // Base case: If both lists are empty, there's nothing to merge, return null.
        if(list1 == null && list2 == null) return null;
        // Base case: If list1 is empty, the merged list is just list2.
        if(list1 == null) return list2;
        // Base case: If list2 is empty, the merged list is just list1.
        if(list2 == null) return list1;

        // Ensure list1 always points to the node with the smaller value.
        // If list2's head is smaller, swap list1 and list2 to simplify the recursive step.
        if(list2.val < list1.val) return mergeTwoLists(list2,list1);

        // If we reach here, list1.val <= list2.val.
        // So, list1's head is the smallest and will be the head of the merged list.
        ListNode head = list1;
        // Advance list1 to its next node, as list1.val is now part of the merged list.
        list1 = list1.next;
        // Recursively merge the rest of list1 (starting from its next node) with list2.
        // The result of this recursive call becomes the 'next' of the current 'head' node.
        head.next = mergeTwoLists(list1,list2);
        // Return the head of the merged list.
        return head;
    }
}
```

## Interview Tips
*   **Explain the recursive approach clearly:** Walk through the base cases and the recursive step.
*   **Consider an iterative solution:** Be prepared to discuss or implement an iterative version, which avoids the O(N+M) space complexity of recursion.
*   **Trace with an example:** Use a simple example like `list1 = [1, 3, 5]` and `list2 = [2, 4, 6]` to demonstrate how the pointers move and the list is built.
*   **Discuss edge cases:** Explicitly mention how null lists and lists of different lengths are handled.

## Revision Checklist
- [ ] Understand the problem statement for merging sorted linked lists.
- [ ] Identify the base cases for recursion (empty lists).
- [ ] Implement the logic to pick the smaller head node.
- [ ] Ensure the recursive call correctly merges the remaining parts.
- [ ] Verify pointer manipulation for `next` assignments.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with examples.
- [ ] Be ready to discuss an iterative solution.

## Similar Problems
*   Merge k Sorted Lists
*   Sort List
*   Remove Duplicates from Sorted List II
*   Remove Duplicates from Sorted List

## Tags
`Linked List` `Recursion` `Two Pointers`
