# Mirror Distance Of An Integer

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math`  
**Time:** O(log10(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int mirrorDistance(int n) {
        return Math.abs(n-reverse(n));
    }
    public int reverse(int n){
        int digits = (int)Math.log10(n)+1;
        int ans = 0;
        while(digits>0){
            ans += (n%10)*Math.pow(10,--digits);
            n /= 10;
        }
        return ans;
    }        
}
```

---

---
## Quick Revision
Given an integer, find the absolute difference between it and its reversed version.
The solution involves reversing the integer and then calculating the absolute difference.

## Intuition
The problem asks for the "mirror distance," which intuitively means how far an integer is from its reflection. The reflection of an integer is simply its digits in reverse order. So, the core task is to reverse the integer and then find the difference. The absolute difference ensures we get a non-negative distance.

## Algorithm
1. Define a helper function `reverse(int n)` that takes an integer `n` and returns its reversed version.
2. Inside `reverse(int n)`:
    a. Initialize a variable `ans` to 0, which will store the reversed number.
    b. Iterate while `n` is greater than 0.
    c. In each iteration, extract the last digit of `n` using the modulo operator (`n % 10`).
    d. Append this digit to `ans` by multiplying `ans` by 10 and adding the extracted digit.
    e. Remove the last digit from `n` by integer division (`n /= 10`).
    f. Return `ans`.
3. In the main function `mirrorDistance(int n)`:
    a. Call the `reverse(n)` helper function to get the reversed integer.
    b. Calculate the absolute difference between the original integer `n` and its reversed version using `Math.abs()`.
    c. Return the absolute difference.

## Concept to Remember
*   **Integer Manipulation:** Understanding how to extract digits (modulo) and build new numbers (multiplication and addition).
*   **Mathematical Functions:** Familiarity with `Math.abs()` for absolute values.
*   **Helper Functions:** The utility of breaking down a problem into smaller, manageable sub-problems.

## Common Mistakes
*   **Integer Overflow:** For very large numbers, reversing them might exceed the maximum integer limit. The provided solution doesn't explicitly handle this, but it's a consideration for larger constraints.
*   **Leading Zeros:** When reversing, leading zeros in the original number (e.g., 120 reversed is 21, not 021) are naturally handled by integer representation. However, if the reversed number ends up with leading zeros (e.g., 100 reversed is 1), this is correct. The issue might arise if one *expects* leading zeros in the output.
*   **Off-by-One Errors:** Incorrectly calculating the number of digits or loop conditions can lead to incorrect reversal.
*   **Forgetting `Math.abs()`:** Returning a negative difference when the reversed number is larger than the original.

## Complexity Analysis
- Time: O(log10(n)) - The `reverse` function iterates through the digits of `n`. The number of digits in `n` is proportional to log10(n). The `mirrorDistance` function performs a constant number of operations after the reversal.
- Space: O(1) - The algorithm uses a constant amount of extra space for variables like `ans` and `digits`, regardless of the input size.

## Commented Code
```java
class Solution {
    // The main function to calculate the mirror distance.
    public int mirrorDistance(int n) {
        // Calculate the absolute difference between the original number and its reversed version.
        return Math.abs(n - reverse(n));
    }

    // Helper function to reverse an integer.
    public int reverse(int n) {
        // Calculate the number of digits in n. This is used to correctly place digits in the reversed number.
        // For example, if n=123, digits will be 3.
        // Note: This approach might have issues with n=0 or negative numbers if not handled.
        // The problem statement implies positive integers.
        int digits = (int) Math.log10(n) + 1; // This line is problematic for n=0. A better approach would be to handle n=0 separately or use a loop.
        
        // Initialize the variable to store the reversed number.
        int ans = 0;
        
        // Loop as long as there are digits to process.
        while (digits > 0) {
            // Get the last digit of n.
            int lastDigit = n % 10;
            
            // Append the last digit to ans.
            // Math.pow(10, --digits) calculates 10 raised to the power of the remaining number of digits.
            // For example, if digits was 3 and we are processing the first digit (from right), --digits becomes 2, so we multiply by 10^2.
            ans += lastDigit * (int) Math.pow(10, --digits);
            
            // Remove the last digit from n.
            n /= 10;
        }
        // Return the reversed number.
        return ans;
    }
}
```
*Self-correction on the provided code's `reverse` function:* The provided `reverse` function uses `Math.log10(n) + 1` to determine the number of digits. This approach has a critical flaw: `Math.log10(0)` is negative infinity, and `Math.log10(n)` for `n < 1` is also problematic. For `n=0`, `Math.log10(0)` would result in an error or `NaN`. A more robust way to reverse an integer without relying on `log10` and `pow` would be:
```java
    public int reverseRobust(int n) {
        int reversedNum = 0;
        while (n != 0) {
            int digit = n % 10;
            reversedNum = reversedNum * 10 + digit;
            n /= 10;
        }
        return reversedNum;
    }
```
This robust version avoids `log10` and `pow`, making it simpler and safer. The original solution's `reverse` function would fail for `n=0`. Assuming `n` is a positive integer as per typical LeetCode problem constraints for this type of question, the `log10` approach might pass some test cases but is not generally robust.

## Interview Tips
1.  **Clarify Constraints:** Ask about the range of `n`. Is it always positive? Can it be zero? This helps identify potential edge cases like integer overflow or the behavior of `log10(0)`.
2.  **Explain the Reversal Logic:** Clearly articulate how you extract digits and reconstruct the reversed number. Discuss the role of modulo and division.
3.  **Discuss Edge Cases:** Mention how you would handle `n=0` (its reverse is 0, distance is 0) and potentially negative numbers if they were allowed (though the problem implies positive integers).
4.  **Consider Alternatives:** Briefly mention that while `log10` and `pow` work, a simpler iterative approach for reversal (as shown in the self-correction) is often preferred for its clarity and robustness.

## Revision Checklist
- [ ] Understand the problem: calculate the absolute difference between an integer and its reverse.
- [ ] Implement an integer reversal function.
- [ ] Handle potential edge cases for reversal (e.g., 0).
- [ ] Use `Math.abs()` for the final difference.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Palindrome Number
*   Reverse Integer
*   Number of Steps to Reduce a Number in Binary Representation to One

## Tags
`Math` `Integer`
