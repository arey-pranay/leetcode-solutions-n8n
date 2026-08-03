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
Find the contiguous subarray within an array that has the largest product.
This is solved by iterating from both left and right, keeping track of current products and resetting on zero.

## Intuition
The core challenge is handling negative numbers. A small negative number multiplied by another negative number can become a large positive number. A single pass from left to right might miss the maximum product if it involves a negative number at the beginning and another at the end. For example, in `[-2, 3, -4]`, the maximum product is `24` (`-2 * 3 * -4`). A left-to-right pass would see `-2`, then `-6`, then `24`. However, if we had `[2, 3, -2, 4]`, the left-to-right pass would yield `6` (from `[2, 3]`), but the maximum is `48` (from `[-2, 4]` if we consider it as a subarray, but the problem asks for contiguous subarray, so the max is `6`). The issue is that a negative number can "flip" the sign of the product, making a previously small negative product into a large positive one.

The two-pass approach addresses this. The first pass (left-to-right) captures maximum products that end at the current position. The second pass (right-to-left) captures maximum products that start at the current position. By combining these, we ensure we don't miss any potential maximums. The zero handling is crucial because any product involving zero becomes zero, effectively resetting the subarray calculation.

## Algorithm
1. Initialize `ans` to the smallest possible integer value to store the maximum product found so far.
2. Initialize `product` to 1 for the left-to-right pass.
3. Iterate through the array from left to right (index `i` from 0 to `n-1`):
    a. Multiply `product` by the current element `nums[i]`.
    b. Update `ans` to be the maximum of `ans` and `product`.
    c. If `product` becomes 0, reset `product` to 1. This is because a zero breaks the contiguity of a product subarray.
4. Reset `product` to 1 for the right-to-left pass.
5. Iterate through the array from right to left (index `i` from `n-1` to 0):
    a. Multiply `product` by the current element `nums[i]`.
    b. Update `ans` to be the maximum of `ans` and `product`.
    c. If `product` becomes 0, reset `product` to 1.
6. Return `ans`.

## Concept to Remember
*   Dynamic Programming (Implicit): While not a classic DP table, the `product` variable acts as a state that depends on the previous state, similar to DP.
*   Handling Negative Numbers: The key insight is that negative numbers can turn small products into large ones, requiring careful tracking.
*   Edge Cases: Zeros and single-element arrays need to be handled correctly.

## Common Mistakes
*   Forgetting to handle zeros: Zeros reset the product, and failing to reset `product` to 1 after encountering a zero will lead to incorrect results.
*   Only performing a single pass: A single pass from left-to-right (or right-to-left) is insufficient because it might miss maximum products that span across negative numbers in a way that requires considering the array from both directions.
*   Not initializing `ans` correctly: Initializing `ans` to 0 might be incorrect if all numbers are negative (e.g., `[-1, -2, -3]`, max product is `6`, not `0`). `Integer.MIN_VALUE` is a safer bet.
*   Integer Overflow: For very large arrays with large numbers, the product could exceed the capacity of an `int`. This solution assumes `int` is sufficient.

## Complexity Analysis
*   Time: O(n) - The algorithm involves two separate passes through the array, each taking linear time with respect to the number of elements `n`.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables like `product` and `ans`, regardless of the input array size.

## Commented Code
```java
class Solution {
    public int maxProduct(int[] nums) {
        // Initialize 'ans' to the smallest possible integer value.
        // This variable will store the maximum product found so far.
        int ans = Integer.MIN_VALUE;
        // Initialize 'product' to 1 for the first pass (left-to-right).
        // This variable will store the current product of the subarray.
        int product = 1;
        // Get the length of the input array.
        int n = nums.length;

        // First pass: Iterate through the array from left to right.
        for(int i = 0; i < n; i++){
            // Multiply the current 'product' by the current element.
            product *= nums[i];
            // Update 'ans' if the current 'product' is greater than the current 'ans'.
            ans = Math.max(ans, product);
            // If 'product' becomes 0, reset it to 1.
            // A zero breaks the contiguity of a product subarray, so we start a new subarray calculation.
            if(product == 0) product = 1;
        }

        // Reset 'product' to 1 for the second pass (right-to-left).
        product = 1;
        // Second pass: Iterate through the array from right to left.
        for(int i = n - 1; i >= 0; i--){
            // Multiply the current 'product' by the current element.
            product *= nums[i];
            // Update 'ans' if the current 'product' is greater than the current 'ans'.
            ans = Math.max(ans, product);
            // If 'product' becomes 0, reset it to 1.
            // A zero breaks the contiguity of a product subarray, so we start a new subarray calculation.
            if(product == 0) product = 1;
        }
        // Return the maximum product found across both passes.
        return ans;
    }
}
```

## Interview Tips
*   Explain the intuition behind the two-pass approach clearly, especially how it handles negative numbers.
*   Discuss the role of zeros and why resetting the product is necessary.
*   Be prepared to discuss alternative approaches, such as a DP solution that tracks both the maximum and minimum product ending at each position.
*   Ask clarifying questions about potential integer overflow if the constraints are not specified.

## Revision Checklist
- [ ] Understand the problem: find the contiguous subarray with the largest product.
- [ ] Recognize the challenge: negative numbers can flip signs and become large positives.
- [ ] Implement the two-pass approach (left-to-right and right-to-left).
- [ ] Handle zeros correctly by resetting the product.
- [ ] Initialize the maximum product variable (`ans`) appropriately (e.g., `Integer.MIN_VALUE`).
- [ ] Analyze time and space complexity.

## Similar Problems
Maximum Subarray (LeetCode 53)
Subarray Product Less Than K (LeetCode 713)

## Tags
`Array` `Dynamic Programming`
