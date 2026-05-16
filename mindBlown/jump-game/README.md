# Jump Game

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy`  
**Time:** O(N^2)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        return canReach(nums,n-1);
    }
    public boolean canReach(int[] nums, int e){
        if(e==0) return true;
        for(int i = e-1; i>=0;i--){
            if(i+nums[i]>=e) return canReach(nums,i);
        }
        return false;
    }
}
```

---

---
## Quick Revision
Given an array of non-negative integers, determine if you can reach the last index starting from the first index.
This problem can be solved using a greedy approach by tracking the farthest reachable index.

## Intuition
The core idea is to always try to reach as far as possible. If at any point, the current position plus the jump value from that position allows us to reach or surpass the last index, we know it's possible. Conversely, if we iterate through all possible previous positions and none of them can reach the current position, then it's impossible. The recursive solution provided explores this by asking "can I reach the end from here?" and then recursively asking "can I reach this position from an earlier one?".

## Algorithm
1. **Base Case:** If the target index `e` is 0, we are already at the start, so return `true`.
2. **Iterate Backwards:** Loop from `e-1` down to 0. This represents checking all possible previous positions from which we might have jumped to `e`.
3. **Check Reachability:** For each index `i`, check if `i + nums[i]` is greater than or equal to `e`. This means we can jump from index `i` and reach or surpass the target index `e`.
4. **Recursive Call:** If we can reach `e` from `i`, then recursively call `canReach` for index `i`. This effectively asks if we can reach index `i` from the start.
5. **Return False:** If the loop completes without finding any index `i` that can reach `e`, return `false`.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Backtracking (Implicit):** The recursive approach explores possibilities and "backtracks" if a path doesn't lead to a solution.
*   **Greedy Strategy:** Making the locally optimal choice at each step with the hope of finding a global optimum.

## Common Mistakes
*   **Infinite Recursion:** Not having a proper base case or incorrect recursive step can lead to infinite loops.
*   **Off-by-One Errors:** Incorrectly handling array indices or boundary conditions.
*   **Inefficient Recursive Calls:** The provided recursive solution can be very inefficient due to repeated calculations for the same subproblems (overlapping subproblems).
*   **Not Considering All Previous Jumps:** Failing to check all possible preceding positions that could lead to the current one.

## Complexity Analysis
*   Time: O(N^2) - In the worst case, for each position `e`, we might iterate through all previous `i` positions. This leads to a nested loop-like behavior in the recursion.
*   Space: O(N) - Due to the recursion depth, which can be up to N in the worst case (e.g., an array of all 1s).

## Commented Code
```java
class Solution {
    // Main function to check if the last index can be reached.
    public boolean canJump(int[] nums) {
        // Get the length of the input array.
        int n = nums.length;
        // Call the helper function to check if the last index (n-1) can be reached.
        return canReach(nums, n - 1);
    }

    // Recursive helper function to determine if index 'e' can be reached from the start.
    public boolean canReach(int[] nums, int e) {
        // Base case: If the target index is 0, we are already at the start, so it's reachable.
        if (e == 0) return true;

        // Iterate backwards from the index just before 'e' down to the start (index 0).
        for (int i = e - 1; i >= 0; i--) {
            // Check if from index 'i', we can make a jump that reaches or surpasses index 'e'.
            if (i + nums[i] >= e) {
                // If we can reach 'e' from 'i', then recursively check if 'i' itself is reachable from the start.
                return canReach(nums, i);
            }
        }
        // If no previous index 'i' can reach 'e', then 'e' is not reachable.
        return false;
    }
}
```

## Interview Tips
*   Explain the recursive approach first, highlighting its logic.
*   Discuss the time and space complexity of the recursive solution and identify its inefficiency (overlapping subproblems).
*   Propose an optimized solution, likely a greedy approach with O(N) time complexity, by tracking the `maxReach`.
*   Be prepared to explain why the greedy approach works: if you can reach a certain point, you can also reach any point before it.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Trace the recursive solution with a small example.
- [ ] Identify the base case and recursive step.
- [ ] Analyze the time and space complexity of the recursive solution.
- [ ] Think about how to optimize the solution (e.g., using dynamic programming or a greedy approach).
- [ ] Implement the optimized greedy solution.

## Similar Problems
Jump Game II
Jump Game III
Jump Game IV

## Tags
`Array` `Dynamic Programming` `Greedy`
