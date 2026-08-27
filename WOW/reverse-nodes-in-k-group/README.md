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
The core idea is to treat the linked list as a sequence of segments. We need to identify segments of length `k`, reverse them, and then re-link them. A dummy node is crucial to simplify the handling of the head of the list, especially when the first group needs to be reversed. We also need a way to count the total number of nodes to ensure we only reverse full groups of `k`.

## Algorithm
1.  **Initialization**: Create a `dummy` node pointing to the `head` of the list. This simplifies edge cases, especially for the first group.
2.  **Count Total Nodes**: Traverse the list to count the total number of nodes.
3.  **Determine Reversible Groups**: Calculate how many full groups of `k` nodes can be reversed. This is `total_nodes - (total_nodes % k)`.
4.  **Iterate and Reverse**:
    *   Use a pointer `temp` (initially `dummy`) to keep track of the node *before* the current group to be reversed.
    *   Iterate `total_nodes - (total_nodes % k)` times, incrementing by `k` in each step.
    *   In each iteration:
        *   Call a helper function `rev` to reverse the next `k` nodes starting from `temp.next`.
        *   The `rev` function should return an array/pair: the new head of the reversed group and the original head of the group (which becomes the tail after reversal).
        *   Connect the `temp` node's `next` pointer to the new head of the reversed group.
        *   Update `temp` to be the original head of the group (now the tail of the reversed group) to prepare for the next iteration.
5.  **Helper Function `rev(head, k)`**:
    *   Takes the start of a `k`-group and `k` as input.
    *   Reverses `k` nodes using standard linked list reversal (iterative approach).
    *   Crucially, it needs to connect the tail of the reversed group to the node *after* the original `k`-group.
    *   Returns the new head of the reversed `k`-group and the original head (which is now the tail).
6.  **Return**: Return `dummy.next`, which will be the new head of the modified list.

## Concept to Remember
*   Linked List Manipulation: Reversing a sublist and re-linking nodes.
*   Dummy Nodes: Simplifying head/tail operations in linked list problems.
*   Iterative Reversal: Efficiently reversing a fixed number of nodes.
*   Pointer Management: Carefully tracking `prev`, `curr`, and `next` pointers during reversal.

## Common Mistakes
*   Incorrectly handling the `next` pointer of the last node in a reversed group, leading to broken links.
*   Off-by-one errors when counting nodes or determining the number of groups to reverse.
*   Not properly updating the `temp` pointer to the tail of the reversed group, preventing correct chaining of subsequent groups.
*   Failing to handle the case where the list length is not a multiple of `k` (i.e., the last partial group should not be reversed).
*   Modifying the original `head` pointer directly without using a `dummy` node, making edge cases harder to manage.

## Complexity Analysis
- Time: O(N) - We traverse the list twice: once to count nodes and once to reverse groups. Each node is visited a constant number of times.
- Space: O(1) - We only use a few extra pointers and a dummy node, which is constant extra space.

## Commented Code
```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Create a dummy node to simplify head operations.
        ListNode dummy = new ListNode(-1);
        // Point dummy's next to the original head.
        dummy.next = head;
        
        // Initialize a temporary pointer to traverse the list.
        ListNode temp = head;
        // Initialize a counter for the total number of nodes.
        int total = 0;
        // Traverse the list to count all nodes.
        while(temp!=null){
            // Move to the next node.
            temp=temp.next;
            // Increment the node count.
            total++;
        }
        
        // Reset temp to the dummy node to start processing groups.
        temp = dummy;
        // Calculate the number of nodes that can form full k-groups.
        // We subtract the remainder to ensure only full groups are reversed.
        total -= (total%k);
        
        // Iterate through the list, processing groups of k nodes.
        // The loop increments by k, moving to the start of the next potential group.
        for(int i=0;i<total;i+=k){
          // Call the helper function 'rev' to reverse the next k nodes.
          // 'temp.next' is the head of the current group to be reversed.
          // 'rev' returns an array: ans[0] is the new head of the reversed group,
          // and ans[1] is the original head of the group (now the tail).
          ListNode[] ans = rev(temp.next,k); 
          // Connect the previous node (temp) to the new head of the reversed group.
          temp.next =  ans[0];
          // Move temp to the tail of the reversed group (which was the original head).
          // This prepares temp for the next iteration, pointing to the node before the next group.
          temp = ans[1];
        }
        
        // Return the next of the dummy node, which is the new head of the modified list.
        return dummy.next;
    }
    
    // Helper function to reverse k nodes starting from 'head'.
    // Returns an array: [new_head_of_reversed_group, original_head_of_group_now_tail]
    public ListNode[] rev(ListNode head , int k){
      // Initialize current node to the head of the group.
      ListNode curr = head;
      // Initialize previous node to null.
      ListNode prev = null;
      // Loop k times to reverse k nodes.
      while(k-->0){
        // Store the next node before modifying curr.next.
        ListNode fwd = curr.next;
        // Reverse the pointer: current node points to the previous node.
        curr.next = prev;
        // Move prev to the current node.
        prev = curr;
        // Move curr to the next node (which was stored in fwd).
        curr = fwd;
      }
      // After the loop, 'prev' is the new head of the reversed group.
      // 'head' (the original head) is now the tail of the reversed group.
      // Connect the tail of the reversed group ('head') to the node that comes after the original group ('curr').
      head.next = curr;
      // Return the new head ('prev') and the original head ('head', now the tail).
      return new ListNode[]{prev,head};
    }
}
```

## Interview Tips
*   **Explain the Dummy Node**: Clearly articulate why a dummy node is used and how it simplifies the logic, especially for the first group.
*   **Visualize the Reversal**: Walk through the `rev` function with a small example (e.g., k=3) to show how pointers are manipulated. Emphasize the connection of the tail of the reversed group to the rest of the list.
*   **Handle Edge Cases**: Discuss what happens if `k=1` (no reversal needed), `k` is larger than the list length, or the list is empty.
*   **Complexity Justification**: Be ready to explain why the time complexity is O(N) and space complexity is O(1).

## Revision Checklist
- [ ] Understand the problem statement: reverse nodes in groups of `k`.
- [ ] Identify the need for a dummy node.
- [ ] Implement a function to reverse a sublist of `k` nodes.
- [ ] Ensure the reversed sublist is correctly linked back to the main list.
- [ ] Handle the case where the list length is not a multiple of `k`.
- [ ] Count total nodes to determine the number of full groups.
- [ ] Test with edge cases: empty list, `k=1`, `k` > list length.

## Similar Problems
*   Reverse Linked List
*   Reverse Linked List II
*   Swap Nodes in Pairs
*   Remove Nth Node From End of List

## Tags
`Linked List` `Recursion` `Two Pointers`
