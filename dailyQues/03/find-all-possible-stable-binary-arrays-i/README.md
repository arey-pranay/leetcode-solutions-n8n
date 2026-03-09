# Find All Possible Stable Binary Arrays I

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Dynamic Programming` `Prefix Sum`  
**Time:** O(zero * one * limit)  
**Space:** O(zero * one)

---

## Solution (java)

```java
class Solution {
    int M = (int)1e9 + 7;
    public int numberOfStableArrays(int zero, int one, int limit) {
        int x = 0 ; 
        int y = 0 ; 
        if(zero>0) x = stable(zero-1,one,limit,false);
        if(one>0) y = stable(zero,one-1,limit,true);
        return (x+y)%M;
    }
    
    public int stable(int zeroLeft , int oneLeft, int limit, boolean lastUsedOne){
        int result = 0;
        if(zeroLeft==0 && oneLeft==0) return 1;
        if(lastUsedOne){ 
            for(int i = 1 ; i<=Math.min(zeroLeft,limit);i++){
                result += stable(zeroLeft-i,oneLeft,limit,false);
                result %= M;
            }
        }
        else{
            for(int i = 1 ; i<=Math.min(oneLeft,limit);i++){
                result = (result+stable(zeroLeft,oneLeft-i,limit,true))%M;
            }
        }
        return result;
    }
}
```

---

---
## Quick Revision
This problem asks to count the number of binary arrays with a given number of zeros and ones, such that no more than `limit` consecutive identical bits appear.
The solution uses a recursive approach with memoization (implicitly through dynamic programming if optimized) to explore valid placements of zeros and ones.

## Intuition
The core idea is to build the stable binary array piece by piece. At each step, we decide whether to append a block of zeros or a block of ones. The constraint is that we cannot append more than `limit` consecutive bits of the same type. This suggests a recursive structure where the state depends on the remaining number of zeros and ones, and the type of the last bit appended.

## Algorithm
1.  **Base Case:** If both `zeroLeft` and `oneLeft` are 0, we have successfully formed a stable array, so return 1.
2.  **Recursive Step (Last Bit was One):** If the last bit appended was a '1' (`lastUsedOne` is true), we can append a block of `i` zeros, where `1 <= i <= min(zeroLeft, limit)`. For each such `i`, recursively call the `stable` function with `zeroLeft - i` zeros remaining, `oneLeft` ones remaining, and `lastUsedOne` set to `false` (since we just appended zeros). Sum up the results from these recursive calls.
3.  **Recursive Step (Last Bit was Zero):** If the last bit appended was a '0' (`lastUsedOne` is false), we can append a block of `i` ones, where `1 <= i <= min(oneLeft, limit)`. For each such `i`, recursively call the `stable` function with `zeroLeft` zeros remaining, `oneLeft - i` ones remaining, and `lastUsedOne` set to `true` (since we just appended ones). Sum up the results from these recursive calls.
4.  **Modulo Operation:** Apply the modulo `M` at each addition to prevent integer overflow.
5.  **Initial Call:** The `numberOfStableArrays` function initializes the process. It considers two starting scenarios:
    *   Starting with a block of zeros: If `zero > 0`, call `stable(zero - 1, one, limit, false)` (one zero is used in the first block, and the last bit used was zero).
    *   Starting with a block of ones: If `one > 0`, call `stable(zero, one - 1, limit, true)` (one one is used in the first block, and the last bit used was one).
    The total count is the sum of these two initial calls, modulo `M`.

## Concept to Remember
*   **Recursion and Backtracking:** The problem can be naturally modeled as exploring different choices (appending blocks of zeros or ones) and backtracking when a choice leads to an invalid state or a dead end.
*   **Dynamic Programming (Implicit):** Although the provided solution is purely recursive, it exhibits overlapping subproblems. A DP approach with memoization or tabulation would significantly optimize performance by storing and reusing results of subproblems.
*   **Combinatorics:** The problem involves counting combinations of arrangements under specific constraints.

## Common Mistakes
*   **Not Handling Base Cases Correctly:** Incorrectly defining the termination condition for the recursion can lead to infinite loops or incorrect counts.
*   **Missing Modulo Operations:** Forgetting to apply the modulo operator at each addition can result in integer overflow, leading to incorrect answers for larger inputs.
*   **Incorrect State Transitions:** Mismanaging the `zeroLeft`, `oneLeft`, and `lastUsedOne` parameters in recursive calls can lead to exploring invalid paths or missing valid ones.
*   **Inefficient Recursion (No Memoization):** Without memoization, the same subproblems are recalculated many times, leading to exponential time complexity.

## Complexity Analysis
*   **Time:** O(zero * one * limit) - The state of our recursion is defined by `(zeroLeft, oneLeft, lastUsedOne)`. `zeroLeft` can range from 0 to `zero`, `oneLeft` from 0 to `one`. The loop inside `stable` runs up to `limit` times. If memoization were explicitly added, each state `(zeroLeft, oneLeft, lastUsedOne)` would be computed only once. The `lastUsedOne` adds a factor of 2, but it's often absorbed into the overall complexity. A more precise analysis with memoization would be O(zero * one * limit). Without memoization, it's exponential.
*   **Space:** O(zero * one) - This is the space required for the recursion call stack. If memoization were explicitly added using a 3D DP table (or a map), the space complexity would be O(zero * one * 2) which simplifies to O(zero * one).

## Commented Code
```java
class Solution {
    // Define the modulo constant for calculations to prevent overflow.
    int M = (int)1e9 + 7;

    // Main function to calculate the number of stable arrays.
    public int numberOfStableArrays(int zero, int one, int limit) {
        // Initialize variables to store counts starting with zeros and ones.
        int countStartingWithZero = 0;
        int countStartingWithOne = 0;

        // If there are zeros available, calculate stable arrays starting with a block of zeros.
        // We use zero-1 because the first block of zeros will consume at least one zero.
        // The last bit used in this initial block is '0', so lastUsedOne is false.
        if (zero > 0) {
            countStartingWithZero = stable(zero - 1, one, limit, false);
        }

        // If there are ones available, calculate stable arrays starting with a block of ones.
        // We use one-1 because the first block of ones will consume at least one one.
        // The last bit used in this initial block is '1', so lastUsedOne is true.
        if (one > 0) {
            countStartingWithOne = stable(zero, one - 1, limit, true);
        }

        // The total number of stable arrays is the sum of those starting with zero and those starting with one.
        // Apply modulo M to ensure the result stays within integer limits.
        return (countStartingWithZero + countStartingWithOne) % M;
    }

    // Recursive helper function to count stable arrays.
    // zeroLeft: number of zeros remaining to be placed.
    // oneLeft: number of ones remaining to be placed.
    // limit: maximum number of consecutive identical bits allowed.
    // lastUsedOne: boolean indicating if the last block appended was of ones (true) or zeros (false).
    public int stable(int zeroLeft, int oneLeft, int limit, boolean lastUsedOne) {
        // Base case: If no zeros and no ones are left, we have successfully formed a stable array.
        if (zeroLeft == 0 && oneLeft == 0) {
            return 1; // Return 1 to indicate one valid arrangement found.
        }

        // Initialize the result for the current state.
        int result = 0;

        // If the last block appended was of ones, we can now append a block of zeros.
        if (lastUsedOne) {
            // Iterate through possible lengths 'i' for the next block of zeros.
            // 'i' must be at least 1 and at most 'limit' (consecutive constraint).
            // 'i' also cannot exceed the number of zeros remaining (zeroLeft).
            for (int i = 1; i <= Math.min(zeroLeft, limit); i++) {
                // Recursively call 'stable' for the remaining zeros and ones.
                // zeroLeft is reduced by 'i' as 'i' zeros are used.
                // oneLeft remains the same.
                // lastUsedOne is set to false because the current block being appended is zeros.
                result += stable(zeroLeft - i, oneLeft, limit, false);
                // Apply modulo M to the result at each addition to prevent overflow.
                result %= M;
            }
        }
        // If the last block appended was of zeros, we can now append a block of ones.
        else { // lastUsedOne is false
            // Iterate through possible lengths 'i' for the next block of ones.
            // 'i' must be at least 1 and at most 'limit' (consecutive constraint).
            // 'i' also cannot exceed the number of ones remaining (oneLeft).
            for (int i = 1; i <= Math.min(oneLeft, limit); i++) {
                // Recursively call 'stable' for the remaining zeros and ones.
                // zeroLeft remains the same.
                // oneLeft is reduced by 'i' as 'i' ones are used.
                // lastUsedOne is set to true because the current block being appended is ones.
                result = (result + stable(zeroLeft, oneLeft - i, limit, true)) % M;
                // Apply modulo M to the result at each addition to prevent overflow.
            }
        }
        // Return the total count of stable arrays for the current state.
        return result;
    }
}
```

## Interview Tips
*   **Explain the Recursive Structure:** Clearly articulate how the problem breaks down into smaller subproblems based on the remaining counts of zeros and ones, and the last bit used.
*   **Discuss Memoization/DP:** Even if the provided solution is recursive, mention that it has overlapping subproblems and can be optimized using memoization or dynamic programming to avoid redundant calculations. This shows an understanding of optimization techniques.
*   **Handle Edge Cases:** Be prepared to discuss what happens if `zero` or `one` is 0 initially, or if `limit` is 1.
*   **Clarify State:** Ensure you can explain what each parameter in the `stable` function represents and why it's necessary for the recursive calls.

## Revision Checklist
- [ ] Understand the definition of a "stable binary array".
- [ ] Identify the base cases for the recursion.
- [ ] Formulate the recursive relation for appending blocks of zeros and ones.
- [ ] Correctly manage the `lastUsedOne` flag.
- [ ] Implement the modulo operation at each addition.
- [ ] Consider the initial calls to the recursive function.
- [ ] Think about potential optimizations (memoization/DP).

## Similar Problems
*   [LeetCode 1411. Number of Ways to Paint N × 3 Grid](https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/) (Similar DP state transitions)
*   [LeetCode 1235. Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) (Can be solved with DP and binary search, related to optimization)
*   [LeetCode 790. Domino and Tromino Tiling](https://leetcode.com/problems/domino-and-tromino-tiling/) (DP with state transitions)

## Tags
`Recursion` `Dynamic Programming` `Backtracking`

## My Notes
dailyQuestions/03
