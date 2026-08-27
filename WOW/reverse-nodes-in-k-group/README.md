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
Solve by iterating through the list, reversing k nodes at a time, and connecting the reversed groups.

## Intuition
The core idea is to treat the linked list as a series of segments of length `k`. For each segment, we need to reverse the nodes within it. The challenge lies in correctly linking the end of one reversed segment to the beginning of the next, and the beginning of the current segment to the end of the previous one. A dummy node at the beginning simplifies handling the head of the list. We also need to ensure we only reverse full groups of `k` nodes, leaving any remaining nodes at the end as they are.

## Algorithm
1.  **Initialization**:
    *   Create a `dummy` node that points to the `head` of the linked list. This simplifies edge cases, especially for the first group.
    *   Initialize a pointer `prevGroupEnd` to `dummy`. This pointer will track the node *before* the current group to be reversed.
2.  **Count Total Nodes**:
    *   Iterate through the list to count the `total` number of nodes.
3.  **Determine Reversible Groups**:
    *   Calculate the number of full groups of `k` nodes that can be reversed: `numReversibleGroups = total / k`.
4.  **Iterate and Reverse**:
    *   Loop `numReversibleGroups` times. In each iteration:
        *   Identify the `groupStart` (which is `prevGroupEnd.next`).
        *   Identify the `groupEnd` (which is `k` nodes ahead of `groupStart`).
        *   Store the node *after* `groupEnd` as `nextGroupStart`. This is crucial for reconnecting later.
        *   **Reverse the current group**:
            *   Initialize `current = groupStart` and `previous = null`.
            *   Iterate `k` times:
                *   Store `current.next` in a temporary variable `forward`.
                *   Set `current.next = previous`.
                *   Update `previous = current`.
                *   Update `current = forward`.
            *   After the loop, `previous` will be the new head of the reversed group, and `groupStart` will be the new tail.
        *   **Reconnect the list**:
            *   Set `prevGroupEnd.next = previous` (linking the previous segment to the new head of the reversed group).
            *   Set `groupStart.next = nextGroupStart` (linking the new tail of the reversed group to the start of the next segment).
            *   Update `prevGroupEnd = groupStart` (the tail of the current reversed group becomes the `prevGroupEnd` for the next iteration).
5.  **Return Result**:
    *   Return `dummy.next`, which points to the head of the modified linked list.

## Concept to Remember
*   **Linked List Manipulation**: Reversing a portion of a linked list requires careful pointer management to avoid losing nodes.
*   **Dummy Node**: Using a dummy node simplifies handling edge cases, especially when modifying the head of the list.
*   **Iterative Reversal**: Reversing a sublist iteratively involves keeping track of the previous, current, and next nodes.
*   **Group Processing**: The problem requires processing the list in segments, necessitating careful tracking of segment boundaries and connections.

## Common Mistakes
*   **Incorrectly handling the `next` pointer of the last node in a reversed group**: Forgetting to link it to the start of the next group (or `null` if it's the last group).
*   **Losing track of the node *after* the current group**: This node is needed to reconnect the list after reversal.
*   **Off-by-one errors when counting or reversing `k` nodes**: Ensuring exactly `k` nodes are reversed and the loop conditions are correct.
*   **Not handling the case where the remaining nodes are less than `k`**: The problem states these should not be reversed.
*   **Modifying pointers before saving necessary references**: For example, overwriting `current.next` before storing its original value.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the linked list twice: once to count the total nodes and once to reverse the nodes in groups. Each node is visited a constant number of times.
- Space: O(1) - reason: We only use a few extra pointers (dummy, prevGroupEnd, groupStart, groupEnd, current, previous, forward) to manage the reversal, which is constant extra space.

## Commented Code
```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Create a dummy node to simplify edge cases, especially for the head.
        ListNode dummy = new ListNode(-1);
        // Link the dummy node to the original head of the list.
        dummy.next = head;
        
        // Initialize a pointer 'prevGroupEnd' to the dummy node.
        // This pointer will always point to the node *before* the current group to be reversed.
        ListNode prevGroupEnd = dummy;
        
        // Initialize a pointer 'current' to the head of the list to traverse and count nodes.
        ListNode current = head;
        // Variable to store the total number of nodes in the list.
        int totalNodes = 0;
        // Traverse the list to count the total number of nodes.
        while(current != null){
            current = current.next;
            totalNodes++;
        }
        
        // Calculate the number of full groups of 'k' nodes that can be reversed.
        // We subtract the remainder of totalNodes divided by k to ensure we only process full groups.
        int numFullGroups = totalNodes - (totalNodes % k);
        
        // Iterate through the list, processing 'k' nodes at a time.
        // The loop increments by 'k' in each step, moving 'prevGroupEnd' to the end of the previously reversed group.
        for(int i = 0; i < numFullGroups; i += k){
            // 'groupStart' is the first node of the current group to be reversed.
            ListNode groupStart = prevGroupEnd.next;
            // 'nextGroupStart' is the node immediately following the current group.
            // This is needed to reconnect the list after reversing the current group.
            ListNode nextGroupStart = groupStart;
            // Initialize 'prev' to null for the reversal process.
            ListNode prev = null;
            // 'curr' is used to iterate through the current group for reversal.
            ListNode curr = groupStart;
            
            // Reverse the 'k' nodes in the current group.
            for(int j = 0; j < k; j++){
                // Store the next node before modifying the current node's 'next' pointer.
                ListNode forward = curr.next;
                // Reverse the pointer: current node points to the previous node.
                curr.next = prev;
                // Move 'prev' to the current node.
                prev = curr;
                // Move 'curr' to the next node in the original sequence.
                curr = forward;
            }
            
            // After the inner loop, 'prev' is the new head of the reversed group,
            // and 'groupStart' is now the tail of the reversed group.
            // 'curr' is pointing to the 'nextGroupStart'.
            
            // Connect the end of the previous group ('prevGroupEnd') to the new head of the reversed group ('prev').
            prevGroupEnd.next = prev;
            // Connect the tail of the reversed group ('groupStart') to the start of the next group ('curr').
            groupStart.next = curr;
            
            // Update 'prevGroupEnd' to be the tail of the just-reversed group ('groupStart').
            // This prepares for the next iteration, where 'groupStart' will be the node before the next group.
            prevGroupEnd = groupStart;
        }
        
        // Return the head of the modified list, which is dummy.next.
        return dummy.next;
    }
    
    // This helper function is not used in the provided solution but is a common pattern for reversing a sublist.
    // The provided solution integrates the reversal logic directly into the main loop.
    // If a helper function were used, it might look like this:
    /*
    public ListNode[] reverseSublist(ListNode head, int k) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode originalHead = head; // Keep track of the original head of the sublist
        
        while (k-- > 0 && curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        // prev is the new head of the reversed sublist
        // originalHead is the new tail of the reversed sublist
        // curr is the node after the reversed sublist
        return new ListNode[]{prev, originalHead, curr}; // Returns new head, new tail, node after sublist
    }
    */
}
```

## Interview Tips
1.  **Clarify Edge Cases**: Ask about `k=1` (no reversal needed), empty list, and list length less than `k`.
2.  **Visualize Pointer Movements**: Draw out the linked list and trace how pointers (`prevGroupEnd`, `groupStart`, `prev`, `curr`) move and change during reversal and reconnection. This is crucial for debugging.
3.  **Explain the Dummy Node**: Clearly articulate why a dummy node is used and how it simplifies the logic, especially for the first group.
4.  **Handle Remaining Nodes**: Emphasize that the problem requires leaving the last partial group unchanged and how your algorithm achieves this.
5.  **Break Down the Reversal**: Explain the inner loop for reversing `k` nodes separately from the outer loop that iterates through groups.

## Revision Checklist
- [ ] Understand the problem statement: reverse nodes in groups of `k`.
- [ ] Identify the need for a dummy node.
- [ ] Implement logic to count total nodes.
- [ ] Determine the number of full groups to reverse.
- [ ] Implement the iterative reversal of `k` nodes.
- [ ] Correctly reconnect the reversed group to the previous and next segments.
- [ ] Update the pointer for the end of the previous group.
- [ ] Handle the case where the list length is not a multiple of `k`.
- [ ] Test with edge cases: `k=1`, empty list, list length < `k`.

## Similar Problems
*   Reverse Linked List
*   Reverse Linked List II
*   Swap Nodes in Pairs
*   Remove Nth Node From End of List

## Tags
`Linked List` `Recursion` `Two Pointers`
