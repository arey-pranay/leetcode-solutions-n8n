# Maximum Number Of Jumps To Reach The Last Index

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(N^2)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    int[] arr;
    int[] memo;
    int t;
    public int maximumJumps(int[] nums, int target) {
        arr = nums;
        t = target;
        memo = new int[nums.length];
        Arrays.fill(memo,-2);
        return func(0);
    }
    public int func(int i){
        int n  = arr.length;
        if(i==n-1) return 0;
        if(memo[i] != -2) return memo[i];
        
        int max = -1;
        for(int j=i+1; j<n; j++){
            if(Math.abs(arr[i]-arr[j]) <= t){
                int temp =func(j);
                if(temp != -1) max = Math.max(max,1+temp);
            }
        }
        
        return memo[i] = max;
    }
}
// nums =
// [1,0,2]
// target =
// 1

// Use Testcase
// Output
// -1
// Expected
// 1
```

---

---
## Quick Revision
This problem asks for the maximum number of jumps to reach the last index from the first index, given a jump condition.
We solve this using dynamic programming with memoization, exploring all valid jumps from each position.

## Intuition
The core idea is that to find the maximum jumps from index `i`, we need to consider all possible next valid jumps to index `j`. For each valid `j`, we recursively find the maximum jumps from `j`. The maximum jumps from `i` will be 1 (for the jump from `i` to `j`) plus the maximum jumps from `j`. If no valid jumps are possible from `i` to reach the end, it's impossible, hence -1. Memoization is crucial to avoid redundant calculations for subproblems.

## Algorithm
1. Initialize a memoization array `memo` of the same size as `nums`, filled with a sentinel value (e.g., -2) to indicate uncomputed states.
2. Define a recursive function `func(i)` that returns the maximum number of jumps to reach the last index from index `i`.
3. Base Case: If `i` is the last index (`n-1`), return 0 (no more jumps needed).
4. Memoization Check: If `memo[i]` is not the sentinel value, return `memo[i]`.
5. Initialize `max_jumps` to -1 (representing impossibility).
6. Iterate through all possible next indices `j` from `i+1` to `n-1`.
7. For each `j`, check if the jump condition `abs(arr[i] - arr[j]) <= target` is met.
8. If the condition is met, recursively call `func(j)`.
9. If `func(j)` returns a valid result (not -1), update `max_jumps = max(max_jumps, 1 + func(j))`.
10. Store the computed `max_jumps` in `memo[i]` and return it.
11. The initial call will be `func(0)`.

## Concept to Remember
*   **Dynamic Programming (DP):** Breaking down a problem into overlapping subproblems and solving each subproblem only once.
*   **Recursion with Memoization:** Using a recursive approach to explore possibilities and storing results of subproblems to avoid recomputation.
*   **State Definition:** The state `dp[i]` or `memo[i]` represents the maximum jumps from index `i` to the end.
*   **Transition:** The transition involves iterating through all valid next states and taking the maximum.

## Common Mistakes
*   **Not handling the impossible case:** Forgetting to return -1 when no path exists to the end.
*   **Incorrect base case:** Not correctly identifying the end condition of the recursion.
*   **Inefficient iteration:** Not using memoization, leading to exponential time complexity.
*   **Off-by-one errors:** In loop bounds or index calculations.
*   **Integer overflow:** Though less likely in this specific problem with typical constraints.

## Complexity Analysis
*   Time: O(N^2) - Each state `memo[i]` is computed once. For each state, we iterate up to N times to find valid next jumps.
*   Space: O(N) - For the memoization array `memo` and the recursion call stack (which can go up to N in the worst case).

## Commented Code
```java
class Solution {
    int[] arr; // Stores the input array nums for easy access within the class.
    int[] memo; // Memoization table to store results of subproblems. memo[i] stores the max jumps from index i.
    int t; // Stores the target value for jump condition.

    public int maximumJumps(int[] nums, int target) {
        arr = nums; // Assign the input array to the class member.
        t = target; // Assign the target to the class member.
        memo = new int[nums.length]; // Initialize memoization array with size of nums.
        Arrays.fill(memo, -2); // Fill memo with -2, a sentinel value indicating that the state has not been computed yet.
        return func(0); // Start the recursive computation from the first index (index 0).
    }

    // Recursive function to calculate the maximum jumps from index i to the last index.
    public int func(int i) {
        int n = arr.length; // Get the length of the array.

        // Base case: If we have reached the last index, no more jumps are needed.
        if (i == n - 1) return 0;

        // Memoization check: If the result for index i has already been computed, return it.
        if (memo[i] != -2) return memo[i];

        int max = -1; // Initialize max jumps from current index to -1 (representing impossibility).

        // Iterate through all possible next indices j, starting from i+1.
        for (int j = i + 1; j < n; j++) {
            // Check if the jump from index i to index j is valid according to the condition.
            if (Math.abs(arr[i] - arr[j]) <= t) {
                // Recursively find the maximum jumps from the next index j.
                int temp = func(j);
                // If a valid path exists from j to the end (temp is not -1).
                if (temp != -1) {
                    // Update max jumps: 1 (for the current jump) + jumps from j.
                    max = Math.max(max, 1 + temp);
                }
            }
        }

        // Store the computed maximum jumps for index i in the memoization table.
        return memo[i] = max;
    }
}
```

## Interview Tips
*   Clearly explain the DP state and transition.
*   Walk through a small example manually to illustrate the logic.
*   Discuss the time and space complexity trade-offs.
*   Mention how memoization prevents redundant calculations and improves efficiency.
*   Be prepared to discuss alternative approaches if asked (e.g., iterative DP, though recursive with memoization is often more intuitive here).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the DP state: `dp[i]` = max jumps from index `i`.
- [ ] Define the base case: `dp[n-1] = 0`.
- [ ] Formulate the transition: `dp[i] = 1 + max(dp[j])` for all valid `j > i`.
- [ ] Implement with recursion and memoization.
- [ ] Handle the case where reaching the end is impossible.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Jump Game II
*   Minimum Number of Jumps to Reach the End
*   Longest Increasing Path in a Matrix (similar DP structure)

## Tags
`Array` `Dynamic Programming` `Recursion` `Memoization`
