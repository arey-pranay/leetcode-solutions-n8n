# Maximum Subarray Sum After Multiplier

**Difficulty:** Medium  
**Language:** Javascript  
**Tags:** `Array` `Hash Map` `Dynamic Programming` `Memoization`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (javascript)

```javascript
class Solution {
    Long[][] memo;
    public long maxSubarraySum(int[] nums, int k) {
        long max = Long.MIN_VALUE;
        
        // 1. Initialize memo ONCE for the multiplication transform
        memo = new Long[3][nums.length];
        for(int i = 0; i < nums.length; i++){
            long a = func(0, nums, i, k, x -> (long) x * k);
            max = Math.max(max, a);
        }
           
        // 2. Reset and reuse memo ONCE for the division transform
        memo = new Long[3][nums.length];
        for(int i = 0; i < nums.length; i++){
            long b = func(0, nums, i, k, x -> (long) x / k);
            max = Math.max(max, b);
        }
        
        return max;
    }

    public long func(int state, int[] arr, int index, int k, java.util.function.LongUnaryOperator transform){
        if(index == arr.length) return state == 1 || state == 2 ? 0 : Long.MIN_VALUE / 4; 
        if(memo[state][index] != null) return memo[state][index];
        
        long x = arr[index];
        long y = transform.applyAsLong(x);

        if(state == 0)
            return memo[state][index] = Math.max(
                x + Math.max(0, func(0, arr, index + 1, k, transform)), 
                y + Math.max(0, func(1, arr, index + 1, k, transform)) 
            );
        if(state == 1)
             return memo[state][index] = Math.max(
                y + Math.max(0, func(1, arr, index + 1, k, transform)), 
                x + Math.max(0, func(2, arr, index + 1, k, transform))  
            );
        return memo[state][index] = x + Math.max(0, func(2, arr, index + 1, k, transform)); 
    }
}
```

---

---
## Quick Revision
The problem is to find the maximum subarray sum after multiplying or dividing all numbers in a given array by a certain value `k`. The solution involves dynamic programming with memoization.

## Intuition
The key insight here is that we can solve this problem by considering two transformations: multiplication and division. By applying these transformations, we can reduce the problem to a smaller subproblem, which allows us to use memoization to avoid redundant calculations.

## Algorithm

1. Initialize an empty memo table `memo` with dimensions `[3] x [n]`, where `n` is the length of the input array.
2. Iterate over the input array and for each element, apply both multiplication and division transformations using a helper function `func`.
3. For each transformation, calculate the maximum subarray sum by considering two cases: including the current element and excluding it.
4. Store the results in the memo table to avoid redundant calculations.
5. Return the maximum value found between the two transformations.

## Concept to Remember
* **Dynamic Programming**: Break down the problem into smaller subproblems that can be solved recursively, storing intermediate results to avoid redundant calculations.
* **Memoization**: Use a table or cache to store previously computed results, reducing the time complexity of solving subproblems.
* **State Machine**: Represent the current state of the algorithm using an integer value, where each state corresponds to a specific transformation (multiplication or division).

## Common Mistakes
* Failing to initialize the memo table correctly before starting the iterations.
* Not considering both multiplication and division transformations in the solution.
* Not storing intermediate results in the memo table to avoid redundant calculations.

## Complexity Analysis
- Time: O(n), where n is the length of the input array, since we only need to iterate over the array once for each transformation.
- Space: O(n), as we need to store the memo table with dimensions `[3] x [n]`.

## Commented Code

```javascript
class Solution {
    // Initialize memo table ONCE
    Long[][] memo;
    
    public long maxSubarraySum(int[] nums, int k) {
        long max = Long.MIN_VALUE;
        
        // Apply multiplication transformation
        memo = new Long[3][nums.length];
        for (int i = 0; i < nums.length; i++) {
            // Calculate maximum subarray sum including current element
            long a = func(0, nums, i, k, x -> (long) x * k);
            max = Math.max(max, a);
        }
        
        // Reset and reuse memo table for division transformation
        memo = new Long[3][nums.length];
        for (int i = 0; i < nums.length; i++) {
            // Calculate maximum subarray sum including current element
            long b = func(0, nums, i, k, x -> (long) x / k);
            max = Math.max(max, b);
        }
        
        return max;
    }

    public long func(int state, int[] arr, int index, int k, java.util.function.LongUnaryOperator transform){
        // Base case: if we've reached the end of the array
        if (index == arr.length) {
            if (state == 1 || state == 2) return 0; // Edge cases for multiplication and division transformations
            else return Long.MIN_VALUE / 4;
        }
        
        // Check if result is already stored in memo table
        if (memo[state][index] != null) {
            return memo[state][index];
        }
        
        long x = arr[index];
        long y = transform.applyAsLong(x);
        
        // Calculate maximum subarray sum including current element for each state
        if (state == 0)
            return memo[state][index] = Math.max(
                x + Math.max(0, func(0, arr, index + 1, k, transform)), 
                y + Math.max(0, func(1, arr, index + 1, k, transform)) 
            );
        if (state == 1)
            return memo[state][index] = Math.max(
                y + Math.max(0, func(1, arr, index + 1, k, transform)), 
                x + Math.max(0, func(2, arr, index + 1, k, transform))  
            );
        return memo[state][index] = x + Math.max(0, func(2, arr, index + 1, k, transform)); 
    }
}
```

## Interview Tips
* Pay attention to the problem statement and identify key constraints (in this case, the multiplication or division transformation).
* Use dynamic programming with memoization to optimize the solution.
* Consider both cases for each state in the algorithm (including and excluding the current element).

## Revision Checklist
- [ ] Review the problem statement carefully before starting the solution.
- [ ] Initialize the memo table correctly before starting the iterations.
- [ ] Store intermediate results in the memo table to avoid redundant calculations.

## Similar Problems
* Maximum Subarray Sum
* Longest Increasing Subsequence

## Tags
`Array`, `Hash Map`, `Dynamic Programming`, `Memoization`
