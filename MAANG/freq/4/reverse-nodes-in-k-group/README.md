# Reverse Nodes In K Group

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Linked List` `Recursion`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode temp = head;
        int total = 0;
        while(temp!=null){temp=temp.next;total++;}
        
        temp = dummy;
        total -= (total%k);
        for(int i=0;i<total;i+=k){
          ListNode[] ans = rev(temp.next,k); 
          temp.next =  ans[0];
          temp = ans[1];
        }
        
        return dummy.next;
    }
    public ListNode[] rev(ListNode head , int k){
      ListNode curr = head;
      ListNode prev = null;
      while(k-->0){
        ListNode fwd = curr.next;
        curr.next = prev;
        prev = curr;
        curr = fwd;
      }
      head.next = curr;
      return new ListNode[]{prev,head};
    }
}    
```

---

---
## Quick Revision
Reverse nodes of a linked list in groups of k.
Solve by iterating through the list, reversing k nodes at a time, and re-linking them.

## Intuition
The core idea is to treat the linked list as a sequence of segments, each of length `k`. We need to reverse each of these segments independently and then connect them back. A dummy node is crucial to simplify handling the head of the list. We also need to ensure we only reverse full groups of `k` nodes, leaving any remaining nodes at the end as they are.

## Algorithm
1.  **Initialization**:
    *   Create a `dummy` node pointing to the `head` of the list. This simplifies edge cases, especially for the first group.
    *   Initialize a `prev` pointer (or `temp` in the provided solution) to the `dummy` node. This pointer will track the node *before* the current group to be reversed.
2.  **Count Total Nodes**:
    *   Iterate through the list to count the total number of nodes.
3.  **Determine Reversible Groups**:
    *   Calculate how many full groups of `k` nodes can be reversed. This is `total_nodes - (total_nodes % k)`.
4.  **Iterate and Reverse Groups**:
    *   Loop from `0` up to the number of reversible nodes, incrementing by `k` in each step.
    *   In each iteration:
        *   Identify the start of the current group: `group_start = prev.next`.
        *   Call a helper function `reverseKNodes(group_start, k)` to reverse `k` nodes starting from `group_start`. This function should return two values: the new head of the reversed group and the new tail of the reversed group (which was the original `group_start`).
        *   Re-link the list:
            *   `prev.next = new_group_head` (connect the previous segment to the reversed group).
            *   `original_group_start.next = next_group_start` (connect the tail of the reversed group to the start of the next segment).
        *   Update `prev` to be the tail of the just-reversed group (which was the original `group_start`). This prepares `prev` for the next iteration.
5.  **Return**:
    *   Return `dummy.next`, which will be the new head of the modified list.

**Helper Function `reverseKNodes(head, k)`**:
1.  Initialize `curr = head`, `prev = null`.
2.  Loop `k` times:
    *   Store `next_node = curr.next`.
    *   Reverse the pointer: `curr.next = prev`.
    *   Move `prev` and `curr`: `prev = curr`, `curr = next_node`.
3.  After the loop, `prev` is the new head of the reversed `k` nodes, and `head` (the original `head` passed to the function) is now the tail.
4.  Connect the tail of the reversed group to the `curr` node (which is the start of the next segment): `head.next = curr`.
5.  Return `[prev, head]` (new head, new tail).

## Concept to Remember
*   **Linked List Manipulation**: Reversing a portion of a linked list requires careful pointer management.
*   **Dummy Nodes**: Useful for simplifying head/tail operations and edge cases in linked list problems.
*   **Iterative Reversal**: Understanding how to reverse a fixed number of nodes in a linked list.
*   **Group Processing**: Breaking down a problem into processing fixed-size segments.

## Common Mistakes
*   **Off-by-one errors**: Incorrectly handling the loop bounds for reversal or group processing.
*   **Losing track of pointers**: Failing to correctly update `prev`, `curr`, and `next` pointers during reversal, leading to broken links.
*   **Not handling the last group**: Forgetting to check if the remaining nodes form a full group of `k` before attempting to reverse them.
*   **Incorrectly re-linking segments**: Failing to connect the end of a reversed group to the start of the next segment, or the previous segment to the start of the reversed group.
*   **Modifying `head` directly**: If `head` is part of the first group, modifying it directly without a `dummy` node can be tricky.

## Complexity Analysis
- Time: O(N) - reason The list is traversed once to count nodes, and then traversed again in segments of `k` for reversal. Each node is visited a constant number of times.
- Space: O(1) - reason Only a few extra pointers are used, regardless of the input size.

## Commented Code
```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Create a dummy node to simplify edge cases, especially for the head.
        ListNode dummy = new ListNode(-1);
        // Point the dummy node's next to the original head.
        dummy.next = head;
        
        // Initialize a temporary pointer to traverse the list and count nodes.
        ListNode temp = head;
        // Variable to store the total number of nodes in the list.
        int total = 0;
        // Traverse the list to count all nodes.
        while(temp!=null){
            // Move to the next node.
            temp=temp.next;
            // Increment the total count.
            total++;
        }
        
        // Reset temp pointer to the dummy node. This pointer will track the node *before* the current group to be reversed.
        temp = dummy;
        // Calculate the number of nodes that can form full groups of k.
        // We subtract the remainder to ensure we only process full groups.
        total -= (total%k);
        
        // Iterate through the list, processing groups of k nodes.
        // The loop increments by k in each step, moving to the start of the next potential group.
        for(int i=0;i<total;i+=k){
          // Call the helper function 'rev' to reverse the next k nodes.
          // 'temp.next' is the head of the current group to be reversed.
          // 'rev' returns an array: ans[0] is the new head of the reversed group, ans[1] is the new tail of the reversed group.
          ListNode[] ans = rev(temp.next,k); 
          // Link the previous segment (ending at 'temp') to the new head of the reversed group.
          temp.next =  ans[0];
          // Move 'temp' to the tail of the just-reversed group (which was the original start of the group).
          // This prepares 'temp' for the next iteration, as it will be the node before the next group.
          temp = ans[1];
        }
        
        // The dummy node's next now points to the head of the modified list.
        return dummy.next;
    }
    
    // Helper function to reverse k nodes starting from 'head'.
    // Returns an array: [new_head_of_reversed_group, new_tail_of_reversed_group].
    public ListNode[] rev(ListNode head , int k){
      // 'curr' pointer starts at the head of the group to be reversed.
      ListNode curr = head;
      // 'prev' pointer will build the reversed list, starting as null.
      ListNode prev = null;
      // Loop k times to reverse k nodes.
      while(k-->0){
        // Store the next node before modifying 'curr.next'.
        ListNode fwd = curr.next;
        // Reverse the pointer: 'curr' now points to the previous node.
        curr.next = prev;
        // Move 'prev' to 'curr' for the next iteration.
        prev = curr;
        // Move 'curr' to the next node in the original list.
        curr = fwd;
      }
      // After the loop:
      // 'prev' is the new head of the reversed k nodes.
      // 'curr' is the node immediately following the reversed group (the start of the next segment).
      // 'head' (the original head of this group) is now the tail of the reversed group.
      // Connect the tail of the reversed group ('head') to the start of the next segment ('curr').
      head.next = curr;
      // Return the new head ('prev') and the new tail ('head') of the reversed group.
      return new ListNode[]{prev,head};
    }
}
```

## Interview Tips
*   **Explain the Dummy Node**: Clearly articulate why a dummy node is used and how it simplifies the logic.
*   **Visualize Pointer Movements**: Be prepared to draw out the pointer changes during the reversal of a small group (e.g., k=3) to demonstrate your understanding.
*   **Handle Edge Cases**: Discuss how you would handle an empty list, `k=1`, or `k` being larger than the list size.
*   **Break Down the Problem**: Explain the two main parts: counting nodes and then iterating/reversing groups.

## Revision Checklist
- [ ] Understand the problem statement for reversing nodes in k groups.
- [ ] Implement a dummy node for easier head management.
- [ ] Accurately count the total number of nodes.
- [ ] Determine the number of full groups to reverse.
- [ ] Implement a helper function to reverse a sublist of k nodes.
- [ ] Correctly re-link the reversed groups back into the main list.
- [ ] Handle the case where the remaining nodes are less than k.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Reverse Linked List
*   Reverse Linked List II
*   Swap Nodes in Pairs
*   Remove Nth Node From End of List

## Tags
`Linked List` `Recursion` `Iteration`
