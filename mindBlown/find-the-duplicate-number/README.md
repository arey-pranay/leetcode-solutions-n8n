# Find The Duplicate Number

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Binary Search` `Bit Manipulation` `Pigeonhole Principle` `Floyd's Cycle Finding Algorithm`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow; //or fast; 
    }

    // 1 3 4 2 2
    // 0->1->3->2->4
        //    |   |
        //     ----

    // 3 1 3 4 2
    // 0 -> 3 -> 4 -> 2
  //        |_________|
}

```

---

---
## Quick Revision
Given an array of integers `nums` containing `n + 1` integers where each integer is between 1 and `n` (inclusive), find the duplicate number.
The problem can be solved by treating the array as a linked list and using Floyd's Tortoise and Hare (Cycle Detection) algorithm.

## Intuition
The core idea is to recognize that the array, with its values as indices and values at those indices as the next "node", forms a linked list structure. Since there are `n + 1` numbers in the range `[1, n]`, by the Pigeonhole Principle, at least one number must be repeated. This repetition creates a cycle in the linked list representation. The duplicate number is the entry point of this cycle. Floyd's algorithm is perfect for detecting cycles and finding their starting point.

## Algorithm
1. **Phase 1: Detect Cycle:**
   - Initialize two pointers, `slow` and `fast`, both starting at index 0.
   - Move `slow` one step at a time: `slow = nums[slow]`.
   - Move `fast` two steps at a time: `fast = nums[nums[fast]]`.
   - Continue this until `slow` and `fast` meet. This meeting point is somewhere within the cycle.

2. **Phase 2: Find Cycle Entry Point:**
   - Reset `slow` back to index 0.
   - Keep `fast` at the meeting point found in Phase 1.
   - Move both `slow` and `fast` one step at a time: `slow = nums[slow]` and `fast = nums[fast]`.
   - The point where `slow` and `fast` meet again is the entry point of the cycle, which is the duplicate number.

## Concept to Remember
*   **Pigeonhole Principle:** When you have more items than containers, at least one container must have more than one item. In this case, `n+1` numbers in the range `[1, n]` guarantees a duplicate.
*   **Linked List Cycle Detection (Floyd's Tortoise and Hare):** A two-pointer approach to find cycles in linked lists.
*   **Array as a Linked List:** Interpreting array values as indices to form a sequence, enabling cycle detection.

## Common Mistakes
*   **Incorrectly initializing pointers:** Starting pointers at `nums[0]` instead of index 0.
*   **Off-by-one errors in fast pointer movement:** Moving `fast` only one step or incorrectly calculating the second step.
*   **Not resetting `slow` pointer:** Forgetting to reset `slow` to the beginning (index 0) for the second phase of cycle detection.
*   **Confusing cycle detection with finding the duplicate directly:** The first meeting point is not necessarily the duplicate; it's a point within the cycle.

## Complexity Analysis
- Time: O(n) - Both phases of the algorithm traverse the array at most a constant number of times. The number of steps is proportional to `n`.
- Space: O(1) - The algorithm uses only a constant amount of extra space for the two pointers (`slow` and `fast`).

## Commented Code
```java
class Solution {
    public int findDuplicate(int[] nums) {
        // Initialize two pointers, slow and fast, to the start of the "linked list" (index 0).
        int slow = 0;
        int fast = 0;

        // Phase 1: Detect if a cycle exists and find a meeting point within the cycle.
        // The do-while loop ensures at least one iteration, as slow and fast start at the same position.
        do {
            // Move slow pointer one step: current value becomes the next index.
            slow = nums[slow];
            // Move fast pointer two steps: first step, then second step from the result.
            fast = nums[nums[fast]];
        } while (slow != fast); // Continue until slow and fast pointers meet.

        // Phase 2: Find the entrance to the cycle.
        // Reset the slow pointer back to the start of the "linked list" (index 0).
        slow = 0;
        // Keep the fast pointer at the meeting point found in Phase 1.

        // Move both pointers one step at a time until they meet again.
        while (slow != fast) {
            // Move slow pointer one step.
            slow = nums[slow];
            // Move fast pointer one step.
            fast = nums[fast];
        }

        // The point where they meet is the entrance to the cycle, which is the duplicate number.
        return slow; // or return fast, as they are equal at this point.
    }
}
```

## Interview Tips
*   **Explain the analogy:** Clearly articulate how the array can be viewed as a linked list with a cycle.
*   **Walk through an example:** Use a small array (e.g., `[1, 3, 4, 2, 2]`) to demonstrate the movement of `slow` and `fast` pointers in both phases.
*   **Justify the cycle:** Explain why a cycle *must* exist due to the constraints (`n+1` numbers in `[1, n]`).
*   **Discuss constraints:** Mention that this solution works because the numbers are within the bounds of valid indices and there's exactly one duplicate.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Recognize the array-as-linked-list analogy.
- [ ] Recall Floyd's Tortoise and Hare algorithm.
- [ ] Implement Phase 1 (cycle detection).
- [ ] Implement Phase 2 (finding cycle entry).
- [ ] Analyze time and space complexity.
- [ ] Be able to explain the intuition and algorithm clearly.

## Similar Problems
*   Linked List Cycle
*   Linked List Cycle II
*   Happy Number

## Tags
`Array` `Two Pointers` `Linked List` `Binary Search`
