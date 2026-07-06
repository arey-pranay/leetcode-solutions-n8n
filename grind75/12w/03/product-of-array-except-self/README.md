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
        int pre[] = new int[n];
        int suf[] = new int[n];
        int ans[] = new int[n];
        pre[0] = 1; suf[n - 1] = 1;
        for (int i = 1; i <n ;i++) pre[i] = pre[i-1] * nums[i-1];
        for (int i = n-2; i >= 0;i--)suf[i] = suf[i+1] * nums[i+1];
        for (int i = 0; i < n;i++) ans[i] = pre[i]*suf[i];     
        return ans;
    }
}
```

---

---
## Quick Revision
The problem asks to find the product of all numbers in an array except for each number itself. The solution is to calculate the prefix and suffix products separately, then multiply them together.

## Intuition
This approach works because we can efficiently compute the product of all numbers up to a certain point using a single pass through the array, and similarly for the product of all numbers from a certain point onwards. By multiplying these two products together, we get the final result.

## Algorithm

1. Initialize three arrays: `pre[]` to store the prefix products, `suf[]` to store the suffix products, and `ans[]` to store the final results.
2. Set the first element of `pre[]` and the last element of `suf[]` to 1, since there is no number before the first element or after the last element to multiply with.
3. Compute the prefix products by iterating through the array from left to right: for each element, multiply it with the previous element's product (if any).
4. Compute the suffix products by iterating through the array from right to left: for each element, multiply it with the next element's product (if any).
5. Multiply corresponding elements of `pre[]` and `suf[]` to get the final results.

## Concept to Remember
* Prefix and suffix arrays can be used to efficiently compute cumulative products.
* Multiplying two arrays element-wise can help compute final results.

## Common Mistakes

* Forgetting to handle edge cases (e.g. empty array).
* Not initializing prefix and suffix arrays correctly.
* Overcomplicating the solution with unnecessary loops or data structures.

## Complexity Analysis
- Time: O(n) - We iterate through the array twice, once for each prefix/suffix product.
- Space: O(n) - We need to store the prefix and suffix products separately.

## Commented Code

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Initialize arrays
        int n = nums.length;
        int pre[] = new int[n];
        int suf[] = new int[n];
        int ans[] = new int[n];

        // Set edge cases for prefix and suffix products
        pre[0] = 1; // product of all numbers before the first element is 1
        suf[n - 1] = 1; // product of all numbers after the last element is 1

        // Compute prefix products
        for (int i = 1; i < n; i++) {
            // Multiply current element with previous element's product
            pre[i] = pre[i-1] * nums[i-1];
        }

        // Compute suffix products
        for (int i = n - 2; i >= 0; i--) {
            // Multiply current element with next element's product
            suf[i] = suf[i+1] * nums[i+1];
        }

        // Multiply corresponding elements of pre[] and suf[]
        for (int i = 0; i < n; i++) {
            ans[i] = pre[i]*suf[i];     
        }
        
        return ans;
    }
}
```

## Interview Tips

* Make sure to handle edge cases carefully, especially when using prefix/suffix arrays.
* Use simple and efficient algorithms whenever possible.
* Practice explaining your solution clearly and concisely.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Choose a suitable data structure for storing prefix/suffix products.
- [ ] Initialize prefix and suffix arrays correctly.
- [ ] Implement efficient computation of prefix/suffix products.
- [ ] Multiply corresponding elements to get final results.

## Similar Problems

* Product of Array Except Self (LeetCode 238)
* Array Product (HackerRank)

## Tags
`Array` `Hash Map`
