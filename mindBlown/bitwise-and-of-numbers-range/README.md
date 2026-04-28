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
The solution uses a trick to efficiently find the common prefix of the binary representations of `left` and `right`.

## Intuition
The bitwise AND operation can only turn bits from 1 to 0. It can never turn a 0 to a 1.
If we consider the binary representations of `left` and `right`, any bit that is different between them (i.e., a 0 in `left` and a 1 in `right` at the same position, or vice versa) will eventually become 0 in the bitwise AND of the entire range. This is because there will always be at least one number in the range that has a 0 at that bit position.
Therefore, the result of the bitwise AND of all numbers in the range will be the common prefix of the binary representations of `left` and `right`. All bits after the first differing bit will be 0.

The provided solution cleverly finds this common prefix. The operation `right = right & (right - 1)` has a special property: it turns off the rightmost set bit (the least significant 1-bit) in `right`.
If `right` and `left` are different, it means there's at least one bit that differs. By repeatedly turning off the rightmost set bit of `right`, we are essentially trying to make `right` closer to `left`. When `right` becomes equal to `left`, it means we have successfully eliminated all the bits that were different between the original `left` and `right` (and thus would have become 0 in the final AND result). The value of `right` at this point will be the common prefix.

## Algorithm
1. Initialize `left` and `right` with the given range boundaries.
2. While `right` is strictly greater than `left`:
   a. Update `right` by performing a bitwise AND with `right - 1`. This operation effectively turns off the rightmost set bit of `right`.
3. Once the loop terminates (i.e., `right` is no longer greater than `left`, meaning `right == left`), return the current value of `right`.

## Concept to Remember
*   **Bitwise AND Properties**: The AND operation can only turn bits from 1 to 0. A bit that is 0 in any number within the range will result in a 0 at that position in the final AND result.
*   **Brian Kernighan's Algorithm**: The `n & (n - 1)` trick efficiently clears the least significant set bit of a number `n`.
*   **Binary Representation and Common Prefixes**: The bitwise AND of a range of numbers is determined by the common most significant bits (prefix) of the range's start and end numbers.

## Common Mistakes
*   **Brute-force Iteration**: Trying to iterate through every number from `left` to `right` and performing the AND operation. This will lead to a Time Limit Exceeded error for large ranges.
*   **Misunderstanding the `n & (n - 1)` trick**: Not realizing that this operation specifically targets and removes the rightmost set bit, which is crucial for converging `right` towards `left`.
*   **Incorrectly handling the loop condition**: Stopping the loop too early or too late, leading to an incorrect result. The loop must continue until `right` equals `left`.
*   **Focusing on `left` instead of `right`**: While `left` is important for the final result, the iterative modification should be applied to `right` to efficiently find the common prefix.

## Complexity Analysis
- Time: O(log N) - where N is the maximum value of `right`. The `right = right & (right - 1)` operation clears one bit at a time. In the worst case, it takes as many operations as there are bits in the number (e.g., 32 for a 32-bit integer).
- Space: O(1) - The algorithm uses a constant amount of extra space, regardless of the input size.

## Commented Code
```java
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        // The loop continues as long as 'right' is greater than 'left'.
        // This condition ensures we are still processing bits that might differ.
        while (right > left) {
            // This is the core of the algorithm (Brian Kernighan's trick).
            // 'right & (right - 1)' clears the least significant set bit (rightmost '1') in 'right'.
            // By repeatedly doing this, we are effectively removing bits from 'right' that are
            // guaranteed to become '0' in the final bitwise AND of the range.
            // This is because if a bit differs between 'left' and 'right', there must be a number
            // in the range that has a '0' at that bit position, making the final AND result '0' there.
            right = right & (right - 1);
        }
        // When the loop terminates, 'right' will be equal to 'left'.
        // At this point, 'right' (or 'left') represents the common prefix of the original
        // 'left' and 'right' numbers. All bits that differed have been cleared.
        // This common prefix is the result of the bitwise AND of all numbers in the range.
        return right;
    }
}
```

## Interview Tips
*   **Explain the intuition first**: Clearly articulate why the bitwise AND of a range is determined by the common prefix of the start and end numbers.
*   **Demonstrate the `n & (n - 1)` trick**: Show you understand how this operation works and why it's useful for this problem. Walk through an example.
*   **Discuss the brute-force approach and its limitations**: This shows you've considered simpler solutions and understand why they are not optimal.
*   **Ask clarifying questions**: If unsure about the constraints or edge cases (e.g., `left == right`), ask the interviewer.

## Revision Checklist
- [ ] Understand the property of bitwise AND that it can only turn bits from 1 to 0.
- [ ] Recognize that the result is the common prefix of `left` and `right` in binary.
- [ ] Master the `n & (n - 1)` trick to clear the least significant set bit.
- [ ] Implement the `while (right > left)` loop correctly.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Single Number III
*   Number of 1 Bits
*   Hamming Distance
*   Counting Bits
*   Power of Two

## Tags
`Bit Manipulation` `Math`
