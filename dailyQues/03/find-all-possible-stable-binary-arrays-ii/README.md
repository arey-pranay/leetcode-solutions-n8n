# Find All Possible Stable Binary Arrays Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Prefix Sum`  
**Time:** O(zero * one * 2)  
**Space:** O(zero * one * 2)

---

## Solution (java)

```java
class Solution {
    int M =(int)1e9 + 7;
    int[][][] memo;
    public int numberOfStableArrays(int zero, int one, int limit) {
        memo = new int[zero+1][one+1][2];
        for(int[][] d2 : memo) for(int[] d1 : d2) Arrays.fill(d1,-1);
        
        int x = stable(zero,one,limit,0);
        int y = stable(zero,one,limit,1);
        
        return (x+y)%M;
    }
    public int stable(int zeroLeft , int oneLeft, int consecutive, int lastUsed ){
       
        if(zeroLeft < 0 || oneLeft < 0) return 0;
        if(zeroLeft==0 && oneLeft==0) return 1;
        
        if(memo[zeroLeft][oneLeft][lastUsed] != -1) return memo[zeroLeft][oneLeft][lastUsed];
        
        int result = 0;
        if(lastUsed == 1){ 
            result = stable(zeroLeft,oneLeft-1,consecutive,1);
            if(zeroLeft >0) result += stable(zeroLeft,oneLeft-1,consecutive,0);
            if(oneLeft>0) result -= stable(zeroLeft,oneLeft-1-consecutive,consecutive,0);
        }
        else{
            result = stable(zeroLeft-1,oneLeft,consecutive,0);
            if(oneLeft>0)  result += stable(zeroLeft-1,oneLeft,consecutive,1);
            if(zeroLeft >0) result -= stable(zeroLeft-1-consecutive,oneLeft,consecutive,1);
                
        }
        // System.out.print(result);
        // System.out.print(" "+result%M);
        result = (result % M + M) % M;
        // System.out.print(" " +result);
        // System.out.println();
        
        memo[zeroLeft][oneLeft][lastUsed] = result;
        return result;
    }
}
// z,o,u
// 10,10,0

// [9,10,1],[8,10,1],[7,10,1]..
// [9,9,0],[9,8,0]

// 10,8,0
// 10,8,1
// 9,8,0

// z,o,u
// 10,10,1
// [10,9,0],[10,8,0]/////
```

---

---
## Quick Revision
This problem asks for the number of binary arrays with a given number of zeros and ones, where no more than `limit` consecutive identical bits are allowed. We solve this using dynamic programming with memoization.

## Intuition
The core idea is to build stable arrays by considering the last bit added. If the last bit was '0', we can add another '0' (up to `limit` times) or switch to '1'. If the last bit was '1', we can add another '1' (up to `limit` times) or switch to '0'. This suggests a recursive structure where the state depends on the remaining counts of zeros and ones, and the last bit used. The "aha moment" comes from realizing that we can use DP to avoid recomputing the same subproblems. The subtraction in the DP recurrence handles the constraint of not exceeding `limit` consecutive bits.

## Algorithm
1.  **Define DP State**: `dp(zeros_left, ones_left, last_bit)` will store the number of stable arrays that can be formed using `zeros_left` zeros and `ones_left` ones, given that the `last_bit` added was `last_bit` (0 or 1).
2.  **Base Cases**:
    *   If `zeros_left < 0` or `ones_left < 0`, return 0 (invalid state).
    *   If `zeros_left == 0` and `ones_left == 0`, return 1 (a valid stable array has been formed).
3.  **Memoization**: Use a 3D array `memo[zero][one][last_bit]` to store computed results. If `memo[zeros_left][ones_left][last_bit]` is not -1, return the stored value.
4.  **Transitions**:
    *   **If `last_bit == 0` (last added was '0')**:
        *   We can add another '0': `dp(zeros_left - 1, ones_left, 0)`.
        *   We can switch to '1': `dp(zeros_left - 1, ones_left, 1)`.
        *   To enforce the `limit` constraint for consecutive zeros, we need to subtract the cases where we would have added `limit + 1` zeros. This is achieved by considering the state where we have `zeros_left - 1 - limit` zeros remaining and the last bit was '1' (meaning we are about to start a new block of zeros). So, we subtract `dp(zeros_left - 1 - limit, ones_left, 1)`.
    *   **If `last_bit == 1` (last added was '1')**:
        *   We can add another '1': `dp(zeros_left, ones_left - 1, 1)`.
        *   We can switch to '0': `dp(zeros_left, ones_left - 1, 0)`.
        *   Similarly, to enforce the `limit` constraint for consecutive ones, we subtract `dp(zeros_left, ones_left - 1 - limit, 0)`.
5.  **Modulo Arithmetic**: All additions and subtractions should be performed modulo `10^9 + 7` to prevent overflow. Ensure that negative results from subtraction are handled correctly by adding `M` before taking the modulo: `(result % M + M) % M`.
6.  **Initial Call**: The total number of stable arrays is the sum of arrays starting with '0' and arrays starting with '1'. So, call `dp(zero, one, 0)` and `dp(zero, one, 1)` and sum their results modulo `M`.

## Concept to Remember
*   **Dynamic Programming with Memoization**: Breaking down a problem into overlapping subproblems and storing their solutions to avoid redundant computations.
*   **State Representation**: Carefully defining the DP state to capture all necessary information for transitions.
*   **Inclusion-Exclusion Principle (Implicit)**: The subtraction in the DP recurrence implicitly uses an inclusion-exclusion idea to count valid states by removing invalid ones.
*   **Modulo Arithmetic**: Handling large numbers by performing operations within a specific modulus.

## Common Mistakes
*   **Incorrect Base Cases**: Failing to handle `zeros_left < 0` or `ones_left < 0` correctly, or not having a proper base case for `zeros_left == 0 && ones_left == 0`.
*   **Off-by-One Errors in Transitions**: Miscalculating the number of remaining zeros/ones or the `limit` in the subtraction step.
*   **Modulo Arithmetic Errors**: Not applying modulo at each step, or incorrectly handling negative results from subtractions.
*   **Forgetting Memoization**: Implementing a purely recursive solution without memoization, leading to exponential time complexity.
*   **Incorrect Initial Calls**: Not considering both starting with '0' and starting with '1' for the initial state.

## Complexity Analysis
*   **Time**: O(zero * one * 2) which simplifies to O(zero * one).
    *   Reason: The DP state is defined by `zeros_left`, `ones_left`, and `lastUsed`. `zeros_left` can range from 0 to `zero`, `ones_left` from 0 to `one`, and `lastUsed` has 2 possibilities (0 or 1). Each state is computed exactly once due to memoization.
*   **Space**: O(zero * one * 2) which simplifies to O(zero * one).
    *   Reason: This is due to the memoization table `memo` which stores the results for each unique DP state.

## Commented Code
```java
class Solution {
    // Define the modulo constant for calculations.
    int M =(int)1e9 + 7;
    // Declare a 3D array for memoization.
    // memo[z][o][last] stores the result for z zeros left, o ones left, and last bit used.
    int[][][] memo;

    // Main function to calculate the number of stable arrays.
    public int numberOfStableArrays(int zero, int one, int limit) {
        // Initialize the memoization table with -1, indicating uncomputed states.
        // The dimensions are (zero+1) for zeros, (one+1) for ones, and 2 for the last bit (0 or 1).
        memo = new int[zero+1][one+1][2];
        for(int[][] d2 : memo) { // Iterate through the first dimension (zeros).
            for(int[] d1 : d2) { // Iterate through the second dimension (ones).
                Arrays.fill(d1,-1); // Fill the innermost array (last bit) with -1.
            }
        }
        
        // Calculate the number of stable arrays starting with '0'.
        int arraysStartingWithZero = stable(zero,one,limit,0);
        // Calculate the number of stable arrays starting with '1'.
        int arraysStartingWithOne = stable(zero,one,limit,1);
        
        // The total number of stable arrays is the sum of those starting with '0' and '1', modulo M.
        return (arraysStartingWithZero + arraysStartingWithOne) % M;
    }

    // Recursive helper function with memoization to calculate stable arrays.
    // zeroLeft: number of zeros remaining to be used.
    // oneLeft: number of ones remaining to be used.
    // consecutive: the maximum allowed consecutive identical bits (limit).
    // lastUsed: the last bit that was added (0 or 1).
    public int stable(int zeroLeft , int oneLeft, int consecutive, int lastUsed ){
       
        // Base case: If we have used more zeros or ones than available, this path is invalid.
        if(zeroLeft < 0 || oneLeft < 0) return 0;
        // Base case: If no zeros and no ones are left, we have successfully formed a stable array.
        if(zeroLeft==0 && oneLeft==0) return 1;
        
        // If the result for this state (zeroLeft, oneLeft, lastUsed) has already been computed, return it.
        if(memo[zeroLeft][oneLeft][lastUsed] != -1) return memo[zeroLeft][oneLeft][lastUsed];
        
        // Initialize the result for the current state.
        int result = 0;

        // If the last bit used was '1':
        if(lastUsed == 1){ 
            // Option 1: Add another '1'. This is valid as long as we don't exceed 'consecutive' ones.
            // The number of zeros remains the same, one less one is used, and the last bit is still '1'.
            result = stable(zeroLeft,oneLeft-1,consecutive,1);
            
            // Option 2: Switch to '0'. This is always possible if we have zeros left.
            // The number of zeros remains the same, one less one is used, and the last bit becomes '0'.
            if(zeroLeft >0) result = (result + stable(zeroLeft,oneLeft-1,consecutive,0)) % M;
            
            // Constraint handling: Subtract invalid states where we would have added more than 'consecutive' ones.
            // This happens if we try to add a '0' after a block of 'consecutive' ones.
            // We are looking for states where we have zeroLeft zeros and oneLeft-1-consecutive ones left,
            // and the last bit used was '0' (meaning we are about to start a new block of ones).
            // This subtraction ensures we don't count arrays with more than 'consecutive' ones.
            if(oneLeft > 0) result = (result - stable(zeroLeft,oneLeft-1-consecutive,consecutive,0) + M) % M;
        }
        // If the last bit used was '0':
        else{ // lastUsed == 0
            // Option 1: Add another '0'.
            // The number of ones remains the same, one less zero is used, and the last bit is still '0'.
            result = stable(zeroLeft-1,oneLeft,consecutive,0);
            
            // Option 2: Switch to '1'.
            // The number of ones remains the same, one less zero is used, and the last bit becomes '1'.
            if(oneLeft >0) result = (result + stable(zeroLeft-1,oneLeft,consecutive,1)) % M;
            
            // Constraint handling: Subtract invalid states where we would have added more than 'consecutive' zeros.
            // This happens if we try to add a '1' after a block of 'consecutive' zeros.
            // We are looking for states where we have zeroLeft-1-consecutive zeros and oneLeft ones left,
            // and the last bit used was '1' (meaning we are about to start a new block of zeros).
            if(zeroLeft >0) result = (result - stable(zeroLeft-1-consecutive,oneLeft,consecutive,1) + M) % M;
        }
        
        // Ensure the result is non-negative and within the modulo range.
        // The `+ M` handles potential negative results from subtraction before taking the modulo.
        result = (result % M + M) % M;
        
        // Store the computed result in the memoization table before returning.
        memo[zeroLeft][oneLeft][lastUsed] = result;
        return result;
    }
}
```

## Interview Tips
1.  **Explain the DP State Clearly**: Be able to articulate what `dp(zeros_left, ones_left, last_bit)` represents and why it's sufficient.
2.  **Justify the Subtraction**: The most complex part is the subtraction for the `limit` constraint. Explain *why* you subtract `stable(..., consecutive, other_bit)` – it's to remove the invalid sequences that would exceed the limit. This is a key insight.
3.  **Handle Modulo Correctly**: Emphasize that you are using modulo arithmetic to prevent overflow and show how you handle negative results from subtraction (`(res % M + M) % M`).
4.  **Discuss Base Cases and Transitions**: Walk through a small example (e.g., `zero=1, one=1, limit=1`) to demonstrate how the base cases and transitions work.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify overlapping subproblems and optimal substructure.
- [ ] Define the DP state `dp(zeros_left, ones_left, last_bit)`.
- [ ] Implement base cases correctly.
- [ ] Implement transitions for adding the same bit and switching bits.
- [ ] Implement the subtraction logic to enforce the `limit` constraint.
- [ ] Apply modulo arithmetic at each step.
- [ ] Handle negative results from subtraction correctly.
- [ ] Initialize memoization table and check for memoized results.
- [ ] Calculate the final answer by summing results from initial states.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [1411. Number of Ways to Paint N x 3 Grid](https://leetcode.com/problems/number-of-ways-to-paint-n-x-3-grid/) (Similar DP structure with state transitions)
*   [1235. Maximum Profit in Job Scheduling](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) (DP with sorted intervals)
*   [931. Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/) (Basic 2D DP)
*   [139. Word Break](https://leetcode.com/problems/word-break/) (DP on strings)

## Tags
`Dynamic Programming` `Recursion` `Memoization` `Combinatorics`

## My Notes
very difficult
