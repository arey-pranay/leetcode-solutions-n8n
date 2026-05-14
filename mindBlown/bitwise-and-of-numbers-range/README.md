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
        while (right > left) {
            right = right & (right - 1);
        }
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
The bitwise AND operation can only turn bits from 1 to 0. If a bit position has a 0 in *any* number within the range, that bit will be 0 in the final result.
Consider the binary representations of `left` and `right`. If `left` and `right` have different most significant bits (MSBs), it means there's at least one number in between that flips this MSB from 0 to 1 (or vice versa). This implies that all bits to the right of this differing MSB will eventually become 0 in the AND operation across the range.
Therefore, the result of the bitwise AND of all numbers in the range [left, right] will be the common prefix of the binary representations of `left` and `right`, followed by all zeros.

## Algorithm
1. Initialize `shift_count` to 0.
2. While `left` is not equal to `right`:
   a. Right-shift `left` by 1.
   b. Right-shift `right` by 1.
   c. Increment `shift_count`.
3. Once `left` equals `right`, this common value represents the common prefix.
4. Left-shift this common value by `shift_count` to restore the zeros that were shifted out. This gives the final result.

## Concept to Remember
*   **Bitwise AND Properties:** The AND operation can only turn bits from 1 to 0. A bit is 1 in the result only if it's 1 in *all* operands.
*   **Binary Representation and Range:** Understanding how numbers change in binary within a range is crucial. Differences in higher-order bits imply that intermediate numbers will flip those bits, leading to zeros in the final AND.
*   **Bit Manipulation Tricks:** Techniques like right-shifting to find common prefixes and left-shifting to restore them are efficient.

## Common Mistakes
*   **Brute-force iteration:** Iterating through every number from `left` to `right` and performing the AND operation is too slow for large ranges.
*   **Incorrectly handling the common prefix:** Not realizing that the common prefix is the key and trying to find a more complex pattern.
*   **Off-by-one errors in shifting:** Miscalculating the number of shifts needed to align `left` and `right`.
*   **Not understanding the effect of `right & (right - 1)`:** This trick efficiently removes the lowest set bit, which is useful for finding the common prefix by repeatedly clearing the differing bits from the right.

## Complexity Analysis
*   **Time:** O(log N), where N is the maximum value of `right`. The number of shifts is proportional to the number of bits in the integers, which is logarithmic with respect to the value of the numbers.
*   **Space:** O(1). The algorithm uses a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        // Initialize a counter to keep track of how many bits we've shifted.
        int shift_count = 0;
        // Loop as long as 'left' and 'right' are not equal.
        // This loop continues until we find the common most significant bits.
        while (left != right) {
            // Right-shift 'left' by 1 bit. This effectively removes the least significant bit.
            left >>= 1;
            // Right-shift 'right' by 1 bit. This also removes the least significant bit.
            right >>= 1;
            // Increment the shift counter. This tells us how many bits were removed from the right.
            shift_count++;
        }
        // At this point, 'left' (or 'right', since they are equal) holds the common prefix of the original 'left' and 'right'.
        // We need to restore the trailing zeros that were shifted out.
        // Left-shift the common prefix by 'shift_count' bits.
        // This appends 'shift_count' zeros to the common prefix, forming the final result.
        return (left << shift_count);
    }
}

/*
// Alternative solution using Brian Kernighan's algorithm idea:
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        // The loop continues as long as 'right' is greater than 'left'.
        // The goal is to make 'right' equal to the common prefix by repeatedly clearing its lowest set bit.
        while (right > left) {
            // 'right & (right - 1)' clears the least significant set bit (the rightmost '1').
            // Example: if right = 12 (1100), right - 1 = 11 (1011). right & (right - 1) = 8 (1000).
            // This operation effectively removes a bit that is guaranteed to be 0 in at least one number in the range [left, right].
            right = right & (right - 1);
        }
        // When the loop terminates, 'right' will be equal to 'left' (or a value less than 'left' if the original 'left' was already the common prefix).
        // The value of 'right' at this point is the common prefix of the original 'left' and 'right', with all differing bits to the right cleared.
        return right;
    }
}
*/
```

## Interview Tips
*   **Explain the intuition first:** Clearly articulate why the common prefix is the key.
*   **Discuss brute-force and its limitations:** Show that you've considered simpler, less efficient solutions and understand why they fail.
*   **Walk through an example:** Use a small range like [5, 7] or [26, 30] to demonstrate how the bits change and how the algorithm finds the common prefix.
*   **Be comfortable with bitwise operators:** Demonstrate proficiency with `&`, `>>`, and `<<`.

## Revision Checklist
- [ ] Understand the properties of bitwise AND.
- [ ] Recognize that the result is the common prefix of `left` and `right` in binary.
- [ ] Implement the shifting algorithm correctly.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition.

## Similar Problems
*   Single Number III
*   Number of 1 Bits
*   Hamming Distance
*   Counting Bits
*   Power of Two

## Tags
`Bit Manipulation` `Math`
