# Swap Nodes In Pairs

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Recursion`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode curr = dummy;
      while(curr!=null){
          if(curr.next==null) break;
          curr.next = swapPair(curr.next,curr.next.next);
          curr = curr.next.next;
      }
      return dummy.next;
    }
    public ListNode swapPair(ListNode left , ListNode right){
      if(left==null || right==null) return left;
      ListNode temp = right.next;
      right.next = left;
      left.next= temp;
      return right;
    }
}
```

---

---
## Quick Revision
Given a singly linked list, swap every two adjacent nodes.
Solve by iterating through the list and swapping pairs using a dummy node for easier head management.

## Intuition
The core idea is to manipulate the `next` pointers of the nodes. When we swap two nodes, say `A` and `B` (where `A` comes before `B`), we need to ensure that the node *before* `A` now points to `B`, and `A` now points to whatever `B` was pointing to. A dummy node simplifies handling the head of the list, as we don't need a special case for swapping the first two nodes.

## Algorithm
1. Create a dummy node and point its `next` to the original `head` of the list. This dummy node will help us manage the head of the modified list.
2. Initialize a `curr` pointer to the dummy node. This pointer will traverse the list, always pointing to the node *before* the pair to be swapped.
3. Iterate while `curr` is not null:
    a. Check if `curr.next` is null. If it is, it means we've reached the end of the list or there's only one node left, so break the loop.
    b. Identify the two nodes to be swapped: `firstNode = curr.next` and `secondNode = curr.next.next`.
    c. Perform the swap:
        i. `curr.next = secondNode` (the node before the pair now points to the second node).
        ii. `firstNode.next = secondNode.next` (the first node now points to the node after the pair).
        iii. `secondNode.next = firstNode` (the second node now points to the first node).
    d. Move `curr` forward by two positions to point to the node *before* the next pair: `curr = firstNode` (which is now the second node of the swapped pair).
4. Return `dummy.next`, which is the new head of the swapped list.

## Concept to Remember
*   **Singly Linked Lists:** Understanding how to traverse and modify `next` pointers is crucial.
*   **Pointer Manipulation:** The core of this problem lies in correctly re-wiring the `next` pointers to achieve the swap.
*   **Dummy Node:** Using a dummy node simplifies edge cases, especially when modifying the head of the list.

## Common Mistakes
*   **Null Pointer Exceptions:** Forgetting to check for null nodes before accessing `node.next`, especially at the end of the list.
*   **Incorrect Pointer Rewiring:** Swapping the pointers in the wrong order, leading to lost nodes or incorrect list structure.
*   **Not Handling the Head:** Failing to correctly update the head of the list when the first two nodes are swapped, if not using a dummy node.
*   **Off-by-One Errors:** Incorrectly advancing the `curr` pointer after a swap, leading to skipping nodes or infinite loops.

## Complexity Analysis
*   **Time:** O(N) - We iterate through the linked list once, visiting each node a constant number of times.
*   **Space:** O(1) - We only use a few extra pointers (dummy, curr, temp), which is constant extra space.

## Commented Code
```java
class Solution {
    public ListNode swapPairs(ListNode head) {
      // Create a dummy node to simplify head manipulation.
      ListNode dummy = new ListNode(0);
      // Point the dummy node's next to the original head.
      dummy.next = head;
      // Initialize 'curr' to the dummy node. 'curr' will always point to the node *before* the pair to be swapped.
      ListNode curr = dummy;
      // Iterate through the list as long as 'curr' is not null.
      while(curr!=null){
          // If 'curr.next' is null, it means we've reached the end or there's only one node left. No more pairs to swap.
          if(curr.next==null) break;
          // Call the helper function to swap the next two nodes.
          // 'curr.next' is the first node of the pair, 'curr.next.next' is the second.
          // The result of swapPair will be the new head of the swapped pair, which we link to 'curr'.
          curr.next = swapPair(curr.next,curr.next.next);
          // Move 'curr' forward by two positions. Since 'curr.next' is now the *second* node of the swapped pair,
          // and its 'next' points to the original first node (which is now the third node in the sequence),
          // moving 'curr' to 'curr.next.next' positions it before the next potential pair.
          curr = curr.next.next;
      }
      // Return the next of the dummy node, which is the new head of the modified list.
      return dummy.next;
    }

    // Helper function to swap two adjacent nodes.
    // 'left' is the first node of the pair, 'right' is the second.
    public ListNode swapPair(ListNode left , ListNode right){
      // If either node is null, no swap is possible, return the 'left' node as is.
      if(left==null || right==null) return left;
      // Store the node that 'right' is currently pointing to. This will become the next node after the swapped pair.
      ListNode temp = right.next;
      // Make 'right' point to 'left'.
      right.next = left;
      // Make 'left' point to the node that 'right' was originally pointing to ('temp').
      left.next= temp;
      // Return 'right' because it is now the new head of this swapped pair.
      return right;
    }
}
```

## Interview Tips
*   **Visualize:** Draw out the linked list and the pointer changes on a whiteboard or paper. This is crucial for understanding the pointer manipulations.
*   **Explain the Dummy Node:** Clearly articulate why a dummy node is used and how it simplifies the logic, especially for the head case.
*   **Edge Cases:** Discuss how you handle an empty list, a list with one node, and a list with an odd number of nodes.
*   **Iterative vs. Recursive:** Be prepared to discuss both iterative and recursive solutions. The provided solution is iterative.

## Revision Checklist
- [ ] Understand linked list node structure.
- [ ] Practice pointer manipulation for swaps.
- [ ] Implement using a dummy node.
- [ ] Handle edge cases (empty list, single node, odd length).
- [ ] Trace execution with an example.

## Similar Problems
*   Reverse Linked List
*   Reverse Nodes in k-Group
*   Remove Duplicates from Sorted List II

## Tags
`Linked List` `Recursion` `Iteration`
