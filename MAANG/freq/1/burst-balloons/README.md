# Burst Balloons

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n^3)  
**Space:** O(n^2)

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
Find the maximum coins obtainable by bursting balloons in the optimal order, where bursting balloon `i` yields `nums[left] * nums[i] * nums[right]` coins.
Solve it using dynamic programming with memoization by working backwards—deciding which balloon to burst *last* in any given sub-interval.

## Intuition
The "aha moment" in this problem comes from shifting perspective. Instead of deciding which balloon to burst *first*, which changes the remaining adjacent neighbors dynamically and complicates sub-problems, we decide which balloon to burst **last** within a range `(left, right)`. 
If we choose balloon `i` to be the very last one to burst in the range `(left, right)`, then at that exact moment, all other balloons in `(left+1, right-1)` have already been burst. Therefore, the immediate neighbors of balloon `i` that are still intact must be the boundaries `left` and `right`. The total coins for this choice will be `arr[left] * arr[i] * arr[right]` plus the optimal results from solving the two independent sub-problems: `func(left, i)` and `func(i, right)`.

## Algorithm
1. Create a new padded array `arr` of size `n + 2` to handle boundary conditions gracefully, setting `arr[0] = 1` and `arr[n+1] = 1`.
2. Copy all non-zero elements from `nums` into the padded array (optimizing out zeros, though even standard padding works; the provided solution filters non-zeros and adjusts bounds).
3. Initialize a 2D memoization table `memo` of size `(j x j)` with `-1`.
4. Define a recursive helper function `func(left, right)` to find the max coins in the interval `(left, right)`.
5. Base case: If `left == right - 1`, there are no balloons between `left` and `right`, so return `0`.
6. Memoization check: If `memo[left][right]` is already computed, return its value.
7. Iterate through every possible candidate `i` from `left + 1` to `right - 1` assuming balloon `i` is burst *last* in this range.
8. Calculate coins: `arr[left] * arr[i] * arr[right] + func(left, i) + func(i, right)`, and track the maximum across all candidates `i`.
9. Store the maximum result in `memo[left][right]` and return it.

## Concept to Remember
- Interval Dynamic Programming: Solving problems where states are defined by sub-arrays or intervals `[i, j]`.
- Reversing the Order of Operations: Transforming a dependent state transition into an independent one by thinking backwards (choosing the last operation instead of the first).
- Divide and Conquer with Overlapping Subproblems: Breaking an interval into two independent sub-intervals plus a combining cost.

## Common Mistakes
- Trying to choose which balloon to burst *first*, leading to complex state definitions that depend on mutable neighbor configurations.
- Forgetting to pad the array with boundaries of `1`, which causes out-of-bounds errors when calculating corner balloons.
- Incorrectly defining the base case, such as checking `left >= right` instead of `left == right - 1`.
- Missing memoization, which turns an efficient polynomial solution into an exponential brute-force search.

## Complexity Analysis
- Time: $O(n^3)$ - There are $O(n^2)$ states defined by pairs of `(left, right)`, and for each state, we loop up to $O(n)$ times to find the optimal last balloon to burst.
- Space: $O(n^2)$ - Required for the 2D memoization table and the recursion stack depth of up to $O(n)$.

## Commented Code
```java
class Solution {
    int[] arr; // Global array to store padded balloon values
    int[][] memo; // Memoization table to store results of sub-problems
    public int maxCoins(int[] nums) {
        int n = nums.length; // Get the number of original balloons
        arr = new int[n+2]; // Initialize padded array with space for boundary 1s
        arr[0] = 1; // Left boundary sentinel value
        int j=1; // Pointer for the padded array
        for(int i=0;i<n;i++) if(nums[i]!=0) arr[j++] = nums[i]; // Copy non-zero elements into padded array
        arr[j++] = 1;  // Right boundary sentinel value
        memo = new int[j][j]; for(int[] temp : memo) Arrays.fill(temp,-1); // Initialize memo table with -1
        return func(0,j-1); // Kick off recursion for the entire array range
    }
    private int func(int left, int right){
        int max = 0; // Track maximum coins for the current interval
        if(left == right-1) return 0; // Base case: no balloons left between left and right
        if(memo[left][right] != -1) return memo[left][right]; // Return cached result if already computed
        for(int i=left+1;i<right;i++){ // Iterate through every balloon i to be burst LAST in this range
            max = Math.max(max, arr[i]*arr[left]*arr[right] + func(left,i) + func(i,right)); // Calculate coins if i is last + sub-problems
        }
        return memo[left][right]=max; // Memoize the maximum result and return it
    }
}
```

## Interview Tips
- When the interviewer asks a question where choices affect future states unpredictably (like removing elements), proactively suggest thinking about the problem in reverse (what happens *last*).
- Clearly explain the interval DP pattern: why the loop iterates through all intermediate points `i` and why sub-problems `func(left, i)` and `func(i, right)` are independent.
- Mention time and space complexity upfront and discuss how memoization avoids redundant calculations.

## Revision Checklist
- [ ] Understand why choosing the *last* balloon to burst simplifies the sub-problems.
- [ ] Know how to set up the boundary padding (`1` at both ends).
- [ ] Write the interval DP state transition recurrence relation from memory.
- [ ] Explain the $O(n^3)$ time complexity breakdown clearly.

## Similar Problems
- Matrix Chain Multiplication
- Minimum Cost to Cut a Stick
- Remove Boxes
- Guess Number Higher or Lower II

## Tags
`Array` `Dynamic Programming`
