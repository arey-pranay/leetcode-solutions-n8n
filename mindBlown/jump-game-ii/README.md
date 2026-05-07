# Jump Game Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    int n ;
    int[] memo ;
    public int jump(int[] nums) {
        n = nums.length;
        memo = new int[n];
        Arrays.fill(memo,10001);
        return func(0,nums);
    }
    public int func(int i , int[] nums){
        if(i>=n-1) return 0;
        if(memo[i]!=10001) return memo[i];
        for(int j=1;j<=nums[i];j++)  memo[i]=Math.min(memo[i],1+func(i+j,nums));
        return memo[i];
    }
}
```

---

---
## Quick Revision
Given an array of non-negative integers, find the minimum number of jumps to reach the last index.
This problem can be solved using dynamic programming or a greedy approach.

## Intuition
The core idea is to minimize the number of jumps. At each position, we want to make a jump that allows us to reach the furthest possible index in the next step. This greedy strategy ensures we cover the maximum ground with each jump, thus minimizing the total jumps.

## Algorithm
1. Initialize `jumps` to 0, `currentReach` to 0, and `maxReach` to 0.
2. Iterate through the array from index 0 up to `n-2` (since we don't need to jump from the last element).
3. In each iteration `i`:
    a. Update `maxReach` to be the maximum of its current value and `i + nums[i]` (the furthest we can reach from the current position).
    b. If `i` equals `currentReach`, it means we have reached the end of the current jump's coverage. We then increment `jumps` and update `currentReach` to `maxReach`. This signifies starting a new jump from the furthest point reached so far.
4. Return `jumps`.

## Concept to Remember
*   **Greedy Approach:** Making the locally optimal choice at each step to achieve a globally optimal solution.
*   **Breadth-First Search (BFS) Analogy:** Each "level" in BFS can be thought of as a jump. We explore all reachable positions within one jump before considering the next jump.
*   **Dynamic Programming (as seen in the provided solution):** Storing results of subproblems (minimum jumps from index `i`) to avoid redundant calculations.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling the loop bounds or the condition for reaching the last index.
*   **Not considering the furthest reach:** A naive approach might just jump by `nums[i]` without checking if a shorter jump could lead to a better overall path.
*   **Inefficient DP state:** The provided DP solution has a time complexity issue due to repeated calculations if not optimized.
*   **Forgetting the base case:** Not handling the scenario where the array has only one element or the last element is already reached.

## Complexity Analysis
*   **Time:** O(n) - reason: We iterate through the array once.
*   **Space:** O(1) - reason: We only use a few variables to keep track of the current reach, maximum reach, and jumps. (Note: The provided DP solution has O(n) space for memoization and O(n^2) time in its naive recursive form without optimization).

## Commented Code
```java
class Solution {
    // Variable to store the length of the input array.
    int n;
    // Memoization array to store the minimum jumps required from each index.
    // Initialized with a value larger than any possible number of jumps (10001).
    int[] memo;

    // Main function to initiate the jump calculation.
    public int jump(int[] nums) {
        // Store the length of the array.
        n = nums.length;
        // Initialize the memoization array with size n.
        memo = new int[n];
        // Fill the memoization array with a sentinel value indicating uncomputed states.
        Arrays.fill(memo, 10001);
        // Call the recursive helper function starting from index 0.
        return func(0, nums);
    }

    // Recursive helper function to calculate minimum jumps from index i.
    public int func(int i, int[] nums) {
        // Base case: If the current index is already at or beyond the last index, no more jumps are needed.
        if (i >= n - 1) return 0;
        // If the minimum jumps from this index have already been computed, return the stored value.
        if (memo[i] != 10001) return memo[i];

        // Iterate through all possible jumps from the current index i.
        // The maximum jump distance is nums[i].
        for (int j = 1; j <= nums[i]; j++) {
            // For each possible next position (i + j), recursively find the minimum jumps from there.
            // Update memo[i] with the minimum of its current value and (1 + jumps from next position).
            // This represents taking one jump to (i + j) and then the minimum jumps from (i + j).
            memo[i] = Math.min(memo[i], 1 + func(i + j, nums));
        }
        // Return the computed minimum jumps from index i.
        return memo[i];
    }
}
```

## Interview Tips
*   Explain the greedy approach first, as it's more efficient and often the preferred solution.
*   If asked about DP, clearly define the state, recurrence relation, and base cases. Be prepared to discuss its time and space complexity.
*   Walk through an example with the greedy approach to demonstrate how `currentReach` and `maxReach` are updated.
*   Mention the BFS analogy to show a deeper understanding of the problem's structure.

## Revision Checklist
- [ ] Understand the problem statement: minimum jumps to reach the end.
- [ ] Identify the greedy strategy: maximize reach at each step.
- [ ] Implement the greedy algorithm with `jumps`, `currentReach`, `maxReach`.
- [ ] Consider edge cases: array of size 1, already at the end.
- [ ] Analyze time and space complexity of the greedy approach.
- [ ] (Optional) Understand the DP approach and its trade-offs.

## Similar Problems
*   Jump Game (LeetCode 55)
*   Minimum Path Sum (LeetCode 64)
*   Word Ladder (LeetCode 127)

## Tags
`Array` `Greedy` `Dynamic Programming` `Breadth-First Search`
