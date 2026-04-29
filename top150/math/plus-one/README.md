# Plus One

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Math`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int[] plusOne(int[] digits) {
        boolean allNine = true;
        for(int i : digits){
            if(i != 9){
                allNine = false;
                break;
            }
        }
        if(allNine){
            int[] nines = new int[digits.length+1];
            nines[0] = 1;
            return nines;
        }
        
        int carry =1;
        for(int i =digits.length-1;i>=0;i--){
            if(digits[i] != 9){
                digits[i]++;
                break;
            } else digits[i] = 0;
        }
        return digits;
    }
}
```

---

---
## Quick Revision
Given a non-empty array of digits representing a non-negative integer, increment the integer by one.
Iterate from the rightmost digit, add one, handle carries, and expand the array if necessary.

## Intuition
The core idea is to simulate manual addition. When we add 1 to a number, we start from the least significant digit (the rightmost one). If that digit is less than 9, we simply increment it and we're done. If it's 9, it becomes 0, and we have a "carry" to the next digit to the left. This process continues until we either increment a digit that isn't 9, or we run out of digits. If we run out of digits and still have a carry (meaning all digits were 9s), we need to create a new, larger array with a leading 1.

## Algorithm
1. Iterate through the `digits` array from right to left (from `digits.length - 1` down to `0`).
2. For each digit:
    a. If the current digit is less than 9, increment it by 1 and immediately return the modified `digits` array. This is because the addition is complete.
    b. If the current digit is 9, set it to 0. This signifies a carry-over to the next digit.
3. If the loop completes without returning (meaning all digits were 9s), it implies a carry-over beyond the most significant digit.
4. In this case, create a new array of size `digits.length + 1`.
5. Set the first element of this new array to 1 (representing the carry).
6. All other elements of this new array will be 0 by default, which is correct for a number like 1000...0.
7. Return the new array.

## Concept to Remember
*   **Array Traversal (Reverse):** Efficiently processing elements from the end of an array.
*   **Carry Propagation:** Understanding how a carry affects subsequent digits in arithmetic operations.
*   **Dynamic Array Sizing:** Handling cases where the result requires a larger data structure than the input.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling loop bounds or array indices, especially when dealing with carries.
*   **Not handling the all-nines case:** Failing to create a new, larger array when all input digits are 9s.
*   **Modifying the array in place incorrectly:** When a digit becomes 0 due to a carry, not correctly propagating the carry to the next iteration.
*   **Returning the original array when a new one is needed:** Forgetting to return the newly created array in the all-nines scenario.

## Complexity Analysis
*   **Time:** O(N) - In the worst case (all nines), we iterate through the entire array once to set digits to 0, and then potentially create a new array. In the best case (last digit is not 9), we only iterate once.
*   **Space:** O(1) - If the array does not need to be expanded (i.e., not all digits are 9s), we modify the array in place. O(N) - If all digits are 9s, we need to create a new array of size N+1.

## Commented Code
```java
class Solution {
    public int[] plusOne(int[] digits) {
        // Iterate through the digits array from right to left.
        for (int i = digits.length - 1; i >= 0; i--) {
            // If the current digit is less than 9, we can simply increment it.
            if (digits[i] < 9) {
                // Increment the digit.
                digits[i]++;
                // Since we've handled the addition and there's no carry, we can return the modified array.
                return digits;
            }
            // If the current digit is 9, it becomes 0, and we need to carry over to the next digit.
            digits[i] = 0;
        }

        // If the loop finishes, it means all digits were 9s (e.g., [9, 9, 9]).
        // We need to create a new array with an additional digit at the beginning.
        // The new array will have a size one greater than the original.
        int[] newDigits = new int[digits.length + 1];
        // The most significant digit of the new number is 1 (e.g., 1000 for [9, 9, 9]).
        newDigits[0] = 1;
        // The rest of the digits in newDigits are initialized to 0 by default, which is correct.
        // Return the newly created array.
        return newDigits;
    }
}
```

## Interview Tips
*   **Walk through examples:** Clearly explain your thought process with examples like `[1, 2, 3]`, `[9]`, and `[9, 9]`.
*   **Edge cases:** Specifically mention how you handle the case where all digits are 9s and require array expansion.
*   **Clarity of logic:** Emphasize the simulation of manual addition and the carry mechanism.
*   **Ask clarifying questions:** If unsure about constraints (e.g., maximum number of digits), ask.

## Revision Checklist
- [ ] Understand the problem statement: incrementing an integer represented by an array.
- [ ] Identify the core operation: adding 1 and handling carries.
- [ ] Consider the edge case: all digits are 9s.
- [ ] Implement the right-to-left iteration.
- [ ] Handle incrementing a non-9 digit.
- [ ] Handle setting a 9 digit to 0 and carrying over.
- [ ] Implement the array expansion logic for the all-9s case.
- [ ] Verify time and space complexity.

## Similar Problems
*   [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)
*   [Multiply Strings](https://leetcode.com/problems/multiply-strings/)

## Tags
`Array` `Math`
