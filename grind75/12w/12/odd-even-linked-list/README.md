# Odd Even Linked List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode headA = head;
        ListNode headB = head.next;
        ListNode tailA = func(headA,headB);
        tailA.next = headB;
        return head;
    }
    public ListNode func(ListNode a, ListNode b){
      if(b==null || b.next==null) return a;
      a.next = a.next.next; //b.next
      b.next = b.next.next;
      return func(a.next,b.next);
    }
}

// 1 2 3 4 5
// a b

//   1->3
//   2->4
```

---

---
## Quick Revision
Given a singly linked list, reorder the list such that all odd-indexed nodes come before all even-indexed nodes.
This is solved by maintaining two separate pointers for odd and even nodes and linking them.

## Intuition
The core idea is to separate the nodes into two distinct lists: one for odd-indexed nodes and one for even-indexed nodes. We can achieve this by iterating through the list and, for each node, deciding whether it belongs to the odd or even list based on its position. Then, we can link the tail of the odd list to the head of the even list.

## Algorithm
1. Handle edge cases: If the list is empty or has only one node, return the head as is.
2. Initialize two pointers: `oddHead` and `evenHead`. `oddHead` will point to the first node (index 0, which is odd), and `evenHead` will point to the second node (index 1, which is even).
3. Initialize two tail pointers: `oddTail` and `evenTail`, both initially pointing to `oddHead` and `evenHead` respectively.
4. Iterate through the list starting from the third node.
5. In each iteration, check if the current node is at an odd or even position.
6. If it's an odd position, append it to the `oddTail` and update `oddTail`.
7. If it's an even position, append it to the `evenTail` and update `evenTail`.
8. After the loop, link the `oddTail` to the `evenHead`.
9. Set the `next` pointer of `evenTail` to `null` to terminate the list.
10. Return `oddHead`.

## Concept to Remember
*   Linked List Traversal: Efficiently moving through nodes using `next` pointers.
*   Pointer Manipulation: Carefully updating `next` pointers to rearrange the list structure.
*   In-place Modification: Modifying the existing linked list without creating new nodes.

## Common Mistakes
*   Incorrectly handling the `null` checks for `head`, `head.next`, and subsequent nodes during iteration.
*   Forgetting to set the `next` pointer of the last even node to `null`, which can lead to cycles or incorrect list termination.
*   Losing track of the tail pointers for both odd and even lists, making it difficult to append new nodes.
*   Confusing node values with node indices when determining odd/even placement.

## Complexity Analysis
- Time: O(N) - We traverse the linked list once.
- Space: O(1) - We only use a few extra pointers, regardless of the list size.

## Commented Code
```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        // If the list is empty or has only one node, no rearrangement is needed.
        if (head == null || head.next == null) {
            return head;
        }

        // Initialize pointer for the head of the odd-indexed list.
        ListNode oddHead = head;
        // Initialize pointer for the head of the even-indexed list.
        ListNode evenHead = head.next;

        // Initialize tail pointer for the odd-indexed list.
        ListNode oddTail = oddHead;
        // Initialize tail pointer for the even-indexed list.
        ListNode evenTail = evenHead;

        // Iterate through the list starting from the third node.
        // We use evenTail to check for the end of the list and to advance.
        while (evenTail != null && evenTail.next != null) {
            // Link the next odd node: skip the current even node.
            oddTail.next = evenTail.next;
            // Move the odd tail to the newly added odd node.
            oddTail = oddTail.next;

            // Link the next even node: skip the current odd node.
            evenTail.next = oddTail.next;
            // Move the even tail to the newly added even node.
            evenTail = evenTail.next;
        }

        // After separating, connect the tail of the odd list to the head of the even list.
        oddTail.next = evenHead;

        // Return the head of the rearranged list (which is the original head).
        return oddHead;
    }
}
```

## Interview Tips
*   Clearly explain your pointer manipulation strategy before coding.
*   Walk through an example (e.g., 1->2->3->4->5) with your pointers to ensure correctness.
*   Pay close attention to the termination condition of your loop and the final linking step.
*   Be prepared to discuss the space and time complexity of your solution.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Identify edge cases (empty list, single node list).
- [ ] Devise a strategy to separate odd and even nodes.
- [ ] Implement pointer manipulation correctly.
- [ ] Handle the linking of the two sub-lists.
- [ ] Ensure the list is properly terminated.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Remove Nth Node From End of List
*   Swap Nodes in Pairs
*   Reverse Linked List II

## Tags
`Linked List` `Two Pointers`
