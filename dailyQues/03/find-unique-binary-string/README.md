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
## Quick Revision
Given a list of unique binary strings of length n, find a binary string of length n that is not present in the list.
The solution involves constructing a new binary string by flipping the i-th character of the i-th input string.

## Intuition
The problem guarantees that all input strings are unique and have the same length, say `n`. We need to find *any* binary string of length `n` that is not in the input. Since there are `2^n` possible binary strings of length `n`, and we are given `n` strings, there are always `2^n - n` strings missing. The key insight is that we can construct a string that is guaranteed to be different from *all* the given strings by a simple, systematic process. If we consider the `i`-th character of each of the `n` input strings, we can create a new string where the `i`-th character is the *opposite* of the `i`-th character of the `i`-th input string. This ensures that our constructed string differs from the `i`-th input string at the `i`-th position. Since this holds for all `i` from 0 to `n-1`, our constructed string will be different from every string in the input array.

## Algorithm
1. Initialize an empty `StringBuilder` called `ans` to store the unique binary string.
2. Iterate through the input array `nums` from index `i = 0` to `nums.length - 1`.
3. For each index `i`, get the character at the `i`-th position of the `i`-th string in `nums`. This is `nums[i].charAt(i)`.
4. If the character is '0', append '1' to `ans`.
5. If the character is '1', append '0' to `ans`.
6. After the loop finishes, convert the `StringBuilder` `ans` to a `String` and return it.

## Concept to Remember
*   **Pigeonhole Principle (Implicit):** While not directly stated, the problem relies on the fact that there are more possible binary strings than given strings, guaranteeing a unique one exists.
*   **Diagonalization Argument:** The core idea is similar to Cantor's diagonalization argument used to prove the uncountability of real numbers. We construct a string that differs from each given string at a specific position.
*   **String Manipulation:** Efficiently building a string character by character.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly accessing characters or iterating through the array.
*   **Not handling both '0' and '1':** Forgetting to flip the character correctly (e.g., only flipping '0' to '1' and not '1' to '0').
*   **Using `String` concatenation in a loop:** This can be inefficient in Java due to repeated object creation. `StringBuilder` is preferred.
*   **Assuming the input array is sorted or has a specific structure:** The algorithm works regardless of the order or content of the input strings, as long as they are unique and of the same length.

## Complexity Analysis
*   **Time:** O(n) - The algorithm iterates through the input array `nums` once. For each of the `n` strings, it accesses a single character and appends to a `StringBuilder`. The length of each string is also `n`, but we only access `nums[i].charAt(i)`, which is O(1) access. Appending to `StringBuilder` is amortized O(1). Therefore, the total time complexity is O(n), where n is the number of strings in `nums` (which is also the length of each string).
*   **Space:** O(n) - A `StringBuilder` is used to construct the result string. The maximum length of this string is `n`. Therefore, the space complexity is O(n).

## Commented Code
```java
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        // Initialize a StringBuilder to efficiently build the result string.
        StringBuilder ans = new StringBuilder();
        
        // Iterate through the input array of binary strings.
        // The loop variable 'i' will represent both the index of the string in the array
        // and the index of the character within that string that we will consider.
        for (int i = 0; i < nums.length; i++) {
            // Get the character at the i-th position of the i-th string.
            // This is the character on the "diagonal" if we imagine the strings laid out.
            Character curr = nums[i].charAt(i);
            
            // Append the opposite of the current character to our result string.
            // If the character is '0', append '1'. If it's '1', append '0'.
            // This ensures that the resulting string will differ from nums[i] at index i.
            ans.append(curr == '0' ? '1' : '0');
        }
        
        // Convert the StringBuilder to a String and return it.
        // This string is guaranteed to be unique because it differs from each input string
        // at at least one position (specifically, the i-th position for the i-th string).
        return ans.toString();        
    }
}
```

## Interview Tips
*   **Explain the Diagonalization:** Clearly articulate how the chosen character at `nums[i].charAt(i)` is used to ensure the constructed string is different from `nums[i]`.
*   **Discuss Edge Cases:** While this problem has few edge cases due to constraints (e.g., `n >= 1`), briefly mention what would happen if `nums` was empty or contained non-binary strings (though problem constraints usually prevent this).
*   **Justify Complexity:** Be prepared to explain why the time and space complexity are O(n).
*   **Consider Alternatives (and why they are worse):** Briefly think about other approaches (like generating all `2^n` strings and checking, or using a hash set) and explain why the current greedy approach is optimal.

## Revision Checklist
- [ ] Understand the problem statement: find a binary string not in the input.
- [ ] Grasp the core intuition: construct a string that differs from each input string at a specific position.
- [ ] Implement the algorithm using `StringBuilder` for efficiency.
- [ ] Verify the time complexity is O(n).
- [ ] Verify the space complexity is O(n).
- [ ] Be able to explain the "diagonal" construction.

## Similar Problems
*   [1980] Find Unique Binary String (This problem)
*   [2411] Smallest Subarrays With Maximum Bitwise OR
*   [1371] Find the Longest Substring Containing Vowels in Even Counts (Similar in using bit manipulation/parity)

## Tags
`Array` `String` `Greedy` `Bit Manipulation`
