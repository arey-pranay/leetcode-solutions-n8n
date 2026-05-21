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
This is solved efficiently using a two-pointer approach in a single pass.

## Intuition
The core idea is to maintain a gap of `n` nodes between two pointers. When the faster pointer reaches the end of the list, the slower pointer will be exactly at the node *before* the one we need to remove. This allows us to modify the `next` pointer of the slower node to skip the Nth node from the end.

## Algorithm
1. Create a dummy node that points to the `head` of the list. This simplifies edge cases, especially when removing the head node.
2. Initialize two pointers, `fast` and `slow`, both pointing to the dummy node.
3. Move the `fast` pointer `n` steps forward. This establishes the `n` node gap.
4. Now, move both `fast` and `slow` pointers one step at a time until `fast` reaches the end of the list (i.e., `fast` becomes `null`).
5. At this point, `slow` will be pointing to the node *before* the Nth node from the end.
6. Update `slow.next` to `slow.next.next` to effectively remove the Nth node.
7. Return `dummy.next`, which is the new head of the modified list.

## Concept to Remember
*   **Singly Linked Lists:** Understanding node structure, traversal, and modification of `next` pointers.
*   **Two-Pointer Technique:** Using multiple pointers to traverse a data structure simultaneously, often to find relationships between elements or to optimize traversal.
*   **Dummy Node:** A common technique to simplify linked list operations, especially those involving the head of the list, by providing a consistent starting point.
*   **Edge Case Handling:** Recognizing and addressing scenarios like an empty list, removing the head, or removing the tail.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly calculating the position of the node to remove or the stopping condition for pointers.
*   **Not handling the dummy node correctly:** Forgetting to use a dummy node can lead to complex conditional logic for removing the head.
*   **Modifying the list while iterating incorrectly:** If `fast` and `slow` are not advanced together, the gap will be lost.
*   **Not returning the correct head:** After modifications, ensure the original `head` (or the new head if the original was removed) is returned.

## Complexity Analysis
- Time: O(L) - reason: We traverse the linked list at most twice (once to set up the gap, once to find the node before removal).
- Space: O(1) - reason: We only use a few extra pointers, which is constant space.

## Commented Code
```java
// Define the ListNode class if it's not provided globally
// public class ListNode {
//     int val;
//     ListNode next;
//     ListNode() {}
//     ListNode(int val) { this.val = val; }
//     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// }

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases, especially removing the head.
        // The dummy node's next pointer points to the original head.
        ListNode res = new ListNode(0, head);
        // Initialize a pointer 'dummy' to the dummy node. This pointer will eventually point to the node *before* the one to be removed.
        ListNode dummy = res;

        // Move the 'head' pointer 'n' steps forward. This creates a gap of 'n' nodes between 'dummy' and 'head'.
        // After this loop, 'head' will be 'n' nodes ahead of the original 'head' (or null if n is larger than list length).
        for (int i = 0; i < n; i++) {
            head = head.next;
        }

        // Now, move both 'head' (which is 'n' steps ahead) and 'dummy' one step at a time.
        // This continues until 'head' reaches the end of the list (becomes null).
        // When 'head' is null, 'dummy' will be pointing to the node *before* the Nth node from the end.
        while (head != null) {
            head = head.next; // Move the fast pointer (originally 'head') forward
            dummy = dummy.next; // Move the slow pointer ('dummy') forward
        }

        // 'dummy' is now at the node preceding the Nth node from the end.
        // To remove the Nth node, we bypass it by setting dummy.next to dummy.next.next.
        dummy.next = dummy.next.next;

        // Return the head of the modified list, which is dummy.next.
        // If the original head was removed, dummy.next will correctly point to the second node.
        return res.next;
    }
}
```

## Interview Tips
1.  **Explain the Two-Pointer Approach:** Clearly articulate why the two-pointer technique is suitable and how the gap is maintained.
2.  **Discuss the Dummy Node:** Emphasize the role of the dummy node in simplifying edge cases, particularly removing the first element.
3.  **Trace an Example:** Walk through a small linked list (e.g., `1->2->3->4->5`, `n=2`) to show how the pointers move and the node is removed.
4.  **Consider Edge Cases:** Be prepared to discuss what happens if `n` is equal to the list length (removing the head), `n=1` (removing the tail), or if the list is empty.

## Revision Checklist
- [ ] Understand singly linked list traversal and modification.
- [ ] Implement the two-pointer (fast/slow) technique.
- [ ] Correctly use a dummy node to handle head removal.
- [ ] Accurately calculate pointer movements to maintain the `n` node gap.
- [ ] Handle edge cases like empty lists or `n` being equal to list length.

## Similar Problems
*   Reverse Linked List
*   Merge Two Sorted Lists
*   Linked List Cycle
*   Middle of the Linked List

## Tags
`Linked List` `Two Pointers`

## My Notes
used slow-fast pointers for one-pass approach.
