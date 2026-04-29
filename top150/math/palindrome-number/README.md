# Palindrome Number

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math`  
**Time:** O(log10(x)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean isPalindrome(int x) { 
        if(x<0) return false;
        int sum =0;
        int copy = x;
        while(x>0){
            int r = x%10;
            sum = sum*10 + r;
            x=x/10;
        }
        return sum==copy;
    }
}

// class Solution {
//     public boolean isPalindrome(int x) {
//         if(x<0) return false;
//         int maxPower = (int)Math.log10(x);
//         int left = maxPower;
//         int right = 0;
//         while(left > right){
            
//             int firstDigit = (x/(int)Math.pow(10,left))%10;
//             int lastDigit = (x/(int)Math.pow(10,right))%10;
           
//             if(firstDigit != lastDigit) return false;
//             left--;
//             right++;
//         }
//         return true;
  
//     }
// }
```

---

---
## Quick Revision
Given an integer, determine if it is a palindrome.
Reverse the integer and compare it with the original.

## Intuition
The core idea of a palindrome is that it reads the same forwards and backward. For a number, this means its digits should be the same when read from left to right as they are from right to left. A straightforward way to check this is to reverse the number and see if the reversed version matches the original.

## Algorithm
1. Handle negative numbers: If the input number `x` is negative, it cannot be a palindrome, so return `false`.
2. Store the original number: Create a copy of `x` (e.g., `copy = x`) because `x` will be modified during the reversal process.
3. Reverse the number:
    a. Initialize a variable `sum` to 0. This will store the reversed number.
    b. While `x` is greater than 0:
        i. Get the last digit of `x` using the modulo operator: `r = x % 10`.
        ii. Append this digit to `sum` by multiplying `sum` by 10 and adding `r`: `sum = sum * 10 + r`.
        iii. Remove the last digit from `x` by integer division: `x = x / 10`.
4. Compare: After the loop, compare the reversed number (`sum`) with the original number (`copy`). If they are equal, return `true`; otherwise, return `false`.

## Concept to Remember
*   **Integer Manipulation:** Understanding how to extract digits using modulo (`%`) and remove digits using integer division (`/`).
*   **Reversal Logic:** The technique of building a reversed number by iteratively multiplying by 10 and adding the next digit.
*   **Edge Cases:** Recognizing and handling special cases like negative numbers.

## Common Mistakes
*   **Modifying the original number without a copy:** If you don't store the original number, you'll lose it when you start reversing.
*   **Integer Overflow:** For very large numbers, reversing might exceed the maximum value of an `int`. (Though for this specific problem, the constraints usually prevent this, it's a good general consideration).
*   **Incorrectly handling single-digit numbers:** The reversal logic should naturally handle single-digit numbers correctly, but it's worth double-checking.
*   **Not handling negative numbers:** Negative numbers are explicitly not palindromes by definition in this context.

## Complexity Analysis
- Time: O(log10(x)) - The number of operations is proportional to the number of digits in `x`, which is logarithmic with respect to the value of `x`.
- Space: O(1) - We only use a few extra variables to store the copy and the reversed number, which is constant space.

## Commented Code
```java
class Solution {
    public boolean isPalindrome(int x) {
        // If the number is negative, it cannot be a palindrome.
        if(x < 0) return false;
        
        // Initialize a variable to store the reversed number.
        int sum = 0;
        
        // Store the original number to compare later, as 'x' will be modified.
        int copy = x;
        
        // Loop as long as there are digits left in 'x'.
        while(x > 0){
            // Get the last digit of 'x'.
            int r = x % 10;
            
            // Build the reversed number: shift existing digits left and add the new last digit.
            sum = sum * 10 + r;
            
            // Remove the last digit from 'x'.
            x = x / 10;
        }
        
        // Compare the reversed number with the original number.
        return sum == copy;
    }
}
```

## Interview Tips
*   **Clarify constraints:** Ask about the range of input integers. This helps identify potential overflow issues.
*   **Explain your approach:** Clearly articulate the "reverse and compare" strategy before coding.
*   **Discuss edge cases:** Mention how you'll handle negative numbers and zero.
*   **Consider alternative approaches:** Briefly mention the two-pointer approach (comparing first and last digits) as a way to avoid potential overflow if reversing the whole number was a concern.

## Revision Checklist
- [ ] Understand the definition of a palindrome number.
- [ ] Implement logic to reverse an integer.
- [ ] Handle negative input numbers.
- [ ] Compare the reversed number with the original.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Reverse Integer
*   Valid Palindrome (for strings)

## Tags
`Math` `Two Pointers`
