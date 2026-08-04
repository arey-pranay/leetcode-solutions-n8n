# Remove Nth Node From End Of List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers`  
**Time:** O(L)  
**Space:** O(L)

---

## Solution (java)

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        func(dummy,n,0);
        return dummy.next;
    }
    public int func(ListNode head, int n, int curr){
        if(head==null) return 0;
        curr = 1 + func(head.next,n,curr);
        if(curr==n+1) head.next=head.next.next;
        return curr;
    }
}
```

---

---
## Quick Revision
Remove the Nth node from the end of a singly linked list.
This is solved using a two-pointer approach or recursion to find the Nth node from the end.

## Intuition
The core challenge is that we don't know the length of the list beforehand, and we need to access a node relative to the *end*. A common technique for linked lists when dealing with relative positions is to use two pointers. If we can maintain a fixed distance between two pointers, when the faster pointer reaches the end, the slower pointer will be at the desired position. For the Nth node from the end, this distance is N.

Alternatively, recursion naturally traverses to the end of the list and then unwinds. As it unwinds, it can count nodes from the end. When the count reaches N, we know we've found the node *before* the one to be removed.

## Algorithm
1.  **Using Two Pointers (Iterative Approach - not the provided solution, but a common alternative):**
    *   Create a `dummy` node that points to the `head` of the list. This simplifies edge cases like removing the head.
    *   Initialize two pointers, `fast` and `slow`, both pointing to the `dummy` node.
    *   Move the `fast` pointer `n` steps ahead.
    *   Now, move both `fast` and `slow` pointers one step at a time until `fast` reaches the end of the list (i.e., `fast.next` is null).
    *   At this point, `slow` will be pointing to the node *before* the Nth node from the end.
    *   Update `slow.next` to `slow.next.next` to remove the Nth node.
    *   Return `dummy.next`.

2.  **Using Recursion (Provided Solution):**
    *   Create a `dummy` node that points to the `head` of the list.
    *   Define a recursive helper function `func(node, n, current_count)` that returns the count of nodes from the current `node` to the end of the list.
    *   **Base Case:** If `node` is `null`, return 0.
    *   **Recursive Step:**
        *   Recursively call `func` on `node.next` to get the count from the next node onwards.
        *   The current node's count is `1 +` the count returned by the recursive call.
        *   If this calculated count (`curr`) is equal to `n + 1`, it means the *current* node is the node *before* the Nth node from the end. So, update `head.next` to `head.next.next` to skip the Nth node.
        *   Return the calculated count (`curr`).
    *   Call the `func` starting from the `dummy` node with an initial count of 0.
    *   Return `dummy.next`.

## Concept to Remember
*   **Linked List Traversal:** Understanding how to move through a linked list node by node.
*   **Dummy Nodes:** Using a dummy node simplifies edge cases, especially when modifying the head of the list.
*   **Recursion (for this solution):** Understanding how to use the call stack to maintain state and count elements from the end.
*   **Two Pointers (common alternative):** A powerful technique for solving problems involving relative positions in lists or arrays.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly calculating the position of the node to remove or the node before it.
*   **Handling edge cases:** Not properly considering cases where `n` is 1 (removing the last node) or `n` is equal to the list length (removing the head).
*   **Modifying `head` directly:** If the head needs to be removed, directly modifying `head` without a dummy node can be problematic.
*   **Incorrectly advancing pointers:** In the two-pointer approach, ensuring the `fast` pointer is `n` steps ahead *before* starting the simultaneous traversal.
*   **Stack Overflow:** For very long lists, a deep recursive solution might lead to a stack overflow error.

## Complexity Analysis
*   **Time:** O(L) - reason: The recursive function `func` visits each node in the linked list exactly once to count them.
*   **Space:** O(L) - reason: The space complexity is due to the recursion depth, which in the worst case (a list of length L) will be L, as each recursive call adds a frame to the call stack.

## Commented Code
```java
class Solution {
    // The main method to remove the Nth node from the end of the list.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node. This node's 'next' pointer will point to the actual head.
        // Using a dummy node simplifies edge cases, especially when the head itself needs to be removed.
        ListNode dummy = new ListNode();
        // Link the dummy node to the original head of the list.
        dummy.next = head;
        // Call the recursive helper function 'func' starting from the dummy node.
        // 'n' is the target position from the end, and '0' is the initial count.
        func(dummy,n,0);
        // After the recursion completes, the Nth node from the end will be removed.
        // Return dummy.next, which will be the new head of the modified list.
        return dummy.next;
    }

    // Recursive helper function to traverse the list, count nodes from the end, and remove the Nth node.
    // 'head' is the current node being processed.
    // 'n' is the target position from the end (1-indexed).
    // 'curr' is the count of nodes from the current 'head' to the end of the list (initially 0).
    public int func(ListNode head, int n, int curr){
        // Base case: If the current node is null, we've reached the end of the list. Return 0.
        if(head==null) return 0;
        // Recursive step:
        // 1. Recursively call 'func' on the next node (head.next).
        // 2. Add 1 to the result of the recursive call to account for the current node.
        // 3. Update 'curr' with this new count. This 'curr' represents the number of nodes from the current 'head' to the end.
        curr = 1 + func(head.next,n,curr);
        // Check if the current count 'curr' is exactly n + 1.
        // If curr == n + 1, it means the *next* node (head.next) is the Nth node from the end.
        // For example, if n=1 (last node), and curr=2, then head.next is the last node.
        // If n=2 (second to last), and curr=3, then head.next is the second to last node.
        if(curr==n+1) {
            // To remove the Nth node from the end, we bypass it by setting the 'next' pointer of the current node ('head')
            // to the node *after* the Nth node (head.next.next).
            head.next=head.next.next;
        }
        // Return the calculated count 'curr' for the current node. This count will be used by the caller.
        return curr;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the constraints on `n` (e.g., is `n` always valid? Can `n` be larger than the list size?).
*   **Explain the Dummy Node:** Clearly articulate why a dummy node is used and how it simplifies edge cases.
*   **Trace with Examples:** Walk through a small example (e.g., list `[1,2,3,4,5]`, `n=2`) to demonstrate your understanding of the algorithm.
*   **Discuss Alternatives:** Mention the two-pointer iterative approach as a common and often preferred alternative due to its O(1) space complexity.

## Revision Checklist
- [ ] Understand the problem: remove Nth node from the end.
- [ ] Recognize the need to know list length or use relative positioning.
- [ ] Implement the dummy node technique.
- [ ] Understand the recursive counting mechanism.
- [ ] Correctly identify the node *before* the one to be removed.
- [ ] Handle edge cases (removing head, removing tail).
- [ ] Analyze time and space complexity.

## Similar Problems
*   [19. Remove Nth Node From End Of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) (This problem)
*   [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)
*   [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)
*   [83. Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)

## Tags
`Linked List` `Recursion` `Two Pointers`
