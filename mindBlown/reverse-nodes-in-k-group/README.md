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
            ListNode[] arr = revRange(curr.next,k);
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
Reverse nodes of a linked list in groups of k.
Solve by iterating through the list, reversing k nodes at a time, and reconnecting them.

## Intuition
The core idea is to treat the linked list as a series of segments. For each segment of size `k`, we need to reverse it and then link it back to the previous segment and the next segment. A dummy node at the beginning simplifies handling the head of the list. We also need to ensure we only reverse full groups of `k` nodes; if the remaining nodes are less than `k`, they should be left as is.

## Algorithm
1. **Calculate List Length:** Traverse the linked list to determine its total length.
2. **Determine Reversible Length:** Subtract the remainder of `length % k` from the total length. This gives the total number of nodes that will be part of full `k`-sized groups.
3. **Initialize Dummy Node:** Create a dummy node and point its `next` to the original `head` of the list. This simplifies edge cases, especially for the first group.
4. **Iterate and Reverse:**
   - Use a `curr` pointer, initialized to the `dummy` node.
   - Iterate `length` times, incrementing by `k` in each step. This loop processes each full `k`-group.
   - In each iteration:
     - Identify the start of the current `k`-group: `curr.next`.
     - Call a helper function `revRange` to reverse the next `k` nodes starting from `curr.next`. This function should return the new head and new tail of the reversed group.
     - Reconnect the list:
       - Set `curr.next` to the new head of the reversed group.
       - Set the original head of the group (which is now the tail after reversal) to point to the node after the reversed group.
     - Update `curr` to be the tail of the reversed group, so the next iteration starts from the correct position.
5. **Return Result:** Return `dummy.next`, which will be the head of the modified linked list.

**Helper Function `revRange(head, k)`:**
1. **Initialization:**
   - `prev = null`
   - `curr = head`
2. **Reverse `k` Nodes:**
   - Loop `k` times:
     - Store `curr.next` in a temporary variable `near`.
     - Reverse the pointer: `curr.next = prev`.
     - Move `prev` to `curr`.
     - Move `curr` to `near`.
3. **Return New Head and Tail:**
   - The `prev` pointer now points to the new head of the reversed `k`-group.
   - The original `head` node (which was the start of the group) is now the tail of the reversed group.
   - Return an array containing `[new_head, new_tail]`.

## Concept to Remember
*   **Linked List Manipulation:** Understanding how to traverse, reverse, and reconnect nodes in a singly linked list is fundamental.
*   **Dummy Nodes:** Using a dummy node simplifies handling edge cases, especially when modifying the head of the list.
*   **Iterative Reversal:** Efficiently reversing a portion of a linked list requires careful pointer manipulation.
*   **In-place Modification:** The solution modifies the list directly without creating new nodes for the reversal.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling the loop bounds or pointer assignments during reversal can lead to incorrect results.
*   **Losing track of pointers:** Forgetting to store `curr.next` before modifying `curr.next` will break the list.
*   **Not handling the last group:** Failing to check if the remaining nodes form a full `k`-group and leaving them as is.
*   **Incorrectly reconnecting segments:** The pointers between the previous group, the reversed group, and the next group must be set precisely.
*   **Modifying `head` directly without a dummy:** This makes handling the first group much more complex.

## Complexity Analysis
- **Time:** O(N) - The list is traversed twice: once to calculate the length and once to perform the reversals. Each node is visited a constant number of times.
- **Space:** O(1) - The solution uses a constant amount of extra space for pointers and the dummy node. The `revRange` function uses a few temporary pointers, which is constant space.

## Commented Code
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val; // The value of the node.
 *     ListNode next; // Pointer to the next node in the list.
 *     ListNode() {} // Default constructor.
 *     ListNode(int val) { this.val = val; } // Constructor with value.
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; } // Constructor with value and next node.
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
        int length = 0; // Initialize a counter for the length of the linked list.
        ListNode first = head; // Use a temporary pointer to traverse the list.
        // Traverse the list to count the total number of nodes.
        while(first!=null){
            length++; // Increment length for each node.
            first = first.next; // Move to the next node.
        }
        // Calculate the number of nodes that will be part of full k-sized groups.
        // This effectively discards any trailing nodes that don't form a full group.
        length -= length%k;

        // Create a dummy node. This simplifies handling the head of the list,
        // especially when reversing the first group.
        ListNode dummy = new ListNode(0);
        dummy.next = head; // Point the dummy node's next to the original head.

        // 'curr' pointer will track the node *before* the current k-group to be reversed.
        // Initially, it's the dummy node.
        ListNode curr = dummy;

        // Iterate through the list, processing k nodes at a time.
        // The loop runs 'length' times, incrementing by 'k' in each step,
        // ensuring we only process full k-groups.
        for(int i=0;i<length;i+=k){
            // 'revRange' is a helper function that reverses a sublist of k nodes
            // starting from 'curr.next'. It returns an array:
            // arr[0] = new head of the reversed k-group
            // arr[1] = new tail of the reversed k-group (which was the original head)
            ListNode[] arr = revRange(curr.next,k);

            // Reconnect the list:
            // 1. Point the 'next' of the node *before* the group ('curr') to the new head of the reversed group.
            curr.next = arr[0];
            // 2. Update 'curr' to be the tail of the reversed group. This is crucial
            //    because the next iteration will need to connect to this node.
            curr = arr[1];
        }
        // The dummy node's next pointer now points to the head of the fully modified list.
        return dummy.next;
    }

    /**
     * Reverses a sublist of k nodes starting from 'head'.
     * @param head The starting node of the sublist to reverse.
     * @param k The number of nodes to reverse.
     * @return A ListNode array where:
     *         index 0 is the new head of the reversed k-group.
     *         index 1 is the new tail of the reversed k-group (which was the original head).
     */
    public ListNode[] revRange(ListNode head,int k){
        ListNode prev = null; // 'prev' will store the previously reversed node.
        ListNode curr = head; // 'curr' is the node currently being processed.

        // Reverse k nodes. The loop runs k times.
        while(k-->0){
            ListNode near = curr.next; // Store the next node before modifying 'curr.next'.
            curr.next = prev; // Reverse the pointer: current node points to the previous one.
            prev = curr; // Move 'prev' to the current node.
            curr = near; // Move 'curr' to the next node in the original list.
        }
        // After the loop:
        // 'prev' is the new head of the reversed k-group.
        // 'curr' is the node *after* the reversed k-group (the start of the next segment).
        // 'head' (the original start of the k-group) is now the tail of the reversed k-group.

        // Connect the tail of the reversed group (original 'head') to the node that follows the group ('curr').
        head.next = curr;

        // Return the new head and new tail of the reversed group.
        return new ListNode[]{prev,head};
    }
}
```

## Interview Tips
*   **Explain the Dummy Node:** Clearly articulate why a dummy node is used and how it simplifies the logic, especially for the first group.
*   **Visualize Pointer Swaps:** Be prepared to draw out the pointer manipulations for reversing a small group of nodes to demonstrate your understanding.
*   **Handle Edge Cases:** Discuss how you'd handle an empty list, `k=1`, or `k` larger than the list length. The provided solution implicitly handles `k=1` and `k` larger than list length gracefully due to the length calculation.
*   **Break Down the Problem:** Explain the two main parts: calculating the length and then iterating to reverse groups. The helper function `revRange` is a good way to modularize the reversal logic.

## Revision Checklist
- [ ] Understand linked list node structure.
- [ ] Implement iterative linked list traversal.
- [ ] Implement iterative linked list reversal for a fixed number of nodes.
- [ ] Use a dummy node to simplify head manipulation.
- [ ] Correctly calculate the number of nodes to reverse (full groups only).
- [ ] Accurately reconnect the reversed groups to the rest of the list.
- [ ] Test with edge cases: empty list, k=1, k > list length, list length is a multiple of k, list length is not a multiple of k.

## Similar Problems
*   Reverse Linked List
*   Reverse Linked List II
*   Swap Nodes in Pairs
*   Remove Nth Node From End of List

## Tags
`Linked List` `Recursion`
