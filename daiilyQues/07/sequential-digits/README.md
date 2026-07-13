# Sequential Digits

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Enumeration`  
**Time:** O(1)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> a = new ArrayList<>();
        for (int i = 1; i <= 9; ++i) {
        int num = i;
        int nextDigit = i + 1;
            while (num <= high && nextDigit <= 9) {
                num = num * 10 + nextDigit++;
                if (low <= num && num <= high) a.add(num);
            }
        }
        Collections.sort(a);
        return a;
    }
}

```

---

---
## Quick Revision
Find all numbers within a given range [low, high] that have digits in sequential order.
Generate all possible sequential digit numbers and filter those within the range.

## Intuition
The core idea is that sequential digit numbers are formed by appending the next digit in sequence. For example, starting with '1', we can form '12', then '123', and so on. We can systematically generate all such numbers and then check if they fall within the given `low` and `high` bounds. Since the maximum possible sequential digit number is '123456789', we don't need to worry about an infinite generation.

## Algorithm
1. Initialize an empty list `result` to store the sequential digit numbers.
2. Iterate through all possible starting digits from 1 to 9.
3. For each starting digit `i`:
    a. Initialize `currentNumber` to `i`.
    b. Initialize `nextDigit` to `i + 1`.
    c. While `currentNumber` is less than or equal to `high` and `nextDigit` is less than or equal to 9:
        i. Update `currentNumber` by appending `nextDigit`: `currentNumber = currentNumber * 10 + nextDigit`.
        ii. Increment `nextDigit`.
        iii. If `currentNumber` is within the range [`low`, `high`], add it to the `result` list.
4. Sort the `result` list in ascending order.
5. Return the `result` list.

## Concept to Remember
*   **Number Generation:** Systematically constructing numbers based on a pattern.
*   **Range Filtering:** Selecting elements that fall within specified bounds.
*   **Iterative Construction:** Building larger numbers from smaller components.
*   **Sorting:** Ensuring the final output is in the required order.

## Common Mistakes
*   **Generating duplicates:** If not careful, the generation logic might produce the same number multiple times.
*   **Missing edge cases:** Not considering sequential numbers starting from different digits (e.g., 234, 345).
*   **Inefficient generation:** Generating numbers that are clearly outside the `high` bound prematurely.
*   **Not sorting the final list:** The problem usually requires the output to be sorted.

## Complexity Analysis
- Time: O(1) - The maximum number of sequential digit numbers is fixed and small (less than 50). The generation and sorting operations take constant time relative to the input `low` and `high` values, as they are bounded by the maximum possible sequential number (123456789).
- Space: O(1) - The space used by the `result` list is also bounded by the fixed number of sequential digit numbers, making it constant space.

## Commented Code
```java
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        // Initialize an ArrayList to store the sequential digit numbers found.
        List<Integer> a = new ArrayList<>();
        // Iterate through each possible starting digit from 1 to 9.
        for (int i = 1; i <= 9; ++i) {
            // Initialize the current number with the starting digit.
            int num = i;
            // Initialize the next digit to append, which is the starting digit + 1.
            int nextDigit = i + 1;
            // Continue building the number as long as it doesn't exceed 'high' and the next digit is within 1-9.
            while (num <= high && nextDigit <= 9) {
                // Append the next digit to the current number.
                // Example: if num is 1 and nextDigit is 2, num becomes 1*10 + 2 = 12.
                num = num * 10 + nextDigit++; // Increment nextDigit after using it.
                // Check if the newly formed number falls within the given range [low, high].
                if (low <= num && num <= high) {
                    // If it's within the range, add it to our result list.
                    a.add(num);
                }
            }
        }
        // Sort the list of sequential digit numbers in ascending order as required.
        Collections.sort(a);
        // Return the sorted list of sequential digit numbers.
        return a;
    }
}
```

## Interview Tips
*   Clearly explain your generation strategy. Walk through an example like starting with '1' to build '12', '123', etc.
*   Mention the bounded nature of sequential numbers and how it leads to constant time/space complexity.
*   Discuss how you would handle the sorting requirement at the end.
*   Be prepared to discuss alternative approaches, like generating all possible sequential numbers first and then filtering, or generating them in a way that naturally produces sorted output (though the provided solution is simpler).

## Revision Checklist
- [ ] Understand the definition of sequential digits.
- [ ] Devise a method to generate all possible sequential digit numbers.
- [ ] Implement the generation logic correctly, handling all starting digits.
- [ ] Filter the generated numbers based on the `low` and `high` bounds.
- [ ] Ensure the final output list is sorted.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Combinations
*   Permutations
*   Generate Parentheses
*   Letter Combinations of a Phone Number

## Tags
`Backtracking` `Math` `Breadth-First Search` `Depth-First Search` `Sorting`
