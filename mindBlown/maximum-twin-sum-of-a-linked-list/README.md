# Maximum Twin Sum Of A Linked List

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Linked List` `Recursion` `Two Pointers`  
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
This is solved by using recursion to traverse to the end, and then summing pairs as the recursion unwinds.

## Intuition
The problem asks for the maximum sum of "twin" nodes. A twin pair consists of the i-th node from the beginning and the i-th node from the end. If we can somehow access both ends of the list simultaneously or in a coordinated manner, we can calculate these sums. The recursive approach naturally allows us to reach the end of the list first. As the recursion unwinds, we are effectively moving from the end of the list towards the beginning. Simultaneously, we can maintain a pointer that starts at the head and moves forward. This way, when the recursive call returns, we have access to a node from the end (via the `end` parameter) and a corresponding node from the beginning (via the `start` pointer), allowing us to calculate the twin sum.

## Algorithm
1. Initialize a global or class-level variable `max` to `Integer.MIN_VALUE` to store the maximum twin sum found so far.
2. Initialize a global or class-level `ListNode start` pointer to `head`. This pointer will traverse from the beginning of the list.
3. Define a recursive function `func(ListNode end)` that takes the current node from the "end" traversal as input.
4. **Base Case:** If `end` is `null`, return. This signifies we have reached the end of the list.
5. **Recursive Step:** Call `func(end.next)` to move the "end" pointer one step further down the list. This ensures we process nodes from the end first.
6. **Process Twin Sum:** After the recursive call returns (meaning we are unwinding the recursion), we have access to a node from the end (`end`) and the current position of the `start` pointer (which is at the corresponding twin node from the beginning). Calculate the sum: `start.val + end.val`.
7. Update `max`: `max = Math.max(max, start.val + end.val)`.
8. Advance the `start` pointer: `start = start.next`. This prepares `start` for the next twin sum calculation as the recursion continues to unwind.
9. In the main `pairSum` function, call `func(head)` to start the recursive process.
10. Return the final `max` value.

## Concept to Remember
*   **Recursion:** Understanding how recursion can be used to traverse a data structure and process elements in a specific order (e.g., post-order traversal implicitly).
*   **Linked List Traversal:** Efficiently moving through a singly linked list.
*   **Two-Pointer Technique (Implicit):** While not explicit two pointers moving towards each other, the `start` pointer and the `end` parameter in the recursive calls effectively act as two pointers coordinating their movement.

## Common Mistakes
*   **Incorrectly managing the `start` pointer:** If `start` is not advanced correctly after each pair sum calculation, it will lead to incorrect sums.
*   **Off-by-one errors in recursion:** Misunderstanding when the base case is hit or when to perform the sum calculation can lead to errors.
*   **Not handling the `null` case for `end`:** The recursion must have a proper base case to terminate.
*   **Modifying the list unintentionally:** While this solution doesn't modify the list, other approaches might, and it's important to be aware of side effects.

## Complexity Analysis
*   **Time:** O(N) - The recursive function visits each node twice (once going down, once coming up). The `start` pointer also traverses the list once. Therefore, the total time complexity is linear with respect to the number of nodes (N).
*   **Space:** O(N) - Due to the recursion depth. In the worst case, the call stack can grow up to the number of nodes in the linked list.

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
    // This pointer will traverse from the head of the list.
    ListNode start;
    // This variable will store the maximum twin sum found. Initialize to the smallest possible integer value.
    int max = Integer.MIN_VALUE;

    // The main function to find the maximum twin sum.
    public int pairSum(ListNode head) {
        // Initialize the 'start' pointer to the head of the list.
        start = head;
        // Call the recursive helper function to traverse and calculate sums.
        func(head);
        // Return the maximum twin sum found.
        return max;
    }

    // Recursive helper function. 'end' pointer traverses from the head towards the end.
    public void func(ListNode end) {
        // Base case: If the 'end' pointer reaches the end of the list (null), stop recursion.
        if (end == null) return;
        // Recursive call: Move the 'end' pointer one step forward. This ensures we reach the end first.
        func(end.next);
        // When the recursion unwinds, 'end' is a node from the latter half of the list,
        // and 'start' is the corresponding twin node from the first half.
        // Calculate the sum of the current twin pair.
        // Update 'max' if the current twin sum is greater than the current maximum.
        max = Math.max(max, start.val + end.val);
        // Move the 'start' pointer one step forward to prepare for the next twin pair calculation
        // as the recursion continues to unwind.
        start = start.next;
    }
}
```

## Interview Tips
*   **Explain the recursive approach clearly:** Emphasize how the recursion naturally allows access to nodes from both ends of the list as it unwinds.
*   **Trace an example:** Walk through a small linked list (e.g., `[5, 4, 3, 2, 1]`) to demonstrate how `start` and `end` pointers move and how `max` is updated.
*   **Discuss space complexity:** Be prepared to explain why the space complexity is O(N) due to the recursion stack. If asked for an O(1) space solution, you'd need to mention reversing the second half of the list.
*   **Consider edge cases:** Mention handling empty lists or lists with only one node (though the problem constraints might prevent these).

## Revision Checklist
- [ ] Understand the definition of a "twin sum" pair.
- [ ] Recognize the need to access nodes from both the beginning and end of the list.
- [ ] Grasp how recursion can simulate this dual access.
- [ ] Implement the recursive function with a proper base case.
- [ ] Correctly manage the `start` pointer's advancement.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Reverse Linked List
*   Merge Two Sorted Lists
*   Palindrome Linked List
*   Remove Nth Node From End of List

## Tags
`Linked List` `Recursion` `Two Pointers`
