# Maximum Product Subarray

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxProduct(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int ans = nums[0];
        int n = nums.length;
        for(int i=1;i<n;i++){
            int curr = nums[i];
            if(curr < 0){
                int temp= min;
                min =  max;
                max = temp;
            }
            max = Math.max(max*curr,curr);
            min = Math.min(min*curr,curr);
            ans = Math.max(max, ans);
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Find the contiguous subarray within an array that has the largest product.
This is solved by tracking both the maximum and minimum product ending at the current position.

## Intuition
The core challenge is handling negative numbers. A negative number multiplied by another negative number can become a large positive number. Therefore, we can't just track the maximum product ending at the current position. We also need to track the minimum product ending at the current position, because this minimum (which could be a large negative number) might become the new maximum when multiplied by another negative number.

## Algorithm
1. Initialize `max_so_far`, `min_so_far`, and `overall_max` to the first element of the array.
2. Iterate through the array starting from the second element (index 1).
3. For each element `current_num`:
    a. If `current_num` is negative, swap `max_so_far` and `min_so_far`. This is because multiplying by a negative number flips the roles of maximum and minimum.
    b. Update `max_so_far`: It's the maximum of `current_num` itself or `max_so_far * current_num`. This accounts for starting a new subarray at `current_num` or extending the previous maximum product subarray.
    c. Update `min_so_far`: It's the minimum of `current_num` itself or `min_so_far * current_num`. This accounts for starting a new subarray at `current_num` or extending the previous minimum product subarray.
    d. Update `overall_max`: It's the maximum of `overall_max` and `max_so_far`. This keeps track of the largest product found so far across all subarrays.
4. Return `overall_max`.

## Concept to Remember
*   Dynamic Programming: The solution builds upon previously computed results (max/min product ending at the previous index).
*   Handling Negative Numbers: The key insight is that negative numbers can turn a minimum product into a maximum product.
*   Edge Cases: Single element arrays, arrays with zeros, and arrays with all negative numbers.

## Common Mistakes
*   Forgetting to track the minimum product: This is the most crucial mistake, as it fails to account for how negative numbers can create large positive products.
*   Not handling the case where the current number itself is the new maximum/minimum: The `max` and `min` updates should consider `curr` as a potential start of a new subarray.
*   Incorrectly swapping `min` and `max` when encountering a negative number.
*   Not initializing `overall_max` correctly (e.g., to 0 if the array can contain negative numbers).

## Complexity Analysis
*   Time: O(n) - The algorithm iterates through the array once.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    public int maxProduct(int[] nums) {
        // Initialize min_so_far, max_so_far, and overall_max to the first element.
        // min_so_far: tracks the minimum product ending at the current position.
        // max_so_far: tracks the maximum product ending at the current position.
        // overall_max: tracks the global maximum product found so far.
        int min = nums[0];
        int max = nums[0];
        int ans = nums[0];
        // Get the length of the array.
        int n = nums.length;
        // Iterate through the array starting from the second element.
        for(int i=1;i<n;i++){
            // Store the current number.
            int curr = nums[i];
            // If the current number is negative, we need to swap min and max.
            // This is because multiplying by a negative number will reverse the order:
            // a large positive becomes a large negative, and a large negative becomes a large positive.
            if(curr < 0){
                // Temporarily store the current minimum.
                int temp= min;
                // The new minimum will be the old maximum.
                min =  max;
                // The new maximum will be the old minimum.
                max = temp;
            }
            // Update max_so_far: it's either the current number itself (starting a new subarray)
            // or the product of the current number and the previous max_so_far.
            max = Math.max(max*curr,curr);
            // Update min_so_far: it's either the current number itself (starting a new subarray)
            // or the product of the current number and the previous min_so_far.
            min = Math.min(min*curr,curr);
            // Update the overall maximum product found so far.
            ans = Math.max(max, ans);
        }
        // Return the overall maximum product.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain the intuition behind tracking both `min` and `max` products. This is the most critical part of the explanation.
*   Walk through an example with negative numbers and zeros to demonstrate how your logic handles them. For instance, `[-2, 3, -4]`.
*   Be prepared to discuss the time and space complexity and justify why it's optimal.
*   If asked for an alternative approach, you could mention a DP solution with a 2D array `dp[i][0]` for max product ending at `i` and `dp[i][1]` for min product ending at `i`, but highlight that the O(1) space solution is preferred.

## Revision Checklist
- [ ] Understand the problem statement: find max product of a contiguous subarray.
- [ ] Recognize the impact of negative numbers on products.
- [ ] Implement the logic to track both `max_so_far` and `min_so_far`.
- [ ] Handle the swap of `min` and `max` when a negative number is encountered.
- [ ] Ensure `overall_max` is updated correctly in each iteration.
- [ ] Consider edge cases: empty array (though constraints usually prevent this), single element, zeros, all negatives.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Subarray (LeetCode 53) - Similar but for sum, doesn't require tracking min.
*   Product of Array Except Self (LeetCode 238) - Different problem, but involves products.

## Tags
`Array` `Dynamic Programming`

## My Notes
amazing algorithm
