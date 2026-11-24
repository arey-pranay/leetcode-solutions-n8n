# Maximum Subarray With Equal Products

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Math` `Sliding Window` `Enumeration` `Number Theory`  
**Time:** O(N^2 \log M)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxLength(int[] nums) {
        // a*b = lcm(a,b)*gcd(a,b)
        int ans = 0,n=nums.length;
        for(int i=0;i<n;i++){
            int product = 1;
            int lcm = 1;
            int gcd = 0;
            for(int j=i;j<n;j++){
                product *= nums[j];// 64
                gcd = getGcd(gcd,nums[j]); // 4
                lcm = (lcm/getGcd(lcm,nums[j])) * nums[j]; //2
                if(product == lcm*gcd) ans = Math.max(ans,j-i+1); //true always
            }
        }
        return ans;
    }
    public int getGcd(int a , int b){
        if(b==0) return a;
        return getGcd(b,a%b);
    }
}

  // for arr having 1, 5 and 7, gcd = 1. we would need lcm = prod
        // gcd can never grow, lcm can never shrink
        // gcd <= lowest, lcm >= highest, product != 1 (except all 1)
        
```

---

---
## Quick Revision
Finds the length of the longest subarray where the product of elements equals the product of their Least Common Multiple (LCM) and Greatest Common Divisor (GCD).
Solved by using a nested loop to check all possible subarrays while dynamically maintaining the product, GCD, and LCM.

## Intuition
The "aha moment" is realizing that the fundamental number-theoretic property $a \times b = \text{lcm}(a, b) \times \text{gcd}(a, b)$ extends to any set of numbers if and only if all numbers in the set are pairwise coprime (or in the context of subarrays, when no prime factors overlap in a way that violates the equation). However, as we expand our subarray to the right, the product grows exponentially while GCD and LCM behave monotonically (GCD decreases or stays same, LCM increases or stays same). Because the product outgrows LCM $\times$ GCD very quickly, any overflow or mismatch terminates the valid subarray extension, allowing us to safely check all subarrays within acceptable constraints.

## Algorithm
1. Initialize `ans` to 0 to store the maximum length found so far, and get the length of the array `n`.
2. Iterate through every possible starting index `i` from `0` to `n - 1`.
3. For each starting index `i`, initialize `product = 1`, `lcm = 1`, and `gcd = 0` to track properties of the current subarray.
4. Iterate through every possible ending index `j` from `i` to `n - 1`.
5. Update `product` by multiplying it with `nums[j]`.
6. Update `gcd` using the Euclidean algorithm function `getGcd(gcd, nums[j])`.
7. Update `lcm` using the relation `(lcm / getGcd(lcm, nums[j])) * nums[j]` to prevent arithmetic overflow where possible.
8. Compare `product` with `lcm * gcd`. If they are equal, update `ans` with the maximum of `ans` and the current subarray length `j - i + 1`.
9. After checking all subarrays, return `ans`.

## Concept to Remember
- Number Theory: The fundamental relationship between product, LCM, and GCD of numbers.
- Subarray Enumeration: Exploring all $O(N^2)$ subarrays efficiently using running calculations.
- Euclidean Algorithm: Efficient $O(\log(\min(a, b)))$ method for computing GCD.
- Integer Overflow: Managing rapidly growing products in array problems.

## Common Mistakes
- Forgetting that integer overflow can happen very quickly when calculating the product of array elements.
- Initializing `gcd` to 1 instead of 0 for the first element comparison.
- Incorrectly computing LCM without dividing by the GCD first, leading to premature overflow.
- Confusing subsequence problems with contiguous subarray problems.

## Complexity Analysis
- Time: $O(N^2 \log M)$ - We use two nested loops to check all $O(N^2)$ subarrays, and inside the inner loop, computing the GCD takes logarithmic time with respect to the values.
- Space: $O(1)$ - Only a few variables are used to maintain the running product, GCD, LCM, and answer, requiring constant extra space.

## Commented Code
```java
class Solution {
    public int maxLength(int[] nums) {
        // Mathematical property: product of two numbers equals product of their LCM and GCD
        int ans = 0, n = nums.length; // Initialize maximum length and array size
        for(int i = 0; i < n; i++) { // Outer loop for the starting index of the subarray
            int product = 1;         // Reset running product for the new subarray start
            int lcm = 1;             // Reset running LCM for the new subarray start
            int gcd = 0;             // Reset running GCD for the new subarray start
            for(int j = i; j < n; j++) { // Inner loop for the ending index of the subarray
                product *= nums[j]; // Multiply current element into the running product
                gcd = getGcd(gcd, nums[j]); // Update running GCD with the new element
                lcm = (lcm / getGcd(lcm, nums[j])) * nums[j]; // Update running LCM safely avoiding overflow
                if(product == lcm * gcd) ans = Math.max(ans, j - i + 1); // Check condition and update max length
            }
        }
        return ans; // Return the maximum length found
    }
    public int getGcd(int a, int b) { // Helper method to compute GCD
        if(b == 0) return a;         // Base case: if second number is 0, return first
        return getGcd(b, a % b);     // Recursive step using Euclidean algorithm
    }
}
```

## Interview Tips
- Mention upfront that while the mathematical identity holds, integer overflow is the primary practical constraint in standard integer types.
- Clearly explain the running calculation approach to avoid recomputing GCD and LCM from scratch for every subarray.
- Be prepared to discuss optimization techniques or constraints if array elements are large.

## Revision Checklist
- [ ] Understand the relation between product, LCM, and GCD
- [ ] Implement the Euclidean GCD algorithm correctly from memory
- [ ] Handle edge cases like single-element subarrays or arrays with zeros/ones
- [ ] Analyze time and space complexity clearly

## Similar Problems
- Maximum Product Subarray
- Subarray Product Less Than K
- Greatest Common Divisor of Strings

## Tags
`Array` `Math` `Number Theory` `Simulation`
