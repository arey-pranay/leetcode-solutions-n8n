# Jump Game V

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Sorting`  
**Time:** O(N*D)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    int[] dp;

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        dp = new int[n];

        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, arr, d));
        }

        return ans;
    }

    private int dfs(int i, int[] arr, int d) {
        if (dp[i] != 0)
            return dp[i];

        int best = 1;

        for (int nxt = i + 1; nxt <= Math.min(arr.length - 1, i + d); nxt++) {
            if (arr[nxt] >= arr[i])
                break;

            best = Math.max(best, 1 + dfs(nxt, arr, d));
        }

        for (int nxt = i - 1; nxt >= Math.max(0, i - d); nxt--) {
            if (arr[nxt] >= arr[i])
                break;

            best = Math.max(best, 1 + dfs(nxt, arr, d));
        }

        return dp[i] = best;
    }
}
```

---

---
## Quick Revision
This problem asks for the maximum number of houses you can visit starting from any house, given jump constraints based on height.
We solve this using Depth First Search (DFS) with memoization (dynamic programming) to avoid redundant calculations.

## Intuition
The core idea is that from any given house `i`, we can jump to other houses within a certain range `d`. The crucial constraint is that we can only jump to a house `j` if `arr[j] < arr[i]`. This "decreasing height" rule is key. If we think about the maximum jumps from house `i`, it's 1 (for the current house) plus the maximum jumps we can make from any valid next house. This recursive structure strongly suggests a DFS approach. To optimize, we notice that the maximum jumps from a particular house will always be the same, regardless of how we arrived there. This leads to memoization, where we store the result for each house once computed.

## Algorithm
1. Initialize a `dp` array of the same size as `arr` to store the maximum jumps possible from each index. Initialize all entries to 0 (or -1, to distinguish from a valid result of 1).
2. Iterate through each house `i` from 0 to `n-1` (where `n` is the length of `arr`).
3. For each house `i`, call a recursive `dfs` function to calculate the maximum jumps starting from `i`.
4. Keep track of the overall maximum jumps found across all starting houses.
5. The `dfs(i, arr, d)` function should:
    a. If `dp[i]` is already computed (not 0), return `dp[i]`.
    b. Initialize `best` to 1 (representing the current house).
    c. Explore jumps to the right: Iterate from `i + 1` up to `min(n - 1, i + d)`.
        i. If `arr[nxt] >= arr[i]`, break the loop (cannot jump to a taller or equal height house).
        ii. Otherwise, recursively call `dfs(nxt, arr, d)` and update `best = max(best, 1 + dfs(nxt, arr, d))`.
    d. Explore jumps to the left: Iterate from `i - 1` down to `max(0, i - d)`.
        i. If `arr[nxt] >= arr[i]`, break the loop.
        ii. Otherwise, recursively call `dfs(nxt, arr, d)` and update `best = max(best, 1 + dfs(nxt, arr, d))`.
    e. Store the computed `best` value in `dp[i]` and return it.
6. Return the overall maximum jumps found.

## Concept to Remember
*   **Dynamic Programming (Memoization):** Storing results of subproblems (max jumps from a house) to avoid recomputation.
*   **Depth First Search (DFS):** Exploring all possible paths from a starting point recursively.
*   **Greedy Choice Property (Implicit):** At each step, we consider all valid next jumps and pick the one that leads to the maximum overall jumps. The "decreasing height" constraint is crucial for this.

## Common Mistakes
*   **Not handling the `dp` array correctly:** Forgetting to initialize it or not checking if a value has already been computed.
*   **Incorrectly implementing the jump conditions:** Not breaking the loop when `arr[nxt] >= arr[i]`, leading to invalid jumps.
*   **Off-by-one errors in loop bounds:** Incorrectly calculating `min(n - 1, i + d)` or `max(0, i - d)`.
*   **Not considering both left and right jumps:** Only implementing jumps in one direction.
*   **Forgetting to add 1 for the current house:** The result from `dfs` should always be at least 1.

## Complexity Analysis
- Time: O(N*D) - Each state (house `i`) is computed once. For each state, we iterate up to `D` steps in both directions. In the worst case, `D` can be `N`.
- Space: O(N) - For the `dp` array and the recursion stack depth, which can be up to `N` in the worst case.

## Commented Code
```java
class Solution {
    // dp array to store the maximum number of jumps possible starting from index i.
    // Initialized to 0, meaning not computed yet.
    int[] dp;

    // Main function to find the maximum jumps possible starting from any house.
    public int maxJumps(int[] arr, int d) {
        // Get the total number of houses.
        int n = arr.length;
        // Initialize the dp array with size n.
        dp = new int[n];

        // Initialize the overall maximum jumps found so far to 1 (minimum possible).
        int ans = 1;

        // Iterate through each house as a potential starting point.
        for (int i = 0; i < n; i++) {
            // Calculate the maximum jumps starting from house i using DFS.
            // Update the overall maximum jumps if the current result is greater.
            ans = Math.max(ans, dfs(i, arr, d));
        }

        // Return the overall maximum jumps.
        return ans;
    }

    // Recursive DFS function to calculate the maximum jumps starting from index i.
    private int dfs(int i, int[] arr, int d) {
        // If the result for index i has already been computed, return it.
        if (dp[i] != 0)
            return dp[i];

        // Initialize the maximum jumps from the current house to 1 (for the current house itself).
        int best = 1;

        // Explore jumps to the right.
        // Iterate from the next house (i + 1) up to a maximum of d steps away,
        // ensuring we don't go out of bounds (arr.length - 1).
        for (int nxt = i + 1; nxt <= Math.min(arr.length - 1, i + d); nxt++) {
            // If the next house's height is greater than or equal to the current house's height,
            // we cannot jump further in this direction according to the problem rules.
            if (arr[nxt] >= arr[i])
                break; // Stop exploring rightward jumps from this point.

            // If a valid jump is possible, recursively find the max jumps from the next house.
            // Add 1 (for the current jump) to the result of the recursive call.
            // Update 'best' if this path yields more jumps.
            best = Math.max(best, 1 + dfs(nxt, arr, d));
        }

        // Explore jumps to the left.
        // Iterate from the previous house (i - 1) down to a minimum of 0,
        // ensuring we don't go out of bounds.
        for (int nxt = i - 1; nxt >= Math.max(0, i - d); nxt--) {
            // If the next house's height is greater than or equal to the current house's height,
            // we cannot jump further in this direction.
            if (arr[nxt] >= arr[i])
                break; // Stop exploring leftward jumps from this point.

            // If a valid jump is possible, recursively find the max jumps from the next house.
            // Add 1 (for the current jump) to the result of the recursive call.
            // Update 'best' if this path yields more jumps.
            best = Math.max(best, 1 + dfs(nxt, arr, d));
        }

        // Store the computed maximum jumps for index i in the dp array before returning.
        // This is the memoization step.
        return dp[i] = best;
    }
}
```

## Interview Tips
*   Clearly explain the recursive structure and why DFS is a natural fit.
*   Emphasize the importance of the `arr[j] < arr[i]` condition and how it prunes the search space.
*   Walk through the memoization (DP) aspect: why it's needed and how it prevents exponential time complexity.
*   Be prepared to discuss the time and space complexity trade-offs.
*   If asked to optimize further, consider if any other data structures could help (though for this specific constraint, O(N*D) is often acceptable).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the recursive nature of the problem.
- [ ] Implement DFS with correct base cases and recursive calls.
- [ ] Implement memoization (DP) to store and reuse computed results.
- [ ] Correctly handle jump boundaries (left and right, `d` distance).
- [ ] Correctly implement the height constraint (`arr[nxt] < arr[i]`).
- [ ] Test with edge cases (e.g., `d=1`, all heights same, `d` larger than `n`).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Jump Game (LeetCode 55)
*   Jump Game II (LeetCode 45)
*   Longest Increasing Path in a Matrix (LeetCode 329)
*   Pacific Atlantic Water Flow (LeetCode 329) - Similar DFS/BFS with boundary conditions.

## Tags
`Array` `Dynamic Programming` `Depth-First Search` `Memoization`
