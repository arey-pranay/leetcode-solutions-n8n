# Rotate List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers`  
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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int n = 0;
        ListNode temp = head;
        
        while(temp!=null && n++ < Integer.MAX_VALUE) temp = temp.next;
        k %= n;
        if(k==0) return head;
        temp = head;
        
        for(int i=1;i<n-k;i++) temp = temp.next;
        
        ListNode start = temp.next;
        temp.next = null;
        temp = start;
        while(temp.next != null) temp = temp.next;

        temp.next = head;
        return start;
    }
}
```

---

---
## Quick Revision
Rotate a linked list to the right by k places.
Connect the end of the list to the beginning and break it at the correct new tail.

## Intuition
The core idea is to treat the linked list as a circular structure temporarily. If we rotate right by `k` places, the last `k` nodes become the new head. To achieve this, we can find the length of the list, calculate the effective `k` (handling cases where `k` is larger than the list length), find the node that will become the new tail (which is `n-k` nodes from the start), break the link after it, and then connect the original tail to the original head.

## Algorithm
1. Handle edge cases: If the list is empty, has only one node, or `k` is 0, return the original head.
2. Calculate the length of the linked list (`n`).
3. Calculate the effective rotation amount: `k = k % n`. If `k` becomes 0 after this, no rotation is needed, so return the original head.
4. Find the node that will become the new tail. This node is `n - k - 1` steps from the original head (or `n - k` nodes from the start, if we consider the first node as index 0).
5. Store the node after the new tail as the new head.
6. Break the link: Set the `next` pointer of the new tail to `null`.
7. Find the original tail of the list.
8. Connect the original tail to the original head.
9. Return the new head.

## Concept to Remember
*   Linked List Manipulation: Traversing, finding length, breaking and reconnecting nodes.
*   Modulo Operator: Efficiently handling rotations where `k` is greater than the list length.
*   Circular Linked List Logic: Temporarily treating the list as circular to simplify rotation.

## Common Mistakes
*   Not handling edge cases like an empty list, single-node list, or `k=0`.
*   Incorrectly calculating the position of the new tail node (off-by-one errors).
*   Failing to handle `k` values larger than the list length by not using the modulo operator.
*   Not properly reconnecting the original tail to the original head after breaking the list.
*   Modifying the list while iterating to find the length, leading to incorrect length calculation.

## Complexity Analysis
- Time: O(N) - reason: We traverse the list twice: once to find its length and the original tail, and once to find the new tail.
- Space: O(1) - reason: We only use a few extra pointers, regardless of the list size.

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
    public ListNode rotateRight(ListNode head, int k) {
        // Handle edge cases: if the list is empty or has only one node, no rotation is possible.
        if(head == null || head.next == null) return head;

        // Initialize a counter for the list length and a temporary pointer to traverse the list.
        int n = 0;
        ListNode temp = head;

        // Traverse the list to find its length (n) and also to reach the end of the list.
        // The loop condition `n++ < Integer.MAX_VALUE` is a safe way to count nodes without overflow
        // and ensures we stop if the list is extremely long (though practically not an issue for typical LeetCode constraints).
        while(temp!=null && n++ < Integer.MAX_VALUE) temp = temp.next;

        // Calculate the effective rotation amount by taking k modulo n.
        // This handles cases where k is larger than the list length.
        k %= n;

        // If k is 0 after the modulo operation, it means no rotation is needed, so return the original head.
        if(k==0) return head;

        // Reset the temporary pointer to the head of the list.
        temp = head;

        // Traverse the list to find the node that will become the new tail.
        // This node is (n - k - 1) steps from the head.
        // The loop runs n - k - 1 times. For example, if n=5, k=2, we need to move 5-2-1 = 2 steps.
        // The node at index 2 will be the new tail.
        for(int i=1;i<n-k;i++) temp = temp.next;

        // 'start' will be the new head of the rotated list. It's the node immediately after the new tail.
        ListNode start = temp.next;

        // Break the link after the new tail node. This effectively splits the list.
        temp.next = null;

        // Move the temporary pointer to the current end of the list (which is the original tail).
        temp = start;
        while(temp.next != null) temp = temp.next;

        // Connect the original tail to the original head, forming a circular link temporarily.
        temp.next = head;

        // Return the new head of the rotated list.
        return start;
    }
}
```

## Interview Tips
*   Clearly explain the "circular list" intuition before diving into code.
*   Walk through an example with `k` larger than `n` and `k=0` to show you've considered edge cases.
*   Be prepared to discuss the time and space complexity and justify your choices.
*   If asked, consider alternative approaches (e.g., using two pointers to find the split point simultaneously, though the provided solution is efficient).

## Revision Checklist
- [ ] Understand the problem: Rotate a linked list right by `k` positions.
- [ ] Handle edge cases: empty list, single node, `k=0`.
- [ ] Calculate list length `n`.
- [ ] Calculate effective `k` using modulo: `k = k % n`.
- [ ] Find the new tail node (at index `n - k - 1`).
- [ ] Identify the new head node (node after new tail).
- [ ] Break the list at the new tail.
- [ ] Find the original tail.
- [ ] Connect original tail to original head.
- [ ] Return the new head.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Reverse Linked List II
*   Remove Nth Node From End of List
*   Swap Nodes in Pairs
*   Rotate Image (2D array rotation, different concept but similar "rotation" idea)

## Tags
`Linked List` `Two Pointers`
