# Predict The Winner

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Dynamic Programming` `Recursion` `Game Theory`  
**Time:** O(n^2)  
**Space:** O(n^2)

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    int[] A;
    public boolean predictTheWinner(int[] arr) {
        int n = arr.length;
        if(n%2==0) return true;// we can always win in even length, because we will always get the cahnce to pick out of the available pair
        memo = new int[n][n];
        A = arr;
        for (int[] temp: memo) Arrays.fill(temp, -1);
        return maxDiff(0, n - 1) >= 0;
    }

    private int maxDiff(int i, int j) {//considering you maximize your difference from their choice at every point
        if (memo[i][j] != -1) return memo[i][j];        
        if (i == j) return memo[i][j] = A[i];
        return memo[i][j] = Math.max(A[i] - maxDiff(i + 1, j), A[j] - maxDiff(i, j - 1));
    }
}
//assuming both cases happen, then we make 2 funtion calls
```

---

---
## Quick Revision
This problem asks if the first player can win a game where players take turns picking numbers from either end of an array.
We solve this using dynamic programming with memoization to find the maximum score difference the first player can achieve.

## Intuition
The core idea is that each player wants to maximize their score *relative to the other player*. If player 1 picks a number, player 2 will then play optimally to maximize *their* score difference. This suggests a recursive structure where we consider the best outcome for the current player given the opponent's optimal play. The "aha moment" is realizing that we don't need to track absolute scores, but rather the *difference* in scores. If the first player can ensure a non-negative score difference at the end, they win.

## Algorithm
1.  **Base Case:** If the subarray has only one element (`i == j`), the current player takes that element, and the score difference is simply the value of that element.
2.  **Recursive Step:** For a subarray from index `i` to `j`:
    *   The current player can choose `A[i]`. If they do, the remaining subarray is `A[i+1...j]`. The opponent will then play optimally on this subarray, aiming to maximize *their* score difference. From the current player's perspective, this means the score difference they achieve is `A[i]` minus the maximum difference the opponent can achieve on `A[i+1...j]`.
    *   Alternatively, the current player can choose `A[j]`. If they do, the remaining subarray is `A[i...j-1]`. The opponent will play optimally on this subarray. The score difference for the current player is `A[j]` minus the maximum difference the opponent can achieve on `A[i...j-1]`.
3.  **Maximization:** The current player will choose the move that maximizes their score difference. So, the result for `maxDiff(i, j)` is `max(A[i] - maxDiff(i+1, j), A[j] - maxDiff(i, j-1))`.
4.  **Memoization:** To avoid redundant calculations, store the result of `maxDiff(i, j)` in a 2D array `memo`. Before computing, check if `memo[i][j]` has already been calculated.
5.  **Initial Call:** Call `maxDiff(0, n-1)` to find the maximum score difference the first player can achieve for the entire array.
6.  **Winning Condition:** If the result of `maxDiff(0, n-1)` is greater than or equal to 0, the first player can win.

## Concept to Remember
*   **Minimax Algorithm:** This problem is a simplified version of the minimax algorithm, where players aim to maximize their own score while assuming the opponent will also play optimally to maximize theirs.
*   **Dynamic Programming (Top-Down with Memoization):** Breaking down the problem into overlapping subproblems and storing their solutions to avoid recomputation.
*   **Game Theory:** Understanding optimal strategies in competitive scenarios.

## Common Mistakes
*   **Not considering the opponent's optimal play:** Simply trying to maximize one's own score without accounting for how the opponent will respond.
*   **Incorrect base case:** Not handling the single-element subarray correctly.
*   **Off-by-one errors in recursion:** Incorrectly defining the start and end indices for subproblems.
*   **Forgetting memoization:** Leading to exponential time complexity due to repeated calculations.
*   **Handling even/odd length arrays incorrectly:** The provided solution has a shortcut for even length arrays, which is not universally true and can be misleading. The DP approach works for both.

## Complexity Analysis
- Time: O(n^2) - The `maxDiff` function is called for each unique pair of `(i, j)`, where `0 <= i <= j < n`. There are O(n^2) such pairs, and each call takes O(1) time due to memoization.
- Space: O(n^2) - For the `memo` table, which stores results for all possible subproblems `(i, j)`. The recursion depth can also go up to O(n), contributing to the call stack space.

## Commented Code
```java
class Solution {
    // memo[i][j] will store the maximum score difference player 1 can achieve
    // when playing on the subarray A[i...j].
    int[][] memo;
    // A is a reference to the input array for easier access.
    int[] A;

    public boolean predictTheWinner(int[] arr) {
        // Get the length of the input array.
        int n = arr.length;
        // The provided shortcut for even length arrays is incorrect.
        // The DP approach handles all cases correctly.
        // if(n%2==0) return true; // This line is problematic and removed for correctness.

        // Initialize the memoization table with size n x n.
        memo = new int[n][n];
        // Store the input array in a class member for easy access in helper methods.
        A = arr;
        // Initialize all entries in the memo table to -1, indicating they haven't been computed yet.
        for (int[] temp: memo) Arrays.fill(temp, -1);

        // Call the recursive helper function to calculate the maximum score difference
        // the first player can achieve starting with the entire array (from index 0 to n-1).
        // If this difference is >= 0, the first player can win.
        return maxDiff(0, n - 1) >= 0;
    }

    // maxDiff(i, j) calculates the maximum score difference the current player
    // can achieve when playing on the subarray A[i...j], assuming optimal play from both sides.
    private int maxDiff(int i, int j) {
        // If the result for this subproblem (i, j) is already computed, return it from memo.
        if (memo[i][j] != -1) return memo[i][j];

        // Base case: If only one element remains in the subarray (i == j),
        // the current player takes this element, and the score difference is its value.
        if (i == j) return memo[i][j] = A[i];

        // Recursive step: The current player has two choices:
        // 1. Pick A[i]: The score gained is A[i]. The opponent will then play on A[i+1...j].
        //    The opponent will try to maximize their score difference, which means
        //    they will achieve maxDiff(i+1, j) from their perspective.
        //    So, from the current player's perspective, the net difference is A[i] - maxDiff(i+1, j).
        int pickLeft = A[i] - maxDiff(i + 1, j);

        // 2. Pick A[j]: The score gained is A[j]. The opponent will then play on A[i...j-1].
        //    Similarly, the net difference from the current player's perspective is A[j] - maxDiff(i, j-1).
        int pickRight = A[j] - maxDiff(i, j - 1);

        // The current player will choose the move that maximizes their score difference.
        // Store this maximum difference in the memo table before returning.
        return memo[i][j] = Math.max(pickLeft, pickRight);
    }
}
```

## Interview Tips
*   **Explain the Score Difference:** Clearly articulate why tracking the score *difference* is more efficient than tracking absolute scores for each player.
*   **Walk Through an Example:** Use a small array (e.g., `[1, 5, 2]`) to trace the recursive calls and memoization process. Show how `maxDiff` is calculated for subproblems.
*   **Discuss the Base Case:** Emphasize the importance of the `i == j` base case and how it correctly terminates the recursion.
*   **Address the Even Length Shortcut:** If you notice the provided solution's shortcut for even length arrays, point out that it's not always correct and that the DP approach is robust for all cases.

## Revision Checklist
- [ ] Understand the game rules: players pick from ends, alternating turns.
- [ ] Recognize the need for optimal play from both sides.
- [ ] Formulate the score difference recurrence relation.
- [ ] Implement the base case for a single element.
- [ ] Apply memoization to optimize overlapping subproblems.
- [ ] Verify the initial call and winning condition.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Stone Game
*   Can I Win
*   Nim Game

## Tags
`Array` `Dynamic Programming` `Recursion` `Game Theory`
