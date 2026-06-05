# Bitwise And Of Numbers Range

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Bit Manipulation`  
**Time:** O(log N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while (right > left) right = right & (right - 1);
        return right;
    }
}

// class Solution {
//     public int rangeBitwiseAnd(int left, int right) {
//         int cnt = 0;
//         while (left != right) {
//             left >>= 1;
//             right >>= 1;
//             cnt++;
//         }
//         return (left << cnt);
//     }
// }


// Brian Kernighan trick
// right isliye kyonki right se and p bada difference m no. gyb hoga , lekin left kyonki chhoti value hai to bada difference m numbero ka gyb hona ho ni paayge , paryapt ni gyb honge 
// ab gyb kyon kese ho rhe hai - > aur unke hone naa hona equal kese hai ?? -> kyonki 2 no. pados k & ho rhe hai , unse jo 1's 0 ban gye to ab iske sath kuchh bhi kabhi 1 ni hoga 
// 2 pados k hi kyon le rhe hum ? -> becuase It removes the lowest set bit (rightmost 1) from right.

//right contains all the possible 1s that might not survive
```

---

---
## Quick Revision
This problem asks for the bitwise AND of all numbers in a given range [left, right].
The solution involves finding the common prefix of the binary representations of `left` and `right`.

## Intuition
The bitwise AND operation can only turn bits from 1 to 0. A bit will remain 1 in the final result only if it is 1 in *all* numbers within the range.
Consider the binary representations of `left` and `right`. If `left` and `right` have different most significant bits, then somewhere in between, there must be a transition from a number starting with `0...` to a number starting with `1...`. This means the most significant bit will be 0 in the final AND result.
This logic extends to all bits. If `left` and `right` differ at a certain bit position, then there must be at least one number in the range where that bit is 0 and at least one where it is 1 (or a transition occurs). Therefore, that bit will become 0 in the final AND.
The bits that *will* remain 1 in the final result are precisely the common prefix of the binary representations of `left` and `right`. All bits after the first differing bit (from MSB) will eventually become 0.

## Algorithm
1. Initialize a counter `shift_count` to 0.
2. While `left` is not equal to `right`:
   a. Right shift `left` by 1.
   b. Right shift `right` by 1.
   c. Increment `shift_count`.
3. Once `left` equals `right`, this common value represents the common prefix of the original `left` and `right`.
4. Left shift this common value by `shift_count` to restore its original magnitude, effectively padding the common prefix with zeros.

## Concept to Remember
*   **Bitwise AND Properties**: The AND operation can only turn bits from 1 to 0. A bit is 1 in the result only if it's 1 in all operands.
*   **Binary Representation and Ranges**: Understanding how bit patterns change across a range of numbers is crucial.
*   **Common Prefix**: The bitwise AND of a range of numbers is determined by their common most significant bits.

## Common Mistakes
*   **Brute-force Iteration**: Trying to iterate through every number from `left` to `right` and performing the AND operation. This will lead to Time Limit Exceeded for large ranges.
*   **Misunderstanding Bitwise AND**: Not realizing that a bit must be 1 in *all* numbers to survive the AND operation.
*   **Incorrectly Handling Bit Shifts**: Errors in shifting bits or restoring the final result can lead to wrong answers.
*   **Not Identifying the Common Prefix**: Failing to recognize that the problem boils down to finding the common binary prefix.

## Complexity Analysis
*   **Time**: O(log N), where N is the maximum value of `right`. This is because in each iteration of the `while` loop, we are effectively removing one bit from the right by right-shifting. The number of bits in an integer is logarithmic to its value.
*   **Space**: O(1). The algorithm uses a constant amount of extra space for variables like `shift_count`.

## Commented Code
```java
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        // Initialize a counter to track the number of right shifts performed.
        // This counter will be used to restore the magnitude of the common prefix later.
        int shift_count = 0;

        // Continue as long as 'left' and 'right' are different.
        // This loop aims to find the common most significant bits (MSBs) of 'left' and 'right'.
        while (left != right) {
            // Right shift 'left' by 1 bit. This effectively removes the least significant bit (LSB).
            // We do this to align the bits of 'left' and 'right' from the MSB side.
            left >>= 1;
            // Right shift 'right' by 1 bit.
            right >>= 1;
            // Increment the shift_count. Each shift corresponds to one bit position we've processed.
            shift_count++;
        }

        // At this point, 'left' (or 'right', since they are equal) holds the common prefix
        // of the original 'left' and 'right' numbers. All bits that differed have been
        // shifted out.

        // Left shift the common prefix ('left') by 'shift_count' bits.
        // This restores the magnitude of the common prefix by padding it with zeros
        // on the right, which is the correct result for the bitwise AND of the range.
        return (left << shift_count);
    }
}
```

## Interview Tips
*   **Explain the "Why"**: Clearly articulate why the bitwise AND of a range is determined by the common prefix of the boundary numbers.
*   **Handle Edge Cases**: Discuss what happens if `left == right` (the loop won't run, and it correctly returns `left`).
*   **Alternative Approach (Brian Kernighan's)**: Mention the alternative approach using `right = right & (right - 1)` and explain its logic (repeatedly turning off the rightmost set bit until `right` becomes less than or equal to `left`).
*   **Complexity Justification**: Be ready to explain the logarithmic time complexity due to bit shifts.

## Revision Checklist
- [ ] Understand the property of bitwise AND in a range.
- [ ] Identify the common prefix as the key.
- [ ] Implement the bit shifting algorithm correctly.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition.

## Similar Problems
*   Single Number III
*   Power of Two
*   Number of 1 Bits
*   Hamming Distance

## Tags
`Bit Manipulation` `Math`
