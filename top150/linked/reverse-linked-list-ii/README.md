# Reverse Linked List Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List`  
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left >= right) return head;
        ListNode temp = new ListNode(0);
        temp.next = head;
        
        ListNode start = temp;
        for(int i=1;i<left;i++)start = start.next;
        
        ListNode tail = start.next;
        ListNode near = tail.next;
        
        for(int i=0;i<right-left;i++){
            tail.next = near.next;
            near.next = start.next;
            start.next = near;
            near = tail.next; 
        }
        
        return temp.next;
    }
}
```

---

---
## Quick Revision
Reverse a portion of a singly linked list between two given positions.
This is solved by carefully manipulating pointers to reverse the sublist in-place.

## Intuition
The core idea is to identify the nodes *before* the reversal starts, the first node *of* the reversal, and the subsequent nodes to be reversed. We then iteratively move nodes from the "to be reversed" section to the beginning of that section, effectively reversing it. Finally, we reconnect the reversed sublist back into the main list. The use of a dummy node simplifies edge cases, especially when the reversal starts from the head.

## Algorithm
1.  **Handle Edge Cases**: If the list is empty or `left` is greater than or equal to `right`, return the original `head`.
2.  **Create a Dummy Node**: Initialize a dummy node `temp` pointing to the `head`. This simplifies handling reversals that start at the head of the list.
3.  **Find the Node Before the Reversal Start**: Traverse the list from `temp` until you reach the node just before the `left`-th node. Let's call this `start`.
4.  **Identify Initial Nodes for Reversal**:
    *   `tail`: This will be the first node of the sublist to be reversed (the `left`-th node). It's `start.next`.
    *   `near`: This will be the node immediately after `tail` (the `left + 1`-th node). It's `tail.next`.
5.  **Reverse the Sublist**: Iterate `right - left` times. In each iteration:
    *   **Relink `tail`**: Make `tail.next` point to `near.next`. This effectively detaches `near` from its original position and prepares `tail` to point to the node after `near`.
    *   **Insert `near` at the beginning of the reversed section**: Make `near.next` point to `start.next`. This makes `near` point to the current first node of the reversed segment.
    *   **Update `start.next`**: Make `start.next` point to `near`. This inserts `near` at the beginning of the reversed segment, right after `start`.
    *   **Advance `near`**: Update `near` to `tail.next`. This prepares `near` for the next iteration, pointing to the next node that needs to be moved.
6.  **Return the New Head**: Return `temp.next`, which will be the head of the modified list.

## Concept to Remember
*   Singly Linked List Manipulation: Understanding how to traverse and modify `next` pointers is crucial.
*   In-place Reversal: Efficiently reversing a portion without using extra data structures.
*   Pointer Management: Careful tracking and updating of multiple pointers to maintain list integrity.
*   Dummy Node Usage: Simplifying edge cases, particularly when modifications involve the head of the list.

## Common Mistakes
*   Incorrectly handling the `head` node if the reversal starts at position 1.
*   Off-by-one errors when calculating the number of iterations for reversal or when identifying `start`, `tail`, and `near`.
*   Losing track of the original `next` pointers, leading to broken links.
*   Not properly reconnecting the reversed sublist back to the rest of the original list.
*   Modifying pointers in an order that breaks the chain before the next node is identified.

## Complexity Analysis
- Time: O(N) - We traverse the list at most twice: once to find the `left` position and then to perform the reversal. In the worst case, `left` is 1 and `right` is N, so we traverse almost the entire list.
- Space: O(1) - We only use a few extra pointers (`temp`, `start`, `tail`, `near`), which is constant extra space.

## Commented Code
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // If the list is empty or the reversal range is invalid, return the head as is.
        if(head == null || left >= right) return head;
        
        // Create a dummy node to simplify edge cases, especially when reversing from the head.
        ListNode temp = new ListNode(0);
        // Point the dummy node's next to the original head.
        temp.next = head;
        
        // 'start' will point to the node *before* the reversal begins.
        ListNode start = temp;
        // Traverse 'start' to the node just before the 'left'-th node.
        for(int i=1;i<left;i++)start = start.next;
        
        // 'tail' is the first node of the sublist to be reversed (the 'left'-th node).
        ListNode tail = start.next;
        // 'near' is the node immediately after 'tail' (the 'left + 1'-th node).
        ListNode near = tail.next;
        
        // Perform the reversal for 'right - left' nodes.
        for(int i=0;i<right-left;i++){
            // 1. Detach 'near' from its current position by making 'tail.next' point to 'near.next'.
            tail.next = near.next;
            // 2. Make 'near.next' point to the current beginning of the reversed segment ('start.next').
            near.next = start.next;
            // 3. Insert 'near' at the beginning of the reversed segment by making 'start.next' point to 'near'.
            start.next = near;
            // 4. Advance 'near' to the next node that needs to be moved. This is now 'tail.next'.
            near = tail.next; 
        }
        
        // The dummy node's next now points to the head of the modified list.
        return temp.next;
    }
}
```

## Interview Tips
*   **Visualize Pointers**: Draw out the linked list and the pointers (`start`, `tail`, `near`) before and after each step of the reversal loop. This is crucial for understanding the logic.
*   **Explain the Dummy Node**: Clearly articulate why a dummy node is used and how it simplifies the code, especially for the `left = 1` case.
*   **Trace with Examples**: Walk through the code with a small example, like reversing from position 2 to 4 in a list of 5 nodes.
*   **Discuss Edge Cases**: Be prepared to discuss what happens if `left` or `right` are out of bounds, or if `left == right`.

## Revision Checklist
- [ ] Understand linked list node structure.
- [ ] Identify the node *before* the reversal start.
- [ ] Identify the first node *of* the reversal.
- [ ] Understand the pointer manipulation for in-place reversal.
- [ ] Correctly handle the loop termination condition.
- [ ] Ensure the reversed sublist is reconnected properly.
- [ ] Consider the dummy node's role.
- [ ] Analyze time and space complexity.

## Similar Problems
Reverse Linked List
Reverse Nodes in k-Group
Swap Nodes in Pairs
Remove Nth Node From End of List

## Tags
`Linked List` `Two Pointers`
