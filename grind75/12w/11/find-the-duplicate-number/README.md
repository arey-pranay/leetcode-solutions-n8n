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
Given an array of integers `nums` containing `n + 1` integers where each integer is between 1 and `n` (inclusive).
The solution uses Floyd's Tortoise and Hare (Cycle Detection) algorithm to find the duplicate number.

## Intuition
The problem statement guarantees that there's exactly one duplicate number and all numbers are within a specific range. This structure hints at a graph-like problem. If we consider each number `nums[i]` as a pointer to the index `nums[i]`, and start traversing from index 0, the presence of a duplicate number will inevitably create a cycle in this linked list representation. The duplicate number will be the entry point of this cycle. Floyd's algorithm is perfect for detecting cycles and finding their entry point.

## Algorithm
1. Initialize two pointers, `slow` and `fast`, both starting at index 0.
2. Move `slow` one step at a time: `slow = nums[slow]`.
3. Move `fast` two steps at a time: `fast = nums[nums[fast]]`.
4. Continue steps 2 and 3 until `slow` and `fast` meet. This indicates a cycle has been detected.
5. Reset `slow` back to index 0.
6. Move both `slow` and `fast` one step at a time: `slow = nums[slow]` and `fast = nums[fast]`.
7. Continue steps 6 until `slow` and `fast` meet again. This meeting point is the duplicate number.
8. Return `slow` (or `fast`).

## Concept to Remember
*   **Cycle Detection (Floyd's Tortoise and Hare):** This algorithm is fundamental for finding cycles in linked lists or sequences that behave like linked lists.
*   **Array as a Linked List:** Understanding how to model an array as a linked list where `nums[i]` points to the next element at index `nums[i]`.
*   **Pigeonhole Principle:** The problem guarantees `n + 1` numbers in the range `[1, n]`, implying at least one number must be repeated.

## Common Mistakes
*   **Incorrect Initialization:** Starting `slow` and `fast` at `nums[0]` instead of index 0.
*   **Off-by-One Errors in Fast Pointer Movement:** Incorrectly calculating `fast = nums[nums[fast]]`.
*   **Not Resetting `slow`:** Forgetting to reset `slow` to the start (index 0) after the first collision.
*   **Misunderstanding Cycle Entry Point:** Confusing the collision point with the cycle entry point.

## Complexity Analysis
*   **Time:** O(n) - The pointers traverse the array at most a constant number of times. The first phase (finding collision) takes O(n) steps, and the second phase (finding cycle entry) also takes O(n) steps.
*   **Space:** O(1) - The algorithm uses only a constant amount of extra space for the two pointers.

## Commented Code
```java
class Solution {
    public int findDuplicate(int[] nums) {
        // Initialize two pointers, slow and fast, to the start of the array (index 0).
        int slow = 0;
        int fast = 0;

        // Phase 1: Detect if a cycle exists and find the meeting point within the cycle.
        // The do-while loop ensures at least one iteration, necessary for the first move.
        do {
            // Move slow pointer one step: current position's value becomes the next index.
            slow = nums[slow];
            // Move fast pointer two steps: first step, then second step from the new position.
            fast = nums[nums[fast]];
        } while (slow != fast); // Continue until slow and fast pointers meet.

        // Phase 2: Find the entrance to the cycle.
        // Reset the slow pointer back to the start of the array (index 0).
        slow = 0;
        // Move both pointers one step at a time until they meet again.
        // This meeting point is the duplicate number, which is the entrance of the cycle.
        while (slow != fast) {
            // Move slow pointer one step.
            slow = nums[slow];
            // Move fast pointer one step.
            fast = nums[fast];
        }

        // Return the duplicate number (where slow and fast met in Phase 2).
        return slow; // or return fast; as they are equal at this point.
    }
}
```

## Interview Tips
*   **Explain the Analogy:** Clearly articulate the "array as a linked list" analogy and how a duplicate creates a cycle.
*   **Walk Through an Example:** Use a small example array (e.g., `[1, 3, 4, 2, 2]`) and trace the `slow` and `fast` pointers step-by-step for both phases.
*   **Justify the Cycle Entry Point:** Explain *why* resetting `slow` to the start and moving both at the same pace finds the cycle entrance. This is the trickiest part of the proof.
*   **Discuss Constraints:** Mention how the constraints (`n + 1` numbers in `[1, n]`) are crucial for this approach to work.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Grasp the "array as a linked list" analogy.
- [ ] Implement Floyd's Tortoise and Hare algorithm correctly.
- [ ] Understand the two phases of the algorithm.
- [ ] Be able to explain why the second phase finds the cycle entrance.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Linked List Cycle
*   Linked List Cycle II
*   Find All Duplicates in an Array

## Tags
`Array` `Two Pointers` `Linked List` `Cycle Detection`
