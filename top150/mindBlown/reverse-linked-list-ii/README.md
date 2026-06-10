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
        System.out.println(start.val);
        
        ListNode curr = start.next;
        ListNode near = null; 
        ListNode prev = null; 
        
        for(int i=left;i<=right;i++){
            near = curr.next; 
            curr.next = prev; 
            prev = curr;  
            curr = near; 
        }
        
        ListNode l = start.next;
        start.next = prev;
        l.next = curr;
        
        return temp.next;
    }
}
```

---

---
## Quick Revision
Reverse a portion of a singly linked list between two given positions.
This is achieved by identifying the nodes before, at the start, and at the end of the sublist to be reversed, and then performing a standard linked list reversal on that sublist.

## Intuition
The core idea is to isolate the sublist that needs to be reversed. Once we have pointers to the node *before* the start of the sublist, the *first* node of the sublist, and the node *after* the end of the sublist, we can treat the sublist as a standalone linked list and reverse it. Finally, we reconnect the reversed sublist back into the original list.

## Algorithm
1.  Handle edge cases: If the list is empty or `left` is greater than or equal to `right`, return the original `head`.
2.  Create a dummy node (`temp`) and point its `next` to the original `head`. This simplifies handling the case where `left` is 1.
3.  Iterate `left - 1` times from the dummy node to find the node *before* the start of the reversal section. Let's call this `start`.
4.  Initialize `curr` to `start.next` (the first node to be reversed) and `prev` to `null`.
5.  Iterate from `left` to `right` (inclusive):
    *   Store `curr.next` in a temporary variable `near`.
    *   Reverse the `next` pointer of `curr` to point to `prev`.
    *   Move `prev` to `curr`.
    *   Move `curr` to `near`.
6.  After the loop, `prev` will point to the new head of the reversed sublist, and `curr` will point to the node immediately after the reversed sublist.
7.  Let `l` be the original `start.next` (which is now the tail of the reversed sublist).
8.  Connect `start.next` to `prev` (linking the node before the sublist to the new head of the reversed sublist).
9.  Connect `l.next` to `curr` (linking the tail of the reversed sublist to the node after the sublist).
10. Return `temp.next` (the head of the modified list).

## Concept to Remember
*   **Singly Linked List Manipulation:** Understanding how to traverse and modify `next` pointers is crucial.
*   **In-place Reversal:** Reversing a linked list segment without using extra data structures (beyond a few pointers).
*   **Dummy Node:** Using a dummy node simplifies edge cases, especially when modifying the head of the list.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly calculating the number of iterations to reach `left` or `right`, or mismanaging the loop bounds for reversal.
*   **Losing track of pointers:** Not properly storing `curr.next` before overwriting `curr.next` during reversal, leading to broken links.
*   **Incorrectly reconnecting the list:** Failing to properly link the node *before* the reversed section to the new head of the reversed section, and the tail of the reversed section to the node *after* it.
*   **Not handling `left = 1` case:** Without a dummy node, special logic is needed if the reversal starts from the head.

## Complexity Analysis
- Time: O(N) - We traverse the list at most twice: once to find the `left` boundary and once to reverse the sublist. In the worst case, `left` is 1 and `right` is N, so we traverse almost the entire list.
- Space: O(1) - We only use a constant number of extra pointers (`temp`, `start`, `curr`, `near`, `prev`, `l`).

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
        // Handle edge cases: if the list is null or the reversal range is invalid.
        if(head == null || left >= right) return head;
        
        // Create a dummy node to simplify handling the case where left = 1.
        ListNode temp = new ListNode(0);
        // Point the dummy node's next to the original head.
        temp.next = head;
        
        // Initialize 'start' to the dummy node.
        ListNode start = temp;
        // Traverse 'left - 1' steps to reach the node *before* the reversal starts.
        for(int i=1;i<left;i++) {
            start = start.next;
        }
        // 'start' is now the node right before the sublist to be reversed.
        // System.out.println(start.val); // Debugging line, not essential for logic.
        
        // 'curr' is the first node of the sublist to be reversed.
        ListNode curr = start.next; 
        // 'near' will temporarily store the next node during reversal.
        ListNode near = null; 
        // 'prev' will store the previously reversed node. Initially null.
        ListNode prev = null;  
        
        // Perform the reversal of the sublist from 'left' to 'right'.
        for(int i=left;i<=right;i++){
            // Store the next node before modifying curr.next.
            near = curr.next; 
            // Reverse the pointer: current node points to the previous node.
            curr.next = prev;  
            // Move 'prev' to the current node, as it's now the "previous" for the next iteration.
            prev = curr;       
            // Move 'curr' to the next node in the original list.
            curr = near;       
        }
        
        // 'l' is the original start.next, which is now the tail of the reversed sublist.
        ListNode l = start.next;
        // Connect the node before the sublist ('start') to the new head of the reversed sublist ('prev').
        start.next = prev;
        // Connect the tail of the reversed sublist ('l') to the node after the sublist ('curr').
        l.next = curr;
        
        // Return the head of the modified list, which is temp.next.
        return temp.next;
    }
}
```

## Interview Tips
*   **Visualize the pointers:** Draw out the linked list and the pointers (`start`, `curr`, `prev`, `near`) as you trace the algorithm. This is crucial for understanding the connections.
*   **Explain the dummy node:** Clearly articulate why a dummy node is used and how it simplifies the logic, especially for the `left = 1` case.
*   **Trace with examples:** Walk through the code with a small linked list and different `left`/`right` values (e.g., `left=1`, `right=N`, `left` and `right` in the middle).
*   **Focus on reconnection:** Emphasize the steps where the reversed sublist is reconnected to the main list, as this is a common point of error.

## Revision Checklist
- [ ] Understand linked list node structure.
- [ ] Identify the sublist to reverse.
- [ ] Implement in-place reversal of a linked list segment.
- [ ] Correctly reconnect the reversed segment to the original list.
- [ ] Handle edge cases (empty list, `left >= right`).
- [ ] Use a dummy node effectively.

## Similar Problems
*   Reverse Linked List
*   Reverse Nodes in k-Group

## Tags
`Linked List` `Two Pointers`
