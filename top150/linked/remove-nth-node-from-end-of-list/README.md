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
Given a linked list, remove the Nth node from the end of the list and return its head.
This is solved efficiently using a two-pointer approach or recursion to find the Nth node from the end.

## Intuition
The core challenge is identifying the Nth node from the *end* of a singly linked list, where we can only traverse forward. If we knew the total length of the list, say `L`, then the Nth node from the end is the `(L - n + 1)`th node from the beginning. However, calculating the length first requires a full traversal, and then another traversal to reach the target node.

A more elegant approach is to use two pointers. If we can maintain a gap of `n` nodes between two pointers, when the "fast" pointer reaches the end of the list, the "slow" pointer will be exactly at the node *before* the one we need to remove. This allows us to modify the `next` pointer of the "slow" node to skip the target node.

The provided recursive solution cleverly uses the call stack to achieve a similar effect. As the recursion unwinds, it effectively counts nodes from the end. When the count reaches `n+1`, it means the current node is the one *before* the Nth node from the end, allowing us to perform the removal.

## Algorithm
1. **Handle Edge Cases:** If the list is empty or `n` is invalid, return the original head.
2. **Dummy Node:** Create a dummy node that points to the head of the list. This simplifies handling cases where the head itself needs to be removed.
3. **Recursive Traversal:**
    * Define a recursive helper function `func(node, current_count, n)`.
    * **Base Case:** If `node` is `null`, return `0` (representing the count of nodes from this point onwards).
    * **Recursive Step:**
        * Recursively call `func` on `node.next`, incrementing `current_count` by 1. The return value of the recursive call represents the count of nodes from `node.next` to the end.
        * Add 1 to this returned count to get the total count of nodes from the current `node` to the end.
    * **Removal Logic:** If the `current_count` (which is now the count from the current node to the end) is equal to `n + 1`, it means the current `node` is the node *before* the Nth node from the end. In this case, update `node.next` to `node.next.next`, effectively skipping the Nth node from the end.
    * **Return Count:** Return the calculated `current_count`.
4. **Initiate Recursion:** Call the `func` starting from the `dummy` node with an initial `current_count` of `0`.
5. **Return Result:** Return `dummy.next`.

## Concept to Remember
*   **Singly Linked Lists:** Understanding node structure, traversal, and manipulation (changing `next` pointers).
*   **Recursion:** Using the call stack to manage state and unwind operations.
*   **Two-Pointer Technique (Implicit):** While not explicitly two pointers, the recursive approach implicitly uses the call stack to simulate the behavior of a "slow" pointer that is `n` steps behind a "fast" pointer reaching the end.
*   **Dummy Node:** A common technique to simplify edge cases in linked list problems, especially when the head might be modified.

## Common Mistakes
*   **Off-by-One Errors:** Incorrectly calculating the position of the node to remove (e.g., `n` vs. `n+1` or `L-n` vs. `L-n+1`).
*   **Handling Head Removal:** Not properly accounting for the case where the head of the list needs to be removed. A dummy node is crucial here.
*   **Modifying `next` Pointer Incorrectly:** Trying to modify the `next` pointer of the node to be removed instead of the node *before* it.
*   **Infinite Recursion:** Incorrect base case or recursive step in the recursive solution.
*   **Not Returning the Correct Head:** Forgetting to return `dummy.next` after modifications.

## Complexity Analysis
- Time: O(L) - reason: The recursive function traverses the linked list once. In the worst case, it visits every node.
- Space: O(L) - reason: The space complexity is due to the recursion depth, which can be up to the length of the linked list in the worst case (for the call stack).

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
        // Create a dummy node to simplify edge cases, especially removing the head.
        ListNode dummy = new ListNode(0);
        // The dummy node's next pointer points to the actual head of the list.
        dummy.next = head;
        // Call the recursive helper function starting from the dummy node.
        // The initial current count is 0. 'n' is the target position from the end.
        func(dummy,0,n);
        // Return the next of the dummy node, which will be the new head of the modified list.
        return dummy.next;
    }

    // Recursive helper function to traverse the list and count nodes from the end.
    // head: the current node being processed.
    // curr: the count of nodes from the current node to the end of the list (accumulated during unwinding).
    // n: the position from the end of the node to be removed.
    private int func(ListNode head, int curr,int n){
        // Base case: If the current node is null, we've reached the end of the list.
        // Return 0, as there are no nodes from this point onwards.
        if(head==null) return 0;
        // Recursive step: Call func on the next node.
        // The result of the recursive call is the count of nodes from head.next to the end.
        // We add 1 to it to get the count of nodes from the current 'head' to the end.
        else curr = 1 + func(head.next,curr,n);

        // Check if the current count (from this node to the end) is n+1.
        // If it is, this 'head' node is the node *before* the Nth node from the end.
        if(curr == n+1) {
            // Skip the Nth node from the end by updating the current node's next pointer.
            // head.next.next points to the node after the one to be removed.
            head.next = head.next.next;
        }
        // Return the total count of nodes from the current 'head' to the end.
        return curr;
    }
}
```

## Interview Tips
*   **Explain the Dummy Node:** Clearly articulate why a dummy node is used and how it simplifies edge cases like removing the head.
*   **Trace the Recursion:** Be prepared to trace the recursive calls and returns on a small example to demonstrate your understanding of how the count is managed and the removal happens.
*   **Discuss Two-Pointer Alternative:** Mention the two-pointer approach (fast and slow pointers) as an iterative alternative and discuss its space complexity advantage (O(1)).
*   **Clarify `n`:** Ensure you understand if `n` is 1-indexed or 0-indexed, and if it refers to the node itself or the position. The problem statement implies 1-indexed.

## Revision Checklist
- [ ] Understand singly linked list operations.
- [ ] Implement the dummy node pattern.
- [ ] Trace recursive calls and return values.
- [ ] Identify the node *before* the one to be removed.
- [ ] Handle the case where the head needs to be removed.
- [ ] Analyze time and space complexity.
- [ ] Consider iterative two-pointer solution.

## Similar Problems
*   Reverse Linked List
*   Merge Two Sorted Lists
*   Remove Duplicates from Sorted List
*   Linked List Cycle

## Tags
`Linked List` `Recursion` `Two Pointers`
