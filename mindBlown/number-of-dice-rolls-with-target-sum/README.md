# Number Of Dice Rolls With Target Sum

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Dynamic Programming`  
**Time:** O(n * target * k)  
**Space:** O(n * target)

---

## Solution (java)

```java
class Solution {
    Integer[][] memo;
    int MOD = (int)(1e9 + 7);
    public int numRollsToTarget(int n, int k, int target) {
        memo = new Integer[n+1][target+1];
       return func(n,k,target) % MOD;
    }
    public int func(int n, int k, int target){
        if(target < 0) return 0;
        if(n==1) return k >= target & target >= 1 ? 1 : 0;
        int ans = 0;
        if(memo[n][target] != null) return memo[n][target]%MOD;
        for(int i=1;i<=k;i++) ans = (ans + func(n-1,k,target-i))%MOD;
        return memo[n][target] = ans % MOD;
    }
}
```

---

---
## Quick Revision
Given `n` dice, each with `k` faces (numbered 1 to `k`), find the number of ways to roll the dice such that the sum of the face-up numbers equals `target`.
This problem can be solved using dynamic programming with memoization, where `dp(dice_left, current_target)` represents the number of ways to achieve `current_target` using `dice_left` dice.

## Intuition
The core idea is to break down the problem into smaller, overlapping subproblems. If we have `n` dice and need to reach a `target` sum, we can consider the outcome of the *first* die. If the first die rolls a `1`, we then need to find the number of ways to reach `target - 1` with `n - 1` dice. If it rolls a `2`, we need to reach `target - 2` with `n - 1` dice, and so on, up to rolling a `k`. The total number of ways is the sum of ways for each possible outcome of the first die. This recursive structure with overlapping subproblems strongly suggests dynamic programming. Memoization is crucial to avoid redundant calculations.

## Algorithm
1.  **Initialization**:
    *   Create a 2D array `memo` of size `(n+1) x (target+1)` to store results of subproblems. Initialize all entries to `null` (or a sentinel value like -1) to indicate they haven't been computed yet.
    *   Define a constant `MOD` for the modulo operation (`10^9 + 7`).
2.  **Recursive Function `func(dice_left, current_target)`**:
    *   **Base Case 1 (Invalid Target)**: If `current_target < 0`, it's impossible to reach this target, so return `0`.
    *   **Base Case 2 (Last Die)**: If `dice_left == 1`:
        *   If `current_target` is between `1` and `k` (inclusive), it means we can achieve this target with the last die by rolling the exact value `current_target`. Return `1`.
        *   Otherwise, return `0`.
    *   **Memoization Check**: If `memo[dice_left][current_target]` is not `null`, return the stored value (modulo `MOD`).
    *   **Recursive Step**:
        *   Initialize `ans = 0`.
        *   Iterate through all possible outcomes `i` for the current die (from `1` to `k`).
        *   For each `i`, recursively call `func(dice_left - 1, current_target - i)` to find the number of ways to achieve the remaining target with one less die.
        *   Add the result of the recursive call to `ans`, taking the modulo `MOD` at each step to prevent overflow: `ans = (ans + func(dice_left - 1, current_target - i)) % MOD`.
    *   **Store and Return**: Store the computed `ans` (modulo `MOD`) in `memo[dice_left][current_target]` and return it.
3.  **Main Function `numRollsToTarget(n, k, target)`**:
    *   Call `func(n, k, target)` to start the computation.
    *   Return the result modulo `MOD`.

## Concept to Remember
*   **Dynamic Programming (DP)**: Breaking down a problem into overlapping subproblems and solving each subproblem only once.
*   **Memoization (Top-Down DP)**: Storing the results of expensive function calls and returning the cached result when the same inputs occur again.
*   **Recursion**: Defining a function that calls itself to solve smaller instances of the same problem.
*   **Modulo Arithmetic**: Handling large numbers by taking the remainder when divided by a specific number to prevent integer overflow.

## Common Mistakes
*   **Forgetting Modulo Operation**: Not applying the modulo `MOD` at each addition step can lead to integer overflow, especially with large `n` and `k`.
*   **Incorrect Base Cases**: Errors in defining the base cases (e.g., for `target < 0` or `n == 1`) can lead to incorrect results.
*   **Off-by-One Errors**: Incorrect loop bounds or array indexing (e.g., using `n` instead of `n-1` for dice count, or `target` instead of `target-i`).
*   **Not Handling `target < 0`**: If `target - i` becomes negative, it should contribute 0 ways, not cause an error or incorrect calculation.
*   **Inefficient State Definition**: If the DP state doesn't capture all necessary information or is too broad, it can lead to exponential time complexity.

## Complexity Analysis
*   **Time**: O(n * target * k) - The `func` is called at most `n * target` times (due to memoization). Inside each call, there's a loop that runs `k` times.
*   **Space**: O(n * target) - For the `memo` table. The recursion depth can also go up to `n`, contributing O(n) to the call stack space, but this is dominated by the memo table.

## Commented Code
```java
class Solution {
    // memo[i][j] will store the number of ways to get sum 'j' using 'i' dice.
    // Using Integer wrapper class allows us to distinguish between 0 ways and uncomputed states (null).
    Integer[][] memo;
    // MOD is used to prevent integer overflow, as the number of ways can be very large.
    int MOD = (int)(1e9 + 7);

    public int numRollsToTarget(int n, int k, int target) {
        // Initialize the memoization table with dimensions (n+1) x (target+1).
        // We use n+1 and target+1 because we'll be using 1-based indexing conceptually for dice count and target sum.
        memo = new Integer[n+1][target+1];
        // Start the recursive calculation. The result is taken modulo MOD to ensure it fits within integer limits.
       return func(n, k, target) % MOD;
    }

    // Recursive helper function to calculate the number of ways.
    // n: number of dice remaining.
    // k: number of faces on each die.
    // target: the remaining sum we need to achieve.
    public int func(int n, int k, int target){
        // Base Case 1: If the target becomes negative, it's impossible to reach.
        if(target < 0) return 0;
        // Base Case 2: If only one die is left.
        if(n==1) {
            // If the target is achievable with a single die (i.e., between 1 and k inclusive), return 1 way.
            // Otherwise, it's impossible, so return 0 ways.
            return k >= target && target >= 1 ? 1 : 0;
        }
        // Initialize the answer for the current state.
        int ans = 0;
        // Memoization Check: If the result for this state (n dice, target sum) has already been computed, return it.
        // We take modulo MOD here as well, in case the stored value was already computed and needs to be returned.
        if(memo[n][target] != null) return memo[n][target]%MOD;

        // Recursive Step: Iterate through all possible outcomes (1 to k) for the current die.
        for(int i=1;i<=k;i++) {
            // For each outcome 'i', recursively find the number of ways to achieve the remaining target (target - i)
            // with one less die (n - 1).
            // Add this to our current answer 'ans', ensuring we take modulo MOD at each addition to prevent overflow.
            ans = (ans + func(n-1,k,target-i))%MOD;
        }
        // Store the computed answer for the current state (n dice, target sum) in the memoization table.
        // Ensure the stored value is also modulo MOD.
        return memo[n][target] = ans % MOD;
    }
}
```

## Interview Tips
*   **Explain the DP State Clearly**: When asked about your approach, clearly define what `dp[i][j]` (or `memo[i][j]` in this top-down approach) represents. For example, "This `memo[dice_left][current_target]` stores the number of ways to achieve `current_target` using `dice_left` dice."
*   **Walk Through a Small Example**: Pick a small `n`, `k`, and `target` (e.g., `n=2, k=6, target=7`) and trace your recursive calls and memoization updates. This demonstrates your understanding of the logic.
*   **Discuss Modulo Arithmetic Early**: Mention the need for modulo arithmetic to handle large results as soon as you propose a DP solution. This shows foresight.
*   **Ask About Constraints**: If not explicitly given, ask about the potential ranges of `n`, `k`, and `target`. This helps in determining if a brute-force approach is feasible or if DP is necessary, and also informs complexity considerations.

## Revision Checklist
- [ ] Understand the problem: counting combinations of dice rolls for a target sum.
- [ ] Identify overlapping subproblems and optimal substructure.
- [ ] Formulate a recursive relation: `ways(n, target) = sum(ways(n-1, target - roll))` for `roll` from 1 to `k`.
- [ ] Implement memoization to store results of `ways(n, target)`.
- [ ] Define correct base cases: `target < 0` and `n == 1`.
- [ ] Handle modulo arithmetic (`% MOD`) at each addition to prevent overflow.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases: `target` too small or too large for `n` dice.

## Similar Problems
*   Coin Change 2 (LeetCode 518)
*   Combination Sum IV (LeetCode 377)
*   Target Sum (LeetCode 494)
*   Minimum Number of Dice Rolls With Target Sum (LeetCode 1354) - This is a variation where you minimize rolls, not count ways.

## Tags
`Dynamic Programming` `Recursion` `Memoization`

## My Notes
one of the best recursion based questions ever ig.
