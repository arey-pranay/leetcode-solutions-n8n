# Complement Of Base 10 Integer

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Bit Manipulation`  
**Time:** O(L)  
**Space:** O(L)

---

## Solution (java)

```java
class Solution {
    public int bitwiseComplement(int n) {
        String s = Integer.toBinaryString(n);
        s = s.replace('0','-');
        s = s.replace('1','0');
        s = s.replace('-','1');
        return Integer.parseInt(s,2);
    }
}
```

---

---
## Quick Revision
Given a base-10 integer, find its bitwise complement.
Convert to binary, flip bits, then convert back to decimal.

## Intuition
The problem asks for the bitwise complement of a number. The most straightforward way to think about bitwise operations is in their binary representation. If we can convert the number to its binary string, we can then easily flip each bit (0 to 1, and 1 to 0). Finally, we convert this modified binary string back to a base-10 integer.

## Algorithm
1. Convert the input integer `n` into its binary string representation.
2. Iterate through the binary string. For each character:
    a. If it's '0', replace it with '1'.
    b. If it's '1', replace it with '0'.
3. Convert the modified binary string back into an integer, interpreting it as a base-2 number.
4. Return the resulting integer.

## Concept to Remember
*   Binary Representation: Understanding how integers are represented in base-2.
*   String Manipulation: Proficiency in converting between numbers and strings, and modifying strings.
*   Base Conversion: Ability to parse a string as a number in a specific base (e.g., base-2).

## Common Mistakes
*   Forgetting to handle leading zeros: The `Integer.toBinaryString()` method doesn't produce leading zeros, which is fine for this problem's logic as we're flipping the bits of the *actual* binary representation. However, if the problem implied a fixed bit-width, this would be an issue.
*   Incorrectly implementing the bit-flipping logic: Simple character replacements can be error-prone if not done carefully.
*   Not specifying the radix (base) when parsing the binary string back to an integer.

## Complexity Analysis
- Time: O(L) - where L is the number of bits in the binary representation of `n`. This is because converting to binary, manipulating the string, and converting back all take time proportional to the length of the binary string.
- Space: O(L) - for storing the binary string representation of `n`.

## Commented Code
```java
class Solution {
    public int bitwiseComplement(int n) {
        // Convert the integer 'n' into its binary string representation.
        String s = Integer.toBinaryString(n);
        
        // Replace all '0's with a temporary placeholder character '-'.
        // This is done to avoid replacing '1's with '0's and then immediately
        // replacing those new '0's back to '1's in a single pass if we did
        // '0' -> '1' and '1' -> '0' directly.
        s = s.replace('0','-');
        
        // Now, replace all original '1's with '0's.
        s = s.replace('1','0');
        
        // Finally, replace the temporary placeholder '-' (which were original '0's) with '1's.
        s = s.replace('-','1');
        
        // Parse the modified binary string 's' back into an integer,
        // specifying that the string is in base-2.
        return Integer.parseInt(s,2);
    }
}
```

## Interview Tips
*   Explain your thought process clearly, starting with the binary representation.
*   Ask clarifying questions about edge cases, like `n=0`. (The provided solution handles `n=0` correctly, returning `1`).
*   Discuss alternative approaches, such as using bitwise operators directly if the problem constraints allowed for a fixed bit width or if you wanted to avoid string conversions.
*   Be prepared to explain why the string replacement method works and its limitations.

## Revision Checklist
- [ ] Understand the definition of a bitwise complement.
- [ ] Practice converting between decimal and binary.
- [ ] Be comfortable with string manipulation in Java.
- [ ] Know how to use `Integer.toBinaryString()` and `Integer.parseInt(String s, int radix)`.

## Similar Problems
*   Number of 1 Bits
*   Reverse Bits
*   Hamming Distance

## Tags
`String` `Bit Manipulation`

## My Notes
Not the most optimal one
