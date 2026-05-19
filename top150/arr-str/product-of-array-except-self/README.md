# Product Of Array Except Self

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Prefix Sum`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        
        int[] pre = new int[n];
        pre[0] = 1;
        for(int i=1; i<n; i++) pre[i] = pre[i-1]*nums[i-1];
        
        int[] suf = new int[n];
        suf[n-1] = 1;
        for(int i=n-2; i>=0; i--) suf[i] = suf[i+1]*nums[i+1];
        
        int[] ans = new int[n];
        for(int i=0; i<n; i++) ans[i] = pre[i] * suf[i];
        
        return ans;
    }
}
```

---

---
## Quick Revision
Given an array of integers, return an array where each element is the product of all elements except itself.
This is solved by calculating prefix products and suffix products separately and then multiplying them for each element.

## Intuition
The product of all elements except `nums[i]` can be thought of as the product of all elements *before* `nums[i]` multiplied by the product of all elements *after* `nums[i]`. This naturally leads to the idea of pre-calculating these two components.

## Algorithm
1. Initialize an array `prefix_products` of the same size as `nums`. Set `prefix_products[0]` to 1.
2. Iterate from the second element (`i = 1`) to the end of `nums`. For each `i`, calculate `prefix_products[i] = prefix_products[i-1] * nums[i-1]`. This stores the product of all elements to the left of the current index.
3. Initialize an array `suffix_products` of the same size as `nums`. Set `suffix_products[n-1]` (where `n` is the length of `nums`) to 1.
4. Iterate from the second to last element (`i = n-2`) down to the beginning of `nums`. For each `i`, calculate `suffix_products[i] = suffix_products[i+1] * nums[i+1]`. This stores the product of all elements to the right of the current index.
5. Initialize an answer array `result` of the same size as `nums`.
6. Iterate through `nums` from `i = 0` to `n-1`. For each `i`, calculate `result[i] = prefix_products[i] * suffix_products[i]`.
7. Return the `result` array.

## Concept to Remember
*   **Prefix Sum/Product:** Accumulating values from the beginning of a sequence.
*   **Suffix Sum/Product:** Accumulating values from the end of a sequence.
*   **Array Manipulation:** Efficiently using auxiliary arrays to store intermediate results.
*   **Time-Space Tradeoff:** Using extra space to achieve a better time complexity.

## Common Mistakes
*   **Division by Zero:** Attempting to calculate the total product and then dividing by `nums[i]` if `nums[i]` is zero. This problem statement often disallows division.
*   **Incorrect Indexing:** Off-by-one errors when calculating prefix or suffix products, especially at the boundaries (first and last elements).
*   **Overwriting Input Array:** Modifying the original `nums` array if it's not allowed or if it complicates the logic.
*   **Not Handling Edge Cases:** Forgetting to initialize the first/last prefix/suffix product to 1.

## Complexity Analysis
*   Time: O(N) - We iterate through the array three times (once for prefix products, once for suffix products, and once to combine them). Each iteration takes O(N) time.
*   Space: O(N) - We use two additional arrays (`pre` and `suf`) of size N to store prefix and suffix products, respectively. The output array `ans` also takes O(N) space, but often output space is not counted towards auxiliary space complexity.

## Commented Code
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Get the length of the input array.
        int n = nums.length;
        
        // Initialize an array to store prefix products.
        // pre[i] will store the product of all elements before nums[i].
        int[] pre = new int[n];
        // The product of elements before the first element is 1.
        pre[0] = 1;
        // Iterate from the second element to calculate prefix products.
        for(int i=1; i<n; i++) {
            // The prefix product at index i is the prefix product at i-1 multiplied by nums[i-1].
            pre[i] = pre[i-1]*nums[i-1];
        }
        
        // Initialize an array to store suffix products.
        // suf[i] will store the product of all elements after nums[i].
        int[] suf = new int[n];
        // The product of elements after the last element is 1.
        suf[n-1] = 1;
        // Iterate from the second to last element backwards to calculate suffix products.
        for(int i=n-2; i>=0; i--) {
            // The suffix product at index i is the suffix product at i+1 multiplied by nums[i+1].
            suf[i] = suf[i+1]*nums[i+1];
        }
        
        // Initialize the answer array.
        int[] ans = new int[n];
        // Iterate through the array to calculate the final result.
        for(int i=0; i<n; i++) {
            // The product except self at index i is the prefix product at i multiplied by the suffix product at i.
            ans[i] = pre[i] * suf[i];
        }
        
        // Return the final answer array.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask if division is allowed. If it is, a simpler O(N) time, O(1) space solution exists (calculate total product, then divide). If not, the prefix/suffix product approach is necessary.
*   **Explain the "Why":** Clearly articulate the intuition behind splitting the problem into prefix and suffix products.
*   **Space Optimization:** Discuss how the space complexity can be reduced to O(1) (excluding the output array) by using the output array itself to store intermediate prefix products and then calculating suffix products on the fly.
*   **Edge Cases:** Be prepared to discuss how your solution handles arrays with zeros, negative numbers, or single elements.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the constraint about not using division.
- [ ] Grasp the prefix and suffix product concept.
- [ ] Implement the prefix product calculation correctly.
- [ ] Implement the suffix product calculation correctly.
- [ ] Combine prefix and suffix products to get the final answer.
- [ ] Analyze time and space complexity.
- [ ] Consider space optimization (O(1) auxiliary space).
- [ ] Practice explaining the solution verbally.

## Similar Problems
*   Maximum Product Subarray
*   Product of Array Except Self II (with constraints on zeros)
*   Running Sum of 1d Array
*   Prefix Sum

## Tags
`Array` `Prefix Sum` `Suffix Sum`
