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

## Concept to Remember
*   Binary Representation: Understanding how integers are represented in base-2.
*   String Manipulation: Ability to convert between number types and strings, and perform character replacements.
*   Base Conversion: Converting a binary string back to a decimal integer.

## Common Mistakes
*   Forgetting to handle leading zeros: The `Integer.toBinaryString()` method doesn't produce leading zeros, which is fine for this problem as we are only concerned with the significant bits.
*   Incorrectly implementing the bit flipping logic: Simple character replacement errors can lead to wrong results.
*   Not specifying the radix (base) when parsing the binary string back to an integer.

## Complexity Analysis
- Time: O(L) - where L is the number of bits in the binary representation of `n`. This is because converting to binary, string replacements, and parsing back all take time proportional to the length of the binary string.
- Space: O(L) - for storing the binary string representation of `n`.

## Commented Code
```java
class Solution {
    public int bitwiseComplement(int n) {
        // Convert the integer n to its binary string representation.
        String s = Integer.toBinaryString(n);
        
        // Replace all '0' characters with a temporary placeholder '-'.
        // This is done to avoid issues when replacing '1' with '0' next.
        s = s.replace('0','-');
        
        // Replace all '1' characters with '0'.
        s = s.replace('1','0');
        
        // Replace all temporary placeholders '-' (which were originally '0') with '1'.
        s = s.replace('-','1');
        
        // Parse the modified binary string back into an integer, specifying base 2.
        return Integer.parseInt(s,2);
    }
}
```

## Interview Tips
*   Explain your thought process clearly, starting with the binary representation.
*   Discuss alternative approaches, like using bitwise operators directly (e.g., XOR with a mask of all ones).
*   Be prepared to explain the time and space complexity of your chosen solution.
*   Ask clarifying questions if the problem statement is ambiguous.

## Revision Checklist
- [ ] Understand the concept of bitwise complement.
- [ ] Know how to convert an integer to its binary string.
- [ ] Be proficient in string manipulation for character replacement.
- [ ] Know how to parse a binary string back to an integer.
- [ ] Analyze time and space complexity.

## Similar Problems
- LeetCode 476: Number Complement
- LeetCode 1009: Complement of Base 10 Integer (This is the same problem, often presented with slightly different wording)

## Tags
`String` `Bit Manipulation` `Math`

## My Notes
Not the most optimal one
