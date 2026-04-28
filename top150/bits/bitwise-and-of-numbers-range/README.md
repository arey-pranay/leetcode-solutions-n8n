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
// Brian Kernighan trick
// right isliye kyonki right se and p bada difference m no. gyb hoga , lekin left kyonki chhoti value hai to bada difference m numbero ka gyb hona ho ni paayge , paryapt ni gyb honge 
// ab gyb kyon kese ho rhe hai - > aur unke hone naa hona equal kese hai ?? -> kyonki 2 no. pados k & ho rhe hai , unse jo 1's 0 ban gye to ab iske sath kuchh bhi kabhi 1 ni hoga 
// 2 pados k hi kyon le rhe hum ? -> becuase It removes the lowest set bit (rightmost 1) from right.

//right contains all the possible 1s that might not survive
```

---

---
## Quick Revision
Given a range [left, right], find the bitwise AND of all numbers in this range.
The solution involves repeatedly clearing the least significant bit of the `right` number until it equals `left`.

## Intuition
The bitwise AND operation can only turn bits from 1 to 0. It can never turn a 0 to a 1.
If we consider the binary representations of numbers in a range `[left, right]`, any bit position that is different between `left` and `right` will eventually become 0 in the final AND result. This is because there will always be at least one number in the range where that bit is 0.
The key insight is that if `left` and `right` have different most significant bits, then all bits to the right of that differing bit will eventually be 0 in the AND result.
The provided solution cleverly uses Brian Kernighan's algorithm to efficiently find the common prefix of `left` and `right`. By repeatedly clearing the least significant bit of `right`, we are essentially removing bits that are guaranteed to become 0 in the final AND result because they differ from `left` at some point. When `right` becomes equal to `left`, all the remaining set bits must be common to all numbers in the range.

## Algorithm
1. Initialize `right` to the given `right` value.
2. While `right` is strictly greater than `left`:
   a. Perform a bitwise AND operation between `right` and `right - 1`. This operation clears the least significant set bit (the rightmost '1') in `right`.
   b. Update `right` with the result of this operation.
3. Once the loop terminates (i.e., `right` is no longer greater than `left`), `right` will hold the bitwise AND of all numbers in the original range.
4. Return `right`.

## Concept to Remember
*   **Bitwise AND Properties**: The AND operation can only turn bits from 1 to 0. A bit that is 0 in any number within the range will result in a 0 at that position in the final AND.
*   **Brian Kernighan's Algorithm**: This algorithm efficiently clears the least significant set bit of a number. The operation `n & (n - 1)` achieves this.
*   **Range Bitwise AND Logic**: The common prefix of the binary representations of `left` and `right` determines the bits that will remain set in the final result. Any bit position where `left` and `right` differ will eventually become 0.

## Common Mistakes
*   **Brute-force Iteration**: Trying to iterate through every number from `left` to `right` and performing the AND operation. This will lead to Time Limit Exceeded for large ranges.
*   **Incorrectly Handling the Loop Condition**: Using `right >= left` instead of `right > left` might lead to an infinite loop or incorrect results if `left` and `right` are initially the same.
*   **Misunderstanding the `n & (n - 1)` Operation**: Not grasping how this operation effectively removes the rightmost set bit and why this is crucial for finding the common prefix.
*   **Focusing on `left` instead of `right`**: While `left` is important for the termination condition, the core operation of clearing bits is applied to `right`.

## Complexity Analysis
*   Time: O(log N) - The loop runs at most as many times as there are bits in the integer (e.g., 32 for a 32-bit integer). Each iteration clears one set bit.
*   Space: O(1) - The algorithm uses a constant amount of extra space.

## Commented Code
```java
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        // The goal is to find the bitwise AND of all numbers from left to right.
        // We can observe that if two numbers have different most significant bits,
        // then all bits to the right of that differing bit will eventually become 0
        // in the bitwise AND of the range.
        // This is because there will always be at least one number in the range
        // where that bit is 0.
        // The strategy is to find the common prefix of 'left' and 'right' in their binary representation.
        // Any bit that differs between 'left' and 'right' will eventually be zeroed out.

        // We use a loop that continues as long as 'right' is greater than 'left'.
        // This loop effectively finds the common prefix by repeatedly clearing the
        // least significant bit (LSB) of 'right'.
        while (right > left) {
            // Brian Kernighan's algorithm: n & (n - 1) clears the least significant set bit.
            // By applying this to 'right', we are removing bits from 'right' that are
            // guaranteed to be 0 in the final AND result because they differ from 'left'
            // at some point in the range.
            // For example, if right = 11010 (26) and left = 10100 (20):
            // Iteration 1: right = 11010 & (11010 - 1) = 11010 & 11001 = 11000 (24)
            // Iteration 2: right = 11000 & (11000 - 1) = 11000 & 10111 = 10000 (16)
            // Now right (16) is not greater than left (20), loop terminates.
            // The common prefix is 10000.
            right = right & (right - 1);
        }
        // When the loop terminates, 'right' will be equal to 'left' (or a value less than 'left'
        // if the original 'right' was significantly larger and had many differing bits).
        // The value of 'right' at this point represents the common prefix of the original
        // 'left' and 'right', which is the bitwise AND of all numbers in the range.
        return right;
    }
}
```

## Interview Tips
*   **Explain the "Why"**: Clearly articulate why the bitwise AND of a range results in the common prefix of `left` and `right`.
*   **Demonstrate Brian Kernighan's Trick**: Show you understand how `n & (n - 1)` works and why it's useful here. Walk through an example.
*   **Discuss Brute-Force First**: Briefly mention the naive approach and why it's inefficient to show you've considered alternatives.
*   **Edge Cases**: Consider cases like `left == right`, `left = 0`, and large numbers.

## Revision Checklist
- [ ] Understand the property of bitwise AND that it can only turn bits from 1 to 0.
- [ ] Recognize that the result is the common prefix of `left` and `right` in binary.
- [ ] Implement Brian Kernighan's algorithm (`n & (n - 1)`).
- [ ] Correctly set up the `while` loop condition (`right > left`).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Single Number III
*   Number of 1 Bits
*   Hamming Distance
*   Counting Bits
*   Power of Two

## Tags
`Bit Manipulation` `Math`
