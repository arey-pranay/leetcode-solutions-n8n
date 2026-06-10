# Reverse Nodes In K Group

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Linked List` `Recursion`  
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
    public ListNode reverseKGroup(ListNode head, int k) {
        int length = 0;
        ListNode first = head;
        while(first!=null){
            length++;
            first = first.next;
        }
        length -= length%k;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        for(int i=0;i<length;i+=k){
            ListNode[] arr = revRange(curr.next,k); // yaha curr.next se call , mtlb head k baad se hi 
            curr.next = arr[0];
            curr = arr[1];
        }
        return dummy.next;
    }
    public ListNode[] revRange(ListNode head,int k){
        ListNode prev = null;
        ListNode curr = head;
        while(k-->0){
            ListNode near = curr.next;
            curr.next = prev;
            prev = curr;
            curr = near;
        }
        head.next = curr;
        return new ListNode[]{prev,head};
    }
}
```

---

---
## Quick Revision
Reverse nodes in a singly linked list in groups of k. If the remaining nodes are less than k, they remain as they are.

## Intuition
The core idea is to iterate through the linked list, identify groups of k nodes, reverse each group, and then connect these reversed groups back into the main list. A dummy node simplifies handling the head of the list.

## Algorithm
1. Calculate the total length of the linked list.
2. Determine how many full groups of `k` nodes exist by `length - (length % k)`.
3. Initialize a `dummy` node pointing to the `head` of the list. This `dummy` node will help manage the connections, especially for the first group.
4. Use a `curr` pointer, starting at the `dummy` node, to traverse and manage the connections between groups.
5. Iterate `length` times, incrementing by `k` in each step. This loop processes each full group.
6. Inside the loop, call a helper function `revRange` to reverse the current group of `k` nodes starting from `curr.next`.
7. `revRange` returns an array: `arr[0]` is the new head of the reversed group, and `arr[1]` is the new tail of the reversed group (which was the original head of the group).
8. Update `curr.next` to point to `arr[0]` (the new head of the reversed group).
9. Update `curr` to `arr[1]` (the new tail of the reversed group) to prepare for the next iteration.
10. The `revRange` helper function:
    a. Takes the start of the group (`head`) and `k` as input.
    b. Uses `prev`, `curr`, and `near` pointers to reverse `k` nodes.
    c. After reversing, the original `head` of the group becomes its tail. Set `head.next` to `curr` (which is the first node *after* the reversed group).
    d. Returns `[prev, head]`, where `prev` is the new head of the reversed group and `head` is the new tail.
11. Finally, return `dummy.next`, which will be the head of the modified linked list.

## Concept to Remember
*   **Linked List Manipulation:** Reversing a portion of a linked list requires careful pointer management (`prev`, `curr`, `next`).
*   **Dummy Node:** Using a dummy node simplifies edge cases, particularly when modifying the head of the list.
*   **Iterative Reversal:** Reversing a linked list or a sub-list can be done efficiently using an iterative approach.
*   **Group Processing:** Breaking down the problem into processing fixed-size groups is a common strategy for list problems.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling the `k` count during reversal or loop termination.
*   **Losing track of pointers:** Forgetting to store the `next` node before modifying `curr.next` during reversal.
*   **Incorrectly connecting groups:** Failing to properly link the tail of a reversed group to the head of the next group, or the head of the first reversed group to the dummy node.
*   **Not handling the last partial group:** The problem statement specifies that the last group, if less than `k`, should not be reversed. The initial length calculation and loop condition should implicitly handle this.
*   **Modifying `head` directly:** If `head` is modified without a `dummy` node, it can be difficult to manage the connections for the first group.

## Complexity Analysis
- Time: O(N) - reason The list is traversed twice: once to calculate the length and once to reverse the groups. Each node is visited a constant number of times.
- Space: O(1) - reason Only a few extra pointers are used, regardless of the input size.

## Commented Code
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val; // The value of the node
 *     ListNode next; // Pointer to the next node in the list
 *     ListNode() {} // Default constructor
 *     ListNode(int val) { this.val = val; } // Constructor with value
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; } // Constructor with value and next node
 * }
 */
class Solution {
    /**
     * Reverses nodes of a linked list k at a time and returns the modified list.
     * @param head The head of the linked list.
     * @param k The size of the group to reverse.
     * @return The head of the modified linked list.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int length = 0; // Initialize a counter for the total length of the list
        ListNode first = head; // Use a temporary pointer to traverse the list and count nodes
        while(first!=null){ // Loop until the end of the list is reached
            length++; // Increment the length for each node
            first = first.next; // Move to the next node
        }
        // Calculate the number of nodes that will form complete k-sized groups.
        // Any remaining nodes (length % k) will not be reversed.
        length -= length%k;

        ListNode dummy = new ListNode(0); // Create a dummy node to simplify head manipulation
        dummy.next = head; // Point the dummy node's next to the original head
        ListNode curr = dummy; // Initialize a 'curr' pointer to the dummy node. This pointer will track the node *before* the current group being reversed.

        // Iterate through the list, processing groups of k nodes.
        // The loop runs 'length' times, incrementing by 'k' each time, ensuring we only process full groups.
        for(int i=0;i<length;i+=k){
            // Call the helper function 'revRange' to reverse the next k nodes.
            // 'curr.next' is the head of the current group to be reversed.
            // 'revRange' returns an array: arr[0] is the new head of the reversed group, arr[1] is the new tail of the reversed group.
            ListNode[] arr = revRange(curr.next,k);
            // Connect the node *before* the group ('curr') to the new head of the reversed group ('arr[0]').
            curr.next = arr[0];
            // Move 'curr' to the new tail of the reversed group ('arr[1]'). This prepares 'curr' for the next iteration,
            // as it will then point to the node *before* the next group.
            curr = arr[1];
        }
        // Return the next of the dummy node, which is the head of the fully modified list.
        return dummy.next;
    }

    /**
     * Reverses a specified number of nodes (k) starting from a given head.
     * @param head The starting node of the group to reverse.
     * @param k The number of nodes to reverse.
     * @return A ListNode array where arr[0] is the new head of the reversed group and arr[1] is the new tail of the reversed group.
     */
    public ListNode[] revRange(ListNode head,int k){
        ListNode prev = null; // Initialize 'prev' to null, as it will become the next of the current node during reversal.
        ListNode curr = head; // Initialize 'curr' to the head of the group to be reversed.

        // Reverse k nodes.
        while(k-->0){ // Loop k times to reverse k nodes.
            ListNode near = curr.next; // Store the next node before modifying 'curr.next'.
            curr.next = prev; // Reverse the pointer: 'curr' now points to the previous node.
            prev = curr; // Move 'prev' forward to the current node.
            curr = near; // Move 'curr' forward to the next node in the original list.
        }
        // After the loop, 'prev' is the new head of the reversed group, and 'head' (the original start of the group) is now the tail.
        // 'curr' is the first node *after* the reversed group.
        // Connect the tail of the reversed group ('head') to the node that follows the group ('curr').
        head.next = curr;
        // Return the new head of the reversed group ('prev') and the new tail of the reversed group ('head').
        return new ListNode[]{prev,head};
    }
}
```

## Interview Tips
*   **Explain the Dummy Node:** Clearly articulate why a dummy node is used and how it simplifies the logic, especially for handling the first group.
*   **Visualize Pointer Movements:** Be prepared to draw or explain the pointer movements step-by-step for both the main `reverseKGroup` function and the `revRange` helper function.
*   **Handle Edge Cases:** Discuss how you'd handle an empty list, `k=1` (no reversal needed), and `k` larger than the list length.
*   **Break Down the Problem:** Emphasize that you're breaking the problem into smaller, manageable parts: calculating length, identifying groups, reversing a group, and connecting groups.

## Revision Checklist
- [ ] Understand the problem: Reverse nodes in groups of k, leave remaining nodes as is.
- [ ] Calculate list length.
- [ ] Determine number of full k-groups.
- [ ] Use a dummy node.
- [ ] Iterate through full k-groups.
- [ ] Implement `revRange` helper function correctly.
- [ ] Connect reversed groups.
- [ ] Handle pointer updates meticulously.
- [ ] Consider edge cases (empty list, k=1, k > length).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Reverse Linked List
*   Reverse Linked List II
*   Swap Nodes in Pairs
*   Remove Nth Node From End of List

## Tags
`Linked List` `Recursion` `Two Pointers`
