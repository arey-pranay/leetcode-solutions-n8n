# Product Of Array Except Self

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Prefix Sum`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] preProds = new int[n];
        int[] suffProds = new int[n];
        preProds[0] = 1;
        suffProds[n-1] = 1;
        for(int i=1;i<n;i++) preProds[i] = preProds[i-1]*nums[i-1];
        for(int i=n-2;i>=0;i--) suffProds[i] = suffProds[i+1]*nums[i+1];        
        for(int i=0;i<n;i++)nums[i] = preProds[i]*suffProds[i];
        return nums;
    }
}
```

---

---
## Quick Revision
Given an array of integers, return an array where each element is the product of all elements except itself.
This is solved by calculating prefix products and suffix products separately and then multiplying them for each element.

## Intuition
The core idea is that for any element `nums[i]`, the product of all elements except `nums[i]` can be broken down into two parts:
1. The product of all elements *before* `nums[i]` (prefix product).
2. The product of all elements *after* `nums[i]` (suffix product).
If we can efficiently calculate these two parts for every index, we can simply multiply them to get the final result. This avoids recalculating the entire product for each element, which would be O(n^2).

## Algorithm
1. Initialize an array `preProds` of the same size as `nums` to store prefix products.
2. Initialize `preProds[0]` to 1 (as there are no elements before the first one).
3. Iterate from the second element (`i = 1`) to the end of `nums`. For each `i`, calculate `preProds[i] = preProds[i-1] * nums[i-1]`. This stores the product of all elements *before* the current index `i`.
4. Initialize an array `suffProds` of the same size as `nums` to store suffix products.
5. Initialize `suffProds[n-1]` to 1 (as there are no elements after the last one).
6. Iterate from the second to last element (`i = n-2`) down to the beginning of `nums`. For each `i`, calculate `suffProds[i] = suffProds[i+1] * nums[i+1]`. This stores the product of all elements *after* the current index `i`.
7. Iterate through `nums` from `i = 0` to `n-1`. For each `i`, update `nums[i]` by multiplying `preProds[i]` and `suffProds[i]`. This gives the product of all elements except `nums[i]`.
8. Return the modified `nums` array.

## Concept to Remember
*   **Prefix Sum/Product:** Accumulating values from the beginning of a sequence.
*   **Suffix Sum/Product:** Accumulating values from the end of a sequence.
*   **Array Manipulation:** Efficiently updating array elements based on pre-computed values.
*   **Time-Space Tradeoff:** Using extra space (prefix and suffix arrays) to achieve a better time complexity.

## Common Mistakes
*   **Division by Zero:** If the problem allowed division, forgetting to handle cases with zeros in the input array would be a major issue. This solution avoids division.
*   **Off-by-One Errors:** Incorrectly handling the base cases (first and last elements) or loop boundaries when calculating prefix and suffix products.
*   **Modifying Input Array Prematurely:** If the problem statement required the original array to be preserved, modifying it in place during prefix/suffix calculations would be an error. This solution modifies it in the final step.
*   **Not Handling Edge Cases:** Forgetting to consider arrays with only one element or arrays with multiple zeros.

## Complexity Analysis
*   Time: O(n) - The algorithm involves three separate passes through the array, each taking O(n) time. (n for prefix products, n for suffix products, n for final multiplication).
*   Space: O(n) - Two additional arrays, `preProds` and `suffProds`, are used, each of size n. (Note: If the output array is not counted towards space complexity, then the space complexity is O(1) as the problem statement often implies).

## Commented Code
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Get the length of the input array.
        int n = nums.length;
        // Initialize an array to store prefix products.
        int[] preProds = new int[n];
        // Initialize an array to store suffix products.
        int[] suffProds = new int[n];

        // The prefix product for the first element is 1, as there are no elements before it.
        preProds[0] = 1;
        // The suffix product for the last element is 1, as there are no elements after it.
        suffProds[n-1] = 1;

        // Calculate prefix products.
        // Iterate from the second element (index 1) up to the end of the array.
        for(int i = 1; i < n; i++) {
            // The prefix product at index i is the prefix product at the previous index (i-1)
            // multiplied by the element at the previous index (nums[i-1]).
            preProds[i] = preProds[i-1] * nums[i-1];
        }

        // Calculate suffix products.
        // Iterate from the second to last element (index n-2) down to the beginning of the array.
        for(int i = n - 2; i >= 0; i--) {
            // The suffix product at index i is the suffix product at the next index (i+1)
            // multiplied by the element at the next index (nums[i+1]).
            suffProds[i] = suffProds[i+1] * nums[i+1];
        }

        // Calculate the final result by multiplying prefix and suffix products.
        // Iterate through the array.
        for(int i = 0; i < n; i++) {
            // The result for the current element nums[i] is the product of its prefix product
            // and its suffix product. We overwrite the original nums array with the result.
            nums[i] = preProds[i] * suffProds[i];
        }

        // Return the array containing the product of all elements except self for each position.
        return nums;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask if division is allowed. If it is, how to handle zeros. This solution assumes no division.
*   **Explain the Prefix/Suffix Idea:** Clearly articulate how breaking the problem into prefix and suffix products simplifies the calculation.
*   **Discuss Space Optimization:** Mention that the space complexity can be reduced to O(1) if the output array is not counted towards space complexity, by reusing the output array to store prefix products and then calculating suffix products on the fly.
*   **Handle Zeros:** Even though this solution doesn't use division, be prepared to discuss how you would handle zeros if division were allowed (e.g., count zeros, calculate product of non-zeros).

## Revision Checklist
- [ ] Understand the problem statement: product of all elements *except* the current one.
- [ ] Recognize the O(n^2) naive approach (nested loops).
- [ ] Grasp the prefix product concept.
- [ ] Grasp the suffix product concept.
- [ ] Combine prefix and suffix products for the final answer.
- [ ] Implement prefix product calculation correctly (base case, loop).
- [ ] Implement suffix product calculation correctly (base case, loop).
- [ ] Combine results correctly.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty array, single element array, zeros).
- [ ] Think about space optimization (O(1) space if output array is excluded).

## Similar Problems
*   [303. Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) (Prefix Sum concept)
*   [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) (This problem)
*   [713. Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) (Involves products, but different goal)

## Tags
`Array` `Prefix Sum` `Suffix Sum`

## My Notes
nice question
