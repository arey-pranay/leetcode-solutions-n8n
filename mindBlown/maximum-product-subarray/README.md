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
        int product = 1;
        int ans = Integer.MIN_VALUE;
        int n = nums.length;
        for(int i=0;i<n;i++){
            product *= nums[i];
            ans = Math.max(ans,product);
            if(product == 0) product = 1;
        }
        product=1;
        for(int i=n-1;i>=0;i--){
            product *= nums[i];
            ans = Math.max(ans,product);
            if(product == 0) product = 1;
        }
        return ans;  
    }
}


```

---

---
## Quick Revision
The problem asks to find the maximum product of a subarray within an array. We solve this by iterating through the array twice, once from left to right and once from right to left.

## Intuition
The key insight is that we can break down the problem into two passes: one for calculating the maximum product up to each position and another for calculating the maximum product ending at each position. This allows us to take advantage of both positive and negative numbers in the array.

## Algorithm

1. Initialize variables `product` and `ans` with 1 (since the product of an empty subarray is defined as 1) and `Integer.MIN_VALUE`, respectively.
2. Iterate through the array from left to right, updating `product` by multiplying it with the current number. Update `ans` if `product` exceeds its current value.
3. If `product` becomes 0, reset it to 1 (since a product of 0 would not contribute to the maximum product).
4. Repeat steps 2-3 in reverse order for the right-to-left pass.
5. Return `ans`, which now holds the maximum product subarray.

## Concept to Remember
* Dynamic Programming: breaking down a problem into smaller sub-problems and solving each one only once.
* Array Iteration: iterating through an array multiple times to calculate different properties.
* Edge Cases: handling special cases like products of 0, which can greatly impact results.

## Common Mistakes
* Failing to reset `product` when it becomes 0, leading to incorrect results for consecutive zeros.
* Not properly initializing variables before the first pass.
* Misunderstanding the significance of using `Integer.MIN_VALUE` as an initial value for `ans`.

## Complexity Analysis
- Time: O(n) - each element is visited twice in both passes.
- Space: O(1) - only a few extra variables are used, independent of input size.

## Commented Code
```java
class Solution {
    public int maxProduct(int[] nums) {
        // Initialize product and ans with 1 (since the product of an empty subarray is defined as 1)
        // and Integer.MIN_VALUE, respectively.
        int product = 1;
        int ans = Integer.MIN_VALUE;

        // First pass: left to right
        int n = nums.length;
        for(int i=0;i<n;i++){
            // Update product by multiplying it with the current number.
            product *= nums[i];
            
            // Update ans if product exceeds its current value.
            ans = Math.max(ans,product);
            
            // If product becomes 0, reset it to 1 (since a product of 0 would not contribute to the maximum product).
            if(product == 0) product = 1;
        }
        
        // Reset product for second pass
        product=1;

        // Second pass: right to left
        for(int i=n-1;i>=0;i--){
            // Update product by multiplying it with the current number.
            product *= nums[i];
            
            // Update ans if product exceeds its current value.
            ans = Math.max(ans,product);
            
            // If product becomes 0, reset it to 1 (since a product of 0 would not contribute to the maximum product).
            if(product == 0) product = 1;
        }
        
        return ans;  
    }
}
```

## Interview Tips
* Pay close attention to edge cases like products of 0 and their impact on the solution.
* Make sure to reset `product` after each pass, as failing to do so can lead to incorrect results.
* Practice solving this problem under timed conditions to improve your speed and accuracy.

## Revision Checklist
- [ ] Understand dynamic programming and its application in array iteration.
- [ ] Be able to identify edge cases like products of 0 and their impact on the solution.
- [ ] Confirm that all variables are properly initialized before the first pass.
- [ ] Verify that `product` is reset after each pass.

## Similar Problems
* Maximum Sum Subarray (LeetCode #53)
* Longest Increasing Subsequence (LeetCode #300)

## Tags
`Array` `Dynamic Programming`
