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
            ListNode[] arr = revRange(curr.next,k); // yaha curr.next se call , mtlb he
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
Reverse nodes of a linked list in groups of k. If the remaining nodes are less than k, they remain as they are.
This is solved by iterating through the list, reversing k nodes at a time, and connecting the reversed segments.

## Intuition
The core idea is to treat the linked list as a series of segments, each of length `k`. We need to reverse each of these segments and then stitch them back together. The challenge lies in handling the connections between these reversed segments and the remaining part of the list. A dummy node is crucial to simplify the head manipulation.

## Algorithm
1. **Calculate List Length:** Traverse the linked list to determine its total length.
2. **Determine Reversible Segments:** Calculate how many full groups of `k` nodes exist. `length -= length % k;` effectively discards any trailing nodes less than `k`.
3. **Initialize Dummy Node:** Create a dummy node that points to the original head of the list. This simplifies handling the reversal of the first group.
4. **Iterate and Reverse:**
   - Use a `curr` pointer, initialized to the dummy node.
   - Loop `length` times, incrementing by `k` in each iteration (`i += k`). This loop signifies processing each full `k`-group.
   - In each iteration:
     - Identify the start of the current `k`-group: `curr.next`.
     - Call a helper function `revRange` to reverse `k` nodes starting from `curr.next`. This function should return the new head and new tail of the reversed segment.
     - Connect the previous segment's tail (`curr`) to the new head of the reversed segment.
     - Update `curr` to be the new tail of the reversed segment.
5. **Return Result:** The `dummy.next` will point to the head of the modified linked list.

**Helper Function `revRange(ListNode head, int k)`:**
1. **Initialize Pointers:** `prev = null`, `curr = head`.
2. **Reverse `k` Nodes:** Loop `k` times:
   - Store `curr.next` in a temporary variable `near`.
   - Set `curr.next = prev`.
   - Update `prev = curr`.
   - Update `curr = near`.
3. **Connect Reversed Segment:** After the loop, `prev` is the new head of the reversed segment, and `head` (the original start of the segment) is now the tail. Set `head.next = curr` (where `curr` is now the node *after* the reversed segment).
4. **Return New Head and Tail:** Return an array `[prev, head]`, where `prev` is the new head and `head` is the new tail of the reversed `k`-group.

## Concept to Remember
*   **Linked List Manipulation:** Reversing a portion of a linked list requires careful pointer manipulation.
*   **Dummy Nodes:** Using a dummy node simplifies edge cases, especially when modifying the head of the list.
*   **Iterative Reversal:** Reversing a linked list iteratively involves keeping track of the previous, current, and next nodes.
*   **Segmented Processing:** Breaking down a larger problem (reversing the whole list in k-groups) into smaller, repeatable sub-problems (reversing a k-group).

## Common Mistakes
*   **Incorrect Pointer Updates:** Mishandling `next` pointers during reversal can lead to lost nodes or cycles.
*   **Off-by-One Errors:** Incorrectly handling the loop bounds or the number of nodes to reverse.
*   **Not Handling the Last Group:** Failing to correctly connect the last reversed group or leaving the remaining nodes un-reversed.
*   **Modifying `head` Directly:** If `head` is modified without a dummy node, it becomes difficult to track the original start and connect subsequent segments.
*   **Incorrect Return Values from Helper:** The helper function must correctly return both the new head and the new tail of the reversed segment.

## Complexity Analysis
- Time: O(N) - reason: We traverse the list once to calculate the length and then traverse it again to reverse the groups. Each node is visited a constant number of times.
- Space: O(1) - reason: We are only using a few extra pointers for manipulation, not proportional to the input size.

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
        ListNode first = head; // Use a temporary pointer to traverse the list
        while(first!=null){ // Iterate through the list to count nodes
            length++; // Increment the length for each node
            first = first.next; // Move to the next node
        }
        // Calculate the number of nodes that can form full k-groups.
        // `length % k` gives the remainder, which is then subtracted from the total length.
        length -= length%k;

        ListNode dummy = new ListNode(0); // Create a dummy node to simplify head manipulations
        dummy.next = head; // Point the dummy node's next to the original head
        ListNode curr = dummy; // `curr` pointer will track the node *before* the current k-group to be reversed

        // Iterate through the list in steps of k, processing each full k-group
        for(int i=0;i<length;i+=k){
            // `revRange` reverses k nodes starting from `curr.next` and returns an array:
            // arr[0] is the new head of the reversed k-group
            // arr[1] is the new tail of the reversed k-group (which was the original head of the k-group)
            ListNode[] arr = revRange(curr.next,k); // Call the helper function to reverse the next k nodes

            curr.next = arr[0]; // Connect the previous segment's tail (`curr`) to the new head of the reversed k-group (`arr[0]`)
            curr = arr[1]; // Move `curr` to the tail of the just-reversed k-group (`arr[1]`) so it's ready for the next iteration
        }
        return dummy.next; // The dummy node's next points to the head of the fully modified list
    }

    /**
     * Reverses a specified number of nodes (k) from a given starting node.
     * @param head The starting node of the segment to reverse.
     * @param k The number of nodes to reverse.
     * @return A ListNode array where:
     *         index 0: the new head of the reversed segment.
     *         index 1: the new tail of the reversed segment (which was the original head).
     */
    public ListNode[] revRange(ListNode head,int k){
        ListNode prev = null; // Pointer to the previous node during reversal, starts as null
        ListNode curr = head; // Pointer to the current node being processed, starts at the head of the segment

        // Reverse k nodes
        while(k-->0){ // Loop k times to reverse k nodes
            ListNode near = curr.next; // Store the next node before modifying `curr.next`
            curr.next = prev; // Reverse the pointer: current node points to the previous node
            prev = curr; // Move `prev` to the current node
            curr = near; // Move `curr` to the next node (which was stored in `near`)
        }
        // After the loop:
        // `prev` is the new head of the reversed k-group.
        // `head` (the original start of the segment) is now the tail of the reversed k-group.
        // `curr` is the node immediately following the reversed k-group.

        head.next = curr; // Connect the tail of the reversed segment (`head`) to the node that follows it (`curr`)
        return new ListNode[]{prev,head}; // Return the new head (`prev`) and the new tail (`head`) of the reversed segment
    }
}
```

## Interview Tips
*   **Explain the Dummy Node:** Clearly articulate why a dummy node is used and how it simplifies the logic, especially for the first group.
*   **Helper Function Design:** Discuss the design of the `revRange` helper function. Explain its inputs, outputs, and how it performs the reversal.
*   **Pointer Tracing:** Be prepared to trace the pointers step-by-step for a small example (e.g., `k=2` or `k=3`) to demonstrate your understanding of the reversal process.
*   **Edge Cases:** Discuss how you handle cases where `k=1` (no reversal needed), `k` is larger than the list length, or the list is empty. The current solution handles these implicitly.

## Revision Checklist
- [ ] Understand the problem statement: reverse nodes in groups of k.
- [ ] Identify the need for a dummy node.
- [ ] Implement list length calculation.
- [ ] Implement the `revRange` helper function for reversing k nodes.
- [ ] Correctly connect reversed segments.
- [ ] Handle the case where remaining nodes are less than k.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Reverse Linked List
*   Reverse Linked List II
*   Swap Nodes in Pairs
*   Remove Nth Node From End of List

## Tags
`Linked List` `Recursion` `Two Pointers`
