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
This problem asks if Player 1 can win a game where players take turns picking numbers from the ends of an array to maximize their score.
The solution uses dynamic programming with memoization to calculate the maximum score difference Player 1 can achieve.

## Intuition
The core idea is that each player wants to maximize their own score, which is equivalent to maximizing the difference between their score and the opponent's score. This is a minimax-like problem. If it's Player 1's turn, they want to pick a number that leads to the maximum possible score difference for them. If it's Player 2's turn, they will pick a number that minimizes Player 1's score difference (or maximizes their own score difference). This recursive structure, where the current player's optimal move depends on the optimal moves of the next player, strongly suggests dynamic programming or recursion with memoization. The base case is when only one number remains, which the current player takes.

## Algorithm
1.  **Handle Even Length Array:** If the input array `arr` has an even length, Player 1 can always win. This is because Player 1 will always have the last pick from any remaining pair of numbers, ensuring they can always match or exceed Player 2's score. Return `true`.
2.  **Initialize Memoization Table:** Create a 2D array `memo` of size `n x n` (where `n` is the length of `arr`) to store the results of subproblems. Initialize all entries to -1, indicating they haven't been computed yet.
3.  **Store Input Array:** Store the input array `arr` in a class-level variable `A` for easy access within the recursive function.
4.  **Define Recursive Function `maxDiff(i, j)`:** This function calculates the maximum score difference Player 1 can achieve when considering the subarray from index `i` to `j`.
    *   **Memoization Check:** If `memo[i][j]` is not -1, return the stored value.
    *   **Base Case:** If `i == j` (only one element left), the current player takes that element. The difference is `A[i]`. Store and return this value.
    *   **Recursive Step:** If it's the current player's turn (Player 1 if `n - (j - i + 1)` is even, Player 2 if odd, but the `maxDiff` function implicitly handles this by calculating the *difference* from the perspective of the *first* player in the subproblem), they have two choices:
        *   Pick `A[i]`: The score difference will be `A[i]` minus the maximum difference the *next* player can achieve from the remaining subarray `A[i+1...j]`. This is `A[i] - maxDiff(i + 1, j)`.
        *   Pick `A[j]`: The score difference will be `A[j]` minus the maximum difference the *next* player can achieve from the remaining subarray `A[i...j-1]`. This is `A[j] - maxDiff(i, j - 1)`.
    *   The current player will choose the move that maximizes this difference. So, `memo[i][j] = Math.max(A[i] - maxDiff(i + 1, j), A[j] - maxDiff(i, j - 1))`. Store and return this value.
5.  **Initiate the Game:** Call `maxDiff(0, n - 1)` to find the maximum score difference Player 1 can achieve starting with the entire array.
6.  **Determine Winner:** If the result of `maxDiff(0, n - 1)` is greater than or equal to 0, it means Player 1 can achieve a score greater than or equal to Player 2's score, so Player 1 wins. Return `true`. Otherwise, return `false`.

## Concept to Remember
*   **Dynamic Programming (Top-Down with Memoization):** Breaking down a problem into overlapping subproblems and storing their solutions to avoid redundant computations.
*   **Minimax Principle (Implicit):** Although not explicitly coded as minimax, the recursive structure where each player tries to optimize their outcome based on the opponent's optimal play is a core concept.
*   **Game Theory:** Understanding optimal strategies in competitive scenarios.

## Common Mistakes
*   **Incorrect Base Case:** Not handling the `i == j` case correctly, or not returning the value from the base case.
*   **Forgetting Memoization:** Implementing the recursive solution without storing results, leading to exponential time complexity.
*   **Incorrectly Calculating Score Difference:** Subtracting the opponent's score from the current player's score in the wrong order or without considering the recursive outcome.
*   **Off-by-One Errors:** Incorrectly defining the subarray ranges `(i+1, j)` or `(i, j-1)`.
*   **Not Handling Even Length Array:** Missing the optimization for even length arrays, which can be solved in O(1).

## Complexity Analysis
*   **Time:** O(n^2) - The `maxDiff` function is called for each unique pair of `(i, j)`, where `0 <= i <= j < n`. There are O(n^2) such pairs. Each call performs constant time operations (comparisons, subtractions, `Math.max`) after the recursive calls return. Memoization ensures each subproblem is solved only once.
*   **Space:** O(n^2) - For the `memo` table, which stores results for all `n x n` possible subproblems. The recursion depth can also go up to O(n) in the worst case, contributing to the call stack space, but the memoization table dominates.

## Commented Code
```java
class Solution {
    // memo[i][j] will store the maximum score difference Player 1 can achieve
    // when considering the subarray from index i to j.
    int[][] memo;
    // A is a copy of the input array arr for easier access.
    int[] A;

    public boolean predictTheWinner(int[] arr) {
        // Get the length of the input array.
        int n = arr.length;
        // Optimization: If the array length is even, Player 1 can always win.
        // Player 1 will always have the last pick from any pair, ensuring they can match or exceed Player 2's score.
        if(n % 2 == 0) return true;

        // Initialize the memoization table with dimensions n x n.
        memo = new int[n][n];
        // Assign the input array to the class member A.
        A = arr;
        // Fill the memoization table with -1 to indicate that no subproblems have been solved yet.
        for (int[] temp: memo) Arrays.fill(temp, -1);

        // Call the recursive helper function to calculate the maximum score difference Player 1 can achieve
        // starting with the entire array (from index 0 to n-1).
        // If this difference is >= 0, Player 1 wins.
        return maxDiff(0, n - 1) >= 0;
    }

    // This recursive function calculates the maximum score difference the current player
    // can achieve from the subarray A[i...j].
    // The difference is calculated from the perspective of the player whose turn it is at the start of this subproblem.
    private int maxDiff(int i, int j) {
        // If the result for this subproblem (i, j) is already computed and stored in memo, return it.
        if (memo[i][j] != -1) return memo[i][j];

        // Base case: If only one element remains in the subarray (i == j),
        // the current player takes this element. The score difference is just the value of this element.
        if (i == j) return memo[i][j] = A[i];

        // Recursive step: The current player has two choices:
        // 1. Pick the element A[i]: The score difference will be A[i] minus the maximum difference the *next* player can achieve from the remaining subarray A[i+1...j].
        //    The next player will play optimally to maximize *their* score difference, which means minimizing Player 1's difference.
        //    So, we subtract maxDiff(i+1, j) from A[i].
        int pickLeft = A[i] - maxDiff(i + 1, j);

        // 2. Pick the element A[j]: The score difference will be A[j] minus the maximum difference the *next* player can achieve from the remaining subarray A[i...j-1].
        //    Similarly, we subtract maxDiff(i, j-1) from A[j].
        int pickRight = A[j] - maxDiff(i, j - 1);

        // The current player will choose the move that maximizes their score difference.
        // Store this maximum difference in memo[i][j] before returning.
        return memo[i][j] = Math.max(pickLeft, pickRight);
    }
}
```

## Interview Tips
*   **Explain the Game Logic:** Clearly articulate how each player's turn affects the overall score difference and why maximizing this difference is the goal.
*   **Discuss DP State:** Explain what `memo[i][j]` represents and why it's crucial for avoiding recomputation.
*   **Trace a Small Example:** Walk through a small array (e.g., `[1, 5, 2]`) to demonstrate how the `maxDiff` function works and how the memoization table is filled.
*   **Mention the Even Length Optimization:** Highlight this as a clever observation that simplifies the problem for a specific case.

## Revision Checklist
- [ ] Understand the game rules and objective (maximize score difference).
- [ ] Recognize the recursive/DP structure.
- [ ] Implement the `maxDiff(i, j)` function correctly.
- [ ] Handle the base case `i == j`.
- [ ] Implement memoization to store and retrieve subproblem results.
- [ ] Understand the score difference calculation: `current_pick - opponent_max_diff`.
- [ ] Implement the even length array optimization.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Stone Game
*   Can I Win
*   Nim Game
*   Game of Life (different concept, but involves state transitions)

## Tags
`Array` `Dynamic Programming` `Recursion` `Memoization` `Game Theory`
