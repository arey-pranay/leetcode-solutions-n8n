# Find Unique Binary String

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Backtracking`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public String findDifferentBinaryString(String[] nums) {
StringBuilder ans = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            Character curr = nums[i].charAt(i);
            ans.append(curr == '0' ? '1' : '0');
        }
        
        return ans.toString();        
    }
}
```

---

---
## Problem Summary
Given a binary string array `nums` of size `n`, where each string in `nums` has length `n`. The task is to find a binary string of length `n` that is not present in `nums`.

## Approach and Intuition
The problem guarantees that a unique binary string exists. The key insight is that we can construct such a string by making a decision for each position independently. For the `i`-th position of our unique binary string, we can look at the `i`-th character of the `i`-th string in the input array `nums`.

If the `i`-th character of `nums[i]` is '0', we can choose '1' for the `i`-th position of our unique string.
If the `i`-th character of `nums[i]` is '1', we can choose '0' for the `i`-th position of our unique string.

By following this strategy, we ensure that our constructed string will differ from *every* string in `nums` at at least one position. Specifically, our constructed string will differ from `nums[i]` at the `i`-th position. Since this holds for all `i` from 0 to `n-1`, our constructed string cannot be equal to any string in `nums`.

This is a constructive proof of existence and a direct algorithm. We iterate through the input array `nums`. For each index `i`, we examine the character at `nums[i].charAt(i)`. Based on this character, we append the opposite character ('0' becomes '1', '1' becomes '0') to our result string.

## Complexity Analysis
- Time: O(n) - We iterate through the input array `nums` once. For each of the `n` strings, we access a single character. Appending to a `StringBuilder` is amortized O(1). Therefore, the total time complexity is proportional to the length of the input array, which is `n`.
- Space: O(n) - We use a `StringBuilder` to construct the result string. The length of the result string is `n`. Therefore, the space complexity is proportional to `n`.

## Code Walkthrough
1. `StringBuilder ans = new StringBuilder();`: Initializes an empty `StringBuilder` to efficiently build the result string.
2. `for (int i = 0; i < nums.length; i++)`: This loop iterates from `i = 0` to `n-1`, where `n` is the number of strings in `nums` (and also the length of each string).
3. `Character curr = nums[i].charAt(i);`: In each iteration, it retrieves the character at the `i`-th position of the `i`-th string in the `nums` array.
4. `ans.append(curr == '0' ? '1' : '0');`: This is the core logic. It checks if `curr` is '0'.
   - If `curr` is '0', it appends '1' to `ans`.
   - If `curr` is '1', it appends '0' to `ans`.
   This ensures that the character at the `i`-th position of the generated string is different from the character at the `i`-th position of `nums[i]`.
5. `return ans.toString();`: After the loop completes, the `StringBuilder` `ans` contains the unique binary string, which is then converted to a `String` and returned.

## Interview Tips
*   **Understand the Constraints:** The problem statement implies `n` strings of length `n`. This is crucial.
*   **Think about Guarantees:** The problem guarantees a unique string exists. This often hints at a constructive approach or a property that simplifies the search.
*   **Diagonal Approach:** The solution cleverly uses the "diagonal" elements (`nums[i].charAt(i)`). This is a common pattern in problems involving 2D-like structures or matrices.
*   **Proof of Correctness:** Be prepared to explain *why* this approach works. The key is that the constructed string differs from *every* input string at *some* position (specifically, the `i`-th position for `nums[i]`).
*   **Edge Cases:** Consider `n=1`. The code should handle this correctly. If `nums = ["0"]`, the output is "1". If `nums = ["1"]`, the output is "0".
*   **StringBuilder vs. String Concatenation:** In Java, using `StringBuilder` for repeated appends is significantly more efficient than using the `+` operator for string concatenation, especially in loops. Mentioning this shows good Java practice.

## Optimization and Alternatives
*   **No significant optimization:** The current O(n) time and O(n) space solution is already optimal given that we need to read the input and construct an output string of length `n`.
*   **Alternative (less efficient) approaches:**
    *   **Brute Force Generation:** Generate all 2^n possible binary strings of length `n` and check each one against the input `nums` to see if it's present. This would be O(2^n * n) time complexity, which is infeasible for larger `n`.
    *   **Using a Set:** Store all strings from `nums` in a `HashSet` for O(1) average time lookups. Then, iterate through all possible binary strings (again, 2^n) and check for existence in the set. This is still O(2^n * n) for generation and O(n) for set insertion, making it inefficient. The diagonal approach avoids generating all possibilities.

## Revision Checklist
- [ ] Did I understand the problem statement and constraints correctly?
- [ ] Is my approach constructive and does it guarantee a unique string?
- [ ] Have I analyzed the time and space complexity accurately?
- [ ] Can I explain the code step-by-step?
- [ ] Have I considered potential interview questions or follow-ups?
- [ ] Are there any obvious edge cases I missed?
- [ ] Is the code clean and readable?

## Similar Problems
*   Permutations of a String
*   Generate Parentheses
*   Combinations
*   Subsets
*   (Problems involving constructing a unique element based on a set of existing elements)

## Tags
`String` `Array` `Math`

## My Notes
Some new algorithm of set theory
