# Maximum Twin Sum Of A Linked List

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Recursion` `Linked List` `Two Pointers`  
**Time:** O(N)  
**Space:** O(N)

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
    ListNode start;
    int max = Integer.MIN_VALUE;

    public int pairSum(ListNode head) {
        start = head;
        func(head);
        return max;
    }

    public void func(ListNode end) {
        if (end == null) return;
        func(end.next);
        max = Math.max(max, start.val + end.val);
        start = start.next;
    }
}
```

---

---
## Quick Revision
Given a linked list, find the maximum sum of a pair of nodes where the nodes are equidistant from the start and end of the list.
This is solved by recursively traversing to the end, and then summing pairs as the recursion unwinds.

## Intuition
The problem asks for the maximum sum of "twin" nodes. A twin pair consists of the i-th node from the beginning and the i-th node from the end. If we can somehow access these pairs simultaneously, we can calculate their sums and find the maximum. The recursive approach naturally allows us to reach the end of the list first. As the recursion unwinds, the `end` pointer effectively moves backward from the end of the list, while a separate `start` pointer (initialized at the head) moves forward. This allows us to pair up the corresponding nodes.

## Algorithm
1. Initialize a global or class-level variable `max` to `Integer.MIN_VALUE` to store the maximum twin sum found so far.
2. Initialize a global or class-level `ListNode start` pointer to the `head` of the linked list. This pointer will traverse from the beginning.
3. Define a recursive function `func(ListNode end)`:
    a. Base Case: If `end` is `null`, return.
    b. Recursive Step: Call `func(end.next)` to move the `end` pointer towards the end of the list.
    c. Process Pair: After the recursive call returns (meaning we've reached the end and are unwinding), calculate the sum of `start.val` and `end.val`.
    d. Update Maximum: Update `max = Math.max(max, start.val + end.val)`.
    e. Advance Start Pointer: Move the `start` pointer to the next node: `start = start.next`.
4. In the `pairSum` method, call `func(head)` to start the recursive process.
5. Return the final `max` value.

## Concept to Remember
*   **Recursion:** Understanding how recursive calls stack and unwind is crucial for this solution.
*   **Linked List Traversal:** Efficiently moving through a linked list is fundamental.
*   **Two Pointers (Implicit):** Although not explicit `ListNode` variables in the traditional sense, the `start` and `end` pointers work together to simulate a two-pointer approach across the list.

## Common Mistakes
*   **Incorrectly managing the `start` pointer:** If the `start` pointer isn't advanced correctly within the recursive unwinding, you'll pair the wrong nodes.
*   **Off-by-one errors in recursion:** Missing the base case or incorrectly handling the recursive step can lead to infinite recursion or incorrect results.
*   **Not initializing `max` correctly:** Starting `max` at 0 might be problematic if all twin sums are negative.
*   **Modifying the list unintentionally:** While this solution doesn't modify the list, other approaches might, and it's important to be aware of side effects.

## Complexity Analysis
*   **Time:** O(N) - The recursive function visits each node twice (once going down the recursion, once coming up).
*   **Space:** O(N) - Due to the recursion call stack, which can go as deep as the number of nodes in the linked list in the worst case.

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
    ListNode start; // Pointer to traverse from the beginning of the list
    int max = Integer.MIN_VALUE; // Variable to store the maximum twin sum found

    public int pairSum(ListNode head) {
        start = head; // Initialize the 'start' pointer to the head of the list
        func(head); // Start the recursive traversal from the head
        return max; // Return the maximum twin sum found
    }

    public void func(ListNode end) {
        if (end == null) return; // Base case: if the 'end' pointer reaches the end of the list, stop recursion
        func(end.next); // Recursively call 'func' with the next node, moving 'end' towards the end
        // When the recursion unwinds, 'end' is effectively moving backward from the end
        // and 'start' is moving forward from the beginning, allowing us to pair them.
        max = Math.max(max, start.val + end.val); // Calculate the sum of the current pair and update 'max' if it's greater
        start = start.next; // Move the 'start' pointer to the next node for the next pair calculation
    }
}
```

## Interview Tips
1.  **Explain the "twin" concept:** Clearly articulate what a twin pair means (i-th from start, i-th from end).
2.  **Walk through the recursion:** Describe how the `start` and `end` pointers interact during the recursive calls and unwinding. Use a small example list to illustrate.
3.  **Discuss space complexity:** Be prepared to explain why the space complexity is O(N) due to the recursion stack. If asked for an O(1) space solution, mention the approach of reversing the second half of the list.
4.  **Consider edge cases:** Think about empty lists, lists with one node, or lists with an even/odd number of nodes.

## Revision Checklist
- [ ] Understand the definition of a "twin sum".
- [ ] Trace the recursive `func` method with a small linked list.
- [ ] Identify how `start` and `end` pointers work together.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative solutions (e.g., iterative with list reversal).

## Similar Problems
*   Reverse Linked List
*   Merge Two Sorted Lists
*   Palindrome Linked List

## Tags
`Recursion` `Linked List` `Two Pointers`
