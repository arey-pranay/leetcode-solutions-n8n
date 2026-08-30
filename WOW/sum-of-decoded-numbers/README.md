# Sum Of Decoded Numbers

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math` `Binary Exponentiation` `Number Theory` `Long`  
**Time:** O(N * log(M)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int sumDecoded(long[] nums) {
        long sum = 0;
        long mod = 1000000007;
        for(int i =0 ; i<nums.length;i++){
            long width = nums[i]%10;
            long d = (long)Math.floor(nums[i]/10);
            long len = String.valueOf(d).length();
            long x = d/(long)(Math.pow(10,len-width));
            long y = d%(long)(Math.pow(10,len-width));
            long temp = binExp(x, y, mod);
            sum += temp;
        }
        return (int)(sum%mod);    
    }
    private long binExp(long x, long y, long mod){
        long ans = 1;
        while(y>0){
            if((y&1)==1) ans = (ans*x)%mod;//4
            x = (x*x)%mod;
            y >>= 1;
        }
        return ans;
    }
     // x^21 => x^20 => x^20 = (x^2)^10
        // 7
        // 6
        // 3
        // 4^5
        // 5 = 10    
}
```

---

---
## Quick Revision
The problem asks to calculate a sum based on decoding numbers from an array. Each number is decoded by splitting it into two parts, `x` and `y`, and then computing `x` raised to the power of `y` modulo 10^9 + 7.

## Intuition
The core idea is to process each number in the input array `nums` individually. For each `num`, we need to extract two components: a base `x` and an exponent `y`. The problem statement implies that the last digit of `num` represents the exponent `y`, and the remaining digits form a number from which the base `x` is derived. The derivation of `x` involves understanding how many digits from the remaining part should be considered. Specifically, if the remaining part has `len` digits and the exponent is `width`, then `x` is the most significant `len - width` digits, and `y` is the least significant `width` digits. However, the provided solution interprets `width` as the number of digits to *keep* for the exponent `y`, and `len - width` as the number of digits to form the base `x`. This is a crucial detail. After obtaining `x` and `y`, we need to compute `x^y` efficiently modulo a large prime. The `binExp` function (binary exponentiation or exponentiation by squaring) is the standard and most efficient way to do this. Finally, we sum up all these computed powers modulo 10^9 + 7.

## Algorithm
1. Initialize `sum` to 0 and `mod` to 1000000007.
2. Iterate through each `num` in the input array `nums`.
3. For the current `num`:
    a. Extract the last digit as `width` (which represents the exponent `y`). This is `num % 10`.
    b. Extract the remaining digits by integer division: `d = num / 10`.
    c. Determine the number of digits in `d`. Let this be `len`. This can be done by converting `d` to a string and getting its length.
    d. Calculate the divisor to split `d` into `x` and `y`. This divisor is `10^(len - width)`.
    e. Extract the base `x`: `x = d / (10^(len - width))`.
    f. Extract the exponent `y`: `y = d % (10^(len - width))`.
    g. Compute `x^y` modulo `mod` using the `binExp` function.
    h. Add the result of `binExp` to `sum`, taking the modulo `mod`.
4. After iterating through all numbers, return `sum % mod` cast to an integer.

The `binExp(x, y, mod)` function:
1. Initialize `ans` to 1.
2. While `y` is greater than 0:
    a. If the last bit of `y` is 1 (i.e., `y & 1 == 1`), update `ans = (ans * x) % mod`.
    b. Square `x`: `x = (x * x) % mod`.
    c. Right shift `y` by 1: `y >>= 1`.
3. Return `ans`.

## Concept to Remember
*   **Modular Arithmetic:** Performing calculations modulo a large prime to prevent integer overflow and keep results within a manageable range.
*   **Exponentiation by Squaring (Binary Exponentiation):** An efficient algorithm to compute `a^b mod m` in O(log b) time.
*   **Number Manipulation:** Extracting digits and splitting numbers based on their place value.
*   **Data Type Considerations:** Using `long` for intermediate calculations to avoid overflow, especially with large numbers and modulo operations.

## Common Mistakes
*   **Integer Overflow:** Not using `long` for intermediate calculations, especially when multiplying large numbers or during exponentiation.
*   **Incorrect Digit Extraction/Splitting:** Misinterpreting how `width` and `d` are used to derive `x` and `y`. The logic `d/(long)(Math.pow(10,len-width))` and `d%(long)(Math.pow(10,len-width))` is specific and needs careful understanding.
*   **Inefficient Exponentiation:** Using a naive loop for `x^y` which would be O(y) and too slow for large exponents.
*   **Modulo Operations:** Forgetting to apply the modulo operator at each step of multiplication in `binExp` and when accumulating the `sum`.
*   **Floating-Point Precision Issues:** Using `Math.pow` with integer types can sometimes lead to precision issues if not handled carefully, though casting to `long` here mitigates some of it.

## Complexity Analysis
*   **Time:** O(N * log(M)), where N is the number of elements in `nums` and M is the maximum value of the exponent `y` derived from the numbers. The `log(M)` factor comes from the `binExp` function. The operations to extract digits and calculate `len` are roughly proportional to the number of digits in `nums[i]`, which is logarithmic with respect to `nums[i]`.
*   **Space:** O(1), as we are only using a few variables to store intermediate results. The space used by `String.valueOf(d).length()` is negligible and considered constant for practical purposes.

## Commented Code
```java
class Solution {
    public int sumDecoded(long[] nums) {
        long sum = 0; // Initialize the total sum to 0.
        long mod = 1000000007; // Define the modulo value as per problem constraints.
        
        // Iterate through each number in the input array.
        for(int i = 0 ; i < nums.length; i++){
            // Extract the last digit, which represents the width of the exponent part.
            long width = nums[i] % 10; 
            // Extract the remaining digits by removing the last digit.
            long d = (long)Math.floor(nums[i] / 10); 
            // Calculate the total number of digits in the remaining part 'd'.
            long len = String.valueOf(d).length(); 
            
            // Calculate the divisor to split 'd' into base 'x' and exponent 'y'.
            // This divisor is 10 raised to the power of (total digits - width).
            // For example, if d=12345 and width=2, len=5, then len-width=3.
            // We need 10^3 = 1000 to split.
            long divisor = (long)Math.pow(10, len - width);
            
            // Extract the base 'x' by dividing 'd' by the calculated divisor.
            // This takes the most significant (len - width) digits.
            long x = d / divisor; 
            // Extract the exponent 'y' by taking the remainder of 'd' divided by the divisor.
            // This takes the least significant 'width' digits.
            long y = d % divisor; 
            
            // Compute x raised to the power of y, modulo mod, using binary exponentiation.
            long temp = binExp(x, y, mod); 
            // Add the computed power to the total sum, applying modulo at each addition.
            sum = (sum + temp) % mod; 
        }
        // Return the final sum, cast to an integer as required by the return type.
        return (int)(sum % mod);    
    }

    // Helper function for binary exponentiation (exponentiation by squaring).
    // Computes (x^y) % mod efficiently.
    private long binExp(long x, long y, long mod){
        long ans = 1; // Initialize the answer to 1.
        // Loop while the exponent 'y' is greater than 0.
        while(y > 0){
            // If the last bit of 'y' is 1 (i.e., y is odd), multiply 'ans' by 'x' (modulo mod).
            if((y & 1) == 1) {
                ans = (ans * x) % mod;
            }
            // Square 'x' (modulo mod) for the next iteration.
            x = (x * x) % mod;
            // Right shift 'y' by 1, effectively dividing it by 2.
            y >>= 1;
        }
        // Return the final computed result.
        return ans;
    }
}
```

## Interview Tips
1.  **Clarify the Decoding Logic:** Before coding, ensure you fully understand how each number in `nums` is split into `x` and `y`. Ask for an example if needed. The interpretation of `width` is critical.
2.  **Explain Binary Exponentiation:** Be prepared to explain why binary exponentiation is used and how it works. Its time complexity is a key selling point.
3.  **Handle Modulo Correctly:** Emphasize that modulo operations must be applied at *every* multiplication step to prevent overflow, both in `binExp` and when accumulating the `sum`.
4.  **Discuss Edge Cases:** Consider cases like `nums[i]` being a single digit (though the problem structure implies at least two), or `d` being 0. How does `String.valueOf(0).length()` behave? (It's 1). How does `Math.pow(10, 0)` behave? (It's 1).

## Revision Checklist
- [ ] Understand the problem statement and decoding logic.
- [ ] Implement digit extraction and number splitting correctly.
- [ ] Implement binary exponentiation (`binExp`) correctly.
- [ ] Apply modulo operations at all necessary steps.
- [ ] Use `long` data type for intermediate calculations.
- [ ] Test with example cases and edge cases.

## Similar Problems
*   [Exponentiation by Squaring (LeetCode 50 - Pow(x, n) is a simpler version, but the concept is the same)](https://leetcode.com/problems/powx-n/)
*   [Modular Arithmetic problems involving large numbers and powers](https://leetcode.com/tag/math/)

## Tags
`Math` `Binary Exponentiation` `Number Theory` `Long`
