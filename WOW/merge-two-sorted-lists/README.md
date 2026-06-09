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
The core idea is to always pick the smaller of the two current nodes to be the next node in the merged list. Since both input lists are already sorted, this greedy approach guarantees that the resulting list will also be sorted. The recursive structure naturally handles the "rest" of the lists after picking the current smallest node.

## Algorithm
1. **Base Cases:**
   - If both `list1` and `list2` are null, return null.
   - If `list1` is null, return `list2`.
   - If `list2` is null, return `list1`.
2. **Comparison:**
   - If the value of `list2`'s head is less than the value of `list1`'s head, swap `list1` and `list2` to ensure `list1` always points to the smaller head. This simplifies the recursive step.
3. **Recursive Step:**
   - Set the `head` of the merged list to `list1`.
   - Advance `list1` to its next node.
   - Recursively call `mergeTwoLists` with the updated `list1` and the original `list2`.
   - Set the `next` pointer of the current `head` to the result of the recursive call.
4. **Return:** Return the `head` of the merged list.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Linked Lists:** Understanding node structure, traversal, and manipulation (specifically `next` pointers).
*   **Base Cases:** Essential for terminating recursive functions and handling edge conditions.
*   **Greedy Approach:** Making the locally optimal choice at each step to achieve a globally optimal solution.

## Common Mistakes
*   **Incorrect Base Cases:** Forgetting to handle scenarios where one or both lists are empty.
*   **Off-by-One Errors:** Incorrectly manipulating `next` pointers, leading to lost nodes or cycles.
*   **Not Handling the "Swap" Logic:** If not swapping `list1` and `list2` when `list2.val < list1.val`, the recursive step becomes more complex to manage.
*   **Infinite Recursion:** Missing a base case or failing to advance pointers correctly, causing the recursion to never terminate.

## Complexity Analysis
- Time: O(N + M) - reason: Each node from both lists is visited and processed exactly once. N is the length of list1, M is the length of list2.
- Space: O(N + M) - reason: In the worst case, the recursion depth can be proportional to the total number of nodes if one list is significantly shorter than the other, leading to stack space usage.

## Commented Code
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val; // The value stored in the node.
 *     ListNode next; // A reference to the next node in the list.
 *     ListNode() {} // Default constructor.
 *     ListNode(int val) { this.val = val; } // Constructor with value.
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; } // Constructor with value and next node.
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Base case: If both lists are empty, the merged list is also empty.
        if(list1 == null && list2 == null) return null;
        // Base case: If list1 is empty, the merged list is just list2.
        if(list1 == null) return list2;
        // Base case: If list2 is empty, the merged list is just list1.
        if(list2 == null) return list1;

        // Ensure list1 always points to the node with the smaller value.
        // This simplifies the recursive step by always picking from list1 first.
        if(list2.val < list1.val) return mergeTwoLists(list2,list1);

        // If list1.val is smaller or equal, it becomes the head of the merged list.
        ListNode head = list1;
        // Move list1 to its next node to consider for the rest of the merge.
        list1 = list1.next;
        // Recursively merge the rest of list1 with list2.
        // The result of this recursive call becomes the 'next' of the current head.
        head.next = mergeTwoLists(list1,list2);
        // Return the head of the merged list.
        return head;
    }
}
```

## Interview Tips
*   **Explain the Recursive Logic:** Clearly articulate how the base cases and recursive step work together.
*   **Trace an Example:** Walk through a small example (e.g., `[1,3,5]` and `[2,4,6]`) to demonstrate your understanding.
*   **Consider Iterative Approach:** Be prepared to discuss or implement an iterative solution as well, which might have better space complexity (O(1)).
*   **Handle Edge Cases:** Explicitly mention and test scenarios with empty lists or lists with only one element.

## Revision Checklist
- [ ] Understand the problem statement for merging sorted linked lists.
- [ ] Identify and implement correct base cases for recursion.
- [ ] Grasp the recursive step: comparing heads and merging the tails.
- [ ] Ensure correct pointer manipulation (`next` references).
- [ ] Analyze time and space complexity of the recursive solution.
- [ ] Be aware of the iterative approach and its complexity.

## Similar Problems
1. Merge K Sorted Lists
2. Remove Duplicates from Sorted List II
3. Sort List

## Tags
`Recursion` `Linked List` `Two Pointers`
