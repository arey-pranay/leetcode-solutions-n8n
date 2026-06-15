# Delete The Middle Node Of A Linked List

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) return null;

        ListNode slow = head;
        ListNode fast = slow.next.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = slow.next.next;
        return head;
    }
}
```

---

---
## Quick Revision
Given a singly linked list, delete the middle node.
Use the fast and slow pointer technique to find the middle node and then delete it.

## Intuition
The core idea is to find the middle node efficiently. A common technique for this in linked lists is the "fast and slow pointer" approach. If a fast pointer moves two steps at a time and a slow pointer moves one step at a time, when the fast pointer reaches the end of the list, the slow pointer will be at the middle. To delete the middle node, we need a pointer to the node *before* the middle node. By starting the fast pointer one step ahead of the slow pointer (or two steps ahead, depending on how you define "middle" for even length lists), we can ensure the slow pointer ends up at the node *preceding* the middle node.

## Algorithm
1. Handle the edge case: If the list has only one node (`head.next == null`), the middle node is the head itself, and deleting it results in an empty list, so return `null`.
2. Initialize two pointers: `slow` to `head` and `fast` to `head.next.next`. This setup ensures that `slow` will point to the node *before* the middle node when `fast` reaches the end.
3. Iterate while `fast` and `fast.next` are not `null`:
    a. Move `slow` one step forward (`slow = slow.next`).
    b. Move `fast` two steps forward (`fast = fast.next.next`).
4. After the loop, `slow` points to the node immediately before the middle node.
5. Delete the middle node by updating `slow.next` to skip the middle node: `slow.next = slow.next.next`.
6. Return the original `head` of the modified list.

## Concept to Remember
*   **Linked List Traversal**: Understanding how to iterate through a linked list.
*   **Fast and Slow Pointers**: A common pattern for finding the middle, detecting cycles, or determining list length.
*   **Node Manipulation**: How to modify `next` pointers to insert or delete nodes.

## Common Mistakes
*   Incorrectly initializing the `fast` pointer, leading to `slow` not being at the node *before* the middle.
*   Not handling the edge case of a single-node list.
*   Off-by-one errors when calculating the middle node's position, especially for even-length lists.
*   Trying to delete the middle node directly without having a pointer to the preceding node.

## Complexity Analysis
*   Time: O(N) - The `fast` pointer traverses at most the entire list, and the `slow` pointer traverses half the list.
*   Space: O(1) - We only use a constant amount of extra space for the `slow` and `fast` pointers.

## Commented Code
```java
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        // Handle the edge case where the list has only one node.
        // Deleting the middle node (which is the head) results in an empty list.
        if (head.next == null) {
            return null; // Return null to represent an empty list.
        }

        // Initialize the slow pointer to the head of the list.
        ListNode slow = head;
        // Initialize the fast pointer to the node *after* the next node of head.
        // This setup ensures that when fast reaches the end, slow will be at the node *before* the middle.
        ListNode fast = head.next.next;

        // Iterate as long as the fast pointer and its next node are not null.
        // This condition ensures we don't go out of bounds when fast moves two steps.
        while (fast != null && fast.next != null) {
            // Move the slow pointer one step forward.
            slow = slow.next;
            // Move the fast pointer two steps forward.
            fast = fast.next.next;
        }

        // At this point, 'slow' is pointing to the node immediately before the middle node.
        // To delete the middle node, we bypass it by setting slow.next to the node after the middle node.
        slow.next = slow.next.next;

        // Return the head of the modified linked list.
        return head;
    }
}
```

## Interview Tips
*   Clearly explain the fast and slow pointer approach and why you chose it.
*   Walk through an example with an odd and an even number of nodes to demonstrate how your pointers move.
*   Pay close attention to edge cases like a list with one or two nodes.
*   Be prepared to discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand linked list basics.
- [ ] Master the fast and slow pointer technique.
- [ ] Practice handling edge cases (empty list, single node list).
- [ ] Be able to explain pointer manipulation for deletion.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Middle of the Linked List
*   Linked List Cycle
*   Remove Nth Node From End of List

## Tags
`Linked List` `Two Pointers`
