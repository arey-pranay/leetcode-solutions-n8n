# Single Number

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Bit Manipulation`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i =0 ; i<nums.length;i++) res ^= nums[i];
        return res;
    }
}
```

---

---
## Quick Revision
Given an array of integers where every element appears twice except for one, find that single one.
This can be solved efficiently using the bitwise XOR operation.

## Intuition
The core idea relies on the properties of the XOR (exclusive OR) operation.
1.  `a ^ a = 0`: XORing a number with itself results in zero.
2.  `a ^ 0 = a`: XORing a number with zero results in the number itself.
3.  XOR is commutative and associative: `a ^ b ^ c` is the same regardless of the order.

If we XOR all the numbers in the array, all the numbers that appear twice will cancel each other out (because `x ^ x = 0`). The only number left will be the single number that appears only once, as it will be XORed with zero (the result of all other pairs canceling out).

## Algorithm
1. Initialize a variable `result` to 0. This variable will store the single number.
2. Iterate through each number in the input array `nums`.
3. For each number, perform a bitwise XOR operation between the current `result` and the number. Update `result` with the outcome of this XOR operation.
4. After iterating through all numbers, the `result` variable will hold the single number that appears only once.
5. Return `result`.

## Concept to Remember
*   Bitwise XOR Operator (`^`): Understanding its properties, especially `a ^ a = 0` and `a ^ 0 = a`.
*   Properties of XOR: Commutativity and associativity, which allow for flexible ordering of operations.
*   Handling Duplicates: How XOR can be used to cancel out pairs of identical elements.

## Common Mistakes
*   Forgetting XOR properties: Not recalling that `a ^ a = 0` and `a ^ 0 = a`.
*   Using other bitwise operators: Trying to use AND (`&`) or OR (`|`) which don't have the same canceling property for duplicates.
*   Incorrect initialization: Initializing `result` to a value other than 0, which would affect the final outcome.
*   Not handling edge cases: Although this problem is simple, considering an empty array (though constraints usually prevent this) or an array with only one element.

## Complexity Analysis
*   Time: O(n) - The algorithm iterates through the array once, performing a constant-time XOR operation for each element.
*   Space: O(1) - The algorithm uses a single variable (`res`) to store the result, regardless of the input array size.

## Commented Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        // Initialize a variable 'res' to 0. This will accumulate the XOR of all numbers.
        // XORing with 0 doesn't change the value, so it's a safe starting point.
        int res = 0;

        // Iterate through each element in the input array 'nums'.
        for(int i = 0; i < nums.length; i++) {
            // Perform a bitwise XOR operation between the current 'res' and the current number nums[i].
            // If a number appears twice, its bits will be XORed with themselves, resulting in 0.
            // The single number will be XORed with 0 (the result of all pairs canceling out),
            // thus retaining its original value.
            res ^= nums[i];
        }

        // After iterating through all numbers, 'res' will hold the value of the single number.
        return res;
    }
}
```

## Interview Tips
*   Explain the XOR properties clearly: Be ready to articulate why `a ^ a = 0` and `a ^ 0 = a`.
*   Walk through an example: Use a small array like `[2, 2, 1]` and show how `0 ^ 2 ^ 2 ^ 1` evaluates to `1`.
*   Discuss alternative (less optimal) solutions: Briefly mention using a HashMap or sorting, and explain why the XOR approach is superior in terms of time and space complexity.
*   Emphasize the "no extra space" constraint: If the interviewer hints at space complexity, highlight that the XOR solution achieves O(1) space.

## Revision Checklist
- [ ] Understand the problem statement: find the single element in an array where others appear twice.
- [ ] Recall XOR properties: `a ^ a = 0`, `a ^ 0 = a`, commutativity, associativity.
- [ ] Implement the XOR accumulation logic.
- [ ] Verify time complexity is O(n).
- [ ] Verify space complexity is O(1).
- [ ] Be prepared to explain the intuition and algorithm.

## Similar Problems
*   Single Number II (LeetCode 137)
*   Single Number III (LeetCode 260)
*   Find the Duplicate Number (LeetCode 287) - though this has a different constraint.

## Tags
`Array` `Bit Manipulation` `Math`
