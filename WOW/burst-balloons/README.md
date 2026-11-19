# Burst Balloons

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(N^3)  
**Space:** O(N^2)

---

## Solution (java)

```java
class Solution {
    int[] arr;
    int[][] memo;
    public int maxCoins(int[] nums) {
        int n = nums.length; arr = new int[n+2]; arr[0] = 1; int j=1;
        for(int i=0;i<n;i++) if(nums[i]!=0) arr[j++] = nums[i];
        arr[j++] = 1; 
        memo = new int[j][j]; for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(0,j-1);
    }
    private int func(int left, int right){
        int max = 0;
        if(left == right-1) return 0;
        if(memo[left][right] != -1) return memo[left][right];
        for(int i=left+1;i<right;i++){
            max = Math.max(max, arr[i]*arr[left]*arr[right] + func(left,i) + func(i,right)); //ye number last me udaayege, then only boundary will remain
        }
        return memo[left][right]=max;
    }
}
```

---

---
## Quick Revision
Find the maximum coins obtainable by bursting balloons in the optimal order, where bursting balloon `i` gives `nums[left] * nums[i] * nums[right]` coins.
Solve this using dynamic programming with memoization by working backwards—deciding which balloon to burst *last* in any given range.

## Intuition
The "aha moment" in this problem is changing perspective from "which balloon should I burst first?" to "which balloon should I burst *last*?" 
If we decide to burst balloon `i` *last* in the range `(left, right)`, then at the moment we burst it, all other balloons in that range have already been burst. This means the left and right neighbors adjacent to `i` at that final moment are guaranteed to be the boundary balloons `left` and `right`. Thus, the subproblems for the left range `(left, i)` and the right range `(i, right)` become completely independent of each other, allowing for clean state transitions.

## Algorithm
1. Create a new padded array `arr` of size `n + 2`, where `arr[0] = 1` and `arr[n+1] = 1`, and copy all non-zero elements from `nums` in between (handling zeros can optimize, but generally we pad with 1s at boundaries).
2. Initialize a 2D memoization table `memo` of size `(n+2) x (n+2)` filled with `-1`.
3. Define a recursive helper function `func(left, right)` that computes the max coins for the subproblem between indices `left` and `right`.
4. Base case: If `left == right - 1`, there are no balloons strictly between them, so return `0`.
5. Check memoization: If `memo[left][right]` is already computed, return it.
6. Iterate through every possible candidate `i` from `left + 1` to `right - 1`, treating `i` as the *last* balloon to burst in this range.
7. Compute coins obtained: `arr[left] * arr[i] * arr[right] + func(left, i) + func(i, right)`.
8. Track the maximum coins over all choices of `i`, store the result in `memo[left][right]`, and return it.

## Concept to Remember
- Dynamic Programming with Memoization (Top-Down approach).
- Interval DP pattern (solving problems defined over sub-arrays/intervals).
- Reversing the order of operations (thinking about the "last" action instead of the "first" to maintain independent subproblems).
- Array boundary padding (adding virtual elements of value `1`).

## Common Mistakes
- Trying to choose which balloon to burst *first*, which breaks subproblem independence because the neighbors change dynamically.
- Forgetting to pad the input array with `1`s at the boundaries to handle edge cases gracefully.
- Incorrect base case definition, leading to out-of-bounds errors or infinite recursion.
- Confusing the subproblem interval definitions (e.g., inclusive vs. exclusive bounds).

## Complexity Analysis
- Time: $O(N^3)$ - There are $O(N^2)$ states represented by pairs of `(left, right)` indices, and for each state, we iterate through $O(N)$ choices for the last balloon to burst.
- Space: $O(N^2)$ - Required for the 2D memoization table and the recursion call stack depth.

## Commented Code
```java
class Solution {
    int[] arr; // Stores the padded balloon values including boundary 1s
    int[][] memo; // Memoization table to store results of subproblems (left, right)
    
    public int maxCoins(int[] nums) {
        int n = nums.length; // Get the original number of balloons
        arr = new int[n+2]; // Initialize padded array with space for boundary 1s
        arr[0] = 1; // Set the left virtual boundary balloon to 1
        int j = 1; // Pointer for filling the padded array
        
        for(int i = 0; i < n; i++) {
            if(nums[i] != 0) arr[j++] = nums[i]; // Copy non-zero elements (optimization, though zeros can also be kept)
        }
        arr[j++] = 1; // Set the right virtual boundary balloon to 1
        
        memo = new int[j][j]; // Initialize memoization table with dimensions matching the padded array
        for(int[] temp : memo) {
            Arrays.fill(temp, -1); // Fill memo table with -1 to indicate uncomputed states
        }
        
        return func(0, j - 1); // Start the recursive DP on the entire padded array range
    }
    
    private int func(int left, int right) {
        int max = 0; // Track the maximum coins for the current range
        
        if(left == right - 1) return 0; // Base case: no balloons left between left and right indices
        
        if(memo[left][right] != -1) return memo[left][right]; // Return cached result if already computed
        
        for(int i = left + 1; i < right; i++) {
            // Try every balloon 'i' as the LAST balloon to be burst in the range (left, right)
            // Coin formula: value of left boundary * value of last balloon * value of right boundary
            // Plus optimal coins from the left sub-interval and right sub-interval
            max = Math.max(max, arr[i] * arr[left] * arr[right] + func(left, i) + func(i, right));
        }
        
        return memo[left][right] = max; // Store the computed max in memo table and return it
    }
}
```

## Interview Tips
- State clearly why you are thinking about interval DP and why standard greedy or forward choices fail.
- Explain the choice of reversing the perspective (choosing the last balloon instead of the first) proactively to impress the interviewer.
- Be ready to discuss the time and space complexity, and how it transitions from $O(N^3)$ top-down to bottom-up interval DP.
- Walk through a small example (e.g., `[3, 1, 5, 8]`) to demonstrate state transitions and padding clearly.

## Revision Checklist
- [ ] Understand why we think about the *last* balloon instead of the first.
- [ ] Know how to pad the array with `1`s.
- [ ] Write the interval DP recursive function with memoization correctly.
- [ ] Identify base cases (`left == right - 1`).
- [ ] Analyze the $O(N^3)$ time and $O(N^2)$ space complexities.

## Similar Problems
- `Matrix Chain Multiplication`
- `Minimum Cost to Merge Stones`
- `Strange Printer`
- `Remove Boxes`

## Tags
`Array` `Dynamic Programming`
