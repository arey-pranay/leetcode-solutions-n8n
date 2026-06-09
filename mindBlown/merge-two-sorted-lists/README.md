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
        if(list1 == null) return list2; if(list2 == null) return list1;
        if(list2.val < list1.val) return mergeTwoLists(list2,list1);
        ListNode head = list1;
        list1 = list1.next;
        head.next = mergeTwoLists(list1,list2);
        return head;
    }
}

// 1 3 4
// 2 4

// 1 -> (...) // 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> null

// -----------

// 3 4 
// 2 4

// 1 -> (...) // 1 -> 2 -> 3 -> 4 -> 4 -> null
// -------------

// 4 
// 3 4

// 2 -> (...) // 2 -> 3 -> 4 -> 4 -> null

// ---------------

// 4
// 4

// 3 -> (...) // 3 -> 4 -> 4 -> null

// ------------------

// _
// 4

// 4 -> (...) // 4 -> 4 -> null

// -------------

// _
// _

// 4 ->(...) // 4 -> null

// ------------


```

---

---
## Quick Revision
The problem asks to merge two sorted linked lists into a single sorted linked list.
This can be solved recursively by comparing the heads of the two lists and prepending the smaller one to the merged result of the rest.

## Intuition
The core idea is to build the new sorted list by always picking the smallest available node from either of the two input lists. Since both input lists are already sorted, we only need to compare their current head nodes. The smaller of the two heads will be the next node in our merged list. We then recursively merge the rest of the lists.

## Algorithm
1. **Base Cases:**
    * If both `list1` and `list2` are null, return null.
    * If `list1` is null, return `list2`.
    * If `list2` is null, return `list1`.
2. **Comparison:**
    * If the value of `list2`'s head is less than the value of `list1`'s head, swap `list1` and `list2` to ensure `list1` always points to the smaller head. This simplifies the recursive step.
3. **Recursive Step:**
    * Set the `next` pointer of the current `list1` head to the result of recursively merging the rest of `list1` (i.e., `list1.next`) with `list2`.
    * Return the current `list1` head, which is now the head of the merged sublist.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Linked Lists:** Understanding node structure, traversal, and pointer manipulation.
*   **Base Cases:** Crucial for terminating recursive functions and preventing infinite loops.
*   **In-place Modification:** Modifying the `next` pointers of existing nodes rather than creating new ones.

## Common Mistakes
*   **Forgetting Base Cases:** Not handling null lists or empty lists correctly can lead to NullPointerExceptions or infinite recursion.
*   **Incorrect Pointer Manipulation:** Mishandling `next` pointers can break the linked list structure or lead to incorrect merging.
*   **Not Swapping for Simplicity:** While not strictly an error, not ensuring `list1` always has the smaller head can make the recursive logic slightly more complex to write.
*   **Modifying Original Lists Unintentionally:** If the problem requires preserving the original lists, this recursive approach modifies them.

## Complexity Analysis
*   **Time:** O(N + M) - where N and M are the lengths of `list1` and `list2` respectively. Each node from both lists is visited and processed exactly once.
*   **Space:** O(N + M) - due to the recursion depth. In the worst case, the recursion stack can grow as deep as the total number of nodes if one list is much shorter than the other and its elements are interleaved.

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
        // If list2's head is smaller, swap list1 and list2 to simplify the recursive call.
        if(list2.val < list1.val) return mergeTwoLists(list2,list1);

        // If list1's head is smaller or equal, it will be the head of the merged list.
        ListNode head = list1;
        // Move list1 pointer to the next node.
        list1 = list1.next;
        // Recursively merge the rest of list1 with list2 and set it as the next of the current head.
        head.next = mergeTwoLists(list1,list2);
        // Return the head of the merged list.
        return head;
    }
}
```

## Interview Tips
1.  **Explain the Recursive Approach First:** Clearly articulate the base cases and the recursive step before diving into code.
2.  **Trace with an Example:** Walk through a small example (e.g., `[1,3,4]` and `[2,4]`) to demonstrate how the recursion unfolds and pointers are updated.
3.  **Discuss Iterative Solution (Optional but good):** Be prepared to discuss or even implement an iterative solution using a dummy head node. This shows a broader understanding.
4.  **Handle Edge Cases:** Explicitly mention how null lists and empty lists are handled by your base cases.

## Revision Checklist
- [ ] Understand the problem: merging two sorted linked lists.
- [ ] Identify base cases for recursion (both null, one null).
- [ ] Implement the comparison and selection of the smaller head node.
- [ ] Correctly set the `next` pointer for the chosen head node using recursion.
- [ ] Ensure the recursive call is made with the correct remaining sublists.
- [ ] Analyze time and space complexity.
- [ ] Consider an iterative approach as an alternative.

## Similar Problems
*   Merge K Sorted Lists
*   Sort List
*   Remove Duplicates from Sorted List II
*   Remove Duplicates from Sorted List

## Tags
`Recursion` `Linked List`
