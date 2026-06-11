# Remove Nth Node From End Of List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers`  
**Time:** O(L)  
**Space:** O(L)

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        func(dummy,0,n);
        return dummy.next;
    }
    private int func(ListNode head, int curr,int n){
        if(head==null) return 0;
        else curr = 1 + func(head.next,curr,n);

        if(curr == n+1) head.next = head.next.next;
        return curr;
    }
}
```

---

---
## Quick Revision
Given a linked list, remove the nth node from the end of the list and return its head.
Solve using recursion to count nodes from the end and identify the node to remove.

## Intuition
The core challenge is finding the Nth node from the *end* without knowing the total length beforehand. A recursive approach naturally traverses to the end of the list. As the recursion unwinds, we can count the nodes from the end. When the count matches `n + 1`, we've found the node *before* the one we need to remove, allowing us to bypass it.

## Algorithm
1. Create a dummy node that points to the head of the list. This simplifies edge cases, especially removing the head itself.
2. Implement a recursive helper function `func(node, current_count, n)`:
    a. Base Case: If `node` is null, return 0 (representing the end of the list).
    b. Recursive Step: Call `func` on `node.next`, incrementing the `current_count` by 1 after the recursive call returns. The returned value from the recursive call is the count of nodes from the end of the sublist starting at `node.next`.
    c. Removal Logic: If the `current_count` (which now represents the position from the end of the *entire* list) is equal to `n + 1`, it means the current `node` is the node *before* the Nth node from the end. Update `node.next` to skip the Nth node: `node.next = node.next.next`.
    d. Return Value: Return the `current_count`.
3. Call the recursive function starting from the dummy node.
4. Return `dummy.next`, which will be the new head of the modified list.

## Concept to Remember
*   **Recursion:** Understanding how recursive calls build up and unwind is crucial for tracking state (like node count) from the end.
*   **Linked List Manipulation:** Properly updating `next` pointers to remove a node is fundamental.
*   **Dummy Node:** A common technique to handle edge cases in linked list problems, particularly when modifying the head.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly calculating the `n+1` condition, leading to removing the wrong node or no node at all.
*   **Not handling the dummy node correctly:** Forgetting to use a dummy node can complicate the logic for removing the head.
*   **Stack Overflow:** For very long lists, a deep recursion might exceed the stack limit (though less common in typical LeetCode constraints).
*   **Modifying `head` directly:** If the head needs to be removed, direct modification without a dummy node is problematic.

## Complexity Analysis
- Time: O(L) - reason: The recursive function visits each node in the linked list exactly once.
- Space: O(L) - reason: The space complexity is determined by the recursion depth, which in the worst case (a list of length L) is proportional to L due to the call stack.

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases, especially removing the head.
        ListNode dummy = new ListNode(0);
        // Link the dummy node to the original head.
        dummy.next = head;
        // Call the recursive helper function starting from the dummy node.
        // The initial current count is 0, and n is the target position from the end.
        func(dummy,0,n);
        // Return the next of the dummy node, which is the new head of the modified list.
        return dummy.next;
    }

    // Recursive helper function to traverse the list and remove the Nth node from the end.
    // head: the current node being processed.
    // curr: the count of nodes processed so far from the start (used implicitly by recursion depth).
    // n: the position from the end of the node to remove.
    private int func(ListNode head, int curr,int n){
        // Base case: If the current node is null, we've reached the end of the list. Return 0.
        if(head==null) return 0;
        // Recursive step: Call func on the next node.
        // The result of the recursive call is the count of nodes from the end of the sublist starting at head.next.
        // We add 1 to this count to get the count from the end of the list starting at 'head'.
        else curr = 1 + func(head.next,curr,n);

        // Check if the current node is the node *before* the Nth node from the end.
        // If curr == n + 1, it means 'head' is the (n+1)th node from the end.
        // Therefore, head.next is the Nth node from the end, which we need to remove.
        if(curr == n+1) {
            // Skip the Nth node from the end by updating the 'next' pointer.
            head.next = head.next.next;
        }
        // Return the current count of nodes from the end.
        return curr;
    }
}
```

## Interview Tips
*   **Explain the dummy node:** Clearly articulate why a dummy node is used and how it simplifies edge cases.
*   **Trace the recursion:** Be prepared to trace the recursive calls and the unwinding process, explaining how the `curr` count is calculated and used.
*   **Discuss alternative approaches:** Mention the two-pointer approach (fast and slow pointers) as a common iterative alternative and discuss its trade-offs (e.g., constant space).
*   **Handle edge cases:** Explicitly mention how your solution handles removing the head or tail, or a list with only one node.

## Revision Checklist
- [ ] Understand linked list structure.
- [ ] Grasp recursion for list traversal.
- [ ] Implement dummy node for edge cases.
- [ ] Correctly identify the node to remove using recursion count.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing recursive calls.

## Similar Problems
*   Reverse Linked List
*   Merge Two Sorted Lists
*   Remove Duplicates from Sorted List
*   Linked List Cycle

## Tags
`Linked List` `Recursion` `Two Pointers`
