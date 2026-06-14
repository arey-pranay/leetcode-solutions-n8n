# Maximum Twin Sum Of A Linked List

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers` `Recursion`  
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
This is solved by reversing the second half of the list and then iterating through both halves simultaneously to find the maximum sum.

## Intuition
The problem asks for the maximum sum of "twin" nodes. A twin pair consists of the i-th node from the beginning and the i-th node from the end. If we can somehow align these pairs, we can easily calculate their sums and find the maximum. A common technique for dealing with the "end" of a linked list is to reverse it. If we reverse the second half of the linked list, the i-th node from the start will be paired with the i-th node from the *original* end (which is now the i-th node from the start of the reversed second half).

## Algorithm
1. **Find the middle of the linked list:** Use the fast and slow pointer technique. The slow pointer will be at the middle when the fast pointer reaches the end.
2. **Reverse the second half of the linked list:** Starting from the node after the middle, reverse the remaining portion of the list.
3. **Calculate twin sums:** Initialize two pointers, one at the head of the original list and another at the head of the reversed second half. Iterate through both lists simultaneously, summing the values of the corresponding nodes. Keep track of the maximum sum found.
4. **Return the maximum sum.**

## Concept to Remember
*   **Linked List Manipulation:** Reversing a linked list is a fundamental operation.
*   **Fast and Slow Pointers:** Essential for finding the middle of a linked list efficiently.
*   **Two-Pointer Technique:** Used to iterate through two related lists or parts of a list simultaneously.

## Common Mistakes
*   Incorrectly identifying the middle of the linked list, especially for even-length lists.
*   Errors in the linked list reversal logic, leading to broken links or infinite loops.
*   Not handling the case where the list has only two nodes.
*   Forgetting to advance both pointers correctly during the sum calculation phase.
*   The provided solution uses recursion, which is an alternative approach to reversing the second half implicitly. A common mistake with recursion here is stack overflow for very long lists or incorrect base cases.

## Complexity Analysis
*   **Time:** O(N) - We traverse the list to find the middle (N/2), reverse the second half (N/2), and then traverse both halves to find the max sum (N/2). Total is proportional to N.
*   **Space:** O(1) - If reversing in-place. The provided recursive solution uses O(N) space due to the recursion call stack in the worst case.

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
    // 'start' will be used as a pointer to traverse the first half of the list
    // It's a class member so it can be accessed and modified by the recursive function
    ListNode start;
    // 'max' stores the maximum twin sum found so far, initialized to the smallest possible integer value
    int max = Integer.MIN_VALUE;

    // The main function to find the maximum twin sum
    public int pairSum(ListNode head) {
        // Initialize the 'start' pointer to the head of the list
        start = head;
        // Call the recursive helper function to traverse the list and calculate sums
        func(head);
        // Return the maximum sum found
        return max;
    }

    // Recursive helper function to traverse the list and calculate twin sums
    public void func(ListNode end) {
        // Base case: if the 'end' pointer reaches the end of the list (null), stop recursion
        if (end == null) return;
        // Recursive step: move the 'end' pointer to the next node and call func again
        // This effectively traverses the list from head to tail, and the recursive calls unwind from tail to head
        func(end.next);
        // When the recursion unwinds, 'end' points to a node from the second half (or middle)
        // and 'start' points to the corresponding node from the first half
        // Calculate the sum of the current twin pair
        max = Math.max(max, start.val + end.val);
        // Move the 'start' pointer to the next node in the first half for the next twin pair calculation
        start = start.next;
    }
}
```

## Interview Tips
*   Clearly explain the "twin" concept and how reversing the second half helps align these pairs.
*   Walk through the fast/slow pointer approach to find the middle.
*   Discuss the in-place reversal of the second half as an optimization for space complexity.
*   Be prepared to explain the recursive solution's logic and its space implications.

## Revision Checklist
- [ ] Understand the definition of a "twin sum".
- [ ] Implement fast and slow pointers to find the middle.
- [ ] Implement linked list reversal for the second half.
- [ ] Implement the two-pointer iteration to calculate sums.
- [ ] Consider edge cases (empty list, single node, two nodes).
- [ ] Analyze time and space complexity.
- [ ] Understand the recursive approach and its trade-offs.

## Similar Problems
*   Reverse Linked List
*   Find the Middle of the Linked List
*   Merge Two Sorted Lists (uses similar pointer manipulation)

## Tags
`Linked List` `Two Pointers` `Recursion`
