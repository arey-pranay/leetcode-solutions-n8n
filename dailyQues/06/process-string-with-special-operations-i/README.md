# Process String With Special Operations I

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Simulation`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public String processStr(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(c=='*'){if(!sb.isEmpty()) sb.deleteCharAt(sb.length()-1);}
            else if(c=='#') sb.repeat(sb,1);
            else if(c=='%') sb.reverse();
            else sb.append(c);
        }
        return sb.toString();
    }
}
```

---

---
## Quick Revision
This problem involves processing a string character by character, applying specific operations based on special characters. We use a mutable string builder to efficiently modify the string in place.

## Intuition
The core idea is to process the string sequentially and maintain a "current" state of the processed string. When a special character is encountered, it modifies this current state. A `StringBuilder` in Java is ideal for this because it allows efficient appending, deletion, and reversal, which are the operations required. The "aha moment" is realizing that each character's effect is dependent on the *current* state of the string being built, not the original string.

## Algorithm
1. Initialize an empty `StringBuilder` called `sb`.
2. Iterate through each character `c` in the input string `s`.
3. If `c` is '*':
    a. Check if `sb` is not empty.
    b. If `sb` is not empty, delete the last character from `sb`.
4. If `c` is '#':
    a. This operation is a bit ambiguous in the provided code (`sb.repeat(sb,1)` is not a standard `StringBuilder` method). Assuming it means to duplicate the last character if `sb` is not empty, or perhaps do nothing if `sb` is empty. For the purpose of this analysis, let's assume it means to append the last character of `sb` to itself if `sb` is not empty.
    b. If `sb` is not empty, append `sb.charAt(sb.length() - 1)` to `sb`.
5. If `c` is '%':
    a. Reverse the entire content of `sb`.
6. If `c` is any other character:
    a. Append `c` to `sb`.
7. After iterating through all characters, convert `sb` to a `String` and return it.

*Note: The provided code has a non-standard `sb.repeat(sb,1)` for '#'. A common interpretation for such a character might be to duplicate the last character. If the intention was different, the algorithm would need adjustment.*

## Concept to Remember
*   **Mutable String Operations:** Understanding the efficiency of `StringBuilder` (or similar mutable string structures in other languages) for repeated modifications compared to immutable strings.
*   **Stack-like Behavior:** The '*' operation (deleting the last character) mimics a pop operation from a stack.
*   **In-place String Manipulation:** Efficiently modifying a string without creating numerous intermediate string objects.

## Common Mistakes
*   **Using `String` concatenation in a loop:** This leads to O(n^2) time complexity due to the creation of new `String` objects in each iteration.
*   **Incorrectly handling empty `StringBuilder`:** For operations like deleting the last character or duplicating it, failing to check if the `StringBuilder` is empty can lead to errors.
*   **Misinterpreting special character operations:** The '#' operation in the provided code is non-standard and could be a source of confusion or incorrect implementation if not clarified.
*   **Not reversing correctly:** Forgetting to reverse the entire `StringBuilder` content when '%' is encountered.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the input string `s` once. Each operation on `StringBuilder` (append, deleteCharAt, reverse) takes amortized O(1) time for append/deleteCharAt and O(k) for reverse where k is the current length of the StringBuilder. In the worst case, reverse can take O(N) if the string grows to length N. However, since each character is processed once and operations are generally efficient, the overall time complexity remains linear with respect to the length of the input string.
- Space: O(N) - reason: In the worst case, the `StringBuilder` might store all characters of the input string if no special operations cause deletions. Therefore, the space complexity is proportional to the length of the input string.

## Commented Code
```java
class Solution {
    public String processStr(String s) {
        // Initialize a StringBuilder to efficiently build the processed string.
        StringBuilder sb = new StringBuilder();
        // Iterate over each character in the input string.
        for(char c : s.toCharArray()){
            // If the character is '*', it means delete the last character.
            if(c=='*'){
                // Check if the StringBuilder is not empty before attempting to delete.
                if(!sb.isEmpty()){
                    // Delete the last character from the StringBuilder.
                    sb.deleteCharAt(sb.length()-1);
                }
            }
            // If the character is '#', it means duplicate the last character (assuming this interpretation).
            else if(c=='#'){
                // Check if the StringBuilder is not empty before attempting to duplicate.
                if(!sb.isEmpty()){
                    // Append the last character of the StringBuilder to itself.
                    // Note: The original code `sb.repeat(sb,1)` is not a standard Java StringBuilder method.
                    // This line assumes an intended behavior of duplicating the last character.
                    sb.append(sb.charAt(sb.length()-1));
                }
            }
            // If the character is '%', it means reverse the entire StringBuilder.
            else if(c=='%'){
                // Reverse the content of the StringBuilder in-place.
                sb.reverse();
            }
            // If the character is any other character, append it to the StringBuilder.
            else {
                sb.append(c);
            }
        }
        // Convert the final StringBuilder content to a String and return it.
        return sb.toString();
    }
}
```

## Interview Tips
*   **Clarify Ambiguities:** If presented with non-standard operations like `sb.repeat(sb,1)`, ask the interviewer for clarification on the intended behavior.
*   **Explain `StringBuilder` Choice:** Be ready to explain why `StringBuilder` is preferred over `String` concatenation for performance.
*   **Trace Examples:** Walk through a few examples with the interviewer, showing how the `StringBuilder` changes with each character.
*   **Discuss Edge Cases:** Consider cases like an empty input string, a string with only special characters, or a string where special characters might lead to an empty `StringBuilder`.

## Revision Checklist
- [ ] Understand the purpose of each special character ('*', '#', '%').
- [ ] Implement the logic using `StringBuilder` for efficient modifications.
- [ ] Handle the edge case of an empty `StringBuilder` when performing delete or duplicate operations.
- [ ] Ensure the reversal operation is applied correctly to the entire `StringBuilder`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Backspace String Compare
*   Remove All Adjacent Duplicates In String
*   Remove All Adjacent Duplicates in String II

## Tags
`String` `StringBuilder` `Stack`
