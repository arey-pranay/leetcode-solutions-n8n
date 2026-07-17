# String To Integer Atoi

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int myAtoi(String s) {
        int sign = 1;
        int ans = 0;
        int i=0;
        s = s.trim(); if(s.isEmpty()) return 0;
        if(s.charAt(i) == '+' || s.charAt(i)=='-'){
            if(s.charAt(i)=='-') sign = -1;
            i++;
        }    
        while(i<s.length() && (s.charAt(i)>='0' && s.charAt(i)<='9')){
            // 2,147,483,647 aakhir ki value 7 hai na
            if(ans > Integer.MAX_VALUE/10 || (ans==Integer.MAX_VALUE/10 && s.charAt(i)-'0'>7)){
                if(sign == -1) return Integer.MIN_VALUE;
                else return Integer.MAX_VALUE;
            }
            char c = s.charAt(i);
            ans *= 10;
            int digit = c-'0';
            ans += digit;
            i++;
        }
        return ans*sign;
    }
}
```

---

---
## Quick Revision
Convert a string to an integer, handling signs and overflow.

Solve this problem by parsing the input string from left to right, checking for signs, digits, and overflow conditions.

## Intuition
The key insight is that we can iterate through the input string once, keeping track of the current sign and the running total of the digit values. We also need to check for overflow conditions, as the result must fit within the range of a 32-bit integer.

## Algorithm
1. Trim the input string to remove leading whitespace.
2. Check if the first character is a '+' or '-' sign; update the sign variable accordingly and increment the index.
3. While the current character is a digit, multiply the running total by 10, add the new digit value, and increment the index.
4. If an overflow condition is detected (i.e., the running total exceeds `Integer.MAX_VALUE/10` or is equal to it and the next digit is greater than 7), return either `Integer.MIN_VALUE` or `Integer.MAX_VALUE` depending on the sign.
5. Return the final result multiplied by the sign.

## Concept to Remember
* Handling signs and overflow conditions in numerical parsing
* Iterative string processing
* Use of character codes for digits (e.g., `c-'0'`)

## Common Mistakes
* Failing to handle leading whitespace correctly
* Not checking for overflow conditions promptly enough
* Misinterpreting the sign or digit values

## Complexity Analysis
- Time: O(n) - we iterate through the input string once
- Space: O(1) - we use a constant amount of extra memory

## Commented Code
```java
class Solution {
    public int myAtoi(String s) {
        // Trim leading whitespace
        s = s.trim();
        
        // Initialize sign and running total
        int sign = 1;
        int ans = 0;
        int i=0;

        // Check for signs
        if(s.charAt(i) == '+' || s.charAt(i)=='-'){
            if(s.charAt(i)=='-') sign = -1;
            i++;
        }
        
        // Parse digits while checking for overflow
        while(i<s.length() && (s.charAt(i)>='0' && s.charAt(i)<='9')){
            char c = s.charAt(i);
            ans *= 10; // Multiply by 10 to shift the digit value left
            int digit = c-'0'; // Convert character code back to digit value
            ans += digit;
            
            // Check for overflow conditions
            if(ans > Integer.MAX_VALUE/10 || (ans==Integer.MAX_VALUE/10 && s.charAt(i)-'0'>7)){
                if(sign == -1) return Integer.MIN_VALUE; // Return min or max value depending on sign
                else return Integer.MAX_VALUE;
            }
            i++;
        }
        
        // Return final result multiplied by sign
        return ans*sign;
    }
}
```

## Interview Tips
* Be mindful of edge cases, such as leading whitespace and overflow conditions.
* Use a clear and concise algorithm that iterates through the input string once.
* Practice handling signs and digit values correctly.

## Revision Checklist
- [ ] Trim leading whitespace from input string
- [ ] Handle '+' or '-' signs correctly
- [ ] Check for overflow conditions promptly
- [ ] Return correct results for min and max integer values

## Similar Problems
* `Integer.parseInt()` method in Java (similar but not identical)
* 8. String to Integer (`atoi`) on LeetCode (C version)
