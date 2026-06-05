# Remove Nth Node From End Of List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers`  
**Time:** O(L)  
**Space:** O(1)

---

## Solution (java)

```java
//slow-fast two pointer. one-pass approach.
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = new ListNode(0, head);
        ListNode dummy = res;

        for (int i = 0; i < n; i++) {
            head = head.next;
        }

        while (head != null) {
            head = head.next;
            dummy = dummy.next;
        }

        dummy.next = dummy.next.next;

        return res.next;        
    }
}

// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode() {}
//  *     ListNode(int val) { this.val = val; }
//  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//  * }
//  */
// class Solution {
//     public ListNode removeNthFromEnd(ListNode head, int n) {
//         if(head==null) return head;

//         int total = 0;
//         ListNode temp = head;
//         while(temp!=null){
//             temp = temp.next;
//             total++;
//         }

//         temp = head;
//         int toRemove = total-n-1;
//         if(toRemove<0) return head.next; //first node

//         for(int i=0;i<toRemove;i++) temp = temp.next;
//         if(temp.next == null) temp = null; //last node
//         else temp.next = temp.next.next;

//         return head;
//     }
// }
```

---

---
## Quick Revision
Remove the Nth node from the end of a singly linked list.
This is solved efficiently using the two-pointer (slow and fast) technique in a single pass.

## Intuition
The core idea is to maintain a fixed gap of `n` nodes between two pointers. When the "fast" pointer reaches the end of the list, the "slow" pointer will be positioned exactly before the node we need to remove. This avoids the need for a first pass to count the total number of nodes.

## Algorithm
1. Create a dummy node that points to the head of the list. This simplifies edge cases, especially when removing the head node.
2. Initialize two pointers, `fast` and `slow`, both starting at the dummy node.
3. Move the `fast` pointer `n` steps forward. This establishes the `n` node gap.
4. Now, move both `fast` and `slow` pointers one step at a time until `fast` reaches the end of the list (i.e., `fast` becomes `null`).
5. At this point, `slow` will be pointing to the node *before* the Nth node from the end.
6. To remove the Nth node, update `slow.next` to point to `slow.next.next`.
7. Return `dummy.next`, which is the new head of the modified list.

## Concept to Remember
*   **Singly Linked Lists:** Understanding node structure, traversal, and manipulation (changing `next` pointers).
*   **Two-Pointer Technique:** Using multiple pointers to traverse a data structure, often to find relationships or solve problems in a single pass.
*   **Dummy Node:** A common pattern to simplify linked list operations, especially at the head of the list.
*   **Edge Case Handling:** Considering scenarios like an empty list, removing the head, or removing the tail.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly calculating the position of the node to remove or the movement of pointers.
*   **Not handling the case where the head needs to be removed:** This often leads to incorrect results if not managed properly (e.g., by using a dummy node).
*   **Modifying the list while still traversing with the same pointer:** This can corrupt the list structure.
*   **Forgetting to return the correct head:** After modifications, ensure the original `head` (or the new head if the original was removed) is returned.

## Complexity Analysis
- Time: O(L) - reason: We traverse the linked list at most twice (once to advance the fast pointer `n` steps, and then both pointers traverse the rest of the list). If `n` is larger than the list length, the first loop might finish early, but the second loop will still cover the remaining nodes. In essence, it's a single pass over the list.
- Space: O(1) - reason: We only use a few extra pointers (dummy, fast, slow), which is constant extra space.

## Commented Code
```java
// Define the ListNode class if it's not provided globally.
// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val; // The value of the node.
//  *     ListNode next; // A reference to the next node in the list.
//  *     ListNode() {} // Default constructor.
//  *     ListNode(int val) { this.val = val; } // Constructor with value.
//  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; } // Constructor with value and next node.
//  * }
//  */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node that points to the head of the list.
        // This simplifies handling the case where the head itself needs to be removed.
        ListNode dummy = new ListNode(0, head);
        
        // Initialize two pointers, 'fast' and 'slow', both starting at the dummy node.
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move the 'fast' pointer 'n' steps ahead.
        // This creates a gap of 'n' nodes between 'fast' and 'slow'.
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Now, move both 'fast' and 'slow' pointers one step at a time
        // until the 'fast' pointer reaches the end of the list (becomes null).
        while (fast.next != null) { // We check fast.next because we want slow to stop *before* the node to be removed.
            fast = fast.next; // Move fast pointer forward.
            slow = slow.next; // Move slow pointer forward.
        }

        // At this point, 'slow' is pointing to the node *before* the Nth node from the end.
        // To remove the Nth node, we bypass it by setting 'slow.next' to 'slow.next.next'.
        slow.next = slow.next.next;

        // Return the head of the modified list, which is 'dummy.next'.
        // If the original head was removed, dummy.next will point to the new head.
        return dummy.next;        
    }
}
```

## Interview Tips
1.  **Explain the Dummy Node:** Clearly articulate why a dummy node is used and how it simplifies edge cases like removing the head.
2.  **Visualize the Pointers:** Draw out the linked list and the movement of the `fast` and `slow` pointers for a small example (e.g., list of 5 nodes, remove 2nd from end). This helps demonstrate your understanding.
3.  **Discuss the Two-Pointer Logic:** Explain the `n` step gap and why when `fast` reaches the end, `slow` is in the correct position to modify the list.
4.  **Consider Edge Cases:** Be prepared to discuss what happens if `n` is 1 (removing the last node), `n` is equal to the list length (removing the first node), or if the list is empty.

## Revision Checklist
- [ ] Understand singly linked list node structure.
- [ ] Implement the two-pointer (slow/fast) approach.
- [ ] Correctly handle the `n` step gap for the fast pointer.
- [ ] Ensure the slow pointer stops at the node *before* the one to be removed.
- [ ] Use a dummy node to simplify head removal.
- [ ] Correctly update `slow.next` to remove the target node.
- [ ] Return `dummy.next` as the new head.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Middle of the Linked List
*   Linked List Cycle
*   Remove Duplicates from Sorted List II
*   Reverse Linked List

## Tags
`Linked List` `Two Pointers`
