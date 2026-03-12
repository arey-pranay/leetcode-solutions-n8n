# Remove Duplicates From Sorted List Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while(curr != null) {
            while(curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }
            if(prev.next == curr) {
                prev = prev.next;
            } else {
                prev.next = curr.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}
```

---

---
## Quick Revision
Given a sorted linked list, remove all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
This is solved by using a dummy node and iterating through the list, skipping over duplicate sequences.

## Intuition
The core idea is to identify and completely remove any sequence of nodes with the same value. Since the list is sorted, duplicates will always be adjacent. We need a way to "look ahead" and detect these duplicate sequences. A dummy node is crucial here because it simplifies handling the head of the list, especially when the head itself might be part of a duplicate sequence that needs to be removed. We maintain a `prev` pointer to link the non-duplicate nodes correctly.

## Algorithm
1. Create a dummy node and set its `next` pointer to the `head` of the original linked list. This dummy node will act as a placeholder before the actual head, simplifying edge cases.
2. Initialize a `prev` pointer to the dummy node. This pointer will track the last node that is guaranteed to be unique.
3. Initialize a `curr` pointer to the `head` of the original linked list. This pointer will traverse the list.
4. Start a `while` loop that continues as long as `curr` is not null.
5. Inside the outer loop, start an inner `while` loop. This inner loop checks if `curr.next` is not null AND if the value of `curr` is equal to the value of `curr.next`. If both conditions are true, it means we've found a duplicate.
6. Inside the inner loop, advance `curr` to `curr.next` to skip over the duplicate node. This continues until `curr` points to the last node in a sequence of duplicates, or until `curr.next` is null.
7. After the inner loop finishes, check if `prev.next` is still pointing to `curr`.
    * If `prev.next == curr`, it means `curr` was not part of a duplicate sequence (or it was the first node of a duplicate sequence that was handled by the inner loop, and `curr` is now pointing to the next distinct element). In this case, advance `prev` to `prev.next` to include `curr` in the result list.
    * If `prev.next != curr`, it means `curr` was the start of a duplicate sequence that was skipped by the inner loop. Therefore, we need to bypass this entire duplicate sequence by setting `prev.next` to `curr.next`. This effectively removes the duplicate sequence from the list.
8. After the `if-else` block, advance `curr` to `curr.next` to move to the next node in the original list for the next iteration of the outer loop.
9. Once the outer `while` loop finishes, return `dummy.next`, which will be the head of the modified linked list with all duplicates removed.

## Concept to Remember
*   **Linked List Manipulation:** Understanding how to traverse and modify linked lists, particularly by adjusting `next` pointers.
*   **Dummy Nodes:** The utility of dummy nodes in simplifying edge cases, especially at the beginning of a linked list.
*   **Two-Pointer Technique:** Using `prev` and `curr` pointers to manage different parts of the list simultaneously.
*   **Handling Adjacent Duplicates:** Recognizing that sorted lists group duplicates together, allowing for efficient detection.

## Common Mistakes
*   **Not handling the head correctly:** Failing to use a dummy node can lead to complex logic for removing duplicates at the very beginning of the list.
*   **Incorrectly advancing pointers:** Advancing `prev` or `curr` at the wrong time or in the wrong order can lead to skipping nodes or creating cycles.
*   **Not fully skipping duplicates:** The inner loop must advance `curr` past *all* nodes in a duplicate sequence, not just the first one.
*   **Modifying `prev.next` before checking if `curr` was a duplicate:** This can lead to losing track of the correct previous node.
*   **Forgetting to advance `curr` at the end of the outer loop:** This will result in an infinite loop.

## Complexity Analysis
- Time: O(N) - reason: Each node in the linked list is visited at most a constant number of times by `curr` and `prev` pointers.
- Space: O(1) - reason: We are only using a few extra pointers (`dummy`, `prev`, `curr`), which is constant extra space.

## Commented Code
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Create a dummy node to simplify edge cases, especially when the head itself needs to be removed.
        ListNode dummy = new ListNode(-1);
        // Link the dummy node to the original head of the list.
        dummy.next = head;
        // 'prev' pointer tracks the last node that is guaranteed to be unique and part of the result list.
        ListNode prev = dummy;
        // 'curr' pointer traverses the list to identify duplicates.
        ListNode curr = head;

        // Iterate through the linked list as long as 'curr' is not null.
        while(curr != null) {
            // Inner loop: Check for consecutive duplicate nodes.
            // Continue as long as 'curr' has a next node AND 'curr's value is the same as its next node's value.
            while(curr.next != null && curr.val == curr.next.val) {
                // If duplicates are found, advance 'curr' to skip the duplicate node.
                curr = curr.next;
            }
            // After the inner loop, 'curr' points to the last node of a potential duplicate sequence,
            // or it's a unique node if no duplicates were found.

            // Check if 'prev.next' is still pointing to 'curr'.
            // If it is, it means 'curr' was not part of a duplicate sequence that needed removal (or it was the first of a sequence that was handled).
            if(prev.next == curr) {
                // If 'curr' is unique (or the first of a sequence that was handled), advance 'prev' to include 'curr' in the result.
                prev = prev.next;
            } else {
                // If 'prev.next' is NOT 'curr', it means 'curr' was the start of a duplicate sequence that was skipped by the inner loop.
                // We need to bypass this entire duplicate sequence by linking 'prev.next' to 'curr.next'.
                prev.next = curr.next;
            }
            // Advance 'curr' to the next node in the original list to continue the traversal.
            curr = curr.next;
        }
        // Return the head of the modified list, which is 'dummy.next'.
        return dummy.next;
    }
}
```

## Interview Tips
1.  **Explain the Dummy Node:** Clearly articulate why a dummy node is used and how it simplifies handling the head of the list.
2.  **Trace with an Example:** Walk through a small example (e.g., `1->2->3->3->4->4->5`) to demonstrate how `prev` and `curr` move and how `prev.next` is updated.
3.  **Clarify Pointer Movements:** Be precise about when and why `prev` and `curr` are advanced, especially in relation to the inner and outer loops.
4.  **Discuss Edge Cases:** Mention how the dummy node helps with cases like an empty list, a list with all duplicates, or a list where the head is a duplicate.

## Revision Checklist
- [ ] Understand the problem statement: remove *all* nodes with duplicate values.
- [ ] Recognize sorted list property: duplicates are adjacent.
- [ ] Implement dummy node for head handling.
- [ ] Use `prev` and `curr` pointers effectively.
- [ ] Correctly implement the inner loop to skip *all* duplicates.
- [ ] Correctly update `prev.next` based on whether `curr` was a duplicate.
- [ ] Ensure `curr` is advanced in each outer loop iteration.
- [ ] Verify complexity: Time O(N), Space O(1).

## Similar Problems
Remove Duplicates from Sorted List
Remove Duplicates from Sorted List II (this problem)
Remove Nth Node From End of List
Swap Nodes in Pairs
Remove Linked List Elements

## Tags
`Linked List` `Two Pointers` `Recursion`
